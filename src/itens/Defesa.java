package itens;

public class Defesa extends Equipamento
{
	private int defesa;

	public Defesa(String nome, String descricao, int peso, int defesa)
	{
		super(nome, descricao, peso);

		this.defesa = defesa;
	}

	public int pegarDefesa()
	{
		if (this.temDurabilidade())
			return defesa;
		return 0;
	}
}
