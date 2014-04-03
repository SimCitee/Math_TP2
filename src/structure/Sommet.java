package structure;

public class Sommet {

	private char etiquette;	// contient l'identifiant du sommet (une lettre)

	public Sommet(char etiquette) {
		this.etiquette = etiquette;
	}
	
	public char getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(char etiquette) {
		this.etiquette = etiquette;
	}
}
