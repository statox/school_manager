package personnes;

import java.util.Vector;

import scolarite.Module;
import scolarite.Specialite;

public class EtudiantAssistant extends Etudiant {


	private static final long serialVersionUID = -1267820098984176726L;
	private Vector<Module>  modules;

	/**
	 * @param nom
	 * @param prenom
	 * @param id
	 * @param annee
	 * @param professeurTuteur
	 * @param specialite
	 * @param modules
	 */
	public EtudiantAssistant(String nom, String prenom, Integer annee,
			ProfesseurTuteur professeurTuteur, Specialite specialite,
			Vector<Module> modules) {
		super(nom, prenom, annee, professeurTuteur, specialite);
		this.modules = modules;
	}

	public Vector<Module> getModules() {
		return modules;
	}

	public void setModules(Vector<Module> modules) {
		this.modules = modules;
	}
}