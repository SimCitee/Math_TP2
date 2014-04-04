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

		System.out.print("Entrez le nombre de sommets : ");
		choixNbSommet = lireChoix(1, 100);
		System.out.println("");
		Graphe graphe = new Graphe(choixNbSommet);

		do {
			System.out.print("Voici les sommets : ");
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
			
			System.out.println("Voulez-vous ajouter un arc? (1 pour oui / 0 pour non) : ");
			choixAjouterArc = lireChoix(0, 1);
		} while(choixAjouterArc == 1);
		
		graphe.calculerDegreePositifNegatif();
		//graphe.afficherMatriceAdjacence();
		
		System.out.println("Le degree de chaque sommet (sommet / degreePositif / degreeNegatif) : ");
		for(Sommet s : graphe.getListeSommets()) {
			System.out.println(s.getEtiquette() + " : " + s.getDegreePositif() + " : " + s.getDegreeNegatif());
		}

		if(graphe.estCycleEulerien()) {
			System.out.println("Le graphe contient un cycle eulerien");
		}
		else {
			System.out.println("Le graphe ne contient pas de cycle eulerien");
		}
		
		ArrayList<Integer> liste = new ArrayList<Integer>();
		Sommet s = graphe.getSommetParEtiquette(1);
		graphe.trouverCycle(s, liste);
		
		/*for(int som : liste) {
			System.out.println(som);
		}*/
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
		} while(input.isEmpty());

		choice = Integer.parseInt(input);
		
		if(choice < min || choice > max) {
			choice = min;
		}
		return choice;
	}

}


