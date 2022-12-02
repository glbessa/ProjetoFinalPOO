package personagens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import itens.*;

public class Heroi extends Personagem {
	private Arma armaEquipada;
	private Defesa defesaEquipada;
	private Inventario mochila;
	private Boolean temDefesaEquipada;
	
	public Heroi(String nome, int vida, int vidaMaxima, int ataque, int defesa, int limiteDePeso) {
		super(nome, vida, vidaMaxima, ataque, defesa);
		this.temDefesaEquipada = false;
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

	public void atacar(Personagem oponente)
	{
		Random gerador = new Random();
		int dado1 = gerador.nextInt(10);
		int dado2 = gerador.nextInt(10);
		

		if (dado1 > dado2)
		{
			int dadoMultiplicadorAtaque = gerador.nextInt(2) + 1;
			System.out.println("DADO ATAUQ" + dadoMultiplicadorAtaque);
			oponente.defender(dadoMultiplicadorAtaque * (ataque + armaEquipada.pegarDano()));
		}
		else
		{
			int dadoMultiplicadorDefesa = gerador.nextInt(2) + 1;
			System.out.println("DADO DEFEQ" + dadoMultiplicadorDefesa);
			this.defender(oponente.pegarAtaque() - dadoMultiplicadorDefesa);
		}

		if (oponente.estaMorto())
			mochila.adicionarMoedas(gerador.nextInt(500));
	}

	public void defender(int dano)
	{
		if(temDefesaEquipada == true){
			int danoTotal = dano - (this.defesa + defesaEquipada.pegarDefesa());
			int vidaRestante = this.vida - danoTotal;
			if (danoTotal > 0 && vidaRestante >= 0)
				vida -= danoTotal;
			else if (danoTotal > 0)
				vida = 0;
		} else {
			int danoTotal = dano - this.defesa;
			int vidaRestante = this.vida - danoTotal;
			if (danoTotal > 0 && vidaRestante >= 0)
				vida -= danoTotal;
			else if (danoTotal > 0)
				vida = 0;
		}
		
	}
}
