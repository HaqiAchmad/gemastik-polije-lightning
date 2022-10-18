package com.manage;

import com.data.db.Database;
import com.error.InValidUserDataException;
import com.users.UserData;
import com.users.UserLevels;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 */
public class Transaksi extends Database{
    
    public final Text txt = new Text();
    
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
    
}
