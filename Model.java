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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author stevengarcia
 */
public class Model {

    protected Database appDB;
    private Controller appController;
    private BasicPlayer bp;
    private BasicController bc;
    private double volumeLevel = .5;

    int playSongID; //id for the basicplayer to keep track of which song is playing
    int songID; //id for the database
    
    public Model(Controller appController) throws IOException, UnsupportedTagException, InvalidDataException, SQLException {
        
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
//      appDB.dropTables();
//      appDB.createTables();
    }

    /**
     * Remove song from database and from view based on user right clicking a
     * song on the GUI and selecting 'Delete Song.'
     *
     * @param primaryKey the unique identifier which shall be determined by
     * event of user right clicking the song in the GUI
     */
    public void deleteSong(int primaryKey) throws SQLException {
        appDB.removeSong(primaryKey);
    }

    /**
     *
     */
    public void addSong(Mp3File songToBeAdded, String songFileName, int songID) throws SQLException {
        
        if (songToBeAdded.hasId3v1Tag()) {
            ID3v1 id3v1Tag = songToBeAdded.getId3v1Tag();        
            String title = id3v1Tag.getTitle();
            String artist = id3v1Tag.getArtist();
            String album = id3v1Tag.getAlbum();
            String year = id3v1Tag.getYear();
            int genre = id3v1Tag.getGenre();
            appDB.addSong(songID, title, artist, album, year, genre, songFileName);
            
        } else if (songToBeAdded.hasId3v2Tag()) {
            
            ID3v2 id3v2Tag = songToBeAdded.getId3v2Tag();  
            String title = id3v2Tag.getTitle();
            
            if (title == null) {
                id3v2Tag.setTitle(songFileName);
                title = songFileName;
            }
            String artist = id3v2Tag.getArtist();
            String album = id3v2Tag.getAlbum();
            String year = id3v2Tag.getYear();
            int genre = id3v2Tag.getGenre();
            appDB.addSong(songID, title, artist, album, year, genre, songFileName);
        }
    }

    /**
     *
     * @throws BasicPlayerException
     */
    public void loadSong(File songToBePlayed) throws BasicPlayerException {
        bp.open(songToBePlayed);
    }
    
    /**
     * 
     * @param songID
     * @return
     * @throws SQLException 
     */
    public File getSongFile(int songID) throws SQLException {
        String currentDirectory;
        currentDirectory = System.getProperty("user.dir");
	System.out.println("Current working directory : " + currentDirectory);
        
        String fileName = appDB.getSongFile(songID);
        System.out.println("the filename is " + fileName);
        currentDirectory += "/src/musicplayerapp/" + fileName;
        
        System.out.println("that path is " + currentDirectory);
        File fileToLoad = new File(currentDirectory);
        return fileToLoad;
    }

    /**
     * 
     * @throws BasicPlayerException 
     */
    public void playSong() throws BasicPlayerException {
        if (!appController.isSongPlaying && appController.hasSongStarted) {
            bp.resume();
            appController.isSongPlaying = true;
        }
        
        if (appController.hasSongStarted == false) {
            appController.hasSongStarted = true;
            appController.isSongPlaying = true;
            bp.play();
            System.out.println("Play was called");
        }
    }
    
    /**
     * 
     * @param filename 
     */
    public void playExternalSong(File filename) throws BasicPlayerException {
        if (appController.isSongPlaying) {
            stopSong();
        }
        loadSong(filename);
        playSong();
    }
    
    /**
     * 
     * @throws BasicPlayerException 
     */
    public void pauseSong() throws BasicPlayerException {
        if (appController.isSongPlaying) {
            bp.pause();
            appController.isSongPlaying = false;
        }
    }

    /**
     *
     * @throws BasicPlayerException
     */
    public void stopSong() throws BasicPlayerException {
        bp.stop();
        appController.hasSongStarted = false;
        appController.isSongPlaying = false;
    }

    /**
     *
     */
    public void skipSong(int songID) throws BasicPlayerException, SQLException {
        if(songID == appController.getSongCount()-1){
            songID = 0; // wraps around to the front of the table view
        } else{
            songID++;
        }
        
        bp.stop();
        appController.hasSongStarted = false;
        
        bp.open( getSongFile(songID) );
        playSong();
        appController.hasSongStarted = true;
    }

    /**
     *
     */
    public void previousSong(int songID) throws BasicPlayerException, SQLException {
        if(songID > 0){
            songID--;
        }else if (songID == 0){
            songID = appController.getSongCount()-1;
        }
        bp.stop();
        appController.hasSongStarted = false;
        
        bp.open( getSongFile(songID) );
        playSong();
    }

    //volume adjustment function to be added here
    //Function to adjust volume: bp.setGain(double gain);
    /**
     * 
     * @throws BasicPlayerException 
     */
    public void volumeUp() throws BasicPlayerException {
        volumeLevel = volumeLevel + .1;
        bp.setGain(volumeLevel);
    }

    /**
     * 
     * @throws BasicPlayerException 
     */
    public void volumeDown() throws BasicPlayerException {
        volumeLevel = volumeLevel - .1;
        bp.setGain(volumeLevel);
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public int getSongCount() throws SQLException {
        return appDB.getSongCount();
    }

}
