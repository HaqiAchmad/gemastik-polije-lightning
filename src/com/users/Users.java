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
import com.media.Gambar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
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
     * <br><br>
     * Untuk data foto dari user jika foto yang diinputkan <code>null</code> atau foto tersebut tidak exist maka 
     * data foto dari user akan secara default diset ke <code>NULL</code>. Jika foto dari user exist maka foto 
     * akan ditambahkan kedalam <b>Database</b> dalam bentuk byte stream / <code>Blob</code>.
     * 
     * @param idUser ID dari user.
     * @param noHp nomor HP dari user.
     * @param email email dari user.
     * @param foto foto dari user.
     * @param password password dari user.
     * @param level level dari user.
     * 
     * @return <strong>True</strong> jika data berhasil ditambahkan. <br>
     *         <strong>False</strong> jika data tidak berhasil ditambahkan. 
     * 
     * @throws FileNotFoundException jika terjadi kegagalan saat menkonversi foto kedalam byte stream / Blob.
     * @throws SQLException jika terjadi kegagalan saat menambahkan data kedalam <b>Database</b>.
     * @throws InValidUserDataException jika data dari petugas tidak valid.
     */
    public final boolean addUser(String idUser, String noHp, String email, File foto, String password, UserLevels level) 
            throws FileNotFoundException, SQLException, InValidUserDataException
    {
        Log.addLog("Menambahkan akun baru dengan ID User '"+idUser +"' ke Database.");
        PreparedStatement pst;
        // mengecek apakah data yang akan ditambahkan valid atau tidak
        if(this.validateAddUser(idUser, noHp, email, password)){
            Log.addLog("Data dari '" + idUser + "' dinyatakan valid.");
            // menambahkan data kedalam Database
            pst = this.conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)");
            pst.setInt(1, Integer.parseInt(idUser));
            pst.setString(2, noHp);
            pst.setString(3, email);
            pst.setString(5, password);
            pst.setString(6, level.name());
            
            // jika foto yang diinputkan tidak kosong
            if(foto != null){
                // jika foto exist maka foto akan dikonversi kedalam bentuk byte stream
                if(foto.exists()){
                    Log.addLog("Menkonversi foto menjadi byte stream.");
                    // mengkonversi file ke byte stream
                    pst.setBlob(4, new FileManager().fileToBlob(foto));
                }else{
                    // jika foto tidak exist maka foto akan diset ke NULL
                    pst.setString(4, null);
                }                
            }else{
                // jika foto kosong maka foto akan diset ke NULL
                pst.setString(4, null);
            }
            
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
     * @param idUser id user yang akan dicek.
     * @param noHp no hp yang akan dicek.
     * @param email email yang akan dicek.
     * @param password password yang akan dicek.
     * @return <strong>True</strong> jika semua data dari user valid. <br>
     *         <strong>False</strong> jika ada salah satu data dari user yang tidak valid.
     */
    private boolean validateAddUser(String idUser, String noHp, String email, String password){
        
        boolean vIdUser, vNoHp, vEmail, vPassword;
        
        Log.addLog("Mengecek data dari '" + idUser + "' valid atau tidak.");
        
        // mengecek apakah id user valid atau tidak
        if(new Text().isNumber(idUser)){
            if(Validation.isIdUser(idUser)){
                if(!this.isExistUser(idUser)){
                    vIdUser = true;
                }else{
                    throw new InValidUserDataException("'" + idUser + "' ID User tersebut sudah terpakai.");
                }
            }else{
                throw new InValidUserDataException("'" + idUser + "' ID User tersebut tidak valid.");
            }
        }else{
            throw new InValidUserDataException("ID User harus berupa Integer.");
        }
        
        // mengecek apakah nomor hp valid atau tidak
        if(Validation.isNoHp(noHp)){
            if(!this.isExistNoHp(noHp)){
                vNoHp = true;
            }else{
                throw new InValidUserDataException("'" + noHp + "' Nomor Hp terebut sudah terpakai.");
            }
        }else{
            throw new InValidUserDataException("'" + noHp + "' Nomor Hp terebut tidak valid.");
        }
        
        // mengecek apakah password valid atau tidak
        if(Validation.isPassword(password)){
            vPassword = true;
        }else{
            throw new InValidUserDataException("'" + password + "' Password tersebut tidak valid.");
        }
        
        return vIdUser && vNoHp && vPassword;
    }
    
    /**
     * Method ini digunakan untuk menghapus sebuah akun dari user yang ada didalam <b>Database</b> berdasarkan 
     * id user yang diinputkan. Method akan menghapus akun user dari <b>Database</b> melalui method 
     * {@code deleteData()} yang ada didalam class {@code Database}. Method akan mengembalikan nilai 
     * <code>True</code> jika akun dari user berhasil dihapus.
     * 
     * @param idUser id dari user yang ingin dihapus.
     * @return <strong>True</strong> jika akun dari user berhasil dihapus. <br>
     *         <strong>Fale</strong> jiak akun dari user tidak berhasil dihapus.
     */
    public final boolean deleteUser(String idUser){
        Log.addLog("Menghapus akun dengan ID User '" + idUser + "'.");
        return this.deleteData(DatabaseTables.USERS.name(), UserData.ID_USER.name(), idUser);
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
     * Method ini digunakan untuk mendapatkan ID Login dari akun yang sedang digunakan untuk Login.
     * Pertama-tama method akan mengecek apakah user sudah melakukan Login atau belum. Jika user belum 
     * melakukan Login maka method akan mengembalikan nilai <code>null</code>. 
     * <br><br>
     * Tetapi jika user sudah melakukan login method akan mendapatkan ID Login yang berada didalam login data. 
     * ID Login yang ada didalam login data akan didapatkan melalui method {@code nextToken()} yang ada didalam 
     * class {@code StringTokenizer()}.
     * <br><br>
     * <b>Example : </b> P9dz93ig
     * 
     * @return ID Login dari akun yang sedang digunakan untuk Login
     */
    private String getCurrentIdLogin(){
        // mengecek apakah user sudah login atau belum
        if(this.isLogin()){
            // mengembalikan data ID Login dari akun yang sedang digunakan untuk Login
            return new StringTokenizer(getLoginData(), "/").nextToken();
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
        // mengecek apakah id user yang diinputkan valid atau tidak
        if(Validation.isIdUser(idUser)){
                return this.isExistData(DatabaseTables.USERS.name(), UserData.ID_USER.name(), idUser);
        }
        // akan menghasilkan error jika id user tidak valid
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak valid.");
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah sebuah Nomor HP sudah exist atau belum didalam <b>Database</b>.
     * Pertama-tama method akan mengecek apakah Nomor HP valid atau tidak dengan menggunakan method 
     * {@code isNoHp()} yang ada didalam class {@code Validation}. Jika Nomor HP tidak valid maka method 
     * akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Method akan mengecek apakah sebuah Nomor HP sudah exist atau belum didalam <b>Database</b> dengan 
     * menggunakan method {@code isExistData()} yang ada didalam class {@code Database}. Jika output dari 
     * method tersebut adalah <code>True</code> maka Nomor HP dinyatakan exist.
     * 
     * @param noHp nomor HP yang akan dicek.
     * @return <strong>True</strong> jika Nomor HP exist. <br>
     *         <strong>False</strong> jika Nomor HP tidak exist.
     */
    public final boolean isExistNoHp(String noHp){
        // mengecek apakah no hp valid atau tidak
        if(Validation.isNoHp(noHp)){
            return this.isExistData(DatabaseTables.USERS.name(), UserData.NO_HP.name(), noHp);
        }
        // akan menghasilkan error jika no hp tidak valid
        throw new InValidUserDataException("'" + noHp + "' Nomor HP tersebut tidak valid.");
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
    private String getUserData(String idUser, UserData data){
//        Log.addLog("Mendapatkan data " + data + " dari akun dengan ID User '" + idUser + "'.");
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mendapatkan data dari user
            return this.getData(DatabaseTables.USERS.name(), data.name(), " WHERE "+ UserData.ID_USER.name() +" = '" + idUser +"'");
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
    private boolean setUserData(String idUser, UserData data, String newValue){
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
     * Method ini digunakan untuk mendapatkan data Nomor HP dari user berdasarkan ID User yang diinputkan. 
     * ID User yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User yang diinputkan ternyata 
     * tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception {@code InValidUserDataException}. 
     * Method hanya akan mendapatkan data Nomor HP dari user jika ID User yang diinputkan terdaftar didalam <b>Database</b>.
     * 
     * @param idUser ID User yang ingin didapatkan datanya.
     * @return data Nomor HP dari ID User yang diinputkan.
     */
    public String getNoHp(String idUser){
        return this.getUserData(idUser, UserData.NO_HP);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data Nomor HP dari user berdasarkan ID User dari akun yang sedang 
     * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
     * Selanjutnya method akan mendapatkan data Nomor HP dari user melalui method {@code getNoHp(String idUser)}.
     * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
     * 
     * @return data Nomor HP dari akun yang sedang Login.
     */
    public String getNoHp(){
        return this.getNoHp(getCurrentLogin());
    }
    
    /**
     * Digunakan untuk mengedit data Nomor HP dari user berdasarkan ID User yang diinputkan. Sebelum mengedit 
     * data Nomor HP method akan mengecek apakah Nomor HP yang diinputkan valid atau tidak dengan menggunakan 
     * method {@code idNoHP(String noHp)} yang ada didalam class {@code Validation}. Jika Nomor HP tidak valid
     * maka method akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Jika Nomor HP valid maka selanjutnya method akan mengecek apakah Nomor HP sudah terpakai atau belum dengan 
     * menggunakan method {@code isExistNoHp}. Jika Nomor HP sudah ada yang memakai maka method akan menghasilkan 
     * exception {@code InValidUserDataException}. 
     * <br><br>
     * Tetapi jika Nomor HP belum ada yang memakai maka data Nomor HP dari user akan diedit. 
     * Jika data dari Nomor HP berhasil diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * @param newNoHp data Nomor HP yang baru.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setNoHp(String idUser, String newNoHp){
        // mengecek apakah nomor hp valid atau tidak
        if(Validation.isNoHp(newNoHp)){
            // mengecek apakah nomor hp sudah dipakai atau belum
            if(!this.isExistNoHp(newNoHp)){
                // mengedit data nomor hp dari user
                return this.setUserData(idUser, UserData.NO_HP, newNoHp);
            }else{
                // akan menghasilkan error jika nomor hp sudah dipakai
                throw new InValidUserDataException("'" + newNoHp + "' Nomor HP tersebut sudah dipakai.");
            }
        }
        // akan menghasilkan error jika nomor hp tidak valid
        throw new InValidUserDataException("'" + newNoHp + "' Nomor HP tersebut tidak valid.");
    }
    
    /**
     * Digunakan untuk mengedit data Nomor HP dari user berdasarkan ID User dari akun yang sedang digunakan untuk Login. 
     * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
     * akan mengedit data Nomor HP dari user melalui method {@code setNoHp(String idUser, String newNoHp)}. Jika
     * output dari method tersebut adalah <code>True</code> maka data Nomor HP dari user berhasil diedit.
     * 
     * @param newNoHp data Nomor HP yang baru.
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setNoHp(String newNoHp){
        return this.setNoHp(getCurrentLogin(), newNoHp);
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
     * Method ini digunakan untuk mendapatkan data Password dari user berdasarkan ID User dari akun yang sedang 
     * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
     * Selanjutnya method akan mendapatkan data Password dari user melalui method {@code getPassword(String idUser)}.
     * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
     * 
     * @return data Password dari akun yang sedang Login.
     */
    public String getPassword(){
        return this.getPassword(getCurrentLogin());
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
     * Method ini digunakan untuk mendapatkan kekuatan password dari User. Kekuatan dari password akan ditentukan 
     * berdasarkan panjang karakter dari password. Ada lima level kekuatan password yang ada didalam aplikasi ini 
     * antara lain.
     * <br><br>
     * <ul>
     *  <li><b>Sangat Lemah : </b> panjang karakter diantara 0 sampai 8.</li>
     *  <li><b>Lemah : </b> panjang karakter diantara 9 sampai 15.</li>
     *  <li><b>Medium : </b> panjang karakter diantara 16 sampai 20.</li>
     *  <li><b>Kuat : </b> panjang karakter diantara 21 sampai 30.</li>
     *  <li><b>Sangat Kuat : </b> panjang karakter diantara 31 sampai 50.</li>
     * </ul>
     * 
     * @param password password yang akan dicek kekuatanya.
     * @return kekuatan dari password.
     */
    public String getPasswordStrength(String password){
        int length = password.length();
        
        if(length >= 0 && length <= 8){
            return "Sangat Lemah";
        }else if(length > 8 && length <= 15){
            return "Lemah";
        }else if(length > 15 && length <= 20){
            return "Sedang";
        }else if(length > 20 && length <= 30){
            return "Kuat";
        }else if(length > 20){
            return "Sangat Kuat";
        }
        
        return "I Dont Know";
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
     * Digunakan untuk mengecek apakah Level dari ID User yang diinputkan memiliki Level <b>PETUGAS</b> atau tidak.
     * Method akan mendapatkan data Level dari ID User dengan menggunakan method {@code getLevel()}. Jika output 
     * dari method tersebut adalah <b>PETUGAS</b> maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> jika level dari user adalah <b>PETUGAS</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isPetugas(String idUser){
        return this.getLevel(idUser).name().equals("PETUGAS");
    }
    
    /**
     * Digunakan untuk mengecek apakah Level dari akun yang sedang digunakan untuk Login apakah memiliki Level
     * <b>PETUGAS</b> atau tidak. Method akan mendapatkan ID User dari akun yang sedang digunakan untuk Login dengan 
     * menggunakan method {@code getCurrentLogin()}. Selanjutnya method akan mengecek apakah user memiliki Level 
     * <b>PETUGAS</b> atau tidak melalui method {@code isPetugas(String idUser)}.
     * 
     * @return <strong>True</strong> jika level dari user adalah <b>PETUGAS</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isPetugas(){
        return this.isPetugas(getCurrentLogin());
    }
    
    /**
     * Digunakan untuk mengecek apakah Level dari ID User yang diinputkan memiliki Level <b>SISWA</b> atau tidak.
     * Method akan mendapatkan data Level dari ID User dengan menggunakan method {@code getLevel()}. Jika output 
     * dari method tersebut adalah <b>SISWA</b> maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> jika level dari user adalah <b>SISWA</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isSiswa(String idUser){
        return this.getLevel(idUser).name().equals("SISWA");
    }
    
    /**
     * Digunakan untuk mengecek apakah Level dari akun yang sedang digunakan untuk Login apakah memiliki Level
     * <b>SISWA</b> atau tidak. Method akan mendapatkan ID User dari akun yang sedang digunakan untuk Login dengan 
     * menggunakan method {@code getCurrentLogin()}. Selanjutnya method akan mengecek apakah user memiliki Level 
     * <b>SISWA</b> atau tidak melalui method {@code isSiswa(String idUser)}.
     * 
     * @return <strong>True</strong> jika level dari user adalah <b>SISWA</b>. <br>
     *         <strong>False</strong> otherwise. 
     */
    public boolean isSiswa(){
        return this.isSiswa(getCurrentLogin());
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
    
    /**
     * Method ini digunakan untuk mendapatkan Nama dari Foto User. Nama dari Foto User dapat berisi ID User dan Nama User.
     * Nama dari Foto User akan berguna saat proses download foto User sedang berlangsung. Pertama-tama method akan 
     * mendapatkan Nama dari User. Setelah Nama dari User berhasil didapatkan maka selanjutnya adalah mengabungkan 
     * ID User dan Nama dari User untuk mendapatkan Nama dari foto user.
     * <br><br>
     * <b>Example : </b> 6156_achmad_baihaqi.png
     * 
     * @param idUser ID User yang akan didapatkan nama fotonya.
     * @return nama dari foto user.
     */
    @Deprecated
    private String getNameOfPhoto(String idUser){
        // mendapatkan nama file dari foto berdasarkan id user
        String name = "";
//        String name = this.getNameOfIdUser(idUser);
        // mengembalikan nama dari file foto
        return idUser + "_" + name.replaceAll(" ", "_").toLowerCase() + ".png";
    }
    
    /**
     * Method ini digunakan untuk mendapatakan direktori dari Foto User. Foto dari User akan disimpan pada folder cache 
     * yang ada didalam storage aplikasi. Direktori dari Foto User akan digunakan saat proses mendownload Foto User dari
     * database. Sebelum mendapatkan direktori dari Foto User method akan mendapatkan Nama dari Foto User terlebih dahulu.
     * Setelah Nama dari Foto User berhasil didapatkan maka selanjutnya method akan mengabungkan direktori folder cache 
     * pada aplikasi dengan Nama dari Foto User.
     * <br><br>
     * <b>Example : </b> C:\Users\Infinite World\AppData\Local\Punya Haqi\SPP Payment 1.0.0\Cache\pictures\6156_achmad_baihaqi.png
     * 
     * @param idUser id user yang akan didapatkan direktori fotonya.
     * @return direktori dari foto user.
     */
    private String getPhotoDir(String idUser){
        // mendapatkan nama file dari foto
        String file = this.getNameOfPhoto(idUser);
        // mendapatkan direktori dari foto
        return new Storage().getCacheDir() + "pictures\\" + file;
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Foto dari User sudah didownlad atau belum. Untuk mengecek apakah 
     * Foto dari User sudah didownload atau belum method pertama-tama method akan  mendapatakan direktori dari Foto User 
     * melalui method {@code getPhotoDir()}. Setelah direktori dari Foto User sudah didapatkan maka selanjunya method 
     * akan mengecek apakah direktori dari Foto User tersebut exist atau tidak dengan menggunakan method {@code exist()} 
     * yang ada didalam class {@code File}. Jika direktori dari Foto User exist maka method akan mengembalikan 
     * nilai <code>True</code>.
     * 
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> Jika Foto dari User sudah didownload. <br>
     *         <strong>False</strong> Jika Foto dari User belum didownload.
     */
    private boolean isDownloadedPhoto(String idUser){
        // mengecek apakah direktori dan file foto exist atau tidak
        return new File(this.getPhotoDir(idUser)).exists();
    }
    
    /**
     * Method ini digunakan untuk mendownload Foto User dari Database berdasarkan ID User yang diinputkan dalam bentuk 
     * object class {@code File}. Foto User akan disimpan didalam folder cache yang ada didalam storage aplikasi dan Nama 
     * dari Foto User (filename) akan diberinama berdasarkan ID User dan Nama Dari User. Setiap Foto User yang didownload 
     * melalui method ini akan memiliki format gambar <b>.png</b>.
     * <br><br>
     * Sebelum mendownload Foto User method akan mengecek apakah Foto User sebelumnya sudah didownload atau belum. 
     * Jika Foto User sebelumnya sudah didownload maka method akan mengembalikan direktori dari Foto User yang sudah 
     * didownload tersebut. Tetapi jika Foto User belum didownload maka method akan membuat sebuah query yang digunakan 
     * untuk mendapatkan data Foto User yang ada didalam <b>Database</b>.
     * <br><br>
     * Setelah query berhasil dibuat maka selanjutnya method akan mengeksekusi query tersebut dengan menggunakan 
     * method {@code executeQuery()}. Selanjutnya method akan mengecek apakah query yang barusan dieksekusi tersebut
     * apakah memiliki output atau tidak dengan menggunakan method {@code res()}. Jika query memiliki output maka 
     * output dari query akan disimpan didalam object dari class {@code Blob}.
     * <br><br>
     * Tetapi jika query tidak memiliki output itu bearti User belum menggungah/mengupload sebuah Foto ke dalam <b>Database</b> dan 
     * object dari class {@code Blob} secara default akan memiliki nilai null. Foto User yang ada didalam <b>Database</b> akan 
     * disimpan dalam bentuk blob/byte stream. Oleh karena itu method memerlukan class {@code Blob} untuk menyimpan blob/byte 
     * stream dari Foto User yang ada didalam <b>Database</b> saat mengeksekusi query.
     * <br><br>
     * Setelah blob/byte stream dari Foto User yang ada didalam <b>Database</b> berhasil didapatkan dan disimpan didalam 
     * object {@code Blob} maka selanjutnya method akan mengecek apakah object dari class {@code Blob} tersebut memiliki 
     * nilai <code>null</code> atau tidak. Jika object dari class {@code Blob} memiliki nilai <code>null</code> maka 
     * method akan mengembalikan direktori dari default Foto User dalam bentuk object class {@code File}.
     * <br><br>
     * Jika object dari class {@code Blob} tidak memiliki nilai <code>null</code> maka selanjutnya method akan mengkonversi 
     * blob/byte stream tersebut kedalam bentuk sebuah file dengan menggunakan method {@code blobToFile()} yang ada didalam class
     * {@code FileManager}. Setelah blob/byte stream behasil dikonversi menjadi file foto. Maka selanjutnya method akan 
     * mengembalikan direktori dari file foto tersebut dalam bentuk object class {@code File}.
     * 
     * @param idUser ID User yang akan didownload fotonya.
     * @return direktori dari foto user dalam bentuk object class {@code File}.
     * 
     * @throws SQLException jika terjadi kesalahan saat mendownload foto user.
     * @throws IOException  jika terjadi kesalahan saat mengkonversi foto.
     */
    private File downloadPhoto(String idUser) throws SQLException, IOException{
        Blob blob = null;
        String query, file;
        
        // mengecek apakah foto sudah terdownload atau belum
        if(this.isDownloadedPhoto(idUser)){
            // mengembalikan direktori dari foto jika foto sudah didownload
            return new File(this.getPhotoDir(idUser));
        }else{
            Log.addLog("Mendownload Foto dari ID User '"+ idUser + "'");
            // membuat query untuk mendapatkan foto user dari database mysql dalam bentuk blob
            query = String.format(
                    "SELECT %s FROM %s WHERE %s = %s", 
                    UserData.FOTO, DatabaseTables.USERS, UserData.ID_USER, idUser
            );
            
            // mengeksekusi query
            this.res = this.stat.executeQuery(query);
            // mengecek apakah query memiliki output atau tidak
            if(this.res.next()){
                blob = this.res.getBlob(UserData.FOTO.name());
            }
            
            // jika foto kosong maka method akan mengembalikan default foto dari user
            if(blob == null){
                // mengembalikan file default foto 
                return this.getDefaultPhoto();
            }else{
                // mengkonversi blob menjadi file
                file = this.getPhotoDir(idUser);
                new FileManager().blobToFile(blob, file);
                
                Log.addLog("Foto dari '" + idUser + "' berhasil didownload");
                // mengembalikan file dari foto
                return new File(file);
            }
        }
    }
    
    /**
     * Method ini digunakan untuk mendapatkan default Foto User. User yang belum mengunggah/mengupload sebuah Foto ke 
     * <b>Database</b> maka User tersebut akan memakai default Foto dari aplikasi. Default Foto User dari aplikasi 
     * disimpan pada User yang memiliki ID User '1'. File default Foto User disimpan pada folder cache yang ada dialam 
     * storage aplikasi.
     * <br><br>
     * Untuk mendapatkan default Foto User petama-tama method akan mengecek apakah default Foto User sudah didownload atau belum. 
     * Jika default Foto User sudah didownload maka method akan mengembalikan direktori dari default Foto User tersebut 
     * dalam bentuk object dari class {@code File}. Tetapi jika default Foto User belum didownload maka akan didownload 
     * dengan menggunakan method {@code downloadPhoto()}.
     * <br><br>
     * Setelah default Foto User berhasil didownload maka selanjutnya method akan merubah nama file dari default Foto User yang 
     * sebelumnya '1_default.png' menjadi 'default.png' dengan menggunakan method {@code renameFile()} yang ada didalam 
     * class {@code FileManager}. Jika default nama file dari Foto User berhasil direname maka selanjutnya method akan 
     * mengembalikan direktori dari default Foto User dalam bentuk object class {@code File}.
     * 
     * @return default Foto User.
     */
    private File getDefaultPhoto(){
        // file default foto
        File defProfile = new File(new Storage().getCacheDir() + "pictures\\default.png");
        boolean rename;
        
        try{
            // jika file default foto sudah exist/didownload maka akan mengembalikan niali dari defProfile
            if(defProfile.exists()){
                // akan mengembalikan defProfile
                return defProfile;
            }else{
                // mendownload file default foto
                defProfile = this.downloadPhoto("1");
                // merename file default foto ke 'default'
                rename = new FileManager().renameFile(defProfile.toString(), "default");
                // jika file default foto berhasil direname maka method akan mengembalikan direktori dari foto
                if(rename){
                    // mengembalikan direktori dari file default foto
                    return new File(new Storage().getCacheDir() + "pictures\\default.png");
                }
            }
        }catch(SQLException | IOException ex){
            Message.showException(this, "Gagal mendapatkan Foto dari User!", ex, true);
        }
        return null;
    }
    
    public String getName(String idUser){
        if(this.getLevel(idUser).name().equalsIgnoreCase("KARYAWAN")){
            return this.getData("karyawan", "nama_karyawan", "WHERE id_karyawan = '" + idUser + "'");
        }
        return null;
    }
    
    private String getNama(String idUser){
                    switch(kode){
                case "1" : tgs.matriks("+"); break;
                case "2" : tgs.matriks("-"); break;
                case "3" : System.out.println("Not Supported Yet!"); break;
                case "4" : System.exit(0); break;
            }   
        return null;
    }
    
    public static void main(String[] args) {
        Log.createLog();
        Users user = new Users();
        
        System.out.println(user.getCurrentIdLogin());
        System.out.println(user.getCurrentIdLogin());
    }
    
}
