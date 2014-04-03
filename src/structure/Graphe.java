package structure;

public class Graphe {

	private Sommet listeSommets[];	// liste contenant les sommets
	private int matriceAdjacence[][];
	private int compteurSommet;
	
	public Graphe(int nombreSommets) {
		listeSommets = new Sommet[nombreSommets];
		compteurSommet = 0;
		
		// initialiser la matrice a 0
		for(int i=0; i<nombreSommets; i++)
			for(int j=0; j<nombreSommets; i++)
				matriceAdjacence[i][j] = 0;
	}
	
	public void ajouterSommet(char etiquette) {
		listeSommets[compteurSommet] = new Sommet(etiquette);
	}
	
	public void ajouterArc(int debut, int fin) {
		matriceAdjacence[debut][fin] = 1;
	}
	
}
