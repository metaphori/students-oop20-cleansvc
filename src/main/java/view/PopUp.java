package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3647943346532131731L;
    
    public void popUpInfo(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void popUpWarning(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Attenzione", JOptionPane.WARNING_MESSAGE);
    }
    
    public void popUpError(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    
    public String popUpInput(String msg) {
        return JOptionPane.showInputDialog(rootPane, msg, "Richiesta dati", JOptionPane.QUESTION_MESSAGE);
    }

}
