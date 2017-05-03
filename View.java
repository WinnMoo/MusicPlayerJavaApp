/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import musicplayerapp.Controller.MyJButton;

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
    
    private boolean songIsPlaying;
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
        playExternalSongItem = new JMenuItem("Play Song Not in Library");
        exitAppItem = new JMenuItem("Exit Application");
        addSongItem = new JMenuItem("Add a Song");
        deleteSongItem = new JMenuItem("Delete a Song");
 
        topMenuPanel = new JPanel(new BorderLayout());
        topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomMenuPanel = new JPanel(new BorderLayout());
        bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        songIsPlaying = false;
        
        // instantiate inner class of MyJButton
        playButton =  appController.new MyJButton("Play");
        previousSongButton = appController.new MyJButton("Previous");
        skipSongButton = appController.new MyJButton("Skip");
        stopButton = appController.new MyJButton("Stop");
        
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
        appMenu.add(playExternalSongItem);
        appMenu.add(exitAppItem);
        appMenu.add(addSongItem);
        appMenu.add(deleteSongItem);
        
        bottomButtonsPanel.add(previousSongButton);
        bottomButtonsPanel.add(playButton);
        bottomButtonsPanel.add(skipSongButton);
        bottomButtonsPanel.add(stopButton);

        playButton.setActionCommand("0");
        previousSongButton.setActionCommand("1");
        skipSongButton.setActionCommand("2");
        stopButton.setActionCommand("3");
        
        bottomMenuPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        frame.add(bottomMenuPanel);
        frame.setJMenuBar(appMenuBar);
        
        frame.setVisible(true);
    }
    
    /**
     * Updates play button UI as to show the image for pause
     * for the button. Shows the corresponding time
     * for the song on the top panel.
     */
    public void updatePlayButtonUI() {
           
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
