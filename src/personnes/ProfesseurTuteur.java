package personnes;

import java.util.Vector;

import ecole.Ecole;
import scolarite.Module;
import scolarite.Notes;

public class ProfesseurTuteur extends Professeur {

	private static final long serialVersionUID = 4455040625339836632L;
	public Vector<Etudiant>  tutores;

	/**
	 * @param nom
	 * @param prenom
	 * @param id
	 * @param modules
	 * @param tutores
	 */
	public ProfesseurTuteur(String nom, String prenom, Vector<Module> modules, Vector<Etudiant> tutores) {
		super(nom, prenom, modules);
		this.tutores = tutores;
	}
	public ProfesseurTuteur(String nom, String prenom, Vector<Module> modules) {
		super(nom, prenom, modules);
		this.tutores = new Vector<Etudiant>();
	}

	public Vector<Etudiant> getTutores() {
		return tutores;
	}

	public void setTutores(Vector<Etudiant> tutores) {
		this.tutores = tutores;
	}
	
	public void addTutore(Etudiant tutore){
		this.tutores.add(tutore);
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		
		for (Etudiant e : tutores){
			s = s.concat("\n\t" + e.getPrenom() + " " + e.getNom());
		}
				
		return s;

	}
	
	public Notes getNotesTutores (Ecole ecole){
		Notes notesEtudiants = new Notes();
		for (Etudiant etudiant : tutores){
			Notes tmp = ecole.getNotes().getNotesEtudiant(etudiant);
			notesEtudiants.getNotes().putAll(tmp.getNotes());
		}
		
		return notesEtudiants;
	}

}