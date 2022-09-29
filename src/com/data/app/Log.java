package com.data.app;

import com.manage.Waktu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class ini digunakan untuk menangani data log pada aplikasi. Log akan disimpan ke sebuah file yang  
 * ada didalam folder penyimpanan aplikasi (storage). Saat user menambahkan sebuah log menggunakan method 
 * {@code addLog(String log)} log tidak akan langsung disimpan kedalam file log yang ada dialam storage. 
 * Melainkan log akan sementara disimpan kedalam object {@code StringBuilder}. Jika setiap ada log baru 
 * lalu log tersebut langsung dsimpan kedalam file maka hal ini sangat tidak mengefisiensi waktu dan memory
 * yang digunakan. Log akan secara otomatis disimpan kedalam file jika kapasitas dari log yang ditambahkan 
 * ke data sementara sudah mencapai 15 log. 
 *
 * @author Achmad Baihaqi
 * @since 2021-06-12
 */
public final class Log {
    
    /**
     * Digunakan untuk menyimpan data sementara dari log sebelum disimpan kedalam file.
     */
    private static StringBuilder logData;
    
    /**
     * Direktori dari file yang dibuat untuk menyimpan data log aplikasi.
     */
    private static final String logFile = new Storage().getLogDir() + "Log.txt";
    
    /**
     * Untuk menyimpan data jumlah dari log yang sedang disimpan di {@code StringBuilder}.
     */
    private static int capacity = 0;
    
    /**
     * Mengembalikan jumlah dari log yang sedang disimpan di {@code StringBuilder}.
     * 
     * @return jumlah log yang disimpan di {@code StringBuilder}.
     */
    public static int getCapacity(){
        return Log.capacity;
    }
    
    /**
     * Digunakan untuk membuat sebuah log baru dengan data yang kosong. Method akan 
     * membuat sebuah object {@code StringBuilder} baru yang nantinya akan digunakan 
     * untuk sementara menyimpan log yang ditambahkan sebelum disimpan ke dalam 
     * file yang ada di dalam sstorage.
     * 
     */
    public static void createLog(){
        logData = new StringBuilder();
    }
    
    /**
     * Method ini digunakan untuk menambahkan sebuah log kedalam object {@code StringBuilder} yang 
     * digunakan untuk menyimpan sementara log yang ditambahkan sebelum disimpan kedalam file.
     * Petama-tama method akan mengecek apakah object dari {@code StringBuilder} yang digunakan untuk 
     * menyimpan semetara log kosong atau tidak. Jika object tersebut kosong maka method akan 
     * menghasilkan exception {@code NullPointerException}.
     * <br><br>
     * Selanjutnya method akan mengecek apakah jumlah log yang sedang disimpan didalam object {@code StringBuilder}
     * jumlahnya kurang dari sama dengan 15 atau tidak dengan menggunakan method {@code getCapacity()}. Jika 
     * output dari method tersebut adalah kurang dari sama dengan 15 maka log yang diinputkan akan disimpan 
     * sementara di object {@code StringBuilder}.
     * <br><br>
     * Tetapi jika output dari method {@code getCapacity()} tersebut adalah lebih besar dari 15 maka log yang 
     * sebelumnya sementara disimpan didalam object {@code StringBuilder} akan disimpan kedalam file log yang 
     * ada didalam storage. Log tidak langsung disimpan kedalam file karena jika setiap ada log baru lalu 
     * log tersebut langsung dsimpan kedalam file maka hal ini sangat tidak mengefisiensi waktu dan memory
     * 
     * @param log log yang akan ditambahkan.
     */
    public static void addLog(String log){
        
        // jika log belum dibuat maka akan menghasilkan exception NullPointerException.
        if(logData == null){
            throw new NullPointerException("Log belum dibuat.");
        }
        
        // jika capacity <= 15 maka log akan disimpan kedalam data semetara.
        if(Log.getCapacity() <= 15){
            // menambahkan log ke data sementara.
            logData.append(new Waktu().getCurrentDateTime()).append(" | ").append(log).append("\n");
            // menampilkan log ke konsol
            System.out.format("%s | %s\n", new Waktu().getCurrentDateTime().substring(0, 19), log);
        }
        // jika capacity > 15 maka log akan secara otomatis disimpan kedalam file.
        else{
            // menambahkan log ke data sementara.
            logData.append(new Waktu().getCurrentDateTime()).append(" | ").append(log).append("\n");
            // menampilkan log ke konsol
            System.out.format("%s | %s\n", new Waktu().getCurrentDateTime().substring(0, 19), log);
            // menyimpan log kedalam file.
            Log.saveLog();
        }
        // capacity akan selalu bertambah saat method dipanggil
        capacity++;
    }
    
    /**
     * Untuk menyimpan log yang sebelumnya disimpan sementara didalam object {@code StringBuilder}
     * kedalam file yang ada didalam storage. Sebelum menyimpan file method akan mendapatkan 
     * semua log yang ada didalam object {@code StringBuilder} dalam bentuk String.
     * Method akan menyimpan log kedalam file menggunakan bantuan dari class {@code BufferedWriter}. 
     * Setelah semua log yang ada didalam object {@code StringBuilder} sudah disimpan kedalam file
     * maka selanjutnya method akan menghapus semua log yang ada dialam object {@code StringBuilder} 
     * dengan cara membuat object {@code StringBuilder} baru melalui method {@code createLog}. Method
     * juga akan mereset jumlah log yang sedang disimpan di {@code StringBuilder} ke 0.
     * 
     */
    public static void saveLog(){
        
        // jika tidak ada log yang ditambahkan maka method akan menghasilkan exception.
        if(Log.getCapacity() <= 0){
            throw new NullPointerException("Tidak ada log yang ditambahkan.");
        }
        
        try(BufferedWriter save = new BufferedWriter(new FileWriter(logFile, true))){
           // menyimpan log kedalam file
           save.write(logData.toString());
           save.flush();
           
           // mereset object {@code StringBuilder} yg digunakan untuk menyimpan sementar log
           Log.createLog();
           // mereset capacity ke 0
           capacity = 0;
        }catch(IOException ex){
            System.out.println("Terjadi Kesalahan : " + ex.getMessage());
        }
    }
}
