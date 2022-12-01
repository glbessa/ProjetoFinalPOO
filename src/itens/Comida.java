package itens;

public class Comida extends Item
{
	private int bonusDeVida;
	private boolean usado;

	public Comida(String nome, String descricao, int peso, int bonusDeVida)
	{
		super(nome, descricao, peso, 2);
		this.bonusDeVida = bonusDeVida;
		this.usado = false;
	}

	public int pegarBonusDeVida()
	{
		if (!usado)
		{
			usado = true;
			return bonusDeVida;
		}
		return 0;
	}
}
