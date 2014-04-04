package structure;

public class Sommet {

	private static int compteurSommet = 0;
	private int etiquette;	// contient l'identifiant du sommet (une lettre)
	private int degreePositif;
	private int degreeNegatif;

	public Sommet() {
		this.etiquette = ++compteurSommet;
		degreePositif = 0;
		degreeNegatif = 0;
	}
	
	public int getEtiquette() {
		return etiquette;
	}
	
	public void incrementDegreePositif() {
		degreePositif++;
	}
	
	public void incrementDegreeNegatif() {
		degreeNegatif++;
	}

	public int getDegreePositif() {
		return degreePositif;
	}

	public int getDegreeNegatif() {
		return degreeNegatif;
	}

	public static int getCompteurSommet() {
		return compteurSommet;
	}
	
	@Override
	public String toString() {
		return String.valueOf(etiquette);
	}
	
}