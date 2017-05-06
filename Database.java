/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*****************************************************
* This code only initializes a Database instance and *
* connects it to the server. No tables are defined   *
* or any other operations provided.                  *
*****************************************************/

public class Database {
    Connection conn; //once a connection is established it stays
                     //as long as the code that created this
			     //instance does not exit

    Statement stat;  //stat can be reused in every operation
    String query;

    public Database()throws IOException, ClassNotFoundException, 
                            SQLException, Exception
    {
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
    public void createTables() {
        /*
        CREATE TABLE Playlist (
            Title VARCHAR(30),
            Artist VARCHAR(30),
            ALBUM VARCHAR(30),
            Year CHAR(4),
        )
        */
    }
    
    /**
     * 
     */
    public void addSong(String songName) {

    }
    
    /**
     * 
     */
    public void removeSong(String songName) {
        
    }
}
