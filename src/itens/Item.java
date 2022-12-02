package itens;

public abstract class Item {
	private String nome;
	private String descricao;
	private int peso;
	private int categoria;
	
	public Item(String nome, String descricao, int peso, int categoria) 
	{
		this.nome = nome;
		this.descricao = descricao;
		this.peso = peso;
		this.categoria = categoria;
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

	public int pegarCategoria()
	{
		return categoria;
	}
}
