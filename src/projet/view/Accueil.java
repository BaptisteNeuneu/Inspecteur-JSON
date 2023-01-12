package projet.view;

import java.awt.*;
import javax.swing.*;

import projet.controller.RefreshButtonSwitcher;
import projet.controller.TestFichier;

public class Accueil extends JFrame{

    public Accueil(){
        /*
         * Parametres fenetre
         */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension (screenSize.width *2/3,screenSize.height * 6/7);
	    this.setSize(frameSize);
        this.setTitle("Interprétateur JSON");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        /*
         * Remplissage avec des elements fixes
         */
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel texteFichier = new JLabel("URL du fichier JSON :");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.weightx = 6.0f;
        constraints.weighty = 1.0f;
        this.add(texteFichier, constraints);

        JTextField zoneURL = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,50,0,0);
        constraints.weightx = 1.0f;
        constraints.weighty = 2.0f;
        this.add(zoneURL, constraints);

        JButton refresh = new JButton(new ImageIcon("res/icons/refresh.png"));
        refresh.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(refresh, constraints);

        JTabbedPane result = new JTabbedPane();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(0,50,50,0);
        constraints.weightx = 6.0f;
        constraints.weighty = 5.0f;
        this.add(result, constraints);

        JButton details = new JButton("Detailler");
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(details, constraints);


        /*
         * Action du bouton refresh
         */
        zoneURL.getDocument().addDocumentListener(new RefreshButtonSwitcher(refresh, zoneURL));
        refresh.addActionListener(new TestFichier(zoneURL));


        setVisible(true);
    }
    
}
