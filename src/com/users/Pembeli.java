package com.users;

import com.data.app.Log;
import com.manage.Text;


/**
 *
 * @author Achmad Baihaqi
 */
public class Pembeli extends Users{
    
    public String createID(){
        return super.createID(UserLevels.PEMBELI, UserData.ID_PEMBELI);
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
//        System.out.println(pembeli.setNama("PB289", "Baihaqi"));
//        System.out.println(pembeli.setNoTelp("PB289", "088888888888"));
//        System.out.println(pembeli.setAlamat("PB289", "Nganjuk"));
//        System.out.println("");
//        System.out.println(pembeli.getNama("PB289"));
//        System.out.println(pembeli.getNoTelp("PB289"));
//        System.out.println(pembeli.getAlamat("PB289"));
        
//        System.out.println(pembeli.getLastID());
        System.out.println(pembeli.createID());
        
    }
}
