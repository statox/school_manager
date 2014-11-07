package graphique;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AccueilPanel extends JPanel {

	/**
	 * Création du panneau d'acceuil contenant le texte de bienvenur
	 */
	public AccueilPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblBienvenueSurEcole = new JLabel("Bienvenue sur Ecole 3000");
		lblBienvenueSurEcole.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBienvenueSurEcole, BorderLayout.CENTER);

	}

}
