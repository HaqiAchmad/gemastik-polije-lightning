package com.window.siswa;

import com.data.app.Application;
import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.Transaksi;
import com.manage.Message;
import com.manage.Text;
import com.media.Gambar;
import com.manage.Waktu;
import com.users.UserPhotoSize;
import com.users.Users;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2021-03-09
 */
public class DashboardSiswa extends javax.swing.JFrame {
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Transaksi tr = new Transaksi();
    private final Kelas kls = new Kelas();
    private final Waktu waktu = new Waktu();
    private final Text txt = new Text();
    
    private boolean isVisible = true;
    private final String currentLogin, nama, gender, kelas, levelKelas, email, wali, nominalSpp, sppDibayar , kekuranganSpp;
    private int x, y;
    
    public DashboardSiswa() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // mendapatkan data-data dari siswa
        currentLogin = siswa.getCurrentLogin();
        nama = txt.toCapitalize(siswa.getNama());
        gender = txt.getGenderName(siswa.getGender());
        kelas = kls.getNamaKelas(siswa.getIdKelas());
        levelKelas = kls.getLevelKelas(siswa.getIdKelas());
        email = siswa.getEmail().toLowerCase();
        wali = txt.toCapitalize(siswa.getNama());
        nominalSpp = tr.addRp(tr.getNominalSpp(Integer.parseInt(siswa.getIdSpp(siswa.getCurrentLogin()))));
        sppDibayar = tr.addRp(tr.getTotalSppDibayarBySiswa(Integer.parseInt(siswa.getCurrentLogin()), "2020-2021"));
        kekuranganSpp = tr.addRp(tr.getKekuranganSppBySiswa(Integer.parseInt(siswa.getCurrentLogin()), "2020-2021"));
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolahBottom.setIcon(Gambar.getBottomIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblNamaSekolahBottom.setText(Application.getCompany());
        this.lblNamaAplikasiBottom.setText(Application.getNama());
        this.lblVersion.setText("Versi " + Application.getVersi());
        this.lblCopyright.setText(Application.getRightReserved());
        
        // menampilkan data-data siwa
        this.lblNamaFoto.setText("<html><p>"+nama+"</p></html>");
        this.lblNamaUser.setText("<html><p>"+nama+"</p></html>");
        this.lblSiswa.setText("<html><p>Siswa Kelas "+levelKelas+"</p></html>");
        this.valNis.setText("<html><p>:&nbsp;"+currentLogin+"</p></html>");
        this.valNama.setText("<html><p>:&nbsp;"+nama+"</p></html>");
        this.valGender.setText("<html><p>:&nbsp;"+gender+"</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;"+kelas+"</p></html>");
        this.valEmail.setText("<html><p>:&nbsp;"+email+"</p></html>");
        this.valWali.setText("<html><p>:&nbsp;"+wali+"</p></html>");
        this.valNominal.setText("<html><p>:&nbsp;"+nominalSpp+"</p></html>");
        this.valDibayar.setText("<html><p>:&nbsp;"+sppDibayar+"</p></html>");
        this.valKekurangan.setText("<html><p>:&nbsp;"+kekuranganSpp+"</p></html>");
        this.lblProfile.setIcon(siswa.getPhoto(UserPhotoSize.DASHBOARD_SISWA_FOTO));
        this.lblPhotoProfile.setIcon(siswa.getPhotoProfile());

        // mengatur ui dari button
        this.btnDashboard.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoAkun.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHistori.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnClose.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinimaze.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    
        this.btnDashboard.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnInfoAkun, this.btnHistori, this.btnTentangApp
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
                    btn.setBackground(new Color(13,88,149));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(22,24,24));
                }
            });
        }
        
        JLabel[] lbls = new JLabel[]{
            this.valDibayar, this.valEmail, this.valGender, this.valKekurangan, 
            this.valKelas, this.valNama, this.valNis, this.valNominal, this.valWali
        };
        
        for(JLabel lbl : lbls){
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
                    lbl.setForeground(new java.awt.Color(7,90,206));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new java.awt.Color(38,40,46));
                }
            });
        }
        
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(isVisible){
                        lblDataJam.setText(waktu.getUpdateWaktu());
                        Thread.sleep(500);
                    }
                }catch(InterruptedException ex){
                    Message.showException(this, "Terjadi Kesalahan Saat Mengupdate Tanggal!\n" + ex.getMessage(), ex, true);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        pnlAccount = new javax.swing.JPanel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblNamaUser = new javax.swing.JLabel();
        lblSiswa = new javax.swing.JLabel();
        btnInfoAkun = new javax.swing.JButton();
        btnHistori = new javax.swing.JButton();
        pnlLeftBottom = new javax.swing.JPanel();
        btnTentangApp = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimaze = new javax.swing.JButton();
        lblSekolah = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        lblCopyright = new javax.swing.JLabel();
        lblSekolahBottom = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblNamaAplikasiBottom = new javax.swing.JLabel();
        lblNamaSekolahBottom = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        pnlDataJam = new javax.swing.JPanel();
        lblDataTop = new javax.swing.JLabel();
        lblDataJam = new javax.swing.JLabel();
        pnlBiodata = new javax.swing.JPanel();
        lblNis = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblWali = new javax.swing.JLabel();
        lblNominal = new javax.swing.JLabel();
        valGender = new javax.swing.JLabel();
        valNis = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();
        valEmail = new javax.swing.JLabel();
        valWali = new javax.swing.JLabel();
        valNominal = new javax.swing.JLabel();
        lblSppDibayar = new javax.swing.JLabel();
        lblKekurangan = new javax.swing.JLabel();
        valDibayar = new javax.swing.JLabel();
        valKekurangan = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblNamaFoto = new javax.swing.JLabel();
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
                    .addComponent(lblNamaUser, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(lblSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPhotoProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnHistori.setBackground(new java.awt.Color(22, 24, 24));
        btnHistori.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHistori.setForeground(new java.awt.Color(255, 255, 255));
        btnHistori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-pembayaran.png"))); // NOI18N
        btnHistori.setText("Riwayat Pembayaran");
        btnHistori.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnHistori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHistori.setIconTextGap(6);
        btnHistori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriActionPerformed(evt);
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
                    .addComponent(btnHistori, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(btnInfoAkun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistori, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTentangApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-logo.png"))); // NOI18N
        lblDashboard.setText("Dashboard Siswa");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));

        lblCopyright.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(0, 0, 0));
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2021. Achmad Baihaqi. All Rights Reserved.");
        lblCopyright.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblSekolahBottom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSekolahBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/logo-smkn1kts_30x37.png"))); // NOI18N
        lblSekolahBottom.setIconTextGap(8);

        lblVersion.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(0, 0, 0));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersion.setText("Version 1.0.0");

        lblNamaAplikasiBottom.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblNamaAplikasiBottom.setForeground(new java.awt.Color(16, 81, 200));
        lblNamaAplikasiBottom.setText("Aplikasi Pembayaran SPP");

        lblNamaSekolahBottom.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        lblNamaSekolahBottom.setForeground(new java.awt.Color(231, 38, 38));
        lblNamaSekolahBottom.setText("SMK Negeri 1 Kertosono");

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblSekolahBottom)
                .addGap(18, 18, 18)
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaSekolahBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(lblNamaAplikasiBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(237, 237, 237)
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(lblVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomLayout.createSequentialGroup()
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSekolahBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBottomLayout.createSequentialGroup()
                        .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBottomLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblVersion))
                            .addGroup(pnlBottomLayout.createSequentialGroup()
                                .addComponent(lblNamaSekolahBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCopyright)
                            .addGroup(pnlBottomLayout.createSequentialGroup()
                                .addComponent(lblNamaAplikasiBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))))
                .addGap(12, 12, 12))
        );

        pnlMain.add(pnlBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 990, 50));

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 90, 206), 3));
        pnlData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDataJam.setBackground(new java.awt.Color(7, 90, 206));

        lblDataTop.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataTop.setForeground(new java.awt.Color(255, 255, 255));
        lblDataTop.setText("Biodata Anda");

        lblDataJam.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataJam.setForeground(new java.awt.Color(255, 255, 255));
        lblDataJam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataJam.setText("Jumat, 19 Maret 2021 | 08:30:45");

        javax.swing.GroupLayout pnlDataJamLayout = new javax.swing.GroupLayout(pnlDataJam);
        pnlDataJam.setLayout(pnlDataJamLayout);
        pnlDataJamLayout.setHorizontalGroup(
            pnlDataJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataJamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataTop, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(lblDataJam, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDataJamLayout.setVerticalGroup(
            pnlDataJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataTop, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addComponent(lblDataJam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlData.add(pnlDataJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, -1, -1));

        pnlBiodata.setBackground(new java.awt.Color(255, 255, 255));

        lblNis.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lblNama.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblNama.setForeground(new java.awt.Color(0, 0, 0));
        lblNama.setText("Nama Siswa");

        lblKelas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        lblJenis.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(0, 0, 0));
        lblJenis.setText("Jenis Kelamin");

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        lblWali.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblWali.setForeground(new java.awt.Color(0, 0, 0));
        lblWali.setText("Nama Wali");

        lblNominal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(0, 0, 0));
        lblNominal.setText("Nominal SPP");

        valGender.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valGender.setForeground(new java.awt.Color(0, 0, 0));
        valGender.setText(": Laki-Laki");

        valNis.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": 6156");

        valNama.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Achmad Baihaqi");

        valKelas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII RPL 1");

        valEmail.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valEmail.setForeground(new java.awt.Color(0, 0, 0));
        valEmail.setText(": hakiahmad756@gmail.com");

        valWali.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valWali.setForeground(new java.awt.Color(0, 0, 0));
        valWali.setText(": Manusia");

        valNominal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valNominal.setForeground(new java.awt.Color(0, 0, 0));
        valNominal.setText(": Rp. 135.000.00");

        lblSppDibayar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblSppDibayar.setForeground(new java.awt.Color(0, 0, 0));
        lblSppDibayar.setText("SPP Dibayar");

        lblKekurangan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        lblKekurangan.setText("Kekurangan ");

        valDibayar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valDibayar.setForeground(new java.awt.Color(0, 0, 0));
        valDibayar.setText(": Rp. 1.080.000.00");

        valKekurangan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        valKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        valKekurangan.setText(": Rp. 540.000.00");

        javax.swing.GroupLayout pnlBiodataLayout = new javax.swing.GroupLayout(pnlBiodata);
        pnlBiodata.setLayout(pnlBiodataLayout);
        pnlBiodataLayout.setHorizontalGroup(
            pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBiodataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBiodataLayout.createSequentialGroup()
                        .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWali, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSppDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKekurangan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valGender, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valWali, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valKekurangan, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBiodataLayout.createSequentialGroup()
                        .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNama, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(valNis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnlBiodataLayout.setVerticalGroup(
            pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBiodataLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNis)
                    .addComponent(valNis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBiodataLayout.createSequentialGroup()
                        .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addComponent(lblNama))
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenis)
                    .addComponent(valGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKelas)
                    .addComponent(valKelas))
                .addGap(11, 11, 11)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(valEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWali)
                    .addComponent(valWali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNominal)
                    .addComponent(valNominal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valDibayar)
                    .addComponent(lblSppDibayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBiodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valKekurangan)
                    .addComponent(lblKekurangan))
                .addGap(86, 86, 86))
        );

        pnlData.add(pnlBiodata, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 37, 580, 380));

        lblProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlData.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 91, 200, 270));

        lblNamaFoto.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblNamaFoto.setForeground(new java.awt.Color(38, 40, 46));
        lblNamaFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaFoto.setText("Achmad Baihaqi");
        lblNamaFoto.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlData.add(lblNamaFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 374, 210, 40));

        pnlMain.add(pnlData, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 910, 420));

        lblBgImage.setBackground(new java.awt.Color(41, 52, 71));
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        siswa.closeConnection();
        kls.closeConnection();
        tr.closeConnection();
        isVisible = false;
        Log.addLog("Menutup Window " + getClass().getName());
    }//GEN-LAST:event_formWindowClosed

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
            yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Application.closeApplication();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        this.btnClose.setBackground(new Color(15,92,186));
        this.btnClose.setIcon(Gambar.getIcon("ic-app-close-entered.png"));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        this.btnClose.setBackground(new Color(11,114,238));
        this.btnClose.setIcon(Gambar.getIcon("ic-app-close.png"));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnMinimazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimazeActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimazeActionPerformed

    private void btnMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimazeMouseEntered
        this.btnMinimaze.setBackground(new Color(15,92,186));
        this.btnMinimaze.setIcon(Gambar.getIcon("ic-app-minimaze-entered.png"));
    }//GEN-LAST:event_btnMinimazeMouseEntered

    private void btnMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimazeMouseExited
        this.btnMinimaze.setBackground(new Color(11,114,238));
        this.btnMinimaze.setIcon(Gambar.getIcon("ic-app-minimaze.png"));
    }//GEN-LAST:event_btnMinimazeMouseExited
    
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
        this.lblNamaUser.setText("<html><p style=\"text-decoration:underline;\">"+siswa.getNama()+"</p></html>");
    }//GEN-LAST:event_lblNamaUserMouseEntered

    private void lblNamaUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNamaUserMouseExited
        this.lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblNamaUser.setText("<html><p style=\"text-decoration:none;\">"+siswa.getNama()+"</p></html>");
    }//GEN-LAST:event_lblNamaUserMouseExited

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

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed

    }//GEN-LAST:event_btnDashboardActionPerformed

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

    private void btnHistoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriActionPerformed
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
    }//GEN-LAST:event_btnHistoriActionPerformed

    private void btnTentangAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangAppActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        TentangAplikasiSiswa tentangApp = new TentangAplikasiSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                tentangApp.setLocation(getX(), getY());
                tentangApp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnTentangAppActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DashboardSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnHistori;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblDataJam;
    private javax.swing.JLabel lblDataTop;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblKekurangan;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaAplikasiBottom;
    private javax.swing.JLabel lblNamaFoto;
    private javax.swing.JLabel lblNamaSekolahBottom;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSekolahBottom;
    private javax.swing.JLabel lblSiswa;
    private javax.swing.JLabel lblSppDibayar;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel lblWali;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlBiodata;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlDataJam;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel valDibayar;
    private javax.swing.JLabel valEmail;
    private javax.swing.JLabel valGender;
    private javax.swing.JLabel valKekurangan;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valNominal;
    private javax.swing.JLabel valWali;
    // End of variables declaration//GEN-END:variables
}
