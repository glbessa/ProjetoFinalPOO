package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import personagens.Heroi;
import personagens.Personagem;
import personagens.Vilao;

import jogo.Command;
import jogo.CommandWord;
import jogo.Game;
import jogo.Parser;
import jogo.Room;

public class JanelaPrincipal extends JFrame implements ActionListener{
	private Parser parser;
    private Room currentRoom;
    private Heroi heroi;
    
    // Atributos de componetes da GUI
	private PainelDoHeroi pHeroi;
    private PainelDoInimigo pVidaInimigos;
	private PainelDaSala painelSala;
    // private PainelItensSala painelSala;
    private PainelDaSala pRoom;
	private JTextArea console;
	private JButton bAtacar; 
	private JButton bPegar; 
	private JButton bSoltar; 
	private JButton bNorte; 
	private JButton bSul; 
	private JButton bLeste; 
	private JButton bOeste; 
    
    /**
     * Create the game and initialise its internal map.
     */
    public JanelaPrincipal() 
    {
    	// Definicoes originais da Classe Game
        createRooms();
        parser = new Parser();
        heroi = new Heroi("Cristopher", 8, 10, 100);
        
        // Chamada do metodo que vai construir a janela principal
        initGUI();
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, mainEntrance, lobby, armorRoom, dungeon, library, bossRoom, hallwayToArmorRoom, hallwayToBoss, hallwayToLibrary;
      
     
        outside = new Room();
        mainEntrance = new Room();
        armorRoom = new Room();
        dungeon = new Room();
        library = new Room();
        bossRoom = new Room();
        lobby = new Room();
        hallwayToArmorRoom = new Room();
        hallwayToLibrary = new Room();
        hallwayToBoss = new Room();

        outside.setExit("west", mainEntrance);

        mainEntrance.setExit("east", outside);
        mainEntrance.setExit("south", lobby);

        lobby.setExit("east", hallwayToLibrary);
        lobby.setExit("west", hallwayToArmorRoom);
        lobby.setExit("south", hallwayToBoss);
        lobby.setExit("north", mainEntrance);

        hallwayToArmorRoom.setExit("east", lobby);
        hallwayToArmorRoom.setExit("west", armorRoom);
        hallwayToLibrary.setExit("west", lobby);
        hallwayToLibrary.setExit("east", library);
        hallwayToBoss.setExit("east", bossRoom);
        hallwayToBoss.setExit("north", lobby);

        library.setExit("west", hallwayToLibrary);
        armorRoom.setExit("east", hallwayToArmorRoom);
        armorRoom.setExit("south", dungeon);
        dungeon.setExit("north", armorRoom);

        Personagem diabinho = new Vilao("Diabinho", 2);
        Personagem olhoDoMal = new Vilao("Olhão do Mal", 2);
        Personagem assassino = new Vilao("Assasino", 2);
        Personagem fantasmaCaolho = new Vilao("Fantasma Caolho", 2);
        Personagem cavaleiro = new Vilao("Cavaleiro Demoníaco", 2);
        Personagem michaelMyers = new Vilao("Michael Myers", 2);
        Personagem capanga1 = new Vilao("Diabinho Capanga 1", 2);
        Personagem capanga2 = new Vilao("Diabinho Capanga 2", 2);
        Personagem capanga3 = new Vilao("Diabinho Capanga 3", 2);
        Personagem diabao = new Vilao("Diabão", 2);


        mainEntrance.inserirPersonagem(diabinho);

        lobby.inserirPersonagem(assassino);
        lobby.inserirPersonagem(olhoDoMal);

        library.inserirPersonagem(fantasmaCaolho);

        armorRoom.inserirPersonagem(cavaleiro);

        dungeon.inserirPersonagem(michaelMyers);

        bossRoom.inserirPersonagem(diabao);
        bossRoom.inserirPersonagem(capanga1);
        bossRoom.inserirPersonagem(capanga2);
        bossRoom.inserirPersonagem(capanga3);
     

        currentRoom = outside;  // Começa o jogo fora da mansão
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    // Remover!
    public void play() 
    {            
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LOOK) {
        	look();
        }
        else if (commandWord == CommandWord.ATTACK) {
        	attack(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    // Remover!
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
    	//*
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
        	console.append("\nGo where?\n");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
        	console.append("\nThere is no door to " + direction + "!\n");
        }
        else {
            currentRoom = nextRoom;
            painelSala.mudarDeSala(currentRoom);
            // painelSala.mudarDeSala(currentRoom);
        }
        //*/
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    // Remover!
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    // Remover!
    private void look()
    {
        System.out.println();
    }
    
    private void attack(Command command) {
    	//*
    	Personagem oponente;
    	
    	if (!command.hasSecondWord()) {
    		console.append("\nAttack who?\n");
    		return;
    	}
    	
    	oponente = currentRoom.pegaPersonagem(command.getSecondWord());
    	if (oponente != null) {
    		heroi.lutar(oponente);
    		pHeroi.atualizar();
    		painelSala.atualizarInimigo();
    	} else {
    		console.append("\nThe character '"+ command.getSecondWord() +"' is not in the current room.\n");
    	}
    	//*/
    }
    
    // ******************************************************
    // Metodos relacionadas a GUI
    // ******************************************************
	
    public void initGUI() {
    	setTitle("Caverna do Dragao");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        
        // Construcao dos paineis principais Esquerda/Direita #############################
        //*
        JPanel painelEsq = new JPanel();
        JPanel painelDir = new JPanel();
        add(painelEsq);
        add(painelDir);
        painelEsq.setBackground(new Color(255, 0, 0));
        painelDir.setBackground(Color.white);
        painelEsq.setVisible(true);
        painelDir.setVisible(true);
        //*/
        
        // Construcao do painel da esquerda #################################################
        //*
        painelEsq.setLayout(new GridLayout(2, 0));
        painelEsq.add(new ImagemSala());
        painelEsq.add(new ImagemMapa());
        //*/
        
        // Construcao do painel da direita ##################################################
        painelDir.setLayout(new GridLayout(3,0));
        
       
        painelSala = new PainelDaSala(currentRoom);
        painelDir.add(painelSala);
        //*/

        // pVidaInimigos = new PainelDoInimigo();
        // painelDir.add(pVidaInimigos);

        pHeroi = new PainelDoHeroi(heroi);
        painelDir.add(pHeroi);
        
        // Painel com os botoes -------------------------------------------------------------
        //*
        JPanel painelDosBotoes = new JPanel();
		painelDosBotoes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		painelDosBotoes.setLayout(new GridLayout(0, 2));
		// Botoes de comandos ---------
		JPanel comandos = new JPanel();
		comandos.setLayout(new GridLayout(3,0));
		comandos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		bAtacar = new JButton("Atacar");
		bAtacar.addActionListener(this);
		comandos.add(bAtacar);
		bPegar = new JButton("Pegar");
		bPegar.addActionListener(this);
		comandos.add(bPegar);
		bSoltar = new JButton("Soltar");
		bSoltar.addActionListener(this);
		comandos.add(bSoltar);
		painelDosBotoes.add(comandos);
		// Botoes de movimento ---------
		JPanel movimentos = new JPanel();
		movimentos.setLayout(new BorderLayout());
		movimentos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		bNorte = new JButton("Norte");
		bNorte.addActionListener(this);
		movimentos.add(bNorte, BorderLayout.NORTH);
		bSul = new JButton("Sul");
		bSul.addActionListener(this);
		movimentos.add(bSul, BorderLayout.SOUTH);
		painelDosBotoes.add(movimentos);
		bLeste = new JButton("Leste");
		bLeste.addActionListener(this);
		movimentos.add(bLeste, BorderLayout.EAST);
		bOeste = new JButton("Oeste");
		bOeste.addActionListener(this);
		movimentos.add(bOeste, BorderLayout.WEST);
		// Adicao do painel de botes no painel principal da direita
		painelDir.add(painelDosBotoes);
		//*/
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		//*
		if (ae.getSource() == bPegar) {
			console.append("AVISO: A funcao para pegar itens ainda precisa ser implementada!\n");
		} else if (ae.getSource() == bSoltar) {
			console.append("AVISO: A funcao para soltar itens ainda precisa ser implementada!\n");
		} else if (ae.getSource() == bNorte) {
			Command comando = new Command(CommandWord.GO, "north");
			goRoom(comando);
		} else if (ae.getSource() == bSul) {
			Command comando = new Command(CommandWord.GO, "south");
			goRoom(comando);
		} else if (ae.getSource() == bLeste) {
			Command comando = new Command(CommandWord.GO, "east");
			goRoom(comando);
		} else if (ae.getSource() == bOeste) {
			Command comando = new Command(CommandWord.GO, "west");
			goRoom(comando);
		} else if (ae.getSource() == bAtacar) {
			Command comando = new Command(CommandWord.ATTACK, painelSala.pegaInimigoSelecionado());
			attack(comando);
		}
		//*/
	}
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
            	JanelaPrincipal jp = new JanelaPrincipal();
                jp.setVisible(true);
            }
        });
    }

}
