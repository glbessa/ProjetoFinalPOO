package personagens;

import java.util.Random;

public abstract class Personagem {
	private String nome;
	private int vida;
	private int vidaMaxima;
	private int ataque;
	private int defesa;
	
	public Personagem(String nome, int vida, int vidaMaxima, int ataque, int defesa) 
	{
		this.nome = nome;
		this.vida = vida;
	}
	
	public String pegarNome() {
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
		int dado1 = gerador.nextInt(6);
		int dado2 = gerador.nextInt(6);

		
		if (dado1 > dado2)
		{
			oponente.defender((dado1 - dado2) * ataque);
		}
		else if (dado1 < dado2)
		{
			this.defender((dado1 - dado2) * oponente.pegarAtaque())
		}


	}

	public void defender(int dano)
	{

	}

	int dadoDoHeroi = sorte(6);
		int dadoDoOponente = sorte(6);
		
		if (dadoDoHeroi == dadoDoOponente) {
			decremento(); // Na vida do proprio heroi
			oponente.decremento();
		} else if (dadoDoHeroi > dadoDoOponente) {
			incremento(); // Na vida do proprio heroi
			oponente.decremento();
		} else { // Quando o oponente vence
			decremento(); // Na vida do proprio heroi
			oponente.incremento();
		}

	public void incrementarVida()
	{
		if (vida < pegaVidaMaxima())
			vida++;
	}
	
	public void decrementarVida()
	{
		if (vida > 0)
			vida--;
		if (vida == 0)
			throw new Exception("\n# " + nome + " esta morto!\n");
	}
}
