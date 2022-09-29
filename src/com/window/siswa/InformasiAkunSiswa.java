package com.window.siswa;

import com.data.app.Application;
import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.Transaksi;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.UserPhotoSize;
import com.users.Users;
import com.window.all.ConfirmLogout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-20
 */
public class InformasiAkunSiswa extends javax.swing.JFrame {
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Transaksi tr = new Transaksi();
    private final Kelas kls = new Kelas();
    private final Text txt = new Text();
    
    private final String currentLogin, name, gender, tmpLahir, tglLahir, kelas, levelKelas, alamat, no_hp, email, wali, pass, passStrength, nominal;
    private int x, y;
    
    public InformasiAkunSiswa() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // mendapatkan data-data dari siswa
        currentLogin = siswa.getCurrentLogin();
        name = txt.toCapitalize(siswa.getNama());
        gender = txt.getGenderName(siswa.getGender());
        tmpLahir = txt.toCapitalize(siswa.getTempatLahir());
        tglLahir = txt.toDateCase(siswa.getTanggalLahir());
        kelas = kls.getNamaKelas(siswa.getIdKelas());
        levelKelas = kls.getLevelKelas(siswa.getIdKelas());
        alamat = txt.toCapitalize(siswa.getAlamat());
        no_hp = txt.toTelephoneCase(siswa.getNoHp());
        email = siswa.getEmail().toLowerCase();
        wali = txt.toCapitalize(siswa.getNamaWali());
        pass = txt.toPasswordCase(siswa.getPassword());
        passStrength = siswa.getPasswordStrength(pass);
        nominal = txt.toMoneyCase(Integer.toString(tr.getNominalSpp(Integer.parseInt(siswa.getIdSpp()))));
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolahBottom.setIcon(Gambar.getBottomIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblNamaSekolahBottom.setText(Application.getCompany());
        this.lblNamaAplikasiBottom.setText(Application.getNama());
        
        // menampilkan data-data dari siswa
        this.lblNamaUser.setText("<html><p>"+name+"</p></html>");
        this.lblSiswa.setText("<html><p>Siswa Kelas "+levelKelas+"</p></html>");
        this.valNis.setText("<html><p>:&nbsp;"+currentLogin+"</p></html>");
        this.valGender.setText("<html><p>:&nbsp;"+gender+"</p></html>");
        this.valTempatLahir.setText("<html><p>:&nbsp;"+tmpLahir+"</p></html>");
        this.valTanggalLahir.setText("<html><p>:&nbsp;"+tglLahir+"</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;"+kelas+"</p></html>");
        this.valNoHp.setText("<html><p>:&nbsp;"+no_hp+"</p></html>");
        this.valNama.setText("<html><p>:&nbsp;"+name+"</p></html>");
        this.valAlamat.setText("<html><p>:&nbsp;"+alamat+"</p></html>");
        this.valEmail.setText("<html><p>:&nbsp;"+email+"</p></html>");
        this.valNominal.setText("<html><p>:&nbsp;"+nominal+"</p></html>");
        this.valPassword.setText("<html><p>:&nbsp;"+pass+"</p></html>");
        this.valKekuatan.setText("<html><p>:&nbsp;"+passStrength+"</p></html>");
        this.valWali.setText("<html><p>:&nbsp;"+wali+"</p></html>");
        this.lblInfoFoto.setIcon(siswa.getPhoto(UserPhotoSize.INFO_AKUN_SISWA));
        this.lblPhotoProfile.setIcon(siswa.getPhotoProfile());
        
        // mengatur ui dari button
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLogout.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDashboard.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoAkun.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHistori.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnClose.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinimaze.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        this.btnInfoAkun.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnDashboard, this.btnHistori,  this.btnTentangApp
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
        pnlInfoUtama = new javax.swing.JPanel();
        lblNis = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        lblNominal = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblWali = new javax.swing.JLabel();
        valNis = new javax.swing.JLabel();
        valNoHp = new javax.swing.JLabel();
        valEmail = new javax.swing.JLabel();
        valNominal = new javax.swing.JLabel();
        valWali = new javax.swing.JLabel();
        pnlInfoUtamaTitle = new javax.swing.JPanel();
        lblInfoUtama = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        pnlInfoKeamanan = new javax.swing.JPanel();
        pnlInfoKemananTitle = new javax.swing.JPanel();
        lblInfoKeamanan = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblKekuatan = new javax.swing.JLabel();
        valPassword = new javax.swing.JLabel();
        valKekuatan = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        pnlInfoPribadi = new javax.swing.JPanel();
        pnlInfoPribadiTitle = new javax.swing.JPanel();
        lblInfoPribadi = new javax.swing.JLabel();
        lblInfoFoto = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        valGender = new javax.swing.JLabel();
        lblTanggalLhr = new javax.swing.JLabel();
        valTanggalLahir = new javax.swing.JLabel();
        lblTempatLhr = new javax.swing.JLabel();
        valTempatLahir = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        valAlamat = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();
        lblSekolahBottom = new javax.swing.JLabel();
        lblNamaSekolahBottom = new javax.swing.JLabel();
        lblNamaAplikasiBottom = new javax.swing.JLabel();
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
                .addGap(35, 35, 35)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistori, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTentangApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));

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
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-infoakun-logo.png"))); // NOI18N
        lblDashboard.setText("Informasi Akun");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        pnlInfoUtama.setBackground(new java.awt.Color(252, 249, 249));
        pnlInfoUtama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 2));

        lblNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        lblNominal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(0, 0, 0));
        lblNominal.setText("Nominal SPP");

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        lblWali.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblWali.setForeground(new java.awt.Color(0, 0, 0));
        lblWali.setText("Nama Wali");

        valNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": 6156");
        valNis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNisMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNisMouseExited(evt);
            }
        });

        valNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoHp.setForeground(new java.awt.Color(0, 0, 0));
        valNoHp.setText(": 0856-5586-4624");
        valNoHp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNoHpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNoHpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNoHpMouseExited(evt);
            }
        });

        valEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valEmail.setForeground(new java.awt.Color(0, 0, 0));
        valEmail.setText(": hakiahmad756@gmail.com");
        valEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valEmailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valEmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valEmailMouseExited(evt);
            }
        });

        valNominal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNominal.setForeground(new java.awt.Color(0, 0, 0));
        valNominal.setText(": Rp. 140.000");
        valNominal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNominalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNominalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNominalMouseExited(evt);
            }
        });

        valWali.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valWali.setForeground(new java.awt.Color(0, 0, 0));
        valWali.setText(": Manusia 12");
        valWali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valWaliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valWaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valWaliMouseExited(evt);
            }
        });

        pnlInfoUtamaTitle.setBackground(new java.awt.Color(15, 98, 230));
        pnlInfoUtamaTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblInfoUtama.setBackground(new java.awt.Color(255, 255, 255));
        lblInfoUtama.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        lblInfoUtama.setForeground(new java.awt.Color(248, 237, 237));
        lblInfoUtama.setText("  Informasi Utama");

        javax.swing.GroupLayout pnlInfoUtamaTitleLayout = new javax.swing.GroupLayout(pnlInfoUtamaTitle);
        pnlInfoUtamaTitle.setLayout(pnlInfoUtamaTitleLayout);
        pnlInfoUtamaTitleLayout.setHorizontalGroup(
            pnlInfoUtamaTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlInfoUtamaTitleLayout.setVerticalGroup(
            pnlInfoUtamaTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlInfoUtamaLayout = new javax.swing.GroupLayout(pnlInfoUtama);
        pnlInfoUtama.setLayout(pnlInfoUtamaLayout);
        pnlInfoUtamaLayout.setHorizontalGroup(
            pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblNominal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoHp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWali, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valWali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNominal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(valEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pnlInfoUtamaTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlInfoUtamaLayout.setVerticalGroup(
            pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoUtamaLayout.createSequentialGroup()
                .addComponent(pnlInfoUtamaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNis, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblWali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valWali, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlMain.add(pnlInfoUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 430, 230));

        lineTop.setBackground(new java.awt.Color(26, 23, 23));
        lineTop.setForeground(new java.awt.Color(20, 18, 18));
        pnlMain.add(lineTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 900, 10));

        pnlInfoKeamanan.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoKeamanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(246, 46, 46), 2));

        pnlInfoKemananTitle.setBackground(new java.awt.Color(246, 46, 46));
        pnlInfoKemananTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblInfoKeamanan.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        lblInfoKeamanan.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoKeamanan.setText("  Informasi Keamanan");

        javax.swing.GroupLayout pnlInfoKemananTitleLayout = new javax.swing.GroupLayout(pnlInfoKemananTitle);
        pnlInfoKemananTitle.setLayout(pnlInfoKemananTitleLayout);
        pnlInfoKemananTitleLayout.setHorizontalGroup(
            pnlInfoKemananTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoKeamanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlInfoKemananTitleLayout.setVerticalGroup(
            pnlInfoKemananTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoKeamanan, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setText("Password");

        lblKekuatan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKekuatan.setForeground(new java.awt.Color(0, 0, 0));
        lblKekuatan.setText("Kekuatan");

        valPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPassword.setForeground(new java.awt.Color(0, 0, 0));
        valPassword.setText(": •••");
        valPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valPasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valPasswordMouseExited(evt);
            }
        });

        valKekuatan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKekuatan.setForeground(new java.awt.Color(0, 0, 0));
        valKekuatan.setText(": Sangat Rendah");
        valKekuatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valKekuatanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valKekuatanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valKekuatanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoKeamananLayout = new javax.swing.GroupLayout(pnlInfoKeamanan);
        pnlInfoKeamanan.setLayout(pnlInfoKeamananLayout);
        pnlInfoKeamananLayout.setHorizontalGroup(
            pnlInfoKeamananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfoKemananTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoKeamananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoKeamananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoKeamananLayout.createSequentialGroup()
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoKeamananLayout.createSequentialGroup()
                        .addComponent(lblKekuatan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(valKekuatan, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInfoKeamananLayout.setVerticalGroup(
            pnlInfoKeamananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoKeamananLayout.createSequentialGroup()
                .addComponent(pnlInfoKemananTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKeamananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKeamananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valKekuatan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKekuatan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoKeamanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 430, 130));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 900, 10));

        btnLogout.setBackground(new java.awt.Color(235, 29, 29));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-app-logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        pnlMain.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 590, 100, 30));

        btnEdit.setBackground(new java.awt.Color(27, 186, 47));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-editakun.png"))); // NOI18N
        btnEdit.setText("Edit Akun");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
        });
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlMain.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 590, 110, 30));

        pnlInfoPribadi.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoPribadi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 2));

        pnlInfoPribadiTitle.setBackground(new java.awt.Color(5, 170, 57));
        pnlInfoPribadiTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblInfoPribadi.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        lblInfoPribadi.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoPribadi.setText("  Informasi Pribadi");

        javax.swing.GroupLayout pnlInfoPribadiTitleLayout = new javax.swing.GroupLayout(pnlInfoPribadiTitle);
        pnlInfoPribadiTitle.setLayout(pnlInfoPribadiTitleLayout);
        pnlInfoPribadiTitleLayout.setHorizontalGroup(
            pnlInfoPribadiTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoPribadi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlInfoPribadiTitleLayout.setVerticalGroup(
            pnlInfoPribadiTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoPribadi, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        lblInfoFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNama.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNama.setForeground(new java.awt.Color(0, 0, 0));
        lblNama.setText("Nama");

        valNama.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Achmad Baihaqi");
        valNama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNamaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNamaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNamaMouseExited(evt);
            }
        });

        lblGender.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblGender.setForeground(new java.awt.Color(0, 0, 0));
        lblGender.setText("Jenis Kelamin");

        valGender.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valGender.setForeground(new java.awt.Color(0, 0, 0));
        valGender.setText(": Laki-Laki");
        valGender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valGenderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valGenderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valGenderMouseExited(evt);
            }
        });

        lblTanggalLhr.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTanggalLhr.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggalLhr.setText("Tanggal Lahir");

        valTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));
        valTanggalLahir.setText(": 04 Agustus 2003");
        valTanggalLahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valTanggalLahirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valTanggalLahirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valTanggalLahirMouseExited(evt);
            }
        });

        lblTempatLhr.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTempatLhr.setForeground(new java.awt.Color(0, 0, 0));
        lblTempatLhr.setText("Tempat Lahir");

        valTempatLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTempatLahir.setForeground(new java.awt.Color(0, 0, 0));
        valTempatLahir.setText(": Jombang");
        valTempatLahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valTempatLahirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valTempatLahirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valTempatLahirMouseExited(evt);
            }
        });

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat.setText(": Jombang, Jawa Timur");
        valAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valAlamatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valAlamatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valAlamatMouseExited(evt);
            }
        });

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        valKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII RPL 1");
        valKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valKelasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valKelasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valKelasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoPribadiLayout = new javax.swing.GroupLayout(pnlInfoPribadi);
        pnlInfoPribadi.setLayout(pnlInfoPribadiLayout);
        pnlInfoPribadiLayout.setHorizontalGroup(
            pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfoPribadiTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblTanggalLhr, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblTempatLhr, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valTempatLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                    .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                        .addComponent(lblInfoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInfoPribadiLayout.setVerticalGroup(
            pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoPribadiLayout.createSequentialGroup()
                .addComponent(pnlInfoPribadiTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInfoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valNama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valGender, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTempatLhr, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTanggalLhr, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPribadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoPribadi, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 440, 380));

        lblSekolahBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSekolahBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/logo-smkn1kts_30x37.png"))); // NOI18N
        lblSekolahBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlMain.add(lblSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, 50, 60));

        lblNamaSekolahBottom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaSekolahBottom.setForeground(new java.awt.Color(12, 64, 245));
        lblNamaSekolahBottom.setText("SMK Negeri 1 Kertosono");
        pnlMain.add(lblNamaSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, 230, -1));

        lblNamaAplikasiBottom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaAplikasiBottom.setForeground(new java.awt.Color(231, 38, 38));
        lblNamaAplikasiBottom.setText("Sistem Aplikasi Pembayaran SPP");
        pnlMain.add(lblNamaAplikasiBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 600, 260, 20));

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
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        siswa.closeConnection();
        kls.closeConnection();
        tr.closeConnection();
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
        // no event
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
        // no event
    }//GEN-LAST:event_lblPhotoProfileMouseClicked

    private void lblPhotoProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseEntered
        this.lblPhotoProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblPhotoProfileMouseEntered

    private void lblPhotoProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseExited
        this.lblPhotoProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblPhotoProfileMouseExited

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

    private void btnInfoAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAkunActionPerformed
        // no event
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

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Audio.play(Audio.SOUND_INFO);
        new EditAkunSiswa(this, true, this.siswa.getCurrentLogin()).setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Audio.play(Audio.SOUND_WARNING);
        new ConfirmLogout(this, true).setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        this.btnLogout.setIcon(Gambar.getIcon("ic-app-logout-entered.png"));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        this.btnLogout.setIcon(Gambar.getIcon("ic-app-logout.png"));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void valNisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNisMouseClicked
        Message.showInformation(this, "NIS : " + currentLogin);
    }//GEN-LAST:event_valNisMouseClicked

    private void valNisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNisMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNis.setText("<html><p style=\" color:rgb(15,98,230)\">:&nbsp;"+currentLogin+"</p></html>");
    }//GEN-LAST:event_valNisMouseEntered

    private void valNisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNisMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNis.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+currentLogin+"</p></html>");
    }//GEN-LAST:event_valNisMouseExited

    private void valNoHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseClicked
        Message.showInformation(this, "Nomor HP : " + no_hp);
    }//GEN-LAST:event_valNoHpMouseClicked

    private void valNoHpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNoHp.setText("<html><p style=\" color:rgb(15,98,230)\">:&nbsp;"+no_hp+"</p></html>");
    }//GEN-LAST:event_valNoHpMouseEntered

    private void valNoHpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNoHp.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+no_hp+"</p></html>");
    }//GEN-LAST:event_valNoHpMouseExited

    private void valEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valEmailMouseClicked
        Message.showInformation(this, "Email : " + email);
    }//GEN-LAST:event_valEmailMouseClicked

    private void valEmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valEmailMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valEmail.setText("<html><p style=\" color:rgb(15,98,230)\">:&nbsp;"+email+"</p></html>");
    }//GEN-LAST:event_valEmailMouseEntered

    private void valEmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valEmailMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valEmail.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+email+"</p></html>");
    }//GEN-LAST:event_valEmailMouseExited

    private void valNominalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNominalMouseClicked
        Message.showInformation(this, "Nominal SPP : " + nominal);
    }//GEN-LAST:event_valNominalMouseClicked

    private void valNominalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNominalMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNominal.setText("<html><p style=\" color:rgb(15,98,230)\">:&nbsp;"+nominal+"</p></html>");
    }//GEN-LAST:event_valNominalMouseEntered

    private void valNominalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNominalMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNominal.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+nominal+"</p></html>");
    }//GEN-LAST:event_valNominalMouseExited

    private void valWaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valWaliMouseClicked
        Message.showInformation(this, "Nama Wali : " + wali);
    }//GEN-LAST:event_valWaliMouseClicked

    private void valWaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valWaliMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valWali.setText("<html><p style=\" color:rgb(15,98,230)\">:&nbsp;"+wali+"</p></html>");
    }//GEN-LAST:event_valWaliMouseEntered

    private void valWaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valWaliMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valWali.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+wali+"</p></html>");
    }//GEN-LAST:event_valWaliMouseExited

    private void valPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valPasswordMouseClicked
        Message.showInformation(this, "Password : " + pass);
    }//GEN-LAST:event_valPasswordMouseClicked

    private void valPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valPasswordMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valPassword.setText("<html><p style=\" color:rgb(246,46,46)\">:&nbsp;"+pass+"</p></html>");
    }//GEN-LAST:event_valPasswordMouseEntered

    private void valPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valPasswordMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valPassword.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+pass+"</p></html>");
    }//GEN-LAST:event_valPasswordMouseExited

    private void valKekuatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKekuatanMouseClicked
        Message.showInformation(this, "Kekuatan : " + passStrength);
    }//GEN-LAST:event_valKekuatanMouseClicked

    private void valKekuatanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKekuatanMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valKekuatan.setText("<html><p style=\" color:rgb(246,46,46)\">:&nbsp;"+passStrength+"</p></html>");
    }//GEN-LAST:event_valKekuatanMouseEntered

    private void valKekuatanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKekuatanMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valKekuatan.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+passStrength+"</p></html>");
    }//GEN-LAST:event_valKekuatanMouseExited

    private void valNamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNamaMouseClicked
        Message.showInformation(this, "Nama : " + name);
    }//GEN-LAST:event_valNamaMouseClicked

    private void valNamaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNamaMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNama.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+name+"</p></html>");
    }//GEN-LAST:event_valNamaMouseEntered

    private void valNamaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNamaMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNama.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+name+"</p></html>");
    }//GEN-LAST:event_valNamaMouseExited

    private void valGenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valGenderMouseClicked
        Message.showInformation(this, "Jenis Kelamin : " + gender);
    }//GEN-LAST:event_valGenderMouseClicked

    private void valGenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valGenderMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valGender.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+gender+"</p></html>");
    }//GEN-LAST:event_valGenderMouseEntered

    private void valGenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valGenderMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valGender.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+gender+"</p></html>");
    }//GEN-LAST:event_valGenderMouseExited

    private void valTempatLahirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTempatLahirMouseClicked
        Message.showInformation(this, "Tempat Lahir : " + tmpLahir);
    }//GEN-LAST:event_valTempatLahirMouseClicked

    private void valTempatLahirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTempatLahirMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valTempatLahir.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+tmpLahir+"</p></html>");
    }//GEN-LAST:event_valTempatLahirMouseEntered

    private void valTempatLahirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTempatLahirMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valTempatLahir.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+tmpLahir+"</p></html>");
    }//GEN-LAST:event_valTempatLahirMouseExited

    private void valTanggalLahirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTanggalLahirMouseClicked
        Message.showInformation(this, "Tanggal Lahir : " + tglLahir);
    }//GEN-LAST:event_valTanggalLahirMouseClicked

    private void valTanggalLahirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTanggalLahirMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valTanggalLahir.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+tglLahir+"</p></html>");
    }//GEN-LAST:event_valTanggalLahirMouseEntered

    private void valTanggalLahirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valTanggalLahirMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valTanggalLahir.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+tglLahir+"</p></html>");
    }//GEN-LAST:event_valTanggalLahirMouseExited

    private void valAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valAlamatMouseClicked
        Message.showInformation(this, "Alamat : " + alamat);
    }//GEN-LAST:event_valAlamatMouseClicked

    private void valAlamatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valAlamatMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valAlamat.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+alamat+"</p></html>");
    }//GEN-LAST:event_valAlamatMouseEntered

    private void valAlamatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valAlamatMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valAlamat.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+alamat+"</p></html>");
    }//GEN-LAST:event_valAlamatMouseExited

    private void valKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKelasMouseClicked
        Message.showInformation(this, "Kelas : " + kelas);
    }//GEN-LAST:event_valKelasMouseClicked

    private void valKelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKelasMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valKelas.setText("<html><p style=\" color:rgb(5,170,57)\">:&nbsp;"+kelas+"</p></html>");
    }//GEN-LAST:event_valKelasMouseEntered

    private void valKelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valKelasMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valKelas.setText("<html><p style=\" color:rgb(0,0,0)\">:&nbsp;"+kelas+"</p></html>");
    }//GEN-LAST:event_valKelasMouseExited

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
            java.util.logging.Logger.getLogger(InformasiAkunSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InformasiAkunSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHistori;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblInfoFoto;
    private javax.swing.JLabel lblInfoKeamanan;
    private javax.swing.JLabel lblInfoPribadi;
    private javax.swing.JLabel lblInfoUtama;
    private javax.swing.JLabel lblKekuatan;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaAplikasiBottom;
    private javax.swing.JLabel lblNamaSekolahBottom;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSekolahBottom;
    private javax.swing.JLabel lblSiswa;
    private javax.swing.JLabel lblTanggalLhr;
    private javax.swing.JLabel lblTempatLhr;
    private javax.swing.JLabel lblWali;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfoKeamanan;
    private javax.swing.JPanel pnlInfoKemananTitle;
    private javax.swing.JPanel pnlInfoPribadi;
    private javax.swing.JPanel pnlInfoPribadiTitle;
    private javax.swing.JPanel pnlInfoUtama;
    private javax.swing.JPanel pnlInfoUtamaTitle;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valEmail;
    private javax.swing.JLabel valGender;
    private javax.swing.JLabel valKekuatan;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valNominal;
    private javax.swing.JLabel valPassword;
    private javax.swing.JLabel valTanggalLahir;
    private javax.swing.JLabel valTempatLahir;
    private javax.swing.JLabel valWali;
    // End of variables declaration//GEN-END:variables
}
