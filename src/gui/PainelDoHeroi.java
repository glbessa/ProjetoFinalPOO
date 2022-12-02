package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personagens.Heroi;
import jogo.Sala;

public class PainelDoHeroi extends JPanel{
	// Objetos do Jogos
	private Heroi heroi;
	// Componentes da GUI
	private JLabel nome;
	private JLabel vida;
	private JLabel labelArma;
	private JLabel labelDefesa;
	private JLabel titulo;

	public PainelDoHeroi(Heroi heroi) {
		this.heroi = heroi;
		
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Label com o nome do Heroi
		titulo = new JLabel("============================================Painel do Herói============================================");
		nome = new JLabel("Nome: ");
		vida = new JLabel("Vida: ");
		labelArma = new JLabel("Arma equipada: ");
		labelDefesa = new JLabel("Defesa equipada: ");
		add(titulo);
		add(nome);
		add(vida);
		add(labelArma);
		add(labelDefesa);
	}
	
	public void atualizar() {
		nome.setText("Nome: " + heroi.pegarNome());
		vida.setText("Vida: " + heroi.pegarVida() + " / " + heroi.pegarVidaMaxima());
		labelArma.setText("Arma equipada: " + heroi.pegarArmaEquipada().pegarNome() + " - Bônus de ataque: " + heroi.pegarArmaEquipada().pegarDano());
		labelDefesa.setText("Defesa equipada: " + heroi.pegarDefesaEquipada().pegarNome() + " - Bônus de defesa: " + heroi.pegarDefesaEquipada().pegarDefesa());
	}
}
