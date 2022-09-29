package com.manage;

import com.data.app.Log;
import com.media.Audio;

import java.awt.Component;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * Class ini digunakan khusus untuk menampilkan dialog yang gunakan untuk memberitahukan sebuah pesan 
 * tertentu seperti pesan error, pesan warning maupun sebuah informasi ke konsol/monitor dari user. 
 * Semua method yang ada dialam class ini adalah static method hal ini dikarenakan karena class ini 
 * hanya digunakan untuk menampilkan sebuah pesan ke konsol/monitor dari user.
 *
 * @author Achmad Baihaqi
 * @since 2021-06-12
 */
public class Message {
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Error" 
     * dan messageType akan di-set ke "ERROR_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_ERROR' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah pesan error dari exception ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka pesan error tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan error akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan pesan error dari exception kedalam log method akan mengecek apakah parameter <code>obj</code>
     * kosong atau tidak jika parameter <code>obj</code> tidak kosong maka pesan error yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang menghasilkan error/exception.
     * <br><br>
     * Method akan mendapatkan pesan dan informasi mengenai exception yang terjadi melalui method {@code getExceptionInfo()}
     * Setelah pesan dan informasi mengenai exception telah didapatkan maka selanjutnya method akan menambahkan pesan dan 
     * informasi dari exception tersebut kedalam log dengan melalui method {@code Log.addLog()}. 
     * <br><br>
     * Jika pesan dan informasi dari exception sudah ditambahkan kedalam log maka selanjutnya method akan menyimpan log 
     * yang baru saja ditambahkan dengan melalui method {@code Log.saveLog()}.
     * 
     * @param obj object/class yang menghasilkan exception.
     * @param msg pesan error yang akan ditampilkan.
     * @param ex class dari exception yang ditangkap.
     * @param addLog jika <code>true</code> maka pesan error akan ditambahkan kedalam log.
     */
    public static void showException(Object obj, String msg, Throwable ex, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_ERROR);
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika obj tidak null
            if(obj != null){
                // menambahkan pesan kedalam log dengan informasi class yang menimbulkan exception
                Log.addLog("Exception in " + obj.getClass().getName() + " : " + Message.getExceptionInfo(ex));
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Exception : " + Message.getExceptionInfo(ex));
            }
            // menyimpan log yang ditambahkan
            Log.saveLog();
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Error" 
     * dan messageType akan di-set ke "ERROR_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_ERROR' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah pesan error dari exception ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka pesan error tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan error akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan pesan error dari exception kedalam log method akan mengecek apakah parameter <code>parent</code>
     * kosong atau tidak jika parameter <code>parent</code> tidak kosong maka pesan error yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang menghasilkan error/exception.
     * <br><br>
     * Method akan mendapatkan pesan dan informasi mengenai exception yang terjadi melalui method {@code getExceptionInfo()}
     * Setelah pesan dan informasi mengenai exception telah didapatkan maka selanjutnya method akan menambahkan pesan dan 
     * informasi dari exception tersebut kedalam log dengan melalui method {@code Log.addLog()}. 
     * <br><br>
     * Jika pesan dan informasi dari exception sudah ditambahkan kedalam log maka selanjutnya method akan menyimpan log 
     * yang baru saja ditambahkan dengan melalui method {@code Log.saveLog()}.
     * 
     * @param parent window/class yang menghasilkan exception.
     * @param msg pesan error yang akan ditampilkan.
     * @param ex class dari exception yang ditangkap.
     * @param addLog jika <code>true</code> maka pesan error akan ditambahkan kedalam log.
     */
    public static void showException(Component parent, String msg, Throwable ex, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_ERROR);
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika parent tidak null
            if(parent != null){
                // menambahkan pesan kedalam log dengan informasi class yang menimbulkan exception
                Log.addLog("Exception in " + parent.getClass().getName() + " : " + Message.getExceptionInfo(ex));
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Exception : " + Message.getExceptionInfo(ex));
            }
            // menyimpan log yang ditambahkan
            Log.saveLog();
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method 
     * {@code showException(Object obj, String msg, Throwable ex, boolean addLog)}. 
     * <br><br>
     * Dengan parameter <code>msg</code> dari method tersebut akan secara default akan diberi nilai sesuai 
     * dengan pesan error yang ditimbulkan oleh exception. Yang dimana pesan error dari exception 
     * tersebut akan didapatkan melalui method {@code getMessage()} yang berada didalam class {@code Throwable}.
     * 
     * @param obj object/class yang menghasilkan exception.
     * @param ex class dari exception yang ditangkap.
     * @param addLog jika <code>true</code> maka pesan error akan ditambahkan kedalam log.
     */
    public static void showException(Object obj, Throwable ex, boolean addLog){
        Message.showException(obj, ex.getMessage(), ex , addLog);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method 
     * {@code showException(Component obj, String msg, Throwable ex, boolean addLog)}. 
     * <br><br>
     * Dengan parameter <code>msg</code> dari method tersebut akan secara default akan diberi nilai sesuai 
     * dengan pesan error yang ditimbulkan oleh exception. Yang dimana pesan error dari exception 
     * tersebut akan didapatkan melalui method {@code getMessage()} yang berada didalam class {@code Throwable}.
     * 
     * @param parent window/class yang menghasilkan exception.
     * @param ex class dari exception yang ditangkap.
     * @param addLog jika <code>true</code> maka pesan error akan ditambahkan kedalam log.
     */
    public static void showException(Component parent, Throwable ex, boolean addLog){
        Message.showException(parent, ex.getMessage(), ex, addLog);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method 
     * {@code showException(Object obj, Throwable ex, boolean addLog)}. 
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti pesan dan informasi dari exception yang terjadi tidak akan 
     * ditambahkan kedalam log.
     * 
     * @param obj object/class yang menghasilkan exception.
     * @param ex class dari exception yang ditangkap.
     */
    public static void showException(Object obj, Throwable ex){
        Message.showException(obj, ex, false);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan error/exception yang sedang terjadi dengan cara memanggil method 
     * {@code showException(Component parent, Throwable ex, boolean addLog)}. 
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti pesan dan informasi dari exception yang terjadi tidak akan 
     * ditambahkan kedalam log.
     * 
     * @param parent window/class yang menghasilkan exception.
     * @param ex class dari exception yang ditangkap.
     */
    public static void showException(Component parent, Throwable ex){
        Message.showException(parent, ex, false);
    }
    
    /**
     * Method ini digunakan untuk mendapakan pesan dan informasi mengenai exception yang diinputkan.
     * Petama-tama method akan mengamblil stack trace dari exception yang diinputkan dengan melalui 
     * method {@code getStackTrace()} yang berada didalam class {@code Throwable} untuk mendapatkan 
     * informasi mengenai exception. Ouput dari method tersebut adalah sebuah array yang sudah 
     * dikonversi kedalam bentuk string.
     * <br><br>
     * Selanjutnya method akan membuat sebuah object {@code StringTokenizer} baru yang digunakan untuk 
     * membaca semua data yang ada didalam stack trace. Setelah object berhasil dibuat maka method akan 
     * melakukan perulangan untuk membaca satu-persatu data yang ada didalam stack trace melalui object 
     * {@code StringTokenizer} yang baru saja dibuat.
     * <br><br>
     * Selama proses membaca data yang ada didalam stack trace untuk mendapatkan informasi error pada 
     * exception method akan mengumpulkan informasi error tersebut kedalam String. Hal ini akan terus
     * dilakukan sampai tidak ada lagi data yang ada didalam stack trance. Jika sudah tidak ada lagi 
     * data yang ada didalam stack trace maka method akan mengembalikan nilai dari String yang digunakan 
     * untuk mengumpulkan informasi mengenai exception saat perulagan sedang berlangsung.
     * 
     * @param thr exception yang ingin didapatkan informasi errornya.
     * @return informasi mengenai exception yang diinputkan.
     */
    private static String getExceptionInfo(Throwable thr){
        // mendapatkan stace trace dari exception
        String stackTrace = Arrays.toString(thr.getStackTrace()).replaceAll("\\[", "").replaceAll("\\]", ""),
               log = thr.toString().replaceAll("\n", ", "); // mendapatkan class dan pesan error
        
        // memasukan stace trace kedalam string tokenizer
        StringTokenizer token = new StringTokenizer(stackTrace, ",");
        
        // membaca semua data yang ada didalam token
        while(token.hasMoreTokens()){
            // menangkap informasi pada exception
            log += "\n\t\t\t  -> at " + (token.nextToken().replace(" ", "")) + "";
        }
        return log;
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan warning yang sedang terjadi dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Warning" 
     * dan messageType akan di-set ke "WARNING_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_WARNING' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah pesan warning ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka pesan warning tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan warning akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan pesan warning kedalam log method akan mengecek apakah parameter <code>obj</code>
     * kosong atau tidak jika parameter <code>obj</code> tidak kosong maka pesan warning yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang menghasilkan warning.
     * 
     * @param obj object/class yang menghasilkan warning.
     * @param msg pesan warning yang akan ditampilkan.
     * @param addLog jika <code>true</code> maka pesan warning akan ditambahkan kedalam log.
     */
    public static void showWarning(Object obj, String msg, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika obj tidak null
            if(obj != null){
                // menambahkan pesan kedalam log dengan informasi class yang menimbulkan warning
                Log.addLog("Warning in " + obj.getClass().getName() + " : " + msg);
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Warning : " + msg);
            }
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan warning yang sedang terjadi dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Warning" 
     * dan messageType akan di-set ke "WARNING_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_WARNING' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah pesan warning ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka pesan warning tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan warning akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan pesan warning kedalam log method akan mengecek apakah parameter <code>parent</code>
     * kosong atau tidak jika parameter <code>parent</code> tidak kosong maka pesan warning yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang menghasilkan warning.
     * 
     * @param parent window/class yang menghasilkan warning.
     * @param msg pesan warning yang akan ditampilkan.
     * @param addLog jika <code>true</code> maka pesan warning akan ditambahkan kedalam log.
     */
    public static void showWarning(Component parent, String msg, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(parent, msg, "Warning", JOptionPane.WARNING_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika parent tidak null
            if(parent != null){
                // menambahkan pesan kedalam log dengan informasi class yang menimbulkan warning
                Log.addLog("Warning in " + parent.getClass().getName() + " : " + msg);
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Warning : " + msg);
            }
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan warning yang sedang terjadi dengan cara memanggil method showWarning(Object obj, String msg, boolean addLog).
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti pesan warning yang diinputkan tidak akan ditambahkan kedalam log.
     * 
     * @param obj object/class yang menghasilkan warning.
     * @param msg pesan warning akan ditampilkan.
     */
    public static void showWarning(Object obj, String msg){
        Message.showWarning(obj, msg, false);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * pesan warning yang sedang terjadi dengan cara memanggil method showWarning(Component parent, String msg, boolean addLog).
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti pesan warning yang diinputkan tidak akan ditambahkan kedalam log.
     * 
     * @param parent window/class yang menghasilkan warning.
     * @param msg pesan warning akan ditampilkan.
     */
    public static void showWarning(Component parent, String msg){
        Message.showWarning(parent, msg, false);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * informasi yang diinputkan dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Information" 
     * dan messageType akan di-set ke "INFORMATION_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_INFO' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah informasi yang diinputkan ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka informasi tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan informasi akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan informasi yang diinputkan kedalam log method akan mengecek apakah parameter <code>obj</code>
     * kosong atau tidak jika parameter <code>obj</code> tidak kosong maka informasi yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang akan menampilkan informasi.
     * 
     * @param obj object/class yang menampilkan informasi.
     * @param msg informasi yang akan ditampilkan.
     * @param addLog jika <code>true</code> informasi akan ditambahkan ke log.
     */
    public static void showInformation(Object obj, String msg, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika obj tidak null
            if(obj != null){
                // menambahkan pesan kedalam log dengan informasi class yang menampilkan informasi
                Log.addLog("Information in " + obj.getClass().getName() + " : " + msg);
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Information : " + msg);
            }
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * informasi yang diinputkan dengan cara memanggil method {@code showMessageDialog()}
     * yang berada didalam class {@code JOptionPane}. Dengan title dari method tersebut akan di-set ke "Information" 
     * dan messageType akan di-set ke "INFORMATION_MESSAGE". 
     * <br><br>
     * Method juga akan memanggil method {@code Audio.play()} yang digunakan untuk memutar sebuah file audio. 
     * File audio yang akan diputar melalui method tersebut adalah 'SOUND_INFO' yang berada didalam folder resources.
     * <br><br>
     * User juga dapat mengatur apakah informasi yang diinputkan ingin ditambahkan ke dalam log atau tidak. 
     * Jika paramenter <code>addLog</code> bernilai <strong>False</strong> maka informasi tidak akan ditambakan 
     * kedalam log. Tetapi jika nilainya <strong>True</strong> maka pesan informasi akan ditambahkan kedalam log.
     * <br><br>
     * Sebelum menambahkan informasi yang diinputkan kedalam log method akan mengecek apakah parameter <code>parent</code>
     * kosong atau tidak jika parameter <code>parent</code> tidak kosong maka informasi yang akan ditambahkan kedalam 
     * log disertai dengan alamat class yang akan menampilkan informasi.
     * 
     * @param parent window/class yang menampilkan informasi.
     * @param msg informasi yang akan ditampilkan.
     * @param addLog jika <code>true</code> informasi akan ditambahkan ke log.
     */
    public static void showInformation(Component parent, String msg, boolean addLog){
        // menampilkan dialog pesan ke layar
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(parent, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
        
        // mengecek apakah pesan akan disimpan kedalam log atau tidak
        if(addLog){
            // jika parent tidak null
            if(parent != null){
                // menambahkan pesan kedalam log dengan informasi class yang menampilkan informasi
                Log.addLog("Information in " + parent.getClass().getName() + " : " + msg);
            }else{
                // menambahkan pesan kedalam log
                Log.addLog("Information : " + msg);
            }
        }
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * informasi yang diinputkan dengan cara memanggil method showInformation(Component parent, String msg, boolean addLog).
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti informasi yang diinputkan tidak akan ditambahkan kedalam log.
     * 
     * @param obj object/class yang menampilkan informasi.
     * @param msg informasi yang akan ditampilkan.
     */
    public static void showInformation(Object obj, String msg){
        Message.showInformation(obj, msg, false);
    }
    
    /**
     * Method ini digunakan untuk menampilkan sebuah dialog yang digunakan untuk memberitahukan 
     * informasi yang diinputkan dengan cara memanggil method showInformation(Component parent, String msg, boolean addLog).
     * <br><br>
     * Dengan parameter <code>addLog</code> dari method tersebut akan secara default akan diberi nilai 
     * <strong>False</strong>. Ini berarti informasi yang diinputkan tidak akan ditambahkan kedalam log.
     * 
     * @param parent window/class yang menampilkan informasi.
     * @param msg informasi yang akan ditampilkan.
     */
    public static void showInformation(Component parent, String msg){
        Message.showInformation(parent, msg, false);
    }
    
}
