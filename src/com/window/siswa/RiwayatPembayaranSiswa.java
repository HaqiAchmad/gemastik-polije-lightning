package com.window.siswa;

import com.data.app.Application;
import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.Transaksi;
import com.manage.Internet;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import com.window.all.CetakLaporanSpp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-09
 */
public class RiwayatPembayaranSiswa extends javax.swing.JFrame {
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();;
    private final Users.LevelPetugas petugas = Users.levelPetugas();;
    private final Kelas kls = new Kelas();;
    private final Transaksi tr = new Transaksi();
    private final Text txt = new Text();
    
    private final String name, levelKelas;
    private String id_selected, id_petugas, nama_petugas, nohp_petugas, bln_bayar, thn_bayar, tgl_bayar, jml_bayar, nominal;
    private int x, y;
    
    public RiwayatPembayaranSiswa() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolahBottom.setIcon(Gambar.getBottomIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblNamaSekolahBottom.setText(Application.getCompany());
        this.lblNamaAplikasiBottom.setText(Application.getNama());
        this.lblVersion.setText("Versi " + Application.getVersi());    
        this.lblCopyright.setText(Application.getRightReserved());

        // mendapatkan dan menampilkan data-data dari riwayat pembayaran
        this.name = this.txt.toCapitalize(this.siswa.getNama());
        this.levelKelas = this.kls.getLevelKelas(this.siswa.getIdKelas());
        this.lblNamaUser.setText("<html><p>" + this.name + "</p></html>");
        this.lblSiswa.setText("<html><p>Siswa Kelas " + this.levelKelas + "</p></html>");
        this.lblPhotoProfile.setIcon(this.siswa.getPhotoProfile());

        // mengatur ui pada button
        this.btnDashboard.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoAkun.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHistori.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnClose.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinimaze.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLihat.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnCetakLaporan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLaporkan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.btnHistori.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnDashboard, this.btnInfoAkun, this.btnTentangApp
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
        
        JLabel[] lbls = new JLabel[]{
            this.valBulanBayar, this.valIdPembayaran, this.valIdPetugas, this.valNamaPetugas, this.valNamaSiswa,
            this.valNoHp, this.valJumlahBayar, this.valNominal, this.valTahunBayar, this.valTanggalBayar
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
                    lbl.setForeground(new Color(15,98,230));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0,0,0));
                }
            });
        }
        
        this.updateTabel();
    }
    
    private int getJumlahData() {
        switch (this.inpJumlahData.getSelectedIndex()) {
            case 0: return 12;
            case 1: return 25;
            case 2: return 50;
            case 3: return this.siswa.getJumlahData("pembayaran", "WHERE nis = " + this.siswa.getCurrentLogin());
            default: return 0;
        }
    }
    
    private Object[][] getData() {
        try {
            int rows = 0;
            // membuat query 
            final String sql = "SELECT id_pembayaran, bln_bayar, thn_bayar, jml_bayar "
                                + "FROM pembayaran WHERE nis = " + this.siswa.getCurrentLogin() + " "
                                + "ORDER BY tgl_bayar DESC "
                                + "LIMIT 0," + this.getJumlahData();
            // mendefinisikan object berdasarkan input jumlah data
            Object[][] obj = new Object[this.getJumlahData()][4];
            
            // mengeksekusi query
            this.tr.res = this.tr.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while (this.tr.res.next()) {
                // menyimpan data ke array object
                obj[rows][0] = this.tr.res.getString("id_pembayaran");
                obj[rows][1] = this.tr.res.getString("bln_bayar");
                obj[rows][2] = this.tr.res.getString("thn_bayar");
                obj[rows][3] = this.txt.toMoneyCase(this.tr.res.getString("jml_bayar"));
                rows++;
            }
            return obj;
        }
        catch (SQLException ex) {
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabel() {
        this.tabelData.setModel(
                new DefaultTableModel(this.getData(), 
                        new String[] 
                        { "ID Pembayaran", "Bulan Bayar", "Tahun Bayar", "Jumlah Bayar" }
                ){
            boolean[] canEdit = { false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
    }
    
    private void showData() {
        // mendapatkan data-data pembayaran
        this.id_petugas = this.tr.getData("pembayaran", "id_petugas", "WHERE id_pembayaran = '" + this.id_selected + "'");
        this.nama_petugas = this.txt.toCapitalize(this.petugas.getNama(this.id_petugas));
        this.nohp_petugas = this.txt.toTelephoneCase(this.petugas.getNoHp(this.id_petugas));
        this.bln_bayar = this.tr.getData("pembayaran", "bln_bayar", "WHERE id_pembayaran = '" + this.id_selected + "'");
        this.thn_bayar = this.tr.getData("pembayaran", "thn_bayar", "WHERE id_pembayaran = '" + this.id_selected + "'");
        this.tgl_bayar = this.txt.toDateCase(this.tr.getData("pembayaran", "tgl_bayar", "WHERE id_pembayaran = '" + this.id_selected + "'"));
        this.jml_bayar = this.txt.toMoneyCase(this.tr.getData("pembayaran", "jml_bayar", "WHERE id_pembayaran = '" + this.id_selected + "'"));
        this.nominal = this.txt.toMoneyCase(Integer.toString(this.tr.getNominalSpp(Integer.parseInt(this.siswa.getIdSpp()))));
        
        // menampilkan data-data pembayan
        this.valIdPembayaran.setText("<html><p>:&nbsp;" + this.id_selected + "</p></html>");
        this.valIdPetugas.setText("<html><p>:&nbsp;" + this.id_petugas + "</p></html>");
        this.valNamaPetugas.setText("<html><p>:&nbsp;" + this.nama_petugas + "</p></html>");
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;" + this.nohp_petugas + "</p></html>");
        this.valNamaSiswa.setText("<html><p>:&nbsp;" + this.name + "</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;" + this.bln_bayar + "</p></html>");
        this.valTahunBayar.setText("<html><p>:&nbsp;" + this.thn_bayar + "</p></html>");
        this.valTanggalBayar.setText("<html><p>:&nbsp;" + this.tgl_bayar + "</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;" + this.jml_bayar + "</p></html>");
        this.valNominal.setText("<html><p>:&nbsp;" + this.nominal + "</p></html>");
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
        lineTop = new javax.swing.JSeparator();
        lblDashboard = new javax.swing.JLabel();
        pnlInfoData = new javax.swing.JPanel();
        pnlTitleInfo = new javax.swing.JPanel();
        lblTitleInfo = new javax.swing.JLabel();
        lblIdPembayaran = new javax.swing.JLabel();
        lblIdPetugas = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        lblNamaPetugas = new javax.swing.JLabel();
        lblBulanBayar = new javax.swing.JLabel();
        lblNamaSiswa = new javax.swing.JLabel();
        valIdPembayaran = new javax.swing.JLabel();
        valIdPetugas = new javax.swing.JLabel();
        valNamaPetugas = new javax.swing.JLabel();
        valNoHp = new javax.swing.JLabel();
        valNamaSiswa = new javax.swing.JLabel();
        valBulanBayar = new javax.swing.JLabel();
        lblNominal = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        valJumlahBayar = new javax.swing.JLabel();
        valNominal = new javax.swing.JLabel();
        lblTanggalBayar = new javax.swing.JLabel();
        lblTahunBayar = new javax.swing.JLabel();
        valTahunBayar = new javax.swing.JLabel();
        lineInfo = new javax.swing.JSeparator();
        valTanggalBayar = new javax.swing.JLabel();
        btnCetakLaporan = new javax.swing.JButton();
        btnLaporkan = new javax.swing.JButton();
        lblTahunAjaran = new javax.swing.JLabel();
        lineCenter = new javax.swing.JSeparator();
        lineBottom = new javax.swing.JSeparator();
        lblVersion = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        inpJumlahData = new javax.swing.JComboBox();
        btnLihat = new javax.swing.JButton();
        lblSekolahBottom = new javax.swing.JLabel();
        lblNamaSekolahBottom = new javax.swing.JLabel();
        lblNamaAplikasiBottom = new javax.swing.JLabel();
        lblShowData = new javax.swing.JLabel();
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(lblSekolah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMain.add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 1100, 50));

        lineTop.setBackground(new java.awt.Color(0, 0, 0));
        lineTop.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 1010, 10));

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-riwayat-logo.png"))); // NOI18N
        lblDashboard.setText("Riwayat Pembayaran");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        pnlInfoData.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 98, 230), 2));

        pnlTitleInfo.setBackground(new java.awt.Color(15, 98, 230));
        pnlTitleInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitleInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleInfo.setText("Informasi Pembayaran");

        javax.swing.GroupLayout pnlTitleInfoLayout = new javax.swing.GroupLayout(pnlTitleInfo);
        pnlTitleInfo.setLayout(pnlTitleInfoLayout);
        pnlTitleInfoLayout.setHorizontalGroup(
            pnlTitleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
        );
        pnlTitleInfoLayout.setVerticalGroup(
            pnlTitleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPembayaran.setText("ID Pembayaran");

        lblIdPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblIdPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPetugas.setText("ID Petugas");

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP Petugas");

        lblNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPetugas.setText("Nama Petugas");

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        lblNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSiswa.setText("Nama Siswa");

        valIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        valIdPembayaran.setText(": TR000014");

        valIdPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valIdPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valIdPetugas.setText(": 12");

        valNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPetugas.setText(": Levi Ackerman");

        valNoHp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNoHp.setForeground(new java.awt.Color(0, 0, 0));
        valNoHp.setText(": +62 856-5586-4624");
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

        valNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        valNamaSiswa.setText(": Achmad Baihaqi");

        valBulanBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        valBulanBayar.setText(": Juli");

        lblNominal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(0, 0, 0));
        lblNominal.setText("Nominal SPP");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        valJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        valJumlahBayar.setText(": Rp. 140.000.00");

        valNominal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNominal.setForeground(new java.awt.Color(0, 0, 0));
        valNominal.setText(": Rp.140.000.00");

        lblTanggalBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTanggalBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggalBayar.setText("Tanggal Bayar");

        lblTahunBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunBayar.setText("Tahun Bayar");

        valTahunBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        valTahunBayar.setText(": 2021");

        lineInfo.setBackground(new java.awt.Color(15, 98, 230));
        lineInfo.setForeground(new java.awt.Color(15, 98, 230));

        valTanggalBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTanggalBayar.setForeground(new java.awt.Color(0, 0, 0));
        valTanggalBayar.setText(": 09 Juli 2021");

        btnCetakLaporan.setBackground(new java.awt.Color(41, 180, 50));
        btnCetakLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnCetakLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-laporan-cetak.png"))); // NOI18N
        btnCetakLaporan.setText("Cetak Laporan");
        btnCetakLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCetakLaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCetakLaporanMouseExited(evt);
            }
        });
        btnCetakLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakLaporanActionPerformed(evt);
            }
        });

        btnLaporkan.setBackground(new java.awt.Color(220, 41, 41));
        btnLaporkan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-error.png"))); // NOI18N
        btnLaporkan.setText("Laporkan Kesalahan");
        btnLaporkan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLaporkanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLaporkanMouseExited(evt);
            }
        });
        btnLaporkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoDataLayout = new javax.swing.GroupLayout(pnlInfoData);
        pnlInfoData.setLayout(pnlInfoDataLayout);
        pnlInfoDataLayout.setHorizontalGroup(
            pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblIdPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valIdPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(lblNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valTanggalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(lblTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valTahunBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                                .addComponent(btnCetakLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLaporkan)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlInfoDataLayout.setVerticalGroup(
            pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                .addComponent(pnlTitleInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPembayaran)
                    .addComponent(valIdPembayaran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPetugas)
                    .addComponent(valIdPetugas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaPetugas)
                    .addComponent(valNamaPetugas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoHp)
                    .addComponent(valNoHp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valNamaSiswa)
                    .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBulanBayar)
                    .addComponent(valBulanBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunBayar)
                    .addComponent(valTahunBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggalBayar)
                    .addComponent(valTanggalBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valJumlahBayar)
                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNominal)
                    .addComponent(valNominal))
                .addGap(24, 24, 24)
                .addComponent(lineInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetakLaporan)
                    .addComponent(btnLaporkan))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 510, 450));

        lblTahunAjaran.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTahunAjaran.setForeground(new java.awt.Color(255, 67, 4));
        lblTahunAjaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTahunAjaran.setText("Tampilkan Riwayat  : ");
        pnlMain.add(lblTahunAjaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 150, 30));

        lineCenter.setBackground(new java.awt.Color(0, 0, 0));
        lineCenter.setForeground(new java.awt.Color(0, 0, 0));
        lineCenter.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlMain.add(lineCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 10, 470));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 620, 1010, 10));

        lblVersion.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(0, 0, 0));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersion.setText("Version 1.0.0");
        pnlMain.add(lblVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 640, 370, -1));

        lblCopyright.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(0, 0, 0));
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2021. Achmad Baihaqi. All Rights Reserved.");
        pnlMain.add(lblCopyright, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, 390, -1));

        tabelData.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"TR023401", "Juli", "2020", "Rp. 180.000.00"},
                {"TR023589", "Agustus", "2020", "Rp. 180.000.00"},
                {"TR023712", "September", "2020", "Rp. 180.000.00"},
                {"TR023901", "Oktober", "2020", "Rp. 180.000.00"},
                {"TR024301", "November", "2020", "Rp. 180.000.00"},
                {"TR024571", "Desember", "2020", "Rp. 180.000.00"},
                {"TR024908", "Januari", "2021", "Rp. 180.000.00"},
                {"TR025145", "Februari", "2021", "Rp. 180.000.00"},
                {"TR025471", "Maret", "2021", "Rp. 180.000.00"},
                {"TR025706", "April", "2021", "Rp. 180.000.00"}
            },
            new String [] {
                "ID Pembayaran", "Bulan Bayar", "Tahun Bayar", "Jumlah Bayar"
            }
        ));
        tabelData.setGridColor(new java.awt.Color(0, 0, 0));
        tabelData.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelData.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelData.getTableHeader().setReorderingAllowed(false);
        tabelData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataMouseClicked(evt);
            }
        });
        tabelData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelDataKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelData);

        pnlMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 440, 400));

        inpJumlahData.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpJumlahData.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlahData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 Riwayat Terakhir", "25 Riwayat Terakhir", "50 Riwayat Terakhir", "Semua Riwayat" }));
        pnlMain.add(inpJumlahData, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 170, 30));

        btnLihat.setBackground(new java.awt.Color(34, 119, 237));
        btnLihat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLihat.setForeground(new java.awt.Color(255, 255, 255));
        btnLihat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-showdata.png"))); // NOI18N
        btnLihat.setText("Lihat");
        btnLihat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLihatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLihatMouseExited(evt);
            }
        });
        btnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatActionPerformed(evt);
            }
        });
        pnlMain.add(btnLihat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 130, 100, 30));

        lblSekolahBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSekolahBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/logo-smkn1kts_30x37.png"))); // NOI18N
        pnlMain.add(lblSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 630, 40, 60));

        lblNamaSekolahBottom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaSekolahBottom.setForeground(new java.awt.Color(231, 38, 38));
        lblNamaSekolahBottom.setText("SMK Negeri 1 Kertosono");
        lblNamaSekolahBottom.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlMain.add(lblNamaSekolahBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 630, 240, 30));

        lblNamaAplikasiBottom.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblNamaAplikasiBottom.setForeground(new java.awt.Color(16, 81, 200));
        lblNamaAplikasiBottom.setText("Sistem Aplikasi Pembayaran SPP");
        lblNamaAplikasiBottom.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlMain.add(lblNamaAplikasiBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 260, 20));

        lblShowData.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblShowData.setForeground(new java.awt.Color(0, 0, 0));
        lblShowData.setText("Menampilkan 12 data riwayat terakhir pembayaran SPP.");
        pnlMain.add(lblShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 590, 440, -1));

        lblBgImage.setBackground(new java.awt.Color(41, 52, 71));
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.siswa.closeConnection();
        this.petugas.closeConnection();
        this.tr.closeConnection();
        this.kls.closeConnection();
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
        this.lblNamaUser.setText("<html><p style=\"text-decoration:underline;\">"+name+"</p></html>");
    }//GEN-LAST:event_lblNamaUserMouseEntered

    private void lblNamaUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNamaUserMouseExited
        this.lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblNamaUser.setText("<html><p style=\"text-decoration:none;\">"+name+"</p></html>");
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
        // no event
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

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        this.setCursor(new Cursor(3));
        this.id_selected = this.tabelData.getValueAt(this.tabelData.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tabelDataMouseClicked

    private void tabelDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataKeyPressed
        this.setCursor(new Cursor(3));
        if (evt.getKeyCode() == 38) {
            this.id_selected = this.tabelData.getValueAt(this.tabelData.getSelectedRow() - 1, 0).toString();
            this.showData();
        }
        else if (evt.getKeyCode() == 40) {
            this.id_selected = this.tabelData.getValueAt(this.tabelData.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tabelDataKeyPressed

    private void btnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatActionPerformed
        // menampilkan data pembayaran
        this.updateTabel();
        this.lblShowData.setText("Menampilkan " + this.getJumlahData() + " data riwayat terakhir pembayaran SPP.");
    }//GEN-LAST:event_btnLihatActionPerformed

    private void btnLihatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatMouseEntered
        this.btnLihat.setIcon(Gambar.getIcon("ic-showdata-entered.png"));
    }//GEN-LAST:event_btnLihatMouseEntered

    private void btnLihatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatMouseExited
        this.btnLihat.setIcon(Gambar.getIcon("ic-showdata.png"));
    }//GEN-LAST:event_btnLihatMouseExited

    private void btnCetakLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakLaporanActionPerformed
        if(this.tabelData.getSelectedRow() > -1){
            Audio.play(Audio.SOUND_INFO);
            new CetakLaporanSpp(this, true, this.id_selected).setVisible(true);
        }else{
            Message.showWarning(this, "Tidak ada data yang dipilih!");
        } 
    }//GEN-LAST:event_btnCetakLaporanActionPerformed

    private void btnCetakLaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakLaporanMouseEntered
        this.btnCetakLaporan.setIcon(Gambar.getIcon("ic-laporan-cetak-entered.png"));
    }//GEN-LAST:event_btnCetakLaporanMouseEntered

    private void btnCetakLaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakLaporanMouseExited
        this.btnCetakLaporan.setIcon(Gambar.getIcon("ic-laporan-cetak.png"));
    }//GEN-LAST:event_btnCetakLaporanMouseExited

    private void btnLaporkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporkanActionPerformed
        if(this.tabelData.getSelectedRow() > -1){
            Audio.play(Audio.SOUND_INFO);
            new com.window.pembayaran.LaporkanPembayaran(this, true, this.id_selected).setVisible(true);
        }else{
            Message.showWarning(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_btnLaporkanActionPerformed

    private void btnLaporkanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporkanMouseEntered
        this.btnLaporkan.setIcon(Gambar.getIcon("ic-pembayaran-error-entered.png"));
    }//GEN-LAST:event_btnLaporkanMouseEntered

    private void btnLaporkanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporkanMouseExited
        this.btnLaporkan.setIcon(Gambar.getIcon("ic-pembayaran-error.png"));
    }//GEN-LAST:event_btnLaporkanMouseExited

    private void valNoHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseClicked
        final Internet net = new Internet();
        final String nomor = this.nohp_petugas.substring(1).replaceAll(" ", "").replaceAll("-", "");
        if (net.isConnectInternet()) {
            try {
                net.openLink("https://wa.me/" + nomor);
            }
            catch (IOException | URISyntaxException ex) {
                Message.showException(this, ex, true);
            }
        }
        else {
            Message.showWarning(this, "Tidak terhubung ke Internet!", true);
        }
    }//GEN-LAST:event_valNoHpMouseClicked

    private void valNoHpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseEntered
        this.setCursor(new Cursor(12));
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(15,98,230);\">:&nbsp;" + this.nohp_petugas + "</p></html>");
    }//GEN-LAST:event_valNoHpMouseEntered

    private void valNoHpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseExited
        this.setCursor(new Cursor(0));
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;" + this.nohp_petugas + "</p></html>");
    }//GEN-LAST:event_valNoHpMouseExited

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
            java.util.logging.Logger.getLogger(RiwayatPembayaranSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RiwayatPembayaranSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetakLaporan;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnHistori;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnLaporkan;
    private javax.swing.JButton btnLihat;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JComboBox inpJumlahData;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblIdPembayaran;
    private javax.swing.JLabel lblIdPetugas;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblNamaAplikasiBottom;
    private javax.swing.JLabel lblNamaPetugas;
    private javax.swing.JLabel lblNamaSekolahBottom;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSekolahBottom;
    private javax.swing.JLabel lblShowData;
    private javax.swing.JLabel lblSiswa;
    private javax.swing.JLabel lblTahunAjaran;
    private javax.swing.JLabel lblTahunBayar;
    private javax.swing.JLabel lblTanggalBayar;
    private javax.swing.JLabel lblTitleInfo;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineCenter;
    private javax.swing.JSeparator lineInfo;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfoData;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleInfo;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valBulanBayar;
    private javax.swing.JLabel valIdPembayaran;
    private javax.swing.JLabel valIdPetugas;
    private javax.swing.JLabel valJumlahBayar;
    private javax.swing.JLabel valNamaPetugas;
    private javax.swing.JLabel valNamaSiswa;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valNominal;
    private javax.swing.JLabel valTahunBayar;
    private javax.swing.JLabel valTanggalBayar;
    // End of variables declaration//GEN-END:variables
}
