package com.users;

import com.data.app.Log;
import com.error.InValidUserDataException;
import com.manage.Text;
import com.manage.Validation;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 */
public class Pembeli extends Users{
    
    private final Text text = new Text();
    
    public String createID(){
        return super.createID(UserLevels.PEMBELI, UserData.ID_PEMBELI);
    }
    
    public boolean isExistPembeli(String idPembeli){
        return super.isExistID(idPembeli, UserLevels.PEMBELI, UserData.ID_PEMBELI);
    }
    
    public final boolean addPembeli(String namaPembeli, String noTelp, String alamat){
        boolean isAdd;
        PreparedStatement pst;
        String idPembeli = this.createID();
        
        try {
            // menambahkan data user ke tabel user
            isAdd = super.addUser(idPembeli, "12345", UserLevels.PEMBELI);
            // mengecek apakah id user sudah ditambahkan ke tabel user
            if(isAdd){
                // validasi data sebelum ditambahkan
                if(this.validateAddPembeli(idPembeli, namaPembeli, noTelp, alamat)){
                    Log.addLog("Menambahkan data pembeli dengan nama '" + namaPembeli + "'");
                    // menambahkan data kedalam Database
                    pst = this.conn.prepareStatement("INSERT INTO pembeli VALUES (?, ?, ?, ?)");
                    pst.setString(1, idPembeli);
                    pst.setString(2, text.toCapitalize(namaPembeli));
                    pst.setString(3, noTelp);
                    pst.setString(4, text.toCapitalize(alamat));

                    // mengekusi query
                    return pst.executeUpdate() > 0;
                }
            }
        } catch (SQLException | InValidUserDataException ex) {
            this.deleteUser(idPembeli);
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean validateAddPembeli(String idPembeli, String namaPembeli, String noTelp, String alamat){
        
        boolean vIdPembeli, vNama, vNoTelp, vAlamat;
        
        // mengecek id pembeli valid atau tidak
        if(Validation.isIdPembeli(idPembeli)){
            vIdPembeli = true;
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
        return super.deleteUser(idPembeli);
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

    }
}
