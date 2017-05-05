/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayerapp;

import com.mpatric.mp3agic.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javazoom.*;
import javazoom.jlgui.basicplayer.*;
import javazoom.spi.*;

/**
 *
 * @author stevengarcia
 */
public class MusicPlayerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedTagException, InvalidDataException, BasicPlayerException {

        Mp3File mp3file = new Mp3File("fur-elise.mp3");
        System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
        System.out.println("Bitrate: " + mp3file.getLengthInSeconds() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
        System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
        System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
        System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
        System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
    

        Controller appController = new Controller();
        appController.startApp();
    }
}
