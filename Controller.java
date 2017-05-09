/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author stevengarcia
 */
public class Controller {

    protected Model appModel;
    private View appView;

    /**
     * Construct the instance variables.
     */
    public Controller() throws IOException, UnsupportedTagException, InvalidDataException, SQLException {
        appModel = new Model(); // db is initalized in Model's class constructor
        appView = new View(this);
    }

    /**
     * Start the application and display the GUI.
     */
    public void startApp() throws BasicPlayerException {
        appView.displayUI();
        appModel.loadSong(appModel.songFileList.get(0));
    }

    /**
     *
     */
    public void playSong() throws BasicPlayerException {
        appView.updatePlayButtonUI();
        //commented out the following line, seems to interfere with play/pause. Doesn't seem to affect functionality of program
        //appModel.loadSong(appModel.songFileList.get(appModel.playSongID));
        appModel.playSong();
    }

    /**
     *
     */
    public void previousSong() throws BasicPlayerException {
        appView.updatePreviousButtonUI();
        appModel.previousSong();

    }

    /**
     *
     */
    public void skipSong() throws BasicPlayerException {
        appView.updateSkipButtonUI();
        appModel.skipSong();
    }

    /**
     *
     */
    public void stopSong() throws BasicPlayerException {
        appView.updateStopButtonUI();
        appModel.stopSong();
    }
    
    /**
     * 
     */
    public void addSong() {
        //appModel.addSong();
    }
    
    /**
     * 
     */
    public void deleteSong() {
        
    }

    class KeyboardListener implements KeyListener {

        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }
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
                         {
                            try {
                                previousSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Skip button was pressed");
                         {
                            try {
                                skipSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
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

                    case 4:
                        System.out.println("Volume Up Button pressed");
                         {
                            try {
                                appModel.volumeUp();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Volume Down Button pressed");
                         {
                            try {
                                appModel.volumeDown();
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

        class MenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {

                int action = Integer.parseInt(event.getActionCommand());
                switch (action) {
                    //needs work
                    case 0:
                        System.out.println("Adding song not in library");
                        
                        break;
                    //needs work
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
                    case 4:
                        System.out.println("Play button was pressed");
                         {
                            try {
                                playSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Skip button was pressed");
                         {
                            try {
                                skipSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Previous button was pressed");
                        {
                            try {
                                previousSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Go to Current Song");
                        break;
                    case 8:
                        System.out.println("Volume Up Button pressed");
                         {
                            try {
                                appModel.volumeUp();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 9:
                        System.out.println("Volume Down Button pressed");
                         {
                            try {
                                appModel.volumeDown();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                }
            }

            private void createComponents() {
                ActionListener listener = new MenuListener();
                addActionListener(listener);
            }
        }
    }

    //not working yet
    class MyJSlider extends JSlider {

        public MyJSlider(int orientation, int min, int max, int value) {
            SliderListener SL = new SliderListener();

        }

        class SliderListener implements ChangeListener {

            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
            }

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }

    }

    public class MyFrame extends JFrame {

    }

    public class MyDialog extends JDialog {

        File songToBePlayed;
        String filename;
        boolean ok = false;
        JFileChooser chooser;

        public MyDialog() {
            setSize(500, 500);
            chooser = new JFileChooser();
            chooser.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        getFile(e);
                        System.out.println("asdf");
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            add(chooser);

            setModal(true);
        }

        void getFile(ActionEvent e) throws BasicPlayerException {
            if (e.getActionCommand().equalsIgnoreCase("ApproveSelection")) {

                filename = chooser.getSelectedFile().getAbsolutePath();
                System.out.println("filename");
                ok = true;
                songToBePlayed = chooser.getSelectedFile();

                appModel.loadSong(songToBePlayed);
                appModel.playSong();

                setVisible(false);
            } else {
                ok = false;
                setVisible(false);
            }
        }
    }

    //create a new jdialog for adding a song to the library
    public class AddSongDialog extends JDialog {

        File songToAdd;
        String filename;
        boolean ok = false;
        JFileChooser chooser;

        public AddSongDialog() {
            setSize(500, 500);
            chooser = new JFileChooser();
            chooser.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        getFile(e);
                        System.out.println("asdf");
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedTagException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidDataException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            add(chooser);

            setModal(true);
        }

        void getFile(ActionEvent e) throws BasicPlayerException, IOException, UnsupportedTagException, InvalidDataException, SQLException {
            if (e.getActionCommand().equalsIgnoreCase("ApproveSelection")) {

                filename = chooser.getSelectedFile().getAbsolutePath();
                System.out.println("filename");
                ok = true;
                songToAdd = chooser.getSelectedFile();

                appModel.songFileList.add(songToAdd); //adds the song to the playlist
                
                Mp3File songToAddTags = new Mp3File(songToAdd.getName());
                appModel.songID++;
                appModel.addSong(songToAddTags, appModel.songID); //adds the song to the database

                //needs code to update jtable with the new song added
                
                setVisible(false);
            } else {
                ok = false;
                setVisible(false);
            }
        }
    }

    public class MyJTable extends JTable {
        
        public MyJTable(Object[][] data, String[] columns) {
            
            MouseListener mL = new MouseAdapter(){
            JTable table = new JTable(data, columns);
                
            public void mousePressed(MouseEvent e) {
               int CurrentSelectedRow = table.getSelectedRow();
               System.out.println("Selected index = " + CurrentSelectedRow);
            }
        };
            this.addMouseListener(mL);
            
        }
        
        
    }
}
