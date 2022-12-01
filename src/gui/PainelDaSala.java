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
import jogo.Sala;

public class PainelDaSala extends JPanel {
	private Sala sala;
	private String inimigoSelecionado;
	private String itemSelecionado;
	
	private JLabel descricao;
	private JList inimigos;
	private JList itens;
	private PainelDoInimigo pInimigo;
	//private PainelDeItensSala pItens; ver depois
	
	public PainelDaSala(Sala sala) 
	{
		this.sala = sala;
		inimigoSelecionado = null;
		
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		descricao = new JLabel(sala.pegarDescricao());
		add(descricao);

		JPanel painelListas = new JPanel();
		painelListas.setLayout(new GridLayout(3, 2));
		painelListas.add(new JLabel("Inimigos"));
		painelListas.add(new JLabel("Itens"));

		
		inimigos = new JList();
		inimigos.setModel(new DefaultListModel());
		inimigos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inimigos.setLayoutOrientation(JList.VERTICAL);
		inimigos.setVisibleRowCount(-1);
		inimigos.addListSelectionListener(new InimigosSelectionHandler());
		JScrollPane pInimigos = new JScrollPane(inimigos);
		painelListas.add(pInimigos);
		
		itens = new JList();
		itens.setModel(new DefaultListModel());
		itens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itens.setLayoutOrientation(JList.VERTICAL);
		itens.setVisibleRowCount(-1);
		inimigos.addListSelectionListener(new ItensSelectionHandler());
		JScrollPane pItens = new JScrollPane(itens);
		painelListas.add(pItens);
		
		add(painelListas);
		
		pInimigo = new PainelDoInimigo();
		add(pInimigo);
	}
	
	public void mudarDeSala(Sala novaSala) 
	{
		sala = novaSala;
		atualizar();
	}
	
	public void atualizar() 
	{
		descricao.setText("Você está " + sala.pegarDescricao());
		
		((DefaultListModel) inimigos.getModel()).removeAllElements();
		for (String nome : sala.pegarPersonagens()) 
		{
			((DefaultListModel) inimigos.getModel()).addElement(nome);
		}
		
		((DefaultListModel) itens.getModel()).removeAllElements();
		for (String nome : sala.pegarItens())
		{
			((DefaultListModel) itens.getModel()).addElement(nome);
		}
	}

	public void atualizarInimigo() 
	{
		if (inimigoSelecionado == null) 
		{
			pInimigo.limpar();
		} 
		else 
		{
			pInimigo.atualizar(sala.pegarPersonagem(inimigoSelecionado));
		}
	}

	public String pegarInimigoSelecionado() 
	{
		return inimigoSelecionado;
	}

	public String pegarItemSelecionado()
	{
		return itemSelecionado;
	}
	
	// Classe que implementa o Listener para tratar das selecoes na lista de inimigos
	class InimigosSelectionHandler implements ListSelectionListener 
	{
		public void valueChanged(ListSelectionEvent e) 
		{ 
			if (inimigos.isSelectionEmpty()) 
			{
				inimigoSelecionado = null;
			}
			else 
			{
				inimigoSelecionado = (String) inimigos.getSelectedValue();
			}
			atualizarInimigo();
		}
	}

	class ItensSelectionHandler implements ListSelectionListener 
	{
		public void valueChanged(ListSelectionEvent e) 
		{ 
			if (inimigos.isSelectionEmpty()) 
			{
				itemSelecionado = null;
			} 
			else 
			{
				itemSelecionado = (String) itens.getSelectedValue();
			}
		}
	}
}