package com.manage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-29
 */
public class Internet {
    
    /**
     * Digunakan untuk mengecek apakah user tersambung ke inernet atau tidak
     * 
     * @return Jika user tersambung te Internet maka akan mengembalikan nilai <b>True</b>. 
     *         Tapi jika user tidak tersambung ke Internet maka akan mengembalikan nilai <b>False</b>
     */
    public boolean isConnectInternet(){
        Socket s = new Socket();
        InetSocketAddress inet = new InetSocketAddress("www.google.com", 80);
        
            try{
                s.connect(inet, 1000);
                return true;
            }catch(Exception ex){
                return false;
            }finally{
                try{
                    s.close();
                }catch(Exception ex){
                    ex.printStackTrace();
//                    Apps.showException("Terjadi kesalahan yang tidak diketahui", Apps.class.getName(), ex.toString());
                }
            }
    }
    
    /**
     * digunakan untuk membuka sebuah link
     * 
     * @param link 
     * @throws java.io.IOException 
     * @throws java.net.URISyntaxException 
     */
    public void openLink(final String link) throws IOException, URISyntaxException {
        if(this.isConnectInternet()){
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(link));
            JOptionPane.showMessageDialog(null, "Sedang membuka link yang dituju!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Tidak terhubung ke internet!!");
        }
    }
    
}
