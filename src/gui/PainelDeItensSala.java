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

public class PainelDeItensSala extends JPanel 
{
	private Sala sala;
	private JList<String> itens;
	private JLabel titulo;

	public PainelDeItensSala(Sala sala)
	{
		this.sala = sala;

		setBackground(new Color(0, 255, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pListaItens = new JPanel();
		titulo = new JLabel("Itens na sala:");
		add(titulo);
		pListaItens.setLayout(new GridLayout());

		itens = new JList<String>();
		itens.setModel(new DefaultListModel());
		itens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itens.setLayoutOrientation(JList.VERTICAL);
		itens.setVisibleRowCount(-1);
		JScrollPane pSalas = new JScrollPane(itens);
		pListaItens.add(pSalas);
			
		add(pListaItens);
	}

	public void mudarDeSala(Sala novaSala)
	{
		sala = novaSala;
		atualizar();
	}

	public void atualizar() 
	{
			((DefaultListModel) itens.getModel()).removeAllElements();
	}


}
