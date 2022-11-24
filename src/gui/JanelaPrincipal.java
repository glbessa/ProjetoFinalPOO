package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import personagens.Heroi;
import personagens.Personagem;
import personagens.Vilao;

import jogo.Game;
import jogo.Room;

public class JanelaPrincipal extends JFrame implements ActionListener{
    private Room currentRoom;
    private Heroi heroi;
    
    // Atributos de componetes da GUI
	private PainelDoHeroi painelHeroi;
	private PainelDaSala painelSala;
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
        createRooms();
        heroi = new Heroi("Batman", 8, 10, 100);
        initGUI();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        Personagem capanga1 = new Vilao("Capanga1", 2);
        Personagem capanga2 = new Vilao("Capanga2", 2);
        lab.inserirPersonagem(capanga1);
        lab.inserirPersonagem(capanga2);
        
        office.setExit("west", lab);
        Personagem coringa = new Vilao("Coringa", 5);
        office.inserirPersonagem(coringa);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    // Remover!
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
    	//*
    	console.append("\n");
    	console.append("Welcome to the World of Zuul!\n");
    	console.append("World of Zuul is a new, incredibly boring adventure game.\n");
    	console.append("\n");
    	console.append(currentRoom.getLongDescription());
    	console.append("\n\n");
    	painelSala.atualizar();
    	painelHeroi.atualizar();
    	//*/
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    // Remover!
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
            //System.out.println(currentRoom.getLongDescription());
            console.append("\n" + currentRoom.getLongDescription() + "\n");
            painelSala.mudarDeSala(currentRoom);
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
        System.out.println(currentRoom.getLongDescription());
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
    		painelHeroi.atualizar();
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
    	// Caracteristicas da Janela Principal ############################################
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
        painelDir.setBackground(new Color(0, 0, 255));
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
        painelDir.setLayout(new GridLayout(4,0));
        
        // Painel do Heroi ------------------------------------------------------------------
        //*
        painelHeroi = new PainelDoHeroi(heroi);
        painelDir.add(painelHeroi);
        //*/
        
		// Painel da Sala -------------------------------------------------------------------
		//*
        painelSala = new PainelDaSala(currentRoom);
		painelDir.add(painelSala);
		//*/
        
        // Painel com a caixa de texto ------------------------------------------------------
        //*
        console = new JTextArea();
        console.setEditable(false);
        painelDir.add(new JScrollPane(console));
        //*/
        
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
        
		printWelcome();
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
