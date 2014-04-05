import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import structure.Graphe;
import structure.Sommet;


public class Program {	
	
	public static void main(String[] args) {
		int choixNbSommet = 0;
		int choixSommetDepart = 0;
		int choixSommetArrive = 0;
		int choixAjouterArc = 0;

		do {
			System.out.print("Entrez le nombre de sommets (2 a 100) : ");
			choixNbSommet = lireChoix(1, 100);
		} while(choixNbSommet <= 1);
		Graphe graphe = new Graphe(choixNbSommet);

		do {
			System.out.print("\nVoici les sommets : ");
			for(Sommet s : graphe.getListeSommets())
				System.out.print(s.getEtiquette() + ", ");
			
			System.out.println("\n\nVeuillez entrer les arcs du graphe");
			System.out.print("Sommet de depart : ");
			choixSommetDepart = lireChoix(1, graphe.getNombreSommets());

			System.out.print("Sommet d'arrive : ");
			choixSommetArrive = lireChoix(1, graphe.getNombreSommets());
			
			if(graphe.validerArc(choixSommetDepart, choixSommetArrive)) {
				graphe.ajouterArc(choixSommetDepart, choixSommetArrive);
				System.out.println("Arc ajoute");
			}
			
			if(graphe.validerSommetSeul()) {
				System.out.print("\nVotre graphe n'est pas un graphe simple, ajoutez un arc.");
				choixAjouterArc = 1;
			}
			else {
				System.out.print("\nVous avez un graphe simple, voulez-vous ajouter un arc? (1 pour oui / 0 pour non) : ");
				choixAjouterArc = lireChoix(0, 1);
			}
		} while(choixAjouterArc == 1);
		
		graphe.calculerDegrePositifNegatif();
		
		System.out.println("\nLe degree de chaque sommet (sommet : degree positif / degree negatif) : ");
		for(Sommet s : graphe.getListeSommets()) {
			System.out.println(s.getEtiquette() + " : " + s.getDegrePositif() + " / " + s.getDegreNegatif());
		}
		
		System.out.println("\nVoici les cycles : ");
		graphe.chercherCycle();
		System.out.println("Le graphe contient " + graphe.getNombreCycle() + " cycle.");
		
		if(graphe.contientCycleEulerien()) {
			System.out.println("Le graphe contient un cycle eulerien.");
		}
		else {
			System.out.println("Le graphe ne contient pas de cycle eulerien.");
		}
		
		if(graphe.contientChaineEulerienne()) {
			System.out.println("Le graphe contient une chaine eulerienne.");
		}
		else {
			System.out.println("Le graphe ne contient pas de chaine eulerienne.");
		}
		
		System.out.println("Le graphe possede " + graphe.getNombreSommets() + " sommets.");
		System.out.println("Le graphe possede " + graphe.getNombreArcs() + " arcs.");

	}
	
	public static int lireChoix(int min, int max) {
		int choice = 0;
		String input = "0";
		
		do {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!isNumeric(input)) {
				input = "";
			}

		} while(input.isEmpty());

		choice = Integer.parseInt(input);
				
		if(choice < min || choice > max) {
			choice = min;
		}
		return choice;
	}
	
	// Methode permettant de valider si une chaine de caractere correspond a un nombre reel
	// Parametre : Une chaine de caractere
	// Valeur de retour : Retourne vrai si la chaine de caractere est un nombre reel, sinon faux
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}

}


