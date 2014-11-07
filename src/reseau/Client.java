package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import personnes.Etudiant;
import personnes.Personne;
import personnes.Professeur;
import scolarite.Module;
import scolarite.Specialite;
import ecole.Ecole;

public class Client {

	private Ecole ecole;

	public Client(){
		
	}
	
	public Ecole getEcole() {
		return ecole;
	}

	/**
	 * demande l'école au serveur et stocke l'école récupérée dans l'attribut ecole
	 * 
	 */
	public void chargerEcoleReseau() {
		Socket socket;
		// ecole que l'on va charger depuis le reseau		
		ecole = new Ecole();
		
        try {
        	// on envoit la demande de connection au serveur
        	socket = new Socket(InetAddress.getLocalHost(),2009);   
        	
        	// on ecrit le message pour récupérer l'ecole
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	String message_get = new String("getEcole");
        	out.writeObject(message_get);
        	
            // on récupere le flux d'entrée de la socket
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            // Désérialisations à partir de la socket:
            Personne.resetCompteurPersonnes();
            Module.resetCompteurModules();
            // etudiants
            ecole.getEtudiants().charger(in);            
            // professeurs
            ecole.getProfesseurs().charger(in);
            // specialites
            ecole.getSpecialites().charger(in);
            // notes
            ecole.getNotes().charger(in);
            
            // on clot la socket
            socket.close();                        
            
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }        
	}
	
	/**
	 * 	Envoi au serveur les mises à jour de l'école
	 * 
	 * @param ecoleSave: école mise à jour à sauvegarder
	 */
	public void saveEcoleReseau(Ecole ecoleSave){
		Socket socket;
		
		try {
			socket = new Socket(InetAddress.getLocalHost(),2009);
			
			// on ecrit dans la socket un message pour recuperer l'ecole
	    	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	    	String message_arret = new String("saveEcole");
	    	out.writeObject(message_arret);
	    	
	    	ecoleSave.getEtudiants().save(out);
	    	out.writeObject(new Etudiant("Stop", "Stop", 1, null, null));
	    	out.flush();
	    	ecoleSave.getProfesseurs().save(out);
	    	out.writeObject(new Professeur("Stop", "Stop", null));
	    	out.flush();
	    	ecoleSave.getSpecialites().save(out);
	    	out.writeObject(new Specialite("Stop", new Vector<Module>() ) );
	    	out.flush();
	    	ecoleSave.getNotes().save(out);
	    	out.flush();
	    	
	    	socket.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Envoi au serveur de l'ordre de fermeture
	 * 
	 */
	public void fermerServeur(){
		Socket socket;
		
		try {
			socket = new Socket(InetAddress.getLocalHost(),2009);
			
        	// on ecrit dans la socket un message pour fermer le serveur
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	String message_arret = new String("close");
        	System.out.println(message_arret);
        	out.writeObject(message_arret);
			
        	socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
