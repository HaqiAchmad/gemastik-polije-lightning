package com.data.db;

import com.data.app.Log;
import com.manage.Message;
import com.media.Audio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Class ini digunakan untuk menghubungkan dan memutuskan koneksi ke <strong>Database</strong>, membackup <strong>Database</strong> dan menangani masalah yang terjadi pada <strong>Database.</strong>
 * Kondisi seperti <strong>CRUD</strong> tidak tersedia di class ini. Kondisi <strong>CRUD</strong> tersedia di class <code>Account</code> dan <code>CovidCases</code>.
 * <BR><BR>
 * <strong>Database</strong> pada aplikasi ini sebagian besar menggunakan <strong>MySQL Database</strong> dan sisanya menggunakan <strong>Database Text</strong>.
 * 
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-14
 */
public class Database {
    
    /**
     * Object ini digunakan untuk membangun koneksi dengan <B>Database</B>
     */
    public Connection conn;
    /**
     * Digunakan untuk mengeksekusi query MySQL
     */
    public Statement stat;
    /**
     * Digunakan untuk mengambil output dari eksekusi query
     */
    public ResultSet res;

    /**
     * Digunakan untuk menyimpan query sql
     */
    private String sql;
    /**
     * Attribute yang digunakan untuk menhubungkan Aplikasi ke <B>Database MySQL</B>
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver",
                                DB_NAME = "gemastik_lightning",
                                URL = "jdbc:mysql://localhost/" + DB_NAME,
                                USER = "root",
                                PASS = "";
    
    /**
     * Digunakan untuk menghubungkan aplikasi ke <B>Database</B>. 
     * <BR>
     * Pertama-tama method akan meregrister Driver yang dipakai.
     * Driver yang dipakai di aplikasi ini adalah <B>MySQL Driver</B>. Selanjutnya method akan melakukan koneksi ke <B>Database</B>.
     * Setelah berhasil terkoneksi ke <B>Database</B> method akan membuat object <code>Statement</code>
     * <BR><BR>
     * Terdapat dua exception yang mungkin akan terjadi di method ini yaitu <code>ClassNotFoundException</code> dan <code>SQLException</code>.
     * Exception akan ditangani dengan mendapatkan pesan error dari method <code>getMessage</code> pada kedua exception tersebut.
     * Beberapa pesan error yang dapat ditanggani di method ini antara lain: 
     * <UL>
     * <LI> <B>com.mysql.jdbc.Driver : </B> pesan error ini berarti aplikasi tidak dapat menemukan Driver yang akan dipakai untuk menkoneksikan aplikasi ke <B>Database</B>. 
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Communications link failure : </B> pesan error ini bearti MySQL pada Komputer user belum diaktifkan. 
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Access denied for user 'root'@'localhost' (using password: YES) : </B> pesan error ini bearti username atau password pada MySQL di komputer user tidak cocok dengan username dan password yang ada di Aplikasi ini.
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Unknown database" : </B> pesan error ini bearti bahwa database MySQL aplikasi ini tidak ada di komputer user. 
     *      Method akan memulihkan database secara otomatis dengan method <code>restoreDatabase()</code> jika mendapatkan pesan error ini.
     * </UL>
     * <B>Note : </B> Jika pesan error tidak dikenali maka aplikasi akan keluar sendiri.
     */
    public void startConnection(){
        try{
            // meregristrasi driver
            Class.forName(DRIVER); 
            // membuat koneksi
            conn = DriverManager.getConnection(URL, USER, PASS);
            // membuat object statement
            stat = conn.createStatement(); 
            
            Log.addLog(String.format("Berhasil terhubung ke Database '%s'", DB_NAME));
        }catch(ClassNotFoundException | SQLException ex){
            // Menanggani exception yang terjadi dengan cara mendapatkan pesan error dari exception tersebut.
            if(ex.getMessage().contains("com.mysql.jdbc.Driver")){
                Message.showException(this, "MySQL Connector tidak dapat ditemukan", ex, true);
            }else if(ex.getMessage().contains("Communications link failure")){
                Message.showException(this, "Sepertinya MySQL Anda belum diaktifkan!! \nSilahkan aktifkan MySQL Anda dan buka kembali Aplikasi!!", ex, true);
            }else if(ex.getMessage().contains("Access denied for user")){
                Message.showException(this, "Maaf untuk membuka aplikasi ini \nUsername dan password dari MySQL anda harus diatur ke default!\nMohon maaf atas ketidaknyamanan ini!", ex, true);
            }else if(ex.getMessage().contains("Unknown database")){
                Message.showException(this, "Tidak dapat menemukan database '"+DB_NAME+"'\nSilahkan melakukan import Database secara manual dan buka kembali Aplikasi!", ex, true);
            }else{
                Message.showException(this, "Terjadi Kesalahan!\nError message : " + ex.getMessage(), ex, true);
            }
            System.exit(1);
        }
    }
    
    /**
     * Digunakan untuk menutup koneksi dari <B>Database</B> MySQL. Koneksi dari <B>Database</B> perlu ditutup jika sudah 
     * tidak digunakan lagi. Sebelum menutup koneksi dari <B>Database</B> method akan mengecek apakah object {@code Connection},
     * {@code Statement} dan {@code ResultSet} kosong atau tidak. Jika tidak maka koneksi dari <B>Database</B> akan ditutup. 
     * Jika tidak dicek kosong atau tidaknya object maka saat objek kosong lalu dipaksa untuk menutup koneksi dari <B>Database</B>
     * maka akan menimbulkan exception {@code NullPointerException}.
     */
    public void closeConnection(){
        try{
            // Mengecek apakah conn kosong atau tidak, jika tidak maka akan diclose
            if(conn != null){
                conn.close();
            }
            // Mengecek apakah stat kosong atau tidak, jika tidak maka akan diclose
            if(stat != null){
                stat.close();
            }
            // Mengecek apakah res koson atau tidak, jika tidak maka akan diclose
            if(res != null){
                res.close();
            }
            
        Log.addLog(String.format("Berhasil memutus koneksi dari Database '%s'.", DB_NAME));
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public int getJumlahData(String tabel, String kondisi){
        try{
            String query = "SELECT COUNT(*) AS total FROM " + tabel + " " + kondisi;
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getInt("total");
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), ex, true);
        }
        return -1;
    }
    
    public int getJumlahData(String tabel){
        return this.getJumlahData(tabel, "");
    }
    
    public int sumData(String tabel, String field, String kondisi){
        try{
            String query = "SELECT SUM("+field+") AS total FROM " + tabel + " " + kondisi;
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getInt("total");
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), ex, true);
        }
        return -1;
    }
    
    public boolean isExistData(String tabel, String field, String data){
        try{
            String query = "SELECT * FROM " + tabel + " WHERE " + field + " = '" + data + "'";
            res = stat.executeQuery(query);
            return res.next();
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public boolean addData(String query){
        try{
            int result = stat.executeUpdate(query);
            return result > 0;
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public String getData(String table, String field, String kondisi){
        try{
            sql = "SELECT "+field+" FROM "+table + " " + kondisi;
            res = stat.executeQuery(sql);
            if(res.next()){
                return res.getString(field);
            }
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
        return "null";
    }
    
    public String getData(String table, String field){
        return getData(table, field, "");
    }
    
    /**
     * digunakan untuk mendapatkan fields yang berasal dari param field pada method getData
     * 
     * @param fields
     * @return 
     */
    private String getMultipleFields(String fields[]){
        String field = "";
        for (String buff : fields) {
            field += buff + ", ";
        }
        // membuang tanda koma diakhir String
        return field.substring(0, field.length()-2);
    }
    
    public Object[][] getData(final String tabel, final String[] fields, final String kondisi){
        try{
            Object[][] obj;
            int rows = 0;
            sql = "SELECT " + getMultipleFields(fields) + " FROM " + tabel + " " + kondisi;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[getJumlahData(tabel, kondisi)][fields.length];
            // mengeksekusi query
            res = stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(res.next()){
                // menyimpan data dari tabel ke object
                for (int i = 0; i < fields.length; i++) {
                    obj[rows][i] = res.getString(i+1);
                }
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
    public Object[][] getData(final String tabel, final String[] fields){
        return this.getData(tabel, fields, "");
    }
    
    public boolean setData(String tabel, String field, String oldValue, String newValue){
        return this.setData(tabel, field, field, oldValue, newValue);
    }
    
    public boolean setData(String tabel, String field, String spesifikasi, String oldValue, String newValue){
        try{
            sql = "UPDATE "+tabel+" SET " + field + " = '" + newValue + "' WHERE " + spesifikasi + " = '" + oldValue + "'";
            int result = stat.executeUpdate(sql);
            return result > 0;
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public boolean deleteData(String tabel, String field, String value){
        try{
            sql = "DELETE FROM " + tabel + " WHERE " + field + " = '" + value + "'";
            int result = stat.executeUpdate(sql);
            return result > 0;
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }    
    
}
