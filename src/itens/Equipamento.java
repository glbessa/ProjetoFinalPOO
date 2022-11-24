package itens;

abstract class Equipamento extends Item implements Desgastavel
{
	private int bonus;
	private int durabilidade;

	public Equipamento(String nome, String descricao, int peso, int bonus)
	{
		super(nome, descricao, peso);
		this.bonus = bonus;
		this.durabilidade = 100;
	}

	public void desgartar(int desgaste)
	{
		if ((durabilidade + desgaste) <= 100 && (durabilidade + desgaste) >= 0)
			durabilidade -= desgaste;
	}

	public void consertar(int conserto)
	{
		if ((durabilidade + conserto) >= 0 && (durabilidade + conserto) <= 100)
			durabilidade += conserto;
	}

	public int pegarDurabilidade()
	{
		return durabilidade;
	}

	public int pegarBonus()
	{
		return Math.round(bonus * durabilidade / 100);
	}
}
