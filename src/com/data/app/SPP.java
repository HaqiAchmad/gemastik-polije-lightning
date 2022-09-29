package com.data.app;

import com.data.db.Database;
import static com.data.db.Database.SISWA;
import static com.data.db.Database.SPP;
import com.error.InValidDataException;
import com.manage.Message;
import com.manage.Text;
import com.manage.Validation;
import com.manage.Waktu;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-06-12
 */
public class SPP extends Database{
    
    private final Waktu waktu = new Waktu();
    private final Text txt = new Text();
    
    public SPP(){
        this.startConnection();
    }
    
    public boolean addDataSpp(int idSpp, int tahun, int nominal) throws SQLException, InValidDataException{
        PreparedStatement pst;
        // mengecek apakah data id spp yang akan ditambahkan valid atau tidak
        if(this.validateAddSpp(idSpp, tahun, nominal)){
            // membuat object PreparedStatement untuk menambahkan data ke database
            pst = super.conn.prepareStatement("INSERT INTO " + Database.SPP + " VALUES (?, ?, ?);");
            // menambahkan data ke object
            pst.setInt(1, idSpp);
            pst.setInt(2, tahun);
            pst.setInt(3, nominal);
            // mengeksekusi query 
            return pst.executeUpdate() > 0;
        }else{
            throw new InValidDataException("Data tidak valid!");
        }
    }
    
    private boolean validateAddSpp(int idSpp, int tahun, int nominal){
        return Validation.isIdSpp(idSpp) && Validation.isTahunSpp(tahun) && Validation.isNominalSpp(nominal);
    }
    
    public boolean hapusDataSpp(int idSpp){
        // jika pengguna spp > 0 maka spp gagal dihapus
        if(this.getPenggunaSpp(idSpp) > 0){
            Message.showWarning(this, "ID SPP ini sedang digunakan oleh "+ this.getPenggunaSpp(idSpp) +" siswa!\nJika anda menghapusnya maka akan mengakibatkan error pada Aplikasi!", true);
        }else{
            return this.deleteData(SPP, "id_spp", Integer.toString(idSpp));
        }
        return false;
    }
    
    public String[] getIDs(){
        try{
            int index = 0;
            // menginisialisasi array seseuai dengan jumlah data pada tabel spp yg ada didalam database
            String[] IDs = new String[super.getJumlahData(Database.SPP)];
            // mengeksekusi query untuk mendapatkan data id spp
            res = stat.executeQuery("SELECT id_spp FROM spp");
            // mendapatkan data id spp
            while(res.next()){
                IDs[index] = res.getString("id_spp");
                index++;
            }
            return IDs;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public int getPenggunaSpp(int idSpp){
        return super.getJumlahData(Database.SISWA, "WHERE id_spp = " + idSpp);
    }
    
    private int getDataSpp(int idSpp, String data){
        return Integer.parseInt(super.getData(Database.SPP, data, "WHERE id_spp = " + idSpp));
    }
    
    private boolean setDataSpp(int idSpp, String data, int newValue){
        return super.setData(Database.SPP, data, "id_spp", Integer.toString(idSpp), Integer.toString(newValue));
    }
    
    public int getTahunSpp(int idSpp){
        return this.getDataSpp(idSpp, "tahun");
    }
    
    public boolean setTahunSpp(int idSpp, int tahun){
        return this.setDataSpp(idSpp, "tahun", tahun);
    }
    
    public int getNominalSpp(int idSpp){
        return this.getDataSpp(idSpp, "nominal");
    }
    
    public int getNominalSppByNis(int nis){
        return this.getNominalSpp(Integer.parseInt(this.getData(SISWA, "id_spp", "WHERE nis = " + nis )));
    }
    
    public boolean setNominalSpp(int idSpp, int nominal){
        return this.setDataSpp(idSpp, "nominal", nominal);
    }
    
    public long getTotalSppDibayar(){
        try{
             String query = "SELECT SUM(jml_bayar) AS TOTAL \n" +
                            "FROM pembayaran";
             // mengeksekusi query
             res = stat.executeQuery(query);
             if(res.next()){
                 return res.getLong("TOTAL");
             }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat menghitung total SPP.\n" + ex.getMessage(), ex, true);
        }
        return -1;
    }
    
    public long getTotalSppDibayar(int idSpp){
        try{
             String query = "SELECT SUM(t.jml_bayar) AS TOTAL \n" +
                            "FROM pembayaran AS t \n" +
                            "INNER JOIN spp AS s ON (t.id_spp = s.id_spp)\n" +
                            "WHERE t.id_spp = " + idSpp,
                    buffer;
             // mengeksekusi query
             res = stat.executeQuery(query);
             // mengecek apakah query memiliki output atau tidak
             if(res.next()){
                 // mendapatkan output
                 buffer = res.getString("TOTAL");
                 // mengecek output adalah sebuah number atau tidak
                 if(txt.isNumber(buffer)){
                     return Long.parseLong(buffer);
                 }
             }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return 0;
    }
    
    public long getTotalSppDibayar(int idSpp, String bulan, int tahun){
        try{
             String query = "SELECT SUM(t.jml_bayar) AS TOTAL \n" +
                            "FROM pembayaran AS t \n" +
                            "INNER JOIN spp AS s ON (t.id_spp = s.id_spp)\n" +
                            String.format("WHERE t.id_spp = %d && t.thn_bayar = %d && t.bln_bayar = '%s'", idSpp, tahun, bulan),
                    buffer;
             // mengeksekusi query
             res = stat.executeQuery(query);
             // mengecek apakah query memiliki output atau tidak
             if(res.next()){
                 // mendapatkan output
                 buffer = res.getString("TOTAL");
                 // mengecek output adalah sebuah number atau tidak
                 if(txt.isNumber(buffer)){
                     return Long.parseLong(buffer);
                 }
             }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return 0;
    }
    
    public int getTotalSppDibayarByKelas(String idKelas, String bulan, int tahun){
        try{
            String  buffer,
                    // membuat query
                    query =  "SELECT \n" +
                            "k.nama_kelas AS NAMA_KELAS, \n" +
                            "SUM(t.jml_bayar) AS TOTAL_SPP\n" +
                            "FROM pembayaran AS t \n" +
                            "INNER JOIN siswa AS s ON(s.nis = t.nis)\n" +
                            "INNER JOIN kelas AS k ON(s.id_kelas = k.id_kelas)\n" +
                            String.format("WHERE k.id_kelas = '%s' AND t.thn_bayar = %d AND t.bln_bayar = '%s';", idKelas, tahun, bulan);
            // mengeksusi query
            res = stat.executeQuery(query);
            // mengecek apakah query memiliki output atau tidak
            if(res.next()){
                // mendapatkan output
                buffer = res.getString("TOTAL_SPP");
                // mengecek output adalah sebuah number atau tidak
                if(txt.isNumber(buffer)){
                    return Integer.parseInt(buffer);
                }
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return 0;
    }
    
    public int getTotalSppDibayarByKelas(String idKelas, String tahunPelajaran){
        // mendapatkan tahun semester 1 dan 2
        int sem1 = Integer.parseInt(tahunPelajaran.substring(0, tahunPelajaran.indexOf("-"))), 
            sem2 = Integer.parseInt(tahunPelajaran.substring(tahunPelajaran.indexOf("-")+1)),
            dibayar = 0;
        
        // menghitung spp dibayar pada semester 1
        for(int bln = 7; bln <= 12; bln++){
            dibayar += this.getTotalSppDibayarByKelas(idKelas, waktu.getNamaBulan(bln-1), sem1);
        }
        // menghitung spp dibayar pada semester 2
        for(int bln = 1; bln <= 6; bln++){
            dibayar += this.getTotalSppDibayarByKelas(idKelas, waktu.getNamaBulan(bln-1), sem2);
        }
        
        // mengembalikan total spp dibayar
        return dibayar;
    }
    
    public int getTotalSppDibayarBySiswa(int nis, String bulan, int tahun){
        try{
            // membuat query
            String query = "SELECT SUM(jml_bayar) AS dibayar FROM pembayaran " + 
                   String.format("WHERE nis = %d AND bln_bayar = '%s' AND thn_bayar = %d", nis, bulan, tahun),
                   buffer;
            // mengeksekussi query
            res = stat.executeQuery(query);
            // mengecek query memiliki output atau tidak
            if(res.next()){
                // mendapatkan output 
                buffer = res.getString("dibayar");
                // mengecek output merupakan number atau tidak
                if(txt.isNumber(buffer)){
                    return Integer.parseInt(buffer);
                }
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat menhitung total pembayaran SPP siswa pada bulan "+bulan+" "+ tahun+"\n" + ex.getMessage(), ex, true);
        }
        return 0;
    }
    
    public int getTotalSppDibayarBySiswa(int nis, String tahunPelajaran){
        // mendapatkan tahun semester 1 dan 2
        int sem1 = Integer.parseInt(tahunPelajaran.substring(0, tahunPelajaran.indexOf("-"))), 
            sem2 = Integer.parseInt(tahunPelajaran.substring(tahunPelajaran.indexOf("-")+1)),
            dibayar = 0;
        
        // menghitung total spp dibayar pada semester 1
        for(int bln = 7; bln <= 12; bln++){
            dibayar += this.getTotalSppDibayarBySiswa(nis, waktu.getNamaBulan(bln-1), sem1);
        }
        // menghitung total spp dibayar pada semester 2
        for(int bln = 1; bln <= 6; bln++){
            dibayar += this.getTotalSppDibayarBySiswa(nis, waktu.getNamaBulan(bln-1), sem2);
        }
        
        // mengembalikan total spp dibayar
        return dibayar;
    }
    
    public int getKekuranganSppBySiswa(int nis, String bulan, int tahun){
        return this.getNominalSppByNis(nis) - this.getTotalSppDibayarBySiswa(nis, bulan, tahun);
    }
    
    public int getKekuranganSppBySiswa(int nis, String tahunPelajaran){
        return (this.getNominalSppByNis(nis) * 12) - this.getTotalSppDibayarBySiswa(nis, tahunPelajaran);
    }
    
    public boolean isLunas(int nis, String bulan, int tahun){
        return this.getTotalSppDibayarBySiswa(nis, bulan, tahun) >= this.getNominalSppByNis(nis);
    }
    
    public boolean isLunas(int nis, String tahunAjaran){
        return this.getTotalSppDibayarBySiswa(nis, tahunAjaran) >= this.getNominalSppByNis(nis);
    }
    
    public int getTotalSiswaLunas(String idKelas, String tahunAjaran){
        try{
            String nis;
            int lunas = 0;
            // mengeksekusi query
            super.res = super.stat.executeQuery("SELECT nis FROM siswa WHERE id_kelas = '" + idKelas + "'");
            // membaca semua data dari hasil eksekusi query
            while(super.res.next()){
                // mendapatkan data nis
                nis = super.res.getString("nis");
                // mengecek siswa lunas atau belum
                if(this.isLunas(Integer.parseInt(nis), tahunAjaran)){
                    lunas++;
                }
            }
            return lunas;
        }catch(SQLException ex){
            Message.showException(this, ex, true);
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Log.createLog();
        SPP spp = new SPP();
        System.out.println(spp.getTotalSiswaLunas("xi.rpl1", Application.getTahunAjaran()));
    }
    
}
