package controleur;

import java.util.Vector;

import personnes.Etudiant;
import personnes.EtudiantAssistant;
import personnes.Etudiants;
import personnes.Professeur;
import personnes.ProfesseurTuteur;
import personnes.Professeurs;
import scolarite.Module;
import scolarite.Note;
import scolarite.Notes;
import scolarite.Specialite;
import scolarite.Specialites;
import ecole.Ecole;

public class ResetSauvegardeEcole {

	/**
	 * programme auxiliaire notamment utilisé pour les tests pour écrire une école 
	 * peuplée "à la main" dans la méthode creerEcoleManuellement()
	 * 
	 * @param args inutilisés
	 */
	public static void main(String[] args) {
		Ecole ecole = new Ecole();
		 
		//		 ecole.charger("ecole");
		ecole = creerEcoleManuellement();
		ecole.save("ecole");
		 
		System.out.println(ecole);

	}
	
	public static Ecole creerEcoleManuellement(){
		
		// creation des modules
		Module algebre 			= new Module(2, "algebre");
		Module analyse 			= new Module(2, "analyse");
		Module mecanique 		= new Module(1, "mecanique");
		Module electronique 	= new Module(1, "electronique");
		
		Vector<Module> math = new Vector<Module>();
		math.add(algebre);
		math.add(analyse);
		
		Vector<Module> phys = new Vector<Module>();
		phys.add(mecanique);
		phys.add(electronique);
		
		// creation des specialites
		Specialite mathematiques 	= new Specialite("mathematiques", math);
		Specialite physique 		= new Specialite("physique", phys);
		
		// creation du conteneur de specialites
		Specialites specialites = new Specialites();
		specialites.addSpecialite(mathematiques);
		specialites.addSpecialite(physique);
		
		// creation des professeurs
		Professeur prof1 = new Professeur("Durant",		"Jean",		math);
		Professeur prof2 = new Professeur("Dugenou",	"Marcel",	math);		
		Professeur prof3 = new Professeur("Dupot",		"Jacques",	phys);
		
		// creation des professeurs tuteurs
		ProfesseurTuteur profTuteur1 = new ProfesseurTuteur("Dupond",	"Pierre",	math);
		ProfesseurTuteur profTuteur2 = new ProfesseurTuteur("Dupont",	"Paul",		math);
		ProfesseurTuteur profTuteur3 = new ProfesseurTuteur("Delorme", 	"Marc",		phys);
		
		// creation du conteneur de professeurs
		Professeurs professeurs = new Professeurs();
		professeurs.addProfesseur(prof1);
		professeurs.addProfesseur(prof2);
		professeurs.addProfesseur(prof3);
		professeurs.addProfesseur(profTuteur1);
		professeurs.addProfesseur(profTuteur2);
		professeurs.addProfesseur(profTuteur3);
		
		// creation des etudiants
		Etudiant etudiant1 = new Etudiant("Connerie",	"Sean",		2, profTuteur1, mathematiques);
		Etudiant etudiant2 = new Etudiant("DeNiro",		"Robert",	2, profTuteur2, mathematiques);
		Etudiant etudiant3 = new Etudiant("Cruze",		"Tom",		1, profTuteur3, physique);
		Etudiant etudiant4 = new Etudiant("DiCaprio",	"Leonard",	1, profTuteur3, physique);
		
		// creation des etudiants assistants
		EtudiantAssistant etudiantAssistant1 = new EtudiantAssistant("Poiot", "Hercule", 2, profTuteur1, mathematiques, phys);		
		
		// creation du conteneur d'etudiants
		Etudiants etudiants = new Etudiants();
		etudiants.addEtudiant(etudiant1);
		etudiants.addEtudiant(etudiant2);
		etudiants.addEtudiant(etudiant3);
		etudiants.addEtudiant(etudiant4);
		etudiants.addEtudiant(etudiantAssistant1);
		
		// creation des notes
		Note n1 = new Note(17.0, 	2.0, 	15.0);
		Note n2 = new Note(5.0, 	2.0, 	1.0);
		Note n3 = new Note(14.0, 	0.0, 	17.0);
		Note n4 = new Note(4.0, 	14.0, 	17.0);
		Note n5 = new Note(5.0, 	5.0, 	9.0);
		
		// creation du conteneur de notes
		Notes notes = new Notes();
		notes.addNote(etudiant1, 			algebre, 		n1);
		notes.addNote(etudiant2, 			analyse, 		n2);
		notes.addNote(etudiant3, 			mecanique, 		n3);
		notes.addNote(etudiant4, 			electronique,	n4);
		notes.addNote(etudiantAssistant1, 	algebre, 		n5);
		
		
		// creation de l'ecole
		Ecole ecole = new Ecole(specialites, professeurs, etudiants, notes);
		
		return ecole;
	
	}

}
