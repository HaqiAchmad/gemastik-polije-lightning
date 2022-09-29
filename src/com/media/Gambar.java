package com.media;

import com.data.app.Application;
import com.data.app.Log;
import com.data.app.Storage;
import com.manage.FileManager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Achmad Baihaqi
 * @since 2020-11-23
 */
public class Gambar {
    
    private static final ImageIcon icWindow = Application.getWindowIcon();
    
    private static final String DIREKTORY_ICONS = "src\\resources\\image\\icons\\";
    
    public static Image getWindowIcon(){
        return icWindow.getImage();
    }
    
    public static ImageIcon getTopIcon(){
        // mendapatkan file top icon
        File fileIcon = new File(Application.getTopIcon().toString()),
             // file cache dari top icon 
             fileCache = new File(new Storage().getCacheDir() + "pictures\\resized\\logo-top-icon.png");
        
        // jika file top icon exist
        if(fileIcon.exists()){
            // mengecek apakah ukuran dari file top icon adalah 35x35
            if(Gambar.getWidth(fileIcon) == 35 && Gambar.getHeight(fileIcon) == 35){
                return Application.getTopIcon();
            }else{
                // mengecek apakah file dari file top icon dengan ukuran 35x35 ada dicache atau tidak
                if(fileCache.exists()){
                    System.out.println("get this top");
                    return new ImageIcon(fileCache.toString());
                }else{
                    // meresize file top icon ke ukuran 35x35
                    return new ImageIcon(Gambar.resizeImage(fileIcon, 35, 35, "logo-top-icon").toString());
                }                
            }
        }
        // jika file top icon tidak exist 
        return null;
    }
    
    public static ImageIcon getBottomIcon(){
        // mendapatkan file bottom icon
        File fileIcon = new File(Application.getBottomIcon().toString()),
             // file cache dari bottom icon 
             fileCache = new File(new Storage().getCacheDir() + "pictures\\resized\\logo-bottom-icon.png");
        
        // jika file bottom icon exist
        if(fileIcon.exists()){
            // mengecek apakah ukuran dari file bottom icon adalah 30x37
            if(Gambar.getWidth(fileIcon) == 30 && Gambar.getHeight(fileIcon) == 37){
                return Application.getBottomIcon();
            }else{
                // mengecek apakah file dari file bottom icon dengan ukuran 30x37 ada dicache atau tidak
                if(fileCache.exists()){
                    System.out.println("get this bottom");
                    return new ImageIcon(fileCache.toString());
                }else{
                    // meresize file bottom icon ke ukuran 30x37
                    return new ImageIcon(Gambar.resizeImage(fileIcon, 30, 37, "logo-bottom-icon").toString());
                }                
            }
        }
        // jika file bottom icon tidak exist 
        return null;
    }
    
    private static int getWidth(File file){
        return new ImageIcon(file.toString()).getIconWidth();
    }
    
    private static int getHeight(File file){
        return new ImageIcon(file.toString()).getIconHeight();
    }
    
    public static ImageIcon getIcon(final String icon){
        File file = new File(Gambar.DIREKTORY_ICONS + icon);
        
        // mengecek apakah file icon ada atau tidak
        if(file.exists()){
            return new ImageIcon(file.toString());
        }else{
            JOptionPane.showMessageDialog(null, "Tidak dapat menemukan file '" + icon + "'", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public static File chooseImage(){
        return new File(
                new FileManager().chooseFile(System.getProperty("user.home")+"\\Desktop\\", (1023*1024)*3, ".IMAGES", "jpg", "jpeg", "png", "gif")
        );
        
    }
    
    public static File resizeImage(File imgFile, int width, int height, String newName){
        
        BufferedImage fileOri, resize;
        Graphics2D g2d;
        File newImg;
        String cacheDir, filename, format;
        boolean result;
        
        try{
            // mengecek file gambar yang diinputkan exist atau tidak
            if(imgFile.exists()){
                // mendapatkan nama dari file gambar
                filename = new FileManager().getNamaFile(imgFile.toString());
                // mendapatkan format dari file gambar
                format = new FileManager().getFormatFile(imgFile.toString());
                // mendapatkan folder cache untuk menyimpan hasil gambar yang sudah diresize
                cacheDir = new Storage().getCacheDir() + "pictures\\resized\\";
                
                // jika newName kosong maka filename dari gambar akan diatur secara default
                if(newName == null){
                    // membuat direktori dan filename dari gambar yang akan diresize berdasarkan nama default
                    newImg = new File(cacheDir + filename.substring(0, filename.lastIndexOf(".")) + "_" + width + "x" + height + format);
                }else{
                    // membuat direktori dan filename dari gambar yang akan diresize berdasarkan nama yang diinputkan user
                    newImg = new File(cacheDir + newName + format);
                }
                
                // jika gambar sebelumnya sudah diresize maka akan mengembalikan direktori dari gambar
                if(newImg.exists()){
                    return newImg;
                }else{
                    Log.addLog("Merubah ukuran gambar " + filename + " ke " + width + "x" + height);
                    
                    // mengatur ukuran gambar
                    fileOri = ImageIO.read(imgFile);
                    resize = new BufferedImage(width, height, fileOri.getType()); 

                    // meresize gambar
                    g2d = resize.createGraphics();
                    g2d.drawImage(fileOri, 0, 0, width, height, null);
                    g2d.dispose();                    
                }
                // menyimpan perubahan pada gambar setelah diresize
                result = ImageIO.write(resize, format.substring(1), newImg); 
                
                // akan megembalikan gambar dalam bentuk file jika gambar berhasil disimpan
                return result ? newImg : null;
            }
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return null;
    }
        
    public static File resizeImage(File imgFile, int width, int height){
        return Gambar.resizeImage(imgFile, width, height, null);
    }
    
    public static ImageIcon scaleImage(File fileImage, final int x, final int y){
        ImageIcon icon, scaleIcon;
        Image img, imgScale;
        if(fileImage.exists()){
            icon = new ImageIcon(fileImage.toString());
            img = icon.getImage();
            imgScale = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            scaleIcon = new ImageIcon(imgScale);
            return scaleIcon;
        }  
        return null;
    }
    
}
