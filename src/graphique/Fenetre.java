package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuListener;

import personnes.Professeur;
import reseau.Client;
import ecole.Ecole;

public class Fenetre extends JFrame {

	private JMenuBar menu = null;

	  private JButton professeurs 		= null;
	  private JButton etudiants 		= null;
	  private JButton administrateurs	= null;

	  private Dimension size;	
	  	  
	/**
	 * 	Constructeur de la fenetre
	 * 
	 * 	Cr�e la barre de menu dont les boutons lanceront les diff�rents sc�nari professeur, etudiant, professeur tuteur et admin
	 *   
	 */
	public Fenetre(){
		System.out.println("creation fenetre");		
		
		// param�tres de la fenetre		
		this.setTitle("Ecole3000");
		this.setSize((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    
//	    this.size = new Dimension(this.getWidth(), this.getHeight());
	    this.size = new Dimension((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	    
	    // ajout du menu � la fenetre
	    menu = new JMenuBar();

	    // ajout des boutons � la barre de menu
	    professeurs = new JButton("Professeur");
	    professeurs.addActionListener(
    		new ActionListener()
    		{
				public void actionPerformed(ActionEvent arg0){
					afficherEcranProfesseur();
				}	    	
    		}
	    );
	    menu.add(professeurs);
	    
	    etudiants = new JButton("Etudiant");
	    etudiants.addActionListener(
	    		new ActionListener()
	    		{
					public void actionPerformed(ActionEvent arg0){
						afficherEcranEtudiant();
					}	    	
	    		}
		    );
	    menu.add(etudiants);
	    
	    
	    administrateurs = new JButton("Administrateur");
	    administrateurs.addActionListener(
	    		new ActionListener()
	    		{
					public void actionPerformed(ActionEvent arg0){
						afficherEcranAdministrateur();
					}	    	
	    		}
		    );
	    menu.add(administrateurs);
	    

	    // on met le panneau de connection et le menu dans la fenetre
	    this.setContentPane(new AccueilPanel());
	    this.setJMenuBar(menu);

	    
	}
	
	/**
	 * Ajoute le panneau professeur dans la fen�tre quand on a cliqu� sur le bouton professeur
	 */
	public void afficherEcranProfesseur(){
		this.setContentPane(new ProfesseurPanel());
		this.revalidate();
	}
	/**
	 * Ajoute le panneau etudiant dans la fen�tre quand on a cliqu� sur le bouton etudiant
	 */
	public void afficherEcranEtudiant(){
		this.setContentPane(new EtudiantPanel());
		this.revalidate();
	}
	/**
	 * Ajoute le panneau professeur tuteur dans la fen�tre quand on a cliqu� sur le bouton professeur tuteur
	 */
	public void afficherEcranAdministrateur(){
		this.setContentPane(new AdministrateurPanel());
		this.revalidate();
	}
}


