package itens;

import java.lang.Math;

public class Arma extends Equipamento
{
	private int dano;

	public Arma(String nome, String descricao, int peso, int dano)
	{
		super(nome, descricao, peso);

		this.dano = dano;
	}

	public int pegarDano()
	{
		if (this.temDurabilidade())
			return dano;
		return 0;
	}
}
