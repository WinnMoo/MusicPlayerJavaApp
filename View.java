/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.awt.BorderLayout;
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
        
        topMenuPanel = new JPanel(new BorderLayout());
        topButtonsPanel = new JPanel();
        bottomMenuPanel = new JPanel(new BorderLayout());
        bottomButtonsPanel = new JPanel();
        
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
        
        bottomButtonsPanel.add(previousSongButton);
        bottomButtonsPanel.add(playButton);
        bottomButtonsPanel.add(skipSongButton);
        bottomButtonsPanel.add(stopButton);

        playButton.setActionCommand("0");
        previousSongButton.setActionCommand("1");
        skipSongButton.setActionCommand("2");
        stopButton.setActionCommand("3");

        playButton.addActionListener(AddInterestListener);
        previousSongButton.addActionListener(AddInterestListener);
        skipSongButton.addActionListener(AddInterestListener);
        stopButton.addActionListener(AddInterestListener);
        
        bottomMenuPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        
        frame.add(bottomMenuPanel);
        
        frame.setVisible(true);
    }
    
    /**
     * 
     */
    public void playButtonWasPressed() {

    }
    
    /**
     * 
     */
    public void previousButtonWasPressed() {
        
    }
    
    /**
     * 
     */
    public void skipButtonWasPressed() {
        
    }

    /**
    *
    */
    public void stopButtonWasPressed(){
        
    }
    
    /**
     * Collapse the menu bar displaying the options.
     */
    public void menuBarWasPressed() {
        
    }
    
    /**
     * 
     */
    public void producePopupMenu() {
        
    }
}
