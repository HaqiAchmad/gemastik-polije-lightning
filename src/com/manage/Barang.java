package com.manage;
import com.data.app.Log;
import com.data.db.Database;
import com.data.db.DatabaseTables;
import com.error.InValidUserDataException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Amirzan Fikri Prasetyo
 */
//public class Barang {
public class Barang extends Database{
    
    private enum BRG{ID_BARANG, NAMA_BARANG, JENIS_BARANG, STOK, HARGA_BELI, HARGA_JUAL};
    
    private final Text txt = new Text();
    
    public Barang(){
        super.startConnection();
    }
    
    public String createID(){
        String lastID = this.getLastID(), nomor;
        
        if(lastID != null){
            nomor = lastID.substring(2);
        }else{
            nomor = "000";
        }
        
        // mengecek nilai dari nomor adalah number atau tidak
        if(txt.isNumber(nomor)){
            // jika id barang belum exist maka id akan 
            return String.format("BG%03d", Integer.parseInt(nomor)+1);
        }
        return null;
    }
    
    public boolean isExistBarang(String idBarang){
        // mengecek apakah id barang yang diinputkan valid atau tidak
        if(Validation.isIdUser(idBarang)){
            return super.isExistData(DatabaseTables.BARANG.name(), BRG.ID_BARANG.name(), idBarang);
        }
        // akan menghasilkan error jika id barang tidak valid
        throw new InValidUserDataException("'" +idBarang + "' ID tersebut tidak valid.");
    }
    
    protected String getLastID(){
        try{
            String query = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 0,1", DatabaseTables.BARANG.name(), BRG.ID_BARANG.name());
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getString(BRG.ID_BARANG.name());
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public final boolean addBarang(String namaBarang, String jenis, String jumlah, String hargaBeli, String hargaJual){
        PreparedStatement pst;
        String idBarang = this.createID();
        try {
            // validasi data sebelum ditambahkan
            if(this.validateAddBarang(idBarang, namaBarang, jenis, jumlah, hargaBeli, hargaJual)){
                Log.addLog("Menambahkan data barang dengan nama '" + namaBarang + "'");
                // menambahkan data kedalam Database
                pst = this.conn.prepareStatement("INSERT INTO barang VALUES (?, ?, ?, ?, ?, ?)");
                pst.setString(1, idBarang);
                pst.setString(2, txt.toCapitalize(namaBarang));
                pst.setString(3, jenis.toUpperCase());
                pst.setInt(4, Integer.parseInt(jumlah));
                pst.setInt(5, Integer.parseInt(hargaBeli));
                pst.setInt(6, Integer.parseInt(hargaJual));
                // mengekusi query
                return pst.executeUpdate() > 0;
            }
        } catch (SQLException | InValidUserDataException ex) {
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean validateAddBarang(String idBarang, String namaBarang, String jenis, String jumlah, String hargaBeli, String hargaJual){
        
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
                
        // mengecek apakah jenis barang valid atau tidak
        if(Validation.isJenisBarang(jenis)){
            vJenis = true;
        }else{
            throw new InValidUserDataException("'" + jenis + "' Jenis tersebut tidak valid.");
        }
                
        // mengecek apakah jumlah valid atau tidak
        if(Validation.isJumlahBarang(jumlah)){
            vJumlah = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Jumlah tersebut tidak valid.");
        }
        
        // mengecek apakah harga beli valid atau tidak
        if(Validation.isHargaBeli(hargaBeli)){
            vHargaBeli = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Harga beli tersebut tidak valid.");
        }
        
        // mengecek apakah harga jual valid atau tidak
        if(Validation.isHargaJual(hargaJual, hargaBeli)){
            vHargaJual = true;
        }else{
            throw new InValidUserDataException("'" + jumlah + "' Harga jual tersebut tidak valid.");
        }
                
        return vIdPembeli && vNama && vJenis && vJumlah && vHargaBeli && vHargaJual;
    }
    
    public boolean deleteBarang(String idBarang){
        Log.addLog("Menghapus barang dengan ID '" + idBarang + "'.");
        return this.deleteData(DatabaseTables.BARANG.name(), BRG.ID_BARANG.name(), idBarang);
    }
    
    private String getDataBarang(String idBarang, BRG data){
        // mengecek apakah id barang exist atau tidak
        if(this.isExistBarang(idBarang)){
            // mendapatkan data dari barang
            return this.getData(DatabaseTables.BARANG.name(), data.name(), " WHERE "+ BRG.ID_BARANG.name() +" = '" + idBarang +"'");
        }
        // akan menghasilkan error jika id barang tidak ditemukan
        throw new InValidUserDataException("'" +idBarang + "' ID Barang tersebut tidak dapat ditemukan.");   
    }
    
    public String getNamaBarang(String idBarang){
        return this.getDataBarang(idBarang, BRG.NAMA_BARANG);
    }
    
    public String getJenis(String idBarang){
        return this.getDataBarang(idBarang, BRG.JENIS_BARANG);
    }
    
    public String getStok(String idBarang){
        return this.getDataBarang(idBarang, BRG.STOK);
    }
    
    public String getHargaBeli(String idBarang) {
        return this.getDataBarang(idBarang, BRG.HARGA_BELI);
    }

    public String getHargaJual(String idBarang) {
        return this.getDataBarang(idBarang, BRG.HARGA_JUAL);
    }
    
    private boolean setDataBarang(String idBarang, BRG data, String newValue){
        Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari barang dengan ID User '" + idBarang + "'.");
        // mengecek apakah id barang exist atau tidak
        if(this.isExistBarang(idBarang)){
            // mengedit data dari barang
            return this.setData(DatabaseTables.BARANG.name(), data.name(), BRG.ID_BARANG.name(), idBarang, newValue);
        }
        // akan menghasilkan error jika id barang tidak ditemukan
        throw new InValidUserDataException("'" +idBarang + "' ID Barang tersebut tidak dapat ditemukan.");
    }
    
    public boolean setNamaBarang(String idBarang, String newNama){
        return this.setDataBarang(idBarang, BRG.NAMA_BARANG, newNama);
    }
    
    public boolean setJenis(String idBarang, String newJenis){
        return this.setDataBarang(idBarang, BRG.JENIS_BARANG, newJenis);
    }
    
    public boolean setStok(String idBarang, String newJumlah){
        return this.setDataBarang(idBarang, BRG.STOK, newJumlah);
    }
    
    public boolean setHargaBeli(String idBarang, String newHargaBeli){
        return this.setDataBarang(idBarang, BRG.HARGA_BELI, newHargaBeli);
    }
    public boolean setHargaJual(String idBarang, String newHargaJual){
        return this.setDataBarang(idBarang, BRG.HARGA_JUAL, newHargaJual);
    }
    
    public static void main(String[] args) {
        
        Log.createLog();
        Barang barang = new Barang();
        
//        System.out.println(barang.isExistBarang("BG017"));
//        System.out.println(barang.getLastID());
//        System.out.println(barang.createID());
        
//        System.out.println(barang.addBarang("Buku Sidu", "atk", 18, 3_500, 4_500));
//        System.out.println(barang.deleteBarang("BG017"));
        
//        String id = "BG017";
//        System.out.println(barang.getNamaBarang(id));
//        System.out.println(barang.getJenis(id));
//        System.out.println(barang.getStok(id));
//        System.out.println(barang.getHargaBeli(id));
//        System.out.println(barang.getHargaJual(id));
        
//        System.out.println(barang.setNamaBarang(id, "Beng Beng"));
//        System.out.println(barang.setJenis(id, "snack"));
//        System.out.println(barang.setStok(id, "15"));
//        System.out.println(barang.setHargaBeli(id, "1500"));
//        System.out.println(barang.setHargaJual(id, "2000"));
    }
}