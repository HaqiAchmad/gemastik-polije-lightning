package com.main;

import com.data.app.Application;
import com.data.app.Log;
import com.data.db.Database;
import com.error.StorageNotFoundException;
import com.manage.Message;

/**
 * Class ini adalah class yang pertama kali dieksekusi saat program dijalankan
 * 
 * @author Achmad Baihaqi
 * @since 2021-03-07
 */
public class Main {

    public static void main(String[] args) {

        Log.createLog();
        
//         membuka aplikasi
        try {
            Application.startApplication();
        } catch (StorageNotFoundException ex) {
            Message.showException(null, ex);
        }
//        
//        Database dbase = new Database();
//        dbase.startConnection();
        
    }
}
