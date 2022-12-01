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

	public void equiparDefesa(Defesa defesa)
	{
		defesaEquipada = defesa;
	}
	
	public int pegarVidaMaxima() 
	{
		return vidaMaxima;
	}

	public void atacar(Personagem oponente)
	{
		Random gerador = new Random();
		int dado1 = gerador.nextInt(10);
		int dado2 = gerador.nextInt(10);

		if (dado1 > dado2)
		{
			oponente.defender((dado1) * (ataque + armaEquipada.pegarDano()));
		}
		else
		{
			this.defender((dado2) * oponente.pegarAtaque());
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
