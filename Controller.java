/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author stevengarcia
 */
public class Controller {
    private View appView;
    private Model appModel;
    
    /**
     * Construct the instance variables.
     */
    public Controller() {
        appView = new View(this);
        appModel = new Model(); // db is initalized in Model's class constructor
    }
    
    /**
     * Start the application and display the GUI.
     */
    public void startApp() {
        appView.displayUI();
    }
    
    /**
     * 
     */
    public void playSong() {
        appView.updatePlayButtonUI();
    }
    
    /**
     * 
     */
    public void previousSong() {
        appView.updatePreviousButtonUI();
    }
    
    /**
     * 
     */
    public void skipSong() {
        appView.updateSkipButtonUI();
    }
    
    /**
     * 
     */
    public void stopSong() {
        appView.updateStopButtonUI();
    }
    
    class MyJButton extends JButton {
        
        
        public MyJButton(String textToDisplay) {
            this.setText(textToDisplay);
            
        }
        
        class ClickListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                int action = Integer.parseInt(event.getActionCommand());
                switch(action){
                    case 0: playSong();
                            break;
                    case 1: previousSong();
                            break;
                    case 2: skipSong();
                            break;
                    case 3: stopSong();
                            break;
                }
            }
            
            private void createComponents() {
                ActionListener listener = new ClickListener();
                addActionListener(listener);
            }        
        } 
    }
    
    public class MyFrame extends JFrame {
        
    }
}
