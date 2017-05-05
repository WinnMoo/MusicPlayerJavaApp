/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

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
            ClickListener cl = new ClickListener();
            cl.createComponents();
        }

        class ClickListener implements ActionListener {
            
            public void actionPerformed(ActionEvent event) {
                
                int action = Integer.parseInt(event.getActionCommand());
                switch (action) {
                    case 0:
                        System.out.println("Play button was pressed");
                        playSong();
                        break;
                    case 1:
                        System.out.println("Previous button was pressed");
                        previousSong();
                        break;
                    case 2:
                        System.out.println("Skip button was pressed");
                        skipSong();
                        break;
                    case 3:
                        System.out.println("Stop button was pressed");
                        stopSong();
                        break;
                }
            }

            private void createComponents() {
                ActionListener listener = new ClickListener();
                addActionListener(listener);
            }
        }
    }
    
    class MyJMenuItem extends JMenuItem{
        public MyJMenuItem(String textToDisplay){
            this.setText(textToDisplay);
            MenuListener ml = new MenuListener();
            ml.createComponents();
            
        }
        class MenuListener implements ActionListener {
            
            public void actionPerformed(ActionEvent event) {
                
                int action = Integer.parseInt(event.getActionCommand());
                switch (action) {
                    case 0:
                        System.out.println("Adding song not in library");
                        
                        break;
                    case 1:
                        System.out.println("Deleting song from library");
                        
                        break;
                    case 2:
                        System.out.println("Playing song not in library");
                        MyDialog md = new MyDialog();
                        md.setVisible(true);
                        if (md.ok) {
                            System.out.println("Filename is " + md.filename);
                        } else {
                            System.out.println("user clicked cancel");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting application");
                        System.exit(0);
                        break;
                    
                }
            }

            private void createComponents() {
                ActionListener listener = new MenuListener();
                addActionListener(listener);
            }
        }
    }

    public class MyFrame extends JFrame {

    }
    
    public class MyDialog extends JDialog {

    String filename;
    boolean ok = false;
    JFileChooser chooser;

    public MyDialog() {
        setSize(300, 300);
        chooser = new JFileChooser();
        chooser.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                getFile(e);
            }
        });

        add(chooser);

        setModal(true);
    }

    void getFile(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("ApprovableSelection")) {
            filename = chooser.getSelectedFile().getAbsolutePath();
            ok = true;
        } else {
            ok = false;
            setVisible(false);
        }
    }
    }
}
