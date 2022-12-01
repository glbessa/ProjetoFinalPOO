package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JanelaDeInventario extends JFrame implements ActionListener
{
	private JPanel painelPrincipal;
	private JPanel botoes;
	private JButton bUsar;
	private JButton bTirar;
	private JButton bSair;

	private JList listaDeInventario;
	private String itemSelecionado;
	private Inventario inventario;

	private JScrollPane pItems;

	public void initGUI() 
	{
        setTitle("Invertário");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());
        

        painelPrincipal = new JPanel();
        add(painelPrincipal);
        painelPrincipal.setBackground(new Color(0, 0, 255));
        painelPrincipal.setVisible(true);

		listaDeInventario = new JList();
		listaDeInventario.setModel(new DefaultListModel());
		listaDeInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeInventario.setLayoutOrientation(JList.VERTICAL);
		listaDeInventario.setVisibleRowCount(-1);
		listaDeInventario.addListSelectionListener(new ListaDeInventarioSelectionHandler());

		pItems = new JScrollPane(listaDeInventario);
		painelPrincipal.add(pItems);


		botoes = new JPanel();
		botoes.setLayout(new GridLayout(3,0));
		botoes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		
		bUsar = new JButton("Usar");
		bUsar.addActionListener(this);
		botoes.add(bUsar);

		bTirar = new JButton("Tirar");
		bTirar.addActionListener(this);
		botoes.add(bTirar);

		bSair = new JButton("Sair");
		bSair.addActionListener(this);
		botoes.add(bSair);
		
		painelPrincipal.add(botoes);
    }

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		switch(ae.getSource())
		{
			case bUsar:
				Item item = inverntario.pegarItem();
				if (item instanceof Equipamento)
					if 
				else

				
				break;
			case bTirar:
				inventario.removerItem(itemSelecionado);
				break;
			case bSair:
				
				break;
			default:
				break;
		}
	}

	public void atualizar() 
	{
		((DefaultListModel) listaDeInventario.getModel()).removeAllElements();

		for (String nome : inventario.pegarNomesDosItens()) 
			((DefaultListModel) listaDeInventario.getModel()).addElement(nome);
	}

	class ListaDeInventarioSelectionHandler implements ListSelectionListener 
	{
		public void valueChanged(ListSelectionEvent e) 
		{ 
			if (itens.isSelectionEmpty()) 
				itemSelecionado = null;
			else
				itemSelecionado = (String) itens.getSelectedValue();
		}
	}
}
