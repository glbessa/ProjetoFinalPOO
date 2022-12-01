package itens;

public class Pocao extends Item
{
	private int bonusDeVida;
	private boolean usado;

	public Pocao(String nome, String descricao, int peso, int bonusDeVida)
	{
		super(nome, descricao, peso);
		this.bonusDeVida = bonusDeVida;
		this.usado = false;
	}

	public int pegarBonusDeVida()
	{	
		if(!usado)
			return bonusDeVida;
		return 0;
	}
}
