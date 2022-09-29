package com.media;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JOptionPane;


/**
 * Class ini digunakan untuk memutar audio mp3 dan wav. 
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-22
 */
public class Audio {
    
    /**
     * attribute yang digunakan untuk memutar dan menonaktifkan audio/suara
     */
    private static Player players;
    /**
     * nama file dari audio yang akan diputar
     */
    public static final String  SOUND_ERROR = "sound-error.wav", SOUND_WARNING = "sound-warning.wav", SOUND_INFO = "sound-info.wav";
    /**
     * directory penyimpanan file audio
     */
    private static final String directory = "src\\resources\\audio\\";
        
    /**
     * Method ini digunakan untuk memutar audio. Method ini akan memutar audio dengan menggunakan object <code>Players</code>.
     * Sebelum memutar audio method akan mengecek apakah ada audio yang sedang diputar atau tidak. Jika audio sedang diputar maka audio
     * sebelumnya akan di stop. Ini penting dilakukan supaya tidak terjadi error saat beberapa audio dijalankan dalam waktu yang bersamaan. 
     * Kemudian method akan mengambil input audio dari user melalui object <code>FileInputStream</code>. Jika tidak terjadi error saat 
     * pengambilan input audio maka audio akan diputar.
     * 
     * @param filename file audio yang akan diputar
     */
    public static void play(final String filename){
        
        // mengecek apakah audio sedang digunakan atau tidak, jika sedang digunakan maka audio akan diclose
        if(players != null){
            stop();
        }
        // mengambil input audio dari user
        try{
            FileInputStream input = new FileInputStream(directory+filename);
            players = new Player(input);
        }catch(IOException | JavaLayerException iex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil file audio \nError : " + iex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        // memutar audio
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    players.play();
                }catch(JavaLayerException jlex){
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat akan meutar audio! \nError : " + jlex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }).start();

    }

    /**
     * Method ini digunakan untuk menghentikan/menclose file audio yang sedang diputar.
     * Sebelum audio dihentikan method akan mengecek apakah object pada <code>Players</code> kosong atau tidak. 
     * Jika object <code>Players</code> tidak kosong maka audio akan dihentikan. 
     */
    public static void stop(){
        // mengecek apakah objek players kosong atau tidak
        if(players != null){
            // menghentikan audio
            players.close(); 
        }
    }
    
    public static void main(String[] args) {
        
        Audio.play("lemon.mp3");
        
    }
    
}
