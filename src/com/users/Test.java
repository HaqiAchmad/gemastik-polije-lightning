package com.users;


import com.data.app.Log;




/**
 *
 * @author Achmad Baihaqi
 */
public class Test {
    
    public static void main(String[] args) {
        
        Log.createLog();
        Users user = new Users();
        System.out.println(user.isLogin());
        
    }
}