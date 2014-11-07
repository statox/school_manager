package graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import personnes.Personne;
import reseau.Client;
import reseau.Serveur;
import ecole.Connection;
import ecole.Ecole;

import javax.swing.JTabbedPane;

public class AdministrateurPanel extends JPanel {
	private Ecole 		ecole;
	private Personne	admin;
	// objets graphiques
		// message d'accueil
	private JLabel lblWelcome;
	
		// panel permettant les diverses opérations
	private JTabbedPane operationsPanel;
	private JComponent operationsEtudiants;
	
		// connection
	private JLabel 			lblPassword;
	private JPasswordField 	fieldPassword;
	private JButton 		btnConnection;
	
		// operations serveur
	private JButton 	btnCloseServeur;
	private JButton 	btnStartServeur;
	
	/**
	 * 		Creation du panneau administrateur
	 * 
	 */
	public AdministrateurPanel() {
		this.setLayout(null);
		this.setSize((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		// recupération des derniere valeurs de l'ecole
		Client client = new Client();
		client.chargerEcoleReseau();
		ecole = client.getEcole();
		
		// panel permettant les diverses opérations
		operationsPanel = new JTabbedPane(JTabbedPane.TOP);
		operationsPanel.setBounds(42, 134, 1263, 533);
		operationsPanel.setVisible(false);
		add(operationsPanel);
		
		operationsPanel.addTab("Etudiants", new AdministrateurOperationsEtudiantsPanel(ecole));
		operationsPanel.addTab("Professeurs", new AdministrateurOperationsProfesseursPanel(ecole));
		operationsPanel.addTab("Scolarite", new AdministrateurOperationsScolaritePanel(ecole));
		
		// champs du message d'accueil
		lblWelcome = new JLabel("Entrez vos identifiants");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblWelcome.setBounds(22, 70, 325, 14);
		add(lblWelcome);
		
		
		// champs relatifs à la connection
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(118, 17, 86, 14);
		add(lblPassword);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(118, 42, 81, 20);
		add(fieldPassword);
		
		btnConnection = new JButton("Connection");
		btnConnection.setBounds(214, 41, 133, 23);
		add(btnConnection);
		
		// operations serveur
		btnCloseServeur = new JButton("Fermer le serveur");
		btnCloseServeur.setBounds(221, 95, 178, 23);
		btnCloseServeur.setVisible(false);
		add(btnCloseServeur);
		
		btnStartServeur = new JButton("Lancer le serveur");
		btnStartServeur.setBounds(32, 95, 178, 23);
		btnStartServeur.setVisible(false);
		add(btnStartServeur);
		
		// Boutons intéractifs

		// connection du professeur + affichage des notes
		// lorsque le professeur à entré ses identifiants et qu'il demande la connection
		btnConnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String passwd	= "a";
				// on récupère les identifiants
				try{
					passwd 		= fieldPassword.getText();

				}catch (java.lang.NumberFormatException e){
					System.out.println("L'id saisi n'est pas valide");
				}
				// on demande la connection
				Connection connection = new Connection(ecole);
				admin = connection.connectionAdmin(passwd);
				
				// si la connection est réussie: on affiche les notes, les etudiants inscrits et les champs de saisie d'une nouvelle note
				if (admin != null){
					lblWelcome.setForeground(Color.black);
					lblWelcome.setText("Bienvenue administrateur");
		
					operationsPanel.setVisible(true);
					
					btnCloseServeur.setVisible(true);
					btnStartServeur.setVisible(true);
				}else{
					lblWelcome.setForeground(Color.red);
					lblWelcome.setText("Erreur d'authentification!");
				}
				
			}
		});
		
		// bouton de fermeture du serveur
		btnCloseServeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeServeur();
			}
		});
		
		// bouton de lancement du serveur du serveur
		btnStartServeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startServeur();
			}
		});

	}
	
	/**
	 * Cette méthode appelle la fermeture du serveur:
	 * 		serialisation puis fin de l'attente de connections des clients
	 * 
	 */
	public void closeServeur(){
		Client client = new Client();
		client.fermerServeur();
	}
	/**
	 * Cette méthode lance un serveur.
	 * Elle est donc complètement inutile puisque
	 * 		- elle lance le serveur sur le poste local
	 * 		- il n'est pas possible de se connecter à l'application et à l'AdministrateurPanel sans avoir déjà un serveur lancé
	 * 
	 */
	public void startServeur(){
		Serveur serveur = new Serveur();
		serveur.main(null);
	}
}
