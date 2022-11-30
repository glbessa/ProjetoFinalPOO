package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personagens.Heroi;
import personagens.Personagem;
import personagens.Vilao;

public class PainelDoInimigo extends JPanel{
	private JLabel nome;
	private JLabel energia;
	private JLabel titulo;

	public PainelDoInimigo() {
		setBackground(new Color(255,99,71));
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Label com o nome do Heroi
		titulo = new JLabel("============================================Painel do Inimigo============================================");
		nome = new JLabel("Nome: ");
		energia = new JLabel("Energia: ");
		add(titulo);
		add(nome);
		add(energia);
	}
	
	public void atualizar(Personagem vilao) {
		nome.setText("Nome: " + vilao.pegaNome());
		energia.setText("Energia: " + vilao.pegaEnergia() + " / " + vilao.pegaEnergiaMaxima());
	}

	public void limpar() {
		nome.setText("Nome: ");
		energia.setText("Energia: ");
	}
}
