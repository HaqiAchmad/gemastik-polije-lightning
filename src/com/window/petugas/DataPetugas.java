package com.window.petugas;

import com.data.app.Application;
import com.data.app.Log;
import com.data.db.Database;
import com.data.db.DatabaseTables;
import com.manage.Internet;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.window.admin.EditDataPetugas;
import com.sun.glass.events.KeyEvent;
import com.users.UserPhotoSize;
import com.users.Users;
import com.window.admin.HapusData;
import com.window.admin.TambahDataPetugas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-09
 */
public class DataPetugas extends javax.swing.JFrame {
    
    private final Users.LevelPetugas petugas = Users.levelPetugas(), level = Users.levelPetugas();
    private final Text txt = new Text();

    private final String name;
    private String keyword ="", idSelected, namaPetugas, gender, ttl, alamat, noHp, email, levelPetugas, transaksi, lastOn;
    private int x, y;
    
    public DataPetugas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        idSelected = "11";
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblVersion.setText("Versi " + Application.getVersi());
        this.lblCopyright.setText(Application.getRightReserved());
        
        // mendapatkan dan menampilkan data dari user
        name = txt.toCapitalize(petugas.getNama());
        this.lblNamaUser.setText("<html><p>"+name+"</p></html>");
        this.lblTotalData.setText("Menampilkan "+ (petugas.getTotalPetugas()+petugas.getTotalAdmin()) +" data petugas.");
        this.lblPhotoProfile.setIcon(petugas.getPhotoProfile());
        
        // megatur ui pada button
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
        this.btnTambah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.btnDataPetugas.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnDashboard, this.btnInfoAkun, this.btnDataSiswa, this.btnDataKelas, 
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
        
        JLabel[] lbls = new JLabel[]{
            this.valId, this.valNama, this.valGender, this.valTtl, this.valAlamat, 
            this.valNoHp, this.valEmail, this.valLevel, this.valTransaksi, this.valLastOn
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
        this.showData();
    }
    
    private Object[][] getData(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_petugas, nama_petugas, gender FROM petugas " + keyword, id;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[petugas.getJumlahData(Database.PETUGAS, keyword)][4];
            // mengeksekusi query
            petugas.res = petugas.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(petugas.res.next()){
                id =  petugas.res.getString("id_petugas");
                // jika id petugas adalah 1 maka data tidak akan ditampilkan
                if(!id.equals("1")){
                    // menyimpan data dari tabel ke object
                    obj[rows][0] = id;
                    obj[rows][1] = petugas.res.getString("nama_petugas");
                    obj[rows][2] = txt.getGenderName(petugas.res.getString("gender"));
                    obj[rows][3] = txt.toCapitalize(level.getLevel(id).name());                    
                    rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
                }
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabel(){
        this.tabelData.setModel(new javax.swing.table.DefaultTableModel(
            getData(),
            new String [] {
                "ID Petugas", "Nama Petugas", "Jenis Kelamin", "Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private void showData(){
        // mendapatkan data dari petugas
        this.namaPetugas = txt.toCapitalize(petugas.getNama(idSelected));
        this.gender = txt.getGenderName(petugas.getGender(idSelected));
        this.ttl = txt.toDateOfBirthCase(petugas.getTempatLahir(idSelected), petugas.getTanggalLahir(idSelected));
        this.alamat = txt.toCapitalize(petugas.getAlamat(idSelected));
        this.noHp = txt.toTelephoneCase(petugas.getNoHp(idSelected));
        this.email = petugas.getEmail(idSelected).toLowerCase();
        this.levelPetugas = petugas.getLevel(idSelected).name();
        this.transaksi = txt.addDelim(petugas.getTotalTransaksi(idSelected));
        this.lastOn = petugas.getLastOnline(idSelected);
        
        if(this.lastOn.equals("null")){
            lastOn = "Never";
        }
        
        // menampilkan data dari petugas
        this.valId.setText("<html><p>:&nbsp;"+idSelected+"</p></html>");
        this.valNama.setText("<html><p>:&nbsp;"+namaPetugas+"</p></html>");
        this.valGender.setText("<html><p>:&nbsp;"+gender+"</p></html>");
        this.valTtl.setText("<html><p>:&nbsp;"+ttl+"</p></html>");
        this.valAlamat.setText("<html><p>:&nbsp;"+alamat+"</p></html>");
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;"+noHp+"</p></html>");
        this.valEmail.setText("<html><p>:&nbsp;"+email+"</p></html>");
        this.valLevel.setText("<html><p>:&nbsp;"+levelPetugas+"</p></html>");
        this.valTransaksi.setText("<html><p>:&nbsp;"+transaksi+" Transaksi Dilakukan</p></html>");
        this.valLastOn.setText("<html><p>:&nbsp;"+lastOn+"</p></html>");
        this.lblShowFoto.setIcon(petugas.getPhoto(idSelected, UserPhotoSize.DATA_PETUGAS));
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
        lblTop = new javax.swing.JSeparator();
        lblDashboard = new javax.swing.JLabel();
        pnlInfoData = new javax.swing.JPanel();
        pnlTitleInfo = new javax.swing.JPanel();
        lblTitleInfo = new javax.swing.JLabel();
        lblShowFoto = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        lblPenampilFoto = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblTtl = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblTransaksi = new javax.swing.JLabel();
        valId = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        valGender = new javax.swing.JLabel();
        valTtl = new javax.swing.JLabel();
        valAlamat = new javax.swing.JLabel();
        valNoHp = new javax.swing.JLabel();
        valEmail = new javax.swing.JLabel();
        valLevel = new javax.swing.JLabel();
        valTransaksi = new javax.swing.JLabel();
        lblLastOn = new javax.swing.JLabel();
        valLastOn = new javax.swing.JLabel();
        lblCari = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        lineCenter = new javax.swing.JSeparator();
        lineBottom = new javax.swing.JSeparator();
        lblTotalData = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 710));

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

        lblTop.setBackground(new java.awt.Color(0, 0, 0));
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lblTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 1010, 10));

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-datapetugas-logo.png"))); // NOI18N
        lblDashboard.setText("Data Petugas");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        pnlInfoData.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 98, 230), 2));

        pnlTitleInfo.setBackground(new java.awt.Color(15, 98, 230));
        pnlTitleInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitleInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleInfo.setText("Informasi Petugas");

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

        lblShowFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblFoto.setText("Foto Petugas");
        lblFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblPenampilFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblPenampilFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblPenampilFoto.setText("Tampilkan Foto");
        lblPenampilFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPenampilFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPenampilFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPenampilFotoMouseExited(evt);
            }
        });

        lblId.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblId.setForeground(new java.awt.Color(0, 0, 0));
        lblId.setText("ID Petugas");

        lblGender.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblGender.setForeground(new java.awt.Color(0, 0, 0));
        lblGender.setText("Jenis Kelamin");

        lblNama.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNama.setForeground(new java.awt.Color(0, 0, 0));
        lblNama.setText("Nama Petugas");

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        lblTtl.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTtl.setForeground(new java.awt.Color(0, 0, 0));
        lblTtl.setText("TTL");

        lblLevel.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblLevel.setForeground(new java.awt.Color(0, 0, 0));
        lblLevel.setText("Level");

        lblTransaksi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lblTransaksi.setText("Total Transaksi");

        valId.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valId.setForeground(new java.awt.Color(0, 0, 0));
        valId.setText(": 12");

        valNama.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Levi Ackerman");

        valGender.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valGender.setForeground(new java.awt.Color(0, 0, 0));
        valGender.setText(": Laki-Laki");

        valTtl.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTtl.setForeground(new java.awt.Color(0, 0, 0));
        valTtl.setText(": Indonesia, 30 Maret 1980");

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valAlamat.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat.setText(": Jawa Timur, Indonesia");

        valNoHp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
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

        valEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valEmail.setForeground(new java.awt.Color(0, 0, 0));
        valEmail.setText(": leviackerman@gmail.com");

        valLevel.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valLevel.setForeground(new java.awt.Color(0, 0, 0));
        valLevel.setText(": Admin");

        valTransaksi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        valTransaksi.setText(": 180 Transaksi Dilakukan");

        lblLastOn.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblLastOn.setForeground(new java.awt.Color(0, 0, 0));
        lblLastOn.setText("Last Online");

        valLastOn.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valLastOn.setForeground(new java.awt.Color(0, 0, 0));
        valLastOn.setText(": 2021-07-11 21:07");

        javax.swing.GroupLayout pnlInfoDataLayout = new javax.swing.GroupLayout(pnlInfoData);
        pnlInfoData.setLayout(pnlInfoDataLayout);
        pnlInfoDataLayout.setHorizontalGroup(
            pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valId, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblShowFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPenampilFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblTtl, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valTtl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLevel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                            .addComponent(lblLastOn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valLastOn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlInfoDataLayout.setVerticalGroup(
            pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoDataLayout.createSequentialGroup()
                .addComponent(pnlTitleInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblShowFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInfoDataLayout.createSequentialGroup()
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPenampilFoto)))
                .addGap(18, 18, 18)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(valId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(valNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(valGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTtl)
                    .addComponent(valTtl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlamat)
                    .addComponent(valAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoHp)
                    .addComponent(valNoHp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(valEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel)
                    .addComponent(valLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTransaksi)
                    .addComponent(valTransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastOn)
                    .addComponent(valLastOn))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 510, 480));

        lblCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari ID / Nama Petugas :");
        pnlMain.add(lblCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 210, 30));

        tabelData.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Petugas", "Nama Petugas", "Jenis Kelamin", "Level"
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

        pnlMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, 440, 440));

        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.setForeground(new java.awt.Color(0, 0, 0));
        inpCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });
        pnlMain.add(inpCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 220, 30));

        lineCenter.setBackground(new java.awt.Color(0, 0, 0));
        lineCenter.setForeground(new java.awt.Color(0, 0, 0));
        lineCenter.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlMain.add(lineCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 10, 490));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 640, 1010, 10));

        lblTotalData.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblTotalData.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalData.setText("Menampilkan 20 data petugas dengan keyword = \"\"");
        pnlMain.add(lblTotalData, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 615, 440, 20));

        btnTambah.setBackground(new java.awt.Color(41, 180, 50));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnTambah.setText("Tambah Data");
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTambahMouseExited(evt);
            }
        });
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        pnlMain.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 660, 130, -1));

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEdit.setText("Edit Data");
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
        pnlMain.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 660, 110, -1));

        btnHapus.setBackground(new java.awt.Color(220, 41, 41));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnHapus.setText("Hapus Data");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusMouseExited(evt);
            }
        });
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        pnlMain.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 660, 120, -1));

        lblVersion.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(0, 0, 0));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersion.setText("Version 1.0.0");
        pnlMain.add(lblVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 660, 370, -1));

        lblCopyright.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(0, 0, 0));
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2021. Achmad Baihaqi. All Rights Reserved.");
        pnlMain.add(lblCopyright, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 680, 390, -1));

        lblBgImage.setBackground(new java.awt.Color(41, 52, 71));
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 710));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        petugas.closeConnection();
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
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DashboardPetugas dashboard = new DashboardPetugas();
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
        // no event
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

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if(petugas.isAdmin()){
            Audio.play(Audio.SOUND_INFO);
            new TambahDataPetugas(this, true).setVisible(true);
        }else{
            Message.showWarning(this, "Access Denied!\nPetugas tidak diperbolehkan untuk menambahkan sebuah data!", true);
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        this.btnTambah.setIcon(Gambar.getIcon("ic-data-tambah-entered.png"));
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        this.btnTambah.setIcon(Gambar.getIcon("ic-data-tambah.png"));
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(petugas.isAdmin()){
            if(tabelData.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new EditDataPetugas(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
            }
        }else{
            Message.showWarning(this, "Access Denied!\nPetugas tidak diperbolehkan untuk mengedit sebuah data!", true);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if(petugas.isAdmin()){
            if(tabelData.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new HapusData(this, true, HapusData.DATA_PETUGAS, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
            }
        }else{
            Message.showWarning(this, "Access Denied!\nPetugas tidak diperbolehkan untuk menghapus sebuah data!", true);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnHapusMouseExited

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_petugas LIKE '%"+key+"%' OR nama_petugas LIKE '%"+key+"%'";
        System.out.println("key = " + keyword);
        this.lblTotalData.setText("Menampilkan "+petugas.getJumlahData(DatabaseTables.PETUGAS.name(), keyword)+" data petugas dengan keyword = \""+key+"\"");
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyTyped

    private void tabelDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() - 1, 0).toString();
            this.showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
    }//GEN-LAST:event_tabelDataKeyPressed

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow(), 0).toString();
        this.showData();
    }//GEN-LAST:event_tabelDataMouseClicked

    private void lblPenampilFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPenampilFotoMouseClicked
        Audio.play(Audio.SOUND_INFO);
        new PenampilFotoPetugas(this, true, petugas.getPhoto(idSelected, UserPhotoSize.SHOW_FOTO_PETUGAS)).setVisible(true);
    }//GEN-LAST:event_lblPenampilFotoMouseClicked

    private void lblPenampilFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPenampilFotoMouseEntered
        this.lblPenampilFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblPenampilFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Tampilkan Foto</p></html>");
    }//GEN-LAST:event_lblPenampilFotoMouseEntered

    private void lblPenampilFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPenampilFotoMouseExited
        this.lblPenampilFoto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblPenampilFoto.setText("<html><p style=\"text-decoration:none; color:rbg(0,0,0);\">Tampilkan Foto</p></html>");
    }//GEN-LAST:event_lblPenampilFotoMouseExited

    private void valNoHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseClicked
        Internet net = new Internet();
        String nomor = noHp.substring(1).replaceAll(" ", "").replaceAll("-", "");
        if(net.isConnectInternet()){
            try {
                net.openLink("https://wa.me/"+nomor);
            } catch (IOException | URISyntaxException ex) {
                Message.showException(this, ex, true);
            }
        }else{
            Message.showWarning(this, "Tidak terhubung ke Internet!", true);
        }
    }//GEN-LAST:event_valNoHpMouseClicked

    private void valNoHpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(15,98,230);\">:&nbsp;"+noHp+"</p></html>");
    }//GEN-LAST:event_valNoHpMouseEntered

    private void valNoHpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;"+noHp+"</p></html>");
    }//GEN-LAST:event_valNoHpMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Log.addLog("Membuka Window " + getClass().getName());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        Log.createLog();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataPetugas().setVisible(true);
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
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnPembayaranSpp;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLastOn;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblPenampilFoto;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblShowFoto;
    private javax.swing.JLabel lblTipeAkun;
    private javax.swing.JLabel lblTitleInfo;
    private javax.swing.JSeparator lblTop;
    private javax.swing.JLabel lblTotalData;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JLabel lblTtl;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineCenter;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfoData;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleInfo;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valEmail;
    private javax.swing.JLabel valGender;
    private javax.swing.JLabel valId;
    private javax.swing.JLabel valLastOn;
    private javax.swing.JLabel valLevel;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valTransaksi;
    private javax.swing.JLabel valTtl;
    // End of variables declaration//GEN-END:variables
}
