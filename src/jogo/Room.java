package jogo;

import java.util.Set;

import gui.LerImagensSalas;

import java.util.HashMap;
import java.util.Iterator;

import personagens.Personagem;

public class Room 
{
    private LerImagensSalas imagemSala;
    private String name;
    private String description;
    private HashMap<String, Room> exits;     
    private HashMap<String, Personagem> personagens;
    

    public Room(String description, String name) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        personagens = new HashMap<String, Personagem>();
    }


    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void onChangeRoom(Room currentRoom){
        lerImagemRoom(currentRoom);
    }
 
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

   
    public String getDescription()
    {
        return description;
    }
   
    public String getName()
    {
        return name;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void inserirPersonagem(Personagem personagem) {
    	personagens.put(personagem.pegaNome(), personagem);
    }

    public LerImagensSalas lerImagemRoom(Room currentRoom){
        imagemSala = new LerImagensSalas(currentRoom.getName());
        return imagemSala;
    }
    
    private String pegaPersonagens() {
    	String lista = "Oponents:";
    	for(String nome : personagens.keySet()) {
    		lista += " " + nome;
    	}
    	return lista;
    }
    
    public Personagem pegaPersonagem(String nome) {
    	return personagens.get(nome);
    }
    
    // *** Novos metodos  inseridos por causa da GUI *******************
    
    public Set<String> pegaConjuntoDePersonagens() {
    	return personagens.keySet();
    }

}

