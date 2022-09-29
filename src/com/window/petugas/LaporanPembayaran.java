package com.window.petugas;

import com.window.pembayaran.EditPembayaran;
import com.window.pembayaran.BatalkanPembayaran;
import com.data.app.Application;
import com.data.app.Transaksi;
import com.data.app.Kelas;
import com.data.app.Log;
import com.data.db.Database;
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
public class LaporanPembayaran extends javax.swing.JFrame {
    
    private final Users.LevelPetugas petugas = Users.levelPetugas();
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Transaksi tr = new Transaksi();
    private final Kelas kls = new Kelas();
    private final Text txt = new Text();
    
    private final String name;
    private String nisSelected = "", idSelected = "", idPetugas, nis, namaSiswa, namaPetugas, 
                   gender, kelas,nohp, blnBayar, thnBayar, tglBayar, jmlBayar, nominal;
    private int x, y;
    
    public LaporanPembayaran() {
        this(0, "");
    }
    
    public LaporanPembayaran(String nis){
        this(1, nis);
    }
    
    public LaporanPembayaran(int index, String nis){
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        
        // jika index bernilai 1 dan nis tidak kosong maka tabLaporan akan diset ke index 1
        if(index == 1 && !nis.equals("")){
            // mengatur tabLaporan ke index 1
            this.tabLaporan.setSelectedIndex(index);
            // menampilkan data pada tabel
            this.nisSelected = nis;
            this.updateTableLaporanSiswa();
            this.updateTabelSemuaLaporan();
            
            // mendapatkan data-data siswa
            this.idPetugas = tr.getData(Database.PEMBAYARAN, "id_petugas", "WHERE id_pembayaran = '" + idSelected + "'");
            this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
            this.gender = txt.getGenderName(siswa.getGender(nis));
            this.kelas = kls.getNamaKelas(siswa.getIdKelas(nis));
            this.nohp = txt.toTelephoneCase(siswa.getNoHp(nis));
            System.out.println(this.nis);
            this.nominal = txt.toMoneyCase(Integer.toString(tr.getNominalSpp(Integer.parseInt(siswa.getIdSpp(this.nisSelected)))));
            
            // menampilkan data-data siswa
            this.inpNis.setText(this.nisSelected);
            this.valNis.setText("<html><p>:&nbsp;" + this.nisSelected + "</p></html>");
            this.valNamaSiswa.setText("<html><p>:&nbsp;" + namaSiswa + "</p></html>");
            this.valGender.setText("<html><p>:&nbsp;" + gender + "</p></html>");
            this.valKelas.setText("<html><p>:&nbsp;" + kelas + "</p></html>");
            this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;" + nohp + "</p></html>");
            this.valNominal.setText("<html><p>:&nbsp;" + nominal + "</p></html>");
            this.valIdPembayaran.setText("<html><p>:&nbsp;null</p></html>");
            this.valNamaPetugas.setText("<html><p>:&nbsp;null</p></html>");
            this.valBulanBayar.setText("<html><p>:&nbsp;null</p></html>");
            this.valTahunBayar.setText("<html><p>:&nbsp;null</p></html>");
            this.valTanggalBayar.setText("<html><p>:&nbsp;null</p></html>");
            this.valJumlahBayar.setText("<html><p>:&nbsp;null</p></html>");
        }else{
            // menampilkan data tabel
            this.updateTabelSemuaLaporan();
            this.updateTableLaporanSiswa();
            this.resetData();
        }
        
        // menampilkan data-data aplikasi
        this.lblSekolah.setIcon(Gambar.getTopIcon());
        this.lblSekolah.setText(Application.getCompany() + " | " + Application.getNama());
        this.lblVersion.setText("Versi " + Application.getVersi());
        this.lblCopyright.setText(Application.getRightReserved());
        
        // mendapatkan dan menampilkan data dari petugas
        name = txt.toCapitalize(petugas.getNama());
        this.lblNamaUser.setText("<html><p>"+name+"</p></html>");
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
        this.btnCetak.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLihatSemuaLaporan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnLihatLaporanSiswa.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        

        // mengatur header pada tabel
        this.tblSemuaLaporan.setRowHeight(29);
        this.tblSemuaLaporan.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tblSemuaLaporan.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        this.tblLaporanSiswa.setRowHeight(29);
        this.tblLaporanSiswa.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tblLaporanSiswa.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.btnLaporan.setBackground(new Color(85,101,114));
        JButton[] btns = new JButton[]{
            this.btnDashboard, this.btnInfoAkun, this.btnDataSiswa, this.btnDataPetugas, 
            this.btnDataKelas, this.btnDataSpp, this.btnPembayaranSpp, this.btnTentangApp
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
            this.valNis, this.valNamaSiswa, this.valGender, this.valKelas, this.valNoHp, this.valNominal, 
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
                    lbl.setForeground(new Color(5,170,57));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0,0,0));
                }
            });
        }
        
        JLabel[] lblPembayarn = new JLabel[]{
            this.valIdPembayaran, this.valNamaPetugas, this.valBulanBayar, 
            this.valTahunBayar, this.valTanggalBayar, this.valJumlahBayar
        };
        
        for(JLabel lbl : lblPembayarn){
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
    }
    
    private int getJumlahData(){
        switch(this.inpJumlahData.getSelectedIndex()){
            case 0: return 25;
            case 1: return 50;
            case 2: return 100;
            case 3: return 500;
            case 4: return 1000;
            case 5: return 5000;
            default: return 1;
        }
    }
    
    private Object[][] getDataSemuaLaporan(){
        try {
            int rows = 0;
            // membuat query 
            final String sql = "SELECT id_pembayaran, id_petugas, nis, jml_bayar "
                                + "FROM pembayaran ORDER BY tgl_bayar DESC "
                                + "LIMIT 0," + this.getJumlahData();
            // mendefinisikan object berdasarkan input jumlah data
            Object[][] obj = new Object[this.getJumlahData()][4];
            
            // mengeksekusi query
            this.tr.res = this.tr.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while (this.tr.res.next()) {
                // menyimpan data ke array object
                obj[rows][0] = this.tr.res.getString("id_pembayaran");
                obj[rows][1] = this.petugas.getNama(tr.res.getString("id_petugas"));
                obj[rows][2] = this.siswa.getNama(tr.res.getString("nis"));
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
    
    private Object[][] getDataLaporanSiswa(String nis){
        try {
            // jika nis kosong maka method akan mengembalikan nilai null
            if(nis.equals("")){
                return null;
            }
            // jika nis exist maka method akan mendapatkan data
            else if(siswa.isExistSiswa(nis)){
                int rows = 0;
                // membuat query 
                final String sql = "SELECT id_pembayaran, bln_bayar, thn_bayar, jml_bayar "
                                    + "FROM pembayaran WHERE nis = '" + nis + "' "
                                    + "ORDER BY tgl_bayar DESC ";
                // mendefinisikan object berdasarkan input jumlah data
                Object[][] obj = new Object[this.tr.getJumlahData(Database.PEMBAYARAN, "WHERE nis = " + nis)][4];

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
            }else{
                Message.showWarning(this, "'" + nis + "' NIS tersebut tidak ditemukan!");
            }
        }catch (SQLException ex) {
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }catch (NumberFormatException nex){
            Message.showException(this, "NIS harus berupa angka!", nex, true);
        }
        return null;
    }
    
    private void updateTabelSemuaLaporan(){
        this.tblSemuaLaporan.setModel(
                new DefaultTableModel(this.getDataSemuaLaporan(), 
                        new String[] 
                        { "ID Pembayaran", "Nama Petugas", "Nama Siswa", "Jumlah Bayar" }
                ){
            boolean[] canEdit = { false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
    }
    
    private void updateTableLaporanSiswa(){
        this.tblLaporanSiswa.setModel(
                new DefaultTableModel(this.getDataLaporanSiswa(nisSelected), 
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
        this.idPetugas = tr.getData(Database.PEMBAYARAN, "id_petugas", "WHERE id_pembayaran = '" + idSelected + "'");
        this.nis = tr.getData(Database.PEMBAYARAN, "nis", "WHERE id_pembayaran = '" + idSelected + "'");
        this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
        this.gender = txt.getGenderName(siswa.getGender(nis));
        this.kelas = kls.getNamaKelas(siswa.getIdKelas(nis));
        this.nohp = txt.toTelephoneCase(siswa.getNoHp(nis));
        this.namaPetugas = txt.toCapitalize(petugas.getNama(idPetugas));
        this.blnBayar = tr.getData("pembayaran", "bln_bayar", "WHERE id_pembayaran = '" + idSelected + "'");
        this.thnBayar = tr.getData("pembayaran", "thn_bayar", "WHERE id_pembayaran = '" + idSelected + "'");
        this.tglBayar = txt.toDateCase(tr.getData("pembayaran", "tgl_bayar", "WHERE id_pembayaran = '" + idSelected + "'"));
        this.jmlBayar = txt.toMoneyCase(this.tr.getData("pembayaran", "jml_bayar", "WHERE id_pembayaran = '" + idSelected + "'"));
        this.nominal = txt.toMoneyCase(Integer.toString(tr.getNominalSpp(Integer.parseInt(siswa.getIdSpp(nis)))));
        
        // menampilkan data-data pembayaran
        this.valNis.setText("<html><p>:&nbsp;" + nis + "</p></html>");
        this.valNamaSiswa.setText("<html><p>:&nbsp;" + namaSiswa + "</p></html>");
        this.valGender.setText("<html><p>:&nbsp;" + gender + "</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;" + kelas + "</p></html>");
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;" + nohp + "</p></html>");
        this.valIdPembayaran.setText("<html><p>:&nbsp;" + idSelected + "</p></html>");
        this.valNamaPetugas.setText("<html><p>:&nbsp;" + namaPetugas + "</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;" + blnBayar + "</p></html>");
        this.valTahunBayar.setText("<html><p>:&nbsp;" + thnBayar + "</p></html>");
        this.valTanggalBayar.setText("<html><p>:&nbsp;" + tglBayar + "</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;" + jmlBayar + "</p></html>");
        this.valNominal.setText("<html><p>:&nbsp;" + nominal + "</p></html>");
    }
    
    private void resetData(){
        this.valNis.setText("<html><p>:&nbsp;null</p></html>");
        this.valNamaSiswa.setText("<html><p>:&nbsp;null</p></html>");
        this.valGender.setText("<html><p>:&nbsp;null</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;null</p></html>");
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;null</p></html>");
        this.valIdPembayaran.setText("<html><p>:&nbsp;null</p></html>");
        this.valNamaPetugas.setText("<html><p>:&nbsp;null</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;null</p></html>");
        this.valTahunBayar.setText("<html><p>:&nbsp;null</p></html>");
        this.valTanggalBayar.setText("<html><p>:&nbsp;null</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;null</p></html>");
        this.valNominal.setText("<html><p>:&nbsp;null</p></html>");
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
        pnlInfoSiswa = new javax.swing.JPanel();
        pnlTitleInfoSiswa = new javax.swing.JPanel();
        lblTitleInfoSiswa = new javax.swing.JLabel();
        lblNis = new javax.swing.JLabel();
        lblNamaSiswa = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblNominal = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        valNis = new javax.swing.JLabel();
        valNamaSiswa = new javax.swing.JLabel();
        valGender = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();
        valNoHp = new javax.swing.JLabel();
        valNominal = new javax.swing.JLabel();
        lineCenter = new javax.swing.JSeparator();
        lineBottom = new javax.swing.JSeparator();
        lblTotalData = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        lblVersion = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        pnlInfoPembayaran = new javax.swing.JPanel();
        pnlTitleInfoPembayaran = new javax.swing.JPanel();
        lblTitleInfoPembayaran = new javax.swing.JLabel();
        lblIdPembayaran = new javax.swing.JLabel();
        lblNamaPetugas = new javax.swing.JLabel();
        lblBulanBayar = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        lblTanggalBayar = new javax.swing.JLabel();
        lblTahunBayar = new javax.swing.JLabel();
        valIdPembayaran = new javax.swing.JLabel();
        valNamaPetugas = new javax.swing.JLabel();
        valBulanBayar = new javax.swing.JLabel();
        valTahunBayar = new javax.swing.JLabel();
        valTanggalBayar = new javax.swing.JLabel();
        valJumlahBayar = new javax.swing.JLabel();
        tabLaporan = new javax.swing.JTabbedPane();
        pnlSemuaLaporan = new javax.swing.JPanel();
        lblSemuaLaporan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSemuaLaporan = new javax.swing.JTable();
        inpJumlahData = new javax.swing.JComboBox();
        btnLihatSemuaLaporan = new javax.swing.JButton();
        lblShowDataSemuaLaporan = new javax.swing.JLabel();
        pnlLaporanSiswa = new javax.swing.JPanel();
        lblLaporanSiswa = new javax.swing.JLabel();
        btnLihatLaporanSiswa = new javax.swing.JButton();
        inpNis = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLaporanSiswa = new javax.swing.JTable();
        lblShowDataLaporanSiswa = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
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

        lblTop.setBackground(new java.awt.Color(0, 0, 0));
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lblTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 1010, 10));

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(22, 19, 19));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-window-laporan-logo.png"))); // NOI18N
        lblDashboard.setText("Laporan Pembayaran");
        lblDashboard.setIconTextGap(6);
        pnlMain.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 64, 400, -1));

        pnlInfoSiswa.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoSiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 2));

        pnlTitleInfoSiswa.setBackground(new java.awt.Color(5, 170, 57));
        pnlTitleInfoSiswa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleInfoSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitleInfoSiswa.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleInfoSiswa.setText("Informasi Siswa");

        javax.swing.GroupLayout pnlTitleInfoSiswaLayout = new javax.swing.GroupLayout(pnlTitleInfoSiswa);
        pnlTitleInfoSiswa.setLayout(pnlTitleInfoSiswaLayout);
        pnlTitleInfoSiswaLayout.setHorizontalGroup(
            pnlTitleInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
        );
        pnlTitleInfoSiswaLayout.setVerticalGroup(
            pnlTitleInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitleInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblNis.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lblNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSiswa.setText("Nama Siswa");

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        lblGender.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblGender.setForeground(new java.awt.Color(0, 0, 0));
        lblGender.setText("Jenis Kelamin");

        lblNominal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(0, 0, 0));
        lblNominal.setText("Nominal SPP");

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        valNis.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": 6156");

        valNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        valNamaSiswa.setText(": Achmad Baihaqi");

        valGender.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valGender.setForeground(new java.awt.Color(0, 0, 0));
        valGender.setText(": Laki-Laki");

        valKelas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII RPL 1");

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

        valNominal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNominal.setForeground(new java.awt.Color(0, 0, 0));
        valNominal.setText(": Rp. 150.000.00");

        javax.swing.GroupLayout pnlInfoSiswaLayout = new javax.swing.GroupLayout(pnlInfoSiswa);
        pnlInfoSiswa.setLayout(pnlInfoSiswaLayout);
        pnlInfoSiswaLayout.setHorizontalGroup(
            pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNis, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInfoSiswaLayout.setVerticalGroup(
            pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNis)
                    .addComponent(valNis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaSiswa)
                    .addComponent(valNamaSiswa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(valGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKelas)
                    .addComponent(valKelas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valNoHp)
                    .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNominal)
                    .addComponent(valNominal))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 510, 240));

        lineCenter.setBackground(new java.awt.Color(0, 0, 0));
        lineCenter.setForeground(new java.awt.Color(0, 0, 0));
        lineCenter.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlMain.add(lineCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 10, 500));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));
        pnlMain.add(lineBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 650, 1010, 10));

        lblTotalData.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        pnlMain.add(lblTotalData, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 635, 440, 0));

        btnCetak.setBackground(new java.awt.Color(41, 180, 50));
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-laporan-cetak.png"))); // NOI18N
        btnCetak.setText("Cetak Laporan");
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCetakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCetakMouseExited(evt);
            }
        });
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        pnlMain.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 670, 140, -1));

        lblVersion.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(0, 0, 0));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersion.setText("Version 1.0.0");
        pnlMain.add(lblVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 670, 370, -1));

        lblCopyright.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(0, 0, 0));
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2021. Achmad Baihaqi. All Rights Reserved.");
        pnlMain.add(lblCopyright, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 690, 390, -1));

        pnlInfoPembayaran.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoPembayaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 98, 230), 2));

        pnlTitleInfoPembayaran.setBackground(new java.awt.Color(15, 98, 230));
        pnlTitleInfoPembayaran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitleInfoPembayaran.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        lblTitleInfoPembayaran.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleInfoPembayaran.setText("Informasi Pembayaran");

        javax.swing.GroupLayout pnlTitleInfoPembayaranLayout = new javax.swing.GroupLayout(pnlTitleInfoPembayaran);
        pnlTitleInfoPembayaran.setLayout(pnlTitleInfoPembayaranLayout);
        pnlTitleInfoPembayaranLayout.setHorizontalGroup(
            pnlTitleInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleInfoPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleInfoPembayaranLayout.setVerticalGroup(
            pnlTitleInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitleInfoPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        lblIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPembayaran.setText("ID Transaksi");

        lblNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPetugas.setText("Nama Petugas");

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        lblTanggalBayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTanggalBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggalBayar.setText("Tanggal Bayar");

        lblTahunBayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunBayar.setText("Tahun Bayar");

        valIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        valIdPembayaran.setText(": TR00003");

        valNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPetugas.setText(": Levi Ackerman");

        valBulanBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        valBulanBayar.setText(": Juli 2021");

        valTahunBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        valTahunBayar.setText(": 2021");

        valTanggalBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valTanggalBayar.setForeground(new java.awt.Color(0, 0, 0));
        valTanggalBayar.setText(": 27 Maret 2021");

        valJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        valJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        valJumlahBayar.setText(": Rp. 130.000.00: ");

        javax.swing.GroupLayout pnlInfoPembayaranLayout = new javax.swing.GroupLayout(pnlInfoPembayaran);
        pnlInfoPembayaran.setLayout(pnlInfoPembayaranLayout);
        pnlInfoPembayaranLayout.setHorizontalGroup(
            pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(lblTanggalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTahunBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(valBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valTahunBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valTanggalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        pnlInfoPembayaranLayout.setVerticalGroup(
            pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoPembayaranLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valIdPembayaran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaPetugas)
                    .addComponent(valNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBulanBayar)
                    .addComponent(valBulanBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valTahunBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTanggalBayar)
                    .addComponent(valTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlahBayar)
                    .addComponent(valJumlahBayar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlMain.add(pnlInfoPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 510, 240));

        tabLaporan.setBackground(new java.awt.Color(255, 255, 255));
        tabLaporan.setForeground(new java.awt.Color(0, 0, 0));

        pnlSemuaLaporan.setBackground(new java.awt.Color(250, 250, 250));
        pnlSemuaLaporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSemuaLaporan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSemuaLaporan.setForeground(new java.awt.Color(255, 67, 4));
        lblSemuaLaporan.setText("Tampilkan Laporan  :");
        pnlSemuaLaporan.add(lblSemuaLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 18, 147, 26));

        tblSemuaLaporan.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        tblSemuaLaporan.setForeground(new java.awt.Color(0, 0, 0));
        tblSemuaLaporan.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID Pembayaran", "Nama Petugas", "Nama Siswa", "Jumlah Bayar"
            }
        ));
        tblSemuaLaporan.setGridColor(new java.awt.Color(0, 0, 0));
        tblSemuaLaporan.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tblSemuaLaporan.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tblSemuaLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSemuaLaporanMouseClicked(evt);
            }
        });
        tblSemuaLaporan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSemuaLaporanKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSemuaLaporan);

        pnlSemuaLaporan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 64, 426, 414));

        inpJumlahData.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpJumlahData.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlahData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25 Laporan Terakhir", "50 Laporan Terakhir", "100 Laporan Terakhir", "500 Laporan Terakhir", "1000 Laporan Terakhir", "5000 Laporan Terakhir" }));
        pnlSemuaLaporan.add(inpJumlahData, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 16, 170, 30));

        btnLihatSemuaLaporan.setBackground(new java.awt.Color(34, 119, 237));
        btnLihatSemuaLaporan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLihatSemuaLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatSemuaLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-showdata.png"))); // NOI18N
        btnLihatSemuaLaporan.setText("LIhat");
        btnLihatSemuaLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLihatSemuaLaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLihatSemuaLaporanMouseExited(evt);
            }
        });
        btnLihatSemuaLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatSemuaLaporanActionPerformed(evt);
            }
        });
        pnlSemuaLaporan.add(btnLihatSemuaLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 15, 90, 30));

        lblShowDataSemuaLaporan.setBackground(new java.awt.Color(255, 255, 255));
        lblShowDataSemuaLaporan.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblShowDataSemuaLaporan.setForeground(new java.awt.Color(0, 0, 0));
        lblShowDataSemuaLaporan.setText("Menampilkan 25 data laporan pembayaran SPP.");
        pnlSemuaLaporan.add(lblShowDataSemuaLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 420, 20));

        tabLaporan.addTab("Semua Laporan", pnlSemuaLaporan);

        pnlLaporanSiswa.setBackground(new java.awt.Color(255, 255, 255));
        pnlLaporanSiswa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLaporanSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLaporanSiswa.setForeground(new java.awt.Color(255, 67, 4));
        lblLaporanSiswa.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblLaporanSiswa.setText("Masukan NIS : ");
        pnlLaporanSiswa.add(lblLaporanSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 30));

        btnLihatLaporanSiswa.setBackground(new java.awt.Color(34, 119, 237));
        btnLihatLaporanSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLihatLaporanSiswa.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatLaporanSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-showdata.png"))); // NOI18N
        btnLihatLaporanSiswa.setText("LIhat");
        btnLihatLaporanSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLihatLaporanSiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLihatLaporanSiswaMouseExited(evt);
            }
        });
        btnLihatLaporanSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatLaporanSiswaActionPerformed(evt);
            }
        });
        pnlLaporanSiswa.add(btnLihatLaporanSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 90, 30));

        inpNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNis.setForeground(new java.awt.Color(0, 0, 0));
        pnlLaporanSiswa.add(inpNis, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 180, 30));

        tblLaporanSiswa.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        tblLaporanSiswa.setForeground(new java.awt.Color(0, 0, 0));
        tblLaporanSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID Pembayaran", "Bulan Bayar", "Tahun Bayar", "Jumlah Bayar"
            }
        ));
        tblLaporanSiswa.setGridColor(new java.awt.Color(0, 0, 0));
        tblLaporanSiswa.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tblLaporanSiswa.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tblLaporanSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaporanSiswaMouseClicked(evt);
            }
        });
        tblLaporanSiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblLaporanSiswaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblLaporanSiswa);

        pnlLaporanSiswa.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 64, 426, 414));

        lblShowDataLaporanSiswa.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblShowDataLaporanSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblShowDataLaporanSiswa.setText("Menampilkan 25 data laporan pembayaran SPP.");
        pnlLaporanSiswa.add(lblShowDataLaporanSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 420, 20));

        tabLaporan.addTab("Laporan Siswa", pnlLaporanSiswa);

        pnlMain.add(tabLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 440, 530));

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-editakun.png"))); // NOI18N
        btnEdit.setText("Edit Pembayaran");
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
        pnlMain.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 670, 160, -1));

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
        btnBatal.setText("Batalkan Pembayaran");
        btnBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBatalMouseExited(evt);
            }
        });
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlMain.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 670, 190, -1));

        lblBgImage.setBackground(new java.awt.Color(41, 52, 71));
        pnlMain.add(lblBgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 720));

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
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        petugas.closeConnection();
        siswa.closeConnection();
        tr.closeConnection();
        kls.closeConnection();
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
        // no evet
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

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // jika user memilih tab semua laporan
        if(this.tabLaporan.getSelectedIndex() == 0){
            if(this.tblSemuaLaporan.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new CetakLaporanSpp(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
        // jika user memilih tab laporan siswa
        else if(this.tabLaporan.getSelectedIndex() == 1){
            if(this.tblLaporanSiswa.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new CetakLaporanSpp(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnCetakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseEntered
        this.btnCetak.setIcon(Gambar.getIcon("ic-laporan-cetak-entered.png"));
    }//GEN-LAST:event_btnCetakMouseEntered

    private void btnCetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseExited
        this.btnCetak.setIcon(Gambar.getIcon("ic-laporan-cetak.png"));
    }//GEN-LAST:event_btnCetakMouseExited

    private void valNoHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseClicked
        final Internet net = new Internet();
        final String nomor = this.nohp.substring(1).replaceAll(" ", "").replaceAll("-", "");
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
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(5,170,57);\">:&nbsp;" + nohp + "</p></html>");
    }//GEN-LAST:event_valNoHpMouseEntered

    private void valNoHpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseExited
        this.setCursor(new Cursor(0));
        this.valNoHp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;" + nohp + "</p></html>");
    }//GEN-LAST:event_valNoHpMouseExited

    private void btnLihatSemuaLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatSemuaLaporanActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data semua pembayaran
        this.updateTabelSemuaLaporan();
        this.lblShowDataSemuaLaporan.setText("Menampilkan " + txt.addDelim(this.getJumlahData()) + " data laporan pembayaran SPP.");
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        System.gc();
    }//GEN-LAST:event_btnLihatSemuaLaporanActionPerformed

    private void btnLihatSemuaLaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatSemuaLaporanMouseEntered
        this.btnLihatSemuaLaporan.setIcon(Gambar.getIcon("ic-showdata-entered.png"));
    }//GEN-LAST:event_btnLihatSemuaLaporanMouseEntered

    private void btnLihatSemuaLaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatSemuaLaporanMouseExited
        this.btnLihatSemuaLaporan.setIcon(Gambar.getIcon("ic-showdata.png"));
    }//GEN-LAST:event_btnLihatSemuaLaporanMouseExited

    private void btnLihatLaporanSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatLaporanSiswaActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data pembayaran pada siswa
        this.nisSelected = inpNis.getText();
        this.updateTableLaporanSiswa();
        this.lblShowDataLaporanSiswa.setText("Menampilkan " + tr.getJumlahData("pembayaran", "WHERE nis = " + nisSelected) + " data laporan pembayaran SPP.");
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnLihatLaporanSiswaActionPerformed

    private void btnLihatLaporanSiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatLaporanSiswaMouseEntered
        this.btnLihatLaporanSiswa.setIcon(Gambar.getIcon("ic-showdata-entered.png"));
    }//GEN-LAST:event_btnLihatLaporanSiswaMouseEntered

    private void btnLihatLaporanSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLihatLaporanSiswaMouseExited
        this.btnLihatLaporanSiswa.setIcon(Gambar.getIcon("ic-showdata.png"));
    }//GEN-LAST:event_btnLihatLaporanSiswaMouseExited

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // jika user memilih tab semua laporan
        if(this.tabLaporan.getSelectedIndex() == 0){
            if(this.tblSemuaLaporan.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new EditPembayaran(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
        // jika user memilih tab laporan siswa
        else if(this.tabLaporan.getSelectedIndex() == 1){
            if(this.tblLaporanSiswa.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new EditPembayaran(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // jika user memilih tab semua laporan
        if(this.tabLaporan.getSelectedIndex() == 0){
            if(this.tblSemuaLaporan.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new BatalkanPembayaran(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
        // jika user memilih tab laporan siswa
        else if(this.tabLaporan.getSelectedIndex() == 1){
            if(this.tblLaporanSiswa.getSelectedRow() > -1){
                Audio.play(Audio.SOUND_INFO);
                new BatalkanPembayaran(this, true, this.idSelected).setVisible(true);
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!");
            }            
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void tblSemuaLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSemuaLaporanMouseClicked
        this.setCursor(new Cursor(3));
        this.idSelected = this.tblSemuaLaporan.getValueAt(this.tblSemuaLaporan.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tblSemuaLaporanMouseClicked

    private void tblSemuaLaporanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSemuaLaporanKeyPressed
        this.setCursor(new Cursor(3));
        if (evt.getKeyCode() == 38) {
            this.idSelected = this.tblSemuaLaporan.getValueAt(this.tblSemuaLaporan.getSelectedRow() - 1, 0).toString();
            this.showData();
        }
        else if (evt.getKeyCode() == 40) {
            this.idSelected = this.tblSemuaLaporan.getValueAt(this.tblSemuaLaporan.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tblSemuaLaporanKeyPressed

    private void tblLaporanSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLaporanSiswaMouseClicked
        this.setCursor(new Cursor(3));
        this.idSelected = this.tblLaporanSiswa.getValueAt(this.tblLaporanSiswa.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tblLaporanSiswaMouseClicked

    private void tblLaporanSiswaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblLaporanSiswaKeyPressed
        this.setCursor(new Cursor(3));
        if (evt.getKeyCode() == 38) {
            this.showData();
        }else if (evt.getKeyCode() == 40) {
            this.idSelected = this.tblLaporanSiswa.getValueAt(this.tblLaporanSiswa.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_tblLaporanSiswaKeyPressed

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
            java.util.logging.Logger.getLogger(LaporanPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LaporanPembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDataKelas;
    private javax.swing.JButton btnDataPetugas;
    private javax.swing.JButton btnDataSiswa;
    private javax.swing.JButton btnDataSpp;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLihatLaporanSiswa;
    private javax.swing.JButton btnLihatSemuaLaporan;
    private javax.swing.JButton btnMinimaze;
    private javax.swing.JButton btnPembayaranSpp;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JComboBox inpJumlahData;
    private javax.swing.JTextField inpNis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBgImage;
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblIdPembayaran;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblLaporanSiswa;
    private javax.swing.JLabel lblNamaPetugas;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblSekolah;
    private javax.swing.JLabel lblSemuaLaporan;
    private javax.swing.JLabel lblShowDataLaporanSiswa;
    private javax.swing.JLabel lblShowDataSemuaLaporan;
    private javax.swing.JLabel lblTahunBayar;
    private javax.swing.JLabel lblTanggalBayar;
    private javax.swing.JLabel lblTipeAkun;
    private javax.swing.JLabel lblTitleInfoPembayaran;
    private javax.swing.JLabel lblTitleInfoSiswa;
    private javax.swing.JSeparator lblTop;
    private javax.swing.JLabel lblTotalData;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineCenter;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfoPembayaran;
    private javax.swing.JPanel pnlInfoSiswa;
    private javax.swing.JPanel pnlLaporanSiswa;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSemuaLaporan;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleInfoPembayaran;
    private javax.swing.JPanel pnlTitleInfoSiswa;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTabbedPane tabLaporan;
    private javax.swing.JTable tblLaporanSiswa;
    private javax.swing.JTable tblSemuaLaporan;
    private javax.swing.JLabel valBulanBayar;
    private javax.swing.JLabel valGender;
    private javax.swing.JLabel valIdPembayaran;
    private javax.swing.JLabel valJumlahBayar;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNamaPetugas;
    private javax.swing.JLabel valNamaSiswa;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valNominal;
    private javax.swing.JLabel valTahunBayar;
    private javax.swing.JLabel valTanggalBayar;
    // End of variables declaration//GEN-END:variables
}
