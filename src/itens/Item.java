package itens;

public abstract class Item {
	private String nome;
	private String descricao;
	private int peso;
	
	public Item(String nome, String descricao, int peso) 
	{
		this.nome = nome;
		this.descricao = descricao;
		this.peso = peso;
	}
	
	public String pegarNome() {
		return nome;
	}
	
	public String pegarDescricao() {
		return descricao;
	}
	
	public int pegarPeso() {
		return peso;
	}
}
