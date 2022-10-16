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
public class Petugas extends Users{
    
    private final Text text = new Text();
    
    public String createID(){
        return super.createID(UserLevels.PETUGAS, UserData.ID_PETUGAS);
    }
    
    public boolean isExistPetugas(String idPetugas){
        return super.isExistID(idPetugas, UserLevels.PETUGAS, UserData.ID_PETUGAS);
    }
    
    public final boolean addPetugas(String namaPetugas, String noTelp, String alamat, String pass, UserLevels level){
        boolean isAdd;
        PreparedStatement pst;
        String idPetugas = this.createID();
        try {
            // menambahkan data user ke tabel user
            isAdd = super.addUser(idPetugas, pass, level);
            // mengecek apakah id user sudah ditambahkan ke tabel user
            if(isAdd){
                // validasi data sebelum ditambahkan
                if(this.validateAddPetugas(idPetugas, namaPetugas, noTelp, alamat, pass, level)){
                    Log.addLog("Menambahkan data petugas dengan nama '" + namaPetugas + "'");
                    // menambahkan data kedalam Database
                    pst = this.conn.prepareStatement("INSERT INTO petugas VALUES (?, ?, ?, ?)");
                    pst.setString(1, idPetugas);
                    pst.setString(2, text.toCapitalize(namaPetugas));
                    pst.setString(3, noTelp);
                    pst.setString(4, text.toCapitalize(alamat));

                    // mengekusi query
                    return pst.executeUpdate() > 0;
                }
            }
        } catch (SQLException | InValidUserDataException ex) {
            this.deleteUser(idPetugas);
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean validateAddPetugas(String idPetugas, String namaPetugas, String noTelp, String alamat, String pass, UserLevels level){
        
        boolean vIdPetugas, vNama, vNoTelp, vAlamat, vPass, vLevel;
        
        // mengecek id petugas valid atau tidak
        if(Validation.isIdPetugas(idPetugas)){
            vIdPetugas = true;
        }else{
            throw new InValidUserDataException("'" + idPetugas + "' ID Petugas tersebut tidak valid.");
        }
        
        // menecek nama valid atau tidak
        if(Validation.isNamaOrang(namaPetugas)){
            vNama = true;
        }else{
            throw new InValidUserDataException("'" + namaPetugas + "' Nama Petugas tersebut tidak valid.");
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
        
        // mengecek apakah password valid atau tidak
        if(Validation.isPassword(pass)){
            vPass = true;
        }else{
            throw new InValidUserDataException("'" + pass + "' Password tersebut tidak valid.");
        }
        
        // mengecek apakah level valid atau tidak
        if(Validation.isLevel(level)){
            vLevel = true;
        }else{
            throw new InValidUserDataException("'" + level + "' Level tersebut tidak valid.");
        }
                
        return vIdPetugas && vNama && vNoTelp && vAlamat && vPass && vLevel;
    }
    
    public boolean deletePetugas(String idPetugas){
        return super.deleteUser(idPetugas);
    }
    
    private String getDataPetugas(String idPetugas, UserData data){
        return super.getUserData(idPetugas, UserLevels.PETUGAS, data, UserData.ID_PETUGAS);
    }
    
    public String getNama(String idPetugas){
        return this.getDataPetugas(idPetugas, UserData.NAMA_PETUGAS);
    }
    
    public String getNoTelp(String idPetugas){
        return this.getDataPetugas(idPetugas, UserData.NO_TELP);
    }
    
    public String getAlamat(String idPetugas){
        return this.getDataPetugas(idPetugas, UserData.ALAMAT);
    }
    
    private boolean setDataPetugas(String idPetugas, UserData data, String newValue){
        return super.setUserData(idPetugas, UserLevels.PETUGAS, data, UserData.ID_PETUGAS, newValue);
    }
    
    public boolean setNama(String idPetugas, String newNama){
        return this.setDataPetugas(idPetugas, UserData.NAMA_PETUGAS, newNama);
    }
    
    public boolean setNoTelp(String idPetugas, String newNoTelp){
        return this.setDataPetugas(idPetugas, UserData.NO_TELP, newNoTelp);
    }
    
    public boolean setAlamat(String idPetugas, String newAlamat){
        return this.setDataPetugas(idPetugas, UserData.ALAMAT, newAlamat);
    }
    

    
    public static void main(String[] args) {
        
        Log.createLog();
        Petugas petugas = new Petugas();
//        System.out.println(petugas.getNama("PG002"));
//        System.out.println(petugas.getNoTelp("PG002"));
//        System.out.println(petugas.getAlamat("PG002"));
//        System.out.println(petugas.getNoTelp("PG002"));
//        System.out.println("");
//        System.out.println(petugas.deletePetugas("PG005"));
   
        
    }
}
