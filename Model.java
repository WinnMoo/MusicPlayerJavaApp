/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stevengarcia
 */
public class Model {
    private Database appDB;
    
    public Model() {
        
        try {
            appDB = new Database();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Remove song from database and from view based on user right clicking
     * a song on the GUI and selecting 'Delete Song.'
     * @param primaryKey the unique identifier which shall be determined by event
     * of user right clicking the song in the GUI
     */
    public void deleteSong(int primaryKey) {
        /*
        String query = "DELETE FROM TableName WHERE id = " + primaryKey;
        stat.execute(query);
        */
    }
    
    /**
     * 
     */
    public void addSong() {
        
    }
    
    /**
     * 
     */
    public void playSong() {
        
    }
    
}
