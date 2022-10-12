package com.test;

import com.data.app.Log;
import com.data.db.Database;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Digunakan untuk menginputkan data pembeli yang semula disimpan pada file ke database mysql.
 *
 * @author Achmad Baihaqi
 */
public class InputDataPembeli {
    
    private final Database dbase = new Database();
    
    public InputDataPembeli(){
        Log.createLog();
        dbase.startConnection();
    }
    
    private void writeData(){
        try{
            int id = 1;
            StringTokenizer token;
            String data, newData = "", idPembeli, namaPembeli, alamat, noHP;
            // input output file
            FileReader fileR = new FileReader("src\\resources\\data\\pembeliMentahan.haqi");
            FileWriter fileW = new FileWriter("src\\resources\\data\\pembeli.haqi");
            BufferedReader buff = new BufferedReader(fileR);
            BufferedWriter write = new BufferedWriter(fileW);
            
            // membaca mentahan data pembeli
            while((data = buff.readLine()) != null){
                token = new StringTokenizer(data, "#");
                
                // mendapatkan id pembeli
                idPembeli = String.format("PB%03d", id);
                token.nextToken(); token.nextToken();
                // mendapatkan data nama pembeli
                namaPembeli = token.nextToken();
                token.nextToken(); token.nextToken(); token.nextToken();
                // mendapatkan data alamat
                alamat = token.nextToken();
                // mendapatkan data no hp
                noHP = token.nextToken();
                
                // auto increment id pembeli
                id++; 
                newData += String.format("%s#%s#%s#%s\n", idPembeli, namaPembeli, noHP, alamat);
            }
            
            System.out.println(newData);
            // menambahkan data pembeli ke file
            write.append(newData);
            write.flush();
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private String getDataPembeli(){
        try{
            FileReader fileR = new FileReader("src\\resources\\data\\pembeli.haqi");
            BufferedReader buff = new BufferedReader(fileR);
            String buffer, data = "";
            
            // mendapatakan data pembeli dari file
            while((buffer = buff.readLine()) != null){
                data += buffer + "\n";
            }
            
            return data;
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    private void insertToMySQL(){
        StringTokenizer token = new StringTokenizer(getDataPembeli(), "\n"), data;
        String buffer, idPembeli, namaPembeli, noHp, alamat, pass = "12345", level = "PEMBELI",
        queryUsers = "INSERT INTO users VALUES ", 
        queryPembeli = "INSERT INTO pembeli VALUES ";
        
        // mendapatkan data pembeli lalu memasukan data ke query mysql
        while(token.hasMoreTokens()){
            // mendapatkan data pembeli
            buffer = token.nextToken();
            data = new StringTokenizer(buffer, "#");
            idPembeli = data.nextToken();
            namaPembeli = data.nextToken();
            noHp = data.nextToken();
            alamat = data.nextToken();
            
            // membuat query mysql untuk menambahkan data
            queryUsers += String.format("\n('%s','%s','%s'),", idPembeli, pass, level);
            queryPembeli += String.format("\n('%s', '%s', '%s', '%s'),", idPembeli, namaPembeli, noHp, alamat);
        }
        // menyiapkan query untuk dieksekusi
        queryUsers = queryUsers.substring(0, queryUsers.lastIndexOf(",")).concat(";");
        queryPembeli = queryPembeli.substring(0, queryPembeli.lastIndexOf(",")).concat(";");
        System.out.println(queryUsers);
        System.out.println(queryPembeli);
        
        // mengeksekusi query dan menambahkan data pembeli ke dalam database
        dbase.addData(queryUsers);
        dbase.addData(queryPembeli);
    }
    
    public static void main(String[] args) {
        // jangan dijalankan lagi jika data pembeli sudah ada di mysql
        InputDataPembeli input = new InputDataPembeli();
        input.insertToMySQL();
        
    }
}
