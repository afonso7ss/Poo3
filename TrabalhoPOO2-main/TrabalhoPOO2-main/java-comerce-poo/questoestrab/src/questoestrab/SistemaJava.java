package questoestrab;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SistemaJava extends JFrame {
    private String arquivoUm;

    public SistemaJava() {
        this.arquivoUm = "C:\\Users\\alunot7\\Downloads\\TrabalhoPOO2-main\\TrabalhoPOO2-main\\java-comerce-poo\\questoestrab\\arquivostxt\\biblioteca.txt";
    }

    public void ShowGUI() {
        setTitle("Exemplo de Lista GUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<String> elementosLista = lerLinhasDoArquivo(this.arquivoUm);

        try {
            String[] arrayElementos = new String[elementosLista.size()];
            elementosLista.toArray(arrayElementos);
            JList<String> lista = new JList<>(arrayElementos);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            for (String linha : elementosLista) {
                textArea.append(linha + "\n");
            }
            JScrollPane painelRolagem = new JScrollPane(textArea);
            add(painelRolagem, BorderLayout.CENTER);
            setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao mostrar GUI: " + e);
        }
    }

    private ArrayList<String> lerLinhasDoArquivo(String caminhoArquivo) {
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
    
    public Map<Integer, Object> identificarLogin() {
		System.out.println("");
		System.out.println("Qual login você quer fazer? 1 para usuário 2 para admin");
		int escolha = new Scanner(System.in).nextInt();
		if (escolha == 1) {
			System.out.println("Digite um nome: ");
			String nome = new Scanner(System.in).nextLine();
			System.out.println("Digite uma senha: ");
			String senha = new Scanner(System.in).nextLine();
			Usuario usuario = new Usuario(nome);
			usuario.login(nome, senha);
			Map<Integer, Object> meuMapa = new HashMap<>();
			meuMapa.put(1, 1);
			meuMapa.put(1, usuario);
			return meuMapa;
		}
		else if (escolha == 2) {
			System.out.println("Digite um nome: ");
			String nome = new Scanner(System.in).nextLine();
			System.out.println("Digite uma senha: ");
			String senha = new Scanner(System.in).nextLine();
			Admin admin = new Admin(nome);
			admin.login(nome, senha);
			Map<Integer, Object> meuMapa = new HashMap<>();
			meuMapa.put(1, 2);
			meuMapa.put(1, admin);
			return meuMapa;
		}
		else {
			System.out.println("Escolha uma opção valida!!!");
			// System.out.println("Essas é a lista com os usuários: ");
			System.out.println("");
			return null;
		}
    }
    
    public void MostrarProdutos() {
    	SwingUtilities.invokeLater(() -> {
            SistemaJava showsistema = new SistemaJava();
            showsistema.ShowGUI();
            showsistema.setVisible(true);
        });
    }
    
    public static void main(String[] args) throws IOException {
    	// tem que começar fazendo o login
    	SistemaJava sistema = new SistemaJava();
    	// tem que fazer um loop para fazer o login, se válido,
    	while (1 == 1) {
    		// dar as opções do que o usuário quer fazer; 
    		Map resposta = sistema.identificarLogin();
    		Map.Entry<Integer, Object> entrada = (Entry<Integer, Object>) resposta.entrySet().iterator().next();
    		Integer chave = entrada.getKey();
    		Object value = entrada.getValue();
    		// - mostrar produtos
    		if (entrada.getKey() == 1) {
    			sistema.MostrarProdutos();
    			Usuario usuario = (Usuario) entrada.getValue();
    			System.out.println(usuario.toString());
    			System.out.println("O que voce quer fazer? 1 para remover ou 2 para mostrar produtos");
    			int escolha = new Scanner(System.in).nextInt();
    			if (escolha == 1) {
    				System.out.println("Digite o nome do produto");
    				String produto = new Scanner(System.in).nextLine();
    				String remocao = usuario.removerProduto(produto);
    				System.out.print(remocao);
    			} else {
    				sistema.MostrarProdutos();
    			}
    		} else if(entrada.getKey() == 2) {
    			sistema.MostrarProdutos();
    			Admin admin = (Admin) entrada.getValue();
    			System.out.println(admin.toString());
    			System.out.println("O que voce quer fazer? 1 para inserir e 2 pra remover");
    			int escolha = new Scanner(System.in).nextInt();
    			if (escolha == 1) {
    				System.out.println("Siga o padrão de escrita: ['produto'];['valor']");
    				String produto = new Scanner(System.in).nextLine();
    				admin.adicionarLinhasAoArquivo(produto);
    				System.out.print("produto adicionado");
    			} else if (escolha == 2) {
    				System.out.println("Digite o nome do produto");
    				String produto = new Scanner(System.in).nextLine();
    				String remocao = admin.removerProduto(produto);
    				System.out.print(remocao);
    			}
    			
    		} else {
    			System.out.println("Sistema encerrado");
    			break;
    		}
    	}
	}
}
