package scolarite;

import personnes.Etudiant;
import personnes.Etudiants;


public class Module implements java.io.Serializable {

	private static Integer compteurModules = 0;
	private static final long serialVersionUID = 6668264123394558849L;
	private Integer id;
	private Integer annee;
	private String nom;
	/**
	 * @param id
	 * @param annee
	 * @param nom
	 */
	public Module(Integer annee, String nom) {
		super();
		compteurModules++;
		this.id = compteurModules;
		this.annee = annee;
		this.nom = nom;
	}
	
	// récupérer les étudiants qui suivent le module
	public Etudiants getEtudiants(Etudiants etudiants){
		Etudiants inscrits = new Etudiants();
		
		for (Etudiant e : etudiants.getEtudiants()){
			if (e.getSpecialite().getModules().contains(this)){
				inscrits.addEtudiant(e);
			}
		}
		return inscrits;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnnee() {
		return annee;
	}
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public static Integer getCompteurModules() {
		return compteurModules;
	}
	public static void incrementCompteurModules() {
		Module.compteurModules++;
	}
	public static void resetCompteurModules() {
		Module.compteurModules = 0;
	}
	@Override
	public String toString() {
		return "id=" + id + " " + nom + " " + annee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annee == null) ? 0 : annee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (annee == null) {
			if (other.annee != null)
				return false;
		} else if (!annee.equals(other.annee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
    
	
	
  	
    

}