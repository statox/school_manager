package scolarite;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

import personnes.Etudiant;
import personnes.Personne;

public class Notes {
	
	// La clé String est la concaténation des IDs d'un etudiant et d'un module
	// La donnée sera un objet de type note
	private HashMap<String, Note> notes;

	public Notes(HashMap<String, Note> notes) {
		super();
		this.notes = notes;
	}
	
	public Notes() {
		super();
		this.notes = new HashMap<String, Note>();
	}

	public HashMap<String, Note> getNotes() {
		return notes;
	}

	public void setNotes(HashMap<String, Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
//		return "Notes [notes=" + notes + "]";
		String s = new String();
		s = s.concat("Notes:\n");
		
		Set<String> cles = notes.keySet();
		java.util.Iterator<String> it = cles.iterator();
		
		while (it.hasNext()){
			String cle = it.next();
			Note note = notes.get(cle);
			// methode qui deconne: si les id vont au dessus de 9 (nombres a 2 chiffres) c'est pas cool
			s = s.concat(cle + "\t");
			s = s.concat(note.toString() + "\n");
			
		}
		
		return s;
	}
	
	public boolean addNote(Etudiant e, Module m, Note n){
		// creation de la clé
		String cle = new String();
		// a l'origine il n'y avait pas le "e" et le "m" dans la clé sauf que du coup la clé n'était
		// pas unique: on ne pouvait pas différencier la note de l'etudiant 11 dans le module 2
		// et la note de l'etudiant 1 dans le module 12
		cle = "e" + e.getId().toString() + "m" + m.getId().toString();
		
		// on vérifie que l'étudiant est bien inscrit dans le module
		if (e.getSpecialite().getModules().contains(m)){
			// on met le tableau dans HashMap
			notes.put(cle, n);
			
			return true;
		}
		return false;		
	}
	
	public Note getNote(Etudiant e, Module m){
		// creation de la clé
		String cle = new String();
		cle = "e" + e.getId().toString() + "m" + m.getId().toString();
	
		// on retourne la note desirée
		return notes.get(cle);
	}

	public Notes getNotesModule(Module m){
		Notes notesModule = new Notes();
		String cle = new String();
		
		// on parcours toutes les personnes pour créer une clé avec tous les id et le module selectionné
		for (int i=0; i<=Personne.getCompteurPersonnes(); i++){
			cle = "e" + i + "m" + m.getId().toString();
			if (notes.get(cle)!=null){
				notesModule.getNotes().put(cle, notes.get(cle));
			}
		}		
		return notesModule;
	}
	
	public Notes getNotesEtudiant(Etudiant e){
		Notes notesEtudiant = new Notes();
		String cle = new String();
		
		// on parcours toutes les personnes pour créer une clé avec tous les id et le module selectionné
		for (int i=0; i<=Module.getCompteurModules(); i++){
			cle = "e" + e.getId() + "m" + i;
			if (notes.get(cle)!=null){
				notesEtudiant.getNotes().put(cle, notes.get(cle));
			}
		}		
		return notesEtudiant;
	}
	
	// serialisation dans un fichier
	public void save(String chemin){
//		System.out.println("Debut de la sauvegarde des notes");
		try {
			FileOutputStream monFichier = new FileOutputStream( chemin ) ;
			ObjectOutputStream monFlux = new ObjectOutputStream( monFichier ) ;
			
			save(monFlux);
//			monFlux.writeObject(notes);
			
			monFlux.flush();
			monFlux.close();
			monFichier.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Fin de la sauvegarde des notes");
	}
	// serialisation dans un flux
	public void save (ObjectOutputStream monFlux){
		try {
			monFlux.writeObject(notes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// deserialisation des etudiants dans le conteneur
	public void charger(String chemin) {

//		System.out.println("Debut de la recuperation des notes");
		try {
			FileInputStream monFichier = new FileInputStream( chemin ) ;
			ObjectInputStream monFlux = new ObjectInputStream( monFichier ) ;
		
			charger(monFlux);
			monFlux.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des notes");
	} 	

	// deserialisation d'un flux (pour s'adapter a un fichier ou une socket)
	public void charger(ObjectInputStream monFlux) {

//		System.out.println("Debut de la recuperation des notes");
		try {			
			notes=(HashMap)monFlux.readObject();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			// C'est moche mais on utilise cette exception qui est envoyé par readObject
			// quand il a atteint la fin du flux	
	        return;
	    }catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des notes");
	} 		
	
	
}