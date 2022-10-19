package com.manage;

import com.data.app.Log;
import com.data.db.Database;
import com.data.db.DatabaseTables;
import com.error.InValidUserDataException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 */
public class TransaksiBeli extends Database{
    private enum TRB{
        ID_TR_BELI, NAMA_TR_BELI, ID_PETUGAS, ID_SUPPLIER, ID_BARANG, 
        JUMLAH_BRG, METODE_BYR, TOTAL_HRG, TANGGAL
    };
    
    private enum LPB{
        ID_LAPORAN_PGLRN, NAMA_LAPORAN_PGLRN, ID_TR_BELI, TANGGAL, TOTAL_HARGA
    };
    
    public final Text txt = new Text();
    
    private final String table = DatabaseTables.TRANSAKSI_BELI.name();
    
    public TransaksiBeli(){
        super.startConnection();
    }
    
    public boolean isExistID(String id){
        // mengecek apakah id transaksi yang diinputkan valid atau tidak
        if(Validation.isIdTransaksi(id)){
            return super.isExistData(this.table, TRB.ID_TR_BELI.name(), id);
        }
        // akan menghasilkan error jika id user tidak valid
        throw new InValidUserDataException("'" +id + "' ID tersebut tidak valid.");
    }
    
    protected String getLastID(){
        try{
            String query = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 0,1", this.table, TRB.ID_TR_BELI.name());
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getString(TRB.ID_TR_BELI.name());
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public String createID(){
        String lastID = this.getLastID(), nomor;
        
        if(!lastID.equals("")){
            nomor = lastID.substring(3);
        }else{
            nomor = "000";
        }
        
        // mengecek nilai dari nomor adalah number atau tidak
        if(txt.isNumber(nomor)){
            // jika id pembayaran belum exist maka id akan 
            return String.format("TRB%04d", Integer.parseInt(nomor)+1); 
        }
        return null;
    }
    
    private String getData(String idTrj, TRB data){
        // mengecek apakah id transaksi exist atau tidak
        if(this.isExistID(idTrj)){
            // mendapatkan data dari transaksi jual
            return this.getData(this.table, data.name(), " WHERE "+ TRB.ID_TR_BELI.name() +" = '" + idTrj +"'");
        }
        // akan menghasilkan error jika id transaksi tidak ditemukan
        throw new InValidUserDataException("'" +idTrj + "' ID Transaksi Beli tersebut tidak dapat ditemukan.");   
    }
    
    public boolean addTransaksiJual(String namaTrJual, String idPetugas, String idSupplier, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        PreparedStatement pst;
        String idTrj = this.createID();
        try {
            // validasi data sebelum ditambahkan
            if(this.validateAddTransaksiJual(idTrj, namaTrJual, idPetugas, idSupplier, idBarang, jmlBrg, metodeByr, ttlHarga, tanggal)){
                Log.addLog(String.format("Menambahkan data transaksi dengan ID Transaksi '%s' ", idTrj));
                // menambahkan data kedalam Database
                pst = this.conn.prepareStatement("INSERT INTO transaksi_beli VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, idTrj);
                pst.setString(2, namaTrJual);
                pst.setString(3, idPetugas);
                pst.setString(4, idSupplier);
                pst.setString(5, idBarang);
                pst.setInt(6, Integer.parseInt(jmlBrg));
                pst.setString(7, metodeByr);
                pst.setInt(8, Integer.parseInt(ttlHarga));
                pst.setString(9, tanggal);
                
                // mengekusi query
                return pst.executeUpdate() > 0;
            }
        } catch (SQLException | InValidUserDataException ex) {
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    private boolean addToLaporan(){
        return false;
    }
    
    public boolean validateAddTransaksiJual(String idTrj, String namaTrJual, String idPetugas, String idSupplier, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        boolean vIdTrj, vNamaTrJual = true, vIdPetugas, vIdSupplier, vIdBarang, vJmlBrg, vMetodeByr, vTtlHarga, vTanggal;
        
        // mengecek id transaksi valid atau tidak
        if(Validation.isIdTransaksi(idTrj)){
            vIdTrj = true;
        }else{
            throw new InValidUserDataException("'" + idTrj + "' ID Transaksi tersebut tidak valid.");
        }
        
        // mengecek apakah id petugas valid atau tidak
        if(Validation.isIdPetugas(idPetugas)){
            vIdPetugas = true;
        }else{
            throw new InValidUserDataException("'" + idPetugas + "'ID Petugas tersebut tidak valid.");
        }
        
        // mengecek apakah id supplier valid atau tidak
        if(Validation.isIdSupplier(idSupplier)){
            vIdSupplier = true;
        }else{
            throw new InValidUserDataException("'" + idSupplier + "'ID Supplier tersebut tidak valid.");
        }
        
        // mengecek apakah id barang valid atau tidak
        if(Validation.isIdBarang(idBarang)){
            vIdBarang = true;
        }else{
            throw new InValidUserDataException("'" + idBarang + "' stersebut tidak valid.");
        }
        
        // mengecek apakah jumlah barang valid atau tidak
        if(Validation.isJumlahBarang(jmlBrg)){
            vJmlBrg = true;
        }else{
            throw new InValidUserDataException("'" + jmlBrg + "'Jumlah barang tersebut tidak valid.");
        }
        
        // mengecek apakah metode bayar valid atau tidak
        if(Validation.isMetodeBayar(metodeByr)){
            vMetodeByr = true;
        }else{
            throw new InValidUserDataException("'" + metodeByr + "'Metode bayar tersebut tidak valid.");
        }
        
        // mengecek apakah total harga valid atau tidak
        if(Validation.isNumber(ttlHarga)){
            vTtlHarga = true;
        }else{
            throw new InValidUserDataException("'" + ttlHarga + "'Total harga tersebut tidak valid.");
        }
        
        // mengecek apakah tanggal valid atau tidak
        if(Validation.isTanggal(tanggal)){
            vTanggal = true;
        }else{
            throw new InValidUserDataException("'" + tanggal + "'Tanggal penjualan tersebut tidak valid.");
        }
        
        return vIdTrj && vNamaTrJual && vIdPetugas && vIdSupplier && vIdBarang && vJmlBrg && vMetodeByr && vTtlHarga && vTanggal;
    }
    
    public boolean deleteTransaksiJual(String idTrj){
        return super.deleteData(this.table, TRB.ID_TR_BELI.name(), idTrj);
    }
    
    public String getNamaTransaksi(String idTrj){
        return this.getData(idTrj, TRB.NAMA_TR_BELI);
    }
    
    public String getIdPetugas(String idTrj){
        return this.getData(idTrj, TRB.ID_PETUGAS);
    }
    
    public String getIdSupplier(String idTrj){
        return this.getData(idTrj, TRB.ID_SUPPLIER);
    }
    
    public String getIdBarang(String idTrj){
        return this.getData(idTrj, TRB.ID_BARANG);
    }
    
    public int getJumlahBarang(String idTrj){
        return Integer.parseInt(this.getData(idTrj, TRB.JUMLAH_BRG));
    }
    
    public String getMetodeBayar(String idTrj){
        return this.getData(idTrj, TRB.METODE_BYR);
    }
    
    public String getTotalBarang(String idTrj){
        return this.getData(idTrj, TRB.TOTAL_HRG);
    }
    
    public String getTanggal(String idTrj){
        return this.getData(idTrj, TRB.TANGGAL);
    }
    
    private boolean setData(String idTrj, TRB data, String newValue){
        Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari transaksi beli dengan ID Transaksi '" + idTrj + "'.");
        // mengecek apakah id transaksi exist atau tidak
        if(this.isExistID(idTrj)){
            // mengedit data dari transaksi
            return super.setData(this.table, data.name(), TRB.ID_TR_BELI.name(), idTrj, newValue);
        }
        // akan menghasilkan error jika id transaksi tidak ditemukan
        throw new InValidUserDataException("'" + idTrj + "' ID Transaksi Beli tersebut tidak dapat ditemukan.");
    }
    
    public boolean setNamaTransaksi(String idTrj, String newValue){
        return this.setData(idTrj, TRB.NAMA_TR_BELI, newValue);
    }
    
    public boolean setIdPetugas(String idTrj, String newValue){
        return this.setData(idTrj, TRB.ID_PETUGAS, newValue);
    }
    
    public boolean setIdSupplier(String idTrj, String newValue){
        return this.setData(idTrj, TRB.ID_SUPPLIER, newValue);
    }
    
    public boolean setIdBarang(String idTrj, String newValue){
        return this.setData(idTrj, TRB.ID_BARANG, newValue);
    }
    
    public boolean setJumlahBarang(String idTrj, String newValue){
        return this.setData(idTrj, TRB.JUMLAH_BRG, newValue);
    }
    
    public boolean setMetodeBayar(String idTrj, String newValue){
        return this.setData(idTrj, TRB.METODE_BYR, newValue);
    }
    
    public boolean setTotalBarang(String idTrj, String newValue){
        return this.setData(idTrj, TRB.TOTAL_HRG, newValue);
    }
    
    public boolean setTanggal(String idTrj, String newValue){
        return this.setData(idTrj, TRB.TANGGAL, newValue);
    }
    
    public static void main(String[] args) {
        
        Log.createLog();
        TransaksiBeli tr = new TransaksiBeli();
        
//        System.out.println(tr.isExistID("TRB0002"));
//        System.out.println(tr.getLastID());
//        System.out.println(tr.createID());
        
        String idTrj = "TRB0002";
//        System.out.println(tr.getNamaTransaksi(idTrj));
//        System.out.println(tr.getIdPetugas(idTrj));
//        System.out.println(tr.getIdSupplier(idTrj));
//        System.out.println(tr.getIdBarang(idTrj));
//        System.out.println(tr.getJumlahBarang(idTrj));
//        System.out.println(tr.getMetodeBayar(idTrj));
//        System.out.println(tr.getTotalBarang(idTrj));
//        System.out.println(tr.getTanggal(idTrj));
//        
//        
//        System.out.println(tr.setNamaTransaksi(idTrj, "Beli 67 dunks"));
//        System.out.println(tr.setIdPetugas(idTrj, "PG001"));
//        System.out.println(tr.setIdSupplier(idTrj, "SP004"));
//        System.out.println(tr.setIdBarang(idTrj, "BG015"));
//        System.out.println(tr.setJumlahBarang(idTrj, "67"));
//        System.out.println(tr.setMetodeBayar(idTrj, "E-WALLET"));
//        System.out.println(tr.setTotalBarang(idTrj, "160000"));
//        System.out.println(tr.setTanggal(idTrj, "2022-03-05"));
        
//        System.out.println(
//                tr.validateAddTransaksiJual("TRB0001", "Test", "PG001", "SP009", "BG003", "1", "E-WALLET", "10", "2022-08-19")
//        );
        
//        boolean isSuccess = tr.addTransaksiJual("sok tau", "PG004", "PB123", "BG011", "9", "E-WALLET", "15000", "2022-10-20");
//        System.out.println(isSuccess);
//        System.out.println(tr.deleteTransaksiJual("TRJ0005"));
//        System.out.println(tr.deleteTransaksiJual("TRJ0007"));
    }
}
