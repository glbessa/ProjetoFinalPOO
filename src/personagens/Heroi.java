package personagens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import itens.*;

public class Heroi extends Personagem {
	private Arma armaEquipada;
	private Defesa defesaEquipada;
	private int vidaMaxima;
	private Inventario mochila;
	
	public Heroi(String nome, int vida, int vidaMaxima, int ataque, int defesa, int limiteDePeso) {
		super(nome, vida, vidaMaxima, ataque, defesa);
		this.vidaMaxima = vidaMaxima;
		this.mochila = new Inventario(limiteDePeso);
	}
	
	public void alimentar() {
		incrementarVida();
		incrementarVida();
	}

	public Inventario pegarMochila()
	{
		return mochila;
	}

	public void setMochila(Inventario mochila)
	{
		this.mochila = mochila;
	}

	public void equiparArma(Arma arma)
	{
		armaEquipada = arma;
	}

	public Arma pegarArmaEquipada()
	{
		return armaEquipada;
	}

	public void equiparDefesa(Defesa defesa)
	{
		defesaEquipada = defesa;
	}

	public Defesa pegarDefesaEquipada()
	{
		return defesaEquipada;
	}
	
	public int pegarVidaMaxima() 
	{
		return vidaMaxima;
	}

	public void atacar(Personagem oponente)
	{
		Random dado1 = new Random();
		Random dado2 = new Random();
		int n1 = dado1.nextInt(10);
		int n2 = dado2.nextInt(10);

		if (n1 > n2)
		{
			oponente.defender((n1) * (ataque + armaEquipada.pegarDano()));
		}
		else
		{
			this.defender((n2) * oponente.pegarAtaque());
		}
	}

	public void defender(int dano)
	{
		int danoTotal = dano - (this.defesa + defesaEquipada.pegarDefesa());
		int vidaRestante = this.vida - danoTotal;
		if (danoTotal > 0 && vidaRestante >= 0)
			vida -= danoTotal;
		else if (danoTotal > 0)
			vida = 0;
	}
}
