package structure;

import java.util.ArrayList;
import java.util.Arrays;

public class Graphe {

	private Sommet listeSommets[];		// liste contenant les sommets
	private int nombreCycle;			// nombre d cycles
	private int matriceAdjacence[][];	// matrice d'adjacence 
	private int nombreSommets;			// nombre total de sommets
	
	public Graphe(int nombreSommets) {
		matriceAdjacence = new int[nombreSommets][nombreSommets];
		listeSommets = new Sommet[nombreSommets];
		this.nombreSommets = nombreSommets;
		nombreCycle = 0;
		
		// initialiser la matrice a 0
		for(int i=0; i<nombreSommets; i++)
			for(int j=0; j<nombreSommets; j++)
				matriceAdjacence[i][j] = 0;
		
		// creer les sommets
		for(int i=0; i<nombreSommets; i++) 
			listeSommets[i] = new Sommet();
	}
	
	public Sommet[] getListeSommets() {
		return listeSommets;
	}
	
	public Sommet getSommetParEtiquette(int etiquette) {
		return listeSommets[etiquette-1];
	}
	
	public int getNombreSommets() {
		return nombreSommets;
	}
	
	public int getNombreCycle() {
		return nombreCycle;
	}
	
	/*
	 * Methode qui valide un arc (donc un seul arc entre chaque sommet et aucun arc qui debute et termine sur un meme sommet)
	 * Parametre: sommet de depart, sommet d'arrivee
	 * Valeur de retour: booleen
	 */
	public boolean validerArc(int debut, int fin) {
			
		// Verifie si le sommet de depart est le meme que le sommet d'arrive
		if(debut == fin) 
			System.out.println("Un sommet ne peut pas avoir un arc qui pointe vers le meme sommet, impossible d'ajouter cet arc.");
		else if(matriceAdjacence[fin - 1][debut - 1] == 1) 
			System.out.println("Un arc pointe deja dans le sens contraire, impossible d'ajouter cet arc.");
		else if(matriceAdjacence[debut - 1][fin - 1] == 1) {
			System.out.println("Cet arc existe deja, impossible d'ajouter cet arc.");
		}
		else {
			return true;
		}
		
		return false;
	}
	
	/*
	 * Methode qui ajoute un arc a la matrice d'adjacence
	 * Parametre : sommet de depart, sommet d'arrive
	 * Valeur de retour : aucune
	 */
	public void ajouterArc(int debut, int fin) {
		matriceAdjacence[debut - 1][fin - 1] = 1;
	}
	
	/*
	 * Methode qui affiche les valeurs de la matrice d'adjacence
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
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
	
	/*
	 * Methode qui calcul les degres de chaques sommets
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
	public void calculerDegrePositifNegatif() {

		// Calculer degree negatif
		for(int i=0; i<nombreSommets; i++) {
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					listeSommets[i].incrementDegrePositif();
					listeSommets[j].incrementDegreNegatif();
				}
			}
		}
		
	}
	
	/*
	 * Methode qui calcul le nombres d'arc que possede le graphe
	 * Parametre : aucun
	 * Valeur de retour : entier
	 */
	public int getNombreArcs() {
		int resultat = 0;
		
		// Calculer degree negatif
		for(int i=0; i<nombreSommets; i++) {
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					resultat++;
				}
			}
		}
		
		return resultat;
	}

	/*	Methode qui determine si le graphe contient un cycle eulerien
	 * 	Parametre : aucun
	 * 	Valeur de retour : booleen
	 */ 
	public boolean contientCycleEulerien() {
		boolean contientCycleEulerien = true;
		
		// pour chaque sommet, verifier le degre est pair
		// si impair != cycle euclerien
		for(Sommet s : listeSommets) {
			if((s.getDegreNegatif() + s.getDegrePositif()) % 2 != 0) {
				contientCycleEulerien = false;
			}
		}
		return contientCycleEulerien;
	}
	
	/*
	 * Methode qui evalue si le graphe contient une chaine eulerienne ou pas
	 * Parametre: aucun
	 * Valeur de retour: booleen
	 */
	public boolean contientChaineEulerienne() {
		int compteurDegreeImpair = 0;
		
		// verifier le nombres de degres impairs parmi les sommets
		for(Sommet s : listeSommets) 
			if((s.getDegreNegatif() + s.getDegrePositif()) % 2 != 0) 
				compteurDegreeImpair++;
		
		//si le graphe contient plus de deux sommet de degres impair, il ne peut y avoir de chaine eulerienne
		if(compteurDegreeImpair > 2) 
			return false;
		else 
			return true;
	}
	
	/*
	 * Methode qui, pour chaque sommet, appel une methode recursive qui parcours les arcs incidents
	 * Parametre : aucun
	 * Valeur de retour: aucune
	 */
	public void chercherCycle() {
		for(int i=0; i<nombreSommets; i++) {
			parcourirArcIncident(listeSommets[i], new ArrayList<Integer>());
		}
	}
	
	/*
	 * Methode recursive qui parcours tout les arcs incidents pour un sommet donnees
	 * Parametre : sommet actuel, liste des sommets precedemment visites
	 * Valeur de retour: aucune
	 */
	public void parcourirArcIncident(Sommet sommetActuel, ArrayList<Integer> sommetVisites) {
		
		//si le sommet actuel est deja present dans la liste des sommets visites
		//on trouve un cycle
		if(sommetVisites.contains((sommetActuel.getEtiquette()))) {
			String stringCycle = "";
			sommetVisites.add(sommetActuel.getEtiquette());
			
			for(int som : sommetVisites) {
				stringCycle = stringCycle.concat(som + " -> ");
			}
			
			stringCycle = stringCycle.substring(0, stringCycle.length() - 4);
			System.out.println(stringCycle);
			nombreCycle++;
		//sinon, trouver tous les arcs incidents sortant et se diriger vers les prochains sommets
		} else {
			sommetVisites.add(sommetActuel.getEtiquette());
			int rangee = sommetActuel.getEtiquette() - 1;
			
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[rangee][j] == 1) {
					Sommet prochainSommet = getSommetParEtiquette(j+1);
					parcourirArcIncident(prochainSommet, new ArrayList<Integer>(sommetVisites));
				}
			} // fin for
		}
		
	}

	/*
	 * Methode qui valide s'il reste un sommet seul, c-a-d qui n'a aucun arc incident
	 * Parametre: aucun
	 * Valeur de retour: booleen
	 */
	public boolean validerSommetSeul() {
		boolean sommetColonneSeul;
		boolean sommetRangeeSeul;
		
		
		//boucle a travers toutes les colonnes de la matrice d'adjacence
		//pour verifier si le sommet en question est lie avec un arc
		for(int i=0; i<nombreSommets; i++) {
			sommetColonneSeul = true;
			sommetRangeeSeul = true;
			for(int j=0; j<nombreSommets; j++) {
				if(matriceAdjacence[i][j] == 1) {
					sommetColonneSeul = false;
					break;
				}
			}
			//si le sommet n'a pas d'arc d'associe dans la colonne de
			//matrice d'adjacence, verifier la rangee associe a ce sommet
			if(sommetColonneSeul == true) {
				for(int k=0; k<nombreSommets; k++) {
					if(matriceAdjacence[k][i] == 1) {
						sommetRangeeSeul = false;
						break;
					}
				}
			}
			if(sommetColonneSeul && sommetRangeeSeul) {
				return true;
			}
		}
		return false;
	}
}