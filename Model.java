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
    private BasicPlayer bp;
    private BasicController bc;
    private double volumeLevel = .5;

    int playSongID; //id for the basicplayer to keep track of which song is playing
    int songID; //id for the database
    
    ArrayList<Mp3File> songsDisplayData = new ArrayList<Mp3File>();
    ArrayList<File> songFileList = new ArrayList<File>();
    private boolean startSong = false;
    private boolean isSongPlaying = true;

    public Model() throws IOException, UnsupportedTagException, InvalidDataException, SQLException {
        
        playSongID = 0;
        songID = 0;
        
//        this.song1 = new Mp3File("fur-elise.mp3");
//        this.song2 = new Mp3File("mpthreetest.mp3");
//        
//        songsDisplayData.add(song1);
//        songsDisplayData.add(song2);
//        
//        this.songFile1 = new File("fur-elise.mp3");
//        this.songFile2 = new File("mpthreetest.mp3");

        getSongDataToDisplay();

//        songFileList.add(songFile1);
//        songFileList.add(songFile2);

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
        //appDB.createTables();
        //populateDatabase();
    }

    /**
     * Function to populate the database. Only called once in the beginning. Any
     * song that needs to be added will use the addSong function
     *
     * @throws SQLException
     */
    public void populateDatabase() throws SQLException {
        for (int i = 0; i < songsDisplayData.size(); i++) {
            songID++;
            if (songsDisplayData.get(i).hasId3v1Tag()) {
                addSong(songsDisplayData.get(i), songID);       
            }
        }
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
    public void addSong(Mp3File songToBeAdded, int songID) throws SQLException {
        ID3v1 id3v1Tag = songToBeAdded.getId3v1Tag();
        int idNum = songID;
        String title = id3v1Tag.getTitle();
        String artist = id3v1Tag.getArtist();
        String album = id3v1Tag.getAlbum();
        String year = id3v1Tag.getYear();
        int genre = id3v1Tag.getGenre();
        appDB.addSong(idNum, title, artist, album, year, genre);

    }

    /**
     *
     * @throws BasicPlayerException
     */
    public void loadSong(File songToBePlayed) throws BasicPlayerException {
        bp.open(songToBePlayed);
    }

    public void playSong() throws BasicPlayerException {
        if (startSong == false) {
            startSong = true;
            bp.play();
            System.out.println("Play was called");
        } else {
            if (!isSongPlaying) {
                System.out.println("Resuming Playback");
                bp.resume();
                System.out.println("Playback resumed");
                isSongPlaying = true;

            } else {
                System.out.println("Pausing Playback");
                bp.pause();
                System.out.println("Playback Paused");
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
    public void skipSong() throws BasicPlayerException {
        if(playSongID == (songFileList.size() - 1)){
            playSongID = 0;
        } else{
            playSongID++;
        }
        
        bp.stop();
        startSong = false;
        
        System.out.println(songFileList.get(playSongID));
        bp.open(songFileList.get(playSongID));
        playSong();
        startSong = true;
    }

    /**
     *
     */
    public void previousSong() throws BasicPlayerException {
        if(playSongID > 0){
        playSongID--;
        }else if(playSongID == 0){
            playSongID = songFileList.size() - 1;
        }
        bp.stop();
        startSong = false;
        
        System.out.println(songFileList.get(playSongID));
        bp.open(songFileList.get(playSongID));
        playSong();
        startSong = true;
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

    /**
     * 
     */
    private void getSongDataToDisplay() {
    
    }

}
