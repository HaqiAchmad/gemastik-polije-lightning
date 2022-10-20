package com.window.panels;

import com.manage.Barang;
import com.manage.Message;
import com.manage.Text;
import com.manage.Validation;
import com.media.Gambar;
import com.sun.glass.events.KeyEvent;
import com.users.Pembeli;
import com.users.Users;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.SQLException;

/**
 *
 * @author Gemastik Lightning
 */
public class TransaksiJual extends javax.swing.JPanel {

    private final Users user = new Users();
    
    private final Pembeli pembeli = new Pembeli();
    
    private final Barang barang = new Barang();
    
    private final com.manage.TransaksiJual trj = new com.manage.TransaksiJual();
    
    private final Text text = new Text();
    
    private String keywordPembeli = "", keywordBarang = "", idSelectedPembeli, idSelectedBarang;
    
    private String idTr, namaPetugas, namaPembeli, namaBarang, idPetugas, idPembeli, idBarang, metodeBayar, tanggal;
    
    private int jumlah = 1, totalHarga = 0, stok = 0;
    
    public TransaksiJual() {
        initComponents();
        
        this.idTr = this.trj.createIDTransaksi();
        this.namaPetugas = this.user.getCurrentLoginName();
        
        this.btnAddJumlah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinJumlah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBayar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelDataBarang.setRowHeight(29);
        this.tabelDataBarang.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataBarang.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.tabelDataPembeli.setRowHeight(29);
        this.tabelDataPembeli.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataPembeli.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.inpID.setText("<html><p>:&nbsp;"+this.trj.createIDTransaksi()+"</p></html>");
        this.inpNamaPetugas.setText("<html><p>:&nbsp;"+this.namaPetugas+"</p></html>");
        
        this.updateTabelPembeli();
        this.updateTabelBarang();
    }
    
    private Object[][] getDataPembeli(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_pembeli, nama_pembeli, no_telp, alamat FROM pembeli " + keywordPembeli;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[pembeli.getJumlahData("pembeli", keywordPembeli)][4];
            // mengeksekusi query
            pembeli.res = pembeli.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(pembeli.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = pembeli.res.getString("id_pembeli");
                obj[rows][1] = pembeli.res.getString("nama_pembeli");
                obj[rows][2] = pembeli.res.getString("no_telp");
                obj[rows][3] = pembeli.res.getString("alamat");
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabelPembeli(){
        this.tabelDataPembeli.setModel(new javax.swing.table.DefaultTableModel(
            getDataPembeli(),
            new String [] {
                "ID Pembeli", "Nama Pembeli", "No Telephone", "Alamat"
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
    
    private Object[][] getDataBarang(){
        try{
            Object obj[][];
            int rows = 0;
            String sql = "SELECT id_barang, nama_barang, jenis_barang, stok, harga_jual FROM barang " + keywordBarang;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[barang.getJumlahData("barang", keywordBarang)][5];
            // mengeksekusi query
            barang.res = barang.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(barang.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = barang.res.getString("id_barang");
                obj[rows][1] = barang.res.getString("nama_barang");
                obj[rows][2] = text.toCapitalize(barang.res.getString("jenis_barang"));
                obj[rows][3] = barang.res.getString("stok");
                obj[rows][4] = text.toMoneyCase(barang.res.getString("harga_jual"));
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabelBarang(){
        this.tabelDataBarang.setModel(new javax.swing.table.DefaultTableModel(
            getDataBarang(),
            new String [] {
                "ID Barang", "Nama Barang", "Jenis Barang", "Stok", "Harga"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private void getData(){
        
        this.idPetugas = this.user.getCurrentLogin();
        if(this.tabelDataPembeli.getSelectedRow() > - 1){
            this.idPembeli = this.tabelDataPembeli.getValueAt(this.tabelDataPembeli.getSelectedRow(), 0).toString();
            this.namaPembeli = this.pembeli.getNama(this.idPembeli);
        }else{
            this.idPembeli = "";
            this.namaPembeli = "";
        }
        if(this.tabelDataBarang.getSelectedRow() > - 1){
            this.idBarang = this.tabelDataBarang.getValueAt(this.tabelDataBarang.getSelectedRow(), 0).toString();
            this.namaBarang = this.barang.getNamaBarang(this.idBarang);
            this.totalHarga = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
        }else{
            this.idBarang = "";
            this.namaBarang = "";
        }
        this.namaPetugas = this.user.getCurrentLoginName();
    }
    
    private void showData(){
        this.getData();
        this.inpNamaPetugas.setText("<html><p>:&nbsp;"+this.namaPetugas+"</p></html>");
        this.inpNamaPembeli.setText("<html><p>:&nbsp;"+this.namaPembeli+"</p></html>");
        this.inpNamaBarang.setText("<html><p>:&nbsp;"+this.namaBarang+"</p></html>");
        this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");
    }
    
    private void addDelimInInput(){
        // mendapatkan input dari textfield
        String input = inpJumlah.getText();
        // mengecek apakah input merupakan sebuah number atau tidak
        if(text.isNumber(input)){
            input = text.removeDelim(input);
            this.inpJumlah.setText(text.addDelim(Long.parseLong(input)));
        }else if(!input.equals("") && !text.isNumber(input)){
            Message.showInformation(this, "Input harus berupa angka!");
            this.inpJumlah.setText("");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCariBarang = new javax.swing.JLabel();
        inpCariBarang = new javax.swing.JTextField();
        lblCariPembeli = new javax.swing.JLabel();
        inpCariPembeli = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelDataPembeli = new javax.swing.JTable();
        pnlTransaksi = new javax.swing.JPanel();
        lblTransaksi = new javax.swing.JLabel();
        lblIDTransaksi = new javax.swing.JLabel();
        lblNamaPetugas = new javax.swing.JLabel();
        lblNamaPembeli = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        inpJumlah = new javax.swing.JTextField();
        lblNamaBarang = new javax.swing.JLabel();
        lblTotalHarga = new javax.swing.JLabel();
        btnAddJumlah = new javax.swing.JButton();
        btnMinJumlah = new javax.swing.JButton();
        lblMetode = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        inpMetode = new javax.swing.JComboBox();
        lineBottom = new javax.swing.JSeparator();
        btnBayar = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        inpID = new javax.swing.JLabel();
        inpNamaPetugas = new javax.swing.JLabel();
        inpNamaPembeli = new javax.swing.JLabel();
        inpNamaBarang = new javax.swing.JLabel();
        inpTotalHarga = new javax.swing.JLabel();
        inpTanggal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelDataBarang = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        lblCariBarang.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCariBarang.setForeground(new java.awt.Color(237, 12, 12));
        lblCariBarang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariBarang.setText("Cari ID / Nama Barang :");

        inpCariBarang.setBackground(new java.awt.Color(255, 255, 255));
        inpCariBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCariBarang.setForeground(new java.awt.Color(0, 0, 0));
        inpCariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariBarangKeyTyped(evt);
            }
        });

        lblCariPembeli.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCariPembeli.setForeground(new java.awt.Color(237, 12, 12));
        lblCariPembeli.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariPembeli.setText("Cari ID / Nama Pembeli :");

        inpCariPembeli.setBackground(new java.awt.Color(255, 255, 255));
        inpCariPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCariPembeli.setForeground(new java.awt.Color(0, 0, 0));
        inpCariPembeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpCariPembeliKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariPembeliKeyTyped(evt);
            }
        });

        tabelDataPembeli.setBackground(new java.awt.Color(255, 255, 255));
        tabelDataPembeli.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelDataPembeli.setForeground(new java.awt.Color(0, 0, 0));
        tabelDataPembeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Pembeli", "Nama Pembeli", "Alamat"
            }
        ));
        tabelDataPembeli.setGridColor(new java.awt.Color(0, 0, 0));
        tabelDataPembeli.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelDataPembeli.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelDataPembeli.getTableHeader().setReorderingAllowed(false);
        tabelDataPembeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataPembeliMouseClicked(evt);
            }
        });
        tabelDataPembeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelDataPembeliKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabelDataPembeli);

        pnlTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        pnlTransaksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(11, 114, 238), 3));

        lblTransaksi.setBackground(new java.awt.Color(11, 114, 238));
        lblTransaksi.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lblTransaksi.setText("   Transaksi Penjualan");
        lblTransaksi.setOpaque(true);

        lblIDTransaksi.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIDTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lblIDTransaksi.setText("ID Transaksi");

        lblNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPetugas.setText("Nama Karyawan");

        lblNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPembeli.setText("Nama Pembeli");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah");

        inpJumlah.setBackground(new java.awt.Color(255, 255, 255));
        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpJumlah.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlah.setText("1");
        inpJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpJumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpJumlahKeyTyped(evt);
            }
        });

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBarang.setText("Nama Barang");

        lblTotalHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalHarga.setText("Total Harga");

        btnAddJumlah.setBackground(new java.awt.Color(34, 119, 237));
        btnAddJumlah.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnAddJumlah.setForeground(new java.awt.Color(255, 255, 255));
        btnAddJumlah.setText("+");
        btnAddJumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddJumlahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddJumlahMouseExited(evt);
            }
        });
        btnAddJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddJumlahActionPerformed(evt);
            }
        });

        btnMinJumlah.setBackground(new java.awt.Color(220, 41, 41));
        btnMinJumlah.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnMinJumlah.setForeground(new java.awt.Color(255, 255, 255));
        btnMinJumlah.setText("-");
        btnMinJumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinJumlahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinJumlahMouseExited(evt);
            }
        });
        btnMinJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinJumlahActionPerformed(evt);
            }
        });

        lblMetode.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(0, 0, 0));
        lblMetode.setText("Metode Bayar");

        lblTanggal.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggal.setText("Tanggal");

        inpMetode.setBackground(new java.awt.Color(255, 255, 255));
        inpMetode.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpMetode.setForeground(new java.awt.Color(0, 0, 0));
        inpMetode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Metode Pembayaran", "Cash", "E-Wallet" }));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));

        btnBayar.setBackground(new java.awt.Color(34, 119, 237));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-pay.png"))); // NOI18N
        btnBayar.setText("Bayar");
        btnBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBayarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBayarMouseExited(evt);
            }
        });
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnBatal.setText("Batal");
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

        inpID.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpID.setForeground(new java.awt.Color(0, 0, 0));
        inpID.setText(":");

        inpNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaPetugas.setText(":");

        inpNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaPembeli.setText(":");

        inpNamaBarang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaBarang.setText(":");

        inpTotalHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        inpTotalHarga.setText(":");

        inpTanggal.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpTanggal.setForeground(new java.awt.Color(0, 0, 0));
        inpTanggal.setText(": 15 Oktober 2022 | 17:55");

        javax.swing.GroupLayout pnlTransaksiLayout = new javax.swing.GroupLayout(pnlTransaksi);
        pnlTransaksi.setLayout(pnlTransaksiLayout);
        pnlTransaksiLayout.setHorizontalGroup(
            pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnBayar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                        .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpID, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddJumlah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnMinJumlah))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 10, Short.MAX_VALUE))
                            .addComponent(lineBottom))
                        .addContainerGap())))
        );
        pnlTransaksiLayout.setVerticalGroup(
            pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiLayout.createSequentialGroup()
                .addComponent(lblTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIDTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddJumlah)
                        .addComponent(btnMinJumlah))
                    .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMetode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpMetode, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        tabelDataBarang.setBackground(new java.awt.Color(255, 255, 255));
        tabelDataBarang.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelDataBarang.setForeground(new java.awt.Color(0, 0, 0));
        tabelDataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"BG001", "Sprite", "Minuman", "12", "Rp. 4.000.00"},
                {"BG002", "Coca Cola", "Minuman", "10", "Rp. 4.000.00"},
                {"BG003", "Teh Pucuk", "Minuman", "10", "Rp. 4.000.00"},
                {"BG004", "Aqua 500ml", "Minuman", "5", "Rp. 5.000.00"},
                {"BG005", "Aqua 1L", "Minuman", "9", "Rp. 5.000.00"},
                {"BG006", "Indomilk", "Minuman", "11", "Rp. 8.000.00"},
                {"BG007", "Kertas Folio", "ATK", "250", "Rp. 250.00"},
                {"BG008", "Kertas HVS", "ATK", "420", "Rp. 250.00"},
                {"BG009", "Pulpen Snowman", "ATK", "23", "Rp. 2.500.00"},
                {"BG010", "Spidol Hitam", "ATK", "19", "Rp. 2.000.00"},
                {"BG011", "Spidol Merah", "ATK", "26", "Rp. 2.500.00"},
                {"BG012", "Spidol Biru", "ATK", "24", "Rp. 2.500.00"},
                {"BG013", "Yupi", "Snack", "45", "Rp. 2.500.00"},
                {"BG014", "Nabati Wafer", "Snack", "30", "Rp. 3.500.00"},
                {"BG015", "Oreo", "Snack", "60", "Rp. 2.000.00"},
                {"BG016", "Roti", "Snack", "27", "Rp. 1.000.00"},
                {"BG017", "Ichi Ocha 350ml", "Minuman", "18", "Rp. 2.000.00"}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Jenis", "Stok", "Harga Jual"
            }
        ));
        tabelDataBarang.setGridColor(new java.awt.Color(0, 0, 0));
        tabelDataBarang.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelDataBarang.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelDataBarang.getTableHeader().setReorderingAllowed(false);
        tabelDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataBarangMouseClicked(evt);
            }
        });
        tabelDataBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelDataBarangKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabelDataBarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCariPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCariBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCariBarang)
                            .addComponent(lblCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCariPembeli)
                            .addComponent(lblCariPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        
    }//GEN-LAST:event_btnBatalActionPerformed

    private void inpCariPembeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariPembeliKeyTyped
        String key = this.inpCariPembeli.getText();
        this.keywordPembeli = "WHERE id_pembeli LIKE '%"+key+"%' OR nama_pembeli LIKE '%"+key+"%'";
        this.updateTabelPembeli();
    }//GEN-LAST:event_inpCariPembeliKeyTyped

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnAddJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddJumlahActionPerformed
        if(Validation.isNumber(this.inpJumlah.getText())){
            this.stok = Integer.parseInt(this.barang.getStok(this.idBarang));
            this.jumlah = Integer.parseInt(inpJumlah.getText());
            this.jumlah++;
            if(jumlah <= stok){
                this.totalHarga = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
                this.totalHarga *= jumlah;
                this.inpJumlah.setText("" + jumlah);
                this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");                
            }else{
                Message.showWarning(this, "Jumlah produk melebihi stok!");
            }
        }
    }//GEN-LAST:event_btnAddJumlahActionPerformed

    private void btnMinJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinJumlahActionPerformed
        if(Validation.isNumber(this.inpJumlah.getText())){
            this.jumlah = Integer.parseInt(inpJumlah.getText());
            jumlah--;
            if(jumlah >= 1){
                this.totalHarga = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
                this.totalHarga *= jumlah;
                this.inpJumlah.setText("" + jumlah);
                this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");                
            }else{
                Message.showWarning(this, "Jumlah produk harus minimal 1!");
            }
        }
    }//GEN-LAST:event_btnMinJumlahActionPerformed

    private void btnBayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseEntered
        this.btnBayar.setIcon(Gambar.getIcon("ic-pembayaran-pay-entered.png"));
    }//GEN-LAST:event_btnBayarMouseEntered

    private void btnBayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseExited
        this.btnBayar.setIcon(Gambar.getIcon("ic-pembayaran-pay.png"));
    }//GEN-LAST:event_btnBayarMouseExited

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnAddJumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddJumlahMouseEntered
        this.btnAddJumlah.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnAddJumlahMouseEntered

    private void btnAddJumlahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddJumlahMouseExited
        this.btnAddJumlah.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnAddJumlahMouseExited

    private void btnMinJumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinJumlahMouseEntered
        this.btnMinJumlah.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnMinJumlahMouseEntered

    private void btnMinJumlahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinJumlahMouseExited
        this.btnMinJumlah.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMinJumlahMouseExited

    private void tabelDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataBarangMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data pembeli
        this.idSelectedBarang = this.tabelDataBarang.getValueAt(tabelDataBarang.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataBarangMouseClicked

    private void tabelDataBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataBarangKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelectedBarang = this.tabelDataBarang.getValueAt(tabelDataBarang.getSelectedRow() - 1, 0).toString();
            this.showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelectedBarang = this.tabelDataBarang.getValueAt(tabelDataBarang.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataBarangKeyPressed

    private void tabelDataPembeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataPembeliMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data pembeli
        this.idSelectedPembeli = this.tabelDataPembeli.getValueAt(tabelDataPembeli.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataPembeliMouseClicked

    private void tabelDataPembeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataPembeliKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelectedPembeli = this.tabelDataPembeli.getValueAt(tabelDataPembeli.getSelectedRow() - 1, 0).toString();
            this.showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelectedPembeli = this.tabelDataPembeli.getValueAt(tabelDataPembeli.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataPembeliKeyPressed

    private void inpCariBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariBarangKeyTyped
        String key = this.inpCariBarang.getText();
        this.keywordBarang = "WHERE id_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%'";
        this.updateTabelBarang();
    }//GEN-LAST:event_inpCariBarangKeyTyped

    private void inpJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpJumlahKeyTyped
        String jml = this.inpJumlah.getText();
        if(text.isNumber(jml)){
            this.stok = Integer.parseInt(this.barang.getStok(this.idBarang));
            this.jumlah = Integer.parseInt(inpJumlah.getText());
            if(jumlah <= stok){
                this.totalHarga = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
                this.totalHarga *= jumlah;
                this.inpJumlah.setText("" + jumlah);
                this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");                
            }else{
                Message.showWarning(this, "Jumlah produk melebihi stok!");
            }
        }else{
            this.inpJumlah.setText("1");
            Message.showWarning(this, "Jumlah barang harus berupa angka!");
        }
    }//GEN-LAST:event_inpJumlahKeyTyped

    private void inpJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpJumlahKeyReleased
        String jml = this.inpJumlah.getText();
        if(text.isNumber(jml)){
            this.stok = Integer.parseInt(this.barang.getStok(this.idBarang));
            this.jumlah = Integer.parseInt(inpJumlah.getText());
            if(jumlah <= stok){
                this.totalHarga = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
                this.totalHarga *= jumlah;
                this.inpJumlah.setText("" + jumlah);
                this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");                
            }else{
                Message.showWarning(this, "Jumlah produk melebihi stok!");
            }
        }else{
            this.inpJumlah.setText("1");
            Message.showWarning(this, "Jumlah barang harus berupa angka!");
        }
    }//GEN-LAST:event_inpJumlahKeyReleased

    private void inpCariPembeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariPembeliKeyReleased
        String key = this.inpCariPembeli.getText();
        this.keywordPembeli = "WHERE id_pembeli LIKE '%"+key+"%' OR nama_pembeli LIKE '%"+key+"%'";
        this.updateTabelPembeli();
    }//GEN-LAST:event_inpCariPembeliKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddJumlah;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnMinJumlah;
    private javax.swing.JTextField inpCariBarang;
    private javax.swing.JTextField inpCariPembeli;
    private javax.swing.JLabel inpID;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JComboBox inpMetode;
    private javax.swing.JLabel inpNamaBarang;
    private javax.swing.JLabel inpNamaPembeli;
    private javax.swing.JLabel inpNamaPetugas;
    private javax.swing.JLabel inpTanggal;
    private javax.swing.JLabel inpTotalHarga;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCariBarang;
    private javax.swing.JLabel lblCariPembeli;
    private javax.swing.JLabel lblIDTransaksi;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblNamaPembeli;
    private javax.swing.JLabel lblNamaPetugas;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JPanel pnlTransaksi;
    private javax.swing.JTable tabelDataBarang;
    private javax.swing.JTable tabelDataPembeli;
    // End of variables declaration//GEN-END:variables
}
