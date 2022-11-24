package itens;

public class Inverntario 
{
	private Hashmap<String,Item> itens;

	public Inventario()
	{
		itens = new HashMap<String, Item>;
	}

	public int calcularPeso() 
	{
		int pesoTotal = 0;
		for(Item item : itens.values()) {
			pesoTotal += item.pegarPeso();
		}
		return pesoTotal;
	}

	public Set<String> pegarNomes()
	{
		return itens.keySet();
	}
	
	public void inserirItem(Item item) {
		if (calcularPeso() + item.pegarPeso() <= limiteDePeso)
			itens.put(item.pegaNome(), item);
		else
			throw new Exception("O inventario nao pode carregar mais itens na mochila!");
	}

	public Item pegarItem(String nome)
	{
		return itens.get(nome);
	}
	
	public Item removerItem(String nome) {
		Item item = pegarItem(nome);
		if (item != null)
			itens.remove(nome);
		else
			throw new Exception("Item " + nome + " nao encontrado!");
		return item;
	}
}
