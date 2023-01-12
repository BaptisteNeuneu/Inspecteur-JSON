package projet.controller;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class RefreshButtonSwitcher implements DocumentListener {

    private JButton buttonToSwitch;
    private JTextComponent textToWatch;

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
