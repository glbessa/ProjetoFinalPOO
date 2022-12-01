package jogo;

import java.util.Set;

import gui.LerImagensSalas;

import java.util.HashMap;
import java.util.Iterator;

import personagens.Personagem;

import itens.Item;

public class Sala
{
    private LerImagensSalas imagemSala;
    private String nome;
    private String descricao;
    private HashMap<String, Sala> saidas;     
    private HashMap<String, Personagem> personagens;
	private HashMap<String, Item> itens;

    public Sala(String nome, String descricao) 
    {
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<String, Sala>();
        personagens = new HashMap<String, Personagem>();
		itens = new HashMap<String, Item>();
    }

    public void setSaida(String direction, Sala neighbor) 
    {
        saidas.put(direction, neighbor);
    }

    public void onChangeSala(Sala currentSala){
        lerImagemSala(currentSala);
    }
   
    public String pegarDescricao()
    {
        return descricao;
    }
   
    public String pegarNome()
    {
        return nome;
    }

    public Sala pegarSaida(String direcao) 
    {
        return saidas.get(direcao);
    }
    


    public void inserirPersonagem(Personagem personagem) {
    	personagens.put(personagem.pegarNome(), personagem);
    }

    public LerImagensSalas lerImagemSala(Sala salaAtual){
        imagemSala = new LerImagensSalas(salaAtual.pegarNome());
        return imagemSala;
    }
    
    public Personagem pegarPersonagem(String nome) {
    	return personagens.get(nome);
    }

    public Set<String> pegarPersonagens() {
    	return personagens.keySet();
    }

	public Item pegarItem(String nome)
	{
		return itens.get(nome);
	}

	public Item inserirItem(Item item)
	{
		return itens.put(item.pegarNome(), item);
	}

	public Set<String> pegarItens()
	{
		return itens.keySet();
	}



}

