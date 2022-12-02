package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

import javax.imageio.ImageIO;
import javax.swing.*;

import itens.*;
import jogo.*;
import personagens.*;

public class JanelaDeInventario extends JFrame implements ActionListener
{
	private JPanel painelPrincipal;
	private JPanel painelBotoes;
	private JButton bUsar;
	private JButton bTirar;
	private JButton bSair;

	private JList listaDeInventario;
	private String itemSelecionado;
	private Inventario inventario;

	public void inicializar(Inventario inventario) 
	{
        setTitle("Invertário");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));
        
		this.inventario = inventario;

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(255, 255, 255));
        painelPrincipal.setVisible(true);
		add(painelPrincipal);

		listaDeInventario = new JList();
		listaDeInventario.setModel(new DefaultListModel());
		listaDeInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeInventario.setLayoutOrientation(JList.VERTICAL);
		listaDeInventario.setVisibleRowCount(-1);
		listaDeInventario.addListSelectionListener(new ListaDeInventarioSelectionHandler());
		JScrollPane pItens = new JScrollPane(listaDeInventario);
		painelPrincipal.add(pItens);


		painelBotoes = new JPanel();
		painelBotoes.setLayout(new GridLayout(3,0));
		
		bUsar = new JButton("Usar/Equipar");
		bUsar.addActionListener(this);
		painelBotoes.add(bUsar);

		bTirar = new JButton("Tirar");
		bTirar.addActionListener(this);
		painelBotoes.add(bTirar);

		bSair = new JButton("Sair");
		bSair.addActionListener(this);
		painelBotoes.add(bSair);
		
		painelPrincipal.add(painelBotoes);

		atualizar();
    }

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if (ae.getSource() == bUsar)
		{
			if (itemSelecionado != null)
			{
				
			}
			atualizar();
		}
		else if (ae.getSource() == bTirar)
		{
			if (itemSelecionado != null)
			{
				inventario.removerItem(itemSelecionado);
			}
			atualizar();
		}
		else if (ae.getSource() == bSair)
		{
			dispose();
		}
	}

	public void atualizar() 
	{
		((DefaultListModel) listaDeInventario.getModel()).removeAllElements();

		for (String nome : inventario.pegarNomes())
		{
			((DefaultListModel) listaDeInventario.getModel()).addElement(nome);
		}
	}

	class ListaDeInventarioSelectionHandler implements ListSelectionListener 
	{
		public void valueChanged(ListSelectionEvent e) 
		{ 
			if (listaDeInventario.isSelectionEmpty()) 
				itemSelecionado = null;
			else
				itemSelecionado = (String) listaDeInventario.getSelectedValue();
		}
	}
}
