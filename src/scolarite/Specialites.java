package scolarite;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

public class Specialites {

	Vector<Specialite> specialites;

	public Specialites(Vector<Specialite> specialites) {
		super();
		this.specialites = specialites;
	}
	public Specialites() {
		super();
		this.specialites = new Vector<Specialite>();
	}
	
	public Vector<Specialite> getSpecialites() {
		return specialites;
	}
	
	public Vector<Module> getModules(){
		Vector<Module> modules = new Vector<Module>();
		
		for (Specialite s : specialites){
			modules.addAll(s.getModules());
		}
		
		return modules;
	}
	
	public int getIndexSpecialite(Specialite s){
		return this.specialites.indexOf(s);
	}
	
	public void addModule (Specialite s, Module m){
		for (Specialite sTmp: this.specialites){
			if (sTmp.equals(s)){
				sTmp.addModule(m);
				return;
			}
		}
	}	
	
	public void deleteModule (Specialite s, Module m){
		for (Specialite sTmp: this.specialites){
			if (sTmp.equals(s)){
				sTmp.deleteModule(m);
				return;
			}
		}
	}
	
	public void deleteModule (Module m){
		for (Specialite sTmp: this.specialites){
			if (sTmp.getModules().contains(m)){
				sTmp.deleteModule(m);
				return;
			}
		}
	}
	
	public void setSpecialites(Vector<Specialite> specialites) {
		this.specialites = specialites;
	}
	@Override
	public String toString() {
		String s = new String();
		s = s.concat("Specialites:\n");
		Iterator<Specialite> it = specialites.iterator();
		while (it.hasNext()){
			s = s.concat("\n");
			s = s.concat(it.next().toString());
		}
		
		return s;
	}
	
	// ajout d'une specialité dans le conteneur
	public void addSpecialite(Specialite s){
		specialites.add(s);
	}
	
	// suppression d'une specialité du conteneur 
	public void deleteSpecialite(String nom){
		
		Iterator<Specialite> it = specialites.iterator();
		int index=-1;
		boolean continuer=true;
		
		while (it.hasNext() && continuer)
		{
			index++;
			if (it.next().getNom()==nom){
				continuer=false;
				specialites.remove(index);
			}
		}			
	}    
	public void deleteSpecialite(int index){
		specialites.remove(index);
	}
	public void deleteSpecialite(Specialite s){
		int index=-1;
		int indexASuppr=-1;
		for (Specialite sTmp: specialites){
			++index;
			if (sTmp.equals(s)){
				indexASuppr=index;
			}
		}
		deleteSpecialite(indexASuppr);
	}
	
	// serialisation dans un fichier
	public void save(String chemin){
//		System.out.println("Debut de la sauvegarde des specialites");
		try {
			FileOutputStream monFichier = new FileOutputStream( chemin ) ;
			ObjectOutputStream monFlux = new ObjectOutputStream( monFichier ) ;
			
			save(monFlux);
//			for (int i=0; i<specialites.size(); ++i){
//				monFlux.writeObject(specialites.get(i));
//			}
			
			monFlux.flush();
			monFlux.close();
			monFichier.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Fin de la sauvegarde des specialites");
	}
	
	// serialisation dans un flux
	public void save (ObjectOutputStream monFlux){
		for (int i=0; i<specialites.size(); ++i){
			try {
				monFlux.writeObject(specialites.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// deserialisation depuis un fichier
	public void charger(String chemin) {

//		System.out.println("Debut de la recuperation des specialites");
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
		
//		System.out.println("Fin de la recuperation des specialites");
	} 		
	
	// deserialisation d'un flux (pour s'adapter a un fichier ou une socket)
	public void charger(ObjectInputStream monFlux) {

//		System.out.println("Debut de la recuperation des specialites");
		try {			
				Specialite tmp;
				tmp=(Specialite)monFlux.readObject();
				for (Module m : tmp.getModules()){
					m.getId();
					Module.incrementCompteurModules();
				}
				while(tmp != null && !tmp.getNom().equals("Stop") ){
					specialites.add(tmp);
					tmp=(Specialite)monFlux.readObject();
					for (Module m : tmp.getModules()){
						m.getId();
						Module.incrementCompteurModules();
					}
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
		
//		System.out.println("Fin de la recuperation des specialites");
	} 	

}
