package personagens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import itens.*;

public class Heroi extends Personagem {
	private Arma armaEquipada;
	private Defesa defesaEquipada;
	private Inventario mochila;
	
	public Heroi(String nome, int vida, int vidaMaxima, int ataque, int defesa, int limiteDePeso) {
		super(nome, vida, vidaMaxima, ataque, defesa);
		this.mochila = new Inventario(limiteDePeso);
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

	public void atacar(Personagem oponente)
	{
		Random gerador = new Random();
		int n1 = gerador.nextInt(10);
		int n2 = gerador.nextInt(10);

		if (n1 > n2)
		{
			oponente.defender((pegarAtaque() + armaEquipada.pegarDano()));
		}
		else
		{
			this.defender(oponente.pegarAtaque());
		}

		if (oponente.estaMorto())
			mochila.adicionarMoedas(gerador.nextInt(500));
	}

	public void defender(int dano)
	{
		int danoTotal = dano - (pegarDefesa() + defesaEquipada.pegarDefesa());
		int vidaRestante = this.vida - danoTotal;
		if (danoTotal > 0 && vidaRestante >= 0)
			vida -= danoTotal;
		else if (danoTotal > 0)
			vida = 0;
	}
}
