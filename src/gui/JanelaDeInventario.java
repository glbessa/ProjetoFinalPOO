package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JanelaDeInventario 
{
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
}
