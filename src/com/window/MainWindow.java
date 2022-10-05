package com.window;

import com.window.panels.DataBarang;
import com.window.panels.DataKaryawan;
import com.window.panels.DataPembeli;
import com.window.panels.DataSupplier;
import com.window.panels.LaporanBeli;
import com.window.panels.LaporanJual;
import com.window.panels.TransaksiBeli;
import com.window.panels.TransaksiJual;
import com.window.test.Dashboard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gemastik Lightning
 */
public class MainWindow extends javax.swing.JFrame {
    
    private final Dashboard dashboard = new Dashboard();
    
    public MainWindow() {
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
        
        this.pnlMenu.removeAll();
        this.pnlMenu.repaint();
        this.pnlMenu.revalidate();
        
        this.pnlMenu.add(new com.window.panels.Dashboard());
        this.pnlMenu.repaint();
        this.pnlMenu.revalidate();
    }
    
    private void setMenu(Object menu){
        // menghapus panel lama
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(dashboard);
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMain = new javax.swing.JPanel();
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
        lblMenuName = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

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
        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashboardMouseClicked(evt);
            }
        });

        btnKaryawan.setBackground(new java.awt.Color(96, 167, 231));
        btnKaryawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        btnKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-karyawan.png"))); // NOI18N
        btnKaryawan.setText("Data Karyawan");
        btnKaryawan.setIconTextGap(7);
        btnKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKaryawanMouseClicked(evt);
            }
        });

        btnSupplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-supplier.png"))); // NOI18N
        btnSupplier.setText("Data Supplier");
        btnSupplier.setIconTextGap(7);
        btnSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupplierMouseClicked(evt);
            }
        });

        btnPembeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPembeli.setForeground(new java.awt.Color(255, 255, 255));
        btnPembeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-pembeli.png"))); // NOI18N
        btnPembeli.setText("Data Pembeli");
        btnPembeli.setIconTextGap(7);
        btnPembeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPembeliMouseClicked(evt);
            }
        });

        btnBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBarang.setForeground(new java.awt.Color(255, 255, 255));
        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-barang.png"))); // NOI18N
        btnBarang.setText("Data Barang");
        btnBarang.setIconTextGap(7);
        btnBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBarangMouseClicked(evt);
            }
        });

        btnTrJual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrJual.setForeground(new java.awt.Color(255, 255, 255));
        btnTrJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-trjual.png"))); // NOI18N
        btnTrJual.setText("Transaksi Jual");
        btnTrJual.setIconTextGap(7);
        btnTrJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrJualMouseClicked(evt);
            }
        });

        btnTrBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrBeli.setForeground(new java.awt.Color(255, 255, 255));
        btnTrBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-trbeli.png"))); // NOI18N
        btnTrBeli.setText("Transaksi Beli");
        btnTrBeli.setIconTextGap(7);
        btnTrBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrBeliMouseClicked(evt);
            }
        });

        btnLpJual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLpJual.setForeground(new java.awt.Color(255, 255, 255));
        btnLpJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-lpjual.png"))); // NOI18N
        btnLpJual.setText("Laporan Jual");
        btnLpJual.setIconTextGap(7);
        btnLpJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLpJualMouseClicked(evt);
            }
        });

        btnLpBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLpBeli.setForeground(new java.awt.Color(255, 255, 255));
        btnLpBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-lpbeli.png"))); // NOI18N
        btnLpBeli.setText("Laporan Beli");
        btnLpBeli.setIconTextGap(7);
        btnLpBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLpBeliMouseClicked(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setIconTextGap(7);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });

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

        lblMenuName.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMenuName.setForeground(new java.awt.Color(0, 0, 0));
        lblMenuName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-logo.png"))); // NOI18N
        lblMenuName.setText("Dashboard");
        lblMenuName.setIconTextGap(10);

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(sideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 600, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblMenuName)
                .addGap(18, 18, 18)
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnlMain);

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void btnDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashboardMouseClicked
        this.lblMenuName.setText("Dashboard");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new com.window.panels.Dashboard());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnDashboardMouseClicked

    private void btnKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKaryawanMouseClicked
        this.lblMenuName.setText("Data Karyawan");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new DataKaryawan());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnKaryawanMouseClicked

    private void btnSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupplierMouseClicked
        this.lblMenuName.setText("Data Supplier");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new DataSupplier());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnSupplierMouseClicked

    private void btnPembeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPembeliMouseClicked
        this.lblMenuName.setText("Data Pembeli");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new DataPembeli());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnPembeliMouseClicked

    private void btnBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBarangMouseClicked
        this.lblMenuName.setText("Data Barang");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new DataBarang());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnBarangMouseClicked

    private void btnTrJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrJualMouseClicked
        this.lblMenuName.setText("Transaksi Jual");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new TransaksiJual());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnTrJualMouseClicked

    private void btnTrBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrBeliMouseClicked
        this.lblMenuName.setText("Transaksi Beli");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new TransaksiBeli());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnTrBeliMouseClicked

    private void btnLpJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLpJualMouseClicked
        this.lblMenuName.setText("Laporan Jual");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new LaporanJual());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnLpJualMouseClicked

    private void btnLpBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLpBeliMouseClicked
        this.lblMenuName.setText("Laporan Beli");
        
        pnlMenu.removeAll();
        pnlMenu.repaint();
        pnlMenu.revalidate();
        
        // menambahkan panel baru
        pnlMenu.add(new LaporanBeli());
        pnlMenu.repaint();
        pnlMenu.revalidate();
    }//GEN-LAST:event_btnLpBeliMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        
    }//GEN-LAST:event_btnLogoutMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMenuName;
    private javax.swing.JLabel lblNamaApp;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblSideMenu;
    private javax.swing.JLabel lblSideMenuFitur;
    private javax.swing.JLabel lblSideMenuInfo;
    private javax.swing.JSeparator lineSideMenu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMenu;
    private keeptoo.KGradientPanel sideMenu;
    // End of variables declaration//GEN-END:variables
}

class RoundedPanel extends JPanel{
    
    private Color backgroundColor;
    private int cornerRadius = 15;
    
    public RoundedPanel(LayoutManager layout, int radius){
        super(layout);
        cornerRadius = radius;
    }
    
    public RoundedPanel(LayoutManager layout, int radius, Color bgColor){
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }
    
    public RoundedPanel(int radius){
        super();
        cornerRadius = radius;
    }
    
    public RoundedPanel(int radius, Color bgColor){
        super();
        cornerRadius = radius;
        this.backgroundColor = bgColor;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // mengambar rounded panel with borders
        if(backgroundColor != null){
            graphics.setColor(getBackground());
        }else{
            graphics.setColor(getBackground());
        }
        
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }
    
}
