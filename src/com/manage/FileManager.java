package com.manage;

import com.data.app.Log;
import com.media.Audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Class ini digunakan untuk memanipulasi file, megelola file dan mendapatkan informasi mengenai file.
 * Class {@code FileManager} ini dibuat di karenakan banyaknya file yang harus dimanajemen/dikelola pada 
 * aplikasi ini. Oleh karena itu diperlukanya sebuah class yang dapat menangani banyaknya file pada aplikasi
 * sehingga efisiensi code dan eksekusi aplikai jauh lebih efisien. Method-Method yang ada didalam class ini 
 * banyak bergantung pada class-class yang ada didalam package {@code java.io}.
 *
 * @author Achmad Baihaqi
 * @since 2021-06-04
 */
public class FileManager {
    
    /**
     * Batas maksimal ukuran file yang dibisa diproses oleh aplikasi secara default 
     * adalah 4 Gb atau setara dengan 4,295,967,295 bytes. 4 Gb merujuk pada batas 
     * maksimal tipe data <code>LongBlob</code> pada <code>SQL</code> yang memiliki 
     * ukuran penyimpanan maksimal 4 Gb.
     */
    private final long maxSizeFile = 4_295_967_295L;

    /**
     * Method ini digunakan untuk membuat sebuah file baru. Method akan membuat 
     * sebuah file baru dengan menggunakna method <code>createNewFile()</code> yang 
     * berada didalam class <code>File</code>. User harus menuliskan direktori dan nama 
     * file yang akan dibuat melalui parameter <code>newFile</code>. Jika user tidak menuliskan 
     * direktori dari file yang akan dibuat maka secara otomatis file akan dibuat didalam project.
     *
     * @param newFile direktori dan nama file dari file yang akan dibuat.
     * @return <strong>True</strong> Jika file berhasil dibuat. <br>
     *         <strong>False</strong> Jika file gagal dibuat.
     */
    public boolean createFile(String newFile) {
        try {
            Log.addLog("Membuat file '" + newFile + "'");
            return new File(newFile).createNewFile();
        } catch (IOException ex) {
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan : " + ex.getMessage());
        }
        return false;
    }

    /**
     * Method ini digunakan untuk membuat sebuah folder baru didalam komputer. 
     * Method ini dapat membuat beberapa sub folder sekaligus. Method akan membuat 
     * sebuah folder dengan menggunakan method <code>mkdirs()</code> yang berada didalam 
     * class <code>File</code>.
     *
     * @param newFolder nama folder yang akan dibuat.
     * @return <strong>True</strong> Jika folder berhadil dibuat. <br>
     *         <strong>False</strong> Jika folder gagal dibuat.
     */
    public boolean createFolders(String newFolder) {
        Log.addLog("Membuat folder '" + newFolder + "'");
        return new File(newFolder).mkdirs();
    }

    /**
     * Method ini digunakan untuk mendapatkan semua nama file yang ada didalam direktori yang 
     * dinputkan dalam bentuk sebuah array Object. Method akan mendapatkan semua file yang 
     * ada didalam direktori/folder yang dinputkan melalui class {@code Path}. Sebelumnya 
     * method akan sementara menyimpan semua data nama file yang didapatkan ke dalam 
     * class {@code List}. Jika semua nama file yang ada didalam direktori sudah didapatkan 
     * maka method akan mengkonversi {@code List} tersebut kedalam sebuah array {@code Object}.
     * 
     * @param direktori direktori yang akan didapatkan list filenya.
     * @return semua nama file yang ada didalam direktori yang diinputkan.
     * 
     * @throws java.nio.file.NoSuchFileException jika direktori yang diinputkan tidak ditemukan.
     * @throws java.io.IOException jika terjadi kesalahan saat pengoperasian file.
     */
    public Object[] getListFile(String direktori) throws NoSuchFileException, IOException{
        // menginputkan direktori yg diinputkan untuk didapatkan filenya memlalui class Path
        Path path = Paths.get(direktori);
        Stream<Path> sub = java.nio.file.Files.walk(path, 1);

        // mendapatkan semua file yang ada dialam folder
        List<String> list = sub.filter(java.nio.file.Files::isRegularFile)
                .map(Objects::toString)
                .collect(Collectors.toList());

        // mengkonversi List kedalam bentuk array Object
        return list.toArray(); 
    }
    
    /**
     * Method ini digunakan untuk mencopy sebuah file kedalam folder/direktori yang diinputkan.
     * Method akan mencopy file dengan menggunakan bantuan dari method {@code copy()} yang ada didalam 
     * class {@code Files}. Sebelum mencopy file method akan mengecek apakah file tersebut sudah ada di
     * direktori yang diinputkan atau tidak. Jika file sudah exist maka file tidak akan dibuat dan 
     * method akan mengembalikan nilai <strong>False</strong>. Jika tidak terjadi error saat proses 
     * copy berlangsung maka method akan  mengembalikan nilai <strong>True</strong>.
     * 
     * @param file file yang akan dicopy.
     * @param destination folder tujuan dari file.
     * @return <strong>True</strong> jika proses copy berhasil. <br>
     *         <strong>False</strong> jika proses copy gagal.
     */
    public boolean copyFile(String file, String destination){
        
        // membuat file baru dengan format dan nama dari file yang diinputkan di direktori yang dituju.
//        File newFile = new File(destination + this.getNamaFile(file) + this.getFormatFile(file));
        File newFile = new File(destination + new File(file).getName());
        try{
            // mengecek apakah file baru exist atau tidak.
            if(!newFile.exists()){
                Log.addLog("Mencopy file " + file + " ke " + destination);
                // mencopy file ke direktori yang diinputkan.
                java.nio.file.Files.copy(new File(file).toPath(), newFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                return true;
            }
        }catch(IOException ex){
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return false;
    }
    
    /**
     * Method ini digunakan untuk mencopy beberapa file sekaligus kedalam folder/direktori yang diinputkan.
     * Method akan melakukan perulangan for each yang digunakan untuk membaca semua file yang diinputkan 
     * dari parameter {@code files} yang berbentuk array {@code Object}. Selama melakukan perulangan method 
     * akan mencopy satu persatu file yang ada didalam array {@code Object} melalui method {@code copyFile()}.
     * Hal ini akan terus dilakukan sampai tidak ada lagi file yang ada dalam array {@code Object}.
     * 
     * @param files file yang akan dicopy.
     * @param destination folder tujuan dari file.
     */
    public void copyFile(Object files [], String destination){
        for(Object file : files){
            this.copyFile(file.toString(), destination);
        }
    }
    
    /**
     * Method ini digunakan untuk mengkonversi sebuah file menjadi sebuah bytes stream. 
     * Bytes Stream inilah yang nantinya akan dikonversi kedalam bentuk binary yang nantinya 
     * akan disimpan pada tipe data {@code Blob} yang ada di {@code MySQL}. Seperti yang sudah kita 
     * ketahui tipe data {@code Blob} digunakan untuk menyimpan sebuah data multimedia kedalam tabel MySQL.
     * 
     * @param file File yang akan dikonversi menjadi {@code Blob}
     * @return Bytes stream dari file.
     * 
     * @throws FileNotFoundException jika file yang dinputkan tidak ditemukan.
     */
    public InputStream fileToBlob(File file) throws FileNotFoundException{
        // mengkonversi file kedalam bentuk byte stream
        return new FileInputStream(file);
    }
    
    /**
     * Method ini digunakan untuk menkonversi tipe data {@code Blob} yang ada didalam {@code MySQL} 
     * menjadi sebuah File. Seperti yang sudah kita ketahui bahwa tipe data {@code Blob} adalah tipe data 
     * yang menyimpan binary dari sebuah File kedalam tabel {@code MySQL}. Untuk merubah binary dari file
     * tersebut maka perlu dilakukanya konversi.
     * <br><br>
     * Saat mengkonversi file hal pertama-tama yang dilakukan adalah mendapatkan semua binary yang ada 
     * didalam {@code Blob} tersebut melalui method {@code getBinaryStream()} dan outputnya akan disimpan 
     * kedalam object dari {@code InputStream}. Selanjutnya method akan membuat sebuah file baru dengan 
     * menggunakan class {@code FileOutputStream}. Direktori dan nama file yang akan dibuat merujuk pada 
     * parameter {@code destination}.
     * <br><br>
     * Selanjutnya method akan membaca semua binary yang ada didalam {@code InputStream} melalui method 
     * {@code read()} sampai tidak ada lagi binary yang tersisa didalam {@code InputStream}. Selama proses 
     * pembacaan binary sedang berlangsung method akan menulisakan binary yang sedang dibaca terebut kedalam 
     * file baru yang barusan dibuat tadi melalui method {@code write()}.
     * 
     * @param blob blob yang akan dikonversi.
     * @param destination 
     * @throws IOException jika terjadi kesalahan saat pengoperasian file.
     * @throws SQLException jika terjadi kesalahan saat mengakses Database {@code MySQL}.
     */
    public void blobToFile(Blob blob, String destination) throws IOException, SQLException{
        int buff;
        // mendapatkan byte/binary stream dari blob yang dinputkan
        InputStream input = blob.getBinaryStream();
        // file output dari hasil konversi
        FileOutputStream fos = new FileOutputStream(destination);

        // membaca semua byte/binary stream yang ada didalam blob
        while((buff = input.read()) != -1){
            // menuliskan byte/binary stream ke-dalam file output
            fos.write(buff); 
        }

        // menutup file 
        input.close();
        fos.close();
    }
    
    /**
     * Digunakan untuk menampilkan sebuah dialog yang digunakan untuk memilih file. Method akan 
     * menampilkan dialog untuk memilih file menggunakan method {@code showOpenDialog()} yang ada 
     * didalam class {@code JFileChooser}. Method ini dapat memilter format file apa saja yang 
     * dapat dipilih oleh user. Method ini juga dapat besaran minimal ukuran dari sebuah file.
     * 
     * @param currentDirectory direktori yang akan dituju.
     * @param limitSize ukuran maksimal dari File.
     * @param description deskripsi dari format File.
     * @param format format File yang didukung.
     * @return jika user memilih sebuah file maka method akan mengembalikan direktori dari file tersebut. <br>
     *         Tapi jika user tidak memilih sebuah file maka method akan mengembalikan nilai null.
     */
    public String chooseFile(String currentDirectory, long limitSize, String description, String... format){
        
        JFileChooser fileChooser = new JFileChooser();
        // mengatur current directory sesuai yg dengan diinputkan user
        fileChooser.setCurrentDirectory(new File(currentDirectory)); 
        
        // mengatur format file yang didukung
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, format); 
        fileChooser.addChoosableFileFilter(filter); 
        
        // membuka dialog untuk memilih file
        int res = fileChooser.showOpenDialog(null); 
        
        // jika user memilih sebuah file
        if(res == JFileChooser.APPROVE_OPTION){
            // mendapatkan file yang dipilih user
            File selected = fileChooser.getSelectedFile();
            // mengecek apakah file yang dipilih user ukuranya kurang dari limitSize atau tidak
            if(selected.length() <= limitSize){
                // mengembalikan direktori dari file yang dipilih oleh user
                return selected.getAbsolutePath();
            }else{
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Ukuran dari file lebih besar dari " + this.getSizeOfBytes(limitSize), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        // jika user tidak jadi memilih sebuah file
        return null;
    }
    
    /**
     * Digunakan untuk menampilkan sebuah dialog yang digunakan untuk memilih file dengan setelah default.
     * 
     * @return jika user memilih sebuah file maka method akan mengembalikan direktori dari file tersebut. <br>
     *         Tapi jika user tidak memilih sebuah file maka method akan mengembalikan nilai null.
     */
    public String chooseFile(){
        return this.chooseFile(System.getProperty("user.home"), maxSizeFile, "*.DEFAULT", "java", "txt", "haqi", "sql", "properties");
    }
    
    /**
     * Digunakan untuk mendapatkan nama file yang ada didalam direktory. Method akan 
     * mendapatkan nama dari file menggunakan method {@code getName} yang ada didalam class
     * {@code File}. Sebelum mendapatkan nama dari file method akan mengecek apakah yang 
     * diinputkan oleh user adalah sebuah file atau tidak. Jika yang diinputkan oleh user 
     * bukanlah sebuah file maka method akan mengembalikan nilai <strong>null</strong>.
     * <br><br>
     * <b>Example : </b> 'C:\Users\Baihaqi\Desktop\aplikasi.exe' maka hasil outputnya adalah '<i>aplikasi</i>'.
     * 
     * @param file file yang ingin didapatkan namanya.
     * @return nama dari file yang diinputkan.
     */
    public String getNamaFile(String file) {
        if(new File(file).isFile()){
            return new File(file).getName();
        }
        return null;
    }

    /**
     * Digunakan untuk mendapatkan format dari file yang diinputkan. Sebelum mendapatkan format
     * dari file method akan mengecek apakah yang diinputkan user itu file atau tidak. Jika bukan 
     * file maka method akan mereturn null. Jika file method akan mereturn format dari file. Format 
     * dari file akan didapatkan dengan melakukan substring pada file yang dinputkan dari index 
     * terakhir dari '.' sampai dengan index yang terakhir.
     * <br><br>
     * <b>Example : </b> 'C:\Users\Baihaqi\Desktop\aplikasi.exe' maka hasil outputnya adalah '<i>.exe</i>'.
     *
     * @param file file yang akan didapatkan formatnya.
     * @return format dari file.
     */
    public String getFormatFile(String file) {
        if(new File(file).isFile()) {
            return file.substring(file.lastIndexOf("."));
        }
        return null;
    }

    /**
     * Method ini digunakan untuk mengubah nama dari sebuah file yang diinputkan user.
     * Method akan mengubah nama file menggunakan method {@code renameTo()} yang ada didalam 
     * class {@code File}. Jika output dari method {@code renameTo()} adalah <strong>True</strong>
     * maka file berhasil direname dan method akan mengembalikan nilai <strong>True</strong>.
     *
     * @param file file yang akan direname;
     * @param newName nama baru dari file
     * @return <strong>True</strong> jika file berhasil direname. <br>
     *         <strong>False</strong> jika file gagal direname.
     */
    public boolean renameFile(String file, String newName) {

        // mendapatkan direktori dari file
        String direktori = new File(file).getParentFile() + "\\",
               // membuat nama file yang baru 
               ubahNama = direktori + newName + getFormatFile(file); 

        // mengubah nama dari file
        return new File(file).renameTo(new File(ubahNama));
    }

    /**
     * Method ini digunakan untuk menghitung berapa jumlah bytes yang ada didalam folder. Tujuan dibuatnya method ini 
     * adalah untuk menhitung ukuran dari sebuah folder. Method hanya akan menhitung jumlah bytes pada folder yang diinputkan 
     * jika folder yang diinputkan memiliki child folder maka bytes dari child folder tidak akan dihitung.
     * <br><br>
     * Petama-tama method akan memdapatkan semua direktori dari file yang ada didalam folder yang diinputkan dengan mengunakan 
     * method {@code getListFile()} dan outputnya akan disimpan pada array {@code Object}. Setelah direktori didapatkan maka 
     * selanjutnya method akan membaca semua data yang ada didalam array {@code Object} yang berupa direktori dari file.
     * <br><br>
     * Saat membaca data yang ada didalam array {@code Object} method akan menghitung satu-persatu jumlah bytes pada setiap direktori
     * file dengan menggunakan method {@code length()} pada class {@code File}. Hal ini akan terus-menerus dilakukan sampai tidak 
     * ada lagi data yang ada di dalam array {@code Object}.
     * 
     * @param folder folder yang akan dihitung jumlah bytes-nya.
     * @return jumlah bytes dari folder.
     */
    public double getFolderBytes(String folder){
        try{
            double bytes = 0;
            // mendapatkan direktori dari file yang ada didalam folder
            Object[] files = this.getListFile(folder);
           
            // menghitung bytes dari setiap file
            for(Object file : files){
                bytes += new File(file.toString()).length();
            }
            
            return bytes;
        }catch(IOException ex){
            Message.showException(this, ex, true);
        }
        return -1;
    }
    
    /**
     * Method ini digunakan untuk mendapatkan ukuran dari sebuah folder yang diinputkan. 
     * Sebelum menghitung ukuran dari folder method akan mendapatakan jumlah bytes yang ada didalam 
     * dari folder dengan menggunakan method {@code getFolderBytes()}. Setelah jumlah bytes sudah 
     * didapatkan maka selanjutnya method akan menghitung size dari folder.
     * 
     * @param folder folder yang akan dihitung sizenya.
     * @return ukuran/size dari folder yang diinputkan.
     */
    public String getSizeFolder(String folder){
        return this.getSizeOfBytes(this.getFolderBytes(folder));
    }
    
    /**
     * Method ini digunakan untuk mendapatkan ukuran dari sebuah file yang diinputkan.
     * Method akan mendapatkan ukuran/size dari file menggunakan method {@code getSizeFile(double bytes)}.
     * Method {@code getSizeFile(double bytes} memerlukan sebuah input bytes dari file. Bytes dari 
     * file akan didapatkan melalui method {@code length()} yang ada didalam class {@code File}.
     * 
     * @param file file yang akan dihitung ukuranya.
     * @return ukuran/size dari file yang dinputkan.
     */
    public String getSizeFile(String file) {
        return this.getSizeOfBytes(new File(file).length());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan ukuran dari beberapa file sekaligus yang ada didalam 
     * parameter file. Method akan membaca semua file yang ada didalam parameter file dengan 
     * menggunakan for each. Selama proses membaca file method akan menghitung jumlah bytes pada file 
     * yang sedang dibaca dengan menggunakan method {@code length()} yang ada didalam class {@code File}.
     * Jika semua file sudah dibaca maka method akan mendapatkan ukuran/size dari file menggunakan method 
     * {@code getSizeFile(double bytes)}.
     * 
     * @param file file yang akan dihitung ukuranya.
     * @return ukuran/size dari file yang dinputkan.
     */
    public String getSizeFile(Object[] file){
        double bytes = 0;
        for(Object filename : file){
            bytes += new File(filename.toString()).length();
        }
        
        return this.getSizeOfBytes(bytes);
    }
    
    /**
     * Digunakan untuk menghitung satuan ukuran dari sebuah file berdasarkan bytes yang diinputkan .
     * Satuan ukuran file yang dapat dihitung oleh method ini hanyalah bytes, Kilobyte (KB), 
     * Megabyte (MB) dan Gigabyte (GB).
     * <br><br>
     * <b>Berikut ini adalah kriteria satuan dari file berdasarkan bytes yang dinputkan.</b>
     * <br>
     * <ul>
     *  <li><b>bytes : </b> berkisar dari 0 bytes sampai 1023 bytes. </li>
     *  <li><b>Kilobyte (KB) : </b> berkisar dari 1024 bytes sampai 1,048,575 bytes.</li>
     *  <li><b>Megabyte (MB) : </b> berkisar dari 1,048,576 bytes sampai 1.073.741.823 bytes.</li>
     *  <li><b>Gigabyte (MB) : </b> berkisar dari 1.073.741.824 bytes keatas.</li>
     * </ul>
     * <br>
     * <b>Note : </b> Jika size file adalah Terabyte maka akan dianggap sebagai Gigabyte.
     *                Jika terjadi error saat menghitung satuan dari file maka method akan 
     *                mengembalikan nilai '-1 bytes'.
     *
     * @param bytes byte dari file yang akan dihitung satuanya.
     * @return satuan ukuran dari file yang diinputkan.
     */
    public String getSizeOfBytes(double bytes){
        if (bytes > 0 && bytes < 1024) {
            return String.format("%.2f bytes", bytes);
        } else if (bytes >= 1_024 && bytes < 1_048_576) {
            return String.format("%.2f KB", bytes / 1024);
        } else if (bytes >= 1_048_576 && bytes < 1_073_741_824) {
            return String.format("%.2f MB", bytes / (1024 * 1024));
        } else if (bytes >= 1_073_741_824) {
            return String.format("%.2f GB", bytes / (1024 * 1024 * 1024));
        }
        
        return "-1 bytes";
    }
    
    /**
     * Method ini digunakan untuk menghapus sebuah file/folder yang diinputkan oleh user.
     * Method akan menghapus file/folder dengan menggunakan method {@code delete()} yang ada 
     * didalam class {@code File}. Jika output dari method {@code delete()} tersebut <strong>True</strong>
     * maka file/folder berhasil dihapus dan method akan mengembalikan nilai <strong>True</strong>.
     *
     * @param file file/folder yang akan dihapus.
     * @return <strong>True</strong> jika file berhasil dihapus. <br>
     *         <strong>False</strong> jika file gagal dihapus.
     */
    public boolean deleteFile(String file) {
        Log.addLog("Menghapus file '" + file + "'");
        return new File(file).delete();
    }    
    
}
