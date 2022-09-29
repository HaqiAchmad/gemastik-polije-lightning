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
import com.manage.Waktu;
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
import java.util.StringTokenizer;
import java.util.UUID;
import javax.swing.ImageIcon;


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
        
        // jika folder user storage dari akun yang sedang login tidak ditemukan maka folder akan dibuat
        if(this.isLogin()){
            if(!this.isExistUserStorage(getCurrentLogin())){
                createUserStorage(getCurrentLogin());
            }
        }
    }
    
    /**
     * Method ini akan mengembalikan object dari inner class {@code LevelPetugas}. Pertama-tama method akan membuat 
     * sebuah object dari class {@code Users} terlebih dahulu. Setelah object dari class {@code Users} berhasil dibuat
     * maka selanjutnya method akan membuat object dari inner class {@code LevelPetgas} dengan keyword {@code new}. 
     * Tujuan dibuatnya method ini adalah agar semua method yang ada didalam class {@code Users} maupun inner class 
     * {@code LevelPetugas} dapat diakses secara bersamaan dalam satu object yang sama.
     * <br><br>
     * Sehingga baris kode pada aplikasi menjadi lebih ringkas karena hanya perlu untuk membuat satu object saja.
     * User dapat menanipulasi atau menadapatkan data yang ada didalam class {@code Users} maupun yang ada 
     * didalam class {@code LevelPetugas}. User juga dapat melakukan Login maupun Logout melalui object dari class
     * {@code LevelPetugas} ini.
     * 
     * @return object dari inner class LevelPetugas. 
     */
    public static LevelPetugas levelPetugas(){
        return new Users().new LevelPetugas();
    }
    
    /**
     * Method ini akan mengembalikan object dari inner class {@code LevelSiswa}. Pertama-tama method akan membuat 
     * sebuah object dari class {@code Users} terlebih dahulu. Setelah object dari class {@code Users} berhasil dibuat
     * maka selanjutnya method akan membuat object dari inner class {@code LevelSiswa} dengan keyword {@code new}. 
     * Tujuan dibuatnya method ini adalah agar semua method yang ada didalam class {@code Users} maupun inner class 
     * {@code LevelSiswa} dapat diakses secara bersamaan.
     * <br><br>
     * Sehingga baris kode pada aplikasi menjadi lebih ringkas karena hanya perlu untuk membuat satu object saja.
     * User dapat menanipulasi atau menadapatkan data yang ada didalam class {@code Users} maupun yang ada 
     * didalam class {@code LevelSiswa}. User juga dapat melakukan Login maupun Logout melalui object dari class
     * {@code LevelSiswa} ini.
     * 
     * @return object dari inner class LevelSiswa. 
     */
    public static LevelSiswa levelSiswa() {
        return new Users().new LevelSiswa();
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
            if(Validation.isIdUser(Integer.parseInt(idUser))){
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
        
        // mengecek apakah email valid atau tidak
        if(Validation.isEmail(email)){
            if(!this.isExistEmail(email)){
                vEmail = true;
            }else{
                throw new InValidUserDataException("'" + email + "' Email tersebut sudah terpakai.");
            }
        }else{
            throw new InValidUserDataException("'" + email + "' Email tersebut tidak valid.");
        }
        
        // mengecek apakah password valid atau tidak
        if(Validation.isPassword(password)){
            vPassword = true;
        }else{
            throw new InValidUserDataException("'" + password + "' Password tersebut tidak valid.");
        }
        
        return vIdUser && vNoHp && vEmail && vPassword;
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
     * Jika file <code>login_data.haqi</code> yang digunakan untuk menyimpan login data tidak ditemukan 
     * maka akan menyebabkan force close pada Aplikasi.Data yang ada didalam login data sangatlah simple 
     * karena hanya berisi id login dan id dari user saja.
     * <br><br>
     * <b>Contoh Login Data : </b> Ju09pzi4/6156 (id login / id user).
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
     * <br><br>
     * Sebelum mengambil data id login dan id user yang ada didalam login data method akan mengecek apakah token yang ada
     * didalam object {@code StringTokenizer} berjumlah dua token atau tidak. Jika jumlah tokennya != 2 maka login data 
     * dinyatakan tidak valid dan method akan mengembalikan nilai <code>False</code>. Jika jumlah tokenya sama dengan 
     * dua maka method akan mendapatkan data id login dan id user yang ada di dalam login data. 
     * <br><br>
     * Selanjutnya method akan mengecek id login dan id user yang dari login data apakah ada atau tidak didalam <b>Database</b>. 
     * Jika id login atau id user tidak ada didalam <b>Database</b> maka login data dianggap tidak valid dan user juga akan 
     * dianggap belum melakukan login. Tetapi jika id login dan id user ada didalam <b>Database</b> maka method akan mengecek 
     * apakah id user dari id login yang ada didalam login data apakah match/sama dengan id user yang ada didalam <b>Database</b>.
     * <br><br>
     * Jika id user dari id login yang ada didalam login data match/sama dengan id user yang ada didalam <b>Database</b>
     * maka user dianggap sudah melalukan login dan method akan mengembalikan nilai <code>True</code>.
     * 
     * @return <strong>True</strong> jika user sudah melakukan login. <br>
     *         <strong>False</strong> jika user belum melakukan login.
     */
    public final boolean isLogin(){
        
        // object dan variabel digunakan untuk mengecek 
        String idLogin, idUser, loginData = this.getLoginData();
        StringTokenizer token;
        
        // jika login data tidak kosong
        if(loginData != null){
            // menginputkan login data kedalam StringTokenizer
            token = new StringTokenizer(loginData, "/");
            // mengecek apakah jumlah token sama dengan 2 atau tidak
            if(token.countTokens() == 2){
                // mendapatkan data dari loginData
                idLogin = token.nextToken();
                idUser = token.nextToken();
                // mengecek apakah idLogin dan idUser dari loginData exist atatu tidak
                if(isExistIdLogin(idLogin) && this.isExistUser(idUser)){
                    // mengecek apakah id user yg ada didalam login data apakah sama dgn yg ada didalam DB
                    return isMatchLogin(idLogin, idUser);
                }
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
        if(this.isLogin()){
            throw new AuthenticationException("Anda sudah login dengan akun '" + this.getCurrentLogin() + "'");
        } 
        
        // mengecek apakah idUser dan password valid atau tidak
        if(this.validateLogin(idUser, password)){
            // membuat sebuah id login baru 
            idLogin = this.createIdLogin();
            // membuat login data baru
            newLoginData = idLogin + "/" + idUser;
            Log.addLog("Melakukan Login dengan ID Login : '" + idLogin + "' dan dengan ID User : '"+ idUser +"'");
            
            // menyimpan login data kedalam file
            BufferedWriter save = new BufferedWriter(new FileWriter(this.LOGIN_DATA_FILE));
            save.write(newLoginData);
            save.flush();
            save.close();
            
            // menyimpan login data ke dalam tabel login yang ada didalam Database
            pst = this.conn.prepareStatement("INSERT INTO login VALUES (?, ?, ?)");
            pst.setString(1, idLogin);
            pst.setString(2, idUser);
            pst.setString(3, new Waktu().getCurrentDateTime());
            
            // true jika login data berhasil ditambahkan dan user storage berhasil dibuat
            return pst.executeUpdate() > 0 && this.createUserStorage(idUser);
        }
        return false;
    }
    
    /**
     * Digunakan untuk mengecek apakah id user dan password valid atau tidak. Jika salah satu data dari id user 
     * atau password ada yang tidak valid maka semua data akan dianggap tidak valid dan method akan mengembalikan 
     * nilai <code>False</code>. Method akan mengembalikan nilai <code>True</code> jika id user dan password valid.
     * 
     * @param idUser ID dari user yang akan dicek.
     * @param password password dari user yang akan dicek.
     * 
     * @return <strong>True</strong> jika id user dan password valid. <br>
     *         <strong>False</strong> jika id user atau password tidak valid.
     * 
     * @throws AuthenticationException jika id user atau password tidak valid.
     */
    private boolean validateLogin(String idUser, String password) throws AuthenticationException{

        // mengecek apakah id user valid atau tidak
        if(!new Text().isNumber(idUser)){
            throw new AuthenticationException("ID User harus berupa Angka.");
        }else if(!Validation.isIdUser(Integer.parseInt(idUser))){
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
     * Digunakan untuk mengecek apakah direktori storage dari user exist atu tidak. Pertama-tama method 
     * akan mendapatkan direktori dari storage user melalui method {@code getUserStorage()}. Setelah 
     * direktori dari storage user berhasil didapatkan selanjutnya method akan mengecek apakah direktori 
     * tersebut exist atau tidak dengan menggunakan method {@code exists()} yang ada didalam class {@code File}
     * 
     * @param idUser id user yang akan dicek.
     * @return <strong>True</strong> jika storage dari user exist. <br>
     *         <strong>False</strong> jika storage dari user tidak exist.
     */
    public final boolean isExistUserStorage(String idUser){
        return new File(this.getUserStorage(idUser)).exists();
    }
    
    /**
     * Method ini digunakan untuk membuat folder storage dari user. Pertama-tama method akan mengecek apakah 
     * direktori dari storage user sudah exist atau belum. Jika sebelumnya direktori storage dari user sudah 
     * exist maka method akan mengembalikan nilai <code>True</code>. Tetapi sebelumnya direktori dari user
     * belum exist maka storage dari user akan dibuat melalui method {@code createFolders()} yang ada didalam
     * class {@code FileManager}. Jika storage dari user berhasil dibuat maka method akan mengembalikan nilai 
     * <code>True</code>.
     * 
     * @param idUser id user yang akan dicek.
     * @return <strong>True</strong> jika storage berhasil dibuat. <br>
     *         <strong>False</strong> jika storage tidak berhasil dibuat.
     */
    public final boolean createUserStorage(String idUser){
        Log.addLog("Membuat user storage dari '" + idUser + "'.");
        if(this.isExistUserStorage(idUser)){
            return true;
        }else{
            return new FileManager().createFolders(this.getUserStorage(idUser));
        }
    }
    
    /**
     * Method ini digunakan untuk memdapatakan direktori storage dari id user yang diinputkan. Kegunaan 
     * direktori storage dari user adalah untuk menyimpan data-data dari user. Pertama-tama method akan 
     * mendapatkan direktori dari Storage melalui method {@code getUsersDirectory()} yang ada didalam class
     * {@code Storage}. 
     * <br><br>
     * Selanjutnya method akan mendapatakan nama dari user melalui method {@code getNameOfIdUser()}. Jika nama dari 
     * user sudah didapatkan selanjutnya method akan mengabungkan direktori dari Storage, id user dan nama dari user 
     * untuk mendapatkan direktori dari user storage.
     * <br><br>
     * <b>Contoh Direktori : </b> C:\Users\Infinite World\AppData\Local\Punya Haqi\SPP Payment 1.0.0\Users\6156_achmad_baihaqi.
     * 
     * @param idUser id dari user.
     * @return akan mengembalikan direktori storage dari id user yang diinputkan.
     */
    public final String getUserStorage(String idUser){
        return new Storage().getUsersDir() + idUser + ("_" + getNameOfIdUser(idUser).replaceAll(" ", "_").toLowerCase());
    }
    
    /**
     * Digunakan untuk membuat sebuah ID Login baru. Method akan membuat sebuah ID Login dengan menggunakan 
     * method {@code randomUUID()} yang ada didalam class {@code UUID}. Output dari method tersebut adalah 
     * sebuah String dengan nilai random. Mehotd akan mengambil delapan karakter pertama dari String tersebut.
     * <br><br>
     * Setelah ID Login didapatkan method akan mengecek apakah ID Login tersebut sudah exist atau belum. Jika 
     * ID Login belum exist maka method akan mengembalikan ID Login tersebut dalam bentuk string. Tetapi jika
     * ID Login sudah exist maka method akan membuat ID Login baru.
     * <br><br>
     * <b>Contoh ID Login : </b> Zq0P38Dk
     * 
     * @return ID Login baru dalam bentuk String.
     */
    private String createIdLogin(){
        Log.addLog("Membuat ID Login baru.");
        // membuat id login dengan bantuan dari class UUID
        String idLogin = UUID.randomUUID().toString().substring(0, 8).replaceAll("-", "");
        
        // jika id login belum exist maka method akan mengebalikan id login
        if(!this.isExistIdLogin(idLogin)){
            return idLogin;
        }
        // membuat id login baru jika id login sudah exist
        return createIdLogin();
    }
    
    /**
     * Digunakan untuk mengecek apakah sebuah ID Login sudah exist atau belum. Pertama-tama method
     * akan mengecek apakah ID Login yang diinputkan sudah valid atau tidak dengan menggunakan 
     * method {@code isIdLogin()} yang ada didalam class {@code Validation}. Jika ID Login tidak 
     * valid maka method akan menghasilkan error {@code InValidUserDataException}. Tetapi jika 
     * ID Login valid maka method akan mengecek apakah ID Login sudah exist atau belum dengan 
     * menggunakan method {@code isExistData()} yang ada didalam class {@code Database}.
     * 
     * @param IdLogin ID Login yang akan dicek.
     * @return <strong>True</strong> jika ID Login exist. <br>
     *         <strong>False</strong> jika ID Login belum exist.
     */
    private boolean isExistIdLogin(String IdLogin){
        // mengecek apakah id login valid atau tidak
        if(Validation.isIdLogin(IdLogin)){
            return this.isExistData(DatabaseTables.LOGIN.name(), UserData.ID_LOGIN.name(), IdLogin);
        }
        // akan menghasilkan error jika id login tidak valid
        throw new InValidUserDataException("'" + IdLogin + "' ID Login tersebut tidak valid");
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
     * melakukan login method akan mendapatkan ID User yang berada didalam login data. ID User yang 
     * ada didalam login data akan didapatkan melalui method {@code nextToken()} yang ada didalam 
     * class {@code StringTokenizer()}.
     * <br><br>
     * <b>Example : </b> 6156
     * 
     * @return ID User dari akun yang sedang digunakan untuk Login.
     */
    public final String getCurrentLogin(){
        // mengecek apakah user sudah login atau belum
        if(this.isLogin()){
            // membaca data yang ada dialam variabel loginData
            StringTokenizer token = new StringTokenizer(getLoginData(), "/");
            // membuang data dari ID Login
            token.nextToken(); 
            // mengembalikan data ID User dari akun yang sedang digunakan untuk Login
            return token.nextToken();
        }            
        return null;
    }
    
    /**
     * Method ini digunakan untuk mengecek apkah ID User dari ID Login yang ada didalam login data apakah 
     * cocok/match dengan ID User dari ID Login yang ada didalam <b>Database</b>. Method akan mendapatkan 
     * ID user dari ID Login yang ada didalam <b>Database</b> berdasarkan ID Login yang diinputkan. 
     * Jika ID User yang ada didalam <b>Database</b> sudah didapatkan maka selanjutnya method akan mengecek 
     * apakah ID user yang ada didalam login data cocok/match dengan ID User yang ada didalam 
     * <b>Database</b> atau tidak.
     * 
     * @param idLogin ID Login yang ada didalam <b>Database</b>.
     * @param idUser ID User yang akan dicek.
     * @return <strong>True</strong> jika cocok. <br>
     *         <strong>False</strong> jika tidak cocok.
     */
    private boolean isMatchLogin(String idLogin, String idUser){
        return
        // mendapatkan data dari id_user yang ada didalam database        
        this.getData(DatabaseTables.LOGIN.name(), UserData.ID_USER.name(), "WHERE " + UserData.ID_LOGIN + " = '" + idLogin +"'")
            // mengecek apakah idUser yang ada didalam login data apakah cocok dengan yang ada didalam database    
            .equals(idUser);
    }
    
    /**
     * Method ini digunakan untuk melakukan Logout pada Aplikasi. Sebelum melogout akun method akan mengecek apakah 
     * user sudah melakukan Login atau belum. Jika user belum melakukan Login maka method akan menghasilkan 
     * exception {@code AuthenticationException}. Method akan melogout akun dengan cara menhapus login data yang 
     * ada didalam <b>Database</b> dengan menggunakan method {@code deleteData()} yang ada didalam class 
     * {@code Database}. Jika login data yang ada didalam <b>Database</b> berhasil dihapus maka Logout akan 
     * dinyatakan berhasil dan method akan mengembalikan nilai <code>True</code>.
     * 
     * @return <strong>True</strong> jika Logout berhasil. <br>
     *         <strong>False</strong> jika Logout tidak berhasil.
     * 
     * @throws AuthenticationException jika user belum melakukan login.
     */
    public final boolean logout() throws AuthenticationException{
        // mengecek apakah user sudah melakukan login atau belum
        if(isLogin()){
            Log.addLog("Melakukan Logout pada Akun dengan ID User : " + this.getCurrentLogin() + "'");
            // menghapus login data yang ada didalam database
            return this.deleteData(DatabaseTables.LOGIN.name(), UserData.ID_LOGIN.name(), this.getCurrentIdLogin());         
        }
        throw new AuthenticationException("Gagal melogout akun dikarenakan Anda belum Login.");
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
        // mengecek apakah id user yang diinputkan merupakah sebuah number atau tidak
        if(new Text().isNumber(idUser)){
            // mengecek apakah id user valid atau tidak
            if(Validation.isIdUser(Integer.parseInt(idUser))){
                return this.isExistData(DatabaseTables.USERS.name(), UserData.ID_USER.name(), idUser);
            }
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
     * Method ini digunakan untuk mengecek apakah sebuah Email sudah exist atau belum didalam <b>Database</b>.
     * Pertama-tama method akan mengecek apakah Email valid atau tidak dengan menggunakan method 
     * {@code isNoHp()} yang ada didalam class {@code Validation}. Jika Email tidak valid maka method 
     * akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Method akan mengecek apakah sebuah Email sudah exist atau belum didalam <b>Database</b> dengan 
     * menggunakan method {@code isExistData()} yang ada didalam class {@code Database}. Jika output dari 
     * method tersebut adalah <code>True</code> maka Email dinyatakan exist.
     * 
     * @param email email yang akan dicek.
     * @return <strong>True</strong> jika Email exist. <br>
     *         <strong>False</strong> jika Email HP tidak exist.
     */
    public final boolean isExistEmail(String email){
        // mengecek apakah email valid atau tidak
        if(Validation.isEmail(email)){
            return this.isExistData(DatabaseTables.USERS.name(), UserData.EMAIL.name(), email);
        }
        // akan menghasilkan error jika email tidak valid
        throw new InValidUserDataException("'" + email + "' Email tersebut tidak valid.");
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
            return this.getData(DatabaseTables.USERS.name(), data.name(), " WHERE "+ UserData.ID_USER.name() +" = " + idUser);
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
     * Method ini digunakan untuk mendapatkan data last online dari user berdasarkan ID User yang diinputkan.
     * Sebelum mendapatkan data method akan mengecek apakah ID User yang inputkan exist atau tidak dengan menggunakan 
     * method {@code isExistUser()}. Jika output dari method tersebut adalah <code>False</code> maka ID User dinyatakan 
     * tidak valid dan method akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Method akan mendapatkan data last online dari user dengan menggunakan method {@code getData()} yang ada didalam
     * class {@code Database}.
     * 
     * @param idUser ID User yang ingin didapatkan datanya.
     * @return data last online dari user.
     */
    public String getLastOnline(String idUser){
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mendapatkan data last online dari user
            return this.getData(DatabaseTables.LOGIN.name(), UserData.LAST_ON.name()," WHERE "+ UserData.ID_USER.name() +" = " + idUser);
        }
        // akan menghasilkan error jika id user tidak ditemukan
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");            
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data last online berdasarkan ID User dari akun yang sedang digunakan 
     * untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
     * 
     * @return data last online dari user yang sedang login.
     */
    public String getLastOnline(){
        return this.getLastOnline(getCurrentLogin());
    }
    
    /**
     * Method ini digunakan untuk mengedit data last online berdaskarkan ID User dan Date Time yang diinputkan.
     * Sebelum mengedit data method akan mengecek apakah ID User exist atau tidak. Jika ID User tidak exist maka 
     * akan menghasilkan exception {@code InValidUserDataException}. Tetapi jika ID User exist maka method akan 
     * mengedit data dengan menggunakan method {@code setData()} yang ada didalam class {@code Database}. Jika data 
     * last online berhasil diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * @param dateTime Date time dari last online.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean updateLastOnline(String idUser, String dateTime){
        // mengecek apakah id user exist atau tidak
        if(this.isExistUser(idUser)){
            // mengedit data last online dari user
            return this.setData(DatabaseTables.LOGIN.name(), UserData.LAST_ON.name(), UserData.ID_USER.name(), idUser, dateTime);
        }
        // akan menghasilkan error jika id user tidak ditemukan
        throw new InValidUserDataException("'" +idUser + "' ID User tersebut tidak dapat ditemukan.");          
    }
    
    /**
     * Method ini digunakan untuk mengedit data last online berdasarkan ID User yang diinputkan. Data last 
     * online akan diset pada date time saat ini. Method akan mengedit data last online dari user dengan 
     * menggunakan method {@code updateLastOnline(String idUser, String dateTime)}. Jika data last online 
     * berhasil diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean updateLastOnline(String idUser){
        // mengedit data last online dengan tanggal dan waktu saat ini
        return this.updateLastOnline(idUser, new Waktu().getCurrentDateTime());
    }
    
    /**
     * Method ini digunakan untuk mengedit data last online berdasarkan ID User dari akun yang sedang digunakan 
     * untuk Login. Data last online akan diset pada date time saat ini. Method akan mengedit data last online 
     * dari user dengan menggunakan method {@code updateLastOnline(String idUser)}. Jika data last online 
     * berhasil diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean updateLastOnline(){
        return this.updateLastOnline(getCurrentLogin());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data dari nama user berdasarkan ID User yang diinputakan.
     * Pertama-tama method akan mengecek apakah user memiliki level Admin atau Petugas. Jika user memiliki 
     * level Admin atau Petugas maka data dari nama user akan diambil dari tabel petugas yang ada didalam 
     * <b>Database</b>. Tetpai jika user memiliki level Siswa maka data dari nama user akan diambil dari 
     * tabel siswa yang ada didalam <b>Database</b>.
     * 
     * @param idUser ID User yang dinggin diambil namanya.
     * @return akan mengembalikan nama dari ID User yang diinputkan.
     */
    private String getNameOfIdUser(String idUser){
        String name = "";
        // mengecek apakah user memiliki level admin atau petugas
        if(this.isAdmin(idUser) || this.isPetugas(idUser)){
            // mendapatkan data dari nama user yang ada didalam tabel petugas
            name = this.getData(DatabaseTables.PETUGAS.name(), PetugasData.NAMA_PETUGAS.name(), "WHERE " + PetugasData.ID_PETUGAS + " = " + idUser);
        }else if(this.isSiswa(idUser)){
            // mendapatkan data dari nama user yang ada didalam tabel siswa
            name = this.getData(DatabaseTables.SISWA.name(), SiswaData.NAMA_SISWA.name(), "WHERE " + SiswaData.NIS + " = " + idUser);
        }
        return name;
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
     * Method ini digunakan untuk mendapatkan data Email dari user berdasarkan ID User yang diinputkan. 
     * ID User yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User yang diinputkan ternyata 
     * tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception {@code InValidUserDataException}. 
     * Method hanya akan mendapatkan data Email dari user jika ID User yang diinputkan terdaftar didalam <b>Database</b>.
     * 
     * @param idUser ID User yang ingin didapatkan datanya.
     * @return data Email dari ID User yang diinputkan.
     */
    public String getEmail(String idUser){
        return this.getUserData(idUser, UserData.EMAIL);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan data Email dari user berdasarkan ID User dari akun yang sedang 
     * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
     * Selanjutnya method akan mendapatkan data Email dari user melalui method {@code getEmail(String idUser)}.
     * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
     * 
     * @return data Email dari akun yang sedang Login.
     */
    public String getEmail(){
        return this.getEmail(getCurrentLogin());
    }
    
    /**
     * Digunakan untuk mengedit data Email dari user berdasarkan ID User yang diinputkan. Sebelum mengedit 
     * data Email method akan mengecek apakah Email yang diinputkan valid atau tidak dengan menggunakan 
     * method {@code idEmail(String email)} yang ada didalam class {@code Validation}. Jika Email tidak valid
     * maka method akan menghasilkan exception {@code InValidUserDataException}.
     * <br><br>
     * Jika Email valid maka selanjutnya method akan mengecek apakah Email sudah terpakai atau belum dengan 
     * menggunakan method {@code isExistEmail}. Jika Email sudah ada yang memakai maka method akan menghasilkan 
     * exception {@code InValidUserDataException}. 
     * <br><br>
     * Tetapi jika Email belum ada yang memakai maka data Email dari user akan diedit. 
     * Jika data dari Email berhasil diedit maka method akan mengembalikan nilai <code>True</code>.
     * 
     * @param idUser ID User yang ingin diedit datanya.
     * @param newEmail data Email yang baru.
     * 
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setEmail(String idUser, String newEmail){
        // mengecek apakah email valid atau tidak
        if(Validation.isEmail(newEmail)){
            // mengecek apakah email sudah dipakai atau belum
            if(!this.isExistEmail(newEmail)){
                // mengedit data email dari user
                return this.setUserData(idUser, UserData.EMAIL, newEmail);
            }else{
                // akan menghasilkan error jika email sudah dipakai
                throw new InValidUserDataException("'" + newEmail + "' Email tersebut sudah dipakai.");
            }
        }
        // akan menghasilkan error jika email tidak valid
        throw new InValidUserDataException("'" + newEmail + "' Email tersebut tidak valid.");
    }
    
    /**
     * Digunakan untuk mengedit data Email dari user berdasarkan ID User dari akun yang sedang digunakan untuk Login. 
     * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
     * akan mengedit data Email dari user melalui method {@code setEmail(String idUser, String newEmail)}. Jika
     * output dari method tersebut adalah <code>True</code> maka data Email dari user berhasil diedit.
     * 
     * @param newEmail data Email yang baru.
     * @return <strong>True</strong> jika data berhasil diedit. <br>
     *         <strong>False</strong> jika data tidak berhasil diedit.
     */
    public boolean setEmail(String newEmail){
        return this.setEmail(getCurrentLogin(), newEmail);
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
    private String getNameOfPhoto(String idUser){
        // mendapatkan nama file dari foto berdasarkan id user
        String name = this.getNameOfIdUser(idUser);
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
    
    /**
     * Method ini digunakan untuk mendapatkan direktori dari Foto User yang sudah didownload dalam bentuk object class 
     * {@code File} berdasarkan ID User yang diinputkan. Method akan mendapatkan direktori dari Foto User dengan melalui 
     * method {@code downloadPhoto()}. Jika terjadi kesalahan/error saat memanggil method {@code downloadPhoto()} maka method 
     * akan mengembalikan direktori dari default Foto User. Direktori dari default Foto User akan didapatkan melalui 
     * method {@code getDefaultPhoto()}.
     * 
     * @param idUser ID User yang ingin didapatkan fotonya.
     * @return Direktori dari Foto User.
     */
    private File getPhoto(String idUser){
        try {
            return this.downloadPhoto(idUser);
        } catch (SQLException | IOException ex) {
            Message.showException(this, "Gagal mendapatkan Foto dari User!", ex, true);
        }
        return this.getDefaultPhoto();
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori dari Foto User yang sudah didownload dalam bentuk object class 
     * {@code ImageIcon} berdasarkan ID User yang diinputkan dan dengan ukuran dari Foto User yang telah diubah. Method akan 
     * mendapatkan direktori dari Foto user dengan menggunakan method {@code getPhoto(String idUser)}. Setelah direktori didapatkan 
     * maka selanjutnya method akan mengubah ukuran lebar dan tinggi dari Foto berdasarkan lebar dan tinggi yang diinputkan pada 
     * parameter method. 
     * <br><br>
     * Ukuran lebar dan tinggi dari Foto User akan diubah dengan melalui method {@code resizeImage()} yang ada didalam class 
     * {@code Gambar}. Setelah ukuran lebar dan tinggi dari Foto User berhasil diubah maka method akan mengembalikan direktori
     * dari Foto User yang telah diubah ukuranya tersebut.
     * 
     * @param idUser ID User yang ingin didapatkan fotonya.
     * @param width lebar dari Foto User.
     * @param height tinggi dari Foto User.
     * 
     * @return Direktori dari Foto User yang telah diubah ukuranya.
     */
    public final ImageIcon getPhoto(String idUser, int width, int height){
        return new ImageIcon(Gambar.resizeImage(this.getPhoto(idUser), width, height).toString());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori dari Foto User yang sudah didownload dalam bentuk object class 
     * {@code ImageIcon} berdasarkan ID User yang sedang digunakan untuk login dan dengan ukuran dari Foto User yang telah diubah. 
     * Method akan mendapatkan ID User yang sedang digunakan untuk login dengan menggunakan methot {@code getCurrentLogin()}. 
     * Sedangkan ukuran dari Foto User akan diubah dengan melalui method {@code getPhoto(String idUser, int width, int height)}.
     * 
     * @param width lebar dari Foto User.
     * @param height tinggi dari Foto User.
     * 
     * @return Direktori dari Foto User yang telah diubah ukuranya.
     */
    public final ImageIcon getPhoto(int width, int height){
        return this.getPhoto(this.getCurrentLogin(), width, height);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori dari Foto User yang sudah didownload dalam bentuk object class 
     * {@code ImageIcon} berdasarkan ID User yang diinputkan dan dengan ukuran dari Foto User yang telah diubah. Method akan 
     * mendapatkan direktori dari Foto user dengan menggunakan method {@code getPhoto(String idUser)}. Setelah direktori didapatkan 
     * maka selanjutnya method akan mengubah ukuran lebar berdasarkan parameter <code>size</code> pada method.
     * <br><br>
     * Parameter <code>size</code> pada method ini adalah sebuah class {@code UserPhotoSize} yang merupakan class untuk 
     * meyimpan ukuran tinggi dan lebar dari Foto User dalam bentuk <code>enum</code>. Method akan mendapatkan ukuran lebar 
     * dan tinggi dari enum dengan menggunakan method {@code getWidth()} dan {@code getHeight()} yang ada didalam 
     * class {@code UserPhotoSize}.
     * <br><br>
     * Ukuran lebar dan tinggi dari Foto User akan diubah dengan melalui method {@code resizeImage()} yang ada didalam class 
     * {@code Gambar}. Setelah ukuran lebar dan tinggi dari Foto User berhasil diubah maka method akan mengembalikan direktori
     * dari Foto User yang telah diubah ukuranya tersebut.
     * 
     * @param idUser ID User yang ingin didapatkan fotonya.
     * @param size ukuran dari Foto User.
     * 
     * @return Direktori dari Foto User yang telah diubah ukuranya.
     */
    public final ImageIcon getPhoto(String idUser, UserPhotoSize size){
        return this.getPhoto(idUser, UserPhotoSize.getWidth(size), UserPhotoSize.getHeight(size));
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori dari Foto User yang sudah didownload dalam bentuk object class 
     * {@code ImageIcon} berdasarkan ID User yang sedang digunakan untuk login dan dengan ukuran dari Foto User yang telah diubah. 
     * Method akan mendapatkan ID User yang sedang digunakan untuk login dengan menggunakan methot {@code getCurrentLogin()}. 
     * Sedangkan ukuran dari Foto User akan diubah dengan melalui method {@code getPhoto(String idUser, UserPhotoSize size)}.
     * 
     * @param size ukuran dari foto user.
     * @return Direktori dari Foto User yang telah diubah ukuranya. 
     */
    public final ImageIcon getPhoto(UserPhotoSize size){
        return this.getPhoto(this.getCurrentLogin(), size);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan Foto Profile dari User berdasarkan ID User yang diinputkan. Method akan 
     * mendapatkan Foto Profile dari User dengan menggunakan method {@code getPhoto()} dengan ukuran yang disesuaikan dengan 
     * ukuran Foto Profile. Ukuran dari Foto Profile dibagi menjadi dua yaitu <code>FOTO_PROFILE_PETUGAS</code> yang digunakan 
     * untuk User dengan level <b>ADMIN</b> atau <b>PETUGAS</b>. Dan <code>FOTO_PROFILE_SISWA</code> yang digunakan untuk user dengan 
     * level <b>SISWA</b>.
     * <br><br>
     * Sebelum mendapatkan Foto Profile dari User method akan mengecek apakah user memiliki level <b>ADMIN</b> atau <b>PETUGAS</b>
     * atau tidak. Jika User memiliki level <b>ADMIN</b> atau <b>PETUGAS</b> maka Foto Profile akan didapatkan dengan melalui 
     * method {@code getPhoto()} dengan ukuran <code>FOTO_PROFILE_PETUGAS</code>.
     * <br><br>
     * Tetapi jika User tidak memiliki level <b>ADMIN</b> atau <b>PETUGAS</b> maka method akan mengecek apakah User tersebut 
     * memiliki level <b>SISWA</b> atau tidak. Jika User memiliki level <b>SISWA</b> maka Foto Profile akan didapatkan dengan melalui 
     * method {@code getPhoto()} dengan ukuran <code>FOTO_PROFILE_SISWA</code>.
     * 
     * @param idUser ID User yang ingin didapatkan fotonya.
     * @return Direktori dari Foto Profile User.
     */
    public final ImageIcon getPhotoProfile(String idUser){
        // jika user memiliki level admin atau petugas
        if(this.isAdmin(idUser) || this.isPetugas(idUser)){
            // mendapatkan foto profile dari user
            return this.getPhoto(idUser, UserPhotoSize.FOTO_PROFILE_PETUGAS);
        }
        // jika user memiliki level siswa
        else if(this.isSiswa(idUser)){
            // mendapatkan foto profile dari user
            return this.getPhoto(idUser, UserPhotoSize.FOTO_PROFILE_SISWA);
        }
        return new ImageIcon(getPhoto(idUser).toString());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan Foto Profile dari User bedasarkan ID User yang sedang digunakan untuk login 
     * saat ini dalam bentuk object dari class {@code ImageIcon}. Method akan mendapatkan ID User yang sedang digunakan untuk login 
     * dengan menggunakan method {@code getCurrentLogin()}. Sedangakan Foto Profile akan didapatkan dengan melalui method 
     * {@code getPhotoProfile()}.
     * 
     * @return Direktori dari Foto Profile User.
     */
    public final ImageIcon getPhotoProfile(){
        return this.getPhotoProfile(getCurrentLogin());
    }
    
    /**
     * Method ini digunakan untuk mengedit sebuah Foto dari User. Method akan mengedit Foto dari User dengan menggunakan 
     * class {@code PreparedStatement}. Sebelum mengedit Foto dari User method akan mengecek apakah parameter newImg yang
     * digunakan untuk menampung file Foto User yang baru apakah parameter tersebut kosong atau tidak. Jika parameter kosong 
     * maka Foto User akan diset ke default Foto User.
     * <br><br>
     * Jika Foto User tidak kosong maka selanjutnya method akan mengkonversi file Foto User yang baru tersebut kedalam bentuk 
     * blob/byte stream dengan menggunakan method {@code fileToBlob()} yang ada didalam class {@code FileManager()}. Setelah 
     * file berhasil dikoversi kedalam bentuk blob/byte stream maka selanjutnya method akan mengedit Foto User dengan menggunakan 
     * method {@code executeUpdate()} yang ada didalam class {@code PreparedStatement}.
     * <br><br>
     * Jika proses pengeditan Foto User berhasil maka selanjutnya method akan menghapus cache dari Foto User lama yang ada didalam 
     * folder cache didalam storage aplikasi. Setelah cache dari Foto User yang lama berhasil dihapus maka proses pengeditan 
     * Foto User dinyatakan berhasil dan method akan mengembalikan nilai <code>true</code>
     * 
     * @param idUser ID User yang ingin diedit fotonya.
     * @param newImg Foto User yang baru.
     * 
     * @return <strong>True</strong> jika proses pengeditan berhasil. <br>
     *         <strong>False</strong> jika proses pengeditan gagal.
     */
    public final boolean setPhoto(String idUser, File newImg){
        PreparedStatement pst;
        boolean result;
        
        try {
            // mengedit foto yang ada didalam database
            pst = super.conn.prepareStatement("UPDATE users SET foto = ? WHERE id_user = " + idUser);
            // jika file newImg tidak kosong maka foto akan diedit
            if(newImg != null){
                // mengkonversi file menjadi blob
                pst.setBlob(1, new FileManager().fileToBlob(newImg));
            }else{
                pst.setString(1, null);
            }
            
            // mengedit foto
            result = pst.executeUpdate() > 0;
            
            // jika foto yang ada didalam database berhasil diedit maka cache foto akan dihapus
            if(result){
                // menghapus cache foto
                this.removeCachePhoto(idUser);
                // mengembalikan nilai true
                return true;
            }
        } catch (IOException | SQLException ex) {
            Message.showException(this, "Gagal mengedit Foto dari User!", ex, true);
        }
        return false;
    }
    
    /**
     * Method ini digunakan untuk menghapus sebuah Foto User yang sebelumnya diunggah/diupload ke dalam <b>Database</b>.
     * Foto User sebenarnya tidak akan benar-benar dihapus melainkan Foto User akan diatur ke default Foto User. 
     * Method akan menghapus Foto User dengan menggunakan method {@code setPhoto()} dengan paremeter file foto diisi 
     * dengan nilai <code>null</code>.
     * 
     * @param idUser ID User yang ingin dihapus fotonya.
     * @return <strong>True</strong> jika foto berhasil dihapus. <br>
     *         <strong>False</strong> jika foto gagal dihapus.
     */
    public final boolean removePhoto(String idUser){
        return this.setPhoto(idUser, null);
    }
    
    /**
     * Method ini digunakan untuk menghapus cache-cache dari Foto User berdasarkan ID User yang diinputakn. Cache
     * dari Foto User perlu dihapus saat user mengedit Foto mereka. Jika cache tidak dihapus maka cache dari Foto User yang 
     * baru tidak akan dibuat dan hal ini akan menyebabkan bug pada aplikasi. Pertama-tama method akan mendapatkan data semua 
     * file cache yang didalam folder cache\\pictures\\resized pada storage aplikasi dengan menggunakan method 
     * {@code getListFile()} yang ada didalam class {@code FileManager} untuk dihapus.
     * <br><br>
     * Sebelum menghapus file cahce yang ada didalam folder cache\\pictures\\resized method akan menghapus cache file foto 
     * original yang ada didalam folder cache\\pictures dengan menggunakan method {@code deleteFile()} yang ada didalam class
     * {@code FileManager()}. Selanjutnya method akan menghapus semua file cache dari foto yang nama filenya diawali dengan 
     * id user yang diinputkan.
     * 
     * @param idUser ID User yang ingin dihapus cachenya.
     * @throws IOException akan terjadi error jika direktori dari folder cache tidak ditemukan.
     */
    private void removeCachePhoto(String idUser) throws IOException{
        
        String file;
        FileManager fm = new FileManager();
        // mendapatkan semua direktori dari file cache untuk dihapus
        Object[] dirs = fm.getListFile(new Storage().getCacheDir()+"\\pictures\\resized");
        
        // menghapus foto original
        fm.deleteFile(this.getPhotoDir(idUser));
        
        // membaca direktori dari foto resized yang ada didalam array files
        for(Object o : dirs){
            // mendapatkan nama dari file
            file = fm.getNamaFile(o.toString());
            // jika nama dari file id_user-nya sama dengan id user yg diinputkan maka file akan dihapus
            if(file.substring(0, file.indexOf("_")).equalsIgnoreCase(idUser)){
                // menghapus file cache
                Log.addLog("Menghapus file cache " + file);
                fm.deleteFile(o.toString());
            }
        }
    }
    
    /**
     * Class ini digunakan untuk segala sesuatu yang berhubungan dengan akun dari user yang memiliki level <i>ADMIN</i> atau  
     * <i>PETUGAS</i> seperti memanipulasi atau mendapatkan data dari akun. Class ini merupakah inheritance dan sekaliguas 
     * inner class dari class {@code Users}. Oleh karena itu object dari class ini juga dapat memanggil method-method yang 
     * ada didalam class {@code Users}.
     * <br><br>
     * Class ini berfokus untuk menangani segala sesuatu yang berhubungan dengan akun dari user yang memiliki level <i>ADMIN</i> 
     * atau <i>PETUGAS</i> saja. Method-method yang ada didalam class ini juga hampir sama dengan method-method yang ada didalam 
     * class {@code Users}. Cara class memanipulasi atau mendapatkan data dari akun yang memiliki level <i>ADMIN</i> atau 
     * <i>PETUGAS</i> juga hapir sama dengan cara yang dilakukan oleh class {@code Users}.
     * <br><br>
     * Yaitu class akan memanfaatkan method-method yang ada didalam claas {@code Database}. Kita hanya perlu menginputkan 
     * id user / id petugas dari akun user untuk memanipulasi atau mendapatkan data dari akun user yang memiliki level 
     * <i>ADMIN</i> atau <i>PETUGAS</i>. Class juga  akan mengecek apakah data yang diinputkan valid atau tidak.
     * <br><br>
     * Class ini juga dapat digunakan untuk menambahkan atau menghapus sebuah akun yang memiliki level <i>ADMIN</i> 
     * atau <i>PETUGAS</i> dari <b>Database</b> aplikasi. Cara kerja class untuk menambahkan atau menghapus sebuah akun 
     * dari <b>Database</b> hapir sama dengan cara kerja menambahkan atau menghapus sebuah akun pada class {@code User}.
     * <br><br>
     * Selama menggunakan class ini mungkin akan akan sering menemui runtime/checked exception. Salah-satu exception yang 
     * mungkin nantinya akan sering anda jumpai adalah {@code InValidUserDataException}. Exception tersebut akan sering dijumpai 
     * saat sedang memanipulasi atau mendapatkan data dari akun user yang memiliki level <i>ADMIN</i> atau <i>PETUGAS</i>. 
     * <br><br>
     * Exception {@code InValidUserDataException} merupakan sebuah runtime exception. Oleh karena itu disaat akan memanipulasi 
     * atau mendapkan data dari user yang memiliki level <i>ADMIN</i> atau <i>PETUGAS</i> disarankan untuk membuat 
     * block try catch untuk menangkap pesan error dari exception. Jika tidak ditangkap menggunakan block try catch maka ada 
     * kemungkinan aplikasi akan force close.
     * 
     * @author Achmad Baihaqi
     * @since 2021-06-14
     */
    public class LevelPetugas extends Users{  
        
        /**
         * Method ini digunakan untuk menambahkan data dari User dan Petugas yang diinputkan kedalam <b>Database MySQL</b>.
         * Method akan mengembalikan nilai <code>True</code> jika data dari User dan Petugas berhasil ditambahkan kedalam 
         * <b>Database</b>. Jika ada salah satu data yang gagal ditambahkan kedalam <b>Database</b> maka method akan 
         * mengembalikan nilai <code>False</code>
         * <br><br>
         * Pertama-tama method akan menambahkan data dari User kedalam <b>Database</b>. Data dari User akan ditambahkan ke 
         * dalam <b>Database</b> dengan melalui method {@code addUser()} yang ada didalam class {@code Users}. Jika data 
         * dari User berhasil ditambahkan kedalam <b>Database</b> maka selanjutnya method akan menambahkan data Petugas 
         * kedalam <b>Database</b>
         * <br><br>
         * Sebelum menambahkan data Petugas kedalam <b>Database</b> method akan mengecek apakah semua data dari Petugas
         * yang diinputkan valid atau tidak. Jika ada salah satu data dari Petugas yang tidak valid maka data Petugas tidak akan 
         * ditambahkan kedalam <b>Database</b> dan data User yang sebelumnya sudah ditambahkan akan dihapus melalui method 
         * {@code deleteUser} yang ada didalam class {@code} Users. 
         * <br><br>
         * Jika semua data dari Petugas valid maka method akan membuat sebuah object {@code PreparedStatement} yang digunakan 
         * untuk menambahkan data Petugas kedalam <b>Database</b>. Setelah object dari class {@code PreparedStatement} berhasil 
         * dibuat selanjutnya method akan menambahkan semua data dari Petugas kedalam object {@code PreparedStatement}. 
         * <br><br>
         * Jika semua data dari Petugas sudah ditambahkan kedalam object {@code PreparedStatement} maka data dari Petugas tersebut 
         * akan ditambahkan kedalam <b>Database</b> melalui method {@code executeUpdate()} yang ada didalam class 
         * {@code PreparedStatement}. Jika data Petugas berhasil ditambahkan kedalam <b>Database</b> maka method akan megembalikan 
         * nilai <code>True</code>.
         * 
         * @param idUser id user dari user atau petugas.
         * @param noHP no hp dari user atau petugas.
         * @param email email dari user atau petugas.
         * @param profile foto dari user atau petugas.
         * @param password passworddari user atau petugas.
         * @param level level dari user atau petugas.
         * @param nama nama dari user atau petugas.
         * @param gender gender dari user atau petugas.
         * @param tempatLahir tampat lahir dari user atau petugas.
         * @param tanggalLahir tanggal lahirdari user atau petugas.
         * @param alamat alamat dari user atau petugas.
         * 
         * @return <strong>True</strong> jika data berhasil ditambahkan. <br>
         *         <strong>False</strong> jika data tidak berhasil ditambahkan. 
         * 
         * @throws FileNotFoundException jika terjadi kegagalan saat menkonversi foto kedalam byte stream / Blob.
         * @throws SQLException jika terjadi kegagalan saat menambahkan data kedalam <b>Database</b>.
         * @throws InValidUserDataException jika data dari petugas tidak valid.
         */
        public final boolean addPetugas(String idUser, String noHP, String email, File profile, String password, UserLevels level, String nama, String gender, String tempatLahir, String tanggalLahir, String alamat) 
                throws FileNotFoundException, SQLException, InValidUserDataException
        {
            // digunakan untuk menambahkan data petugas kedalam database
            PreparedStatement pst;
            boolean addUser, addPetugas = false;
            
            // menambahkan data user kedalam database
            addUser = Users.this.addUser(idUser, noHP, email, profile, password, level);
            
            // jika data user berhasil ditambahkan maka data petugas akan ditambahkan kedalam database
            if(addUser){
                Log.addLog("Akun dengan ID User '" + idUser + "' berhasil ditambahkan.");
                Log.addLog("Menambahkan data Petugas dengan ID User '"+ idUser + "' ke Database.");
                // mengecek apakah data petugas valid atau tidak
                if(this.validateAddPetugas(idUser, level, nama, gender, tempatLahir, tanggalLahir, alamat)){
                    Log.addLog("Data Petugas dengan ID User '" + idUser + "' dinyatakan valid.");
                    // menambahkan data petugas kedalam database
                    pst = Users.this.conn.prepareStatement("INSERT INTO petugas VALUES (?, ?, ?, ?, ?, ?)");
                    pst.setString(1, idUser);
                    pst.setString(2, nama);
                    pst.setString(3, gender);
                    pst.setString(4, tempatLahir);
                    pst.setString(5, tanggalLahir);
                    pst.setString(6, alamat);
                    // mengeksekusi query
                    addPetugas = pst.executeUpdate() > 0;
                    System.out.println("ADD PETUGAS : " + addPetugas);
                }
            }
            
            // jika data petugas berhasil ditambahkan maka method akan mengembalikan nilai true
            if(addPetugas){
                Log.addLog("Data Petugas dengan ID User '"+idUser+"' ditambahkan ke Database.");
                return true;
            }else{
                // jika data petugas gagal ditambahkan maka data user akan dihapus melalui mehtod deleteUser()
                Users.this.deleteUser(idUser);
                return false;
            }
        }
        
        /**
         * Method ini digunakan untuk mengecek apakah semua data dari Petugas yang diinputkan valid atau tidak.
         * Method akan mengecek satu persatu data dari Petugas. Jika ada salah satu data saja yang tidak valid maka 
         * semua data dari Petugas yang di inputkan akan dianggap tidak valid dan method akan mengembalikan nilai 
         * <code>False</code>. Method hanya akan mengembalikan nilai <code>True</code> jika semua data dari 
         * Petugas yang diinputkan valid.
         * 
         * @param idPetugas id id petugas yang akan dicek.
         * @param level level yang akan dicek.
         * @param nama nama level yang akan dicek.
         * @param gender gender level yang akan dicek.
         * @param tempatLahir tempat lahir level yang akan dicek.
         * @param tanggalLahir tanggal lahir level yang akan dicek.
         * @param alamat alamat level yang akan dicek.
         * 
         * @return <strong>True</strong> jika semua data dari Petugas valid. <br>
         *         <strong>False</strong> jika ada salah satu data dari Petugas yang tidak valid.
         */
        private boolean validateAddPetugas(String idPetugas, UserLevels level, String nama, String gender, String tempatLahir, String tanggalLahir, String alamat){
            
            boolean vPetugas, vLevel, vNama, vGender, vTmpLhr, vTglLhr, vAlamat;
            
            Log.addLog("Mengecek apakah data Petugas dengan ID User '" + idPetugas + "' valid atau tidak.");
            
            // mengecek id peugas valid atau tidak
            if(Validation.isIdPetugas(Integer.parseInt(idPetugas))){
                // mengecek apakah id petugas exist atau tidak
                if(!this.isExistPetugas(idPetugas)){
                    vPetugas = true;
                }else{
                    throw new InValidUserDataException("'" + idPetugas + "' ID Petugas tersebut sudah terpakai.");
                }
            }else{
                throw new InValidUserDataException("'" + idPetugas + "' ID Petugas tersebut tidak valid.");
            }
            
            // mengecek level valid atau tidak
            if(level.name().equalsIgnoreCase("ADMIN") || level.name().equalsIgnoreCase("PETUGAS")){
                vLevel = true;
            }else{
                throw new InValidUserDataException("Level dari Petugas harus diantara 'ADMIN' dan 'PETUGAS'.");
            }
            
            // mengecek nama petugas valid atau tidak
            if(Validation.isNamaOrang(nama)){
                vNama = true;
            }else{
                throw new InValidUserDataException("'" + nama + "' Nama tersebut tidak valid.");
            }                
            
            // mengecek gender valid atau tidak
            if(Validation.isGender(gender)){
                vGender = true;
            }else{
                throw new InValidUserDataException("Gender harus diantara 'L' atau 'P'");
            }
            
            // mengecek tempat lahir valid atau tidak            
            if(Validation.isNamaTempat(tempatLahir)){
                vTmpLhr = true;
            }else{
                throw new InValidUserDataException("Tempat Lahir tidak valid.");
            }
            
            // mengecek tanggal lahir valid atau tidak
            if(Validation.isTanggalLahir(tanggalLahir)){
                vTglLhr = true;
            }else{
                throw new InValidUserDataException("Tanggal Lahir tidak valid.");
            }

            // megecek apakah alamat valid atau tidak
            if(Validation.isNamaTempat(alamat)){
                vAlamat = true;
            }else{
                throw new InValidUserDataException("Alamat tidak valid.");
            }
            return vPetugas && vLevel && vNama && vGender && vTmpLhr && vTglLhr && vAlamat;
        }
        
        /**
         * Method ini digunakan untuk menghapus sebuah akun Petugas yang tersimpan didalam tabel <code>users</code> dan 
         * tabel <code>petugas</code> yang ada didalam <b>Database</b> berdasarkan id user yang diinputkan. Method akan menghapus 
         * data akun Petugas yang ada didalam tabel <code>users</code> dengan melalui method {@code deleteUser()} yang ada 
         * didalam class {@code Users}.
         * <br><br>
         * Method akan menghapus data akun Petugas yang ada didalam tabel <code>petugas</code> dengan menggunakan method
         * {@code deleteData()} yang ada didalam class {@code Database}. Jika kedua data Petugas yang ada didalam tabel 
         * <code>users</code> dan tabel <code>petugas</code> berhasil dihapus maka method akan mengembalikan nilai <code>True</code>s
         * 
         * @param idUser id dari user yang ingin dihapus.
         * @return <strong>True</strong> jika akun berhasil dihapus. <br>
         *         <strong>False</strong> jiak akun tidak berhasil dihapus.
         */
        public final boolean deletePetugas(String idUser){
            return Users.this.deleteUser(idUser) && 
                   Users.this.deleteData(DatabaseTables.PETUGAS.name(), PetugasData.ID_PETUGAS.name(), idUser);
        }
        
        /**
         * Method ini digunakan untuk mengecek apakah ID Petugas yang diinputkan exist atau tidak didalam tabel petugas 
         * yang ada didalam <code>Database</code>. Method akan mengembalikan nilai <code>True</code> jika ID Petugas 
         * yang diinputkan exist. Pertama-tama method akan mengecek apakah ID User dari Petugas yang diinputkan valid atau tidak
         * jika ID User dari Petugas tidak valid maka akan mengembalikan nilai <code>False</code>. Tetapi jika ID User dari Petugas valid maka 
         * method akan mengecek exist atau tidanya ID User dari Petugas yang diinputkan dengan melalui method <code>isExistData()</code>
         * yang ada didalam class {@code Database}.
         * 
         * @param idUser ID User dari Petugas yang akan dicek.
         * @return <strong>True</strong> jika ID User dari Petugas exist. <br>
         *         <strong>False</strong> jika ID User dari Petugas tidak exist.
         */
        public final boolean isExistPetugas(String idUser){
            // mengecek apakah id petugas valid atau tidak
            if(Validation.isIdPetugas(Integer.parseInt(idUser))){
                // mengecek apakah id petugas exist atau tidak
                return super.isExistData(DatabaseTables.PETUGAS.name(), PetugasData.ID_PETUGAS.name(), idUser);
            }
            return false;
        }
    
        /**
         * Method ini akan mengembalikan data dari Petugas berdasarkan ID Petugas yang diinputkan. Pertama-tama method 
         * akan mengecek apakah ID Petugas exist atau tidak. Jika ID Petugas tidak exist maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Tetapi jika ID Petugas exist maka data dari Petugas akan didapatkan dengan 
         * melalui method {@code getData()} yang ada didalam class {@code Database}.
         * 
         * @param idPetugas ID Petugas yang ingin diambil datanya.
         * @param data data yang ingin diambil.
         * @return akan mengembalikan data dari Petugas berdasarkan ID Petugas yang diinputkan.
         */
        private String getPetugasData(String idPetugas, PetugasData data){
            // mengecek apakah id petugas exist atau tidak
            if(this.isExistPetugas(idPetugas)){
                // mendapatkan data dari petugas
                return this.getData(DatabaseTables.PETUGAS.name(), data.name(), " WHERE "+ PetugasData.ID_PETUGAS +" = " + idPetugas);
            }
            // akan menghasilkan error jika id petugas tidak ditemukan
            throw new InValidUserDataException("'" +idPetugas + "' ID Petugas tersebut tidak dapat ditemukan.");
        }

        /**
         * Method ini digunakan untuk megedit data dari Petugas berdasarkan ID Petugas yang diinputkan. Sebelum mengedit data
         * method akan mengecek apakah ID Petugas exist atau tidak. Jika ID Petugas tidak exist maka method akan menghasilkan 
         * exception {@code InValidUserDataException}. Tetapi jika ID Petugas exist maka method akan mengedit data dari Petugas
         * dengan menggunakan method {@code setData()} yang ada didalam class {@code Database}. Jika data dari Petugas berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idPetugas ID Petugas yang ingin diedit datanya.
         * @param data data dari ID Petugas yang ingin diedit.
         * @param newValue nilai baru dari data yang ingin diedit.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        private boolean setPetugasData(String idPetugas, PetugasData data, String newValue){
            Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari Petugas dengan ID User '" + idPetugas + "'.");
            // mengecek apakah id petugas exist atau tidak
            if(this.isExistPetugas(idPetugas)){
                // mengedit data dari user
                return this.setData(DatabaseTables.PETUGAS.name(), data.name(), PetugasData.ID_PETUGAS.name(), idPetugas, newValue);
            }
            // akan menghasilkan error jika id petugas tidak ditemukan
            throw new InValidUserDataException("'" +idPetugas + "' ID Petugas tersebut tidak dapat ditemukan.");
        }

        /**
         * Method ini digunakan untuk mendapatkan data Nama Petugas berdasarkan ID User dari Petugas yang diinputkan. 
         * ID User dari Petugas yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Petugas yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Nama Petugas jika ID User dari Petugas 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Petugas yang ingin didapatkan datanya.
         * @return data Nama dari Petugas.
         */
        public String getNama(String idUser) {
            return this.getPetugasData(idUser, PetugasData.NAMA_PETUGAS);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Nama Petugas berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Nama Petugas melalui method {@code getNama(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Nama dari akun yang sedang Login.
         */
        public String getNama(){
            return this.getNama(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Nama Petugas berdasarkan ID User dari Petugas yang diinputkan. Sebelum mengedit 
         * data Nama method akan mengecek apakah Nama yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNama(String nama)} yang ada didalam class {@code Validation}. Jika Nama tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Nama valid maka data Nama dari Petugas akan diedit. Jika data dari Nama berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newNama data Nama yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNama(String idUser, String newNama) {
            // mengecek nama valid atau tidak
            if(Validation.isNamaOrang(newNama)){
                return this.setPetugasData(idUser, PetugasData.NAMA_PETUGAS, newNama);
            }
            throw new InValidUserDataException("'" + newNama + "' Nama tersebut tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Nama Petugas berdasarkan ID User dari Petugas yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Nama Petugas melalui method {@code setNama(String idUser, String newNama)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newNama data Nama yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNama(String newNama){
            return this.setNama(Users.this.getCurrentLogin(), newNama);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Gender Petugas berdasarkan ID User dari Petugas yang diinputkan. 
         * ID User dari Petugas yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Petugas yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Gender Petugas jika ID User dari Petugas 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Petugas yang ingin didapatkan datanya.
         * @return data Gender dari Petugas.
         */
        public String getGender(String idUser) {
            return this.getPetugasData(idUser, PetugasData.GENDER);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Gender Petugas berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Gender Petugas melalui method {@code getGender(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Gender dari akun yang sedang Login.
         */
        public String getGender(){
            return this.getGender(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Gender Petugas berdasarkan ID User dari Petugas yang diinputkan. Sebelum mengedit 
         * data Gender method akan mengecek apakah Gender yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isGender(String gender)} yang ada didalam class {@code Validation}. Jika Gender tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Gender valid maka data Gender dari Petugas akan diedit. Jika data dari Gender berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newGender data Gender yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setGender(String idUser, String newGender) {
            // mengecek apakah gender valid atau tidak
            if(Validation.isGender(newGender)){
                return this.setPetugasData(idUser, PetugasData.GENDER, newGender);
            }
            throw new InValidUserDataException("Gender harus diantara 'L' atau 'P'.");
        }
        
        /**
         * Digunakan untuk mengedit data Gender Petugas berdasarkan ID User dari Petugas yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Gender Petugas melalui method {@code setGender(String idUser, String newGender)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Gender dari user berhasil diedit.
         * 
         * @param newGender data Gender yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setGender(String newGender){
            return this.setGender(Users.this.getCurrentLogin(), newGender);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Tempat Lahir Petugas berdasarkan ID User dari Petugas yang diinputkan. 
         * ID User dari Petugas yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Petugas yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Tempat Lahir Petugas jika ID User dari Petugas 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Petugas yang ingin didapatkan datanya.
         * @return data Tempat Lahir dari Petugas.
         */
        public String getTempatLahir(String idUser) {
            return this.getPetugasData(idUser, PetugasData.TEMPAT_LHR);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Tempat Lahir Petugas berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Tempat Lahir Petugas melalui method {@code getTempatLahir(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Tempat Lahir dari akun yang sedang Login.
         */
        public String getTempatLahir(){
            return this.getTempatLahir(Users.this.getCurrentLogin());
        }
        
        /**
         * Digunakan untuk mengedit data Tempat Lahir Petugas berdasarkan ID User dari Petugas yang diinputkan. Sebelum mengedit 
         * data Tempat Lahir method akan mengecek apakah Tempat Lahir yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNamaTempat(String namaTempat)} yang ada didalam class {@code Validation}. Jika Tempat Lahir tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Tempat Lahir valid maka data Tempat Lahir dari Petugas akan diedit. Jika data dari Tempat Lahir berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newTempatLahir data Tempat Lahir yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTempatLahir(String idUser, String newTempatLahir) {
            // mengecek tempat lahir valid atau tidak
            if(Validation.isNamaTempat(newTempatLahir)){
                return this.setPetugasData(idUser, PetugasData.TEMPAT_LHR, newTempatLahir);
            }
            throw new InValidUserDataException("Tempat Lahir yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Tempat Lahir Petugas berdasarkan ID User dari Petugas yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Tempat Lahir Petugas melalui method {@code setTempatLahir(String idUser, String newTempatLahir)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data TempatLahir dari user berhasil diedit.
         * 
         * @param newTempatLahir data Tempat Lahir yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTempatLahir(String newTempatLahir){
            return this.setTempatLahir(Users.this.getCurrentLogin(), newTempatLahir);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Tanggal Lahir Petugas berdasarkan ID User dari Petugas yang diinputkan. 
         * ID User dari Petugas yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Petugas yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Tanggal Lahir Petugas jika ID User dari Petugas 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Petugas yang ingin didapatkan datanya.
         * @return data Tanggal Lahir dari Petugas.
         */
        public String getTanggalLahir(String idUser) {
            return this.getPetugasData(idUser, PetugasData.TANGGAL_LHR);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Tanggal Lahir Petugas berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Tanggal Lahir Petugas melalui method {@code getTanggalLahir(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Tanggal Lahir dari akun yang sedang Login.
         */
        public String getTanggalLahir(){
            return this.getTanggalLahir(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Tanggal Lahir Petugas berdasarkan ID User dari Petugas yang diinputkan. Sebelum mengedit 
         * data Tanggal Lahir method akan mengecek apakah Tanggal Lahir yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isTanggalLahir(String tanggalLahir)} yang ada didalam class {@code Validation}. Jika Tanggal Lahir tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Tanggal Lahir valid maka data Tanggal Lahir dari Petugas akan diedit. Jika data dari Tanggal Lahir berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newTanggalLahir data Tanggal Lahir yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTanggalLahir(String idUser, String newTanggalLahir) {
            // mengecek tanggal lahir valid atau tidak
            if(Validation.isTanggalLahir(newTanggalLahir)){
                return this.setPetugasData(idUser, PetugasData.TANGGAL_LHR, newTanggalLahir);
            }
            throw new InValidUserDataException("Tanggal Lahir yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Tanggal Lahir Petugas berdasarkan ID User dari Petugas yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Tanggal Lahir Petugas melalui method {@code setTanggalLahir(String idUser, String newTanggalLahir)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Tanggal Lahir dari user berhasil diedit.
         * 
         * @param newTanggalLahir data Tanggal Lahir yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTanggalLahir(String newTanggalLahir){
            return this.setTanggalLahir(Users.this.getCurrentLogin(), newTanggalLahir);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Alamat Petugas berdasarkan ID User dari Petugas yang diinputkan. 
         * ID User dari Petugas yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Petugas yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Alamat Petugas jika ID User dari Petugas 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Petugas yang ingin didapatkan datanya.
         * @return data Alamat dari Petugas.
         */
        public String getAlamat(String idUser) {
            return this.getPetugasData(idUser, PetugasData.ALAMAT);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Alamat Petugas berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Alamat Petugas melalui method {@code getAlamat(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Alamat dari akun yang sedang Login.
         */
        public String getAlamat(){
            return this.getAlamat(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Alamat Petugas berdasarkan ID User dari Petugas yang diinputkan. Sebelum mengedit 
         * data Alamat method akan mengecek apakah Alamat yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNamaTempat(String isNamaTempat)} yang ada didalam class {@code Validation}. Jika Alamat tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Alamat valid maka data Alamat dari Petugas akan diedit. Jika data dari Alamat berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newAlamat data Alamat yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setAlamat(String idUser, String newAlamat) {
            // mengecek apakah alamat valid atau tidak
            if(Validation.isNamaTempat(newAlamat)){
                return this.setPetugasData(idUser, PetugasData.ALAMAT, newAlamat);
            }
            throw new InValidUserDataException("Alamat yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Alamat Petugas berdasarkan ID User dari Petugas yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Alamat Petugas melalui method {@code setAlamat(String idUser, String newAlamat)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Alamat dari user berhasil diedit.
         * 
         * @param newAlamat data Alamat yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setAlamat(String newAlamat){
            return this.setAlamat(Users.this.getCurrentLogin(), newAlamat);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan total transaksi yang dilakukan oleh admin/petugas berdasarkan ID User yang 
         * diinputkan. Method akan mendapatkan total transaksi yang dilakukan oleh admin/petugas dengan melalui method 
         * {@code getJumlahData()} yang ada didalam class {@code Database}.
         * 
         * @param idUser ID User yang akan didapatkan total transaksinya.
         * @return total transaksi dari admin/petugas.
         */
        public int getTotalTransaksi(String idUser){
            return super.getJumlahData(DatabaseTables.PEMBAYARAN.name(), "WHERE " + PetugasData.ID_PETUGAS + " = '" + idUser + "'");
        }
        
        /**
         * Method ini digunakan untuk mendapatkan total transaksi yang dilakukan oleh admin/petugas berdasarkan ID User yang 
         * sedang digunakan untuk Login. Method akan mendapatkan ID User yang sedang digunakan untuk login dengan menggunakan method 
         * {@getCurrentLogin()}. Setelah ID User didapatkan maka method akan mendapatkan total transaksi dari admin/petugas dengan
         * melalui method {@code getTotalTransaksi(String idUser)}.
         * 
         * @return total transaksi dari admin/petugas.
         */
        public int getTotalTransaksi(){
            return getTotalTransaksi(this.getCurrentLogin());
        }
        
        /**
         * Method ini digunakan untuk mendapatkan total user yang memiliki level <b>ADMIN</b> yang terdaftar di <b>Database</b> 
         * aplikasi. Method akan mendapatkan data total user dengan melalui method {@code getJumlahData()} yang ada didalam 
         * class {@code Database}.
         * 
         * @return total user yang memiliki level admin.
         */
        public int getTotalAdmin(){
            return super.getJumlahData(DatabaseTables.USERS.name(), "WHERE " + UserData.LEVEL + " = '" + UserLevels.ADMIN + "'");
        }
        
        /**
         * Method ini digunakan untuk mendapatkan total user yang memiliki level <b>PETUGAS</b> yang terdaftar di <b>Database</b> 
         * aplikasi. Method akan mendapatkan data total user dengan melalui method {@code getJumlahData()} yang ada didalam 
         * class {@code Database}.
         * 
         * @return total user yang memiliki level petugas.
         */
        public int getTotalPetugas(){
            return super.getJumlahData(DatabaseTables.USERS.name(), "WHERE " + UserData.LEVEL + " = '" + UserLevels.PETUGAS + "'");
        }
        
        /**
         * Digunakan untuk menutup koneksi dari <B>Database</B> MySQL. Koneksi dari <B>Database</B> perlu ditutup jika sudah 
         * tidak digunakan lagi. Sebelum menutup koneksi dari <B>Database</B> method akan mengecek apakah object {@code Connection},
         * {@code Statement} dan {@code ResultSet} kosong atau tidak. Jika tidak maka koneksi dari <B>Database</B> akan ditutup. 
         * Jika tidak dicek kosong atau tidaknya object maka saat objek kosong lalu dipaksa untuk menutup koneksi dari <B>Database</B>
         * maka akan menimbulkan exception {@code NullPointerException}.
         */
        @Override
        public void closeConnection(){
            // menutup koneksi dari object Users
            Users.this.closeConnection();
            // menutup koneksi dari object Level Petugas
            Users.this.closeConnection();
        }

    }
    
    /**
     * Class ini digunakan untuk segala sesuatu yang berhubungan dengan akun dari user yang memiliki level <i>SISWA</i> 
     * seperti memanipulasi atau mendapatkan data dari akun. Class ini merupakah inheritance dan sekaliguas inner class dari 
     * class {@code Users}. Oleh karena itu object dari class ini juga dapat memanggil method-method yang ada didalam class 
     * {@code Users}.
     * <br><br>
     * Class ini berfokus untuk menangani segala sesuatu yang berhubungan dengan akun dari user yang memiliki level <i>SISWA</i> 
     * saja. Method-method yang ada didalam class ini juga hampir sama dengan method-method yang ada didalam class {@code Users}.
     * Cara class memanipulasi atau mendapatkan data dari akun yang memiliki level <i>SISWA</i> juga hapir sama dengan cara 
     * yang dilakukan oleh class {@code Users}.
     * <br><br>
     * Yaitu class akan memanfaatkan method-method yang ada didalam claas {@code Database}. Kita hanya perlu menginputkan 
     * id user / nis dari akun user untuk memanipulasi atau mendapatkan data dari akun user yang memiliki level 
     * <i>SISWA</i>. Class juga akan mengecek apakah data yang diinputkan valid atau tidak.
     * <br><br>
     * Class ini juga dapat digunakan untuk menambahkan atau menghapus sebuah akun yang memiliki level <i>SISWA</i> dari 
     * <b>Database</b> aplikasi. Cara kerja class untuk menambahkan atau menghapus sebuah akun dari <b>Database</b> hapir sama 
     * dengan cara kerja menambahkan atau menghapus sebuah akun pada class {@code User}.
     * <br><br>
     * Selama menggunakan class ini mungkin akan akan sering menemui runtime/checked exception. Salah-satu exception yang 
     * mungkin nantinya akan sering anda jumpai adalah {@code InValidUserDataException}. Exception tersebut akan sering dijumpai 
     * saat sedang memanipulasi atau mendapatkan data dari akun user yang memiliki level <i>SISWA</i>. 
     * <br><br>
     * Exception {@code InValidUserDataException} merupakan sebuah runtime exception. Oleh karena itu disaat akan memanipulasi 
     * atau mendapkan data dari user yang memiliki level <i>SISWA</i> disarankan untuk membuat block try catch untuk menangkap 
     * pesan error dari exception. Jika tidak ditangkap menggunakan block try catch maka ada kemungkinan aplikasi akan force close.
     * 
     * @author Achmad Baihaqi
     * @since 2021-06-14
     */
    public class LevelSiswa extends Users{
        
        /**
         * Method ini digunakan untuk menambahkan data dari User dan Siswa yang diinputkan kedalam <b>Database MySQL</b>.
         * Method akan mengembalikan nilai <code>True</code> jika data dari User dan Siswa berhasil ditambahkan kedalam 
         * <b>Database</b>. Jika ada salah satu data yang gagal ditambahkan kedalam <b>Database</b> maka method akan 
         * mengembalikan nilai <code>False</code>
         * <br><br>
         * Pertama-tama method akan menambahkan data dari User kedalam <b>Database</b>. Data dari User akan ditambahkan ke 
         * dalam <b>Database</b> dengan melalui method {@code addUser()} yang ada didalam class {@code Users}. Jika data 
         * dari User berhasil ditambahkan kedalam <b>Database</b> maka selanjutnya method akan menambahkan data Siswa 
         * kedalam <b>Database</b>
         * <br><br>
         * Sebelum menambahkan data Siswa kedalam <b>Database</b> method akan mengecek apakah semua data dari Siswa
         * yang diinputkan valid atau tidak. Jika ada salah satu data dari Siswa yang tidak valid maka data Siswa tidak akan 
         * ditambahkan kedalam <b>Database</b> dan data User yang sebelumnya sudah ditambahkan akan dihapus melalui method 
         * {@code deleteUser} yang ada didalam class {@code} Users. 
         * <br><br>
         * Jika semua data dari Siswa valid maka method akan membuat sebuah object {@code PreparedStatement} yang digunakan 
         * untuk menambahkan data Siswa kedalam <b>Database</b>. Setelah object dari class {@code PreparedStatement} berhasil 
         * dibuat selanjutnya method akan menambahkan semua data dari Siswa kedalam object {@code PreparedStatement}. 
         * <br><br>
         * Jika semua data dari Siswa sudah ditambahkan kedalam object {@code PreparedStatement} maka data dari Siswa tersebut 
         * akan ditambahkan kedalam <b>Database</b> melalui method {@code executeUpdate()} yang ada didalam class 
         * {@code PreparedStatement}. Jika data Siswa berhasil ditambahkan kedalam <b>Database</b> maka method akan megembalikan 
         * nilai <code>True</code>.
         * 
         * @param idUser id user dari user atau siswa.
         * @param noHP no hp dari user atau siswa.
         * @param email email dari user atau siswa.
         * @param profile foto dari user atau siswa.
         * @param nama nama dari user atau siswa.
         * @param gender gender dari user atau siswa.
         * @param tempatLahir tampat lahir dari user atau siswa.
         * @param tanggalLahir tanggal lahirdari user atau siswa.
         * @param alamat alamat dari user atau siswa.
         * @param idKelas id kelas dari user atau siswa.
         * @param namaWali id kelas dari user atau siswa.
         * @param idSpp id spp dari user atau siswa.
         * 
         * @return <strong>True</strong> jika data berhasil ditambahkan. <br>
         *         <strong>False</strong> jika data tidak berhasil ditambahkan. 
         * 
         * @throws FileNotFoundException jika terjadi kegagalan saat menkonversi foto kedalam byte stream / Blob.
         * @throws SQLException jika terjadi kegagalan saat menambahkan data kedalam <b>Database</b>.
         * @throws InValidUserDataException jika data dari siswa tidak valid.
         */
        public final boolean addSiswa(String idUser, String noHP, String email, File profile, 
                                      String nama, String gender, String tempatLahir, String tanggalLahir, String alamat, String idKelas, String namaWali, int idSpp) 
                throws FileNotFoundException, SQLException, InValidUserDataException
        {
            PreparedStatement pst;
            boolean addUser, addSiswa = false;
            
            // menambahkan data user ke database
            addUser = Users.this.addUser(idUser, noHP, email, profile, "Siswa"+idUser, UserLevels.SISWA);
            
            // jika data user berhasil ditambahkan maka data siswa akan ditambahkan kedalam database
            if(addUser){
                Log.addLog("Akun dengan ID User '" + idUser + "' berhasil ditambahkan.");
                Log.addLog("Menambahkan data Siswa dengan ID User '"+ idUser + "' ke Database.");
                // mengecek apakah data siswa valid atau tidak
                if(this.validateDataSiswa(idUser, nama, gender, tempatLahir, tanggalLahir, alamat, idKelas, namaWali, idSpp)){
                    Log.addLog("Data Siswa dengan ID User '" + idUser + "' dinyatakan valid.");
                    // menambahkan data siswa kedalam database
                    pst = Users.this.conn.prepareStatement("INSERT INTO siswa VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pst.setString(1, idUser);
                    pst.setString(2, nama);
                    pst.setString(3, gender);
                    pst.setString(4, tempatLahir);
                    pst.setString(5, tanggalLahir);
                    pst.setString(6, alamat);
                    pst.setString(7, idKelas);
                    pst.setString(8, namaWali);
                    pst.setInt(9, idSpp);
                    // mengeksekusi query
                    addSiswa = pst.executeUpdate() > 0;
                }
            }
            
            // jika data siswa berhasil ditambahakan maka method akan mengembalikan nilai true
            if(addSiswa){
                Log.addLog("Data Siswa dengan ID User '"+idUser+"' ditambahkan ke Database.");
                return true;
            }else{
                // jika data siswa tidak berhasil ditambahkan maka data user akan dihapus
                Users.this.deleteUser(idUser);
                return false;
            }
        }
        
        /**
         * Method ini digunakan untuk mengecek apakah semua data dari Siswa yang diinputkan valid atau tidak.
         * Method akan mengecek satu persatu data dari Petugas. Jika ada salah satu data saja yang tidak valid maka 
         * semua data dari Siswa yang di inputkan akan dianggap tidak valid dan method akan mengembalikan nilai 
         * <code>False</code>. Method hanya akan mengembalikan nilai <code>True</code> jika semua data dari 
         * Siswa yang diinputkan valid.
         * 
         * @param level level yang akan dicek.
         * @param nama nama level yang akan dicek.
         * @param gender gender level yang akan dicek.
         * @param tempatLahir tempat lahir level yang akan dicek.
         * @param tanggalLahir tanggal lahir level yang akan dicek.
         * @param alamat alamat level yang akan dicek.
         * @param idKelas id kelas yang akan dicek.
         * @param namaWali nama wali yang akan dicek.
         * @param idSpp id spp yang akan dicek.
         * 
         * @return <strong>True</strong> jika semua data dari Siswa valid. <br>
         *         <strong>False</strong> jika ada salah satu data dari Siswa yang tidak valid.
         */
        private boolean validateDataSiswa(String nis, String nama, String gender, String tempatLahir, String tanggalLahir, String alamat, String idKelas, String namaWali, int idSpp){
            
            boolean vNis, vNama, vGender, vTmpLhr, vTglLhr, vAlamat, vIdKelas, vNamaWali, vIdSpp;
            
            Log.addLog("Mengecek apakah data Siswa dengan ID User '" + nis + "' valid atau tidak.");
            
            // mengecek nis valid atau tidak
            if(Validation.isNis(Integer.parseInt(nis))){
                // mengecek nis sudah terpakai atau belulm
                if(!this.isExistSiswa(nis)){
                    vNis = true;
                }else{
                    throw new InValidUserDataException("'" + nis + "' NIS tersebut sudah terpakai.");
                }
            }else{
                throw new InValidUserDataException("'" + nis + "' NIS tersebut tidak valid.");
            }
            
            // mengecek nama petugas valid atau tidak
            if(Validation.isNamaOrang(nama)){
                vNama = true;
            }else{
                throw new InValidUserDataException("'" + nama + "' Nama tersebut tidak valid.");
            }                
            
            // mengecek gender valid atau tidak
            if(Validation.isGender(gender)){
                vGender = true;
            }else{
                throw new InValidUserDataException("Gender harus diantara 'L' atau 'P'");
            }
            
            // mengecek tempat lahir valid atau tidak            
            if(Validation.isNamaTempat(tempatLahir)){
                vTmpLhr = true;
            }else{
                throw new InValidUserDataException("Tempat Lahir tidak valid.");
            }
            
            // mengecek tanggal lahir valid atau tidak
            if(Validation.isTanggalLahir(tanggalLahir)){
                vTglLhr = true;
            }else{
                throw new InValidUserDataException("Tanggal Lahir tidak valid.");
            }

            // megecek apakah alamat valid atau tidak
            if(Validation.isNamaTempat(alamat)){
                vAlamat = true;
            }else{
                throw new InValidUserDataException("Alamat tidak valid.");
            }
            
            // mengecek apakah id kelas valid atau tidak
            if(Validation.isIdKelas(idKelas)){
                vIdKelas = true;
            }else{
                throw new InValidUserDataException("ID Kelas tidak valid.");
            }

            // mengecek apakah nama wali valid atau tidak
            if(namaWali.length() >= 8){
                vNamaWali = true;
            }else{
                throw new InValidUserDataException("Nama Wali tidak valid.");
            }

            // mengecek apakah id spp valid atau tidak
            if(Validation.isIdSpp(idSpp)){
                vIdSpp = true;
            }else{
                throw new InValidUserDataException("ID SPP tidak valid.");
            }
            
            return vNis && vNama && vGender && vTmpLhr && vTglLhr && vAlamat && vIdKelas && vNamaWali && vIdSpp;
        }
        
        /**
         * Method ini digunakan untuk menghapus sebuah akun Siswa yang tersimpan didalam tabel <code>users</code> dan 
         * tabel <code>siswa</code> yang ada didalam <b>Database</b> berdasarkan id user yang diinputkan. Method akan menghapus 
         * data akun Siswa yang ada didalam tabel <code>users</code> dengan melalui method {@code deleteUser()} yang ada 
         * didalam class {@code Users}.
         * <br><br>
         * Method akan menghapus data akun Siswa yang ada didalam tabel <code>siswa</code> dengan menggunakan method
         * {@code deleteData()} yang ada didalam class {@code Database}. Jika kedua data Siswa yang ada didalam tabel 
         * <code>users</code> dan tabel <code>siswa</code> berhasil dihapus maka method akan mengembalikan nilai <code>True</code>s
         * 
         * @param idUser id dari user yang ingin dihapus.
         * @return <strong>True</strong> jika akun berhasil dihapus. <br>
         *         <strong>False</strong> jiak akun tidak berhasil dihapus.
         */
        public final boolean deleteSiswa(String idUser){
            return Users.this.deleteUser(idUser) && 
                   Users.this.deleteData(DatabaseTables.SISWA.name(), SiswaData.NIS.name(), idUser);
        }
        
        /**
         * Method ini digunakan untuk mengecek apakah ID User dari Siswa yang diinputkan exist atau tidak didalam tabel siswa 
         * yang ada didalam <code>Database</code>. Method akan mengembalikan nilai <code>True</code> jika ID User dari Siswa 
         * yang diinputkan exist. Pertama-tama method akan mengecek apakah ID User dari Siswa yang diinputkan valid atau tidak
         * jika ID User dari Siswa tidak valid maka akan mengembalikan nilai <code>False</code>. Tetapi jika ID User dari Siswa valid maka 
         * method akan mengecek exist atau tidanya ID User dari Siswa yang diinputkan dengan melalui method <code>isExistData()</code>
         * yang ada didalam class {@code Database}.
         * 
         * @param idUser ID User dari Siswa yang akan dicek.
         * @return <strong>True</strong> jika ID User dari Siswa exist. <br>
         *         <strong>False</strong> jika ID User dari Siswa tidak exist.
         */
        public final boolean isExistSiswa(String idUser){
            // mengecek is siswa valid atau tidak
            if(Validation.isNis(Integer.parseInt(idUser))){
                // mengecek apakah id siswa exist atau tidak
                return super.isExistData(DatabaseTables.SISWA.name(), SiswaData.NIS.name(), idUser);
            }
            return false;
        }
        
        /**
         * Method ini akan mengembalikan data dari Siswa berdasarkan NIS yang diinputkan. Pertama-tama method 
         * akan mengecek apakah NIS exist atau tidak. Jika NIS tidak exist maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Tetapi jika NIS exist maka data dari Siswa akan didapatkan dengan 
         * melalui method {@code getData()} yang ada didalam class {@code Database}.
         * 
         * @param idPetugas NIS yang ingin diambil datanya.
         * @param data data yang ingin diambil.
         * @return akan mengembalikan data dari Siswa berdasarkan NIS yang diinputkan.
         */
        private String getSiswaData(String nis, SiswaData data){
            // mengecek apakah nis exist atau tidak
            if(this.isExistSiswa(nis)){
                // mendapatkan data dari siswa
                return Users.this.getData(DatabaseTables.SISWA.name(), data.name(), " WHERE "+ SiswaData.NIS.name() +" = " + nis);
            }
            // akan menghasilkan error jika nis tidak ditemukan
            throw new InValidUserDataException("'" + nis + "' NIS tersebut tidak dapat ditemukan.");
        }

        /**
         * Method ini digunakan untuk megedit data dari Siswa berdasarkan NIS yang diinputkan. Sebelum mengedit data
         * method akan mengecek apakah NIS exist atau tidak. Jika NIS tidak exist maka method akan menghasilkan 
         * exception {@code InValidUserDataException}. Tetapi jika NIS exist maka method akan mengedit data dari Siswa
         * dengan menggunakan method {@code setData()} yang ada didalam class {@code Database}. Jika data dari Siswa berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idPetugas NIS yang ingin diedit datanya.
         * @param data data dari NIS yang ingin diedit.
         * @param newValue nilai baru dari data yang ingin diedit.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        private boolean setSiswaData(String nis, SiswaData data, String newValue){
            Log.addLog("Mengedit data '" + data.name().toLowerCase() + "' dari Siswa dengan ID User '" + nis + "'.");
            // mengecek apakah nis exist atau tidak
            if(this.isExistSiswa(nis)){
                // mengedit data dari siswa
                return Users.this.setData(DatabaseTables.SISWA.name(), data.name(), SiswaData.NIS.name(), nis, newValue);
            }
            // akan menghasilkan error jika nis tidak ditemukan
            throw new InValidUserDataException("'" + nis + "' NIS tersebut tidak dapat ditemukan.");
        }

        /**
         * Method ini digunakan untuk mendapatkan data Nama Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Nama Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Nama dari Siswa.
         */
        public String getNama(String idUser) {
            return this.getSiswaData(idUser, SiswaData.NAMA_SISWA);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Nama Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Nama Siswa melalui method {@code getNama(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Nama dari akun yang sedang Login.
         */
        public String getNama(){
            return this.getNama(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Nama Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Nama method akan mengecek apakah Nama yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNama(String nama)} yang ada didalam class {@code Validation}. Jika Nama tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Nama valid maka data Nama dari Siswa akan diedit. Jika data dari Nama berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newNama data Nama yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNama(String idUser, String newNama) {
            // mengecek nama valid atau tidak
            if(Validation.isNamaOrang(newNama)){
                return this.setSiswaData(idUser, SiswaData.NAMA_SISWA, newNama);
            }
            throw new InValidUserDataException("'" + newNama + "' Nama tersebut tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Nama Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Nama Siswa melalui method {@code setNama(String idUser, String newNama)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newNama data Nama yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNama(String newNama){
            return this.setNama(Users.this.getCurrentLogin(), newNama);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Gender Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Gender Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Gender dari Siswa.
         */
        public String getGender(String idUser) {
            return this.getSiswaData(idUser, SiswaData.GENDER);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Gender Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Gender Siswa melalui method {@code getGender(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Gender dari akun yang sedang Login.
         */
        public String getGender(){
            return this.getGender(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Gender Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Gender method akan mengecek apakah Gender yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isGender(String gender)} yang ada didalam class {@code Validation}. Jika Gender tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Gender valid maka data Gender dari Siswa akan diedit. Jika data dari Gender berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newGender data Gender yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setGender(String idUser, String newGender) {
            // mengecek apakah gender valid atau tidak
            if(Validation.isGender(newGender)){
                return this.setSiswaData(idUser, SiswaData.GENDER, newGender);
            }
            throw new InValidUserDataException("Gender harus diantara 'L' atau 'P'.");
        }
        
        /**
         * Digunakan untuk mengedit data Gender Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Gender Siswa melalui method {@code setGender(String idUser, String newGender)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newGender data Gender yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setGender(String newGender){
            return this.setGender(Users.this.getCurrentLogin(), newGender);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Tempat Lahir Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Tempat Lahir Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Tempat Lahir dari Siswa.
         */
        public String getTempatLahir(String idUser) {
            return this.getSiswaData(idUser, SiswaData.TEMPAT_LHR);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Tempat Lahir Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data TempatLahir Siswa melalui method {@code getTempatLahir(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Tempat Lahir dari akun yang sedang Login.
         */
        public String getTempatLahir(){
            return this.getTempatLahir(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Tempat Lahir Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Tempat Lahir method akan mengecek apakah Tempat Lahir yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNamaTempat(String namaTempat)} yang ada didalam class {@code Validation}. Jika Tempat Lahir tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Tempat Lahir valid maka data Tempat Lahir dari Siswa akan diedit. Jika data dari Tempat Lahir berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newTempatLahir data Tempat Lahir yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTempatLahir(String idUser, String newTempatLahir) {
            // mengecek tempat lahir valid atau tidak
            if(Validation.isNamaTempat(newTempatLahir)){
                return this.setSiswaData(idUser, SiswaData.TEMPAT_LHR, newTempatLahir);
            }
            throw new InValidUserDataException("Tempat Lahir yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Tempat Lahir Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Tempat Lahir Siswa melalui method {@code setTempatLahir(String idUser, String new[TempatLahir)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newTempatLahir data TempatLahir yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTempatLahir(String newTempatLahir){
            return this.setTempatLahir(Users.this.getCurrentLogin(), newTempatLahir);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Tanggal Lahir Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Tanggal Lahir Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Tanggal Lahir dari Siswa.
         */
        public String getTanggalLahir(String idUser) {
            return this.getSiswaData(idUser, SiswaData.TANGGAL_LHR);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Tanggal Lahir Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Tanggal Lahir Siswa melalui method {@code getTanggalLahir(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Tanggal Lahir dari akun yang sedang Login.
         */
        public String getTanggalLahir(){
            return this.getTanggalLahir(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Tanggal Lahir Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Tanggal Lahir method akan mengecek apakah Tanggal Lahir yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isTanggalLahir(String tanggaLahir)} yang ada didalam class {@code Validation}. Jika Tanggal Lahir tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Tanggal Lahir valid maka data Tanggal Lahir dari Siswa akan diedit. Jika data dari Tanggal Lahir berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newTanggalLahir data TanggalLahir yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTanggalLahir(String idUser, String newTanggalLahir) {
            // mengecek apakah tanggal lahir valid atau tidak
            if(Validation.isTanggalLahir(newTanggalLahir)){
                return this.setSiswaData(idUser, SiswaData.TANGGAL_LHR, newTanggalLahir);
            }
            throw new InValidUserDataException("Tanggal Lahir yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Tanggal Lahir Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Tanggal Lahir Siswa melalui method {@code setTanggalLahir](String idUser, String newTanggalLahir)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newTanggalLahir data Tanggal Lahir yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setTanggalLahir(String newTanggalLahir){
            return this.setTanggalLahir(Users.this.getCurrentLogin(), newTanggalLahir);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Alamat Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Alamat Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Alamat dari Siswa.
         */
        public String getAlamat(String idUser) {
            return this.getSiswaData(idUser, SiswaData.ALAMAT);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data Alamat Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Alamat Siswa melalui method {@code getAlamat(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Alamat dari akun yang sedang Login.
         */
        public String getAlamat(){
            return this.getAlamat(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Alamat Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Alamat method akan mengecek apakah Alamat yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNamaTempat(String namaTempat)} yang ada didalam class {@code Validation}. Jika Alamat tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jikaAlamat valid maka data Alamat dari Siswa akan diedit. Jika data dari Alamat berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newAlamat data Alamat yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setAlamat(String idUser, String newAlamat) {
            // mengecek alamat valid atau tidak
            if(Validation.isNamaTempat(newAlamat)){
                return this.setSiswaData(idUser, SiswaData.ALAMAT, newAlamat);
            }
            throw new InValidUserDataException("Alamat yang Anda masukan tidak valid.");
        }
        
        /**
         * Digunakan untuk mengedit data Alamat Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Alamat Siswa melalui method {@code setAlamat(String idUser, String newAlamat)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newAlamat data Alamat yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setAlamat(String newAlamat){
            return this.setAlamat(Users.this.getCurrentLogin(), newAlamat);
        }

        /**
         * Method ini digunakan untuk mendapatkan data ID Kelas Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data ID Kelas Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data ID Kelas dari Siswa.
         */
        public String getIdKelas(String idUser){
            return this.getSiswaData(idUser, SiswaData.ID_KELAS);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan data ID Kelas Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data ID Kelas Siswa melalui method {@code getIdKelas(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data ID Kelas dari akun yang sedang Login.
         */
        public String getIdKelas(){
            return this.getIdKelas(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data ID Kelas Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data ID Kelas method akan mengecek apakah ID Kelas yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isIdKelas(String idKelas)} yang ada didalam class {@code Validation}. Jika ID Kelas tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika ID Kelas valid maka data ID Kelas dari Siswa akan diedit. Jika data dari ID Kelas berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newIdKelas data ID Kelas yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setIdKelas(String idUser, String newIdKelas){
            // mengecek apakah ID Kelas valid atau tidak
            if(Validation.isIdKelas(newIdKelas)){
                return this.setSiswaData(idUser, SiswaData.ID_KELAS, newIdKelas);
            }
            throw new InValidUserDataException("'" + newIdKelas +"' ID Kelas tersebut tidak valid.");
        }

        /**
         * Digunakan untuk mengedit data ID Kelas Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data ID Kelas Siswa melalui method {@code setIdKelas(String idUser, String newIdKelas)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newIdKelas data ID Kelas yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setIdKelas(String newIdKelas){
            return this.setIdKelas(Users.this.getCurrentLogin(), newIdKelas);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Nama Wali Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data Nama Wali Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data Nama Wali dari Siswa.
         */
        public String getNamaWali(String idUser){
            return this.getSiswaData(idUser, SiswaData.NAMA_WALI);
        }

        /**
         * Method ini digunakan untuk mendapatkan data Nama Wali Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data Nama Wali Siswa melalui method {@code getNamaWali(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data Nama Wali dari akun yang sedang Login.
         */
        public String getNamaWali(){
            return this.getNamaWali(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data Nama Wali Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data Nama Wali method akan mengecek apakah Nama Wali yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isNamaOrang(String namaWali)} yang ada didalam class {@code Validation}. Jika Nama Wali tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika Nama Wali valid maka data Nama Wali dari Siswa akan diedit. Jika data dari Nama Wali berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newNama data Nama Wali yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNamaWali(String idUser, String newNama){
            // mengecek apakah nama valid atau tidak
            if(Validation.isNamaOrang(newNama)){
                return this.setSiswaData(idUser, SiswaData.NAMA_WALI, newNama);
            }
            throw new InValidUserDataException("'" + newNama + "' Nama tersebut tidak valid.");
        }

        /**
         * Digunakan untuk mengedit data Nama Wali Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data Nama Wali Siswa melalui method {@code setNamaWali(String idUser, String newNamaWali)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newNama data Nama Wali yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setNamaWali(String newNama){
            return this.setNamaWali(Users.this.getCurrentLogin(), newNama);
        }

        /**
         * Method ini digunakan untuk mendapatkan data ID SPP Siswa berdasarkan ID User dari Siswa yang diinputkan. 
         * ID User dari Siswa yang diinputkan harus sudah terdaftar didalam <b>Database</b>. Jika ID User dari Siswa yang 
         * diinputkan ternyata tidak terdaftar didalam <b>Database</b> maka method akan menghasilkan exception 
         * {@code InValidUserDataException}. Method hanya akan mendapatkan data ID SPP Siswa jika ID User dari Siswa 
         * yang diinputkan terdaftar didalam <b>Database</b>.
         * 
         * @param idUser ID User dari Siswa yang ingin didapatkan datanya.
         * @return data ID SPP dari Siswa.
         */
        public String getIdSpp(String idUser){
            return this.getSiswaData(idUser, SiswaData.ID_SPP);
        }

        /**
         * Method ini digunakan untuk mendapatkan data ID SPP Siswa berdasarkan ID User dari Petugas yang sedang 
         * digunakan untuk Login. Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}.
         * Selanjutnya method akan mendapatkan data ID SPP Siswa melalui method {@code getIdSpp(String idUser)}.
         * Jika user belum melakukan login maka method akan mengembalikan nilai <code>null</code>.
         * 
         * @return data ID SPP dari akun yang sedang Login.
         */
        public String getIdSpp(){
            return this.getIdSpp(Users.this.getCurrentLogin());
        }

        /**
         * Digunakan untuk mengedit data ID SPP Siswa berdasarkan ID User dari Siswa yang diinputkan. Sebelum mengedit 
         * data ID SPP method akan mengecek apakah ID SPP yang diinputkan valid atau tidak dengan menggunakan 
         * method {@code isIdSpp(String idSpp)} yang ada didalam class {@code Validation}. Jika ID SPP tidak valid
         * maka method akan menghasilkan exception {@code InValidUserDataException}.
         * <br><br>
         * Tetapi jika ID SPP valid maka data ID SPP dari Siswa akan diedit. Jika data dari ID SPP berhasil 
         * diedit maka method akan mengembalikan nilai <code>True</code>.
         * 
         * @param idUser ID User yang ingin diedit datanya.
         * @param newIdSpp data ID SPP yang baru.
         * 
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setIdSpp(String idUser, String newIdSpp){
            // mengecek apakah id spp yang diinputkan adalah sebuah number atau tidak
            if(new Text().isNumber(newIdSpp)){
                // mengecek apakah id spp valid atau tidak
                if(Validation.isIdSpp(Integer.parseInt(newIdSpp))){
                    return this.setSiswaData(idUser, SiswaData.ID_SPP, newIdSpp);
                }
            }
            throw new InValidUserDataException("'" + newIdSpp + "' ID SPP tersebut tidak valid.");
        }

        /**
         * Digunakan untuk mengedit data ID SPP Siswa berdasarkan ID User dari Siswa yang sedang digunakan untuk Login. 
         * Method akan mendapatkan ID User dengan menggunakan method {@code getCurrentLogin()}. Selanjutnya method 
         * akan mengedit data ID SPP Siswa melalui method {@code setIdSpp(String idUser, String newIdSpp)}. 
         * Jika output dari method tersebut adalah <code>True</code> maka data Nama dari user berhasil diedit.
         * 
         * @param newIdSpp data ID SPP yang baru.
         * @return <strong>True</strong> jika data berhasil diedit. <br>
         *         <strong>False</strong> jika data tidak berhasil diedit.
         */
        public boolean setIdSpp(String newIdSpp){
            return this.setIdSpp(Users.this.getCurrentLogin(), newIdSpp);
        }
        
        /**
         * Method ini digunakan untuk mendapatkan total user yang memiliki level <b>SISWA</b> yang terdaftar di <b>Database</b> 
         * aplikasi. Method akan mendapatkan data total user dengan melalui method {@code getJumlahData()} yang ada didalam 
         * class {@code Database}.
         * 
         * @return total user yang memiliki level siswa.
         */
        public int getTotalSiswa(){
            return super.getJumlahData(DatabaseTables.USERS.name(), "WHERE " + UserData.LEVEL + " = '" + UserLevels.SISWA + "'");
        }
        
        /**
         * Digunakan untuk menutup koneksi dari <B>Database</B> MySQL. Koneksi dari <B>Database</B> perlu ditutup jika sudah 
         * tidak digunakan lagi. Sebelum menutup koneksi dari <B>Database</B> method akan mengecek apakah object {@code Connection},
         * {@code Statement} dan {@code ResultSet} kosong atau tidak. Jika tidak maka koneksi dari <B>Database</B> akan ditutup. 
         * Jika tidak dicek kosong atau tidaknya object maka saat objek kosong lalu dipaksa untuk menutup koneksi dari <B>Database</B>
         * maka akan menimbulkan exception {@code NullPointerException}.
         */
        @Override
        public void closeConnection(){
            // menutup koneksi dari object Users
            Users.this.closeConnection();
            // menutup koneksi dari object Level Siswa
            Users.this.closeConnection();
        }

    }
}
