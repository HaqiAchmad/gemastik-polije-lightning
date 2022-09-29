package com.data.app;

import com.data.db.Database;
import com.data.db.DatabaseTables;
import com.manage.Message;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-15
 */
public class Kelas extends Database{
    
    public Kelas(){
        this.startConnection();
    }
    
    public static final String TPM = "TPM", TITL = "TITL", TOI = "TOI", TBS = "TBS", TBG = "TBG", RPL = "RPL";
    
    public boolean addKelas(String id, String namaKelas, String levelKelas, String jurusan){
        String query = "INSERT INTO kelas VALUES ("
                +"'"+id+"', '"+namaKelas+"', '"+levelKelas+"', '"+jurusan+"');";
        return this.addData(query);
    }
    
    public String[] getIDs(){
        try{
            int index = 0;
            // menginisialisasi array seseuai dengan jumlah data pada tabel kelas yg ada didalam database
            String[] IDs = new String[super.getJumlahData(Database.KELAS)];
            // mengeksekusi query untuk mendapatkan data id kelas
            res = stat.executeQuery("SELECT id_kelas FROM kelas");
            // mendapatkan data id spp
            while(res.next()){
                IDs[index] = res.getString("id_kelas");
                index++;
            }
            return IDs;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    public String getNamaKelas(String id){
        return this.getData(KELAS, "nama_kelas", "WHERE id_kelas = '"+id+"'");
    }
    
    public boolean setNamaKelas(String nis, String newNama){
        return this.setData(KELAS, "nama_kelas", "id_kelas", nis, newNama);
    }
    
    public String getLevelKelas(String id){
        return this.getData(KELAS, "level_kelas", "WHERE id_kelas = '"+id+"'");
    }    
    
    public String getGelombang(String id){
        String namaKelas = getNamaKelas(id);
        return ""+namaKelas.charAt(namaKelas.length()-1);
    }
    
    public boolean setLevelKelas(String id, String newLevel){
        return this.setData(KELAS, "level_kelas", "id_kelas", id, newLevel);
    }
    
    public String getJurusan(String id){
        return this.getData(KELAS, "jurusan", "WHERE id_kelas = '"+id+"'");
    }
    
    public String getLevelName(String id){
        switch(this.getLevelKelas(id)){
            case "X": return "X (sepuluh)";
            case "XI": return "XI (sebelas)";
            case "XII": return "XII (dua belas)";
            case "XIII": return "XIII (tiga belas)";
            default: return null;
        }
    }
    
    public String getJurusanName(String id){
        switch(this.getJurusan(id)){
            case TPM: return "Teknik Pemesinan";
            case TITL: return "Teknik Instalasi Tenaga Listrik";
            case TOI: return "Teknik Otomasi Industri";
            case TBG: return "Tata Boga";
            case TBS: return "Tata Busana";
            case RPL: return "Rekayasa Perangkat Lunak";
            default : return null;
        }
    }
    
    public boolean setJurusan(String id, String newJurusan){
        return this.setData(KELAS, "jurusan", "id_kelas", id, newJurusan);
    }
    
    public String[] getTahunAjaran(String kelas){
        switch(kelas){
            case "X": return new String[]{"2020-2021", "2021-2022", "2022-2023"};
            case "XI": return new String[]{"2019-2020", "2020-2021", "2021-2022"};
            case "XII": return new String[]{"2018-2019","2019-2020","2020-2021"};
            case "XIII": return new String[]{"2017-2018", "2018-2019","2019-2020","2020-2021"};
            default: return null;
        }
    }
    
    public boolean deleteKelas(String id){
        return this.deleteData(KELAS, "id_kelas", id);
    }
    
    /**
     * Method ini digunakan untuk mendapatkan total kelas yang terdaftar di <b>Database</b> aplikasi. Method akan mendapatkan 
     * data total kelas dengan melalui method {@code getJumlahData()} yang ada didalam class {@code Database}.
     * 
     * @return total kelas pada aplikasi.
     */
    public int getTotalKelas(){
        return super.getJumlahData(DatabaseTables.KELAS.name());
    }
    
    /**
     * Method ini digunakan untuk mendapatkan total siswa yang ada didalam kelas.
     * 
     * @param idKelas
     * @return 
     */
    public int getTotalSiswa(String idKelas){
        return super.getJumlahData(DatabaseTables.SISWA.name(), "WHERE id_kelas = '" + idKelas + "'");
    }
    
}
