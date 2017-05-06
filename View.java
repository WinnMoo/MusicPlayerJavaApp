/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
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
    
    private String[] columnNames = {"Title", "Artist", "Album", "Year"};
    private JTable appTable;
    private String[][] data = { {"Hello", "hi", "bye", "rarh"}
                               };
    
    private JPanel bottomButtonsPanel;
    private JPanel centerPanel;
    private JPanel panel;
    
    private JMenuBar appMenuBar;
    private JMenu appMenu;
    private JMenuItem playExternalSongItem;
    private JMenuItem exitAppItem;
    private JMenuItem addSongItem;
    private JMenuItem deleteSongItem;
    
    private MyJSlider volumeSlider;    
    private boolean songIsPlaying = false;

    private JMenu appControlMenu;
    private JMenuItem playCurrentSong;
    private JMenuItem nextSong;
    private JMenuItem previousSong;
    private JMenuItem hoverCurentSong; // highlight the currently playing song
    private JMenuItem increaseVolume;
    private JMenuItem decreaseVolume;
    
    private boolean isSongPlaying;
    // playButton 'toggles' from playing or pause, so image needs to change based on state of songIsPlaying
    private MyJButton playButton; 
    private MyJButton previousSongButton;
    private MyJButton skipSongButton;
    private MyJButton stopButton;
    
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
        
    /**
     * Construct instance variables and set the width
     * and height constants for the frame.
     */
    public View(Controller appController) {
        frame = new JFrame();
        this.appController = appController;
        
        appMenuBar = new JMenuBar();
        appMenu = new JMenu("Menu");
        playExternalSongItem = appController.new MyJMenuItem("Play Song Not in Library");
        exitAppItem = appController.new MyJMenuItem("Exit Application");
        addSongItem = appController.new MyJMenuItem("Add a Song");
        deleteSongItem = appController.new MyJMenuItem("Delete a Song");
        
        appControlMenu = new JMenu("Controls");
        playCurrentSong = appController.new MyJMenuItem("Play");
        nextSong = appController.new MyJMenuItem("Next");
        previousSong = appController.new MyJMenuItem("Previous");
        hoverCurentSong = appController.new MyJMenuItem("Go to Current Song");
        increaseVolume = appController.new MyJMenuItem("Increase Volume");
        decreaseVolume = appController.new MyJMenuItem("Decrease Volume");
 
        bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel = new JPanel(new BorderLayout());
        panel = new JPanel(new BorderLayout());
        
        songIsPlaying = false;
        
        // instantiate inner class of MyJButton
        playButton =  appController.new MyJButton("Play");
        previousSongButton = appController.new MyJButton("Previous");
        skipSongButton = appController.new MyJButton("Skip");
        stopButton = appController.new MyJButton("Stop");
        
        volumeSlider = appController.new MyJSlider(JSlider.HORIZONTAL, 0, 10, 5);
        
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
        appMenuBar.add(appControlMenu);
       
        appMenu.add(addSongItem);
        appMenu.add(deleteSongItem);
        appMenu.add(playExternalSongItem);
        appMenu.add(exitAppItem);
        
        appControlMenu.add(playCurrentSong);
        appControlMenu.add(nextSong);
        appControlMenu.add(previousSong);
        appControlMenu.add(hoverCurentSong);
        appControlMenu.add(increaseVolume);
        appControlMenu.add(decreaseVolume);
        
        bottomButtonsPanel.add(previousSongButton);
        bottomButtonsPanel.add(playButton);
        bottomButtonsPanel.add(skipSongButton);
        bottomButtonsPanel.add(stopButton);
        bottomButtonsPanel.add(volumeSlider);
        

        playButton.setActionCommand("0");
        previousSongButton.setActionCommand("1");
        skipSongButton.setActionCommand("2");
        stopButton.setActionCommand("3");
        
        addSongItem.setActionCommand("0");
        deleteSongItem.setActionCommand("1");
        playExternalSongItem.setActionCommand("2");
        exitAppItem.setActionCommand("3");
        
        appTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(appTable);
        appTable.setFillsViewportHeight(true);
        
        panel.add(appMenuBar, BorderLayout.NORTH);
        panel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        
        frame.add(panel); 
        frame.setVisible(true);
    }
    
    
    //updating text doesn't work
    /**
     * Updates play button UI as to show the image for pause
     * for the button. Shows the corresponding time
     * for the song on the top panel.
     */
    public void updatePlayButtonUI() {
        if(songIsPlaying){
            playButton.setText("Pause");
        }else{
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
    public void updateStopButtonUI(){

    }
    
    /**
     * Collapse the menu bar displaying the options.
     */
    public void producePopupMenu() {
        
    }
     
}