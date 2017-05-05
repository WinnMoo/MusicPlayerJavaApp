/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.*;
import com.mpatric.mp3agic.*;

/**
 *
 * @author stevengarcia
 */
public class Model {

    private Database appDB;
    private BasicPlayer bp;
    private double volumeLevel = .5;

    File fileToPlay = new File("fur-elise.mp3");
    private boolean startSong = false;
    private boolean isSongPlaying = true;

    public Model() {
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
     * Remove song from database and from view based on user right clicking a
     * song on the GUI and selecting 'Delete Song.'
     *
     * @param primaryKey the unique identifier which shall be determined by
     * event of user right clicking the song in the GUI
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
    public void loadSong(File songToBePlayed) throws BasicPlayerException {
        bp.open(songToBePlayed);
    }

    public void playSong(File songToBePlayed) throws BasicPlayerException {
        if (startSong == false) {
            startSong = true;
            bp.play();
        } else {

            if (!isSongPlaying) {
                bp.resume();
                isSongPlaying = true;

            } else {
                bp.pause();
                isSongPlaying = false;
            }
        }
    }

    /**
     *
     * @throws BasicPlayerException
     */
    public void stopSong() throws BasicPlayerException {
        bp.stop();
        startSong = false;
    }

    /**
     *
     */
    public void skipSong() {
        //Same process for previous
    }

    /**
     *
     */
    public void previousSong() {
        //No function to play previous song
        //Must keep track of current song and the previous song

        //1. Stop current song bp.stop()
        //2. Find previous song file 
        //3. Open song file bp.open(File file)
        //3. Play previous song file bp.play(previousSongFile)
    }

    //volume adjustment function to be added here
    //Function to adjust volume: bp.setGain(double gain);
    public void volumeUp() throws BasicPlayerException {
        volumeLevel = volumeLevel + .1;
        bp.setGain(volumeLevel);
    }

    public void volumeDown() throws BasicPlayerException {
        volumeLevel = volumeLevel - .1;
        bp.setGain(volumeLevel);
    }

}
