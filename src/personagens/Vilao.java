package personagens;

public class Vilao extends Personagem {
	private static final int energiaMaxima = 7;

	public Vilao(String nome, int energia) {
		super(nome, energia);
	}
	
	public int pegaEnergiaMaxima() {
		return energiaMaxima;
	}
	
	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Dados do Vilao");
		super.imprimir();
	}
}
