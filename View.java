/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import com.mpatric.mp3agic.ID3v1;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.*;
import musicplayerapp.Controller.MyJButton;
import musicplayerapp.Controller.MyJSlider;

/**
 *
 * @author stevengarcia
 */
public class View {

    private JFrame frame;
    private Controller appController;

    private JPanel topMenuPanel;
    private JPanel topButtonsPanel;
    private JPanel bottomMenuPanel;
    private JPanel bottomButtonsPanel;

    private JMenuBar appMenuBar;
    private JMenu appMenu;
    private JMenuItem playExternalSongItem;
    private JMenuItem exitAppItem;
    private JMenuItem addSongItem;
    private JMenuItem deleteSongItem;
    
    private JMenu controlMenu;
    private JMenuItem playItem;
    private JMenuItem nextItem;
    private JMenuItem previousItem;
    private JMenuItem currentSong;
    private JMenuItem separator;
    private JMenuItem increaseVol;
    private JMenuItem decreaseVol;

    private MyJSlider volumeSlider;

    private boolean songIsPlaying = false;
    // playButton 'toggles' from playing or pause, so image needs to change based on state of songIsPlaying
    protected MyJButton playButton;
    private MyJButton previousSongButton;
    private MyJButton skipSongButton;
    private MyJButton stopButton;
    
    private MyJButton volumeUpButton;
    private MyJButton volumeDownButton;

    private JTable table;
    private Object[][] data;
    private JScrollPane scrollPane;
    private int rowsAmount;
    private int currentlySelectedRow;

    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;

    /**
     * Construct instance variables and set the width and height constants for
     * the frame.
     */
    public View(Controller appController) throws SQLException {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        this.appController = appController;

        appMenuBar = new JMenuBar();
        appMenu = new JMenu("Menu");
        addSongItem = appController.new MyJMenuItem("Add a Song to the Library");
        exitAppItem = appController.new MyJMenuItem("Exit Application");
        playExternalSongItem = appController.new MyJMenuItem("Play Song Not in Library");
        deleteSongItem = appController.new MyJMenuItem("Delete a Song from the Library");
        
        controlMenu = new JMenu("Controls");
        playItem = appController.new MyJMenuItem("Play");
        nextItem = appController.new MyJMenuItem("Next");
        previousItem = appController.new MyJMenuItem("Previous");
        currentSong = appController.new MyJMenuItem("Go to Current Song");
        separator = new JMenuItem();
        increaseVol = appController.new MyJMenuItem("Increase Volume");
        decreaseVol = appController.new MyJMenuItem("Decrease Volume");

        topMenuPanel = new JPanel(new BorderLayout());
        topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomMenuPanel = new JPanel(new BorderLayout());
        bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        songIsPlaying = false;

        // instantiate inner class of MyJButton
        playButton = appController.new MyJButton("Play");
        previousSongButton = appController.new MyJButton("Previous");
        skipSongButton = appController.new MyJButton("Skip");
        stopButton = appController.new MyJButton("Stop");
        volumeUpButton = appController.new MyJButton("Increase Vol");
        volumeDownButton = appController.new MyJButton("Decrease Vol");
        

        volumeSlider = appController.new MyJSlider(JSlider.HORIZONTAL, 0, 10, 5);
        
        updateTableView();

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
               int currentSelectedRow = table.getSelectedRow();
               System.out.println("Selected index = " + currentSelectedRow);
            }
        };
        //assign the listener
        table.addMouseListener(mouseListener);
        scrollPane = new JScrollPane(table);

        FRAME_WIDTH = 800;
        FRAME_HEIGHT = 600;
    }

    /**
     *
     */
    public void displayUI() {
        frame.setTitle("Music Player App");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        appMenuBar.add(appMenu);
        appMenuBar.add(controlMenu);

        appMenu.add(addSongItem);
        appMenu.add(deleteSongItem);
        appMenu.add(playExternalSongItem);
        appMenu.add(exitAppItem);
        
        controlMenu.add(playItem);
        controlMenu.add(nextItem);
        controlMenu.add(previousItem);
        controlMenu.add(currentSong);
        controlMenu.add(separator);
        controlMenu.add(increaseVol);
        controlMenu.add(decreaseVol);
        

        bottomButtonsPanel.add(previousSongButton);
        bottomButtonsPanel.add(playButton);
        bottomButtonsPanel.add(skipSongButton);
        bottomButtonsPanel.add(stopButton);
        bottomButtonsPanel.add(volumeUpButton);
        bottomButtonsPanel.add(volumeDownButton);
        
        
        //bottomButtonsPanel.add(volumeSlider);

        playButton.setActionCommand("0");
        previousSongButton.setActionCommand("1");
        skipSongButton.setActionCommand("2");
        stopButton.setActionCommand("3");
        volumeUpButton.setActionCommand("4");
        volumeDownButton.setActionCommand("5");

        addSongItem.setActionCommand("0");
        deleteSongItem.setActionCommand("1");
        playExternalSongItem.setActionCommand("2");
        exitAppItem.setActionCommand("3");
        
        playItem.setActionCommand("4");     
        nextItem.setActionCommand("5");
        previousItem.setActionCommand("6");
        currentSong.setActionCommand("7");
        increaseVol.setActionCommand("8");
        decreaseVol.setActionCommand("9");
        
        KeyStroke spacebar = KeyStroke.getKeyStroke(' ');
        playItem.setAccelerator(spacebar);
        KeyStroke volUp = KeyStroke.getKeyStroke(73, 17);
        increaseVol.setAccelerator(volUp);
        KeyStroke volDown = KeyStroke.getKeyStroke(68, 17);
        decreaseVol.setAccelerator(volDown);
        KeyStroke skip = KeyStroke.getKeyStroke(39, 17);
        nextItem.setAccelerator(skip);
        KeyStroke previous = KeyStroke.getKeyStroke(37, 17);
        previousItem.setAccelerator(previous);

        
        bottomMenuPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        frame.setJMenuBar(appMenuBar);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomMenuPanel);

        frame.setVisible(true);
    }
    
    /**
     * 
     */
    public void updateTableView() throws SQLException {
        String[] columns = {"Title", "Artist", "Album", "Year", "Genre"};
        setCurrentRowCount(); // updates the rowsAmount
        data = new Object[rowsAmount][5];
        // appController.appModel.songsDisplayData.size()
        
        for (int i = 0; i < rowsAmount; i++){
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    //String title = id3v1Tag.getTitle();
                    String title = appController.appModel.appDB.getTitle(i);
                    data[i][j] = title;
                } else if (j == 1) {
                    //String artist = id3v1Tag.getArtist();
                    String artist = appController.appModel.appDB.getArtist(i);
                    data[i][j] = artist;
                } else if (j == 2) {
                    //String album = id3v1Tag.getAlbum();
                    String album = appController.appModel.appDB.getAlbum(i);
                    data[i][j] = album;
                } else if (j == 3) {
                    //String year = id3v1Tag.getYear();
                    String year = appController.appModel.appDB.getYear(i);
                    data[i][j] = year;
                } else if (j == 4) {
                    //int genre = id3v1Tag.getGenre();
                    int genre = appController.appModel.appDB.getGenre(i);
                    data[i][j] = genre;
                }
            }
        }
        
        table = new JTable(data, columns);
    }
    
    /**
     * 
     * @return 
     */
    public JTable getTable() {
        return table;
    }
    
    //updating text doesn't work
    /**
     * Updates play button UI as to show the image for pause for the button.
     * Shows the corresponding time for the song on the top panel.
     */
    public void updatePlayButtonUI() {
        if (songIsPlaying) {
            playButton.setText("Pause");
        } else {
            playButton.setText("Play");
        }
    }

    /**
     *
     */
    public void updatePreviousButtonUI() {

    }

    /**
     *
     */
    public void updateSkipButtonUI() {

    }

    /**
     *
     */
    public void updateStopButtonUI() {

    }

    /**
     * Collapse the menu bar displaying the options.
     */
    public void producePopupMenu() {

    }
    
    /**
     * 
     * @return 
     */
    public int getCurrentRowCount() {
        return rowsAmount;
    }
    
    /**
     * 
     * @throws SQLException 
     */
    public void setCurrentRowCount() throws SQLException {
        rowsAmount = appController.getSongCount();
    }
    
    /**
     * @return currentlySelectedRow, integer
     */
    public int getCurrentlySelectedRow() {
        return currentlySelectedRow;
    }
    
    /**
     * 
     */
    public void updateSelectedSong() {
        currentlySelectedRow = table.getSelectedRow();
    }

}
