package personnes;

import java.util.Vector;

import scolarite.Module;

public class Professeur extends Personne {

	private static final long serialVersionUID = -2698346930496988831L;
	private Vector<Module>  modules;

	/**
	 * @param nom
	 * @param prenom
	 * @param id
	 * @param modules
	 */
	public Professeur(String nom, String prenom, Vector<Module> modules) {
		super(nom, prenom);
		this.modules = modules;
	}

	public Vector<Module> getModules() {
		return modules;
	}

	public void setModules(Vector<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module m){
		this.modules.add(m);
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tenseigne: \t" + modules;
	}
	
	
	
	
}