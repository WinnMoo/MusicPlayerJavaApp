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
    
    public boolean hasSongStarted;
    public boolean isSongPlaying;
    public int currentRowPlaying;
            
    /**
     * Construct the instance variables.
     */
    public Controller() throws IOException, UnsupportedTagException, InvalidDataException, SQLException {
        appModel = new Model(this); // db is initalized in Model's class constructor
        appView = new View(this);
        
        hasSongStarted = false;
        isSongPlaying = false;
    }

    /**
     * Start the application and display the GUI.
     */
    public void startApp() throws BasicPlayerException {
        appView.displayUI();
    }
    
    /**
     *
     */
    public void playSong(int songID) throws BasicPlayerException, SQLException {
        if (!hasSongStarted) {
            appModel.loadSong(appModel.getSongFile(songID));
        } else if (currentRowPlaying != songID) {
            // current song is already playing that's different from currently selected song
            appModel.stopSong();
            appModel.loadSong(appModel.getSongFile(songID));
        }
        currentRowPlaying = songID;
        appModel.playSong();
        //commented out the following line, seems to interfere with play/pause. Doesn't seem to affect functionality of program
        //appModel.loadSong(appModel.songFileList.get(appModel.playSongID));
        isSongPlaying = true;
        appView.updatePlayButtonUI();
    }
    
    /**
     * 
     * @param filename
     */
    public void playExternalSong(File filename) throws BasicPlayerException {
        appModel.playExternalSong(filename);
    }
    
    public void pauseSong() throws BasicPlayerException {
        appModel.pauseSong();
        isSongPlaying = false;
        appView.updatePlayButtonUI();
    }
    
    /**
     *
     */
    public void previousSong(int songID) throws BasicPlayerException, SQLException {      
        appView.updatePreviousButtonUI();
        appModel.previousSong(songID);

    }
    
    /**
     *
     */
    public void skipSong(int songID) throws BasicPlayerException, SQLException {
        appView.updateSkipButtonUI();
        appModel.skipSong(songID);
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
     * @return
     * @throws SQLException 
     */
    public int getSongCount() throws SQLException {
        return appModel.getSongCount();
    }
    
    /**
     * 
     */
    public void addSong(Mp3File songFile, String fileName) throws SQLException {
        appModel.addSong(songFile, fileName, appView.getCurrentRowCount());
        System.out.println("In db song count is now " + getSongCount());
        int rowID = appView.getCurrentRowCount();
        
        Object[] dataRow = new Object[5];
        for (int i = 0; i < dataRow.length; i++) {
            switch(i) {
                case 0:
                    dataRow[i] = appModel.appDB.getTitle(rowID);
                    break;
                case 1:
                    dataRow[i] = appModel.appDB.getArtist(rowID);
                    break;
                case 2:
                    dataRow[i] = appModel.appDB.getAlbum(rowID);
                    break;
                case 3:
                    dataRow[i] = appModel.appDB.getYear(rowID);
                    break;
                case 4: 
                    dataRow[i] = appModel.appDB.getGenre(rowID);
                    break;
            }
        }
        appView.tableModel.addRow(dataRow);
    }   
    
    /**
     * 
     */
    public void deleteSong(int songID) throws SQLException {
        appModel.deleteSong( songID );
        appView.tableModel.removeRow(songID);
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

                        System.out.println("Play/Pause button was pressed");
                        
                        if (!isSongPlaying) {
                         
                            try {
                                appView.updateSelectedSong(); 
                                currentRowPlaying = appView.getCurrentlySelectedRow();
                                playSong(currentRowPlaying);
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        } 
                        else {
                            try {
                                pauseSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        break;
                    case 1:
                        System.out.println("Previous button was pressed");
                         {
                            try {
                                
                                previousSong( currentRowPlaying );
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Skip button was pressed");
                         {
                            try {
                                skipSong( currentRowPlaying );
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
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
                    case 0:
                        System.out.println("Adding song not in library");
                       
                        MyDialog addSongDialog = new MyDialog();
                        addSongDialog.setVisible(true);
                        if (addSongDialog.ok) {
                            System.out.println("Filename is " + addSongDialog.filename + "!!");
                        } else {
                            System.out.println("user clicked cancel");
                        }
                        
                        String[] fileToAdd = addSongDialog.filename.split("/");
                        String lastStrFileName = fileToAdd[fileToAdd.length-1];
                        
                        try {
                            Mp3File mp3fileToAdd = new Mp3File(lastStrFileName);    
                            System.out.println(lastStrFileName);
                            addSong(mp3fileToAdd, lastStrFileName);
                            System.out.println("SUCCESS *****************");
  
                        } catch (IOException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedTagException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidDataException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) { 
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    case 1:
                        System.out.println("Deleting song from library");
                        appView.updateSelectedSong();
                        int selectedRow = appView.getCurrentlySelectedRow();
                        
                        try {
                            deleteSong(selectedRow);
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                        break;
                    case 2:
                        System.out.println("Playing song not in library");
                        MyDialog md = new MyDialog();
                        md.setVisible(true);
                        if (md.ok) {
                            System.out.println("Filename is " + md.filename);
                            
                            //String currentDirectory = System.getProperty("user.dir");
        
                            //String[] fileName = md.filename.split("/");
                            //currentDirectory += "/src/musicplayerapp/" + fileName[fileName.length-1];
                            
                           // System.out.println("**** THAT PATH IS " + currentDirectory);
                            //File fileToLoad = new File(currentDirectory);
                            
                            //try {
                              //  appModel.playExternalSong(fileToLoad);
                                //System.out.println("**** THAT PATH IS " + currentDirectory);
                            //} catch (BasicPlayerException ex) {
                              //  Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            //}
                            
                        } else {
                            System.out.println("user clicked cancel");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting application");
                        System.exit(0);
                        break;
                    case 4:

                        System.out.println("Play/Pause button was pressed");
                        
                        if (!isSongPlaying) {
                         
                            try {
                                appView.updateSelectedSong(); 
                                playSong(appView.getCurrentlySelectedRow());
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        } 
                        else {
                            try {
                                pauseSong();
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        break;
                    case 5:
                        System.out.println("Skip button was pressed");
                         {
                            try {
                                skipSong( currentRowPlaying );
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Previous button was pressed");
                        {
                            try {
                                previousSong( currentRowPlaying );
                            } catch (BasicPlayerException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Go to Current Song");
                        appView.table.changeSelection(appModel.playSongID, 5, true, true);
                        
                        
          
                        
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
                
                //appModel.songFileList.add(songToAdd); //adds the song to the playlist
                
                Mp3File songToAddTags = new Mp3File(songToAdd.getName());
                //appModel.songID++;
                //appModel.addSong(songToAddTags, appModel.songID); //adds the song to the database
                
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
