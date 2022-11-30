package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personagens.Heroi;
import jogo.Room;

public class PainelDoHeroi extends JPanel{
	// Objetos do Jogos
	private Heroi heroi;
	// Componentes da GUI
	private JLabel nome;
	private JLabel energia;
	private JLabel titulo;

	public PainelDoHeroi(Heroi heroi) {
		this.heroi = heroi;
		
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Label com o nome do Heroi
		titulo = new JLabel("============================================Painel do Herói============================================");
		nome = new JLabel("Nome: ");
		energia = new JLabel("Energia: ");
		add(titulo);
		add(nome);
		add(energia);
	}
	
	public void atualizar() {
		nome.setText("Nome: " + heroi.pegaNome());
		energia.setText("Energia: " + heroi.pegaEnergia() + " / " + heroi.pegaEnergiaMaxima());
	}
}
