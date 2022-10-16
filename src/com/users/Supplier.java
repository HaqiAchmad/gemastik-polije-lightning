package com.users;

import com.data.app.Log;
import com.error.InValidUserDataException;
import com.manage.Text;
import com.manage.Validation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achmad Baihaqi
 */
public class Supplier extends Users{
    
    private final Text text = new Text();
    
    public String createID(){
        return super.createID(UserLevels.SUPPLIER, UserData.ID_SUPPLIER);
    }
    
    public boolean isExistSupplier(String idSupplier){
        return super.isExistID(idSupplier, UserLevels.SUPPLIER, UserData.ID_SUPPLIER);
    }
    
    public final boolean addSupplier(String namaSupplier, String noTelp, String alamat){
        boolean isAdd;
        PreparedStatement pst;
        String idSupplier = this.createID();
        
        try {
            // menambahkan data user ke tabel user
            isAdd = super.addUser(idSupplier, "12345", UserLevels.SUPPLIER);
            // mengecek apakah id user sudah ditambahkan ke tabel user
            if(isAdd){
                // validasi data sebelum ditambahkan
                if(this.validateAddSupplier(idSupplier, namaSupplier, noTelp, alamat)){
                    Log.addLog("Menambahkan data supplier dengan nama '" + namaSupplier + "'");
                    // menambahkan data kedalam Database
                    pst = this.conn.prepareStatement("INSERT INTO supplier VALUES (?, ?, ?, ?)");
                    pst.setString(1, idSupplier);
                    pst.setString(2, text.toCapitalize(namaSupplier));
                    pst.setString(3, noTelp);
                    pst.setString(4, text.toCapitalize(alamat));

                    // mengekusi query
                    return pst.executeUpdate() > 0;
                }
            }
        } catch (SQLException | InValidUserDataException ex) {
            this.deleteUser(idSupplier);
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean validateAddSupplier(String idSupplier, String namaSupplier, String noTelp, String alamat){
        
        boolean vIdSupplier, vNama, vNoTelp, vAlamat;
        
        // mengecek id supplier valid atau tidak
        if(Validation.isIdSupplier(idSupplier)){
            vIdSupplier = true;
        }else{
            throw new InValidUserDataException("'" + idSupplier + "' ID Supplier tersebut tidak valid.");
        }
        
        // menecek nama valid atau tidak
        if(Validation.isNamaOrang(namaSupplier)){
            vNama = true;
        }else{
            throw new InValidUserDataException("'" + namaSupplier + "' Nama Supplier tersebut tidak valid.");
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
                
        return vIdSupplier && vNama && vNoTelp && vAlamat;
    }
    
    public boolean deleteSupplier(String idSupplier){
        return super.deleteUser(idSupplier);
    }
    
    private String getDataSupplier(String idSupplier, UserData data){
        return super.getUserData(idSupplier, UserLevels.SUPPLIER, data, UserData.ID_SUPPLIER);
    }
    
    public String getNama(String idSupplier){
        return this.getDataSupplier(idSupplier, UserData.NAMA_SUPPLIER);
    }
    
    public String getNoTelp(String idSupplier){
        return this.getDataSupplier(idSupplier, UserData.NO_TELP);
    }
    
    public String getAlamat(String idSupplier){
        return this.getDataSupplier(idSupplier, UserData.ALAMAT);
    }
    
    private boolean setDataSupplier(String idSupplier, UserData data, String newValue){
        return super.setUserData(idSupplier, UserLevels.SUPPLIER, data, UserData.ID_SUPPLIER, newValue);
    }
    
    public boolean setNama(String idSupplier, String newNama){
        return this.setDataSupplier(idSupplier, UserData.NAMA_SUPPLIER, newNama);
    }
    
    public boolean setNoTelp(String idSupplier, String newNoTelp){
        return this.setDataSupplier(idSupplier, UserData.NO_TELP, newNoTelp);
    }
    
    public boolean setAlamat(String idSupplier, String newAlamat){
        return this.setDataSupplier(idSupplier, UserData.ALAMAT, newAlamat);
    }
    
    
    public static void main(String[] args) {
        
        Log.createLog();
        Supplier supplier = new Supplier();
//        System.out.println(supplier.createID());
//        System.out.println(supplier.getNama("SP001"));
//        System.out.println(supplier.getNoTelp("SP001"));
//        System.out.println(supplier.getAlamat("SP001"));
//        System.out.println("");
//        System.out.println(supplier.setNama("SP001", "Amirzan Fikri Prasetyo"));
//        System.out.println(supplier.setNoTelp("SP001", "085790235810"));
//        System.out.println(supplier.setAlamat("SP001", "Jombang, Indonesia"));
//        System.out.println("");
//        System.out.println(supplier.getNama("SP001"));
//        System.out.println(supplier.getNoTelp("SP001"));
//        System.out.println(supplier.getAlamat("SP001"));
        System.out.println("");
        System.out.println(supplier.isExistSupplier("SP001"));
//        System.out.println(supplier.addSupplier("Mohammad Ilham Islamy", "086732905428", "Jombang, Jawa Timur"));
//        System.out.println(supplier.getNama("SP002"));
//        System.out.println(supplier.isExistSupplier("SP002"));
        System.out.println(supplier.deleteSupplier("SP007"));
//        System.out.println(supplier.addSupplier("Amirzan Fikri Prasetyo", "086732905428", "Jombang, Jawa Timur"));
//        System.out.println(supplier.getNama("SP002"));
    }
}
