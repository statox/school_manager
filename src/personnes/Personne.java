package personnes;

public class Personne implements java.io.Serializable {

	private static Integer compteurPersonnes = 0;
	private static final long serialVersionUID = 604232935817425016L;	// ID pour la serialisation	
	private String nom;
	private String prenom;
	private Integer id;
	private String mdp;

	public Personne() {
		super();
		compteurPersonnes++;
		this.nom = "default";
		this.prenom = "default";
		this.mdp = "default";
		this.id = compteurPersonnes;
	}
	
	public Personne(String nom, String prenom) {
		super();
		compteurPersonnes++;
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = "default";
		this.id = compteurPersonnes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp, String newMdp) {
		
		if (!mdp.equals(this.mdp)){
			System.out.println("mauvais mot de passe, modification interdite");
			return;
		}
		System.out.println("nouveau mot de passe definit");
		this.mdp = newMdp;
	}

	public static Integer getCompteurPersonnes() {
		return compteurPersonnes;
	}

	public static void incrementCompteurPersonnes(){
		Personne.compteurPersonnes++;
	}
	
	public static void decrementCompteurPersonnes(){
		Personne.compteurPersonnes--;
	}
	
	public static void resetCompteurPersonnes() {
		Personne.compteurPersonnes = 0;
	}
	

	@Override
	public String toString() {
		return id + ": " + prenom +" " + nom + "\tmdp: " + mdp;
	}
}