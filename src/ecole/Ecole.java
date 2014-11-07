package ecole;

import personnes.Etudiant;
import personnes.Etudiants;
import personnes.Personne;
import personnes.Professeur;
import personnes.ProfesseurTuteur;
import personnes.Professeurs;
import scolarite.Module;
import scolarite.Note;
import scolarite.Notes;
import scolarite.Specialite;
import scolarite.Specialites;


public class Ecole {


	private Specialites specialites;
	private Professeurs professeurs;
	private Etudiants etudiants;
	private Notes notes;
	private static String mdpAdmin = "admin";
	
	public Ecole(Specialites specialites, Professeurs professeurs,
			Etudiants etudiants, Notes notes) {
		super();
		this.specialites = specialites;
		this.professeurs = professeurs;
		this.etudiants = etudiants;
		this.notes = notes;
	}

	public Ecole() {
		super();
		this.specialites = new Specialites();
		this.professeurs = new Professeurs();
		this.etudiants = new Etudiants();
		this.notes = new Notes();
	}

	public Specialites getSpecialites() {
		return specialites;
	}

	public void setSpecialites(Specialites specialites) {
		this.specialites = specialites;
	}

	public Professeurs getProfesseurs() {
		return professeurs;
	}
	
	public Professeurs getProfesseursTuteurs(){
		Professeurs profTuteurs = new Professeurs();
		for (Professeur p: professeurs.getProfesseurs()){
			if (p instanceof ProfesseurTuteur){
				profTuteurs.addProfesseur(p);
			}
		}
		
		return profTuteurs;
	}

	public void setProfesseurs(Professeurs professeurs) {
		this.professeurs = professeurs;
	}

	public Etudiants getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Etudiants etudiants) {
		this.etudiants = etudiants;
	}
		
	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	
	public static String getMdpAdmin() {
		return mdpAdmin;
	}

	public static void setMdpAdmin(String mdpAdmin) {
		Ecole.mdpAdmin = mdpAdmin;
	}

	public void addProfesseur(Professeur p) {
		professeurs.addProfesseur(p);
	}
	
	public void addProfesseurTuteur(ProfesseurTuteur p){
		professeurs.addProfesseur(p);
	}
	
	public void deleteProfesseur(Professeur p){
		professeurs.deleteProfesseur(p);
	}
	
	public void addEtudiant(Etudiant e){
		etudiants.addEtudiant(e);
	}
	
	public void deleteEtudiant(Etudiant e){
		etudiants.deleteEtudiant(e);
	}
	
	public void addModule (Specialite s, Module m){
		this.specialites.addModule(s, m);
	}
	
	public void deleteModule (Specialite s, Module m){
		this.specialites.deleteModule(s, m);
	}
	
	public void deleteModule (Module m){
		this.specialites.deleteModule(m);
	}
	
	public void addSpecialite(Specialite s){
		this.specialites.addSpecialite(s);
	}
	
	public void deleteSpecialite(Specialite s){

		this.specialites.deleteSpecialite(s);
	}
	
	public String toString() {
		String s = new String();
		s = s.concat("ecole:\n" + etudiants.toString() + "\n" + professeurs.toString() + "\n" + specialites.toString() + "\n" + notes.toString());
		s = s.concat("\n\nNOMBRE DE PERSONNES: " + Personne.getCompteurPersonnes());
		s = s.concat("\n\nNOMBRE DE MODULES: " + Module.getCompteurModules() + "\n\n");
		
		return s;
	}
	
	public void save(String chemin){
		System.out.println("Debut sauvegarde de l'ecole");
		
		etudiants.save( 	chemin + ".etudiants.save" );
		professeurs.save(	chemin + ".professeurs.save" );
		specialites.save(	chemin + ".specialites.save" );
		notes.save(			chemin + ".notes.save" );
		
		System.out.println("Fin sauvegarde de l'ecole");
	}

	public void charger(String chemin){
		System.out.println("Debut du chargement de l'ecole");
		
		// RaZ des compteurs d'instances
		// il seront incrémentés dans les méthodes charger() et permettent de tenir les id à jour
		Personne.resetCompteurPersonnes();
		Module.resetCompteurModules();
		etudiants.charger(		chemin + ".etudiants.save");
		professeurs.charger(	chemin + ".professeurs.save");
		specialites.charger(	chemin + ".specialites.save");
		notes.charger(			chemin + ".notes.save");
		
		System.out.println("Fin chargement de l'ecole");
	}	
}
