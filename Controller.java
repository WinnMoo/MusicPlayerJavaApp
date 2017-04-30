/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

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
        appView = new View();
        appModel = new Model(); // db is initalized in Model's class constructor
    }
    
    /**
     * Start the application and display the GUI.
     */
    public void startApp() {
        appView.displayUI();
    }
}
