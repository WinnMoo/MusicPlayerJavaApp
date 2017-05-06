/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.*;

/**
 *
 * @author stevengarcia
 */
public class Model {
    private Database appDB;
    private Controller appController;
    private BasicPlayer bp;
    private Mp3File mp3file;
    private ID3v1 id3v1Tag;
    private ID3v2 id3v2Tag;
    
    public Model(Controller appController) {
        this.appController = appController;
        bp = new BasicPlayer();
        
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
     * @throws BasicPlayerException 
     */
    public void playSong() throws BasicPlayerException {
        //The argument for play is a file
        bp.play();
    }
    
    /**
     * 
     * @throws BasicPlayerException 
     */
    public void stopSong() throws BasicPlayerException {
        bp.stop();
    }

    /**
     * 
     */
    public void skipSong(){
        //Same process for previous
    }

    /**
     * 
     */
    public void previousSong(){
        //No function to play previous song
        //Must keep track of current song and the previous song

        //1. Stop current song bp.stop()
        //2. Find previous song file 
        //3. Open song file bp.open(File file)
        //3. Play previous song file bp.play(previousSongFile)
    }

    //volume adjustment function to be added here
    //Function to adjust volume: bp.setGain(double gain);
    
}
