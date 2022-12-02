package personagens;

import java.util.Random;

public abstract class Personagem 
{
	private String nome;
	protected int vida;
	private int vidaMaxima;
	private int ataque;
	private int defesa;
	
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

	public void recuperarVida(int vidaExtra)
	{
		int novaVida = vidaExtra + pegarVida();
		if (novaVida < 0)
			vida = 0;
		else if (novaVida > pegarVidaMaxima())
			vida = vidaMaxima;
		else
			vida = novaVida;
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
}
