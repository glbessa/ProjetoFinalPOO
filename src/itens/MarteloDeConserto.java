package itens;

import java.util.Random;

public class MarteloDeConserto extends Equipamento
{
	public MarteloDeConserto(String nome, String descricao, int peso)
	{
		super(nome, descricao, peso);
	}

	public int usar()
	{
		if (!temDurabilidade())
			return 0;

		Random gerador = new Random();
		return gerador.nextInt(101);
	}
}
