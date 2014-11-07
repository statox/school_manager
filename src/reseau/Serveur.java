package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ecole.Ecole;

public class Serveur {

	public Serveur(){
		
	}
	
	/**
	 * Méthode main du serveur:
	 * 		désérialise l'école en début d'exécution
	 * 		ouvre le port 2009
	 * 		lance une boucle qui attend que l'ordre de fermer le serveur
	 * 			dans cette boucle on lance un nouveau thread à chaque connection de client
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Socket socketDuServeur		= null;
		int numeroConnection		= 0;
		ServerSocket socketServeur 	= null;
		
		System.out.println("Début de l'application serveur");
		
		// deserialisation de notre ecole
		Ecole ecole = new Ecole();
		ecole.charger("ecole");
		
		System.out.println("ecole chargee au demarrage du serveur");
		System.out.println(ecole);
		
		try{
			// le serveur ouvre le port 2009
			try{
				socketServeur = new ServerSocket(2009);
			}catch (java.net.BindException e){
				System.out.println("Le serveur est déjà lancé");
				return;
			}
			
			System.out.println("En attente de connections de clients");
			
			// tant qu'on à pas recu de socket donnant l'ordre de fermer le serveur
			while (!socketServeur.isClosed()){
				
				// si un client se connecte on l'accepte
				try{
					socketDuServeur = socketServeur.accept(); 
				
					numeroConnection++;
					System.out.println("\n\nconnection numero " + numeroConnection);
					// création et lancement du threadServeur qui traite la requete du client
					Thread t = new Thread(new ThreadServeur(socketServeur, ecole, socketDuServeur));
					t.start();
				
				}catch (java.net.SocketException e){
					
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}	
		
		// serialisation de l'école à la fermeture du serveur
		System.out.println("sauvegarde de l'école");
		ecole.save("ecole");
		
		System.out.println("Fin de l'application serveur");
	}
}
