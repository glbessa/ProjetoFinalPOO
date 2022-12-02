package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personagens.Heroi;
import jogo.Sala;

public class PainelDoHeroi extends JPanel
{
	private Heroi heroi;

	private JLabel nome;
	private JLabel vida;
	private JLabel labelArma;
	private JLabel labelDefesa;
	private JLabel labelMoedas;
	private JLabel titulo;

	public PainelDoHeroi(Heroi heroi) 
	{
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
		labelMoedas = new JLabel("Moedas: ");
		add(titulo);
		add(nome);
		add(vida);
		add(labelArma);
		add(labelDefesa);
		add(labelMoedas);
	}
	
	public void atualizar() 
	{
		nome.setText("Nome: " + heroi.pegarNome());
		vida.setText("Vida: " + heroi.pegarVida() + " / " + heroi.pegarVidaMaxima());
		labelArma.setText("Arma equipada: " + heroi.pegarArmaEquipada().pegarNome() + " - Bônus de ataque: " + heroi.pegarArmaEquipada().pegarDano() + " - Durab.: " + heroi.pegarArmaEquipada().pegarDurabilidade() + "/100");
		labelDefesa.setText("Defesa equipada: " + heroi.pegarDefesaEquipada().pegarNome() + " - Bônus de defesa: " + heroi.pegarDefesaEquipada().pegarDefesa() + " - Durab.: " + heroi.pegarDefesaEquipada().pegarDurabilidade() + "/100");
		labelMoedas.setText("Moedas: " + heroi.pegarMochila().pegarMoedas());
	}
}
