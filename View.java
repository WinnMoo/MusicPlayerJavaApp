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
    
    private boolean songIsPlaying;
    // playButton 'toggles' from playing or pause, so image needs to change based on state of songIsPlaying
    private MyJButton playButton; 
    private MyJButton previousSongButton;
    private MyJButton skipSongButton;
    private MyJButton stopButton;
    
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
    
//  private BasicPlayer bp;
    
    /**
     * Construct instance variables and set the width
     * and height constants for the frame.
     */
    public View(Controller appController) {
        frame = new JFrame();
        this.appController = appController;
        
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
        
//      bp = new BasicPlayer();
        
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
        
        bottomMenuPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        
        frame.add(bottomMenuPanel);
        
        frame.setVisible(true);
    }
    
    //Might want to add a pause button/function. Pause is different than stop

    //Function to adjust volume: bp.setGain(double gain);

    
    /**
     * 
     */
    public void playButtonWasPressed() {
        // bp.play();
    }
    
    /**
     * 
     */
    public void previousButtonWasPressed() {
        //No function to play previous song
        //Must keep track of current song and the previous song

        //1. Stop current song bp.stop()
        //2. Find previous song file 
        //3. Open song file bp.open(File file)
        //3. Play previous song file bp.play(previousSongFile)
    }
    
    /**
     * 
     */
    public void skipButtonWasPressed() {
        //Same process for previous
    }

    /**
    *
    */
    public void stopButtonWasPressed(){
//      bp.stop();

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
