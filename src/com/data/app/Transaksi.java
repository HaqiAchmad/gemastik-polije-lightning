package com.data.app;

import com.data.db.Database;
import com.manage.Message;
import com.manage.Validation;
import com.manage.Text;
import com.media.Audio;
import com.manage.Waktu;
import com.users.Users;

import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-15
 */
public class Transaksi extends SPP{
    
    public Transaksi(){
        super.startConnection();
    }
    
    private final Users user = new Users();
    
    private final Waktu waktu = new Waktu();
    private final Text txt = new Text();
    
    public String addRp(long nominal){
        return txt.toMoneyCase(Long.toString(nominal));
    }
    
    private String getLastID(){
        try{
            String query = "SELECT * FROM pembayaran ORDER BY id_pembayaran DESC LIMIT 0,1";
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getString("id_pembayaran");
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private boolean isExistID(String idPembayaran){
        return super.isExistData(Database.PEMBAYARAN, "id_pembayaran", idPembayaran);
    }
    
    public String createID(){
        String nomor = this.getLastID().substring(2);
        // mengecek nilai dari nomor adalah number atau tidak
        if(txt.isNumber(nomor)){
            // jika id pembayaran belum exist maka id akan 
            if(!this.isExistID(nomor)){
                return String.format("TR%06d", Integer.parseInt(nomor)+1);
            }
        }
        return null;
    }
    
    private String getDataPembayaran(String idPembayaran, String data){
        return super.getData(Database.PEMBAYARAN, data, "WHERE id_pembayaran = '" + idPembayaran + "'");
    }
    
    public String getNis(String idPembayaran){
        return this.getDataPembayaran(idPembayaran, "nis");
    }
    
    @Deprecated
    public String getNamaSiswa(String idPembayaran){
//        return txt.toCapitalize(this.siswa.getNama(this.getNis(idPembayaran)));
        return null;
    }
    
    public String getIdPetugas(String idPembayaran){
        return this.getDataPembayaran(idPembayaran, "id_petugas");
    }
    
    @Deprecated
    public String getNamaPetugas(String idPembayaran){
//        return txt.toCapitalize(this.petugas.getNama(this.getIdPetugas(idPembayaran)));
        return null;
    }
    
    public String getBulanBayar(String idPembayaran){
        return this.getDataPembayaran(idPembayaran, "bln_bayar");
    }
    
    public String getTahunBayar(String idPembayaran){
        return this.getDataPembayaran(idPembayaran, "thn_bayar");
    }
    
    public String getJumlahBayar(String idPembayaran){
        return txt.toMoneyCase(this.getDataPembayaran(idPembayaran, "jml_bayar"));
    }
    
    public String getTanggalBayar(String idPembayaran){
        return txt.toDateCase(this.getDataPembayaran(idPembayaran, "tgl_bayar"));
    }
    
    public String getIdSpp(String idPembayaran){
        return this.getDataPembayaran(idPembayaran, "id_spp");
    }
    
    @Deprecated
    public boolean bayarSpp(int idPetugas, int nis, String bulan, int tahun, int nominal){  
        
        if(Validation.isValidPembayaran(bulan, tahun)){
            if(!this.isLunas(nis, bulan, tahun)){
                int kekurangan = this.getKekuranganSppBySiswa(nis, bulan, tahun);
                if(nominal >= 0){
                    if(nominal <= kekurangan){
                        String query = "INSERT INTO pembayaran VALUES " + 
                                String.format("('%s', '%d', '%d', '%s', '%d', '%d' , '%s', '%s')", 
//                                        createID(), idPetugas, nis, bulan, tahun, nominal, waktu.getCurrentDate(), siswa.getIdSpp(Integer.toString(nis)));
                                        createID(), idPetugas, nis, bulan, tahun, nominal, waktu.getCurrentDate(), "19");
                        return this.addData(query);
                    }else{
                        Audio.play(Audio.SOUND_WARNING);
                        JOptionPane.showMessageDialog(null, "Uang Anda kelebihan!", "Pesan!", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }                    
                }else{
                    Audio.play(Audio.SOUND_WARNING);
                    JOptionPane.showMessageDialog(null, "Uang Anda Harus Lebih Dari Rp. 0.00 Untuk Mebayar SPP!", "Pesan!", JOptionPane.WARNING_MESSAGE);
                    return false;                    
                }
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "SPP siswa ini sudah lunas!", "Pesan!", JOptionPane.WARNING_MESSAGE);
                return true;
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Pembayaran Dibatalkan!", "Pesan!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean batalkanPembayaran(int nis, String bulan, int tahun){
        try{
            String query = String.format(
                    "DELETE FROM pembayaran WHERE nis = %d AND bln_bayar = '%s' AND thn_bayar = %d", nis, bulan, tahun
            );
            int result = stat.executeUpdate(query);
            if(result > 0){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Gagal Membatalkan Pembayaran!", "Pesan!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public int getTotalTransaksi(){
        return super.getJumlahData(Database.PEMBAYARAN);
    }
    
    private int getTotalTransaksi0(String kondisi){
        try{
            // membuat query
            String query = "SELECT COUNT(id_pembayaran) AS TOTAL "+
                           "FROM pembayaran " + kondisi,
                   buffer;
            // mengeksekusqi query
            res = stat.executeQuery(query);
            // mengecek query memiliki output atau tidak
            if(res.next()){
                // mendapatkan output
                buffer = res.getString("TOTAL");
                // mengecek apakah output merupakan number atau tidak
                if(txt.isNumber(buffer)){
                    return Integer.parseInt(buffer);
                }
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        // mengembalikan nilai default
        return 0;
    }
    
    public int getTotalTransaksi(int idSpp){
        return this.getTotalTransaksi0("WHERE id_spp = " + idSpp);
    }
    
    public int getTotalTransaksiPetugas(int idPetugas){
        return this.getTotalTransaksi0("WHERE id_petugas = " + idPetugas);
    }
    
    public int getTotalTransakiSiswa(int nis){
        return this.getTotalTransaksi0("WHERE nis = " + nis);
    }
    
    @Override
    public void closeConnection(){
        super.closeConnection();
        user.closeConnection();
    }
    
    public static void main(String[] args) {
        
        Log.createLog();
        Transaksi tr = new Transaksi();
        
        System.out.println();
        String idPembayaran = "TR019010";
        System.out.println(tr.getNis(idPembayaran));
        System.out.println(tr.getNamaSiswa(idPembayaran));
        System.out.println(tr.getIdPetugas(idPembayaran));
        System.out.println(tr.getNamaPetugas(idPembayaran));
        System.out.println(tr.getBulanBayar(idPembayaran));
        System.out.println(tr.getTahunBayar(idPembayaran));
        System.out.println(tr.getJumlahBayar(idPembayaran));
        System.out.println(tr.getTanggalBayar(idPembayaran));
        System.out.println(tr.getIdSpp(idPembayaran));
        
        
        
    }
    
    
}
