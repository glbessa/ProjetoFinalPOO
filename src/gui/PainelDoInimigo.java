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
	private JLabel vida;
	private JLabel titulo;

	public PainelDoInimigo() {
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Label com o nome do Heroi
		titulo = new JLabel("==Inimigo==");
		nome = new JLabel("Nome: ");
		vida = new JLabel("Vida: ");
		add(titulo);
		add(nome);
		add(vida);
	}
	
	public void atualizar(Personagem vilao) {
		nome.setText("Nome: " + vilao.pegarNome());
		vida.setText("Vida: " + vilao.pegarVida() + " / " + vilao.pegarVidaMaxima());
	}

	public void limpar() {
		nome.setText("Nome: ");
		vida.setText("Vida: ");
	}
}
