package personnes;

import java.util.Set;
import java.util.Vector;
import java.util.HashMap;

import ecole.Ecole;
import scolarite.Note;
import scolarite.Notes;
import scolarite.Specialite;
import scolarite.Specialites;
import scolarite.Module;



public class Etudiant extends Personne {

	private static final long serialVersionUID = 8949583193770990839L;
	private Integer annee;
	private ProfesseurTuteur professeurTuteur;
	private Specialite specialite;
	
	/**
	 * @param nom
	 * @param prenom
	 * @param id
	 * @param annee
	 * @param professeurTuteur
	 * @param specialite
	 */
	public Etudiant(String nom, String prenom, Integer annee,
			ProfesseurTuteur professeurTuteur, Specialite specialite) {
		super(nom, prenom);
		this.annee = annee;
		this.professeurTuteur = professeurTuteur;
		if (professeurTuteur!=null){
			professeurTuteur.addTutore(this);
		}
		this.specialite = specialite;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public ProfesseurTuteur getProfesseurTuteur() {
		return professeurTuteur;
	}

	public void setProfesseurTuteur(ProfesseurTuteur professeurTuteur) {
		this.professeurTuteur = professeurTuteur;
		professeurTuteur.addTutore(this);
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	@Override
	public String toString() {
		return super.toString() 
				+  "\n\tannee " + annee + "\t specialite " + specialite.getNom() 
				+ "\n\tprofesseurTuteur: " + professeurTuteur.getPrenom() +" " + professeurTuteur.getNom();
	}    	
}

