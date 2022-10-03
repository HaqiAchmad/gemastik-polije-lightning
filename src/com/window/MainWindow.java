package com.window.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Gemastik Lightning
 */
public class EmptyWindow extends javax.swing.JFrame {
    
    public EmptyWindow() {
        initComponents();
        
        this.setTitle("Dashboard");
        this.btnDashboard.setForeground(new Color(0,0,0));
        this.setExtendedState(this.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        
        JLabel[] btns = {
            this.btnKaryawan, this.btnSupplier, this.btnPembeli, this.btnBarang,
            this.btnTrJual, this.btnTrBeli, this.btnLpJual, this.btnLpBeli, this.btnLogout
        };
        
        for(JLabel btn : btns){
            btn.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setOpaque(true);
                    btn.setForeground(new Color(0,0,0));
                    btn.setBackground(new Color(96,167,231));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setOpaque(false);
                    btn.setForeground(new Color(255,255,255));
                    btn.setBackground(new Color(0,0,0,0));                    
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        sideMenu = new keeptoo.KGradientPanel();
        lblNamaApp = new javax.swing.JLabel();
        lineSideMenu = new javax.swing.JSeparator();
        lblNamaUser = new javax.swing.JLabel();
        lblSideMenu = new javax.swing.JLabel();
        lblSideMenuFitur = new javax.swing.JLabel();
        lblSideMenuInfo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JLabel();
        btnKaryawan = new javax.swing.JLabel();
        btnSupplier = new javax.swing.JLabel();
        btnPembeli = new javax.swing.JLabel();
        btnBarang = new javax.swing.JLabel();
        btnTrJual = new javax.swing.JLabel();
        btnTrBeli = new javax.swing.JLabel();
        btnLpJual = new javax.swing.JLabel();
        btnLpBeli = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        sideMenu.setkEndColor(new java.awt.Color(28, 181, 224));
        sideMenu.setkGradientFocus(-20);
        sideMenu.setkStartColor(new java.awt.Color(0, 0, 70));

        lblNamaApp.setFont(new java.awt.Font("Swis721 LtEx BT", 1, 24)); // NOI18N
        lblNamaApp.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaApp.setText("Simple Buy");

        lineSideMenu.setBackground(new java.awt.Color(255, 255, 255));
        lineSideMenu.setForeground(new java.awt.Color(255, 255, 255));

        lblNamaUser.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-akun.png"))); // NOI18N
        lblNamaUser.setText("Achmad Baihaqi");

        lblSideMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSideMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-top.png"))); // NOI18N
        lblSideMenu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblSideMenuFitur.setFont(new java.awt.Font("Dialog", 1, 19)); // NOI18N
        lblSideMenuFitur.setForeground(new java.awt.Color(255, 255, 255));
        lblSideMenuFitur.setText("Menu");

        lblSideMenuInfo.setFont(new java.awt.Font("Dialog", 1, 19)); // NOI18N
        lblSideMenuInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblSideMenuInfo.setText("Informasi");

        btnDashboard.setBackground(new java.awt.Color(166, 203, 227));
        btnDashboard.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(0, 0, 0));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-home-dark.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setIconTextGap(7);
        btnDashboard.setOpaque(true);

        btnKaryawan.setBackground(new java.awt.Color(96, 167, 231));
        btnKaryawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        btnKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-karyawan.png"))); // NOI18N
        btnKaryawan.setText("Data Karyawan");
        btnKaryawan.setIconTextGap(7);

        btnSupplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-supplier.png"))); // NOI18N
        btnSupplier.setText("Data Supplier");
        btnSupplier.setIconTextGap(7);

        btnPembeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPembeli.setForeground(new java.awt.Color(255, 255, 255));
        btnPembeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-pembeli.png"))); // NOI18N
        btnPembeli.setText("Data Pembeli");
        btnPembeli.setIconTextGap(7);

        btnBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBarang.setForeground(new java.awt.Color(255, 255, 255));
        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-barang.png"))); // NOI18N
        btnBarang.setText("Data Barang");
        btnBarang.setIconTextGap(7);

        btnTrJual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrJual.setForeground(new java.awt.Color(255, 255, 255));
        btnTrJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-trjual.png"))); // NOI18N
        btnTrJual.setText("Transaksi Jual");
        btnTrJual.setIconTextGap(7);

        btnTrBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrBeli.setForeground(new java.awt.Color(255, 255, 255));
        btnTrBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-trbeli.png"))); // NOI18N
        btnTrBeli.setText("Transaksi Beli");
        btnTrBeli.setIconTextGap(7);

        btnLpJual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLpJual.setForeground(new java.awt.Color(255, 255, 255));
        btnLpJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-lpjual.png"))); // NOI18N
        btnLpJual.setText("Laporan Jual");
        btnLpJual.setIconTextGap(7);

        btnLpBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLpBeli.setForeground(new java.awt.Color(255, 255, 255));
        btnLpBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-lpbeli.png"))); // NOI18N
        btnLpBeli.setText("Laporan Beli");
        btnLpBeli.setIconTextGap(7);

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setIconTextGap(7);

        javax.swing.GroupLayout sideMenuLayout = new javax.swing.GroupLayout(sideMenu);
        sideMenu.setLayout(sideMenuLayout);
        sideMenuLayout.setHorizontalGroup(
            sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideMenuLayout.createSequentialGroup()
                .addGroup(sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sideMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(sideMenuLayout.createSequentialGroup()
                                .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 19, Short.MAX_VALUE))))
                    .addGroup(sideMenuLayout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSideMenuFitur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSideMenuInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(lineSideMenu))
                        .addGap(10, 10, 10)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKaryawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPembeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLpJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLpBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        sideMenuLayout.setVerticalGroup(
            sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamaApp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lineSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSideMenuInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSideMenuFitur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrJual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLpJual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLpBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(sideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 961, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
            java.util.logging.Logger.getLogger(EmptyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmptyWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBarang;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnKaryawan;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnLpBeli;
    private javax.swing.JLabel btnLpJual;
    private javax.swing.JLabel btnPembeli;
    private javax.swing.JLabel btnSupplier;
    private javax.swing.JLabel btnTrBeli;
    private javax.swing.JLabel btnTrJual;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNamaApp;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblSideMenu;
    private javax.swing.JLabel lblSideMenuFitur;
    private javax.swing.JLabel lblSideMenuInfo;
    private javax.swing.JSeparator lineSideMenu;
    private keeptoo.KGradientPanel sideMenu;
    // End of variables declaration//GEN-END:variables
}
