package controleur;

import graphique.Fenetre;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Créatinon de la fenetre contenant l'application
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		System.out.println("lancement de l'appli");
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}
}
