package com.window.petugas;

import com.data.app.Application;
import com.data.app.Log;
import com.data.app.Transaksi;
import com.data.db.DatabaseTables;
import com.manage.Message;
import com.manage.Text;
import com.media.Gambar;
import com.manage.Waktu;
import com.users.Users;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-09
 */
public class DashboardPetugas extends javax.swing.JFrame {
    
    private final Users.LevelPetugas petugas = Users.levelPetugas();
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Transaksi tr = new Transaksi();
    private final Waktu waktu = new Waktu();
    private final Text txt = new Text();
    
    private boolean isVisible = true;
    private final String name, totalAkun, totalAdmin, totalPetugas, totalSiswa, totalKelas, totalSpp;
    private int x, y;
    
    public DashboardPetugas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());        
        
        // mendapatkan data-data petugas
        name = txt.toCapitalize(petugas.getNama());
        totalAkun = txt.addDelim(petugas.getTotalUser());
        totalAdmin = txt.addDelim(petugas.getTotalAdmin());
        totalPetugas = txt.addDelim(petugas.getTotalPetugas());
        totalSiswa = txt.addDelim(siswa.getTotalSiswa());
        totalKelas = txt.addDelim(petugas.getJumlahData(DatabaseTables.KELAS.name()));
        totalSpp = txt.toMoneyCase(Long.toString(tr.getTotalSppDibayar()));
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolahBottom.setIcon(Gambar.getBottomIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblNamaSekolahBottom.setText(Application.getCompany());
        this.lblNamaAplikasiBottom.setText(Application.getNama());
        this.lblVersion.setText("Versi " + Application.getVersi());
        this.lblCopyright.setText(Application.getRightReserved());
        
        // menampilkan data-data petugas
        this.lblNamaUser.setText("<html><p>"+name+"</p></html>");
        this.valDataUser.setText(totalAkun + " User");
        this.valDataAdmin.setText(totalAdmin + " Admin");
        this.valDataPetugas.setText(totalPetugas + " Petugas");
        this.valDataSiswa.setText(totalSiswa + " Siswa");
        this.valDataKelas.setText(totalKelas + " Kelas");
        this.valDataSpp.setText(totalSpp);
        this.lblPhotoProfile.setIcon(petugas.getPhotoProfile());
        
        // mengatur ui dari button
        this.btnDashboard.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoAkun.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataPetugas.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataSiswa.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataKelas.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataSpp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnPembayaranSpp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLaporan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnClose.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinimaze.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.btnDashboard.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnInfoAkun, this.btnDataPetugas, this.btnDataSiswa, this.btnDataKelas, 
            this.btnDataSpp, this.btnPembayaranSpp, this.btnLaporan, this.btnTentangApp
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
        
        // mengupdate waktu
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(isVisible){
                        System.out.println("mengudate waktu");
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
        lblTipeAkun = new javax.swing.JLabel();
        btnInfoAkun = new javax.swing.JButton();
        btnDataPetugas = new javax.swing.JButton();
        btnDataSiswa = new javax.swing.JButton();
        btnDataKelas = new javax.swing.JButton();
        btnPembayaranSpp = new javax.swing.JButton();
        pnlLeftBottom = new javax.swing.JPanel();
        btnDataSpp = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        btnTentangApp = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimaze = new javax.swing.JButton();
        lblSekolah = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        pnlDataJam = new javax.swing.JPanel();
        lblDataTop = new javax.swing.JLabel();
        lblDataJam = new javax.swing.JLabel();
        pnlDataUser = new javax.swing.JPanel();
        pnlDataUserIcon = new javax.swing.JPanel();
        lblDataUserIcon = new javax.swing.JLabel();
        lblDataUser = new javax.swing.JLabel();
        valDataUser = new javax.swing.JLabel();
        pnlDataPetugas = new javax.swing.JPanel();
        pnlDataPetugasIcon = new javax.swing.JPanel();
        lblDataPetugasIcon = new javax.swing.JLabel();
        lblDataPetugas = new javax.swing.JLabel();
        valDataPetugas = new javax.swing.JLabel();
        pnlDataKelas = new javax.swing.JPanel();
        pnlDataKelasIcon = new javax.swing.JPanel();
        lblDataKelasIcon = new javax.swing.JLabel();
        lblDataKelas = new javax.swing.JLabel();
        valDataKelas = new javax.swing.JLabel();
        pnlDataAdmin = new javax.swing.JPanel();
        pnlDataAdminIcon = new javax.swing.JPanel();
        lblDataAdminIcon = new javax.swing.JLabel();
        lblDataAdmin = new javax.swing.JLabel();
        valDataAdmin = new javax.swing.JLabel();
        pnlDataSiswa = new javax.swing.JPanel();
        pnlDataSiswaIcon = new javax.swing.JPanel();
        lblDataSiswaIcon = new javax.swing.JLabel();
        lblDataSiswa = new javax.swing.JLabel();
        valDataSiswa = new javax.swing.JLabel();
        pnlDataSpp = new javax.swing.JPanel();
        pnlDataSppIcon = new javax.swing.JPanel();
        lblDataSppIcon = new javax.swing.JLabel();
        lblDataSpp = new javax.swing.JLabel();
        valDataSpp = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        lblCopyright = new javax.swing.JLabel();
        lblSekolahBottom = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblNamaAplikasiBottom = new javax.swing.JLabel();
        lblNamaSekolahBottom = new javax.swing.JLabel();
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

        lblTipeAkun.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblTipeAkun.setForeground(new java.awt.Color(255, 255, 255));
        lblTipeAkun.setText("Admin User");

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
                    .addComponent(lblTipeAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhotoProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipeAkun)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        btnDataPetugas.setBackground(new java.awt.Color(22, 24, 24));
        btnDataPetugas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDataPetugas.setForeground(new java.awt.Color(255, 255, 255));
        btnDataPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-admin.png"))); // NOI18N
        btnDataPetugas.setText("Data Petugas");
        btnDataPetugas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataPetugas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataPetugas.setIconTextGap(6);
        btnDataPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPetugasActionPerformed(evt);
            }
        });

        btnDataSiswa.setBackground(new java.awt.Color(22, 24, 24));
        btnDataSiswa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDataSiswa.setForeground(new java.awt.Color(255, 255, 255));
        btnDataSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-siswa.png"))); // NOI18N
        btnDataSiswa.setText("Data Siswa");
        btnDataSiswa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataSiswa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataSiswa.setIconTextGap(6);
        btnDataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSiswaActionPerformed(evt);
            }
        });

        btnDataKelas.setBackground(new java.awt.Color(22, 24, 24));
        btnDataKelas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDataKelas.setForeground(new java.awt.Color(255, 255, 255));
        btnDataKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-kelas.png"))); // NOI18N
        btnDataKelas.setText("Data Kelas");
        btnDataKelas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataKelas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataKelas.setIconTextGap(6);
        btnDataKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataKelasActionPerformed(evt);
            }
        });

        btnPembayaranSpp.setBackground(new java.awt.Color(22, 24, 24));
        btnPembayaranSpp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPembayaranSpp.setForeground(new java.awt.Color(255, 255, 255));
        btnPembayaranSpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-pembayaran.png"))); // NOI18N
        btnPembayaranSpp.setText("Pembayaran SPP");
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

        btnDataSpp.setBackground(new java.awt.Color(22, 24, 24));
        btnDataSpp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDataSpp.setForeground(new java.awt.Color(255, 255, 255));
        btnDataSpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-spp.png"))); // NOI18N
        btnDataSpp.setText("Data SPP");
        btnDataSpp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataSpp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataSpp.setIconTextGap(6);
        btnDataSpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSppActionPerformed(evt);
            }
        });

        btnLaporan.setBackground(new java.awt.Color(22, 24, 24));
        btnLaporan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-sidemenu-petugas-laporan.png"))); // NOI18N
        btnLaporan.setText("Laporan");
        btnLaporan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLaporan.setIconTextGap(6);
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

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
                    .addComponent(btnLaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPembayaranSpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(btnDataSpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataKelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataSiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataPetugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInfoAkun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPembayaranSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTentangApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 610));

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
        lblSekolah.setText("SMK Negeri 1 Kertosono | SPP Payment");
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

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 3));

        pnlDataJam.setBackground(new java.awt.Color(26, 121, 173));

        lblDataTop.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataTop.setForeground(new java.awt.Color(255, 255, 255));
        lblDataTop.setText("Data Aplikasi Pembayaran SPP");

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

        pnlDataUser.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataUser.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataUserIcon.setBackground(new java.awt.Color(226, 72, 72));

        lblDataUserIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-user.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataUserIconLayout = new javax.swing.GroupLayout(pnlDataUserIcon);
        pnlDataUserIcon.setLayout(pnlDataUserIconLayout);
        pnlDataUserIconLayout.setHorizontalGroup(
            pnlDataUserIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataUserIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataUserIconLayout.setVerticalGroup(
            pnlDataUserIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataUserIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataUser.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataUser.setForeground(new java.awt.Color(0, 0, 0));
        lblDataUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDataUser.setText("Total Keseluruhan User");
        lblDataUser.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataUserMouseExited(evt);
            }
        });

        valDataUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataUser.setForeground(new java.awt.Color(0, 0, 0));
        valDataUser.setText("1,446 User");
        valDataUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataUserLayout = new javax.swing.GroupLayout(pnlDataUser);
        pnlDataUser.setLayout(pnlDataUserLayout);
        pnlDataUserLayout.setHorizontalGroup(
            pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataUserLayout.createSequentialGroup()
                .addComponent(pnlDataUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(valDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataUserLayout.setVerticalGroup(
            pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataUserLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataUserLayout.createSequentialGroup()
                        .addComponent(lblDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlDataPetugas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataPetugas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataPetugasIcon.setBackground(new java.awt.Color(57, 146, 228));

        lblDataPetugasIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataPetugasIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-petugas.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataPetugasIconLayout = new javax.swing.GroupLayout(pnlDataPetugasIcon);
        pnlDataPetugasIcon.setLayout(pnlDataPetugasIconLayout);
        pnlDataPetugasIconLayout.setHorizontalGroup(
            pnlDataPetugasIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataPetugasIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataPetugasIconLayout.setVerticalGroup(
            pnlDataPetugasIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataPetugasIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataPetugas.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblDataPetugas.setText("Total Keseluruhan Petugas");
        lblDataPetugas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataPetugasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataPetugasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataPetugasMouseExited(evt);
            }
        });

        valDataPetugas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valDataPetugas.setText("7 Petugas");
        valDataPetugas.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataPetugasLayout = new javax.swing.GroupLayout(pnlDataPetugas);
        pnlDataPetugas.setLayout(pnlDataPetugasLayout);
        pnlDataPetugasLayout.setHorizontalGroup(
            pnlDataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPetugasLayout.createSequentialGroup()
                .addComponent(pnlDataPetugasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataPetugasLayout.setVerticalGroup(
            pnlDataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPetugasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataPetugasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataPetugasLayout.createSequentialGroup()
                        .addComponent(lblDataPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlDataKelas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataKelas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataKelasIcon.setBackground(new java.awt.Color(228, 133, 44));

        lblDataKelasIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataKelasIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-kelas.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataKelasIconLayout = new javax.swing.GroupLayout(pnlDataKelasIcon);
        pnlDataKelasIcon.setLayout(pnlDataKelasIconLayout);
        pnlDataKelasIconLayout.setHorizontalGroup(
            pnlDataKelasIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataKelasIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataKelasIconLayout.setVerticalGroup(
            pnlDataKelasIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataKelasIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataKelas.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblDataKelas.setText("Total Kelas");
        lblDataKelas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataKelasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataKelasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataKelasMouseExited(evt);
            }
        });

        valDataKelas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataKelas.setForeground(new java.awt.Color(0, 0, 0));
        valDataKelas.setText("40 Kelas");
        valDataKelas.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataKelasLayout = new javax.swing.GroupLayout(pnlDataKelas);
        pnlDataKelas.setLayout(pnlDataKelasLayout);
        pnlDataKelasLayout.setHorizontalGroup(
            pnlDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataKelasLayout.createSequentialGroup()
                .addComponent(pnlDataKelasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataKelasLayout.setVerticalGroup(
            pnlDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataKelasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataKelasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataKelasLayout.createSequentialGroup()
                        .addComponent(lblDataKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlDataAdmin.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataAdmin.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataAdminIcon.setBackground(new java.awt.Color(40, 178, 85));

        lblDataAdminIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataAdminIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-admin.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataAdminIconLayout = new javax.swing.GroupLayout(pnlDataAdminIcon);
        pnlDataAdminIcon.setLayout(pnlDataAdminIconLayout);
        pnlDataAdminIconLayout.setHorizontalGroup(
            pnlDataAdminIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataAdminIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataAdminIconLayout.setVerticalGroup(
            pnlDataAdminIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataAdminIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataAdmin.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataAdmin.setForeground(new java.awt.Color(0, 0, 0));
        lblDataAdmin.setText("Total Keseluruhan Admin");
        lblDataAdmin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataAdminMouseExited(evt);
            }
        });

        valDataAdmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataAdmin.setForeground(new java.awt.Color(0, 0, 0));
        valDataAdmin.setText("5 Admin");
        valDataAdmin.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataAdminLayout = new javax.swing.GroupLayout(pnlDataAdmin);
        pnlDataAdmin.setLayout(pnlDataAdminLayout);
        pnlDataAdminLayout.setHorizontalGroup(
            pnlDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataAdminLayout.createSequentialGroup()
                .addComponent(pnlDataAdminIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(valDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataAdminLayout.setVerticalGroup(
            pnlDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataAdminLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataAdminIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataAdminLayout.createSequentialGroup()
                        .addComponent(lblDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlDataSiswa.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataSiswa.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataSiswaIcon.setBackground(new java.awt.Color(217, 67, 207));

        lblDataSiswaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataSiswaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-siswa.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataSiswaIconLayout = new javax.swing.GroupLayout(pnlDataSiswaIcon);
        pnlDataSiswaIcon.setLayout(pnlDataSiswaIconLayout);
        pnlDataSiswaIconLayout.setHorizontalGroup(
            pnlDataSiswaIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataSiswaIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataSiswaIconLayout.setVerticalGroup(
            pnlDataSiswaIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataSiswaIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataSiswa.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblDataSiswa.setText("Total Keseluruhan Siswa");
        lblDataSiswa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataSiswaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataSiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataSiswaMouseExited(evt);
            }
        });

        valDataSiswa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataSiswa.setForeground(new java.awt.Color(0, 0, 0));
        valDataSiswa.setText("1.430 Siswa");
        valDataSiswa.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataSiswaLayout = new javax.swing.GroupLayout(pnlDataSiswa);
        pnlDataSiswa.setLayout(pnlDataSiswaLayout);
        pnlDataSiswaLayout.setHorizontalGroup(
            pnlDataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataSiswaLayout.createSequentialGroup()
                .addComponent(pnlDataSiswaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataSiswaLayout.setVerticalGroup(
            pnlDataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataSiswaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataSiswaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataSiswaLayout.createSequentialGroup()
                        .addComponent(lblDataSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlDataSpp.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataSpp.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        pnlDataSppIcon.setBackground(new java.awt.Color(55, 61, 64));

        lblDataSppIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataSppIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-data-spp.png"))); // NOI18N

        javax.swing.GroupLayout pnlDataSppIconLayout = new javax.swing.GroupLayout(pnlDataSppIcon);
        pnlDataSppIcon.setLayout(pnlDataSppIconLayout);
        pnlDataSppIconLayout.setHorizontalGroup(
            pnlDataSppIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataSppIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pnlDataSppIconLayout.setVerticalGroup(
            pnlDataSppIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataSppIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        lblDataSpp.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        lblDataSpp.setForeground(new java.awt.Color(0, 0, 0));
        lblDataSpp.setText("Total SPP Yang Sudah Dibayar");
        lblDataSpp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDataSpp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataSppMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataSppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataSppMouseExited(evt);
            }
        });

        valDataSpp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valDataSpp.setForeground(new java.awt.Color(0, 0, 0));
        valDataSpp.setText("Rp. 390.340.000");
        valDataSpp.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlDataSppLayout = new javax.swing.GroupLayout(pnlDataSpp);
        pnlDataSpp.setLayout(pnlDataSppLayout);
        pnlDataSppLayout.setHorizontalGroup(
            pnlDataSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataSppLayout.createSequentialGroup()
                .addComponent(pnlDataSppIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlDataSppLayout.setVerticalGroup(
            pnlDataSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataSppLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlDataSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataSppIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataSppLayout.createSequentialGroup()
                        .addComponent(lblDataSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valDataSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDataJam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataLayout.createSequentialGroup()
                                .addComponent(pnlDataJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlDataPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDataSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDataKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDataSpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pnlMain.add(pnlData, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 910, 420));

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-logo.png"))); // NOI18N
        lblDashboard.setText("Dashboard Petugas");
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
        lblNamaAplikasiBottom.setText("SPP Payment");

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
        petugas.closeConnection();
        siswa.closeConnection();
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
        InformasiAkunPetugas infoAkun = new InformasiAkunPetugas();
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

    private void lblPhotoProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        InformasiAkunPetugas infoAkun = new InformasiAkunPetugas();
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
        // no event
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnInfoAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAkunActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        InformasiAkunPetugas infoAkun = new InformasiAkunPetugas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                infoAkun.setLocation(getX(), getY());
                infoAkun.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnInfoAkunActionPerformed

    private void btnDataPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPetugasActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataPetugas dataPetugas = new DataPetugas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataPetugas.setLocation(getX(), getY());
                dataPetugas.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnDataPetugasActionPerformed

    private void btnDataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSiswaActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataSiswa dataSiswa = new DataSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataSiswa.setLocation(getX(), getY());
                dataSiswa.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnDataSiswaActionPerformed

    private void btnDataKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKelasActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataKelas dataKelas = new DataKelas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataKelas.setLocation(getX(), getY());
                dataKelas.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnDataKelasActionPerformed

    private void btnDataSppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSppActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataSpp dataSpp = new DataSpp();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataSpp.setLocation(getX(), getY());
                dataSpp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnDataSppActionPerformed

    private void btnPembayaranSppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranSppActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        PembayaranSpp pembayaranSpp = new PembayaranSpp();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                pembayaranSpp.setLocation(getX(), getY());
                pembayaranSpp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnPembayaranSppActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        LaporanPembayaran laporanSpp = new LaporanPembayaran();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                laporanSpp.setLocation(getX(), getY());
                laporanSpp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnTentangAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangAppActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        TentangAplikasi tentangApp = new TentangAplikasi();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                tentangApp.setLocation(getX(), getY());
                tentangApp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnTentangAppActionPerformed

    private void lblDataUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataUserMouseClicked
        Message.showInformation(this, "Total Keseluruhan Akun : " + totalAkun);
    }//GEN-LAST:event_lblDataUserMouseClicked

    private void lblDataUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataUserMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataUser.setText("<html><p style=\"text-decoration:underline; color:rgb(226,72,72)\">Total Keseluruhan User</p></html>");
    }//GEN-LAST:event_lblDataUserMouseEntered

    private void lblDataUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataUserMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataUser.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total Keseluruhan User</p></html>");
    }//GEN-LAST:event_lblDataUserMouseExited

    private void lblDataPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataPetugasMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataPetugas dataPetugas = new DataPetugas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataPetugas.setLocation(getX(), getY());
                dataPetugas.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblDataPetugasMouseClicked

    private void lblDataPetugasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataPetugasMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataPetugas.setText("<html><p style=\"text-decoration:underline; color:rgb(57,146,228)\">Total Keseluruhan Petugas</p></html>");
    }//GEN-LAST:event_lblDataPetugasMouseEntered

    private void lblDataPetugasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataPetugasMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataPetugas.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total Keseluruhan Petugas</p></html>");
    }//GEN-LAST:event_lblDataPetugasMouseExited

    private void lblDataKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataKelasMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataKelas dataKelas = new DataKelas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataKelas.setLocation(getX(), getY());
                dataKelas.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblDataKelasMouseClicked

    private void lblDataKelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataKelasMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataKelas.setText("<html><p style=\"text-decoration:underline; color:rgb(228,133,44)\">Total Kelas</p></html>");
    }//GEN-LAST:event_lblDataKelasMouseEntered

    private void lblDataKelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataKelasMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataKelas.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total Kelas</p></html>");
    }//GEN-LAST:event_lblDataKelasMouseExited

    private void lblDataAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAdminMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataPetugas dataPetugas = new DataPetugas();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataPetugas.setLocation(getX(), getY());
                dataPetugas.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblDataAdminMouseClicked

    private void lblDataAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAdminMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataAdmin.setText("<html><p style=\"text-decoration:underline; color:rgb(40,178,85)\">Total Keseluruhan Admin</p></html>");
    }//GEN-LAST:event_lblDataAdminMouseEntered

    private void lblDataAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAdminMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataAdmin.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total Keseluruhan Admin</p></html>");
    }//GEN-LAST:event_lblDataAdminMouseExited

    private void lblDataSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSiswaMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataSiswa dataSiswa = new DataSiswa();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataSiswa.setLocation(getX(), getY());
                dataSiswa.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblDataSiswaMouseClicked

    private void lblDataSiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSiswaMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataSiswa.setText("<html><p style=\"text-decoration:underline; color:rgb(217,67,207)\">Total Keseluruhan Siswa</p></html>");
    }//GEN-LAST:event_lblDataSiswaMouseEntered

    private void lblDataSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSiswaMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataSiswa.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total Keseluruhan Siswa</p></html>");
    }//GEN-LAST:event_lblDataSiswaMouseExited

    private void lblDataSppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSppMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataSpp dataSpp = new DataSpp();
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                dataSpp.setLocation(getX(), getY());
                dataSpp.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_lblDataSppMouseClicked

    private void lblDataSppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSppMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblDataSpp.setText("<html><p style=\"text-decoration:underline; color:rgb(55,61,64)\">Total SPP Yang Sudah Dibayar</p></html>");
    }//GEN-LAST:event_lblDataSppMouseEntered

    private void lblDataSppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataSppMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblDataSpp.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Total SPP Yang Sudah Dibayar</p></html>");
    }//GEN-LAST:event_lblDataSppMouseExited

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
            java.util.logging.Logger.getLogger(DashboardPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DashboardPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKelas;
    private javax.swing.JButton btnDataPetugas;
    private javax.swing.JButton btnDataSiswa;
    private javax.swing.JButton btnDataSpp;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnPembayaranSpp;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblDataAdmin;
    private javax.swing.JLabel lblDataAdminIcon;
    private javax.swing.JLabel lblDataJam;
    private javax.swing.JLabel lblDataKelas;
    private javax.swing.JLabel lblDataKelasIcon;
    private javax.swing.JLabel lblDataPetugas;
    private javax.swing.JLabel lblDataPetugasIcon;
    private javax.swing.JLabel lblDataSiswa;
    private javax.swing.JLabel lblDataSiswaIcon;
    private javax.swing.JLabel lblDataSpp;
    private javax.swing.JLabel lblDataSppIcon;
    private javax.swing.JLabel lblDataTop;
    private javax.swing.JLabel lblDataUser;
    private javax.swing.JLabel lblDataUserIcon;
    private javax.swing.JLabel lblNamaAplikasiBottom;
    private javax.swing.JLabel lblNamaSekolahBottom;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSekolahBottom;
    private javax.swing.JLabel lblTipeAkun;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlDataAdmin;
    private javax.swing.JPanel pnlDataAdminIcon;
    private javax.swing.JPanel pnlDataJam;
    private javax.swing.JPanel pnlDataKelas;
    private javax.swing.JPanel pnlDataKelasIcon;
    private javax.swing.JPanel pnlDataPetugas;
    private javax.swing.JPanel pnlDataPetugasIcon;
    private javax.swing.JPanel pnlDataSiswa;
    private javax.swing.JPanel pnlDataSiswaIcon;
    private javax.swing.JPanel pnlDataSpp;
    private javax.swing.JPanel pnlDataSppIcon;
    private javax.swing.JPanel pnlDataUser;
    private javax.swing.JPanel pnlDataUserIcon;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel valDataAdmin;
    private javax.swing.JLabel valDataKelas;
    private javax.swing.JLabel valDataPetugas;
    private javax.swing.JLabel valDataSiswa;
    private javax.swing.JLabel valDataSpp;
    private javax.swing.JLabel valDataUser;
    // End of variables declaration//GEN-END:variables

}
