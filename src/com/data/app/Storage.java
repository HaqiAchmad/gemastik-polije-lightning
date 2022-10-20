package com.data.app;

import com.manage.FileManager;
import java.io.File;

/**
 * Class ini digunakan untuk memanajemen penyimpanan pada aplikasi. Penyimpanan yang akan 
 * dimanage adalah penyimpanan data seperti cache, log, user dan backup. 
 * 
 * @author Achmad Baihaqi
 * @since 2021-06-04
 */
public final class Storage {
    
//    public Storage(){
//        System.setProperty("os.name", "MacOS");
//    }
    
    /**
     * Digunakan untuk melakukan manipulasi terhadap folder atau file
     */
    private final FileManager fm = new FileManager();
    
    /**
     * Direktori dari storage aplikasi.
     */
    private final String WIN_DIR = String.format("%s\\AppData\\Local\\%s\\%s %s\\", System.getProperty("user.home"), Application.getCompany(), Application.getNama(), Application.getVersi()),
                         MAC_DIR = String.format("%s\\Library\\Application Support\\%s\\%s %s\\", System.getProperty("user.home"), Application.getCompany(), Application.getNama(), Application.getVersi());
    
    /**
     * Digunakan untuk mendapatkan direktori dari storage aplikasi.
     * 
     * @return direktori storage aplikasi.
     */
    private String getDirectory(){
        if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
            return this.WIN_DIR;
        }else{
            return this.MAC_DIR;
        }
    }
    
    /**
     * Digunakan untuk mengecek apakah direktori dari storage aplikasi exist atau tidak.
     * 
     * @return <strong>True</strong> jika direktori exist. <br>
     *         <strong>False</strong> jika direktori tidak exist.
     */
    public boolean isExistDirecotry(){
        return new File(this.getDirectory()).exists();
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah storage dari aplikasi sehat atau tidak. Sehat yang dimaksud adalah 
     * semua folder dan content yang ada didalam storage dari aplikasi exist. Jika ada salah satu saja folder atau content
     * yang ada didalam storage tidak exist maka storage akan dianggap tidak sehat.
     * 
     * @return <strong>True</strong> jika storage sehat. <br>
     *         <strong>False</strong> jika storage tidak sehat.
     */
    public boolean isHealtyStorage(){
        return this.isExistDirecotry() && this.isExistBackupDir() && this.isExistCacheDir() &&
               this.isExistLogDir() && this.isExistSettingDir() && this.isExistUsersDir();
    }
    
    /**
     * Method ini digunakan untuk memulihkan storage dari aplikasi. Method akan memulihkan folder dan content  
     * didalam storage yang tidak exist serpeti backup, cache, log, setting dan user.
     * 
     */
    public void recoverStorage(){
        this.createBackupDir();
        this.createCacheDir();
        this.createLogDir();
        this.createSettingDir();
        this.createUsersDir();
    }
    
    /**
     * Method ini digunakan untuk mendapatkan ukuran/size dari storage aplikasi. Untuk menghitung size dari 
     * storage method akan mendapatkan jumlah bytes yang ada setiap folder didalam storage seperti backup,
     * cache, log, setting, dan user dengan menggunakan method {@code getFolderBytes()} yang ada didalam class
     * {@code FileManager}.
     * <br><br>
     * Setelah jumlah bytes dari setiap folder didalam storage berhasil didapatkan maka selanjutnya method 
     * akan menjumlahkan semua jumlah bytes dari setiap folder menjadi satu. Selanjutnya method akan 
     * menghitung ukuran/size dari folder dengan menggunakan jumlah bytes dari folder yang sudah dijumlahkan 
     * menjadi satu tadi dengan melalui method {@code getSizeOfBytes()} pada class {@code FileManager}.
     * 
     * @return ukuran/size dari storage aplikasi.
     */
    public String getStorageSize(){
        double 
               // mendapatkan byte dari storage backup
               bck_db_old = fm.getFolderBytes(this.getBackupDir()+"databases\\db_old"),
               bck_db_new = fm.getFolderBytes(this.getBackupDir()+"databases\\db_new"),
               bck_pictures = fm.getFolderBytes(this.getBackupDir()+"pictures"),
               // mendapatakn byte dari storage cache
               chc_pictures = fm.getFolderBytes(this.getCacheDir()+"pictures"),
               chc_resized = fm.getFolderBytes(this.getCacheDir()+"pictures\\resized"),
               // mendapatakan byte dari storage log
               log = fm.getFolderBytes(this.getLogDir()),
               // mendapatkan byte dari storage setting
               setting = fm.getFolderBytes(this.getSettingDir()),
               // mendapatkan byte dari storage users
               users = fm.getFolderBytes(this.getUsersDir());
        
        // menhitung dan mengembalikan ukuran dari folder
        return fm.getSizeOfBytes(bck_db_old+bck_db_new+bck_pictures+chc_pictures+chc_resized+log+setting+users);
    }
    
    /**
     * Method ini digunakan untuk membuat Storage Backup berserta folder dan content yang ada didalamnya 
     * jika Storage Backup tidak exist. Method akan membuat Storage Backup berserta folder dan contenya yang 
     * ada didalamnya dengan melalui method {@code createFolders()} yang ada didalam class {@code FileManager}.
     * 
     */
    public void createBackupDir(){
        fm.createFolders(this.getBackupDir());
        fm.createFolders(this.getBackupDir()+"databases");
        fm.createFolders(this.getBackupDir()+"databases\\db_old");
        fm.createFolders(this.getBackupDir()+"databases\\db_new");
        fm.createFolders(this.getBackupDir()+"pictures");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori Storage Backup dari aplikasi dalam bentuk string.
     * 
     * @return direktori storage backup.
     */
    public String getBackupDir(){
        return this.getDirectory() + "Backup\\";
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Storage Backup berserta folder dan content yang ada 
     * didalamnya exist atau tidak. Method akan mengembalikan nilai <code>true</code> jika Storage 
     * Backup bersarta folder dan content yang ada didalamnya exist.
     * 
     * @return <strong>True</strong> jika storage backup exist. <br>
     *         <strong>False</strong> otherwise.
     */
    public boolean isExistBackupDir(){
        return new File(this.getBackupDir()).exists() &&
               new File(this.getBackupDir()+"databases").exists() &&
               new File(this.getBackupDir()+"pictures").exists();
    }
    
    /**
     * Method ini digunakan untuk membuat Stroage Cache berserta folder dan content yang ada didalamnya 
     * jika Stroage Cache tidak exist. Method akan membuat Stroage Cache berserta folder dan contenya yang 
     * ada didalamnya dengan melalui method {@code createFolders()} yang ada didalam class {@code FileManager}.
     * 
     */
    public void createCacheDir(){
        fm.createFolders(this.getCacheDir());
        fm.createFolders(this.getCacheDir()+"pictures\\resized");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori Storage Cache dari aplikasi dalam bentuk string.
     * 
     * @return direktori storage cache.
     */
    public String getCacheDir(){
        return this.getDirectory() + "Cache\\";
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Storage Cache berserta folder dan content yang ada 
     * didalamnya exist atau tidak. Method akan mengembalikan nilai <code>true</code> jika Storage 
     * Cache bersarta folder dan content yang ada didalamnya exist.
     * 
     * @return <strong>True</strong> jika storage cache exist. <br>
     *         <strong>False</strong> otherwise.
     */
    public boolean isExistCacheDir(){
        return new File(this.getCacheDir()).exists() && 
               new File(this.getCacheDir()+"pictures\\resized").exists();
    }
    
    /**
     * Method ini digunakan untuk menghitung ukuran/size Storage Cache dari aplikasi. Sebelum menghitung
     * size dari Storage Cache method akan mendapatkan jumlah byte dari folder yang ada didalam Storage 
     * Cache dengan menggunakan method {@code getFolderBytes()} yang ada didalam class {@code FileManager}.
     * <br><br>
     * Setelah jumlah byte dari folder yang ada didalam Storage Cache didapatkan maka selanjutnya method 
     * akan menghitung ukuran/size dari Storage Cache dengan menggunakan jumlah byte dari folder tadi 
     * dengan melalui method {@code getSizeOfBytes()} pada class {@code FileManager()}.
     * 
     * @return ukuran/size dari Storage Cache.
     */
    public String getCacheSize(){
        // mendapatkan byte dari foder cache
        double pictues = fm.getFolderBytes(this.getCacheDir()+"pictures"),
               resized = fm.getFolderBytes(this.getCacheDir()+"pictures\\resized");
        // menghitung dan mengebalikan ukuran dari folder cache
        return fm.getSizeOfBytes(pictues+resized);
    }
    
    /**
     * Method ini digunakan untuk membersihakan content-content yang ada didalam Storage Cache. 
     * Content-content yang ada didalam storage cache perlu dibersihkan jika jumlahnya sudah banyak dikarenakan
     * ukuran/size-nya yang cenderung memakan memory. Untuk membersihkan content-content yang ada didalam 
     * Storage Cache pertama-tama method akan menghapus Storage Cahce dengan menggunakan method {@code deleteFile()}
     * yang ada didalam class {@code FileManager}. Setelah Storage Cache berhasil dihapus maka method akan membuat
     * Storage Cahce kembali dengan menggunakan method {@code createCacheDir()}.
     * 
     */
    public void clearCache(){
        // menghapus cache
        fm.deleteFile(this.getCacheDir());
        // membuat ulang folder cache
        this.createCacheDir();
    }
    
    /**
     * Method ini digunakan untuk membuat Storage Log berserta folder dan content yang ada didalamnya 
     * jika Storage Log tidak exist. Method akan membuat Storage Log berserta folder dan contenya yang 
     * ada didalamnya dengan melalui method {@code createFolders()} dan {@code createFile()} yang ada 
     * didalam class {@code FileManager}.
     * 
     */
    public void createLogDir(){
        fm.createFolders(this.getLogDir());
        fm.createFile(this.getLogDir()+"Log.txt");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori Storage Log dari aplikasi dalam bentuk string.
     * 
     * @return direktori storage log.
     */
    public String getLogDir(){
        return this.getDirectory() + "Log\\";
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Storage Log berserta folder dan content yang ada 
     * didalamnya exist atau tidak. Method akan mengembalikan nilai <code>true</code> jika Storage 
     * Log bersarta folder dan content yang ada didalamnya exist.
     * 
     * @return <strong>True</strong> jika storage log exist. <br>
     *         <strong>False</strong> otherwise.
     */
    public boolean isExistLogDir(){
        return new File(this.getLogDir()).exists() &&
               new File(this.getLogDir()+"Log.txt").exists();
    }
    
    /**
     * Method ini digunakan untuk membuat Storage Setting berserta folder dan content yang ada didalamnya 
     * jika Storage Setting tidak exist. Method akan membuat Storage Setting berserta folder dan contenya yang 
     * ada didalamnya dengan melalui method {@code createFolders()} yang ada didalam class {@code FileManager}.
     * 
     */
    public void createSettingDir(){
        fm.createFolders(this.getSettingDir());
        fm.createFile(this.getSettingDir()+"Settings.haqi");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori Storage Setting dari aplikasi dalam bentuk string.
     * 
     * @return direktori storage setting.
     */
    public String getSettingDir(){
        return this.getDirectory() + "Setting\\";
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Storage Setting berserta folder dan content yang ada 
     * didalamnya exist atau tidak. Method akan mengembalikan nilai <code>true</code> jika Storage 
     * Setting bersarta folder dan content yang ada didalamnya exist.
     * 
     * @return <strong>True</strong> jika storage setting exist. <br>
     *         <strong>False</strong> otherwise.
     */
    public boolean isExistSettingDir(){
        return new File(this.getSettingDir()).exists() &&
               new File(this.getSettingDir()+"Settings.haqi").exists();
    }
    
    /**
     * Method ini digunakan untuk membuat Storage Users berserta folder dan content yang ada didalamnya 
     * jika Storage Users tidak exist. Method akan membuat Storage Users berserta folder dan contenya yang 
     * ada didalamnya dengan melalui method {@code createFolders()} dan {@code createFile()} yang ada 
     * didalam class {@code FileManager}.
     * 
     */
    public void createUsersDir(){
        fm.createFolders(this.getUsersDir());
        fm.createFile(this.getUsersDir()+"login_data.haqi");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan direktori Storage Users dari aplikasi dalam bentuk string.
     * 
     * @return direktori storage users.
     */
    public String getUsersDir(){
        return this.getDirectory() + "Users\\login_data.haqi";
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah Storage Users berserta folder dan content yang ada 
     * didalamnya exist atau tidak. Method akan mengembalikan nilai <code>true</code> jika Storage 
     * Users bersarta folder dan content yang ada didalamnya exist.
     * 
     * @return <strong>True</strong> jika storage users exist. <br>
     *         <strong>False</strong> otherwise.
     */
    public boolean isExistUsersDir(){
        return new File(this.getUsersDir()).exists() &&
               new File(this.getUsersDir()+"login_data.haqi").exists();
    }
    
    
    public static void main(String[] args) {
        
        Storage str = new Storage();

    }
    
}