package questoestrab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Pessoa {
	public String name;
	public String arquivoDois;
	
	public abstract boolean login(String username, String password);
	public Pessoa(String name) {
		this.name = name;
		this.arquivoDois = "C:\\Users\\alunot7\\Downloads\\TrabalhoPOO2-main\\TrabalhoPOO2-main\\java-comerce-poo\\questoestrab\\arquivostxt\\biblioteca.txt";
	}
	
	public ArrayList<String> lerLinhasDoArquivo(String caminhoArquivo) {
        ArrayList<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linhas;
    }
	
	public String removerProduto(String nomeproduto) throws IOException {
		ArrayList<String> linhas = this.lerLinhasDoArquivo(this.arquivoDois);
		System.out.println("");
		int counter = 0;
		for (counter = 0; counter < linhas.size(); counter += 1) {
			String linhasString = linhas.get(counter);
			String[] partes = linhasString.split(";");
			if (nomeproduto.equals(partes[0])) {
				System.out.println("");
				this.removerLinha(counter);
				return "Produto foi removido";
			}
		}
		System.out.println("");
		return "Produto não foi encontrado";
	}
	

    public void removerLinha(int numeroLinhaParaRemover) throws IOException {
        File arquivoOriginal = new File(this.arquivoDois);
        File arquivoTemporario = new File(this.arquivoDois);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemporario))) {

            String linhaAtual;
            int numeroLinhaAtual = 1;

            while ((linhaAtual = reader.readLine()) != null) {
                if (numeroLinhaAtual != numeroLinhaParaRemover) {
                    writer.write(linhaAtual);
                    writer.newLine();
                }
                numeroLinhaAtual++;
            }
        }

        if (arquivoOriginal.delete()) {
            if (!arquivoTemporario.renameTo(arquivoOriginal)) {
                throw new IOException("Não foi possível renomear o arquivo temporário para o original.");
            }
        } else {
            throw new IOException("Não foi possível excluir o arquivo original.");
        }
    }
	
}
