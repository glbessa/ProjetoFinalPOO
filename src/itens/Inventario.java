package itens;

import java.util.HashMap;
import java.util.Set;
import java.lang.Math;

public class Inventario 
{
	private HashMap<String,Item> itens;
	private int moedas;
	private int limiteDePeso;

	public Inventario(int limiteDePeso)
	{
		itens = new HashMap<String, Item>();
		this.limiteDePeso = limiteDePeso;
	}

	public int pegarMoedas()
	{
		return moedas;
	}

	public void adicionarMoedas(int maisMoedas)
	{
		if (maisMoedas > 0)
			moedas += maisMoedas;
	}

	public int calcularPeso() 
	{
		int pesoTotal = 0;
		for(Item item : itens.values()) 
		{
			pesoTotal += item.pegarPeso();
		}
		pesoTotal += Math.ceil(moedas / 1000);
		return pesoTotal;
	}

	public int pegarLimiteDePeso()
	{
		return limiteDePeso;
	}

	public Set<String> pegarNomes()
	{
		return itens.keySet();
	}
	
	public boolean inserirItem(Item item) {
		if (calcularPeso() + item.pegarPeso() <= limiteDePeso)
		{
			itens.put(item.pegarNome(), item);
			return true;
		}
		return false;
	}

	public Item pegarItem(String nome)
	{
		return itens.get(nome);
	}
	
	public Item removerItem(String nome) {
		Item item = pegarItem(nome);
		if (item != null)
			itens.remove(nome);
		return item;
	}
}
