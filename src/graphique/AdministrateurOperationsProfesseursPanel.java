package graphique;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import personnes.Professeur;
import personnes.ProfesseurTuteur;
import reseau.Client;
import scolarite.Module;
import ecole.Ecole;

public class AdministrateurOperationsProfesseursPanel extends JPanel {
	private Ecole ecole;
	
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JList listProfesseurs;
	private JButton btnSupprimerProfesseur;
	private JList listModulesNouveauProfesseur;
	private JButton btnAjouterProfesseur;
	private JLabel lblEnseignantTuteur;
	private JCheckBox chckbxProfesseurTuteur;

	/**
	 * Create the panel.
	 */
	public AdministrateurOperationsProfesseursPanel(Ecole e) {
		setLayout(null);
		ecole = e;
		
		JLabel lblSupprimerProfesseur = new JLabel("Supprimer Professeur");
		lblSupprimerProfesseur.setBounds(459, 24, 203, 14);
		add(lblSupprimerProfesseur);
		
		listProfesseurs = new JList();
		listProfesseurs.setBounds(459, 63, 269, 334);
		add(listProfesseurs);
		
		btnSupprimerProfesseur = new JButton("Supprimer professeur");
		btnSupprimerProfesseur.setBounds(459, 429, 269, 23);
		add(btnSupprimerProfesseur);
		
		JLabel lblAjouterProfesseur = new JLabel("Ajouter Professeur");
		lblAjouterProfesseur.setBounds(30, 24, 246, 14);
		add(lblAjouterProfesseur);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(30, 64, 46, 14);
		add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(30, 89, 86, 20);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(151, 64, 125, 14);
		add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(151, 89, 86, 20);
		add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblModulesEnseigns = new JLabel("Modules enseign\u00E9s");
		lblModulesEnseigns.setBounds(30, 158, 207, 14);
		add(lblModulesEnseigns);
		
		listModulesNouveauProfesseur = new JList();
		listModulesNouveauProfesseur.setBounds(30, 183, 207, 63);
		add(listModulesNouveauProfesseur);
		
		btnAjouterProfesseur = new JButton("Ajouter Professeur");
		btnAjouterProfesseur.setBounds(30, 257, 210, 23);
		add(btnAjouterProfesseur);
		
		lblEnseignantTuteur = new JLabel("Enseignant tuteur");
		lblEnseignantTuteur.setBounds(30, 120, 163, 14);
		add(lblEnseignantTuteur);
		
		chckbxProfesseurTuteur = new JCheckBox("");
		chckbxProfesseurTuteur.setBounds(221, 116, 97, 23);
		add(chckbxProfesseurTuteur);

		initialisationChamps();
		
		btnAjouterProfesseur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addProfesseur();
			}
		});
		
		btnSupprimerProfesseur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				deleteProfesseur();
			}
		});
	}

	public void initialisationChamps(){
		listProfesseurs.setListData(ecole.getProfesseurs().getProfesseurs());
		listModulesNouveauProfesseur.setListData(ecole.getSpecialites().getModules());
	}
	
	public void addProfesseur(){
		String nom 		= textFieldNom.getText();
		String prenom	= textFieldPrenom.getText();
		Vector<Module> modules	= new Vector<Module>();
		for (Module m: (ArrayList<Module>) listModulesNouveauProfesseur.getSelectedValuesList()){
			modules.add(m);
		}
		
		if (!chckbxProfesseurTuteur.isSelected()){
			ecole.addProfesseur(new Professeur(nom, prenom, modules));
		}else{
			ecole.addProfesseur(new ProfesseurTuteur(nom, prenom, modules));
		}
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	public void deleteProfesseur(){
		Professeur p = (Professeur) listProfesseurs.getSelectedValue();
		ecole.deleteProfesseur(p);
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
}
