package jogo;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

import personagens.Personagem;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Room 
{
    private HashMap<String, Room> exits;      // stores exits of this room.
    private HashMap<String, Personagem> personagens;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room() 
    {
        exits = new HashMap<String, Room>();
        personagens = new HashMap<String, Personagem>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
  
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void inserirPersonagem(Personagem personagem) {
    	personagens.put(personagem.pegaNome(), personagem);
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
