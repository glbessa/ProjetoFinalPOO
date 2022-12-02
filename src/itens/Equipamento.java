package itens;

abstract class Equipamento extends Item implements Desgastavel
{
	private int durabilidade;

	public Equipamento(String nome, String descricao, int peso, int categoria)
	{
		super(nome, descricao, peso, categoria);
		this.durabilidade = 100;
	}

	public void desgastar(int desgaste)
	{
		int novaDurabilidade = durabilidade - desgaste;
		if (novaDurabilidade > 0)
			durabilidade -= desgaste;
		else
			durabilidade = 0;
	}

	public void consertar(int conserto)
	{
		int novaDurabilidade = (durabilidade + conserto);
		if (novaDurabilidade <= 100)
			durabilidade += conserto;
		else
			durabilidade = 100;
	}

	public boolean temDurabilidade()
	{
		return durabilidade != 0;
	}

	public int pegarDurabilidade()
	{
		return durabilidade;
	}
}
