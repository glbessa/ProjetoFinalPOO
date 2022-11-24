package personagens;

import java.util.HashMap;
import java.util.Map;
import itens.*;

public class Heroi extends Personagem {
	private Arma armaEquipada;
	private int vidaMaxima;
	private int limiteDePeso;
	private Map<String, Item> mochila;
	
	public Heroi(String nome, int vida, int vidaMaxima, int limiteDePeso) {
		super(nome, vida);
		this vidaMaxima = vidaMaxima;
		this.limiteDePeso = limiteDePeso;
		mochila = new HashMap();
	}
	
	private int calcularPeso() {
		int pesoTotal = 0;
		for(Item item : mochila.values()) {
			pesoTotal += item.pegaPeso();
		}
		return pesoTotal;
	}
	
	public void inserirItem(Item item) {
		if (calcularPeso() + item.pegaPeso() <= limiteDePeso) {
			mochila.put(item.pegaNome(), item);
		} else {
			System.out.println("\n# O " + pegaNome() + " nao pode carregar mais itens na mochila!\n");
		}
	}
	
	public Item removerItem(String nome) {
		Item item = mochila.get(nome);
		if (item != null)
			mochila.remove(nome);
		else
			System.out.println("\n# O item '" + nome + "' nao esta na mochila do heroi!\n");
		return item;
	}
	
	public void alimentar() {
		incremento();
		incremento();
	}
	
	public void lutar(Personagem oponente) {
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
	}

	public void equiparItem(Item equipamento)
	{

	}
	
	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Dados do Heroi");
		super.imprimir();
	}
	
	public int peg vidaMaxima() {
		return vidaMaxima;
	}
}
