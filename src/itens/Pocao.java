package itens;

public class Pocao extends Item
{
	private int bonusDeVida;
	private boolean usado;

	public Pocao(String nome, String descricao, int peso, int bonusDeVida)
	{
		super(nome, descricao, peso, 3);
		this.bonusDeVida = bonusDeVida;
		this.usado = false;
	}

	public int pegarBonusDeVida()
	{	
		if(!usado)
		{
			usado = true;
			return bonusDeVida;
		}
		return 0;
	}
}
