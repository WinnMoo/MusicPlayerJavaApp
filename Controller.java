/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;

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
        appModel = new Model(this); // db is initalized in Model's class constructor
    }

    /**
     * Start the application and display the GUI.
     */
    public void startApp() throws BasicPlayerException {
        appView.displayUI();
        appModel.loadSong(appModel.fileToPlay);
    }

    /**
     *
     */
    public void playSong() throws BasicPlayerException {
        appView.displayUI();
        appView.updatePlayButtonUI();
        appModel.playSong(appModel.fileToPlay);
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
    public void stopSong() throws BasicPlayerException {
        appView.updateStopButtonUI();
        appModel.stopSong();
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
                         {
                            try {
                                playSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
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
                {
                    try {
                        stopSong();
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                }
            }

            private void createComponents() {
                ActionListener listener = new ClickListener();
                addActionListener(listener);
            }
        }
    }

    class MyJMenuItem extends JMenuItem {

        public MyJMenuItem(String textToDisplay) {
            this.setText(textToDisplay);
            MenuListener ml = new MenuListener();
            ml.createComponents();

        }
<<<<<<< HEAD

        class menuListener implements ActionListener {

=======
        class MenuListener implements ActionListener {
            
>>>>>>> origin/master
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

    class MyJSlider extends JSlider {

<<<<<<< HEAD
        public MyJSlider(int orientation, int min, int max, int value) {
            SliderListener SL = new SliderListener();
=======
    }
    
    public class MyDialog extends JDialog {
>>>>>>> origin/master

        }

        class SliderListener implements ChangeListener {

            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
            }

<<<<<<< HEAD
=======
    public MyDialog() {
        setSize(300, 300);
        chooser = new JFileChooser();
        chooser.addActionListener(new ActionListener() {
            
>>>>>>> origin/master
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }

    }

    public class MyFrame extends JFrame {

    }

    public class myDialog extends JDialog {

        File songToBePlayed;
        String filename;
        boolean ok = false;
        JFileChooser chooser;

        public myDialog() {
            setSize(500, 500);
            chooser = new JFileChooser();
            chooser.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        getFile(e);
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            add(chooser);

            setModal(true);
        }

        void getFile(ActionEvent e) throws BasicPlayerException {
            if (e.getActionCommand().equalsIgnoreCase("ApprovableSelection")) {

                filename = chooser.getSelectedFile().getAbsolutePath();
                ok = true;
                songToBePlayed = chooser.getSelectedFile();

                appModel.playSong(songToBePlayed);

            } else {
                ok = false;
                setVisible(false);
            }
        }
    }
<<<<<<< HEAD
=======
    }
>>>>>>> origin/master
}
