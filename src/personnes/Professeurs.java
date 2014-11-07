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

public class Professeurs {

    public Vector<Professeur>  professeurs;

	/**
	 * @param professeurs
	 */
	public Professeurs(Vector<Professeur> professeurs) {
		super();
		this.professeurs = professeurs;
	}
	public Professeurs() {
		super();
		this.professeurs = new Vector<Professeur>();
	}

	public Vector<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(Vector<Professeur> professeurs) {
		this.professeurs = professeurs;
	}
	
	@Override
	public String toString() {
		String s = new String();
		s = s.concat("Professeurs:\n");
		Iterator<Professeur> it = professeurs.iterator();
		while (it.hasNext()){
			s = s.concat("\n");
			s = s.concat(it.next().toString());
		}
		
		return s;
	}
	
	// ajout d'un professeur dans le conteneur
	public void addProfesseur(Professeur p){
		professeurs.add(p);
	}
	
	// suppression d'un professeur du conteneur 
	public void deleteProfesseur(int id){
		
		Iterator<Professeur> it = professeurs.iterator();
		int index=-1;
		boolean continuer=true;
		
		while (it.hasNext() && continuer)
		{
			index++;
			if (it.next().getId()==id){
				continuer=false;
				professeurs.remove(index);
			}
		}			
	}    
	
	public void deleteProfesseur(Professeur p){
		deleteProfesseur(p.getId());
	}
	
	// serialisation dans un fichier
	public void save(String chemin){
//		System.out.println("Debut de la sauvegarde des professeurs");
		try {
			FileOutputStream monFichier = new FileOutputStream( chemin ) ;
			ObjectOutputStream monFlux = new ObjectOutputStream( monFichier ) ;
			
			save(monFlux);
//			for (int i=0; i<professeurs.size(); ++i){
//				monFlux.writeObject(professeurs.get(i));
//			}
			
			monFlux.flush();
			monFlux.close();
			monFichier.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Fin de la sauvegarde des professeurs");
	}
	
	// serialisation dans un flux
	public void save(ObjectOutputStream monFlux){
		for (int i=0; i<professeurs.size(); ++i){
			try {
				monFlux.writeObject(professeurs.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// deserialisation a partir d'un fichier
	public void charger(String chemin) {

//		System.out.println("Debut de la recuperation des professeurs");
		try {
			FileInputStream monFichier = new FileInputStream( chemin ) ;
			ObjectInputStream monFlux = new ObjectInputStream( monFichier ) ;
			
			// deserialisation a partir du flux
			charger(monFlux);
			monFlux.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des professeurs");
	} 	
	
	// deserialisation des professeurs dans le conteneur a partir d'un flux (pour s'adapter a un fichier ou une socket)
	public void charger(ObjectInputStream monFlux) {
//		System.out.println("Debut de la recuperation des professeurs");
		try {			
			Professeur tmp;
			tmp=(Professeur)monFlux.readObject();
			Personne.incrementCompteurPersonnes();
			while(tmp != null && !tmp.getNom().equals("Stop")){
				professeurs.add(tmp);
				tmp=(Professeur)monFlux.readObject();
				Personne.incrementCompteurPersonnes();
			}
		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			// C'est moche mais on utilise cette exception qui est envoyé par readObject
			// quand il a atteint la fin du flux	
	        return;
	    }catch (IOException e) {
			e.printStackTrace();
		} 
		
//		System.out.println("Fin de la recuperation des professeurs");
	}
}