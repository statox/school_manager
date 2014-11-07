package personnes;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

public class Etudiants {

    private Vector<Etudiant>  etudiants;


	public Etudiants(Vector<Etudiant> etudiants) {
		super();
		this.etudiants = etudiants;
	}
    
	public Etudiants() {
		super();
		this.etudiants = new Vector<Etudiant>();
	}
	// retourne l'ensemble des etudiants
	public Vector<Etudiant> getEtudiants() {
		return etudiants;
	}
	// retourne l'un des etudiants selon son id
	public Etudiant getEtudiant(Integer idRecherche){
		for (Etudiant e : etudiants){
			if (e.getId().equals(idRecherche)){
				return e;
			}
		}
		
		return null;
	}

	public void setEtudiants(Vector<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}    

	@Override
	public String toString() {
		String s = new String();
		s = s.concat("Etudiants:\n");
		Iterator<Etudiant> it = etudiants.iterator();
		while (it.hasNext()){
			s = s.concat("\n");
			s = s.concat(it.next().toString());
		}
		
		return s;
	}
	
	// ajout d'un etudiant dans le conteneur
	public void addEtudiant(Etudiant e){
		etudiants.add(e);
	}
	
	// suppression d'un etudiant du conteneur 
	public void deleteEtudiant(int id){
		
		Iterator<Etudiant> it = etudiants.iterator();
		int index=-1;
		boolean continuer=true;
		
		while (it.hasNext() && continuer)
		{
			index++;
			if (it.next().getId()==id){
				continuer=false;
				etudiants.remove(index);
			}
		}			
	}    
	
	// suppression d'un etudiant du conteneur
	public void deleteEtudiant(Etudiant e){
		deleteEtudiant(e.getId());
	}
	
	// serialisation dans un fichier
	public void save(String chemin){
//		System.out.println("Debut de la sauvegarde des etudiants");
		try {
			FileOutputStream monFichier = new FileOutputStream( chemin ) ;
			ObjectOutputStream monFlux = new ObjectOutputStream( monFichier ) ;
			
			save(monFlux);
//			for (int i=0; i<etudiants.size(); ++i){
//				monFlux.writeObject(etudiants.get(i));
//			}
			
			monFlux.flush();
			monFlux.close();
			monFichier.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Fin de la sauvegarde des etudiants");
	}
	// serialization dans un flux
	public void save (ObjectOutputStream monFlux){
		for (int i=0; i<etudiants.size(); ++i){
			try {
				monFlux.writeObject(etudiants.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// deserialisation des etudiants dans le conteneur
	public void charger(String chemin) {

//		System.out.println("Debut de la recuperation des etudiants");
		try {
			FileInputStream monFichier = new FileInputStream( chemin ) ;
			ObjectInputStream monFlux = new ObjectInputStream( monFichier ) ;
			
			// deserialisation depuis un flux
			charger(monFlux);
	
			monFlux.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des etudiants");
	} 	
	// deserialisation des etudiants dans le conteneur a partir d'un flux (pour s'adapter a un fichier ou une socket)
	public void charger(ObjectInputStream monFlux) {

//		System.out.println("Debut de la recuperation des etudiants");
		try {			
			Etudiant tmp;
			//System.out.println(monFlux.available());
			tmp=(personnes.Etudiant)monFlux.readObject();
			Personne.incrementCompteurPersonnes();
			while(tmp != null && !tmp.getNom().equals("Stop")){
				etudiants.add(tmp);
				tmp=(Etudiant)monFlux.readObject();
				Personne.incrementCompteurPersonnes();
			}
		
//			System.out.println("Fin du chargement");
//			monFlux.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			// C'est moche mais on utilise cette exception qui est envoyé par readObject
			// quand il a atteint la fin du flux	
	        return;
	    }catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des etudiants");
	} 	
}