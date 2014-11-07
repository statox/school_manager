package graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import personnes.Etudiant;
import personnes.Etudiants;
import personnes.Professeur;
import personnes.ProfesseurTuteur;
import reseau.Client;
import scolarite.Module;
import scolarite.Note;
import scolarite.Notes;
import ecole.Connection;
import ecole.Ecole;

public class ProfesseurPanel extends JPanel {
	private Ecole 		ecole;
	private Professeur 	professeur;
	private ProfesseurTuteur professeurTuteur;
	
	// objets graphiques
		// message d'accueil
	private JLabel lblWelcome;
		// connection
	private JLabel lblId;
	private JTextField 		fieldID;
	
	private JLabel lblPassword;
	private JPasswordField 	fieldPassword;

	private JButton 		btnConnection;
	
		// etudiants
	private JLabel lblEtudiants;
	private JTextArea textAreaEtudiants;
	private JScrollPane scroll;
	
		// notes
	private JTextArea 		textAreaNotes;
	private JLabel lblNotesDesModules;
	
		// notes des etudiants tutores
	private JTextArea textAreaNotesTutores;
	private JLabel 	lblNotesTutores;
		// saisie des notes
	private JLabel lblDE;
	private JTextField fieldNoteDE;

	private JLabel lblCE;
	private JTextField fieldNoteCE;
	
	private JLabel lblTP;
	private JTextField fieldNoteTP;
	
	private JLabel lblIdEtudiant;
	private JList<Etudiant> listeEtudiants;
	private JScrollPane scrollListeEtudiants;

	private JLabel lblIdModule;
	private JList<Module>	listeModules;
	private JScrollPane scrollListeModules;	
	
	private JTextArea textAreaMessage;
	private JButton btnAddNote;
	
			
	/**
	 * Creation du panneau professeur contenant
	 * 		- l'identification
	 * 		- les notes dans les modules enseignés par le professeur
	 * 		- les étudiants inscrits dans les modules enseignés par le professeur
	 * 		- les items de saisie des notes
	 * 		- un message de confirmation pour la saisie des notes
	 * 	
	 * 		- si le professeur est un professeur tuteur: les notes des élèves tutorés
	 */
	public ProfesseurPanel() {
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
		textAreaNotes.setBounds(22, 120, 325, 300);
		textAreaNotes.setVisible(false);
		add(textAreaNotes);
		
		lblNotesDesModules = new JLabel("Notes des modules que vous enseignez");
		lblNotesDesModules.setBounds(22, 95, 325, 14);
		lblNotesDesModules.setVisible(false);
		add(lblNotesDesModules);
		
		// champs relatifs à la saisie des notes
		fieldNoteDE = new JTextField();
		fieldNoteDE.setBounds(422, 400, 70, 20);
		add(fieldNoteDE);
		fieldNoteDE.setColumns(10);
		fieldNoteDE.setVisible(false);
		
		fieldNoteCE = new JTextField();
		fieldNoteCE.setBounds(521, 400, 70, 20);
		add(fieldNoteCE);
		fieldNoteCE.setColumns(10);
		fieldNoteCE.setVisible(false);
		
		fieldNoteTP = new JTextField();
		fieldNoteTP.setColumns(10);
		fieldNoteTP.setBounds(626, 400, 70, 20);
		add(fieldNoteTP);
		fieldNoteTP.setVisible(false);
		
		btnAddNote = new JButton("Envoyer!");
		btnAddNote.setBounds(607, 436, 89, 23);
		add(btnAddNote);
		btnAddNote.setVisible(false);
		
		lblDE = new JLabel("DE:");
		lblDE.setBounds(422, 375, 46, 14);
		add(lblDE);
		lblDE.setVisible(false);
		
		lblCE = new JLabel("CE:");
		lblCE.setBounds(521, 375, 46, 14);
		add(lblCE);
		lblCE.setVisible(false);
		
		lblTP = new JLabel("TP:");
		lblTP.setBounds(626, 375, 46, 14);
		add(lblTP);
		lblTP.setVisible(false);

		lblIdEtudiant = new JLabel("ID Etudiant");
		lblIdEtudiant.setBounds(422, 45, 86, 14);
		add(lblIdEtudiant);
		lblIdEtudiant.setVisible(false);
		
		lblIdModule = new JLabel("ID Module");
		lblIdModule.setBounds(422, 190, 82, 14);
		add(lblIdModule);
		lblIdModule.setVisible(false);

		textAreaMessage = new JTextArea();
		textAreaMessage.setBounds(371, 480, 325, 200);
		add(textAreaMessage);
		textAreaMessage.setVisible(false);
		
		listeEtudiants = new JList();
		listeEtudiants.setBounds(381, 42, 236, 60);
		listeEtudiants.setVisible(false);
		add(listeEtudiants);
		
		scrollListeEtudiants = new JScrollPane(listeEtudiants);
		scrollListeEtudiants.setBounds(422, 68, 274, 111);
		scrollListeEtudiants.setVisible(false);
		add(scrollListeEtudiants);
		
		listeModules = new JList<Module>();
		listeModules.setBounds(381, 124, 236, 53);
		listeModules.setVisible(false);
		add(listeModules);
		
		scrollListeModules = new JScrollPane(listeModules);
		scrollListeModules.setBounds(422, 219, 274, 130);
		scrollListeModules.setVisible(false);
		add(scrollListeModules);
		
		
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
		
		// champs relatifs aux étudiants qui suivent les cours du prof
		textAreaEtudiants = new JTextArea();
		textAreaEtudiants.setBounds(22, 389, 325, 154);
		add(textAreaEtudiants);
		textAreaEtudiants.setVisible(false);
		
		lblEtudiants = new JLabel("Etudiants inscrits dans vos cours");
		lblEtudiants.setBounds(22, 440, 325, 14);
		add(lblEtudiants);
		lblEtudiants.setVisible(false);
		
		scroll = new JScrollPane (textAreaEtudiants, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(22, 479, 325, 200);
		scroll.setVisible(false);
		add(scroll);
		
			// notes des etudiants tutores
		textAreaNotesTutores = new JTextArea();
		textAreaNotesTutores.setBounds(756, 120, 325, 300);
		add(textAreaNotesTutores);
		textAreaNotesTutores.setVisible(false);
		
		lblNotesTutores = new JLabel("Notes des etudiants que vous tutorez");
		lblNotesTutores.setBounds(756, 80, 325, 29);
		add(lblNotesTutores);	
		lblNotesTutores.setVisible(false);
		
		// Boutons intéractifs
		
		// connection du professeur + affichage des notes
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
				professeur = connection.connectionProfesseur(user, passwd);
				// on regarde aussi si le professeur est tuteur
				professeurTuteur = connection.connectionProfesseurTuteur(user, passwd);
				
				// si la connection est réussie: on affiche les notes, les etudiants inscrits et les champs de saisie d'une nouvelle note
				if (professeur != null){
					getNotes();
					getEtudiants();
					
					lblWelcome.setForeground(Color.black);
					lblWelcome.setText("Bienvenue " + professeur.getPrenom() + " " + professeur.getNom());
					
					if (professeurTuteur != null){
						getNotesTutores();
					}
					
					listeEtudiants.setListData(getEtudiants().getEtudiants());
					listeEtudiants.setVisible(true);
					scrollListeEtudiants.setVisible(true);
					
					listeModules.setListData(professeur.getModules());
					listeModules.setVisible(true);
					scrollListeModules.setVisible(true);
					
					lblIdModule.setVisible(true);
					lblIdEtudiant.setVisible(true);
					lblTP.setVisible(true);
					lblCE.setVisible(true);
					lblDE.setVisible(true);
					btnAddNote.setVisible(true);
					fieldNoteTP.setVisible(true);
					fieldNoteCE.setVisible(true);
					fieldNoteDE.setVisible(true);

					textAreaMessage.setVisible(true);
				}else{
					lblWelcome.setForeground(Color.red);
					lblWelcome.setText("Erreur d'authentification!");
				}
				
			}
		});
		
		// envoi d'une nouvelle note
		btnAddNote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// si le professeur a bien été identifié (inutile car les boutons sont cachés s'il n'est pas identifié
				if (professeur!=null){
					int idEtudiant	= 1;
					int idModule	= 1;
					double DE			= 1;
					double CE			= 1;
					double TP			= 1;
					// on récupère les informations
					try{
//						idEtudiant	= Integer.parseInt(fieldIDEtudiant.getText());
//						idModule	= Integer.parseInt(fieldIDModule.getText());
						DE			= Double.parseDouble(fieldNoteDE.getText());
						CE			= Double.parseDouble(fieldNoteCE.getText());
						TP			= Double.parseDouble(fieldNoteTP.getText());
	
					}catch (java.lang.NumberFormatException e){
						System.out.println("La saisie n'est pas valide");
					}
					addNote(idEtudiant, idModule, DE, CE, TP);
				}
			}
		});
		
		
	}
	
	/**
	 * Ajout des notes des modules enseignés par le professeur dans la textArea
	 * 
	 * @return les notes des modules
	 */
	public Notes getNotes(){
		Notes notesProfesseur = new Notes();
		textAreaNotes.setVisible(true);
		textAreaNotes.setText(" ");
		lblNotesDesModules.setVisible(true);

		for(Module m : professeur.getModules()){
			textAreaNotes.append(m.toString() + "\n");
			Notes n = ecole.getNotes().getNotesModule(m);
			if (n != null){
				textAreaNotes.append(n.toString());
				notesProfesseur.getNotes().putAll(n.getNotes());
			}
		}
		return notesProfesseur;
	}
	
	/**
	 * Recupère les notes des élèves tutorés par le professeur et les écrit dans la text area dédiée
	 * 
	 * @return
	 */
	public Notes getNotesTutores(){
		Notes notesTutores = new Notes();
		notesTutores = professeurTuteur.getNotesTutores(ecole);
		textAreaNotesTutores.setVisible(true);
		textAreaNotesTutores.setText(" ");
		lblNotesTutores.setVisible(true);
		
		textAreaNotesTutores.append(notesTutores.toString());
		return notesTutores;
	}
	
	/**
	 *  Ajout des étudiants inscrits dans les modules dans la textArea
	 * 
	 * @return les etudiants inscrits
	 */
	public Etudiants getEtudiants(){
		Etudiants etudiants = new Etudiants();
		lblEtudiants.setVisible(true);
		textAreaEtudiants.setVisible(true);
		scroll.setVisible(true);
		textAreaEtudiants.setText(" ");
		int i = -1;
		for(Module m : professeur.getModules()){
			++i;
			Etudiants etudiantsTMP = m.getEtudiants(ecole.getEtudiants());
			textAreaEtudiants.append("\n********************************************************************\n\t");
			textAreaEtudiants.append(m.getNom().toString().toUpperCase() + "\n");
			textAreaEtudiants.append(etudiantsTMP.toString());
			
			for (Etudiant eTMP: etudiantsTMP.getEtudiants()){
				if (!etudiants.getEtudiants().contains(eTMP)){
					etudiants.addEtudiant(eTMP);
				}
				
			}
		}
		textAreaEtudiants.setCaretPosition(1);
		return etudiants;
	}
	
	/**
	 * ajout d'une note dans les notes de l'école
	 * 
	 * Les paramètres sont saisis dans l'action listener du bouton addNotes
	 * @param idEtudiant
	 * @param idModule		/!\ Pour l'instant ce n'est pas l'id du module dans l'école c'est sont rang dans le vecteur
	 * 						de modules du professeur
	 * @param DE
	 * @param CE
	 * @param TP
	 * @return	booleen indiquant la réussite de l'opération de la saisie de notes
	 */
	public boolean addNote(int idEtudiant, int idModule, double DE, double CE, double TP){
		Module m = null;
		Note n = null;
		boolean result = false;
		Etudiant etudiantSelectionne = null;
		Client client = new Client();
		
		try{
			m = listeModules.getSelectedValue();
			n = new Note(TP, DE, CE);
			etudiantSelectionne = (Etudiant) listeEtudiants.getSelectedValue();
			
			textAreaMessage.append("Vous entrez la note " + n );
			textAreaMessage.append("\nPour l'etudiant: " + etudiantSelectionne.getPrenom() + " " + etudiantSelectionne.getNom());
			textAreaMessage.append("\nDans le module : " + m);
		
			result = ecole.getNotes().addNote(etudiantSelectionne, m, n);
		}catch (java.lang.NullPointerException e){
			textAreaMessage.append("\najout de la note: erreur etudiant inconnu\n");
			return false;
		}catch (java.lang.ArrayIndexOutOfBoundsException e){
			textAreaMessage.append("\najout de la note: erreur module inconnu\n");
			return false;
		}

		if (result){
			textAreaMessage.append("\najout de la note: OK\n");
			client.saveEcoleReseau(ecole);
		}else{
			textAreaMessage.append("\najout de la note: erreur\n");
		}
		return result;
	}
}
