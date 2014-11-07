package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import personnes.Etudiant;
import personnes.Etudiants;
import personnes.Professeur;
import personnes.Professeurs;
import scolarite.Module;
import scolarite.Notes;
import scolarite.Specialite;
import scolarite.Specialites;
import ecole.Ecole;

public class ThreadServeur implements Runnable {

//	private Ecole ecole = new Ecole() ;
//	private ServerSocket socketServeur;
//	private Socket socketDuServeur;
//	private int numeroConnection;
//	
//	public ThreadServeur(ServerSocket s, Ecole ecoleServeur){
//		socketServeur = s;
//		numeroConnection=0;
//		ecole=ecoleServeur;
//	}

	private Ecole ecole = new Ecole() ;
	private ServerSocket socketServeur;
	private Socket socketDuServeur;
	private int numeroConnection;
	
	public ThreadServeur(ServerSocket s, Ecole ecoleServeur, Socket s2){
		socketServeur = s;
		numeroConnection=0;
		ecole=ecoleServeur;
		socketDuServeur = s2;
	}
	
	/**
	 *  Ce thread reste vivant tant qu'aucun client ne demande la fermeture du serveur
	 *  On se place en attente des connections et à chaque connection on traite la requete du client
	 *  Les requetes sont de 3 types:
	 *  	close: 		sérialise l'école en mémoire, ferme la socket du serveur et clot le thread
	 *  	getEcole:	ecris l'école en mémoire dans le flux de la socket
	 *  	saveEcole:	lis l'école dans le flux de la socket et l'écris dans l'école en mémoire du serveur
	 * 
	 */
//	@Override
//	public void run() {
//		try {
//			boolean continuer=true;
//			while(continuer){
//				socketDuServeur = socketServeur.accept(); // Un client se connecte on l'accepte
//				numeroConnection++;
//				
//				System.out.println("\n\nconnection numero " + numeroConnection);
//				
//				// test pour savoir ce qu'il faut faire
//				ObjectInputStream in = new ObjectInputStream(socketDuServeur.getInputStream());
//				String message_client = (String) in.readObject();
//				
//				System.out.println("ordre du client: " + message_client);
//				if (message_client.equals("close")){
//					System.out.println("On arrete  le serveur sur ordre du client");
//					socketDuServeur.close();
//					socketServeur.close();
//					ecole.save("ecole");
//					return;
//				}else if (message_client.equals("getEcole")){
//					
////					System.out.println(ecole);
//					
//					// on crée un nouveau flux d'objet
//					ObjectOutputStream out = new ObjectOutputStream(socketDuServeur.getOutputStream());
//					out.flush();
//					
//					// on ecrit nos étudiants dedans
//					for (Etudiant e : ecole.getEtudiants().getEtudiants()){
//						out.writeObject(e);
//					}
//					out.writeObject(new Etudiant("Stop", "Stop", 1, null, null));
//					out.flush();
//	
//					// on ecrit nos professeurs dedans			
//					for (Professeur p : ecole.getProfesseurs().getProfesseurs()){
//						out.writeObject(p);
//					}
//					out.writeObject(new Professeur("Stop", "Stop", null));
//					out.flush();
//					
//					// on ecrit nos specialites dedans
//					for (Specialite s : ecole.getSpecialites().getSpecialites()){
//						out.writeObject(s);
//					}
//					out.writeObject(new Specialite("Stop", new Vector<Module>() ) );
//					out.flush();
//					
//					// on ecrit nos notes dedans
//					out.writeObject(ecole.getNotes().getNotes());
//					out.flush();
//					
////					Thread.sleep(15000);
//					socketDuServeur.close();
//				}else if (message_client.equals("saveEcole")){
//					
//					// TODO ici pour sauvegarder l'ecole il faut que le client envoit les conteneurs uns par
//					// un pcq l'objet ecole n'est pas serialisable
//					
//					// on charge les etudiants depuis le flux
//					Etudiants etudiants = new Etudiants();
//					etudiants.charger(in);
//					
//					Professeurs professeurs = new Professeurs();
//					professeurs.charger(in);
//					
//					Specialites specialites = new Specialites();
//					specialites.charger(in);
//					
//					Notes notes = new Notes();
//					notes.charger(in);
//					
//					ecole.setEtudiants(etudiants);
//					ecole.setProfesseurs(professeurs);
//					ecole.setSpecialites(specialites);
//					ecole.setNotes(notes);					
//				}
//			}
//		} catch (IOException e) {
//		e.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 		
	@Override
	public void run() {
		try {						
			// test pour savoir ce qu'il faut faire
			ObjectInputStream in = new ObjectInputStream(socketDuServeur.getInputStream());
			String message_client = (String) in.readObject();
			
			System.out.println("ordre du client: " + message_client);
			if (message_client.equals("close")){
				System.out.println("On arrete  le serveur sur ordre du client");
				System.out.println("fermeture socketDuServeur");
				socketDuServeur.close();
				System.out.println("fermeture socketServeur");
				socketServeur.close();
				return;
			}else if (message_client.equals("getEcole")){				
				// on crée un nouveau flux d'objet
				ObjectOutputStream out = new ObjectOutputStream(socketDuServeur.getOutputStream());
				out.flush();
				
				// on ecrit nos étudiants dedans
				ecole.getEtudiants().save(out);
				out.writeObject(new Etudiant("Stop", "Stop", 1, null, null));
				out.flush();

				// on ecrit nos professeurs dedans			
				ecole.getProfesseurs().save(out);
				out.writeObject(new Professeur("Stop", "Stop", null));
				out.flush();
				
				// on ecrit nos specialites dedans
				ecole.getSpecialites().save(out);
				out.writeObject(new Specialite("Stop", new Vector<Module>() ) );
				out.flush();
				
				// on ecrit nos notes dedans
				ecole.getNotes().save(out);
				out.flush();
				
				socketDuServeur.close();
			}else if (message_client.equals("saveEcole")){
				
				// TODO ici pour sauvegarder l'ecole il faut que le client envoit les conteneurs uns par
				// un pcq l'objet ecole n'est pas serialisable
				
				// on charge les etudiants depuis le flux
				Etudiants etudiants = new Etudiants();
				etudiants.charger(in);
				
				Professeurs professeurs = new Professeurs();
				professeurs.charger(in);
				
				Specialites specialites = new Specialites();
				specialites.charger(in);
				
				Notes notes = new Notes();
				notes.charger(in);
				
				ecole.setEtudiants(etudiants);
				ecole.setProfesseurs(professeurs);
				ecole.setSpecialites(specialites);
				ecole.setNotes(notes);					
				
				socketDuServeur.close();
			}
		} catch (IOException e) {
		e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		
	}
}
