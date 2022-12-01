package personagens;

import java.util.Random;

public abstract class Personagem 
{
	private String nome;
	protected int vida;
	private int vidaMaxima;
	protected int ataque;
	protected int defesa;
	
	public Personagem(String nome, int vida, int vidaMaxima, int ataque, int defesa) 
	{
		this.nome = nome;
		this.vida = vida;
		this.vidaMaxima = vidaMaxima;
		this.ataque = ataque;
		this.defesa = defesa;
	}
	
	public String pegarNome() 
	{
		return nome;
	}
	
	public int pegarVida() 
	{
		return vida;
	}

	public int pegarVidaMaxima()
	{
		return vidaMaxima;
	}

	public int pegarAtaque()
	{
		return ataque;
	}

	public int pegarDefesa()
	{
		return defesa;
	}
	
	public boolean estaMorto() 
	{
		return vida == 0;
	}
	
	public void atacar(Personagem oponente)
	{
		Random gerador = new Random();
		int dado1 = gerador.nextInt(10);
		int dado2 = gerador.nextInt(10);

		
		if (dado1 > dado2)
		{
			oponente.defender((dado1) * ataque);
		}
		else
		{
			this.defender((dado2) * oponente.pegarAtaque());
		}
	}

	public void defender(int dano)
	{
		int danoTotal = dano - defesa;
		int vidaRestante = vida - danoTotal;
		if (danoTotal > 0 && vidaRestante >= 0)
			vida -= danoTotal;
		else if (danoTotal > 0)
			vida = 0;
	}

	public void incrementarVida()
	{
		if (vida < pegarVidaMaxima())
			vida++;
	}
	
	public void decrementarVida()
	{
		if (vida > 0)
			vida--;
		//if (vida == 0)
			//throw new Exception("\n# " + nome + " esta morto!\n");
	}
}
