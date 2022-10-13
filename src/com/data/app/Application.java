package com.data.app;

import com.error.StorageNotFoundException;
import com.manage.Waktu;
import com.window.frames.WelcomeApp;
import javax.swing.ImageIcon;

/**
 * Class ini digunakan untuk mendapatkan data-data informasi dari Aplikasi. Data-data informasi dapat berupa versi, author, nama aplikasi,
 * tahun ajaran dll. Selain digunakan untuk mendapatkan data-data informasi dari aplikasi class ini juga dapat digunakan untuk 
 * memulai dan menutup aplikasi.
 *
 * @author Achmad Baihaqi
 * @since 2021-04-25
 */
public final class Application {
    
    /**
     * Informasi-informasi mengenai aplikasi.
     */
    private static final String VERSION = "1.0.0",
                                APP_NAME = "Simple Buy",
                                AUTHOR = "Gemastik Team Lightning",
                                COMPANY = "Politeknik Negeri Jember",
                                APP_ICON = "src\\resources\\image\\icons\\app-logo.png";
    /**
     * Method ini digunakan untuk membuka aplikasi. Sebelum membuka aplikasi method akan membuat sebuah log baru dengan menggunakan 
     * method {@code createLog()} yang ada didalam class {@code Log}. Setelah log berhasil dibuat maka selanjutnya method akan 
     * mengecek apakah user baru pertama kali menjalankan/menginstal aplikasi atau tidak dengan menggunakan method 
     * {@code isFistTimeInstall()}. Jika user baru menjalankan/menginstal aplikasi maka method akan membuat storage dari aplikasi 
     * dengan menggunakan method {@code recoverStorage()} pada class {@code Storage}. Setelah storage berhasil dibuat maka 
     * selanjutnya method akan membuka window WelcomeWindow.
     * <br><br>
     * Tetapi jika user sebelumnya sudah menjalankan/menginstal aplikasi maka method akan mengecek apakah storage dari aplikasi 
     * sehat atau tidak. Sehat yang dimaksud adalah semua foder dan content yang ada didalam storage aplikasi lengkap dan tidak 
     * kurang sedikitpun. Jika storage dari aplikasi tidak sehat maka akan menghasilkan exception {@code StorageNotFoundException} 
     * dan method akan memulihkan storage dari aplikasi dengan menggunakan method {@code recoverStorage()} pada class {@code Storage}. 
     * Setelah storage berhasil dipulihkan maka selanjutnya method akan membuka SplashWindow.
     * 
     * @throws StorageNotFoundException jika storage dari aplikasi tidak sehat.
     */
    public static void startApplication() throws StorageNotFoundException{
        
        // membuat log
        Log.createLog();
        Log.addLog("Membuka Aplikasi");
        
        // mengupdate waktu
        Waktu.updateWaktuOld();
        
        // jika user baru pertama kali menginstall maka data aplikasi akan dibuat
        if(Application.isFirstTimeInstall()){
            new Storage().recoverStorage();
            // membuka window welcome
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new WelcomeApp().setVisible(true);
                }
            });
        }else{
            // mengecek apakah data aplikasi sehat atau tidak
            if(!new Storage().isHealtyStorage()){
                // memulihkan data aplikasi
                new Storage().recoverStorage();
                throw new StorageNotFoundException("Fatal Error : \n\nData Aplikasi Anda telah Corrupt!\nKlik 'OK' dan buka kembali Aplikasi untuk memulihkan Data Aplikasi!!");
            }
            // membuka splash window
            java.awt.EventQueue.invokeLater(new Runnable(){

                @Override
                public void run(){
                    new com.window.frames.SplashWindow().setVisible(true);
                }
            });
        }
    }
    
    /**
     * Method ini digunakan untuk menutup aplikasi. Sebelum menutup aplikasi method akan menyimpan log aplikasi dengan 
     * menggunakan method {@code saveLog()} yang ada didalam class {@code Log}. Setelah log berhasil disimpan maka 
     * selanjutnya method akan menutup aplikasi dengan melalui method {@code exit()} pada class {@code System()}.
     * 
     */
    public static void closeApplication(){
        Log.addLog("Menutup Aplikasi");
        // menyimpan log
        Log.saveLog();
        // menutup aplikasi
        System.exit(0);
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah user baru pertama kali menjalakan aplikasi atau tidak. 
     * Untuk melihat apakah user baru pertama kali menginstal aplikasi dapat di ketauhi dengan cara mengecek
     * apakah folder storage aplikasi exist atau tidak. Jika folder storage aplikasi tidak exist maka user dinyatakan 
     * baru menginstal aplikasi.
     * 
     * @return <strong>True</strong> jika baru pertama kali menginstal aplikasi. <br>
     *         <strong>False</strong> jika tidak.
     */
    private static boolean isFirstTimeInstall(){
        return !new Storage().isExistDirecotry();
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai versi dari aplikasi.
     * 
     * @return versi dari aplikasi.
     */
    public static String getVersi(){
        return VERSION;
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai nama dari aplikasi.
     * 
     * @return nama dari aplikasi.
     */
    public static String getNama(){
        return APP_NAME;
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai author dari aplikasi.
     * 
     * @return author dari aplikasi.
     */
    public static String getAuthor(){
        return AUTHOR;
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai right dari aplikasi.
     * 
     * @return right dari aplikasi.
     */
    public static String getRights(){
        return String.format("Copyright © 2022 %s.", getAuthor());
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai right reserved dari aplikasi.
     * 
     * @return right reserved dari aplikasi.
     */
    public static String getRightReserved(){
        return String.format("%s All Rights Reserved.", getRights());
    }
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai company dari aplikasi.
     * 
     * @return company dari aplikasi.
     */
    public static String getCompany(){
        return COMPANY;
    }    
    
    /**
     * Digunakan untuk mendapatkan informasi mengenai window icon dari aplikasi.
     * 
     * @return window icon dari aplikasi.
     */
    public static ImageIcon getWindowIcon(){
        return new ImageIcon(APP_ICON);
    }       
    
}
