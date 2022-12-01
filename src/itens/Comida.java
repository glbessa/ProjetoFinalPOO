package itens;

public class Comida extends Item
{
	private int bonusDeVida;
	private boolean usado;

	public Comida(String nome, String descricao, int peso, int bonusDeVida)
	{
		super(nome, descricao, peso);
		this.bonusDeVida = bonusDeVida;
		this.usado = false;
	}

	public int pegarBonusDeVida()
	{
		if (!usado)
			return bonusDeVida;
		return 0;
	}
}
