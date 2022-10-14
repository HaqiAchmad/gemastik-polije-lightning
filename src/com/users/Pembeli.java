package com.users;

import com.data.app.Log;

/**
 *
 * @author Achmad Baihaqi
 */
public class Pembeli extends Users{
    
    public String getLastID(){
        return null;
    }
    
    public String createID(){
        return null;
    }
    
    public boolean isExistPembeli(String idPembeli){
        return false;
    }
    
    public boolean addPembeli(String idPembeli, String namaPembeli, String noTelp, String alamat){
        return false;
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
    
    public boolean setNama(String idPembeli, String newNama){
        return false;
    }
    
    public boolean setNoTelp(String idPembeli, String newNoTelp){
        return false;
    }
    
    public boolean setAlamat(String idPembeli, String newAlamat){
        return false;
    }
    
    
    public static void main(String[] args) {
        
        Log.createLog();
        Pembeli pembeli = new Pembeli();
        System.out.println(pembeli.getNama("PB289"));
        System.out.println(pembeli.getNoTelp("PB289"));
        System.out.println(pembeli.getAlamat("PB289"));
        
    }
}
