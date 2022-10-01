package com.window.all;

import com.data.app.Application;
import com.data.db.Database;
import com.media.Gambar;
import com.users.Users;
import javax.swing.JOptionPane;


/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-08
 */
public class SplashWindow extends javax.swing.JFrame {

    private final Database db = new Database();
    
    private Users user;
    
    public SplashWindow() {
        initComponents();
        
        this.lblTop.setText(Application.getNama());
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // menjalankan loding aplikasi
        loading();
    }
    
    private void loading(){
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                int val = 0, delay = 50;
                try{
                    // CHANGE THIS ( SKIP SPLASH WINDOW )
//                    val = 100;
                    // melakukan loading dari 0% sampai 100%
                    while(val <= 100){
                        if(val >= 0 && val < 9){
                            delay = 50;
                            lblMessage.setText("  Menyiapkan Aplikasi...");
                        }else if(val >= 9 && val < 15){
                            delay = 40;
                            lblMessage.setText("  Memulai Aplikasi...");
                        }else if(val >= 15 && val < 25){
                            delay = 20;
                            lblMessage.setText("  Menyiapkan Penyimpanan...");
                        }else if(val >= 25 && val < 35){
                            delay = 30;
                            lblMessage.setText("  Menyiapkan Database...");
                        }else if(val >= 35 && val < 50){
                            lblMessage.setText("  Mengecek Database...");
                            Thread.sleep(300);
                            val = 51;
                            db.startConnection();
                            db.closeConnection();
                        }else if(val >= 50 && val < 60){
                            delay = 60;
                            lblMessage.setText("  Menghubungkan ke Database Server...");
                        }else if(val >= 60 && val < 70){
                            delay = 50;
                            lblMessage.setText("  Mendapatkan Data Aplikasi...");
                        }else if(val >= 70 && val < 75){
                            delay = 30;
                            lblMessage.setText("  Mendapatkan Data Current Login...");
                        }else if(val >= 75 && val < 80){
                            delay = 25;
                            lblMessage.setText("  Memuat Window...");
                        }else if(val >= 80 && val < 90){
                            delay = 25;
                            lblMessage.setText("  Menyipakan Window...");
                        }
                        else{
                            lblMessage.setText("  Membuka Aplikasi...");
                        }
                        
                        val++;
                        proLoad.setValue(val);
                        lblLoadVal.setText("" + val + " %  ");
                        Thread.sleep(delay);
                        System.out.println("Loading Values : "+ val+"/100%");
                    }
                    
                    // jika proses loading sudah selesai
                    if(val >= 100){
                        user = new Users();
                        // mengecek user sudah login atau belum
                        if(user.isLogin()){
                            JOptionPane.showMessageDialog(null, "Login berhasil");
                            // jika user login dengan level admin atau petugas
//                            if(user.isAdmin() || user.isPetugas()){
//                                // membuka window DashboardPetugas
//                                java.awt.EventQueue.invokeLater(new Runnable(){
//                                    @Override
//                                    public void run(){
//                                        new com.window.petugas.DashboardPetugas().setVisible(true);
//                                    }
//                                });
//                            }
//                            // jika user login login dengan level siswa
//                            else if(user.isSiswa()){
//                                // membuka window DashboardSiswa
//                                java.awt.EventQueue.invokeLater(new Runnable(){
//                                    @Override
//                                    public void run(){
//                                        new com.window.siswa.DashboardSiswa().setVisible(true);
//                                    }
//                                });
//                            }
                        }else{
                            // membuka window login jika user belum melakukan login
                            java.awt.EventQueue.invokeLater(new Runnable(){
                                
                                @Override
                                public void run(){
                                    new LoginWindow().setVisible(true);
                                }
                            });
                        }
                    }
                    // menutup window
                    user.closeConnection();
                    dispose();
                }catch(InterruptedException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        proLoad = new javax.swing.JProgressBar();
        lblTop = new javax.swing.JLabel();
        lblLogoSmk = new javax.swing.JLabel();
        lblLoadVal = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        lblSekolah = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlMain.setBackground(new java.awt.Color(31, 33, 39));

        proLoad.setBackground(new java.awt.Color(255, 255, 255));
        proLoad.setForeground(new java.awt.Color(0, 125, 255));
        proLoad.setValue(85);

        lblTop.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTop.setForeground(new java.awt.Color(10, 223, 121));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("SPP Payment");

        lblLogoSmk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoSmk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-splashwindow-logosmkn1kts.png"))); // NOI18N

        lblLoadVal.setForeground(new java.awt.Color(255, 255, 255));
        lblLoadVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLoadVal.setText("85%  ");

        lblMessage.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setText("  Membuka Aplikasi...");

        lblSekolah.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSekolah.setForeground(new java.awt.Color(226, 39, 39));
        lblSekolah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSekolah.setText("SMK Negeri 1 Kertosono");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(proLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblLogoSmk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(lblLoadVal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblSekolah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogoSmk, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoadVal)
                    .addComponent(lblMessage))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplashWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLoadVal;
    private javax.swing.JLabel lblLogoSmk;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JProgressBar proLoad;
    // End of variables declaration//GEN-END:variables
}
