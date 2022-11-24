package personagens;

import java.util.Random;

public abstract class Personagem {
	private String nome;
	private int vida;
	private int vidaMaxima;
	private int ataque;
	private int defesa;
	
	public Personagem(String nome, int vida) {
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
	
	public boolean estaMorto() 
	{
		if (vida == 0)
			return true;
		else
		    return false;
	}
	
	public void atacar(Personagem oponente)
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
			System.out.println("\n# " + nome + " esta morto!\n");
	}
	
 	public void imprimir() 
	{
		System.out.println("#####################");
		System.out.println("# Nome: " + nome);
		System.out.println("# vida: " + vida);
		System.out.println("#####################");
	}
}
