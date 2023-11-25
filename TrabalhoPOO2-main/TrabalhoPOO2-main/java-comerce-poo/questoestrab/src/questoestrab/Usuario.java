package questoestrab;

import java.util.ArrayList;

public class Usuario extends Pessoa {
	private String arquivoUm;
	
	public Usuario(String name) {
		super(name);
		this.arquivoUm = "C:\\Users\\alunot7\\Downloads\\TrabalhoPOO2-main\\TrabalhoPOO2-main\\java-comerce-poo\\questoestrab\\arquivostxt\\usuarios.txt";
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
	
	public static void main(String[] args) {
		Usuario user = new Usuario("afonso");
		if (user.login("afonso", "1234")) {
			System.out.println("login bem feito");
		}
		else {
			System.out.println("login deu errado");
		}
	}
	
	@Override
    public String toString() {
        return "Usuário [nome = " + this.name + "]";
    }
}
