package questoestrab;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends Pessoa {
	public String arquivoUm;
	public String arquivoDois;
	
	public Admin(String name) {
		super(name);
		this.arquivoUm = "C:\\Users\\alunot7\\Downloads\\TrabalhoPOO2-main\\TrabalhoPOO2-main\\java-comerce-poo\\questoestrab\\arquivostxt\\admins.txt";
		this.arquivoDois = "C:\\Users\\alunot7\\Downloads\\TrabalhoPOO2-main\\TrabalhoPOO2-main\\java-comerce-poo\\questoestrab\\arquivostxt\\biblioteca.txt";
	}
	String cargo;
	int salario;
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	@Override
	public boolean login(String username, String password) {
		ArrayList<String> linhas = this.lerLinhasDoArquivo(this.arquivoUm);
		System.out.println(linhas);
		for (String linhasString : linhas) {
			String[] partes = linhasString.split(";");
			if (username.equals(partes[0])) {
				return password.equals(partes[1]);
			}
		}
		return false;
	}
	
	public void adicionarLinhasAoArquivo(String linha) throws IOException {
		String caminhoDoArquivo = this.arquivoDois;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoArquivo, true))) {
            writer.write(linha);
            writer.newLine();
        }
        
    }
	
	@Override
    public String toString() {
        return "Admin [nome = " + this.name + "]";
        
    }
}
