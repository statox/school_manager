package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ecole.Ecole;

public class Serveur {

	public Serveur(){
		
	}
	
	/**
	 * M�thode main du serveur:
	 * 		d�s�rialise l'�cole en d�but d'ex�cution
	 * 		ouvre le port 2009
	 * 		lance une boucle qui attend que l'ordre de fermer le serveur
	 * 			dans cette boucle on lance un nouveau thread � chaque connection de client
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Socket socketDuServeur		= null;
		int numeroConnection		= 0;
		ServerSocket socketServeur 	= null;
		
		System.out.println("D�but de l'application serveur");
		
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
				System.out.println("Le serveur est d�j� lanc�");
				return;
			}
			
			System.out.println("En attente de connections de clients");
			
			// tant qu'on � pas recu de socket donnant l'ordre de fermer le serveur
			while (!socketServeur.isClosed()){
				
				// si un client se connecte on l'accepte
				try{
					socketDuServeur = socketServeur.accept(); 
				
					numeroConnection++;
					System.out.println("\n\nconnection numero " + numeroConnection);
					// cr�ation et lancement du threadServeur qui traite la requete du client
					Thread t = new Thread(new ThreadServeur(socketServeur, ecole, socketDuServeur));
					t.start();
				
				}catch (java.net.SocketException e){
					
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}	
		
		// serialisation de l'�cole � la fermeture du serveur
		System.out.println("sauvegarde de l'�cole");
		ecole.save("ecole");
		
		System.out.println("Fin de l'application serveur");
	}
}
