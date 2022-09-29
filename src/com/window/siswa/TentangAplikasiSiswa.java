package com.window.siswa;

import com.data.app.Application;
import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.Storage;
import com.manage.Internet;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import com.window.all.ConfirmHapusData;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-09
 */
public class TentangAplikasiSiswa extends javax.swing.JFrame {
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Storage strg = new Storage();
    private final Text txt = new Text();
    
    private final String name, levelKelas, namaApp, versiApp, ukuranApp, dirilis, diupdate, bahasaProgram, dbPlatform, tahunAjaran, pengguna, 
                        developer, company, dataApp = "-1 MB", cacheApp, total = "-1 MB", ruangTersedia = "-1 MB", penggunaanRam = "-1 MB";
    private int x, y;
    
    public TentangAplikasiSiswa() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // mendapatkan data-data siswa
        name = new Text().toCapitalize(siswa.getNama());
        levelKelas = kls.getLevelKelas(siswa.getIdKelas());
        
        // menampilkan data-data siswa
        this.lblNamaUser.setText("<html><p>"+name+"</p></html>");
        this.lblSiswa.setText("<html><p>Siswa Kelas "+levelKelas+"</p></html>");

        // mendapatkan data-data aplikasi
        this.namaApp = Application.getNama();
        this.versiApp = Application.getVersi();
        this.ukuranApp = Application.getUkuran();
        this.dirilis = Application.getTanggalRilis();
        this.diupdate = Application.getTanggalUpdate();
        this.bahasaProgram = Application.getBahasaPemrograman();
        this.dbPlatform = Application.getDatabase();
        this.tahunAjaran = Application.getTahunAjaran();
        this.pengguna = txt.addDelim(siswa.getTotalUser());
        this.developer = Application.getAuthor();
        this.company = Application.getCompany();
        this.cacheApp = strg.getCacheSize();
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolahBottom.setIcon(Gambar.getBottomIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblNamaSekolahBottom.setText(this.company);
        this.lblNamaAplikasiBottom.setText(this.namaApp);
        this.lblVersion.setText("Versi " + this.versiApp);
        this.lblCopyright.setText(Application.getRightReserved());
        this.lblPhotoProfile.setIcon(siswa.getPhotoProfile());
        this.valNamaApp.setText("<html><p>:&nbsp;"+this.namaApp+"</p></html>");
        this.valVersiApp.setText("<html><p>:&nbsp;"+this.versiApp+"</p></html>");
        this.valUkuranApp.setText("<html><p>:&nbsp;"+this.ukuranApp+"</p></html>");
        this.valDirilis.setText("<html><p>:&nbsp;"+this.dirilis+"</p></html>");
        this.valDiupdate.setText("<html><p>:&nbsp;"+this.diupdate+"</p></html>");
        this.valBahasa.setText("<html><p>:&nbsp;"+this.bahasaProgram+"</p></html>");
        this.valDatabase.setText("<html><p>:&nbsp;"+this.dbPlatform+"</p></html>");
        this.valTahunAjaran.setText("<html><p>:&nbsp;"+this.tahunAjaran+"</p></html>");
        this.valPengguna.setText("<html><p>:&nbsp;"+this.pengguna+" Pengguna</p></html>");
        this.valDeveloper.setText("<html><p>:&nbsp;"+this.developer+"</p></html>");
        this.valCompany.setText("<html><p>:&nbsp;"+this.company+"</p></html>");
        this.valCacheAplikasi.setText("<html><p>:&nbsp;"+this.cacheApp+"</p></html>");
        
        // mengatur ui dari button
        this.btnDashboard.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoAkun.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnPembayaranSpp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnClose.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinimaze.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapusData.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapusCache.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        this.btnTentangApp.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnDashboard, this.btnInfoAkun, this.btnPembayaranSpp,
        };
        
        for(JButton btn : btns){
            btn.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new java.awt.Color(13,88,149));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new java.awt.Color(22,24,24));
                }
            });
        }
        
        JLabel[] lblInfo = new JLabel[]{
            this.valNamaApp, this.valVersiApp, this.valUkuranApp, this.valDirilis, this.valDiupdate, this.valBahasa,
            this.valDatabase, this.valTahunAjaran, this.valPengguna, this.valDeveloper, this.valCompany
        };
        
        for(JLabel lbl : lblInfo){
            lbl.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setForeground(new Color(15,98,230));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0,0,0));
                    
                }
            });
        }
       
        JLabel[] lblPenyimpanan = new JLabel[]{
          this.valDataAplikasi, this.valCacheAplikasi, this.valTotalPenyimpanan, this.valRuangTersedia, this.valPenggunaanRam  
        };
        
        for(JLabel lbl : lblPenyimpanan){
            lbl.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setForeground(new Color(5,170,57));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0, 0, 0));
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        pnlAccount = new javax.swing.JPanel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblNamaUser = new javax.swing.JLabel();
        lblSiswa = new javax.swing.JLabel();
        btnInfoAkun = new javax.swing.JButton();
        btnPembayaranSpp = new javax.swing.JButton();
        pnlLeftBottom = new javax.swing.JPanel();
        btnTentangApp = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimaze = new javax.swing.JButton();
        lblSekolah = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        pnlPenyimpanan = new javax.swing.JPanel();
        pnlTitlePenyimpanan = new javax.swing.JPanel();
        lblTitlePenyimpanan = new javax.swing.JLabel();
        lblDataApplikasi = new javax.swing.JLabel();
        valDataAplikasi = new javax.swing.JLabel();
        lblCacheAplikasi = new javax.swing.JLabel();
        valCacheAplikasi = new javax.swing.JLabel();
        lblTotalPenyimpanan = new javax.swing.JLabel();
        valTotalPenyimpanan = new javax.swing.JLabel();
        lblRuangTesedia = new javax.swing.JLabel();
        valRuangTersedia = new javax.swing.JLabel();
        lblPenggunaanRam = new javax.swing.JLabel();
        valPenggunaanRam = new javax.swing.JLabel();
        linePenyimpanan = new javax.swing.JSeparator();
        btnHapusData = new javax.swing.JButton();
        btnHapusCache = new javax.swing.JButton();
        pnlInfoKontak = new javax.swing.JPanel();
        pnlTitleInfo = new javax.swing.JPanel();
        lblTitleKontak = new javax.swing.JLabel();
        lblIconYT = new javax.swing.JLabel();
        lblYT = new javax.swing.JLabel();
        lblIconIG = new javax.swing.JLabel();
        lblIG = new javax.swing.JLabel();
        lblIconTwitter = new javax.swing.JLabel();
        lblTwitter = new javax.swing.JLabel();
        lblIconWA = new javax.swing.JLabel();
        lblWA = new javax.swing.JLabel();
        pnlInfoApp = new javax.swing.JPanel();
        pnlTitleInfoApp = new javax.swing.JPanel();
        lblTitleInfoApp = new javax.swing.JLabel();
        lblNamaApp = new javax.swing.JLabel();
        valNamaApp = new javax.swing.JLabel();
        lblVersiApp = new javax.swing.JLabel();
        valVersiApp = new javax.swing.JLabel();
        valUkuranApp = new javax.swing.JLabel();
        lblUkuranApp = new javax.swing.JLabel();
        valDirilis = new javax.swing.JLabel();
        lblDirilis = new javax.swing.JLabel();
        lblPengguna = new javax.swing.JLabel();
        valPengguna = new javax.swing.JLabel();
        lblDeveloper = new javax.swing.JLabel();
        valDeveloper = new javax.swing.JLabel();
        lblDiupdate = new javax.swing.JLabel();
        valDiupdate = new javax.swing.JLabel();
        lblBahasa = new javax.swing.JLabel();
        valBahasa = new javax.swing.JLabel();
        valDatabase = new javax.swing.JLabel();
        lblDatabase = new javax.swing.JLabel();
        lblTahunAjaran = new javax.swing.JLabel();
        valTahunAjaran = new javax.swing.JLabel();
        lblCompany = new javax.swing.JLabel();
        valCompany = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        lblSekolahBottom = new javax.swing.JLabel();
        lblNamaAplikasiBottom = new javax.swing.JLabel();
        lblNamaSekolahBottom = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        lblBgImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
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

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-tentangapp-logo.png"))); // NOI18N
        lblDashboard.setText("Tentang Aplikasi");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        sidePanel.setBackground(new java.awt.Color(22, 24, 24));
        sidePanel.setMaximumSize(new java.awt.Dimension(218, 555));

        btnDashboard.setBackground(new java.awt.Color(22, 24, 24));
        btnDashboard.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-home.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setIconTextGap(6);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        pnlAccount.setBackground(new java.awt.Color(43, 61, 93));
        pnlAccount.setMaximumSize(new java.awt.Dimension(194, 82));

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-profile.png"))); // NOI18N
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPhotoProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPhotoProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPhotoProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPhotoProfileMouseExited(evt);
            }
        });

        lblNamaUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNamaUser.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaUser.setText("Achmad Baihaqi");
        lblNamaUser.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNamaUser.setInheritsPopupMenu(false);
        lblNamaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNamaUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNamaUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNamaUserMouseExited(evt);
            }
        });

        lblSiswa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblSiswa.setForeground(new java.awt.Color(255, 255, 255));
        lblSiswa.setText("Siswa Kelas XII");

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccount);
        pnlAccount.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNamaUser, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(lblSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhotoProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSiswa)
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnInfoAkun.setBackground(new java.awt.Color(22, 24, 24));
        btnInfoAkun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInfoAkun.setForeground(new java.awt.Color(255, 255, 255));
        btnInfoAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-akun.png"))); // NOI18N
        btnInfoAkun.setText("Informasi Akun");
        btnInfoAkun.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInfoAkun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInfoAkun.setIconTextGap(6);
        btnInfoAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoAkunActionPerformed(evt);
            }
        });

        btnPembayaranSpp.setBackground(new java.awt.Color(22, 24, 24));
        btnPembayaranSpp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPembayaranSpp.setForeground(new java.awt.Color(255, 255, 255));
        btnPembayaranSpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-pembayaran.png"))); // NOI18N
        btnPembayaranSpp.setText("Riwayat Pembayaran");
        btnPembayaranSpp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPembayaranSpp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPembayaranSpp.setIconTextGap(6);
        btnPembayaranSpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranSppActionPerformed(evt);
            }
        });

        pnlLeftBottom.setBackground(new java.awt.Color(22, 24, 24));

        javax.swing.GroupLayout pnlLeftBottomLayout = new javax.swing.GroupLayout(pnlLeftBottom);
        pnlLeftBottom.setLayout(pnlLeftBottomLayout);
        pnlLeftBottomLayout.setHorizontalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLeftBottomLayout.setVerticalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        btnTentangApp.setBackground(new java.awt.Color(22, 24, 24));
        btnTentangApp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTentangApp.setForeground(new java.awt.Color(255, 255, 255));
        btnTentangApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-tentangApp.png"))); // NOI18N
        btnTentangApp.setText("Tentang Aplikasi");
        btnTentangApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTentangApp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTentangApp.setIconTextGap(6);
        btnTentangApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTentangAppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlLeftBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTentangApp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPembayaranSpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(btnInfoAkun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnPembayaranSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTentangApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 650));

        pnlTop.setBackground(new java.awt.Color(11, 114, 238));

        pnlTitle.setBackground(new java.awt.Color(11, 114, 238));
        pnlTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClose.setBackground(new java.awt.Color(11, 114, 238));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-app-close.png"))); // NOI18N
        btnClose.setText(" ");
        btnClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        pnlTitle.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 1, 40, 48));

        btnMinimaze.setBackground(new java.awt.Color(11, 114, 238));
        btnMinimaze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-app-minimaze.png"))); // NOI18N
        btnMinimaze.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMinimaze.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnMinimaze.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimazeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimazeMouseExited(evt);
            }
        });
        btnMinimaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimazeActionPerformed(evt);
            }
        });
        pnlTitle.add(btnMinimaze, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 30, 48));

        lblSekolah.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSekolah.setForeground(new java.awt.Color(255, 255, 255));
        lblSekolah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/logo-smkn1kts-circle_35x35.png"))); // NOI18N
        lblSekolah.setText("SMK Negeri 1 Kertosono | Aplikasi Pembayaran SPP");
        lblSekolah.setIconTextGap(13);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(lblSekolah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMain.add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 990, 50));

        lineTop.setBackground(new java.awt.Color(0, 0, 0));
        lineTop.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 900, 10));

        pnlPenyimpanan.setBackground(new java.awt.Color(255, 255, 255));
        pnlPenyimpanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 2));

        pnlTitlePenyimpanan.setBackground(new java.awt.Color(5, 170, 57));
        pnlTitlePenyimpanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitlePenyimpanan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitlePenyimpanan.setForeground(new java.awt.Color(255, 255, 255));
        lblTitlePenyimpanan.setText("Penyimpanan Aplikasi");

        javax.swing.GroupLayout pnlTitlePenyimpananLayout = new javax.swing.GroupLayout(pnlTitlePenyimpanan);
        pnlTitlePenyimpanan.setLayout(pnlTitlePenyimpananLayout);
        pnlTitlePenyimpananLayout.setHorizontalGroup(
            pnlTitlePenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitlePenyimpananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitlePenyimpanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitlePenyimpananLayout.setVerticalGroup(
            pnlTitlePenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitlePenyimpanan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblDataApplikasi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblDataApplikasi.setForeground(new java.awt.Color(0, 0, 0));
        lblDataApplikasi.setText("Data Aplikasi");

        valDataAplikasi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valDataAplikasi.setForeground(new java.awt.Color(0, 0, 0));
        valDataAplikasi.setText(": 406.0 MB");
        valDataAplikasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valDataAplikasiMouseClicked(evt);
            }
        });

        lblCacheAplikasi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCacheAplikasi.setForeground(new java.awt.Color(0, 0, 0));
        lblCacheAplikasi.setText("Cache Aplikasi");

        valCacheAplikasi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valCacheAplikasi.setForeground(new java.awt.Color(0, 0, 0));
        valCacheAplikasi.setText(": 40.12. MB");
        valCacheAplikasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valCacheAplikasiMouseClicked(evt);
            }
        });

        lblTotalPenyimpanan.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTotalPenyimpanan.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPenyimpanan.setText("Total");

        valTotalPenyimpanan.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTotalPenyimpanan.setForeground(new java.awt.Color(0, 0, 0));
        valTotalPenyimpanan.setText(": 446.0 MB");
        valTotalPenyimpanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valTotalPenyimpananMouseClicked(evt);
            }
        });

        lblRuangTesedia.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblRuangTesedia.setForeground(new java.awt.Color(0, 0, 0));
        lblRuangTesedia.setText("Ruang Tersedia");

        valRuangTersedia.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valRuangTersedia.setForeground(new java.awt.Color(0, 0, 0));
        valRuangTersedia.setText(": 14.7 GB");
        valRuangTersedia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valRuangTersediaMouseClicked(evt);
            }
        });

        lblPenggunaanRam.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblPenggunaanRam.setForeground(new java.awt.Color(0, 0, 0));
        lblPenggunaanRam.setText("Penggunaan RAM");

        valPenggunaanRam.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valPenggunaanRam.setForeground(new java.awt.Color(0, 0, 0));
        valPenggunaanRam.setText(": 167.8 MB");
        valPenggunaanRam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valPenggunaanRamMouseClicked(evt);
            }
        });

        linePenyimpanan.setBackground(new java.awt.Color(5, 170, 57));
        linePenyimpanan.setForeground(new java.awt.Color(5, 170, 57));

        btnHapusData.setBackground(new java.awt.Color(235, 29, 29));
        btnHapusData.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-data.png"))); // NOI18N
        btnHapusData.setText("Hapus Data");
        btnHapusData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusDataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusDataMouseExited(evt);
            }
        });
        btnHapusData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDataActionPerformed(evt);
            }
        });

        btnHapusCache.setBackground(new java.awt.Color(27, 186, 47));
        btnHapusCache.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusCache.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-cache.png"))); // NOI18N
        btnHapusCache.setText("Hapus Cache");
        btnHapusCache.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusCacheMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusCacheMouseExited(evt);
            }
        });
        btnHapusCache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusCacheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPenyimpananLayout = new javax.swing.GroupLayout(pnlPenyimpanan);
        pnlPenyimpanan.setLayout(pnlPenyimpananLayout);
        pnlPenyimpananLayout.setHorizontalGroup(
            pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitlePenyimpanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPenyimpananLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(lblDataApplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valDataAplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(lblCacheAplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valCacheAplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(lblTotalPenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valTotalPenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(lblRuangTesedia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valRuangTersedia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(lblPenggunaanRam, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valPenggunaanRam, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
            .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                        .addComponent(btnHapusData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusCache))
                    .addComponent(linePenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPenyimpananLayout.setVerticalGroup(
            pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPenyimpananLayout.createSequentialGroup()
                .addComponent(pnlTitlePenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataApplikasi)
                    .addComponent(valDataAplikasi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCacheAplikasi)
                    .addComponent(valCacheAplikasi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalPenyimpanan)
                    .addComponent(valTotalPenyimpanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuangTesedia)
                    .addComponent(valRuangTersedia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPenggunaanRam)
                    .addComponent(valPenggunaanRam))
                .addGap(18, 18, 18)
                .addComponent(linePenyimpanan, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPenyimpananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapusData)
                    .addComponent(btnHapusCache))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pnlMain.add(pnlPenyimpanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 400, 270));

        pnlInfoKontak.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoKontak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(246, 46, 46), 2));

        pnlTitleInfo.setBackground(new java.awt.Color(246, 46, 46));
        pnlTitleInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleKontak.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitleKontak.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleKontak.setText("Kontak Developer");

        javax.swing.GroupLayout pnlTitleInfoLayout = new javax.swing.GroupLayout(pnlTitleInfo);
        pnlTitleInfo.setLayout(pnlTitleInfoLayout);
        pnlTitleInfoLayout.setHorizontalGroup(
            pnlTitleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleKontak, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );
        pnlTitleInfoLayout.setVerticalGroup(
            pnlTitleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitleKontak, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblIconYT.setForeground(new java.awt.Color(0, 0, 0));
        lblIconYT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconYT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-youtube.png"))); // NOI18N
        lblIconYT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconYTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconYTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconYTMouseExited(evt);
            }
        });

        lblYT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYT.setForeground(new java.awt.Color(0, 0, 0));
        lblYT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYT.setText("Youtube");

        lblIconIG.setForeground(new java.awt.Color(0, 0, 0));
        lblIconIG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconIG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-instagram.png"))); // NOI18N
        lblIconIG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconIGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconIGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconIGMouseExited(evt);
            }
        });

        lblIG.setForeground(new java.awt.Color(0, 0, 0));
        lblIG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIG.setText("Instagram");

        lblIconTwitter.setForeground(new java.awt.Color(0, 0, 0));
        lblIconTwitter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-twitter.png"))); // NOI18N
        lblIconTwitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconTwitterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconTwitterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconTwitterMouseExited(evt);
            }
        });

        lblTwitter.setForeground(new java.awt.Color(0, 0, 0));
        lblTwitter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTwitter.setText("Twitter");

        lblIconWA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIconWA.setForeground(new java.awt.Color(0, 0, 0));
        lblIconWA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconWA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-tentangapp-whatsapp.png"))); // NOI18N
        lblIconWA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconWAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconWAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconWAMouseExited(evt);
            }
        });

        lblWA.setForeground(new java.awt.Color(0, 0, 0));
        lblWA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWA.setText("Whatsapp");

        javax.swing.GroupLayout pnlInfoKontakLayout = new javax.swing.GroupLayout(pnlInfoKontak);
        pnlInfoKontak.setLayout(pnlInfoKontakLayout);
        pnlInfoKontakLayout.setHorizontalGroup(
            pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoKontakLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYT, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconIG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIG, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconTwitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoKontakLayout.setVerticalGroup(
            pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoKontakLayout.createSequentialGroup()
                .addComponent(pnlTitleInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconTwitter, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(lblIconIG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconYT, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(lblIconWA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYT)
                    .addComponent(lblIG)
                    .addComponent(lblTwitter)
                    .addComponent(lblWA))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoKontak, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, 400, 130));

        pnlInfoApp.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoApp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 98, 230), 2));

        pnlTitleInfoApp.setBackground(new java.awt.Color(15, 98, 230));
        pnlTitleInfoApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleInfoApp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitleInfoApp.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleInfoApp.setText("Info Aplikasi");

        javax.swing.GroupLayout pnlTitleInfoAppLayout = new javax.swing.GroupLayout(pnlTitleInfoApp);
        pnlTitleInfoApp.setLayout(pnlTitleInfoAppLayout);
        pnlTitleInfoAppLayout.setHorizontalGroup(
            pnlTitleInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoAppLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleInfoApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleInfoAppLayout.setVerticalGroup(
            pnlTitleInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitleInfoApp, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblNamaApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNamaApp.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaApp.setText("Nama Aplikasi");

        valNamaApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNamaApp.setForeground(new java.awt.Color(0, 0, 0));
        valNamaApp.setText(": UKK Pembayaran SPP");
        valNamaApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNamaAppMouseClicked(evt);
            }
        });

        lblVersiApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblVersiApp.setForeground(new java.awt.Color(0, 0, 0));
        lblVersiApp.setText("Versi Aplikasi");

        valVersiApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valVersiApp.setForeground(new java.awt.Color(0, 0, 0));
        valVersiApp.setText(": 1.0.0");
        valVersiApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valVersiAppMouseClicked(evt);
            }
        });

        valUkuranApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valUkuranApp.setForeground(new java.awt.Color(0, 0, 0));
        valUkuranApp.setText(": 150 Mb");
        valUkuranApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valUkuranAppMouseClicked(evt);
            }
        });

        lblUkuranApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblUkuranApp.setForeground(new java.awt.Color(0, 0, 0));
        lblUkuranApp.setText("Ukuran Aplikasi");

        valDirilis.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valDirilis.setForeground(new java.awt.Color(0, 0, 0));
        valDirilis.setText(": 01 Maret 2021");
        valDirilis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valDirilisMouseClicked(evt);
            }
        });

        lblDirilis.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblDirilis.setForeground(new java.awt.Color(0, 0, 0));
        lblDirilis.setText("Dirilis Pada");

        lblPengguna.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblPengguna.setForeground(new java.awt.Color(0, 0, 0));
        lblPengguna.setText("Total Pengguna");

        valPengguna.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valPengguna.setForeground(new java.awt.Color(0, 0, 0));
        valPengguna.setText(": 393 Pengguna");
        valPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valPenggunaMouseClicked(evt);
            }
        });

        lblDeveloper.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblDeveloper.setForeground(new java.awt.Color(0, 0, 0));
        lblDeveloper.setText("Developer");

        valDeveloper.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valDeveloper.setForeground(new java.awt.Color(0, 0, 0));
        valDeveloper.setText(": Achmad Baihaqi");
        valDeveloper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valDeveloperMouseClicked(evt);
            }
        });

        lblDiupdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblDiupdate.setForeground(new java.awt.Color(0, 0, 0));
        lblDiupdate.setText("Diperbarui Pada");

        valDiupdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valDiupdate.setForeground(new java.awt.Color(0, 0, 0));
        valDiupdate.setText(": 19 Juli 2021");
        valDiupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valDiupdateMouseClicked(evt);
            }
        });

        lblBahasa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblBahasa.setForeground(new java.awt.Color(0, 0, 0));
        lblBahasa.setText("Bahasa Program");

        valBahasa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valBahasa.setForeground(new java.awt.Color(0, 0, 0));
        valBahasa.setText(": Java");
        valBahasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valBahasaMouseClicked(evt);
            }
        });

        valDatabase.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valDatabase.setForeground(new java.awt.Color(0, 0, 0));
        valDatabase.setText(": MySQL");
        valDatabase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valDatabaseMouseClicked(evt);
            }
        });

        lblDatabase.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblDatabase.setForeground(new java.awt.Color(0, 0, 0));
        lblDatabase.setText("Database Platform");

        lblTahunAjaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTahunAjaran.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunAjaran.setText("Tahun Ajaran");

        valTahunAjaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTahunAjaran.setForeground(new java.awt.Color(0, 0, 0));
        valTahunAjaran.setText(": 2020-2021");
        valTahunAjaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valTahunAjaranMouseClicked(evt);
            }
        });

        lblCompany.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCompany.setForeground(new java.awt.Color(0, 0, 0));
        lblCompany.setText("Company");

        valCompany.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valCompany.setForeground(new java.awt.Color(0, 0, 0));
        valCompany.setText(": SMK Negeri 1 Kertosono");
        valCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valCompanyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoAppLayout = new javax.swing.GroupLayout(pnlInfoApp);
        pnlInfoApp.setLayout(pnlInfoAppLayout);
        pnlInfoAppLayout.setHorizontalGroup(
            pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoAppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                        .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInfoAppLayout.createSequentialGroup()
                                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDirilis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDiupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(lblBahasa, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(lblDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(lblTahunAjaran, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(lblCompany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(valDirilis, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(valDiupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(valBahasa, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(valDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(valTahunAjaran, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valCompany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInfoAppLayout.createSequentialGroup()
                                .addComponent(lblUkuranApp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valUkuranApp, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(pnlInfoAppLayout.createSequentialGroup()
                        .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                .addComponent(lblNamaApp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valNamaApp, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInfoAppLayout.createSequentialGroup()
                                .addComponent(lblVersiApp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valVersiApp, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))))
        );
        pnlInfoAppLayout.setVerticalGroup(
            pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoAppLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaApp)
                    .addComponent(valNamaApp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVersiApp)
                    .addComponent(valVersiApp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valUkuranApp)
                    .addComponent(lblUkuranApp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDirilis)
                    .addComponent(valDirilis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiupdate)
                    .addComponent(valDiupdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBahasa)
                    .addComponent(valBahasa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabase)
                    .addComponent(valDatabase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunAjaran)
                    .addComponent(valTahunAjaran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valPengguna)
                    .addComponent(lblPengguna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valDeveloper)
                    .addComponent(lblDeveloper))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valCompany)
                    .addComponent(lblCompany))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 470, 420));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 582, 900, 10));

        lblSekolahBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSekolahBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/logo-smkn1kts_30x37.png"))); // NOI18N
        pnlMain.add(lblSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 590, 40, 60));

        lblNamaAplikasiBottom.setForeground(new java.awt.Color(16, 81, 200));
        lblNamaAplikasiBottom.setText("Sistem Aplikasi Pembayaran SPP");
        pnlMain.add(lblNamaAplikasiBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 390, -1));

        lblNamaSekolahBottom.setForeground(new java.awt.Color(231, 38, 38));
        lblNamaSekolahBottom.setText("SMK Negeri 1 Kertosono");
        pnlMain.add(lblNamaSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 600, 390, 20));

        lblVersion.setForeground(new java.awt.Color(0, 0, 0));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersion.setText("Verison 1.0.0");
        pnlMain.add(lblVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, 280, -1));

        lblCopyright.setForeground(new java.awt.Color(0, 0, 0));
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2021. Achmad Baihaqi. All Rights Reserved.");
        pnlMain.add(lblCopyright, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 620, 350, -1));

        lblBgImage.setBackground(new java.awt.Color(41, 52, 71));
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 650));

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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        siswa.closeConnection();
        kls.closeConnection();
        Log.addLog("Menutup Window " + getClass().getName());
    }//GEN-LAST:event_formWindowClosed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        this.btnClose.setBackground(new Color(15,92,186));
        this.btnClose.setIcon(Gambar.getIcon("ic-app-close-entered.png"));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        this.btnClose.setBackground(new Color(11,114,238));
        this.btnClose.setIcon(Gambar.getIcon("ic-app-close.png"));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Application.closeApplication();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimazeMouseEntered
        this.btnMinimaze.setBackground(new Color(15,92,186));
        this.btnMinimaze.setIcon(Gambar.getIcon("ic-app-minimaze-entered.png"));
    }//GEN-LAST:event_btnMinimazeMouseEntered

    private void btnMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimazeMouseExited
        this.btnMinimaze.setBackground(new Color(11,114,238));
        this.btnMinimaze.setIcon(Gambar.getIcon("ic-app-minimaze.png"));
    }//GEN-LAST:event_btnMinimazeMouseExited

    private void btnMinimazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimazeActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimazeActionPerformed

    private void valDataAplikasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valDataAplikasiMouseClicked
        Message.showInformation(this, "Data Aplikasi : " + this.dataApp);
    }//GEN-LAST:event_valDataAplikasiMouseClicked

    private void valCacheAplikasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valCacheAplikasiMouseClicked
        Message.showInformation(this, "Cache Aplikasi : " + this.cacheApp);
    }//GEN-LAST:event_valCacheAplikasiMouseClicked

    private void valTotalPenyimpananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTotalPenyimpananMouseClicked
        Message.showInformation(this, "Total : " + this.total);
    }//GEN-LAST:event_valTotalPenyimpananMouseClicked

    private void valRuangTersediaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valRuangTersediaMouseClicked
        Message.showInformation(this, "Ruang Tersedia : " + this.ruangTersedia);
    }//GEN-LAST:event_valRuangTersediaMouseClicked

    private void valPenggunaanRamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valPenggunaanRamMouseClicked
        Message.showInformation(this, "Penggunaan RAM : " + this.penggunaanRam);
    }//GEN-LAST:event_valPenggunaanRamMouseClicked

    private void btnHapusDataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusDataMouseEntered
        this.btnHapusData.setIcon(Gambar.getIcon("ic-tentangapp-data-entered.png"));
    }//GEN-LAST:event_btnHapusDataMouseEntered

    private void btnHapusDataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusDataMouseExited
        this.btnHapusData.setIcon(Gambar.getIcon("ic-tentangapp-data.png"));
    }//GEN-LAST:event_btnHapusDataMouseExited

    private void btnHapusDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDataActionPerformed
        Audio.play(Audio.SOUND_WARNING);
        new ConfirmHapusData(this, true).setVisible(true);
    }//GEN-LAST:event_btnHapusDataActionPerformed

    private void btnHapusCacheMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusCacheMouseEntered
        this.btnHapusCache.setIcon(Gambar.getIcon("ic-tentangapp-cache-entered.png"));
    }//GEN-LAST:event_btnHapusCacheMouseEntered

    private void btnHapusCacheMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusCacheMouseExited
        this.btnHapusCache.setIcon(Gambar.getIcon("ic-tentangapp-cache.png"));
    }//GEN-LAST:event_btnHapusCacheMouseExited

    private void btnHapusCacheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusCacheActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnHapusCacheActionPerformed

    private void lblIconYTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconYTMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini.");
    }//GEN-LAST:event_lblIconYTMouseClicked

    private void lblIconYTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconYTMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblIconYT.setIcon(Gambar.getIcon("ic-tentangapp-youtube-entered.png"));
        this.lblYT.setForeground(new Color(246,46,46));
    }//GEN-LAST:event_lblIconYTMouseEntered

    private void lblIconYTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconYTMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblIconYT.setIcon(Gambar.getIcon("ic-tentangapp-youtube.png"));
        this.lblYT.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_lblIconYTMouseExited

    private void lblIconIGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini.");
    }//GEN-LAST:event_lblIconIGMouseClicked

    private void lblIconIGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblIconIG.setIcon(Gambar.getIcon("ic-tentangapp-instagram-entered.png"));
        this.lblIG.setForeground(new Color(246,46,46));
    }//GEN-LAST:event_lblIconIGMouseEntered

    private void lblIconIGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblIconIG.setIcon(Gambar.getIcon("ic-tentangapp-instagram.png"));
        this.lblIG.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_lblIconIGMouseExited

    private void lblIconTwitterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTwitterMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini.");
    }//GEN-LAST:event_lblIconTwitterMouseClicked

    private void lblIconTwitterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTwitterMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblIconTwitter.setIcon(Gambar.getIcon("ic-tentangapp-twitter-entered.png"));
        this.lblTwitter.setForeground(new Color(246,46,46));
    }//GEN-LAST:event_lblIconTwitterMouseEntered

    private void lblIconTwitterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTwitterMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblIconTwitter.setIcon(Gambar.getIcon("ic-tentangapp-twitter.png"));
        this.lblTwitter.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_lblIconTwitterMouseExited

    private void lblIconWAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseClicked
        Internet net = new Internet();
        if(net.isConnectInternet()){
            try {
                net.openLink("https://wa.me/625655864624");
            } catch (IOException | URISyntaxException ex) {
                Message.showException(this, ex, true);
            }
        }else{
            Message.showWarning(this, "Tidak terhubung ke Internet!");
        }
    }//GEN-LAST:event_lblIconWAMouseClicked

    private void lblIconWAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblIconWA.setIcon(Gambar.getIcon("ic-tentangapp-whatsapp-entered.png"));
        this.lblWA.setForeground(new Color(246,46,46));
    }//GEN-LAST:event_lblIconWAMouseEntered

    private void lblIconWAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblIconWA.setIcon(Gambar.getIcon("ic-tentangapp-whatsapp.png"));
        this.lblWA.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_lblIconWAMouseExited

    private void valNamaAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNamaAppMouseClicked
        Message.showInformation(this, "Nama Aplikasi : " + this.namaApp);
    }//GEN-LAST:event_valNamaAppMouseClicked

    private void valVersiAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valVersiAppMouseClicked
        Message.showInformation(this, "Versi Aplikasi : " + this.versiApp);
    }//GEN-LAST:event_valVersiAppMouseClicked

    private void valUkuranAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valUkuranAppMouseClicked
        Message.showInformation(this, "Ukuran Aplikasi : " + this.ukuranApp);
    }//GEN-LAST:event_valUkuranAppMouseClicked

    private void valDirilisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valDirilisMouseClicked
        Message.showInformation(this, "Dirilis Pada : " + this.dirilis);
    }//GEN-LAST:event_valDirilisMouseClicked

    private void valPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valPenggunaMouseClicked
        Message.showInformation(this, "Total Pengguna : " + this.pengguna);
    }//GEN-LAST:event_valPenggunaMouseClicked

    private void valDeveloperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valDeveloperMouseClicked
        Message.showInformation(this, "Developer : " + this.developer);
    }//GEN-LAST:event_valDeveloperMouseClicked

    private void valDiupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valDiupdateMouseClicked
        Message.showInformation(this, "Diperbarui Pada : " + this.diupdate);
    }//GEN-LAST:event_valDiupdateMouseClicked

    private void valBahasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valBahasaMouseClicked
        Message.showInformation(this, "Bahasa Program : " + this.bahasaProgram);
    }//GEN-LAST:event_valBahasaMouseClicked

    private void valDatabaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valDatabaseMouseClicked
        Message.showInformation(this, "Database Platform : " + this.dbPlatform);
    }//GEN-LAST:event_valDatabaseMouseClicked

    private void valTahunAjaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTahunAjaranMouseClicked
        Message.showInformation(this, "Tahun Ajaran : " + this.tahunAjaran);
    }//GEN-LAST:event_valTahunAjaranMouseClicked

    private void valCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valCompanyMouseClicked
        Message.showInformation(this, "Company : " + this.company);
    }//GEN-LAST:event_valCompanyMouseClicked

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
        yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DashboardSiswa dashboard = new DashboardSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dashboard.setLocation(getX(), getY());
                dashboard.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void lblPhotoProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        InformasiAkunSiswa infoAkun = new InformasiAkunSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                infoAkun.setLocation(getX(), getY());
                infoAkun.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblPhotoProfileMouseClicked

    private void lblPhotoProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseEntered
        this.lblPhotoProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblPhotoProfileMouseEntered

    private void lblPhotoProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseExited
        this.lblPhotoProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblPhotoProfileMouseExited

    private void lblNamaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNamaUserMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        InformasiAkunSiswa infoAkun = new InformasiAkunSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                infoAkun.setLocation(getX(), getY());
                infoAkun.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblNamaUserMouseClicked

    private void lblNamaUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNamaUserMouseEntered
        this.lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblNamaUser.setText("<html><p style=\"text-decoration:underline;\">"+name+"</p></html>");
    }//GEN-LAST:event_lblNamaUserMouseEntered

    private void lblNamaUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNamaUserMouseExited
        this.lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblNamaUser.setText("<html><p style=\"text-decoration:none;\">"+name+"</p></html>");
    }//GEN-LAST:event_lblNamaUserMouseExited

    private void btnInfoAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAkunActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        InformasiAkunSiswa infoAkun = new InformasiAkunSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                infoAkun.setLocation(getX(), getY());
                infoAkun.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnInfoAkunActionPerformed

    private void btnPembayaranSppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranSppActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        RiwayatPembayaranSiswa histori = new RiwayatPembayaranSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                histori.setLocation(getX(), getY());
                histori.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnPembayaranSppActionPerformed

    private void btnTentangAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangAppActionPerformed
        // no event
    }//GEN-LAST:event_btnTentangAppActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Log.addLog("Menutup Window " + getClass().getName());
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
            java.util.logging.Logger.getLogger(TentangAplikasiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TentangAplikasiSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnHapusCache;
    private javax.swing.JButton btnHapusData;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnPembayaranSpp;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel lblBahasa;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblCacheAplikasi;
    private javax.swing.JLabel lblCompany;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblDataApplikasi;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblDeveloper;
    private javax.swing.JLabel lblDirilis;
    private javax.swing.JLabel lblDiupdate;
    private javax.swing.JLabel lblIG;
    private javax.swing.JLabel lblIconIG;
    private javax.swing.JLabel lblIconTwitter;
    private javax.swing.JLabel lblIconWA;
    private javax.swing.JLabel lblIconYT;
    private javax.swing.JLabel lblNamaAplikasiBottom;
    private javax.swing.JLabel lblNamaApp;
    private javax.swing.JLabel lblNamaSekolahBottom;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblPengguna;
    private javax.swing.JLabel lblPenggunaanRam;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblRuangTesedia;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSekolahBottom;
    private javax.swing.JLabel lblSiswa;
    private javax.swing.JLabel lblTahunAjaran;
    private javax.swing.JLabel lblTitleInfoApp;
    private javax.swing.JLabel lblTitleKontak;
    private javax.swing.JLabel lblTitlePenyimpanan;
    private javax.swing.JLabel lblTotalPenyimpanan;
    private javax.swing.JLabel lblTwitter;
    private javax.swing.JLabel lblUkuranApp;
    private javax.swing.JLabel lblVersiApp;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel lblWA;
    private javax.swing.JLabel lblYT;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator linePenyimpanan;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfoApp;
    private javax.swing.JPanel pnlInfoKontak;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPenyimpanan;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleInfo;
    private javax.swing.JPanel pnlTitleInfoApp;
    private javax.swing.JPanel pnlTitlePenyimpanan;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel valBahasa;
    private javax.swing.JLabel valCacheAplikasi;
    private javax.swing.JLabel valCompany;
    private javax.swing.JLabel valDataAplikasi;
    private javax.swing.JLabel valDatabase;
    private javax.swing.JLabel valDeveloper;
    private javax.swing.JLabel valDirilis;
    private javax.swing.JLabel valDiupdate;
    private javax.swing.JLabel valNamaApp;
    private javax.swing.JLabel valPengguna;
    private javax.swing.JLabel valPenggunaanRam;
    private javax.swing.JLabel valRuangTersedia;
    private javax.swing.JLabel valTahunAjaran;
    private javax.swing.JLabel valTotalPenyimpanan;
    private javax.swing.JLabel valUkuranApp;
    private javax.swing.JLabel valVersiApp;
    // End of variables declaration//GEN-END:variables
}
