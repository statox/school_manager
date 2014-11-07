package controleur;

import personnes.Etudiant;
import personnes.Professeur;
import reseau.Client;
import reseau.Serveur;
import ecole.Connection;
import ecole.Ecole;

public class Test1 {


	public static void main(String[] args) {
		Serveur serveur = new Serveur();
		Client client = new Client();
		Ecole ecole = new Ecole();
		Connection connection = new Connection();
		
		serveur.main(null);
		client.chargerEcoleReseau();
		ecole = client.getEcole();
		
		System.out.println(ecole.getProfesseurs());
		
		
		System.out.println("fermeture du serveur");
		client.fermerServeur();
		
	}

}
