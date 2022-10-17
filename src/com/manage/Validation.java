package com.manage;

import com.media.Audio;
import javax.swing.JOptionPane;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-15
 */
public class Validation {
    
    /**
     * Digunakan untuk mengecek apakah didalam sebuah text mengandung sebuah angka atau tidak
     * 
     * @param text text yang akan dicek 
     * @return Jika didalam text mengandung number maka akan mengembalikan nilai True
     *         Tapi jika didalam text tidak mengandung number maka akan mengembalikan nilai False
     */
    public static boolean containsNumber(String text){
        String number = "1234567890";
        char a, b;
        for(int i = 0; i < number.length(); i++){
            for(int k = 0; k < text.length(); k++){
                a = number.charAt(i); b = text.charAt(k);
                if(a == b){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Digunakan untuk mengecek apakah sebuah String dapat dikonversi ke Integer atau tidak.
     * 
     * @param text text yang ingin dicek
     * @return jika bisa dikonversi maka akan mengembalikan nilai true
     *         tapi jika tidak maka akan mengembalikan nilai false.
     */
    public static boolean isNumber(String text){
        if(text == null){
            return false;
        }
        text = text.toLowerCase();
        // karakter yang bukan merupakan number
        String notNums = "abcdefghijklmnopqrstuvwxyz`~!@#$%^&*()_+=\\|{[]}:;'\"<>?/";
        for(int i = 0; i < text.length(); i++){
            for(int k = 0; k < notNums.length(); k++){
                // jika kerakter yang ada didalam text mengandung karakter yang ada didalam notNums maka akan mereturn false
                if(text.charAt(i) == notNums.charAt(k)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isIdLogin(String idLogin){
        return idLogin.length() == 8;
    }
    
    /**
     * id user harus terdiri dari 5 karakter
     * 
     * @param idUser
     * @return 
     */
    public static boolean isIdUser(String idUser){
        if(idUser.length() == 5){
            return true;
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "'" + idUser + "' ID tersebut tidak valid!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static boolean isIdPetugas(String idKaryawan){
        if(Validation.isIdUser(idKaryawan)){
            if(idKaryawan.substring(0, 2).equalsIgnoreCase("PG")){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "'" + idKaryawan + "' Kode ID Petugas tersebut tidak valid!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }
    
    public static boolean isIdSupplier(String idSupplier){
        if(Validation.isIdUser(idSupplier)){
            if(idSupplier.substring(0, 2).equalsIgnoreCase("SP")){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "'" + idSupplier + "' Kode ID Supplier tersebut tidak valid!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }
    
    public static boolean isIdPembeli(String idPembeli){
        if(Validation.isIdUser(idPembeli)){
             if(idPembeli.substring(0, 2).equalsIgnoreCase("PB")){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "'" + idPembeli + "' Kode ID Pembeli tersebut tidak valid!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }
    
    /**
     *  - nama harus terdiri dari 6-50 karakter
     *  - nama tidak boleh mengandung angka
     * 
     * @param nama nama yang akan dicek
     * @return 
     */
    public static boolean isNamaOrang(String nama){
        if(nama.length() >= 6 && nama.length() <= 50){
            if(!containsNumber(nama)){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Nama tidak boleh mengandung angka!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Nama harus terdiri dari 6-50 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static boolean isGender(String gender){
        return gender.equalsIgnoreCase("L") || gender.equalsIgnoreCase("P");
    }
    
    /**
     * Digunakan untuk mengecek apakah Tanggal lahir dari Siswa valid atau tidak. 
     * Method akan mengecek valid atau tidaknya tanggal lahir dari siswa menggunakan method
     * <code>isTanggal(String tanggal)</code> yang berada di class <code>Waktu</code>.
     * 
     * @param tanggal Tanggal lahir yang akan dicek.
     * @return <strong>True</strong> jika Tanggal lahir memenuhi kriteria tanggal yang ada di method <code>isTanggal(String tanggal).</code><br>
     *         <strong>False</strong> jika Tanggal lahir tidak memenuhi kriteria tanggal yang ada di method <code>isTanggal(String tanggal).</code><br>
     */
    public static boolean isTanggalLahir(String tanggal){
        return new Waktu().isTanggal(tanggal);
    }
    
    /**
     * @param bulan
     * @param tahun
     * @return 
     */
    public static boolean isValidPembayaran(String bulan, int tahun){
       String tahunAjaran = "";
       int tahunAwal = Integer.parseInt(tahunAjaran.substring(0, tahunAjaran.indexOf("-"))),
           tahunAkhir = Integer.parseInt(tahunAjaran.substring(tahunAjaran.indexOf("-")+1));
       if(tahun == tahunAwal){
           if(bulan.equalsIgnoreCase(Waktu.JULI) || bulan.equalsIgnoreCase(Waktu.AGUSTUS) || bulan.equalsIgnoreCase(Waktu.SEPTEMBER) || 
              bulan.equalsIgnoreCase(Waktu.OKTOBER) || bulan.equalsIgnoreCase(Waktu.NOVEMBER) || bulan.equalsIgnoreCase(Waktu.DESEMBER)
            ){
               return true;
           }else{
               Audio.play(Audio.SOUND_WARNING);
               JOptionPane.showMessageDialog(null, "Bulan Pembayaran Tidak Valid!!", "Pesan!", JOptionPane.WARNING_MESSAGE);
               return false;
           }
       }else if(tahun == tahunAkhir){
           if(bulan.equalsIgnoreCase(Waktu.JANUARI) || bulan.equalsIgnoreCase(Waktu.FEBRUARI) || bulan.equalsIgnoreCase(Waktu.MARET) || 
              bulan.equalsIgnoreCase(Waktu.APRIL) || bulan.equalsIgnoreCase(Waktu.MEI) || bulan.equalsIgnoreCase(Waktu.JUNI)
            ){
               return true;
           }else{
               Audio.play(Audio.SOUND_WARNING);
               JOptionPane.showMessageDialog(null, "Bulan Pembayaran Tidak Valid!!", "Pesan!", JOptionPane.WARNING_MESSAGE);
               return false;
           }
       }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Tahun Pembayaran Tidak Valid!!", "Pesan!", JOptionPane.WARNING_MESSAGE);
           return false;
       }
    }
    
    /**
     * - panjang dari username harus diantara 4 sampai 50 karakter
     * - username tidak boleh mengandung spasi
     * 
     * @param username username yang akan dicek
     * @return 
     */
    public static boolean isUsername(String username){
        if(username.length() >= 4 && username.length() <= 50){
            if(!username.contains(" ")){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Username tidak boleh mengandung spasi!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Panjang dari Username harus diantara 4-50 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     * - panjang dari password harus diantara 5-50 karakter
     * 
     * @param password password yang akan dicek
     * @return 
     */
    public static boolean isPassword(String password){
        if(password.length() >= 5 && password.length() <= 50){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Panjang dari Password harus diantara 5-50 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     * - panjangnya harus diantara 5-50 karakter
     * 
     * @param tempat nama tempat yang akan dicek
     * @return 
     */
    public static boolean isNamaTempat(String tempat){
        if(tempat.length() >= 5 && tempat.length() <= 50){
            return true;
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Panjang dari Nama Tempat harus diantara 5-50 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     * - panjang dari email harus terdiri dari 10-60 karakter
     * - alamat email yang didukung hanyalah @gmail, @yahoo dan @smkn1kts.sch.id
     * 
     * @param email email yang akan dicek
     * @return 
     */
    public static boolean isEmail(String email){
        String gmail = "@gmail.com", ymail = "@yahoo.com", sekolah = "@smkn1kts.sch.id", alamat;
        if(email.length() >= 10 && email.length() <= 60){
            if(email.contains("@") && email.contains(".")){
                alamat = email.substring(email.lastIndexOf("@"));
                if(alamat.equalsIgnoreCase(gmail) || alamat.equalsIgnoreCase(ymail) || alamat.equalsIgnoreCase(sekolah)){
                    return true;
                }else{
                    Audio.play(Audio.SOUND_WARNING);
                    JOptionPane.showMessageDialog(null, "Alamat email yang didukung hanyalah : @gmail.com, @yahoo.com dan @smkn1kts.sch.id", "Pesan", JOptionPane.WARNING_MESSAGE);
                }                
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Email tersebut tidak valid!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Panjang dari Email harus diantara 10-60 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     * - panjang dari nomor hp harus diantara 10-15 karakter
     * 
     * @param noHp nohp yang akan dicek
     * @return 
     */
    public static boolean isNoHp(String noHp){
        if(noHp.length() >= 10 && noHp.length() <= 15){
            if(noHp.startsWith("08")){
                return true;
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Nomor HP tidak valid!\nContoh Nomor HP yang valid : 085655864624", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Panjang dari Nomor Hp harus diantara 10-15 karakter!", "Pesan", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static boolean isLevel(com.users.UserLevels level){
        switch(level.name()){
            case "ADMIN" : return true;
            case "KARYAWAN" : return true;
            case "SUPPLIER" : return true;
            case "PEMBELI" : return true;
            default : return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Validation.isIdUser("KY001"));
    }
    
}
