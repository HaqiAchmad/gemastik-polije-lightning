package com.users;

import com.data.app.Log;
import com.error.InValidUserDataException;
import com.manage.Validation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achmad Baihaqi
 */
public class Pembeli extends Users{
    
    public String createID(){
        return super.createID(UserLevels.PEMBELI, UserData.ID_PEMBELI);
    }
    
    public boolean isExistPembeli(String idPembeli){
        return super.isExistID(idPembeli, UserLevels.PEMBELI, UserData.ID_PEMBELI);
    }
    
    public final boolean addPembeli(String idPembeli, String namaPembeli, String noTelp, String alamat){
        boolean isAdd;
        PreparedStatement pst;
        try {
            // menambahkan data user ke tabel user
            isAdd = super.addUser(this.createID(), "12345", UserLevels.PEMBELI);
            // mengecek apakah id user sudah ditambahkan ke tabel user
            if(isAdd){
                // validasi data sebelum ditambahkan
                if(this.validateAddPembeli(idPembeli, namaPembeli, noTelp, alamat)){
                    Log.addLog("Data dari '" + idPembeli + "' dinyatakan valid.");
                    // menambahkan data kedalam Database
                    pst = this.conn.prepareStatement("INSERT INTO pembeli VALUES (?, ?, ?, ?)");
                    pst.setString(1, idPembeli);
                    pst.setString(2, namaPembeli);
                    pst.setString(3, noTelp);
                    pst.setString(4, alamat);

                    // mengekusi query
                    return pst.executeUpdate() > 0;
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    private boolean validateAddPembeli(String idPembeli, String namaPembeli, String noTelp, String alamat){
        
        boolean vIdPembeli, vNama, vNoTelp, vAlamat;
        
        // mengecek id pembeli valid atau tidak
        if(Validation.isIdPembeli(idPembeli)){
            if(this.isExistPembeli(idPembeli)){
                vIdPembeli = true;
            }else{
                throw new InValidUserDataException("'" + idPembeli + "' ID Pembeli tersebut sudah terpakai.");
            }
        }else{
            throw new InValidUserDataException("'" + idPembeli + "' ID Pembeli tersebut tidak valid.");
        }
        
        // menecek nama valid atau tidak
        if(Validation.isNamaOrang(namaPembeli)){
            vNama = true;
        }else{
            throw new InValidUserDataException("'" + namaPembeli + "' Nama Pembeli tersebut tidak valid.");
        }
                
        // mengecek apakah no hp valid atau tidak
        if(Validation.isNoHp(noTelp)){
            vNoTelp = true;
        }else{
            throw new InValidUserDataException("'" + noTelp + "' No Telephone tersebut tidak valid.");
        }
                
        // mengecek apakah alamat valid atau tidak
        if(Validation.isNamaTempat(alamat)){
            vAlamat = true;
        }else{
            throw new InValidUserDataException("'" + alamat + "' Alamat tersebut tidak valid.");
        }
                
        return vIdPembeli && vNama && vNoTelp && vAlamat;
    }
    
    public boolean deletePembeli(String idPembeli){
        return false;
    }
    
    private String getDataPembeli(String idPembeli, UserData data){
        return super.getUserData(idPembeli, UserLevels.PEMBELI, data, UserData.ID_PEMBELI);
    }
    
    public String getNama(String idPembeli){
        return this.getDataPembeli(idPembeli, UserData.NAMA_PEMBELI);
    }
    
    public String getNoTelp(String idPembeli){
        return this.getDataPembeli(idPembeli, UserData.NO_TELP);
    }
    
    public String getAlamat(String idPembeli){
        return this.getDataPembeli(idPembeli, UserData.ALAMAT);
    }
    
    private boolean setDataPembeli(String idPembeli, UserData data, String newValue){
        return super.setUserData(idPembeli, UserLevels.PEMBELI, data, UserData.ID_PEMBELI, newValue);
    }
    
    public boolean setNama(String idPembeli, String newNama){
        return this.setDataPembeli(idPembeli, UserData.NAMA_PEMBELI, newNama);
    }
    
    public boolean setNoTelp(String idPembeli, String newNoTelp){
        return this.setDataPembeli(idPembeli, UserData.NO_TELP, newNoTelp);
    }
    
    public boolean setAlamat(String idPembeli, String newAlamat){
        return this.setDataPembeli(idPembeli, UserData.ALAMAT, newAlamat);
    }
    
    
    public static void main(String[] args) {
        
        Log.createLog();
        Pembeli pembeli = new Pembeli();
        
        boolean isValid = pembeli.validateAddPembeli("PB333", "Achmad", "085655864624", "Jombang");
        System.out.println(isValid);
        
//        Validation.isIdAdmin("PB222");
//        
//        System.out.println(Validation.isIdAdmin("AD333"));
//        System.out.println(Validation.isIdKaryawan("KY333"));
//        System.out.println(Validation.isIdSupplier("SP333"));
//        System.out.println(Validation.isIdPembeli("PB333"));
    }
}
