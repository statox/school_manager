package ecole;

import java.util.Vector;

import personnes.*;

public class Connection {

	private Ecole ecole;
	
	public Connection (){
		
	}
	
	public Connection (Ecole e){
		ecole = e;
	}
	
	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	/**
	 * Cherche le couple id/mdp dans les attributs des �tudiants de l'�cole
	 * 
	 * @param id
	 * @param mdpEntre
	 * @return 	l'�tudiant correspondant si il existe
	 * 			null sinon
	 */
	public Etudiant connectionEtudiant(Integer id, String mdpEntre) {
		
		for (Etudiant e : ecole.getEtudiants().getEtudiants()){
			if (e.getId().equals(id) && e.getMdp().equals(mdpEntre)){
				return e;
			}
		}
		return null;
	}

	/**
	 * Cherche le couple id/mdp dans les attributs des professeurs de l'�cole
	 * 
	 * @param id
	 * @param mdpEntre
	 * @return 	le professeur correspondant si il existe
	 * 			null sinon
	 */
	public Professeur connectionProfesseur(Integer id, String mdpEntre){
		// on parcours tous les professeurs de l'�cole
		for (Professeur p : ecole.getProfesseurs().getProfesseurs()){
			if (p.getId().equals(id) && p.getMdp().equals(mdpEntre)){
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Cherche le couple id/mdp dans les attributs des professeurs de l'�cole qui sont 
	 * des instances de la classe ProfesseurTuteur
	 * 
	 * @param id
	 * @param mdpEntre
	 * @return 	le professeurTuteur correspondant si il existe
	 * 			null sinon
	 */
	public ProfesseurTuteur connectionProfesseurTuteur(Integer id, String mdpEntre){
		for (Professeur p : ecole.getProfesseurs().getProfesseurs()){
			if (p instanceof ProfesseurTuteur && p.getId().equals(id) && p.getMdp().equals(mdpEntre)){

				return (ProfesseurTuteur) p;
			}
		}
		return null;
	}
	
	/**
	 * V�rifie si le mdp entr� correspond au champ mdpAdmin de l'�cole
	 * 
	 * @param mdpEntre
	 * @return	une nouvelle personne si le mot de passe est le bon
	 * 			null sinon
	 */
	public Personne connectionAdmin(String mdpEntre){
		if (ecole.getMdpAdmin().equals(mdpEntre)){
			Personne.decrementCompteurPersonnes();
			return new Personne("Admin", "Admin");
		}
		return null;
	}
	
}
