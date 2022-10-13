package com.window.frames;

import com.data.app.Application;
import com.media.Audio;
import com.media.Gambar;
import java.awt.Cursor;

/**
 *
 * @author Achmad Baihaqi
 * @since 19 July 2021
 */
public class WelcomeApp extends javax.swing.JFrame {

    public WelcomeApp() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // menampilkan data-data aplikasi
        this.lblNamaAplikasi.setText(Application.getNama());
        this.lblVersi.setText("Versi " + Application.getVersi());
        this.lblCopyright.setText(Application.getRightReserved());
        this.lblIconSekolah.setIcon(Gambar.scaleImage(new java.io.File("src\\resources\\image\\icons\\app-logo.png"), 180, 180));
        
        // mengatur iu button
        this.btnLanjut.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblSelamat = new javax.swing.JLabel();
        btnLanjut = new javax.swing.JButton();
        line = new javax.swing.JSeparator();
        lblNamaAplikasi = new javax.swing.JLabel();
        lblIconSekolah = new javax.swing.JLabel();
        lblAppDes = new javax.swing.JLabel();
        lblVersi = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlMain.setBackground(new java.awt.Color(31, 33, 39));
        pnlMain.setAlignmentX(2.0F);
        pnlMain.setAlignmentY(2.0F);

        lblSelamat.setFont(new java.awt.Font("Dialog", 1, 29)); // NOI18N
        lblSelamat.setForeground(new java.awt.Color(33, 120, 241));
        lblSelamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelamat.setText("Selamat Datang Di Aplikasi");

        btnLanjut.setBackground(new java.awt.Color(34, 119, 237));
        btnLanjut.setForeground(new java.awt.Color(255, 255, 255));
        btnLanjut.setText("Lanjut");
        btnLanjut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLanjutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLanjutMouseExited(evt);
            }
        });
        btnLanjut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanjutActionPerformed(evt);
            }
        });

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setForeground(new java.awt.Color(255, 255, 255));
        line.setAlignmentY(2.0F);

        lblNamaAplikasi.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblNamaAplikasi.setForeground(new java.awt.Color(10, 223, 121));
        lblNamaAplikasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaAplikasi.setText("Simple Buy");

        lblIconSekolah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconSekolah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-app-splashwindow.png"))); // NOI18N

        lblAppDes.setFont(new java.awt.Font("Dialog", 1, 27)); // NOI18N
        lblAppDes.setForeground(new java.awt.Color(226, 39, 39));
        lblAppDes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppDes.setText("Aplikasi Manajemen Bisnis");

        lblVersi.setForeground(new java.awt.Color(222, 226, 253));
        lblVersi.setText("Versi 1.0.0");

        lblCopyright.setForeground(new java.awt.Color(222, 226, 253));
        lblCopyright.setText("Copyright © 2021. Gemastik. All Rights Reserved.");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSelamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAppDes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconSekolah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaAplikasi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                            .addComponent(lblVersi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(btnLanjut, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(line, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(20, 20, 20))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelamat, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamaAplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAppDes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLanjut, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblVersi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCopyright)))
                .addGap(12, 12, 12))
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

    private void btnLanjutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanjutActionPerformed
        Audio.play(Audio.SOUND_INFO);
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // membuka window login 
        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                new LoginWindow().setVisible(true);
            }
        });
        // menutup window ini
        this.dispose();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnLanjutActionPerformed

    private void btnLanjutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanjutMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }//GEN-LAST:event_btnLanjutMouseEntered

    private void btnLanjutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanjutMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_btnLanjutMouseExited

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLanjut;
    private javax.swing.JLabel lblAppDes;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblIconSekolah;
    private javax.swing.JLabel lblNamaAplikasi;
    private javax.swing.JLabel lblSelamat;
    private javax.swing.JLabel lblVersi;
    private javax.swing.JSeparator line;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
