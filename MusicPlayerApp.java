/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import com.mpatric.mp3agic.*;
import java.io.IOException;
import java.sql.SQLException;
import javazoom.jlgui.basicplayer.*;
/**
 *
 * @author stevengarcia
 */
public class MusicPlayerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedTagException, InvalidDataException, BasicPlayerException, SQLException {
        Controller appController = new Controller();
        appController.startApp();
    }
}
