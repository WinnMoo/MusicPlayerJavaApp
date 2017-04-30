/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import javax.swing.*;

/**
 *
 * @author stevengarcia
 */
public class View {
    private JFrame frame;
    
    
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
    
    /**
     * Construct instance variables and set the width
     * and height constants for the frame.
     */
    public View() {
        frame = new JFrame();
        
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
        
        frame.setVisible(true);
    }
}
