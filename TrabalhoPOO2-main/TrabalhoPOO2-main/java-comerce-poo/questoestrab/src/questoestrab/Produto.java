package questoestrab;
import java.io.BufferedReader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat; 
import java.text.DecimalFormat;

public class Produto implements Frete {
	private String arquivoUm;
	private String  nome;
	private float preco;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Produto(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
		this.arquivoUm = "C:\\Users\\alunom33\\questoestrab\\arquivostxt\\biblioteca.txt";
	}
	public float CalcularFrete() {
		DecimalFormat df = new DecimalFormat("#,###.00");
    	String random = df.format(Math.random()*100);
    	random = random.replace(",", ".");
    	return Float.parseFloat(random);
	}
	
	public static void main(String[] args) {
		Produto test = new Produto("fulano", (float) 50.40);
		float frete = test.CalcularFrete();
		System.out.println(frete);
		test.criarProduto("Breno", (float) 25.75);
	}
	
	public void criarProduto(String nome, float preco) {
		// interagir com o arquivo para criar o produto no arquivo
		File file = new File (this.arquivoUm);
		try {
			FileWriter fw = new FileWriter (file, true);
			BufferedWriter bw = new BufferedWriter (fw);
			String stringFloat = Float.toString(preco);
			String linha = nome + ", " + stringFloat + "\n";
			bw.write(linha);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Ocorreu um erro ao adicionar a linha ao arquivo: " + e.getMessage());
		}
	}
}
