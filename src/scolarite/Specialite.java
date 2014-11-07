package scolarite;

import java.util.Vector;

public class Specialite implements java.io.Serializable {

	private static final long serialVersionUID = 6796512224097932248L;
	private String nom;
    private Vector<Module>  modules;

	/**
	 * @param modules
	 */
	public Specialite(String nom, Vector<Module> modules) {
		super();
		this.nom = nom;
		this.modules = modules;
	}
	
	public Vector<Module> getModules() {
		return modules;
	}

	public void setModules(Vector<Module> modules) {
		this.modules = modules;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void addModule (Module m){
		this.modules.add(m);
	}
	
	public void deleteModule (Module m){
		this.modules.remove(m);
	}

	@Override
	public String toString() {
		return nom + "\t" + modules;
	}   
	
	
}