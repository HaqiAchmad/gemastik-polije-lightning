package com.users;
import com.data.app.Log;
import com.error.InValidUserDataException;
import com.manage.Text;
import com.manage.Validation;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Windows
 */
//public class Barang {
public class Barang extends Users{
    
    private final Text text = new Text();
    
    public String createID(){
        return super.createID(UserLevels.BARANG, UserData.ID_BARANG);
    }
    
    public boolean isExistBarang(String idBarang){
        return super.isExistID(idBarang, UserLevels.BARANG, UserData.ID_BARANG);
    }
    
    public final boolean addBarang(String namaBarang, String jenis, int jumlah, int hargaBeli, int hargaJual){
        boolean isAdd;
        PreparedStatement pst;
        String idBarang = this.createID();
//        try {
//            // menambahkan data user ke tabel user
//            isAdd = super.addUser(idBarang, "12345", UserLevels.BARANG);
//            // mengecek apakah id user sudah ditambahkan ke tabel user
//            if(isAdd){
//                // validasi data sebelum ditambahkan
//                if(this.validateAddBarang(idBarang, namaBarang, jenis, jumlah, hargaBeli, hargaJual)){
//                    Log.addLog("Menambahkan data pembeli dengan nama '" + namaBarang + "'");
//                    // menambahkan data kedalam Database
//                    pst = this.conn.prepareStatement("INSERT INTO pembeli VALUES (?, ?, ?, ?)");
//                    pst.setString(1, idBarang);
//                    pst.setString(2, text.toCapitalize(namaBarang));
//                    pst.setString(3, text.toCapitalize(jenis));
//                    pst.setString(4, Integer.toString(jumlah));
//                    pst.setString(5, Integer.toString(hargaBeli));
//                    pst.setString(6, Integer.toString(hargaJual));
//
//                    // mengekusi query
//                    return pst.executeUpdate() > 0;
//                }
//            }
//        } catch (SQLException | InValidUserDataException ex) {
//            this.deleteUser(idBarang);
//            System.out.println("Error Message : " + ex.getMessage());
//        }
        return false;
    }
    
    public boolean validateAddBarang(String idBarang, String namaBarang, String jenis, int jumlah, int hargaBeli, int hargaJual){
        
        boolean vIdPembeli, vNama, vJenis, vJumlah, vHargaBeli, vHargaJual;
        
        // mengecek id pembeli valid atau tidak
        if(Validation.isIdBarang(idBarang)){
            vIdPembeli = true;
        }else{
            throw new InValidUserDataException("'" + idBarang + "' ID Barang tersebut tidak valid.");
        }
        
        // menecek nama valid atau tidak
        if(Validation.isNamaBarang(namaBarang)){
            vNama = true;
        }else{
            throw new InValidUserDataException("'" + namaBarang + "' Nama Pembeli tersebut tidak valid.");
        }
                
        // mengecek apakah no hp valid atau tidak
        if(Validation.isJenisBarang(jenis)){
            vJenis = true;
        }else{
            throw new InValidUserDataException("'" + jenis + "' Jenis tersebut tidak valid.");
        }
                
        // mengecek apakah alamat valid atau tidak
        if(Validation.isJumlahBarang(jumlah)){
            vJumlah = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Jumlah tersebut tidak valid.");
        }
        if(Validation.isHargaBeli(hargaBeli)){
            vHargaBeli = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Jumlah tersebut tidak valid.");
        }
        if(Validation.isHargaJual(hargaJual, hargaBeli)){
            vHargaJual = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Jumlah tersebut tidak valid.");
        }
                
        return vIdPembeli && vNama && vJenis && vJumlah && vHargaBeli && vHargaJual;
    }
    
    public boolean deleteBarang(String idBarang){
        return super.deleteUser(idBarang);
    }
    
    private String getDataBarang(String idBarang, UserData data){
        return super.getUserData(idBarang, UserLevels.BARANG, data, UserData.ID_BARANG);
    }
    
    public String getNama(String idBarang){
        return this.getDataBarang(idBarang, UserData.NAMA_BARANG);
    }
    
    public String getJenis(String idBarang){
        return this.getDataBarang(idBarang, UserData.JENIS);
    }
    
    public String getJumlah(String idBarang){
        return this.getDataBarang(idBarang, UserData.JUMLAH);
    }
    public String getHargaBeli(String idBarang){
        return this.getDataBarang(idBarang, UserData.HARGA_BELI);
    }
    public String getHargaJual(String idBarang){
        return this.getDataBarang(idBarang, UserData.HARGA_JUAL);
    }
    
    private boolean setDataBarang(String idBarang, UserData data, String newValue){
        return super.setUserData(idBarang, UserLevels.BARANG, data, UserData.ID_BARANG, newValue);
    }
    
    public boolean setNama(String idBarang, String newNama){
        return this.setDataBarang(idBarang, UserData.NAMA_BARANG, newNama);
    }
    
    public boolean setJenis(String idBarang, String newJenis){
        return this.setDataBarang(idBarang, UserData.JENIS, newJenis);
    }
    public boolean setJumlah(String idBarang, String newJumlah){
        return this.setDataBarang(idBarang, UserData.JUMLAH, newJumlah);
    }
    
    public boolean setHargaBeli(String idBarang, String newHargaBeli){
        return this.setDataBarang(idBarang, UserData.HARGA_BELI, newHargaBeli);
    }
    public boolean setHargaJual(String idBarang, String newHargaJual){
        return this.setDataBarang(idBarang, UserData.HARGA_JUAL, newHargaJual);
    }
    
    public static void main(String[] args) {
        
        Log.createLog();
        Pembeli pembeli = new Pembeli();

    }
}