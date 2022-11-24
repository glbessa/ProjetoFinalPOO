package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personagens.Heroi;
import jogo.Room;

public class PainelDaSala extends JPanel {
	// Objetos do Jogos
	private Room sala;
	private String inimigoSelecionado;
	// Componentes da GUI
	private JLabel descricao;
	private JList inimigos;
	private JList itens;
	private PainelDoInimigo pInimigo;
	
	public PainelDaSala(Room sala) 
	{
		this.sala = sala;
		inimigoSelecionado = null;
		
		setBackground(new Color(0, 255, 255));
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLayout(new GridLayout(3, 0));
		
		// Label com a descricao da sala
		descricao = new JLabel("You are ...");
		add(descricao);
		
		// Painel com listas de Heroi e Itens
		JPanel painelListas = new JPanel();
		painelListas.setLayout(new GridLayout(2, 2));
		
		painelListas.add(new JLabel("Inimigos"));
		painelListas.add(new JLabel("Itens"));

		//String[] list = {"teste1", "teste2", "teste3", "teste4", "teste5"};
		inimigos = new JList();
		inimigos.setModel(new DefaultListModel());
		inimigos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inimigos.setLayoutOrientation(JList.VERTICAL);
		inimigos.setVisibleRowCount(-1);
		inimigos.addListSelectionListener(new InimigosSelectionHandler());
		JScrollPane pHerois = new JScrollPane(inimigos);
		painelListas.add(pHerois);
		
		itens = new JList();
		itens.setModel(new DefaultListModel());
		itens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itens.setLayoutOrientation(JList.VERTICAL);
		itens.setVisibleRowCount(-1);
		JScrollPane pSalas = new JScrollPane(itens);
		painelListas.add(pSalas);
		
		add(painelListas);
		
		pInimigo = new PainelDoInimigo();
		add(pInimigo);
	}
	
	public void mudarDeSala(Room novaSala) {
		sala = novaSala;
		atualizar();
	}
	
	public void atualizar() {
		descricao.setText("You are " + sala.getShortDescription());
		
		((DefaultListModel) inimigos.getModel()).removeAllElements();
		for (String nome : sala.pegaConjuntoDePersonagens()) {
			((DefaultListModel) inimigos.getModel()).addElement(nome);
		}
		
		((DefaultListModel) itens.getModel()).removeAllElements();
	}

	public void atualizarInimigo() {
		if (inimigoSelecionado == null) {
			pInimigo.limpar();
		} else {
			pInimigo.atualizar(sala.pegaPersonagem(inimigoSelecionado));
		}
	}
	
	public String pegaInimigoSelecionado() {
		return inimigoSelecionado;
	}
	
	// Classe que implementa o Listener para tratar das selecoes na lista de inimigos
	class InimigosSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) { 
			if (inimigos.isSelectionEmpty()) {
				inimigoSelecionado = null;
			} else {
				//System.out.println((String) inimigos.getSelectedValue());
				inimigoSelecionado = (String) inimigos.getSelectedValue();
			}
			atualizarInimigo();
		}
	}
}