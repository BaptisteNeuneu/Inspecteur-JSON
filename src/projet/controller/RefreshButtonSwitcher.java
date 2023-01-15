package projet.controller;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * La classe  <code>RefreshButtonSwitcher</code> gère l'activation et désactivation d'un bouton "refresh" 
 * en fonction de si son textComponent est vide ou non
 *  
 * @version 1.1
 */
public class RefreshButtonSwitcher implements DocumentListener {

    private JButton buttonToSwitch;
    private JTextComponent textToWatch;

    /**
    * Constructeur qui définit le bouton à activer/désactiver et le textComponent à surveiller
    * 
    * @param buttonToSwitch le bouton à activer/désactiver
    * @param textToWatch le textComponent à surveiller
    */
    public RefreshButtonSwitcher(JButton buttonToSwitch, JTextComponent textToWatch){
        this.buttonToSwitch = buttonToSwitch;
        this.textToWatch = textToWatch;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        buttonChange();  
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        buttonChange();  
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        buttonChange();
    }

    private void buttonChange(){
        if (this.textToWatch.getText().isEmpty()) {
            buttonToSwitch.setEnabled(false);
        } else {
            buttonToSwitch.setEnabled(true);
        } 
    }
        
    
}
