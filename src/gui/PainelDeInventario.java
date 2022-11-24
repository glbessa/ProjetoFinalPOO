package gui;

public class PainelDeInventario extends JPanel
{
	Inventario inventario;
	JList lista;
	String itemSelecionado;

	public PainelDeInventario(Inventario inventario) 
	{			
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		lista = new JList();
		lista.setModel(new DefaultListModel());
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setLayoutOrientation(JList.VERTICAL);
		lista.setVisibleRowCount(-1);
		JScrollPane pItens = new JScrollPane(lista);
		this.add(pItens);

		JPanel painelDeBotoes = new JPanel();
	}

	public void atualizar() 
	{
		((DefaultListModel) lista.getModel()).removeAllElements();

		for (String nome : inventario.pegarNomes()) 
			((DefaultListModel) lista.getModel()).addElement(nome);
	}

	class ItemSelectionHandler implements ListSelectionListener 
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
