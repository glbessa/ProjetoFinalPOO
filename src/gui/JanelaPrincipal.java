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

import itens.*;

import personagens.*;

import jogo.*;

public class JanelaPrincipal extends JFrame implements ActionListener{
    private Sala salaAtual;
    private JPanel painelEsq;
    private JPanel painelDir;
    private Heroi heroi;
    
    // Atributos de componetes da GUI
	private PainelDoHeroi pHeroi;
    private PainelDoInimigo pVidaInimigos;
	private PainelDaSala painelSala;
    // private PainelItensSala painelSala;
    private LerImagensSalas imagemSala;
    private PainelDaSala pSala;
	// private JTextArea console;
	private JButton bAtacar; 
	private JButton bPegar;
	private JButton bAbrirInventario;
	private JButton bSoltar; 
	private JButton bNorte; 
	private JButton bSul; 
	private JButton bLeste; 
	private JButton bOeste; 
    
    public JanelaPrincipal() 
    {
        criarSalas();

        heroi = new Heroi("Cristopher", 20, 20, 10, 100, 100);

		heroi.setMochila(new Inventario(10));
        
		Arma arma = new Arma("Espada", "", 2, 10);
		heroi.pegarMochila().inserirItem(arma);
		heroi.equiparArma(arma);

        setTitle("Mansão Demoníaca");
        setSize(1366, 768);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
  
        inicializar();
        
    }

    private void criarSalas()
    {
        Sala outside, mainEntrance, lobby, armorSala, dungeon, library, bossRoom, hallwayToArmorSala, hallwayToBoss, hallwayToLibrary;
     
        outside = new Sala("outside", "na Frente da Mansão");
        mainEntrance = new Sala("mainEntrance", "na Sala de Entrada");
        armorSala = new Sala("armorSala", "na Sala de Armaduras");
        dungeon = new Sala("dungeon", "na Dungeon");
        library = new Sala("library", "na Biblioteca");
        bossRoom = new Sala("bossRoom", "no Salão Final");
        lobby = new Sala("lobby", "no Lobby");
        hallwayToArmorSala = new Sala("hallwayToArmorSala", "no corredor para Sala de Armaduras");
        hallwayToLibrary = new Sala("hallwayToLibrary", "no corredor para Biblioteca");
        hallwayToBoss = new Sala("hallwayToBoss", "no corredor para o Salão Final ");

        outside.setSaida("west", mainEntrance);

        mainEntrance.setSaida("east", outside);
        mainEntrance.setSaida("south", lobby);

        lobby.setSaida("east", hallwayToLibrary);
        lobby.setSaida("west", hallwayToArmorSala);
        lobby.setSaida("south", hallwayToBoss);
        lobby.setSaida("north", mainEntrance);

        hallwayToArmorSala.setSaida("east", lobby);
        hallwayToArmorSala.setSaida("west", armorSala);
        hallwayToLibrary.setSaida("west", lobby);
        hallwayToLibrary.setSaida("east", library);
        hallwayToBoss.setSaida("east", bossRoom);
        hallwayToBoss.setSaida("north", lobby);

        library.setSaida("west", hallwayToLibrary);
        armorSala.setSaida("east", hallwayToArmorSala);
        armorSala.setSaida("south", dungeon);
        dungeon.setSaida("north", armorSala);

		// criando inimigos
        Personagem diabinho = new Vilao("Diabinho", 2, 2, 4, 3);
        Personagem olhoDoMal = new Vilao("Olhão do Mal", 2, 2, 4, 3);
        Personagem assassino = new Vilao("Assasino", 2, 2, 4, 3);
        Personagem fantasmaCaolho = new Vilao("Fantasma Caolho", 2, 2, 4, 3);
        Personagem cavaleiro = new Vilao("Cavaleiro Demoníaco", 2, 2, 4, 3);
        Personagem michaelMyers = new Vilao("Michael Myers", 2, 2, 4, 3);
        Personagem capanga1 = new Vilao("Diabinho Capanga 1", 2, 2, 4, 3);
        Personagem capanga2 = new Vilao("Diabinho Capanga 2", 2, 2, 4, 3);
        Personagem capanga3 = new Vilao("Diabinho Capanga 3", 2, 2, 4, 3);
        Personagem diabao = new Vilao("Diabão", 2, 2, 4, 3);

        mainEntrance.inserirPersonagem(diabinho);

        lobby.inserirPersonagem(assassino);
        lobby.inserirPersonagem(olhoDoMal);

        library.inserirPersonagem(fantasmaCaolho);

        armorSala.inserirPersonagem(cavaleiro);

        dungeon.inserirPersonagem(michaelMyers);

        bossRoom.inserirPersonagem(diabao);
        bossRoom.inserirPersonagem(capanga1);
        bossRoom.inserirPersonagem(capanga2);
        bossRoom.inserirPersonagem(capanga3);

				Arma espadaEntranceRoom = new Arma("Espada de Aço", "Uma espada leve porém poderosa", 3, 4);
				Defesa escudoEntranceRoom = new Defesa("Escudo de Madeira", "Escudo leve", 4, 3);
				Arma machadoArmorRoom = new Arma("Machado Medieval", "Machado usado por cavaleiros medievais", 5, 7);
				Defesa escudoArmorRoom = new Defesa("Escudo Dourado", "Escudo muito resistente", 5, 3);
				Pocao pocaoLobby = new Pocao("Poção de vida", "Tome para recuperar sua vida", 1, 8);
				Comida pizzaLibrary = new Comida("Pizza", "Uma pizza para recuperar sua vida", 1, 4);
				Comida uvaHallway = new Comida("Uvas", "Um cacho de uvas para recuperar sua vida", 1, 4);
				Arma espadaHallway = new Arma("Espada Lendária", "A espada mais forte de todas", 3, 9);

				mainEntrance.inserirItem(espadaEntranceRoom);
				mainEntrance.inserirItem(escudoEntranceRoom);
				lobby.inserirItem(pocaoLobby);
				library.inserirItem(pizzaLibrary);
				armorSala.inserirItem(machadoArmorRoom);
				armorSala.inserirItem(escudoArmorRoom);
				hallwayToBoss.inserirItem(uvaHallway);
				hallwayToBoss.inserirItem(espadaHallway);
			

		// criando itens
		//...

		// adicionando os itens as salas
		//...

        salaAtual = outside;  // Começa o jogo fora da mansão
    }

	private void irPara(String direcao)
	{
		Sala proximaSala = salaAtual.pegarSaida(direcao);
		if (proximaSala != null)
		{
			salaAtual = proximaSala;
			atualizar(salaAtual);
			painelSala.mudarDeSala(salaAtual);
		}
	}

	private void atacarPersonagem(String nome)
	{
		Personagem oponente = salaAtual.pegarPersonagem(nome);
		if (oponente != null)
		{
			heroi.atacar(oponente);
			pHeroi.atualizar();
			painelSala.atualizarInimigo();
		}
	}
    
	private void pegarItem(String nome)
	{
		Item item = salaAtual.pegarItem(nome);
		if (item != null)
		{
			heroi.pegarMochila().inserirItem(item);
		}
	}

    // ******************************************************
    // Metodos relacionadas a GUI
    // ******************************************************

	public void initPainelEsq()
	{
        painelEsq = new JPanel();
        painelEsq.setLayout(new GridLayout(2, 0));
        painelEsq.add(salaAtual.lerImagemSala(salaAtual));
        painelEsq.add(new ImagemMapa());
        painelEsq.setVisible(true);
        add(painelEsq);
    }

    public void initPainelDir()
	{
        painelDir = new JPanel();
        painelDir.setVisible(true);
        painelDir.setLayout(new GridLayout(3,0));

        painelSala = new PainelDaSala(salaAtual);
        painelDir.add(painelSala);

        pHeroi = new PainelDoHeroi(heroi);
        painelDir.add(pHeroi);
        

        JPanel painelDosBotoes = new JPanel();
		painelDosBotoes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		painelDosBotoes.setLayout(new GridLayout(0, 2));
		
		

		JPanel comandos = new JPanel();
		comandos.setLayout(new GridLayout(3,0));
		comandos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		bAtacar = new JButton("Atacar");
		bAtacar.addActionListener(this);
		comandos.add(bAtacar);
		bPegar = new JButton("Pegar");
		bPegar.addActionListener(this);
		comandos.add(bPegar);
		bAbrirInventario = new JButton("Abrir inventário");
		bAbrirInventario.addActionListener(this);
		comandos.add(bAbrirInventario);
		bSoltar = new JButton("Soltar");
		bSoltar.addActionListener(this);
		comandos.add(bSoltar);
		painelDosBotoes.add(comandos);
		

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

		
		painelDir.add(painelDosBotoes);

        add(painelDir);
    }

    public void inicializar() 
	{
        setLayout(new GridLayout());
        initPainelEsq();
        initPainelDir();

		// atualiza o painel do heroi
		pHeroi.atualizar();
    }

    public void atualizar(Sala currentSala)
	{
        remove(painelEsq);
        remove(painelDir);
        initPainelEsq();
        initPainelDir();

		// atualiza o painel do heroi
		pHeroi.atualizar();
    }

	@Override
	public void actionPerformed(ActionEvent ae) 
	{	
		if (ae.getSource() == bPegar) 
		{
			
		}
		else if (ae.getSource() == bAbrirInventario)
		{
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					JanelaDeInventario ji = new JanelaDeInventario();
					ji.setVisible(true);
					ji.inicializar(heroi.pegarMochila());
				}
			});
		}
		else if (ae.getSource() == bSoltar) 
		{
			
		} 
		else if (ae.getSource() == bNorte) 
		{
			irPara("north");
		} 
		else if (ae.getSource() == bSul) 
		{
			irPara("south");
		} 
		else if (ae.getSource() == bLeste) 
		{
			irPara("este");
		} 
		else if (ae.getSource() == bOeste) 
		{
			irPara("west");
		} 
		else if (ae.getSource() == bAtacar) 
		{
			atacarPersonagem(painelSala.pegarInimigoSelecionado());
		}
		
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