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
public class ManageTransaksiJual extends Database{
    
    private enum TRJ{
        ID_TR_JUAL, NAMA_TR_JUAl, ID_PETUGAS, ID_PEMBELI, ID_BARANG, 
        JUMLAH_BRG, METODE_BYR, TOTAL_HRG, TANGGAL
    };
    
    private enum LPJ{
        ID_LAPORAN_PDPTN, NAMA_LAPORAN_PDPTN, ID_TR_JUAL, TANGGAL, TOTAL_HARGA
    };
    
    public final Text txt = new Text();
    
    private final String table = DatabaseTables.TRANSAKSI_JUAL.name();
    
    public ManageTransaksiJual(){
        super.startConnection();
    }
    
    public boolean isExistIDTransaksi(String id){
        // mengecek apakah id transaksi yang diinputkan valid atau tidak
        if(Validation.isIdTransaksi(id)){
            return super.isExistData(this.table, TRJ.ID_TR_JUAL.name(), id);
        }
        // akan menghasilkan error jika id user tidak valid
        throw new InValidUserDataException("'" +id + "' ID tersebut tidak valid.");
    }
    
    protected String getLastIDTransaksi(){
        try{
            String query = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 0,1", this.table, TRJ.ID_TR_JUAL.name());
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getString(TRJ.ID_TR_JUAL.name());
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public String createIDTransaksi(){
        String lastID = this.getLastIDTransaksi(), nomor;
        
        if(!lastID.equals("")){
            nomor = lastID.substring(3);
        }else{
            nomor = "000";
        }
        
        // mengecek nilai dari nomor adalah number atau tidak
        if(txt.isNumber(nomor)){
            // jika id pembayaran belum exist maka id akan 
            return String.format("TRJ%04d", Integer.parseInt(nomor)+1); 
        }
        return null;
    }
    
    public String createIDLaporan(){
        return this.createIDTransaksi().replace("TRJ", "LPD");
    }
    
    public boolean addTransaksiJual(String namaTrJual, String idPetugas, String idPembeli, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        PreparedStatement pst;
        String idTrj = this.createIDTransaksi(), idLaporan = this.createIDLaporan();
        try {
            // validasi data sebelum ditambahkan
            if(this.validateAddTransaksiJual(idTrj, namaTrJual, idPetugas, idPembeli, idBarang, jmlBrg, metodeByr, ttlHarga, tanggal)){
                Log.addLog(String.format("Menambahkan data transaksi jual dengan ID Transaksi '%s' ", idTrj));
                // menambahkan data kedalam Database
                pst = this.conn.prepareStatement("INSERT INTO transaksi_jual VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, idTrj);
                pst.setString(2, namaTrJual);
                pst.setString(3, idPetugas);
                pst.setString(4, idPembeli);
                pst.setString(5, idBarang);
                pst.setInt(6, Integer.parseInt(jmlBrg));
                pst.setString(7, metodeByr);
                pst.setInt(8, Integer.parseInt(ttlHarga));
                pst.setString(9, tanggal);
                
                // mengekusi query
                if(pst.executeUpdate() > 0){
                    // menambahkan laporan pendapatan
                    return this.addLaporanPendapatan(idLaporan, namaTrJual, idTrj, tanggal, ttlHarga);
                }
            }
        } catch (SQLException | InValidUserDataException ex) {
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    private boolean addLaporanPendapatan(String idLaporan, String namaLaporan, String idTrj, String tanggal, String ttlHarga){
        PreparedStatement pst;
        try{
            Log.addLog(String.format("Menambahkan data laporan pendapatan dengan ID Transaksi '%s' ", idTrj));
            pst = this.conn.prepareStatement("INSERT INTO laporan_pendapatan VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, idLaporan);
            pst.setString(2, namaLaporan);
            pst.setString(3, idTrj);
            pst.setString(4, tanggal);
            pst.setInt(5, Integer.parseInt(ttlHarga));
            
            return pst.executeUpdate() > 0;
        }catch(SQLException ex){
            System.out.println("Error Message : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean validateAddTransaksiJual(String idTrj, String namaTrJual, String idPetugas, String idPembeli, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        boolean vIdTrj, vNamaTrJual = true, vIdPetugas, vIdPembeli, vIdBarang, vJmlBrg, vMetodeByr, vTtlHarga, vTanggal;
        
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
        
        // mengecek apakah id pembeli valid atau tidak
        if(Validation.isIdPembeli(idPembeli)){
            vIdPembeli = true;
        }else{
            throw new InValidUserDataException("'" + idPembeli + "'ID Pembeli tersebut tidak valid.");
        }
        
        // mengecek apakah id barang valid atau tidak
        if(Validation.isIdBarang(idBarang)){
            vIdBarang = true;
        }else{
            throw new InValidUserDataException("'" + idBarang + "' ID barang tersebut tidak valid.");
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
        
        return vIdTrj && vNamaTrJual && vIdPetugas && vIdPembeli && vIdBarang && vJmlBrg && vMetodeByr && vTtlHarga && vTanggal;
    }
    
    public boolean deleteTransaksiJual(String idTrj){
        return super.deleteData(this.table, TRJ.ID_TR_JUAL.name(), idTrj);
    }
    
    private String getData(String idTrj, TRJ data){
        // mengecek apakah id transaksi exist atau tidak
        if(this.isExistIDTransaksi(idTrj)){
            // mendapatkan data dari transaksi jual
            return this.getData(this.table, data.name(), " WHERE "+ TRJ.ID_TR_JUAL.name() +" = '" + idTrj +"'");
        }
        // akan menghasilkan error jika id transaksi tidak ditemukan
        throw new InValidUserDataException("'" +idTrj + "' ID Transaksi Jual tersebut tidak dapat ditemukan.");   
    }
    
    public String getNamaTransaksi(String idTrj){
        return this.getData(idTrj, TRJ.NAMA_TR_JUAl);
    }
    
    public String getIdPetugas(String idTrj){
        return this.getData(idTrj, TRJ.ID_PETUGAS);
    }
    
    public String getIdPembeli(String idTrj){
        return this.getData(idTrj, TRJ.ID_PEMBELI);
    }
    
    public String getIdBarang(String idTrj){
        return this.getData(idTrj, TRJ.ID_BARANG);
    }
    
    public int getJumlahBarang(String idTrj){
        return Integer.parseInt(this.getData(idTrj, TRJ.JUMLAH_BRG));
    }
    
    public String getMetodeBayar(String idTrj){
        return this.getData(idTrj, TRJ.METODE_BYR);
    }
    
    public String getTotalHarga(String idTrj){
        return this.getData(idTrj, TRJ.TOTAL_HRG);
    }
    
    public String getTanggal(String idTrj){
        return this.getData(idTrj, TRJ.TANGGAL);
    }
    
    private boolean setData(String idTrj, TRJ data, String newValue){
        Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari transaksi jual dengan ID Transaksi '" + idTrj + "'.");
        // mengecek apakah id transaksi exist atau tidak
        if(this.isExistIDTransaksi(idTrj)){
            // mengedit data dari transaksi
            return super.setData(this.table, data.name(), TRJ.ID_TR_JUAL.name(), idTrj, newValue);
        }
        // akan menghasilkan error jika id transaksi tidak ditemukan
        throw new InValidUserDataException("'" + idTrj + "' ID Transaksi Jual tersebut tidak dapat ditemukan.");
    }
    
    public boolean setNamaTransaksi(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.NAMA_TR_JUAl, newValue);
    }
    
    public boolean setIdPetugas(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.ID_PETUGAS, newValue);
    }
    
    public boolean setIdPembeli(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.ID_PEMBELI, newValue);
    }
    
    public boolean setIdBarang(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.ID_BARANG, newValue);
    }
    
    public boolean setJumlahBarang(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.JUMLAH_BRG, newValue);
    }
    
    public boolean setMetodeBayar(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.METODE_BYR, newValue);
    }
    
    public boolean setTotalHarga(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.TOTAL_HRG, newValue);
    }
    
    public boolean setTanggal(String idTrj, String newValue){
        return this.setData(idTrj, TRJ.TANGGAL, newValue);
    }
    
    class LaporanPendapatan{
        
        public boolean addLaporanPendapatan(){
            return false;
        }
        
    }
    
    public static void main(String[] args) {
        
        Log.createLog();
        ManageTransaksiJual tr = new ManageTransaksiJual();
//        System.out.println(tr.isExistID("TRJ0002"));
//        System.out.println(tr.getLastID());
//        System.out.println(tr.createID());
        
        String idTrj = "TRJ0002";
//        System.out.println(tr.getNamaTransaksi(idTrj));
//        System.out.println(tr.getIdPetugas(idTrj));
//        System.out.println(tr.getIdPembeli(idTrj));
//        System.out.println(tr.getIdBarang(idTrj));
//        System.out.println(tr.getJumlahBarang(idTrj));
//        System.out.println(tr.getMetodeBayar(idTrj));
//        System.out.println(tr.getTotalBarang(idTrj));
//        System.out.println(tr.getTanggal(idTrj));
//        
//        
//        System.out.println(tr.setNamaTransaksi(idTrj, "Beli 1 dunks"));
//        System.out.println(tr.setIdPetugas(idTrj, "PG003"));
//        System.out.println(tr.setIdPembeli(idTrj, "PB289"));
//        System.out.println(tr.setIdBarang(idTrj, "BG001"));
//        System.out.println(tr.setJumlahBarang(idTrj, "10"));
//        System.out.println(tr.setMetodeBayar(idTrj, "CASH"));
//        System.out.println(tr.setTotalBarang(idTrj, "120000"));
//        System.out.println(tr.setTanggal(idTrj, "2022-10-15"));
        
//        System.out.println(
//                tr.validateAddTransaksiJual("TRJ0001", "Test", "PG001", "PB289", "BG003", "1", "E-WALLET", "10", "2022-08-19")
//        );
        
        boolean isSuccess = tr.addTransaksiJual("sok tau", "PG004", "PB123", "BG011", "9", "E-WALLET", "15000", "2022-10-20");
        System.out.println(isSuccess);
//        System.out.println(tr.deleteTransaksiJual("TRJ0005"));
//        System.out.println(tr.deleteTransaksiJual("TRJ0007"));
    }
    
}
