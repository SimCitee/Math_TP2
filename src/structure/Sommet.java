package structure;

public class Sommet {

	private static int compteurSommet = 0;	// permet d'etiquetter chaque sommet
	private int etiquette;		// contient l'identifiant du sommet (une lettre)
	private int degrePositif;	// contient le degre positif du sommet
	private int degreNegatif;	// contient le degre negatif du sommet

	public Sommet() {
		this.etiquette = ++compteurSommet;
		degrePositif = 0;
		degreNegatif = 0;
	}
	
	public int getEtiquette() {
		return etiquette;
	}
	
	public void incrementDegrePositif() {
		degrePositif++;
	}
	
	public void incrementDegreNegatif() {
		degreNegatif++;
	}

	public int getDegrePositif() {
		return degrePositif;
	}

	public int getDegreNegatif() {
		return degreNegatif;
	}

	public static int getCompteurSommet() {
		return compteurSommet;
	}
	
	@Override
	public String toString() {
		return String.valueOf(etiquette);
	}
	
}