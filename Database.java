/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ***************************************************
 * This code only initializes a Database instance and * connects it to the
 * server. No tables are defined * or any other operations provided. *
 * ***************************************************
 */
public class Database {

    Connection conn; //once a connection is established it stays
    //as long as the code that created this
    //instance does not exit

    Statement stat;  //stat can be reused in every operation
    String query;

    public Database() throws IOException, ClassNotFoundException,
            SQLException, Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Derby driver not found.");
        }
        //In the string to getConnection you may replace "MP3Player"      
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost/MP3Player;create=true;user=APP;pass=APP");
            stat = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is to run only once to create the tables for the database.
     */
    public void createTables() throws SQLException {
        String createString =
        "CREATE TABLE songInfo("
                + "SONGID VARCHAR(30),"
                + "TITLE VARCHAR(30),"
                + "ARTIST VARCHAR(30),"
                + "ALBUM VARCHAR(30),"
                + "SONGYEAR VARCHAR(4),"
                + "GENRE VARCHAR(3)"
                + ")";
        PreparedStatement pstmt = conn.prepareStatement(createString);
        pstmt.close();
        
    }

    /**
     *
     * @param title
     * @param artist
     * @param album
     * @param year
     * @param genre
     * @throws java.sql.SQLException
     */
    public void addSong(int idNum, String title, String artist, String album, String year, int genre) throws SQLException {
        String SQL = "INSERT INTO songInfo VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setInt(1, idNum);
        pstmt.setString(2, title);
        pstmt.setString(3, artist);
        pstmt.setString(4, album);
        pstmt.setString(5, year);
        pstmt.setInt(6, genre);
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     *
     * @param songName
     * @throws java.sql.SQLException
     */
    public void removeSong(int id) throws SQLException {
        String SQL = "DELETE FROM SongInfo WHERE SongId = (?)";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    public String getTitle(int id) throws SQLException {
        String title = "asdf";
        String getTitle = "select * from songInfo where SONGID = ?";
        PreparedStatement statement = conn.prepareStatement(getTitle);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            title = resultSet.getString(2);
        }
        return title;
    }

    public String getArtist(int id) throws SQLException {
        String artist = "asdf";

        String getArtist = "select * from songInfo where SONGID = ?";
        PreparedStatement statement = conn.prepareStatement(getArtist);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            artist = resultSet.getString(3);
        }
        return artist;
    }

    public String getAlbum(int id) throws SQLException {
        String album = "asdf";

        String getAlbum = "select * from songInfo where SONGID = ?";
        PreparedStatement statement = conn.prepareStatement(getAlbum);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            album = resultSet.getString(4);
        }
        return album;
    }

    public String getYear(int id) throws SQLException {
        String year = "asdf";

        String getYear = "select * from songInfo where SONGID = ?";
        PreparedStatement statement = conn.prepareStatement(getYear);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            year = resultSet.getString(5);
        }
        return year;
    }

    public int getGenre(int id) throws SQLException {
        int genre = 0;
        String getGenre = "select * from songInfo where SONGID = ?";
        PreparedStatement statement = conn.prepareStatement(getGenre);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            genre = resultSet.getInt(6);
        }
        return genre;
    }
            
}
