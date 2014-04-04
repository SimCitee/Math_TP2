package structure;

public class Graphe {

	private Sommet listeSommets[];	// liste contenant les sommets

	private int matriceAdjacence[][]; 
	private int nombreSommets;
	
	public Graphe(int nombreSommets) {
		matriceAdjacence = new int[nombreSommets][nombreSommets];
		listeSommets = new Sommet[nombreSommets];
		this.nombreSommets = nombreSommets;
		
		// initialiser la matrice a 0
		for(int i=0; i<nombreSommets; i++)
			for(int j=0; j<nombreSommets; j++)
				matriceAdjacence[i][j] = 0;
		
		// creer les sommets
		for(int i=0; i<nombreSommets; i++) {
			listeSommets[i] = new Sommet();
		}
	}
	
	public void ajouterArc(int debut, int fin) {
		boolean sommetSeul = true;
		
		for(int i=0; i<nombreSommets; i++) {
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					sommetSeul = false;
				}
			}
		}
				
		// Verifie si le sommet de depart est le meme que le sommet d'arrive
		if(debut == fin) {
			System.out.println("Un sommet ne peut pas avoir un arc qui pointe vers le meme sommet, impossible d'ajouter cet arc.");
		}
		else if(matriceAdjacence[fin - 1][debut - 1] == 1) {
			System.out.println("Un arc pointe deja dans le sens contraire, impossible d'ajouter cet arc.");
		}
		else if(sommetSeul){
			System.out.println("Un sommet n'est pas lie avec un arc, impossible d'ajouter cet arc.");
		}
		else {
			matriceAdjacence[debut - 1][fin - 1] = 1;
			System.out.println("Arc ajoute");
		}
	}
	
	public void afficherMatriceAdjacence() {
		for(int i=0; i<nombreSommets; i++) {
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					System.out.print("1 ");
				}
				else {
					System.out.print("0 ");
				}
			}
			System.out.println("");
		}
	}
	
	public Sommet[] getListeSommets() {
		return listeSommets;
	}
	
	public int getNombreSommets() {
		return nombreSommets;
	}
	
	public int[] calculerDegreePositifNegatif() {
		int compteurDegreeNeg = 0;
		int compteurDegreePos = 0;
		int[] degreePosNeg = new int[2];
		
		// Calculer degree negatif
		for(int i=0; i<nombreSommets; i++) {
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					listeSommets[i].incrementDegreePositif();
					listeSommets[j].incrementDegreeNegatif();
				}
			}
		}
		
		return degreePosNeg;
	}

	public boolean cycleEulerien() {
		boolean cycleEulerien = true;
		for(Sommet s : listeSommets) {
			if(s.getDegreeNegatif() + s.getDegreePositif() % 2 != 0) {
				cycleEulerien = false;
			}
		}
		return cycleEulerien;
	}
}