package com.users;


import com.data.app.Log;




/**
 *
 * @author Achmad Baihaqi
 */
public class Test {
    
    public static void main(String[] args) {
        
        Log.createLog();
        Users.LevelSiswa siswa = Users.levelSiswa();
        
    }
    
    
    public static void showDataPetugas(Users.LevelPetugas user){
        System.out.println("\nData Petugas dengan ID User : " + user.getCurrentLogin());
        System.out.println("Nama : " + user.getNama());
        System.out.println("Jenis Kelamin : " + user.getGender());
        System.out.println("Tempat Lahir : " + user.getTempatLahir());
        System.out.println("Tempat Lahir : " + user.getTanggalLahir());
        System.out.println("Alamat : " + user.getAlamat());
        System.out.println("Nomor HP : " + user.getNoHp());
        System.out.println("Email : " + user.getEmail());
    }
    
    public static void showDataPetugas(Users.LevelPetugas user, String idUser){
        System.out.println("\nData Petugas dengan ID User : " + idUser);
        System.out.println("Nama : " + user.getNama(idUser));
        System.out.println("Jenis Kelamin : " + user.getGender(idUser));
        System.out.println("Tempat Lahir : " + user.getTempatLahir(idUser));
        System.out.println("Tempat Lahir : " + user.getTanggalLahir(idUser));
        System.out.println("Alamat : " + user.getAlamat(idUser));
        System.out.println("Nomor HP : " + user.getNoHp(idUser));
        System.out.println("Email : " + user.getEmail(idUser));
    }
    
    public static void showDataSiswa(Users.LevelSiswa user, String idUser){
        System.out.println("\nData Siswa ->\n");
        System.out.println("Nama : " + user.getNama(idUser));
        System.out.println("Jenis Kelamin : " + user.getGender(idUser));
        System.out.println("Tempat Lahir : " + user.getTempatLahir(idUser));
        System.out.println("Tempat Lahir : " + user.getTanggalLahir(idUser));
        System.out.println("Alamat : " + user.getAlamat(idUser));
        System.out.println("Nomor HP : " + user.getNoHp(idUser));
        System.out.println("Email : " + user.getEmail(idUser));
        System.out.println("ID Kelas : " + user.getIdKelas(idUser));
        System.out.println("Nama Wali : " + user.getNamaWali(idUser));
        System.out.println("ID SPP : " + user.getIdSpp(idUser));
        System.out.println("Storage : " + user.getUserStorage(idUser));
    }
    
    public static void showDataSiswa(Users.LevelSiswa user){
        System.out.println("\nData Siswa ->\n");
        System.out.println("Nama : " + user.getNama());
        System.out.println("Jenis Kelamin : " + user.getGender());
        System.out.println("Tempat Lahir : " + user.getTempatLahir());
        System.out.println("Tempat Lahir : " + user.getTanggalLahir());
        System.out.println("Alamat : " + user.getAlamat());
        System.out.println("Nomor HP : " + user.getNoHp());
        System.out.println("Email : " + user.getEmail());
        System.out.println("ID Kelas : " + user.getIdKelas());
        System.out.println("Nama Wali : " + user.getNamaWali());
        System.out.println("ID SPP : " + user.getIdSpp());
        System.out.println("Storage : " + user.getUserStorage(user.getCurrentLogin()));
    }
}


/*
    public final boolean login(String idUser, String password) throws IOException, AuthenticationException{
        String sql = "", idLogin = "", newLoginData = "";
        boolean create = false, success = false;
        
        // mengecek apakah user sudah login atau belum
        if(this.isLogin()){
            throw new AuthenticationException("Anda sudah login dengan akun '" + this.getCurrentLogin() + "'");
        }
        
        // mengecek apakah idUser dan password valid atau tidak
        if(this.validateLogin(idUser, password)){
            // membuat sebuah id login
            idLogin = this.createIdLogin();
            // megecek apakah id login sudah exist atau belum
            if(!this.isExistIdLogin(idLogin)){
                // membuat query untuk untuk melakukan login
                sql = String.format("INSERT INTO login VALUES ('%s', '%s', '%s')", idLogin, idUser, new Waktu().getCurrentDateTime());
                // membuat login data baru
                newLoginData = idLogin + "/" + idUser;
                // untuk menandakan bahwa login data behasil dibuat
                create = true;
            }else{
                // mencoba memanggil kembali method
                login(idUser, password);
            }
        }
        
        // mengecek apakah login data sukses dibuat atau tidak
        if(create){
            // menyimpan login data kedalam file
            BufferedWriter save = new BufferedWriter(new FileWriter(this.LOGIN_DATA_FILE));
            save.write(newLoginData);
            save.flush();
            save.close();
            // mengeksekusqi query
            success = this.addData(sql);
        }
        
        // jika login data berhasil ditambahkan kedalam database 
        if(success){
            Log.addLog("Melakukan Login dengan ID Login : '" + idLogin + "' dan dengan ID User : '"+ idUser +"'");
            return this.createUserStorage(idUser);
        }
        return false;
    }
*/

