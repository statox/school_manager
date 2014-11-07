package graphique;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import personnes.Etudiant;
import personnes.ProfesseurTuteur;
import reseau.Client;
import scolarite.Specialite;
import ecole.Ecole;

public class AdministrateurOperationsEtudiantsPanel extends JPanel {
	private Ecole ecole;
	
		// champs ajout etudiant
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAnnee;
	private JList listAnnee;
	private JLabel lblTuteur;
	private JList listTuteur;
	private JLabel lblSpecialite;
	private JList listSpecialite;
	private JButton btnAjouterEtudiant;
	// champs relatifs à la suppression d'un étudiant
	private JLabel lblSupprimerUnEtudiant;
	private JList listEtudiants;
	private JButton btnSupprimerEtudiant;

	/**
	 * 		Creation du panel admin permettant les opérations sur les étudiants:
	 * 			- supprimer un étudiant
	 * 			- rajouter un étudiant
	 * 
	 * 		Les modifications sont changées dans l'école en mémoire dans le serveur donc directement visible
	 * 		par les autres utilisateurs
	 */
	public AdministrateurOperationsEtudiantsPanel(Ecole e) {
		setLayout(null);
		ecole = e;
		
		// champs relatifs à l'ajout d'un étudiant
		JLabel lblAjouterUntudiant = new JLabel("Ajouter un \u00E9tudiant");
		lblAjouterUntudiant.setBounds(24, 41, 141, 14);
		add(lblAjouterUntudiant);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(10, 82, 127, 20);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(191, 82, 127, 20);
		add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		lblNom = new JLabel("Nom:");
		lblNom.setBounds(10, 66, 46, 14);
		add(lblNom);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(192, 66, 46, 14);
		add(lblPrenom);
		
		lblAnnee = new JLabel("Ann\u00E9e:");
		lblAnnee.setBounds(10, 113, 46, 14);
		add(lblAnnee);
		
		listAnnee = new JList();
		listAnnee.setBounds(10, 138, 46, 92);
		add(listAnnee);
		
		lblTuteur = new JLabel("Tuteur:");
		lblTuteur.setBounds(88, 113, 95, 14);
		add(lblTuteur);
		
		listTuteur = new JList();
		listTuteur.setBounds(88, 138, 230, 92);
		add(listTuteur);
		
		lblSpecialite = new JLabel("Sp\u00E9cialit\u00E9:");
		lblSpecialite.setBounds(10, 262, 116, 14);
		add(lblSpecialite);
		
		listSpecialite = new JList();
		listSpecialite.setBounds(10, 287, 308, 152);
		add(listSpecialite);
		
		btnAjouterEtudiant = new JButton("Ajouter");
		btnAjouterEtudiant.setBounds(10, 469, 308, 23);
		add(btnAjouterEtudiant);
		
		// champs relatifs à la suppression d'un étudiant
		lblSupprimerUnEtudiant = new JLabel("Supprimer un etudiant");
		lblSupprimerUnEtudiant.setBounds(422, 41, 148, 14);
		add(lblSupprimerUnEtudiant);
		
		listEtudiants = new JList();
		listEtudiants.setBounds(443, 84, 285, 355);
		add(listEtudiants);
		
		btnSupprimerEtudiant = new JButton("Supprimer");
		btnSupprimerEtudiant.setBounds(443, 469, 285, 23);
		add(btnSupprimerEtudiant);
		
		initialisationChamps();
		
		// boutons interactifs
		btnAjouterEtudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ajouterEtudiant();
			}
		});
		
		btnSupprimerEtudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				supprimerEtudiant();
			}
		});

	}
	
	public void initialisationChamps(){
		listAnnee.setListData(new Object[] {1, 2, 3, 4, 5});
		listTuteur.setListData(ecole.getProfesseursTuteurs().getProfesseurs());
		listSpecialite.setListData(ecole.getSpecialites().getSpecialites());
		listEtudiants.setListData(ecole.getEtudiants().getEtudiants());
	}
	
	public void ajouterEtudiant(){
		String nom 		= textFieldNom.getText();
		String prenom 	= textFieldPrenom.getText();
		int annee		= (int) listAnnee.getSelectedValue();
		ProfesseurTuteur tuteur = (ProfesseurTuteur) listTuteur.getSelectedValue();
		Specialite specialite	= (Specialite) listSpecialite.getSelectedValue();
		
		// ajout de l'étudiant dans l'école en mémoire client
		Etudiant newEtudiant = new Etudiant(nom, prenom, annee, tuteur, specialite);
		ecole.addEtudiant(newEtudiant);
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	public void supprimerEtudiant(){
		Etudiant etudiant = (Etudiant) listEtudiants.getSelectedValue();
		ecole.deleteEtudiant(etudiant);
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
}
