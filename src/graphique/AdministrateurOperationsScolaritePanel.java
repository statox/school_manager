package graphique;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import reseau.Client;
import scolarite.Module;
import scolarite.Specialite;
import ecole.Ecole;

public class AdministrateurOperationsScolaritePanel extends JPanel {
	private Ecole ecole;
	
	// objets graphiques
		
	private JTextField textFieldNomSpecialite;
	private JTextField textFieldNomModule;
	private JList listSpecialiteASupprimer;
	private JList listAnneeModule;
	private JList listModules;
	private JList listSpecialites;
	/**
	 * Create the panel.
	 */
	public AdministrateurOperationsScolaritePanel(Ecole e) {
		setLayout(null);
		ecole = e;
		
		JLabel lblAjouterUneSpecialite = new JLabel("Ajouter une sp\u00E9cialit\u00E9");
		lblAjouterUneSpecialite.setBounds(39, 28, 145, 14);
		add(lblAjouterUneSpecialite);
		
		JLabel lblSupprimerUneSpcialit = new JLabel("Supprimer une sp\u00E9cialit\u00E9");
		lblSupprimerUneSpcialit.setBounds(267, 28, 145, 14);
		add(lblSupprimerUneSpcialit);
		
		JLabel lblAjouterUnModule = new JLabel("Ajouter un module");
		lblAjouterUnModule.setBounds(459, 28, 145, 14);
		add(lblAjouterUnModule);
		
		JLabel lblSupprimerUnModule = new JLabel("Supprimer un module");
		lblSupprimerUnModule.setBounds(627, 28, 145, 14);
		add(lblSupprimerUnModule);
		
		listSpecialiteASupprimer = new JList();
		listSpecialiteASupprimer.setBounds(267, 126, 134, 284);
		add(listSpecialiteASupprimer);
		
		JLabel lblNomSpecialite = new JLabel("Nom de la sp\u00E9calit\u00E9:");
		lblNomSpecialite.setBounds(10, 53, 134, 14);
		add(lblNomSpecialite);
		
		textFieldNomSpecialite = new JTextField();
		textFieldNomSpecialite.setBounds(10, 74, 174, 20);
		add(textFieldNomSpecialite);
		textFieldNomSpecialite.setColumns(10);
		
		JLabel lblNomModule = new JLabel("Nom du module:");
		lblNomModule.setBounds(459, 53, 101, 14);
		add(lblNomModule);
		
		textFieldNomModule = new JTextField();
		textFieldNomModule.setBounds(459, 78, 111, 20);
		add(textFieldNomModule);
		textFieldNomModule.setColumns(10);
		
		JLabel lblAnneeModule = new JLabel("Annee du module");
		lblAnneeModule.setBounds(459, 127, 111, 14);
		add(lblAnneeModule);
		
		listAnneeModule = new JList();
		listAnneeModule.setBounds(459, 152, 111, 101);
		add(listAnneeModule);
		
		listModules = new JList();
		listModules.setBounds(627, 126, 174, 284);
		add(listModules);
		
		listSpecialites = new JList();
		listSpecialites.setBounds(452, 302, 134, 108);
		add(listSpecialites);
		
		JLabel lblSpcialitDuModule = new JLabel("Sp\u00E9cialit\u00E9 du module");
		lblSpcialitDuModule.setBounds(459, 277, 122, 14);
		add(lblSpcialitDuModule);
		
		JButton btnAjouterSpecialite = new JButton("Ajouter specialite");
		btnAjouterSpecialite.setBounds(10, 435, 174, 23);
		add(btnAjouterSpecialite);
		
		JButton btnSupprimerSpecialite = new JButton("Supprimer specialite");
		btnSupprimerSpecialite.setBounds(267, 435, 134, 23);
		add(btnSupprimerSpecialite);
		
		JButton btnAjouterModule = new JButton("Ajouter module");
		btnAjouterModule.setBounds(452, 435, 134, 23);
		add(btnAjouterModule);
		
		JButton btnSupprimerModule = new JButton("Supprimer module");
		btnSupprimerModule.setBounds(627, 435, 174, 23);
		add(btnSupprimerModule);
		
		initialisationChamps();

		// objets interactifs
		
		btnSupprimerModule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				deleteModule();
			}
		});
		
		btnAjouterModule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addModule();
			}
		});
		
		btnSupprimerSpecialite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				deleteSpecialite();
			}
		});
		
		btnAjouterSpecialite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addSpecialite();
			}
		});
	
	}
	
	public void initialisationChamps(){
		listModules.setListData(ecole.getSpecialites().getModules());
		listSpecialiteASupprimer.setListData(ecole.getSpecialites().getSpecialites());
		listSpecialites.setListData(ecole.getSpecialites().getSpecialites());
		listAnneeModule.setListData(new Object[] {1, 2, 3, 4, 5});
	}
	
	public void deleteModule(){
		// TODO : supprimer les notes correspondant aux modules
		
		Module m = (Module) listModules.getSelectedValue();
		ecole.deleteModule(m);
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	public void addModule(){
		Module m = new Module((int) listAnneeModule.getSelectedValue(), textFieldNomModule.getText()) ;
		Specialite s = (Specialite) listSpecialites.getSelectedValue();
		
		ecole.addModule(s, m);
		System.out.println("ajout du module " + m + "\n" + s);
		System.out.println(ecole.getSpecialites().getModules());
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	public void deleteSpecialite(){
		Specialite s = (Specialite) listSpecialiteASupprimer.getSelectedValue();
		System.out.println("Specialite a suppr:\n" + s);
		ecole.deleteSpecialite(s);
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	public void addSpecialite(){
		String nom 			= textFieldNomSpecialite.getText();
		
		ecole.addSpecialite( new Specialite(nom, new Vector<Module>()));
		
		// envoi des mises à jour au serveur
		Client client = new Client();
		client.saveEcoleReseau(ecole);
		
		initialisationChamps();
	}
	
	
	

}
