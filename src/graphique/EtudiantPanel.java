package graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import personnes.Etudiant;
import personnes.EtudiantAssistant;
import reseau.Client;
import scolarite.Notes;
import ecole.Connection;
import ecole.Ecole;

public class EtudiantPanel extends JPanel {

	private Ecole 		ecole;
	private Etudiant 	etudiant;
	private EtudiantAssistant etudiantAssistant;
	
	// objets graphiques
		// message d'accueil
	private JLabel lblWelcome;
	private JTextField 		fieldID;
	private JPasswordField 	fieldPassword;
	private JTextArea 		textAreaNotes;
	private JButton 		btnConnection;
	private JLabel lblId;
	private JLabel lblPassword;
	private JLabel lblNotesDesModules;
	/**
	 * Creation du panneau étudiant contenant
	 * 		- l'identification
	 * 		- l'affichage des notes de l'étudiant
	 */
	public EtudiantPanel() {
		this.setLayout(null);
		this.setSize((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
		// recupération des derniere valeurs de l'ecole
		Client client = new Client();
		client.chargerEcoleReseau();
		ecole = client.getEcole();
		
		// champs du message d'accueil
		lblWelcome = new JLabel("Entrez vos identifiants");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblWelcome.setBounds(22, 70, 325, 14);
		add(lblWelcome);
		
		// champs relatifs à l'affichage des notes
		textAreaNotes = new JTextArea();
		textAreaNotes.setBounds(22, 120, 470, 515);
		textAreaNotes.setVisible(false);
		add(textAreaNotes);
		
		lblNotesDesModules = new JLabel("Notes des modules auxquels vous etes inscrits");
		lblNotesDesModules.setBounds(22, 95, 325, 14);
		lblNotesDesModules.setVisible(false);
		add(lblNotesDesModules);
		
		// champs relatifs à la connection
		lblId = new JLabel("ID");
		lblId.setBounds(22, 17, 46, 14);
		add(lblId);
		
		fieldID = new JTextField();
		fieldID.setBounds(22, 42, 86, 20);
		add(fieldID);
		fieldID.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(118, 17, 86, 14);
		add(lblPassword);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(118, 42, 81, 20);
		add(fieldPassword);
		
		btnConnection = new JButton("Connection");
		btnConnection.setBounds(214, 41, 133, 23);
		add(btnConnection);
		
		// Boutons intéractifs
		
		// connection de l'étudiant + affichage des notes
		// lorsque le professeur à entré ses identifiants et qu'il demande la connection
		btnConnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int user		= 1;
				String passwd	= "a";
				// on récupère les identifiants
				try{
					user 		= Integer.parseInt(fieldID.getText());
					passwd 		= fieldPassword.getText();

				}catch (java.lang.NumberFormatException e){
					System.out.println("L'id saisi n'est pas valide");
				}
				// on demande la connection
				Connection connection = new Connection(ecole);
				etudiant = connection.connectionEtudiant(user, passwd);
				
				// si la connection est réussie: on affiche les notes
				if (etudiant != null){
					lblWelcome.setForeground(Color.black);
					lblWelcome.setText("Bienvenue " + etudiant.getPrenom() + " " + etudiant.getNom());
					
					getNotes();
				}else{
					lblWelcome.setForeground(Color.red);
					lblWelcome.setText("Erreur d'authentification!");
				}
				
			}
		});
	}

	/**
	 * Ajoute les notes de l'étudiant dans la textArea
	 * 
	 * @return les notes de l'étudiant
	 */
	public Notes getNotes(){
		Notes notesEtudiant = new Notes();
		textAreaNotes.setVisible(true);
		textAreaNotes.setText(" ");
		lblNotesDesModules.setVisible(true);
	
		notesEtudiant = ecole.getNotes().getNotesEtudiant(etudiant);
		textAreaNotes.append(notesEtudiant.toString());
		
		return notesEtudiant;
	}
}
