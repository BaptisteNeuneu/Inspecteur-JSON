package projet.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

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

        //TODO: boutons qui se font la malle quand le texte spawn

        JLabel texteFichier = new JLabel("URL du fichier JSON :");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.weightx = 1.0f;
        constraints.weighty = 0.05f;
        this.add(texteFichier, constraints);

        JTextField zoneURL = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,50,0,0);
        constraints.weightx = 0.80f;
        constraints.weighty = 0.05f;
        this.add(zoneURL, constraints);

        JButton refresh = new JButton(new ImageIcon("res/icons/refresh.png"));
        refresh.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0,20,0,20);
        constraints.weightx = 0.0f;
        constraints.weighty = 0.05f;
        this.add(refresh, constraints);

        JTabbedPane result = new JTabbedPane();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(0,50,50,0);
        constraints.weightx = 0.80f;
        constraints.weighty = 0.90f;
        this.add(result, constraints);

        JButton details = new JButton("Detailler");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0,20,50,20);
        constraints.weightx = 0.0f;
        constraints.weighty = 0.90f;
        this.add(details, constraints);


        /*
         * Mise en place du JTabbedPan
         */
        Container textPaneJSON = new Container();
        textPaneJSON.setLayout(new FlowLayout(3,0,0));
        result.addTab("JSON",textPaneJSON);
        result.addTab("PHP",new JTextPane());

        /*
         * Action du bouton refresh
         */
        zoneURL.getDocument().addDocumentListener(new RefreshButtonSwitcher(refresh, zoneURL));
        refresh.addActionListener(new TestFichier(zoneURL, result));


        setVisible(true);
    }
    
}
