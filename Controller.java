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
    
    public static class MyJButton extends JButton {
        
        
        public MyJButton(String textToDisplay) {
            this.setText(textToDisplay);
            
        }
        
        class AddInterestListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                int action = Integer.parseInt(Event.getActionCommand());
                switch(action){
                    case 0: appView.playButtonWasPressed();
                            break;
                    case 1: appView.previousButtonWasPressed();
                            break;
                    case 2: appView.skipButtonWasPressed();
                            break;
                    case 3: appView.stopButtonWasPressed();
                            break;
                }
            }
            
            private void createComponents() {
                
            }        
        } 
    }
    
    public static class MyFrame extends JFrame {
        
    }
}
