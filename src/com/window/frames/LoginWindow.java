package com.window.frames;

import com.data.app.Application;
import com.data.app.Log;
import com.error.AuthenticationException;
import com.manage.Message;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import com.window.MainWindow;
import com.window.test.Dashboard;

import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Digunakan untuk login bagi admin, petugas dan siswa.
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-22
 */
public class LoginWindow extends javax.swing.JFrame {

    private final Users user = new Users();
    private String idUser, password;
    private int x, y;
    
    public LoginWindow() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        this.lblKembali.setVisible(false);
        this.btnLogin.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        lblLogoSmk = new javax.swing.JLabel();
        lblSekolah = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblEye = new javax.swing.JLabel();
        inpPassword = new javax.swing.JPasswordField();
        inpUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblBgImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(21, 20, 20));
        pnlMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMainMouseDragged(evt);
            }
        });
        pnlMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMainMousePressed(evt);
            }
        });
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLeft.setBackground(new java.awt.Color(33, 33, 37));

        lblLogoSmk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoSmk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-app-splashwindow.png"))); // NOI18N
        lblLogoSmk.setToolTipText("");
        lblLogoSmk.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblSekolah.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        lblSekolah.setForeground(new java.awt.Color(255, 255, 255));
        lblSekolah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSekolah.setText("Gemastik Lightning");
        lblSekolah.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogoSmk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSekolah, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addComponent(lblLogoSmk, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlMain.add(pnlLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 380));

        btnLogin.setBackground(new java.awt.Color(250, 56, 56));
        btnLogin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        pnlMain.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 105, 35));

        lblEye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-eye-close.png"))); // NOI18N
        lblEye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEyeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEyeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEyeMouseExited(evt);
            }
        });
        pnlMain.add(lblEye, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 36, 39));

        inpPassword.setBackground(new java.awt.Color(36, 37, 39));
        inpPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpPassword.setForeground(new java.awt.Color(255, 255, 255));
        inpPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlMain.add(inpPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 272, 39));

        inpUsername.setBackground(new java.awt.Color(36, 37, 39));
        inpUsername.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpUsername.setForeground(new java.awt.Color(255, 255, 255));
        inpUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        inpUsername.setMaximumSize(new java.awt.Dimension(2147483647, 35));
        inpUsername.setMinimumSize(new java.awt.Dimension(6, 35));
        inpUsername.setPreferredSize(new java.awt.Dimension(6, 35));
        pnlMain.add(inpUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 272, 39));

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Password");
        pnlMain.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 374, 22));

        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("ID Admin / Karyawan");
        pnlMain.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 384, 23));

        lblKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-kembali.png"))); // NOI18N
        lblKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKembaliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKembaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKembaliMouseExited(evt);
            }
        });
        pnlMain.add(lblKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 24, 28));

        lblMinimaze.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblMinimaze.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimaze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-minimaze.png"))); // NOI18N
        lblMinimaze.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseExited(evt);
            }
        });
        pnlMain.add(lblMinimaze, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 28));

        lblTop.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        lblTop.setForeground(new java.awt.Color(44, 119, 238));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Login User");
        lblTop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlMain.add(lblTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 384, -1));

        lblClose.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-close.png"))); // NOI18N
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
        });
        pnlMain.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 31, 28));

        lblBgImage.setForeground(new java.awt.Color(255, 255, 255));
        lblBgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/bgimages/bg-login.jpg"))); // NOI18N
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 0, 386, 380));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
            yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Log.addLog("Menutup Window " + getClass().getName());
        user.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Log.addLog("Menutup Window " + getClass().getName());
        user.closeConnection();
    }//GEN-LAST:event_formWindowClosing

    private void lblEyeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMouseExited
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblEye.setIcon(Gambar.getIcon("ic-login-eye-close.png"));
        this.inpPassword.setEchoChar('•');
    }//GEN-LAST:event_lblEyeMouseExited

    private void lblEyeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMouseEntered
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblEye.setIcon(Gambar.getIcon("ic-login-eye-open.png"));
        this.inpPassword.setEchoChar((char)0);
    }//GEN-LAST:event_lblEyeMouseEntered

    private void lblEyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMouseClicked

    }//GEN-LAST:event_lblEyeMouseClicked

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited
        this.lblMinimaze.setIcon(Gambar.getIcon("ic-login-minimaze.png"));
    }//GEN-LAST:event_lblMinimazeMouseExited

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered
        this.lblMinimaze.setIcon(Gambar.getIcon("ic-login-minimaze-entered.png"));
    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon("ic-login-close.png"));
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon("ic-login-close-entered.png"));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        Application.closeApplication();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            // mendapatkan input
            idUser = this.inpUsername.getText();
            password = this.inpPassword.getText();
            // melakukan login
            boolean login = user.login(idUser, password);
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            // jika login berhasil
            if(login){
                // jika user login dengan level akun admin atau petugas
                Audio.play(Audio.SOUND_INFO);
                JOptionPane.showMessageDialog(this, "Login Berhasil!\n\nSelamat datang " + user.getData("karyawan", "nama_karyawan", "WHERE id_karyawan = '" + idUser + "'"));
                
                java.awt.EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        new MainWindow().setVisible(true);
                    }
                });
                
//                if(user.isAdmin() || user.isPetugas()){
//                    // membuka window DashboardPetugas
//                    java.awt.EventQueue.invokeLater(new Runnable(){
//                        
//                        @Override
//                        public void run(){
//                            new com.window.petugas.DashboardPetugas().setVisible(true);
//                        }
//                    });
//                    
//                    // menutup window LoginWindow
//                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//                    dispose();
//                }
//                // jika user login dengan level akun siswa
//                else if(user.isSiswa()){
//                    // membuka window DashboardSiswa
//                    java.awt.EventQueue.invokeLater(new Runnable(){
//                        
//                        @Override
//                        public void run(){
//                            new com.window.siswa.DashboardSiswa().setVisible(true);
//                        }
//                    });
//                    
//                    // menutup window LoginWindow
//                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//                    dispose();
//                }else{
//                    Message.showWarning(this, "Level akun Anda tidak valid!", true);
//                }
            }else{
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                // mereset textfield jika login gagal
                this.inpUsername.setText("");
                this.inpPassword.setText("");
            }
        } catch (IOException | AuthenticationException | SQLException ex) {
            // mereset textfield jika terjadi error
            this.inpUsername.setText("");
            this.inpPassword.setText("");
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            // menampilkan pesan error
            Message.showException(this, ex, true);
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        this.btnLogin.setBackground(new Color(250,56,56));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        this.btnLogin.setBackground(new Color(23,24,26));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon("ic-login-kembali.png"));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon("ic-login-kembali-entered.png"));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked

    }//GEN-LAST:event_lblKembaliMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Log.addLog("Membuka Window " + getClass().getName());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JPasswordField inpPassword;
    private javax.swing.JTextField inpUsername;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblEye;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblLogoSmk;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
