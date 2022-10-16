package com.users;

import com.data.app.Log;
import com.data.app.Storage;
import com.data.db.Database;
import com.data.db.DatabaseTables;
import com.error.AuthenticationException;
import com.error.InValidUserDataException;
import com.manage.FileManager;
import com.manage.Message;
import com.manage.Text;
import com.manage.Validation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.StringTokenizer;


/**
 * Class ini digunakan untuk segala sesuatu yang berhubungan dengan akun dari user seperti memanipulasi atau mendapatkan 
 * data dari akun user. Class ini sangat ketergantungan terhadap <b>Database</b> aplikasi oleh karena itu class ini merupakan 
 * inheritance dari class {@code Database}. Oleh karena itu jika ada kesalahan terhadap <b>Database</b> aplikasi class ini 
 * juga akan mengalami error yang menimbulkan terjadinya force close pada aplikasi.
 * <br><br>
 * Untuk memanipulasi atau mendapatkan data dari akun user class akan memanfaatkan method-method yang ada didalam claas 
 * {@code Database}. Kita hanya perlu menginputkan id user dari akun user untuk memanipulasi atau mendapatkan data dari 
 * akun user. Sebelum memanipulasi atau mendapatkan data dari user class akan mengecek apakah data yang diinputkan 
 * valid atau tidak.
 * <br><br>
 * Pengecekan perlu dilakukan untuk menghindari terjadinya error pada aplikasi saat data yang diinputkan tidak valid.
 * Class ini juga dapat digunakan untuk menambahkan atau menghapus sebuah akun dari <b>Database</b> aplikasi. Cara 
 * kerja class untuk menambahkan atau menghapus sebuah akun dari <b>Database</b> hapir sama dengan cara kerja 
 * memanipulasi atau mendapatkan data dari user.
 * <br><br>
 * Selain itu class juga dapat digunakan untuk melakukan login dan logout pada aplikasi. Untuk login pada aplikasi 
 * user cukup dengan memasukan id user dan password dari akun. Jika id user dan password cocok dengan yang ada di 
 * dldalam <b>Database</b> maka login akan dianggap berhasil. Setelah login berhasil maka class akan menyimpan login 
 * data yang dilakukan oleh user didalam <b>Database</b> dan folder yang ada didalam komputer.
 * <br><br>
 * Untuk logout aplikasi class akan menghapus login data yang ada didalam <b>Database</b> dan yang ada didalam komputer.
 * Jika proses penghapusan login data berhasil maka logout akan dianggap behasil. Selama menggunakan class ini mungkin 
 * akan akan sering menemui runtime/checked exception. 
 * <br><br>
 * Beberapa exception yang mungkin sering anda jumpai adalah {@code AuthenticationException} yang merupakan checked exception.
 * Exception tersebut akan sering dijumpai ketika terjadi kesalahan pada saat proses login atau logout aplikasi. Exception 
 * yang lainya adalah {@code InValidUserDataException}. Exception tersebut akan sering dijumpai saat sedang memanipulasi 
 * atau mendapatkan data dari akun user.
 * <br><br>
 * Exception {@code InValidUserDataException} merupakan sebuah runtime exception. Oleh karena itu disaat akan memanipulasi 
 * atau mendapkan data dari user disarankan untuk membuat block try catch untuk menangkap pesan error dari exception. 
 * Jika tidak ditangkap menggunakan block try catch maka ada kemungkinan aplikasi akan force close.
 * <br><br>
 * Akun user pada aplikasi ini dibagi menjadi 3 level antara lain <i>ADMIN</i>, <i>PETUGAS</i> dan <i>SISWA</i>. 
 * Pembagian diperlukan agar menajemen data pada akun jauh lebih mudah. Data akun dari user yang memiliki level 
 * <i>ADMIN</i> dan <i>PETUGAS</i> akan disimpan pada tabel petugas. Semetara data dari user yang memiliki level 
 * siswa akan disimpan pada tabel <i>SISWA</i>.
 * 
 * @author Achmad Baihaqi
 * @since 2021-06-11
 */
public class Users extends Database{
    
    private Date date;
    
    private final Text txt = new Text();
    
    /**
     * Direktori dari file yang digunakan untuk menyimpan data dari akun yang sedang digunakan untuk login.
     */
    private final String LOGIN_DATA_FILE = new Storage().getUsersDir() + "login_data.haqi";

    /**
     * Merupakan satu-satunya constructor yang ada didalam class {@code Users}. Saat constructor dipanggil saat pembuatan 
     * object constructor akan secara otomatis memanggil method {@code startConnection()} yang ada didalam class 
     * {@code Database} untuk membuat koneksi ke <b>Database MySQL</b>. Setelah membuat koneksi ke <b>Database MySQL</b>
     * constructor akan mengecek apakah folder user storage dari aplikasi, file login_data.haqi dan user storage dari 
     * user yang sedang login apakah exist atau tidak. 
     * <br><br>
     * Jika ada salah satu dari folder atau file tersebut yang tidak exist maka folder dan file tersebut akan dibuat. 
     * Folder dan File tersebut sangatlah penting karena jika tidak ada folder dan file tersebut akan menyebabkan 
     * force close pada aplikasi.
     * <br><br>
     * Constructor dari class {@code Users} ini juga dapat untuk membuat object dari inner class dari class {@code Users} 
     * yaitu {@code LevelPetugas} dan {@code LevelSiswa}. Anda dapat membuat object dari kedua class tersebut dengan 
     * memanggil method {@code levelPetugas()} untuk membuat object dari class {@code LevelPetugas} dan method 
     * {@code levelSiswa()} untuk membuat object dari class {@code LevelSiswa}.
     */
    public Users(){
        // memulai koneksi ke database
        this.startConnection();
        
        // jika storage tidak ditemukan maka akan dibuat
        if(!new File(new Storage().getUsersDir()).exists()){
            new FileManager().createFolders(new Storage().getUsersDir());
        }
        
        // jika file login data tidak ditemukan maka file akan dibuat
        if(!new File(this.LOGIN_DATA_FILE).exists()){
            new FileManager().createFile(this.LOGIN_DATA_FILE);
        }
    }
    
    /**
     * Method ini digunakan untuk menambahkan data dari user yang diinputkan kedalam <b>Database MySQL</b>.
     * Data dari user akan ditambahkan ke dalam <b>Database</b> melalui class {@code PreparedStatement} sehingga 
     * proses menambahkan data kedalam <b>Database</b> lebih aman. Pertama-tama method akan mengecek apakah 
     * semua data dari user valid atau tidak. Jika ada salah satu data dari user yang tidak valid maka data 
     * tidak akan ditambahkan kedalam <b>Database</b> dan method akan mengembalikan nilai <code>false</code>.
     * <br><br>
     * Jika semua data dari user valid maka method akan membuat sebuah object {@code PreparedStatement}. Setelah 
     * object dari class {@code PreparedStatement} berhasil dibuat selanjutnya method akan menambahkan semua data 
     * dari user kedalam object {@code PreparedStatement}. Jika semua data dari user sudah ditambahkan kedalam 
     * object {@code PreparedStatement} maka data dari user tersebut akan ditambahka kedalam <b>Database</b> melalui 
     * method {@code executeUpdate()} yang ada didalam class {@code PreparedStatement}.
     * 
     * @param idUser ID dari user
     * @param pass password dari user
     * @param level level dari user
     * 
     * @return <strong>True</strong> jika data berhasil ditambahkan. <br>
     *         <strong>False</strong> jika data tidak berhasil ditambahkan. 
     * 
     * @throws SQLException jika terjadi kegagalan saat menambahkan data kedalam <b>Database</b>.
     * @throws InValidUserDataException jika data dari petugas tidak valid.
     */
    public final boolean addUser(String idUser, String pass, UserLevels level) throws SQLException, InValidUserDataException{
        Log.addLog("Menambahkan user baru dengan ID User '" + idUser + "' dengan level " + level.name());
        PreparedStatement pst;
        // mengecek apakah data yang akan ditambahkan valid atau tidak
        if(this.validateAddUser(idUser, pass, level)){
            Log.addLog("Data dari '" + idUser + "' dinyatakan valid.");
            // menambahkan data kedalam Database
            pst = this.conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?)");
            pst.setString(1, idUser);
            pst.setString(2, pass);
            pst.setString(3, level.name());
            
            // mengekusi query
            return pst.executeUpdate() > 0;
            
        }
        return false;
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah semua data dari user yang diinputkan valid atau tidak.
     * Method akan mengecek satu persatu data dari user. Jika ada salah satu data saja yang tidak valid maka 
     * semua data dari user yang di inputkan akan dianggap tidak valid dan method akan mengembalikan nilai 
     * <code>False</code>. Method hanya akan mengembalikan nilai <code>True</code> jika semua data dari 
     * user yang diinputkan valid.
     * 
     * @param idUser ID dari user
     * @param pass password dari user
     * @param level level dari user
     * 
     * @return <strong>True</strong> jika semua data dari user valid. <br>
     *         <strong>False</strong> jika ada salah satu data dari user yang tidak valid.
     */
    private boolean validateAddUser(String idUser, String pass, UserLevels level){

        boolean vIdUser, vPassword, vLevel;

        // mengecek apakah id user valid atau tidak
        if(Validation.isIdUser(idUser)){
            if(!this.isExistUser(idUser)){
                vIdUser = true;
            }else{
                throw new InValidUserDataException("'" + idUser + "' ID User tersebut sudah terpakai.");
            }
        }else{
            throw new InValidUserDataException("'" + idUser + "' ID User tersebut tidak valid.");
        }

        // mengecek apakah password valid atau tidak
        if(Validation.isPassword(pass)){
            vPassword = true;
        }else{
            throw new InValidUserDataException("'" + pass + "' Password tersebut tidak valid.");
        }

        // mengecek apakah user level valid atau tidak
        if(Validation.isLevel(level)){
            vLevel = true;
        }else{
            throw new InValidUserDataException("'" + level.name() + "' level tersebut tidak valid.");
        }
        
        return vIdUser && vPassword && vLevel;
    }
    
    /**
     * Method ini digunakan untuk menghapus sebuah akun dari user yang ada didalam <b>Database</b> berdasarkan 
     * id user yang diinputkan. Method akan menghapus akun user dari <b>Database</b> melalui method 
     * {@code deleteData()} yang ada didalam class {@code Database}. Method akan mengembalikan nilai 
     * <code>True</code> jika akun dari user berhasil dihapus.
     * 
     * @param idUser id dari user yang ingin dihapus.
     * 
     * @return <strong>True</strong> jika akun dari user berhasil dihapus. <br>
     *         <strong>Fale</strong> jiak akun dari user tidak berhasil dihapus.
     */
    public final boolean deleteUser(String idUser){
        Log.addLog("Menghapus akun dengan ID User '" + idUser + "'.");
        return this.deleteData(DatabaseTables.USERS.name(), UserData.ID_USER.name(), idUser);
    }
    
    /**
     * Digunakan untuk mengecek apakah user sudah melalukan Login pada Aplikasi atau belum. Petama-tama
     * method akan mendapatkan login data dari Aplikasi. Selanjunya method akan mengecek apakah login data 
     * kosong atau tidak. Jika login data kosong maka user akan dianggap belum melakukan login. Jika login data tidak 
     * kosong maka method akan mengambil data id login dan id user yang ada didalam login data melalui object dari 
     * class {@code StringTokenizer}.
     * 
     * @return <strong>True</strong> jika user sudah melakukan login. <br>
     *         <strong>False</strong> jika user belum melakukan login.
     */
    public final boolean isLogin(){
        
        // object dan variabel digunakan untuk mengecek 
        String idUser = this.getLoginData();
        
        // jika login data tidak kosong
        if(idUser != null){
            // mengecek apakah idUser yang dibuat untuk login exist atau tidak
            if(this.isExistUser(idUser)){
                return true;
            }
        }            
        return false;
    }
    
    /**
     * Method ini digunakan untuk melakukan Login pada Aplikasi. User dapat melakukan Login pada Aplikasi cukup 
     * dengan menginputkan ID User beserta passwordnya. Pertama-tama method akan mengecek apakah user sudah 
     * melakukan login atau belum. Jika user sudah melakukan login maka method akan menghasilkan exception 
     * {@code AuthenticationException}. Pertama-tama method akan mengecek apakah ID User dan passwordnya valid atau tidak. 
     * Jika ID User atau passwordnya tidak valid maka Login akan dibatalkan dan method akan mengembalikan nilai <b>False</b>.
     * <br><br>
     * Jika ID User dan passwordya valid maka method akan membuat sebuah ID Login baru. Setelah membuat ID Login
     * method akan juga membuat login data baru bedasarkan ID User yang diinputkan dan ID Login yang baru saja dibuat.
     * Setelah login data dibuat method akan menyimpan login data tersebut kedalam file login_data.haqi yang 
     * ada didalam folder Storage dengan menggunakan class {@code BufferedWriter}. 
     * <br><br>
     * Jika login data sudah berhasil disimpan kedalam file maka selanjutnya mehtod akan membuat sebuah object 
     * {@code PreparedStatement} yang digunakan untuk menyimpan login data kedalam <b>Database</b>. Method juga 
     * akan membuat folder user storage yang digunakan untuk menyimpan data dari user berdasarkan ID User yang diinputkan. 
     * Jika login data berhasil ditambahkan kedalam <b>Database</b> dan folder user storage berhasil dibuat maka 
     * login dianggap berhasil dan method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser id dari user yang akan melakukan login.
     * @param password password dari user.
     * 
     * @return <strong>True</strong> jika login berhasil dilakukan. <br>
     *         <strong>False</strong> jika login tidak berhasil dilakukan.
     * 
     * @throws IOException jika terjadi kesalahan saat memanipulasi file login_data.haqi.
     * @throws AuthenticationException jika user sudah melakukan login.
     * @throws SQLException jika terjadi kesalahan pada <b>Database</b>.
     */
    public final boolean login(String idUser, String password) throws IOException, AuthenticationException, SQLException{
        
        PreparedStatement pst;
        String idLogin, newLoginData;
        
        // mengecek apakah user sudah login atau belum
//        if(this.isLogin()){
//            throw new AuthenticationException("Anda sudah login dengan akun '" + this.getCurrentLogin() + "'");
//        } 
        
        // mengecek apakah idUser dan password valid atau tidak
        if(this.validateLogin(idUser, password)){
            // membuat sebuah id login baru 
//            idLogin = this.createIdLogin();
            // membuat login data baru
//            newLoginData = idLogin + "/" + idUser;
            Log.addLog("Melakukan Login dengan ID Login : '" + idUser + "' dan dengan ID User : '"+ idUser +"'");
            
            // menyimpan login data kedalam file
            BufferedWriter save = new BufferedWriter(new FileWriter(this.LOGIN_DATA_FILE));
            save.write(idUser);
            save.flush();
            save.close();
            
            // menyimpan login data ke dalam tabel login yang ada didalam Database
//            pst = this.conn.prepareStatement("INSERT INTO login VALUES (?, ?, ?)");
//            pst.setString(1, idLogin);
//            pst.setString(2, idUser);
//            pst.setString(3, new Waktu().getCurrentDateTime());
            
            // true jika login data berhasil ditambahkan dan user storage berhasil dibuat
//            return pst.executeUpdate() > 0 && this.createUserStorage(idUser);
            return true;
        }
        return false;
    }
    
    private boolean validateLogin(String idUser, String password) throws AuthenticationException{

        // mengecek apakah id user valid atau tidak
        if(!Validation.isIdUser(idUser)){
            throw new AuthenticationException("'" +idUser + "' ID User tersebut tidak valid.");
        }else if(!this.isExistUser(idUser)){
            throw new AuthenticationException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");
        }
        // mengecek apakah password valid atau tidak
        else if(!Validation.isPassword(password)){
            throw new AuthenticationException("Password yang anda masukan tidak valid.");
        }else if(!password.equalsIgnoreCase(this.getPassword(idUser))){
            throw new AuthenticationException("Password yang anda masukan tidak cocok.");
        }else{
            return true;
        }
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data akun yang sedang digunakan untuk login (login data) 
     * pada Aplikasi. Login data disimpan pada file <code>login_data.haqi</code> yang ada didalam folder 
     * Storage. Method membaca data yang ada didalam file <code>login_data.haqi</code> dengan melalui 
     * class {@code BufferedReader}. 
     * <br><br>
     * <br><br>
     * <b>Contoh Login Data = ID User
     * 
     * @return akan mengembalikan data akun yang sedang digunakan untuk login (login data).
     */
    private String getLoginData(){        
        // membaca semua data yang ada didalam file login_data.haqi
        try(BufferedReader data = new BufferedReader(new FileReader(this.LOGIN_DATA_FILE))){
            // mengembalikan nilai loginData
            return data.readLine();
        }catch(IOException ex){
            Message.showException(this, "Storage Corrupt!!", ex, true);
            System.exit(404);
        }     
        return null;
    }
    
    /**
     * Method ini digunakan untuk mendapatkan ID User dari akun yang sedang digunakan untuk Login.
     * Pertama-tama method akan mengecek apakah user sudah melakukan Login atau belum. Jika user belum 
     * melakukan Login maka method akan mengembalikan nilai <code>null</code>. Tetapi jika user sudah 
     * melakukan login method akan mendapatkan ID User yang berada didalam login data. 
     * 
     * <br><br>
     * <b>Example : </b> KY001
     * 
     * @return ID User dari akun yang sedang digunakan untuk Login.
     */
    public final String getCurrentLogin(){
        // mengecek apakah user sudah login atau belum
        if(this.isLogin()){
            // mengembalikan id user
            return this.getLoginData();
        }            
        return null;
    }
    
    /**
     * Digunakan untuk mendapatkan data dari nama akun yang sedang digunakan untuk login
     * 
     * @return nama dari akun
     */
    public String getCurrentLoginName(){
        return this.getData(DatabaseTables.PETUGAS.name(), "nama_petugas", "WHERE id_petugas = '" + this.getCurrentLogin() + "'");
    }
    
    /**
     * Method ini digunakan untuk melakukan Logout pada Aplikasi. Sebelum melogout akun method akan mengecek apakah 
     * user sudah melakukan Login atau belum. Jika user belum melakukan Login maka method akan menghasilkan 
     * exception {@code AuthenticationException}. 
     * 
     * @return <strong>True</strong> jika Logout berhasil. <br>
     *         <strong>False</strong> jika Logout tidak berhasil.
     * 
     * @throws AuthenticationException jika user belum melakukan login.
     */
    public final boolean logout() throws AuthenticationException{
        try{
            // mengecek apakah user sudah melakukan login atau belum
            if(isLogin()){
                Log.addLog("Melakukan Logout pada Akun dengan ID User : " + this.getCurrentLogin() + "'");
                // menghapus login data yang ada didalam database
                BufferedWriter buff = new BufferedWriter(
                        new FileWriter(
                                new Storage().getUsersDir()));
                buff.write("");
                buff.flush();
                return true;
            }            
        }catch(IOException ex){
            Message.showException(null, ex, true);
        }
            throw new AuthenticationException("Gagal melogout akun!");
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah sebuah ID User sudah exist atau belum didalam <b>Database</b>.
     * 
     * @param id ID User yang akan dicek.
     * @param level level dari user yang akan dicek id-nya
     * @param primary primary key dari level
     * @return <strong>True</strong> jika ID User exist. <br>
     *         <strong>False</strong> jika ID User tidak exist.
     */
    protected boolean isExistID(String id, UserLevels level, UserData primary){
        // mengecek apakah id user yang diinputkan valid atau tidak
        if(Validation.isIdUser(id)){
            return super.isExistData(level.name(), primary.name(), id);
        }
//         akan menghasilkan error jika id user tidak valid
        throw new InValidUserDataException("'" +id + "' ID tersebut tidak valid.");
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah sebuah ID User sudah exist atau belum didalam <b>Database</b>.
     * Pertama-tama method akan mengecek apakah ID User valid atau tidak dengan menggunakan method 
     * {@code isIdUser()} yang ada didalam class {@code Validation}. Jika ID User tidak valid maka method 
     * akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Method akan mengecek apakah sebuah ID User sudah exist atau belum didalam <b>Database</b> dengan 
     * menggunakan method {@code isExistData()} yang ada didalam class {@code Database}. Jika output dari 
     * method tersebut adalah <code>True</code> maka ID User dinyatakan exist.
     * 
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> jika ID User exist. <br>
     *         <strong>False</strong> jika ID User tidak exist.
     */
    public final boolean isExistUser(String idUser){
        return this.isExistID(idUser, UserLevels.USERS, UserData.ID_USER);
    }
    
    protected String getLastID(UserLevels level, UserData primary){
        try{
            String query = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 0,1", level.name(), primary.name());
            res = stat.executeQuery(query);
            if(res.next()){
                return res.getString(primary.name());
            }
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public String createID(UserLevels level, UserData primary){
        String lastID = this.getLastID(level, primary), nomor;
        
        if(!lastID.equals("")){
            nomor = lastID.substring(2);
        }else{
            nomor = "000";
        }
        
        // mengecek nilai dari nomor adalah number atau tidak
        if(txt.isNumber(nomor)){
            // jika id pembayaran belum exist maka id akan 
            switch(level.name()){
                case "PETUGAS" : return String.format("PG%03d", Integer.parseInt(nomor)+1); // level admin dan karyawan
                case "SUPPLIER" : return String.format("SP%03d", Integer.parseInt(nomor)+1);
                case "PEMBELI" : return String.format("PB%03d", Integer.parseInt(nomor)+1);
                default : System.out.println("Error!");
            }
        }
        return null;
    }
    
    /**
     * Method ini akan mengembalikan data dari user berdasarkan ID User yang diinputkan. Pertama-tama method 
     * akan mengecek apakah ID User exist atau tidak. Jika ID User tidak exist maka akan menghasilkan exception 
     * {@code InValidUserDataException}. Tetapi jika ID User exist maka data dari user akan didapatkan dengan 
     * melalui method {@code getData()} yang ada didalam class {@code Database}.
     * 
     * @param idUser id user yang ingin didapatkan datanya
     * @param level level dari user (tabelnya apa)
     * @param data data yang akan diambil
     * @param primary primary key dari tabel
     * @return data dari user
     */
    protected String getUserData(String idUser, UserLevels level, UserData data, UserData primary){
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mendapatkan data dari user
            return this.getData(level.name(), data.name(), " WHERE "+ primary.name() +" = '" + idUser +"'");
        }
        // akan menghasilkan error jika id user tidak ditemukan
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");   
    }
    
    /**
     * Method ini akan mengembalikan data dari user berdasarkan ID User yang diinputkan. Pertama-tama method 
     * akan mengecek apakah ID User exist atau tidak. Jika ID User tidak exist maka akan menghasilkan exception 
     * {@code InValidUserDataException}. Tetapi jika ID User exist maka data dari user akan didapatkan dengan 
     * melalui method {@code getData()} yang ada didalam class {@code Database}.
     * 
     * @param idUser ID User yang ingin diambil datanya.
     * @param data data yang ingin diambil.
     * @return akan mengembalikan data dari user berdasarkan ID User yang diinputkan.
     */
    public String getUserData(String idUser, UserData data){
        return this.getUserData(idUser, UserLevels.USERS, data, UserData.ID_USER);         
    }
    
    protected boolean setUserData(String idUser, UserLevels level, UserData data, UserData primary, String newValue){
        Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari akun dengan ID User '" + idUser + "'.");
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mengedit data dari user
            return super.setData(level.name(), data.name(), primary.name(), idUser, newValue);
        }
        // akan menghasilkan error jika id user tidak ditemukan
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");
    }
    
    /**
     * Method ini digunakan untuk megedit data dari user berdasarkan ID User yang diinputkan. Sebelum mengedit data
     * method akan mengecek apakah ID User exist atau tidak. Jika ID User tidak exist maka akan menghasilkan exception 
     * {@code InValidUserDataException}. Tetapi jika ID User exist maka method akan mengedit data dari user dengan 
     * menggunakan method {@code setData()} yang ada didalam class {@code Database}. Jika data dari user berhasil 
     * diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * @param data data dari ID User yang ingin diedit.
     * @param newValue nilai baru dari data yang ingin diedit.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setUserData(String idUser, UserData data, String newValue){
        Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari akun dengan ID User '" + idUser + "'.");
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mengedit data dari user
            return this.setData(DatabaseTables.USERS.name(), data.name(), UserData.ID_USER.name(), idUser, newValue);
        }
        // akan menghasilkan error jika id user tidak ditemukan
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data Password dari user berdasarkan ID User yang diinputkan. 
     * ID User yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User yang diinputkan ternyata 
     * tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception {@code InValidUserDataException}. 
     * Method hanya akan mendapatkan data Password dari user jika ID User yang diinputkan terdaftar didalam <b>Database</b>.
     * 
     * @param idUser ID User yang ingin didapatkan datanya.
     * @return data Password dari ID User yang diinputkan.
     */
    public String getPassword(String idUser){
        return this.getUserData(idUser, UserData.PASSWORD);
    }
    
    /**
     * Digunakan untuk mengedit data Password dari user berdasarkan ID User yang diinputkan. Sebelum mengedit 
     * data Password method akan mengecek apakah Password yang diinputkan valid atau tidak dengan menggunakan 
     * method {@code isPassword(String password)} yang ada didalam class {@code Validation}. Jika Password tidak valid
     * maka method akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Tetapi jika Password valid maka data Password dari user akan diedit. Jika data dari Password berhasil 
     * diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * @param newPassword data Password yang baru.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setPassword(String idUser, String newPassword){
        // mengecek apakah password valid atau tidak
        if(Validation.isPassword(newPassword)){
            // mengedit password dari user
            return this.setUserData(idUser, UserData.PASSWORD, newPassword);
        }
        // akan menghasilkan error jika password tidak valid
        throw new InValidUserDataException("'" + newPassword + "' Password tersebut tidak valid.");
    }
    
    /**
     * Digunakan untuk mengedit data Password dari user berdasarkan ID User dari akun yang sedang digunakan untuk Login. 
     * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
     * akan mengedit data Password dari user melalui method {@code setPassword(String idUser, String newPassword)}. Jika
     * output dari method tersebut adalah <code>True</code> maka data Password dari user berhasil diedit.
     * 
     * @param newPassword data Password yang baru.
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setPassword(String newPassword){
        return this.setPassword(getCurrentLogin(), newPassword);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data Level dari user berdasarkan ID User yang diinputkan. 
     * ID User yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User yang diinputkan ternyata 
     * tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception {@code InValidUserDataException}. 
     * Method hanya akan mendapatkan data Level dari user jika ID User yang diinputkan terdaftar didalam <b>Database</b>.
     * 
     * @param idUser ID User yang ingin didapatkan datanya.
     * @return data Level dari ID User yang diinputkan.
     */
    public UserLevels getLevel(String idUser){
        return UserLevels.valueOf(this.getUserData(idUser, UserData.LEVEL));
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data Level dari user berdasarkan ID User dari akun yang sedang 
     * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
     * Selanjutnya method akan mendapatkan data Level dari user melalui method {@code getLevel(String idUser)}.
     * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
     * 
     * @return data Level dari akun yang sedang Login.
     */
    public UserLevels getLevel(){
        return this.getLevel(getCurrentLogin());
    }
    
    /**
     * Digunakan untuk mengedit data Level dari user berdasarkan ID User yang diinputkan. Sebelum mengedit 
     * data Level method akan mengecek apakah Level yang diinputkan valid atau tidak dengan menggunakan 
     * method {@code isLevel(String level)} yang ada didalam class {@code Validation}. Jika Level tidak valid
     * maka method akan menghasilkan exception {@code InValidUserDataException}.
     * 
     * @param idUser ID User yang ingin diedit levelnya
     * @param newLevel data Password yang baru.
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setLevel(String idUser, UserLevels newLevel){
        if(Validation.isLevel(newLevel)){
            return this.setUserData(idUser, UserData.LEVEL, newLevel.name());
        }
        // akan menghasilkan error jika password tidak valid
        throw new InValidUserDataException("'" + newLevel + "' Level tersebut tidak valid.");
    }
    
    /**
     * Digunakan untuk mengecek apakah Level dari ID User yang diinputkan memiliki Level <b>ADMIN</b> atau tidak.
     * Method akan mendapatkan data Level dari ID User dengan menggunakan method {@code getLevel()}. Jika output 
     * dari method tersebut adalah <b>ADMIN</b> maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> jika level dari user adalah <b>ADMIN</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isAdmin(String idUser){
        return this.getLevel(idUser).name().equals("ADMIN");
    }
    
    /**
     * Digunakan untuk mengecek apakah Level dari akun yang sedang digunakan untuk Login apakah memiliki Level
     * <b>ADMIN</b> atau tidak. Method akan mendapatkan ID User dari akun yang sedang digunakan untuk Login dengan 
     * menggunakan method {@code getCurrentLogin()}. Selanjutnya method akan mengecek apakah user memiliki Level 
     * <b>ADMIN</b> atau tidak melalui method {@code isAdmin(String idUser)}.
     * 
     * @return <strong>True</strong> jika level dari user adalah <b>ADMIN</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isAdmin(){
        return this.isAdmin(getCurrentLogin());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan total user yang terdaftar di <b>Database</b> aplikasi. Method 
     * akan mendapatkan data total user dengan melalui method {@code getJumlahData()} yang ada didalam 
     * class {@code Database}.
     * 
     * @return total user yang terdaftar di aplikasi.
     */
    public int getTotalUser(){
        return super.getJumlahData(DatabaseTables.USERS.name());
    }
    

    
    public static void main(String[] args) {
        Log.createLog();
        Users user = new Users();
        System.out.println(user.getCurrentLoginName());
//        System.out.println(Validation.isIdUser("PB286"));
//        System.out.println(user.getLastID(UserLevels.PEMBELI, UserData.ID_PEMBELI));
//        System.out.println(user.createID(UserLevels.PEMBELI, UserData.ID_PEMBELI));
    }
    
}
