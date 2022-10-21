package com.window.panels;

import com.manage.Barang;
import com.manage.Message;
import com.manage.Text;
import com.manage.Waktu;
import com.media.Audio;
import com.media.Gambar;
import com.sun.glass.events.KeyEvent;
import com.users.Supplier;
import com.users.Users;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gemastik Lightning
 */
public class TransaksiBeli extends javax.swing.JPanel {
    
    
    private final Users user = new Users();
    
    private final Supplier supplier = new Supplier();
    
    private final Barang barang = new Barang();
    
    private final Waktu waktu = new Waktu();
    
    private final com.manage.ManageTransaksiBeli trb = new com.manage.ManageTransaksiBeli();
    
    private final Text text = new Text();
    
    private String keywordSupplier = "", keywordBarang = "", idSelectedSupplier, idSelectedBarang;
    
    private String idTr, namaTr, namaSupplier, namaBarang, idPetugas, idSupplier, idBarang, metodeBayar, tglNow;
    
    private int jumlah = 1, hargaBeli, totalHarga = 0, stok = 0;
    
    public TransaksiBeli() {
        initComponents();
        
        this.idTr = this.trb.createIDTransaksi();
        
        this.inpJumlah.setEditable(false);
        this.inpID.setText("<html><p>:&nbsp;"+this.trb.createIDTransaksi()+"</p></html>");
        this.inpNamaPetugas.setText("<html><p>:&nbsp;"+this.user.getCurrentLoginName()+"</p></html>");
        
        this.btnAddJumlah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnMinJumlah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBeli.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelDataBarang.setRowHeight(29);
        this.tabelDataBarang.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataBarang.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.tabelDataSupplier.setRowHeight(29);
        this.tabelDataSupplier.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataSupplier.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.updateTabelSupplier();
        this.updateTabelBarang();
        
        // mengupdate waktu
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(isVisible()){
                        tglNow = waktu.getUpdateTime();
                        inpTanggal.setText("<html><p>:&nbsp;"+tglNow+"</p></html>");
                        Thread.sleep(100);
                    }
                }catch(InterruptedException ex){
                    Message.showException(this, "Terjadi Kesalahan Saat Mengupdate Tanggal!\n" + ex.getMessage(), ex, true);
                }
            }
        }).start();
    }
    
    private Object[][] getDataSupplier(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_supplier, nama_supplier, no_telp, alamat FROM supplier " + keywordSupplier;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[supplier.getJumlahData("supplier", keywordSupplier)][4];
            // mengeksekusi query
            supplier.res = supplier.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(supplier.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = supplier.res.getString("id_supplier");
                obj[rows][1] = supplier.res.getString("nama_supplier");
                obj[rows][2] = supplier.res.getString("no_telp");
                obj[rows][3] = supplier.res.getString("alamat");
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabelSupplier(){
        this.tabelDataSupplier.setModel(new javax.swing.table.DefaultTableModel(
            getDataSupplier(),
            new String [] {
                "ID Supplier", "Nama Supplier", "No Telephone", "Alamat"
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
            String sql = "SELECT id_barang, nama_barang, jenis_barang, stok, harga_beli FROM barang " + keywordBarang;
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
                obj[rows][4] = text.toMoneyCase(barang.res.getString("harga_beli"));
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
                "ID Barang", "Nama Barang", "Jenis Barang", "Stok", "Harga Beli"
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
    
    private boolean isSelectedSupplier(){
        return this.tabelDataSupplier.getSelectedRow() > - 1;
    }
    
    private boolean isSelectedBarang(){
        return this.tabelDataBarang.getSelectedRow() > - 1;
    }
    
    private void showDataSupplier(){
        
        // cek akapah ada data supplier yg dipilih
        if(this.isSelectedSupplier()){
            // mendapatkan data supplier
            this.idSupplier = this.idSelectedSupplier;
            this.namaSupplier = this.supplier.getNama(this.idSupplier);
            
            // menampilkan data pembeli
            this.inpNamaSupplier.setText("<html><p>:&nbsp;"+this.namaSupplier+"</p></html>");
        }
    }
    
    private void showDataBarang(){
        
        // cek apakah ada data barang yang dipilih
        if(this.isSelectedBarang()){
            // mendapatkan data barang
            this.idBarang = this.idSelectedBarang;
            this.namaBarang = text.toCapitalize(this.barang.getNamaBarang(this.idBarang));
            this.jumlah = 1;
            this.stok = Integer.parseInt(this.barang.getStok(this.idBarang));
            this.hargaBeli = Integer.parseInt(this.barang.getHargaBeli(this.idBarang));
            this.totalHarga = this.hargaBeli;
            
            // menampilkan data barang
            this.inpNamaBarang.setText("<html><p>:&nbsp;"+this.namaBarang+"</p></html>");
            this.inpJumlah.setText(Integer.toString(this.jumlah));
            this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCariBarang = new javax.swing.JLabel();
        inpCariBarang = new javax.swing.JTextField();
        lblCariSupplier = new javax.swing.JLabel();
        inpCariSupplier = new javax.swing.JTextField();
        pnlTransaksi = new javax.swing.JPanel();
        lblTransaksiBeli = new javax.swing.JLabel();
        lblIDTransaksi = new javax.swing.JLabel();
        lblNamaKaryawan = new javax.swing.JLabel();
        lblNamaSupplier = new javax.swing.JLabel();
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
        btnBeli = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        inpID = new javax.swing.JLabel();
        inpNamaPetugas = new javax.swing.JLabel();
        inpNamaSupplier = new javax.swing.JLabel();
        inpNamaBarang = new javax.swing.JLabel();
        inpTotalHarga = new javax.swing.JLabel();
        inpTanggal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelDataBarang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelDataSupplier = new javax.swing.JTable();

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpCariBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariBarangKeyTyped(evt);
            }
        });

        lblCariSupplier.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCariSupplier.setForeground(new java.awt.Color(237, 12, 12));
        lblCariSupplier.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariSupplier.setText("Cari ID / Nama Supplier :");

        inpCariSupplier.setBackground(new java.awt.Color(255, 255, 255));
        inpCariSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCariSupplier.setForeground(new java.awt.Color(0, 0, 0));
        inpCariSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpCariSupplierKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariSupplierKeyTyped(evt);
            }
        });

        pnlTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        pnlTransaksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(11, 114, 238), 3));

        lblTransaksiBeli.setBackground(new java.awt.Color(11, 114, 238));
        lblTransaksiBeli.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTransaksiBeli.setForeground(new java.awt.Color(255, 255, 255));
        lblTransaksiBeli.setText("   Transaksi Pembelian");
        lblTransaksiBeli.setOpaque(true);

        lblIDTransaksi.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIDTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lblIDTransaksi.setText("ID Transaksi");

        lblNamaKaryawan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaKaryawan.setText("Nama Petugas");

        lblNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaSupplier.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSupplier.setText("Nama Supplier");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah");

        inpJumlah.setBackground(new java.awt.Color(255, 255, 255));
        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpJumlah.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlah.setText("1");

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBarang.setText("Nama Barang");

        lblTotalHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalHarga.setText("Total Harga");

        btnAddJumlah.setBackground(new java.awt.Color(34, 119, 237));
        btnAddJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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
        btnMinJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        btnBeli.setBackground(new java.awt.Color(34, 119, 237));
        btnBeli.setForeground(new java.awt.Color(255, 255, 255));
        btnBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-pay.png"))); // NOI18N
        btnBeli.setText("Beli");
        btnBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBeliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBeliMouseExited(evt);
            }
        });
        btnBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeliActionPerformed(evt);
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
        inpID.setText(": ");

        inpNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaPetugas.setText(": ");

        inpNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpNamaSupplier.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaSupplier.setText(":");

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
            .addComponent(lblTransaksiBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnBeli)
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
                                        .addComponent(inpID, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addComponent(inpTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 10, Short.MAX_VALUE))
                            .addComponent(lineBottom))
                        .addContainerGap())))
        );
        pnlTransaksiLayout.setVerticalGroup(
            pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiLayout.createSequentialGroup()
                .addComponent(lblTransaksiBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpNamaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        tabelDataSupplier.setBackground(new java.awt.Color(255, 255, 255));
        tabelDataSupplier.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelDataSupplier.setForeground(new java.awt.Color(0, 0, 0));
        tabelDataSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"SP001", "Asrul Achmad Asrofi", "086653837718", "Nganjuk, Indonesia"},
                {"SP002", "Dimas Abimanyu", "084884582640", "Nganjuk, Indonesia"},
                {"SP003", "Farel Kurniawan", "080548653287", "Nganjuk, Indonesia"},
                {"SP004", "Feby Ayu Dyah Pitaloka", "082203072218", "Jombang, Indonesia"},
                {"SP005", "Afif Fitra Nugroho", "087676072232", "Kediri, Indonesia"},
                {"SP006", "Abdulloh Kafabi", "086134471867", "Nganjuk, Indonesia"},
                {"SP007", "Deni Yuda Mahendra", "083851907614", "Kediri, Indonesia"},
                {"SP008", "Hendro Wawan Setiyo", "085790836149", "Nganjuk, Indonesia"},
                {"SP009", "Mahendra Putra Pratama", "085775502026", "Nganjuk, Indonesia"},
                {"SP010", "Mohamad Rodzikul Hidayatulloh", "0895395057931", "Nganjuk, Indonesia"},
                {"SP011", "Arfan Ardiansyah", "085706595022", "Jombang, Indonesia"},
                {"SP012", "Flavia Reiska Januari Putri", "0881026542584", "Nganjuk, Indonesia"},
                {"SP013", "Kartika Dewi Claudia", "087824060309", "Kediri, Indonesia"},
                {"SP014", "Tegar Pratama Alfianto", "085816555034", "Kediri, Indonesia"},
                {"SP015", "Sultan Yorgi Praba Mahendra", "081336252154", "Nganjuk, Indonesia"},
                {"SP016", "Renaldi Gilang Prasetyo", "087779212229", "Jombang, Indonesia"},
                {"SP017", "Rahmat Aji Wibowo", "082333174750", "Kediri, Indonesia"},
                {"SP018", "Nyofrizal Teguh Santoso", "083440455377", "Nganjuk, Indonesia"},
                {"SP019", "Mohammad Bagas Pratama", "082887145367", "Nganjuk, Indonesia"},
                {"SP020", "Kurniawan Adi Candra", "088404705857", "Nganjuk, Indonesia"},
                {"SP021", "Amanda Rahmawati", "086460356514", "Nganjuk, Indonesia"},
                {"SP022", "Esly Reeka Augustinyo", "085232007805", "Nganjuk, Indonesia"},
                {"SP023", "Mokhammad Mansor Kornianto", "089654770601", "Jombang, Indonesia"}
            },
            new String [] {
                "ID Supplier", "Nama Supplier", "No Telp", "Alamat"
            }
        ));
        tabelDataSupplier.setGridColor(new java.awt.Color(0, 0, 0));
        tabelDataSupplier.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelDataSupplier.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelDataSupplier.getTableHeader().setReorderingAllowed(false);
        tabelDataSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataSupplierMouseClicked(evt);
            }
        });
        tabelDataSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelDataSupplierKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabelDataSupplier);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCariSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCariBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 129, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCariBarang)
                            .addComponent(lblCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCariSupplier)
                            .addComponent(lblCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        int status;
        Audio.play(Audio.SOUND_INFO);
        status = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin membatalkan transaksi?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        switch(status){
            case JOptionPane.YES_OPTION : {
                // mereset tabel
                this.updateTabelSupplier();
                this.updateTabelBarang();

                // mereset input
                this.inpNamaSupplier.setText("<html><p>:&nbsp;</p></html>");
                this.inpNamaBarang.setText("<html><p>:&nbsp;</p></html>");
                this.inpJumlah.setText("1");
                this.inpMetode.setSelectedIndex(0);
                this.inpTotalHarga.setText("<html><p>:&nbsp;</p></html>");
                break;
            }
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeliActionPerformed
        this.idTr = this.trb.createIDTransaksi();
        this.idPetugas = this.user.getCurrentLogin();
        
        // cek apakah user sudah memilih supplier
        if(this.tabelDataSupplier.getSelectedRow() > -1){
            // mendapatkan data supplier
            this.idSupplier = this.tabelDataSupplier.getValueAt(this.tabelDataSupplier.getSelectedRow(), 0).toString();
            this.namaSupplier = this.supplier.getNama(this.idSupplier);
        }else{
            Message.showWarning(this, "Tidak ada data supplier yang dipilih!");
            return;
        }
        
        // cek apakah user sudah memilih data barang
        if(this.tabelDataBarang.getSelectedRow() > -1){
            // mendapatkan data barang
            this.idBarang = this.tabelDataBarang.getValueAt(this.tabelDataBarang.getSelectedRow(), 0).toString();
            this.namaBarang = this.barang.getNamaBarang(this.idBarang);
            this.jumlah = Integer.parseInt(this.inpJumlah.getText());
            this.hargaBeli = Integer.parseInt(this.barang.getHargaBeli(this.idBarang));
            this.totalHarga = hargaBeli * jumlah;
            switch(this.inpMetode.getSelectedIndex()){
                case 1 : this.metodeBayar = "CASH"; break;
                case 2 : this.metodeBayar = "E-WALLET"; break;
                default : Message.showWarning(this, "Silahkan pilih metode pembayaran terlebih dahulu!"); return;
            }
            this.namaTr = String.format("%s membeli %s sebanyak %s dengan total harga %s", this.namaSupplier, this.namaBarang, this.jumlah, text.toMoneyCase(""+this.totalHarga));
        }else{
            Message.showWarning(this, "Tidak ada data barang yang dipilih!");
            return;
        }
        
        System.out.println("");
        System.out.println("ID Transaksi : " + this.idTr);
        System.out.println("Nama Transaksi : " + this.namaTr);
        System.out.println("ID Petugas : " + this.idPetugas);
        System.out.println("ID Supplier : " + this.idSupplier);
        System.out.println("ID Barang : " + this.idBarang);
        System.out.println("Jumlah : " + this.jumlah);
        System.out.println("Metode Bayar : " + this.metodeBayar);
        System.out.println("Total Harga : " + this.totalHarga);
        System.out.println("Tanggal : " + this.tglNow);
        System.out.println("");
    }//GEN-LAST:event_btnBeliActionPerformed

    private void btnAddJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddJumlahActionPerformed
        // cek apakah ada data yang dipilih
        if(this.isSelectedBarang()){
            // menambahkan jumlah
            this.jumlah++;
            // mengupdate total harga
            this.totalHarga = 0;
            this.totalHarga = this.hargaBeli * this.jumlah;

            // menampilkan data jumlah dan total harga
            this.inpJumlah.setText(Integer.toString(this.jumlah));
            this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");
        }else{
            Message.showWarning(this, "Tidak ada data barang yang dipilih!");
        }
    }//GEN-LAST:event_btnAddJumlahActionPerformed

    private void btnMinJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinJumlahActionPerformed
        // cek apakah ada data yang dipilih
        if(this.isSelectedBarang()){
            // mengurangi jumlah
            this.jumlah--;
            // cek apakah jumlah lebih dari 0
            if(this.jumlah > 0){
                // mengupdate total harga
                this.totalHarga = 0;
                this.totalHarga = this.hargaBeli * this.jumlah;
                
                // menampilkan data jumlah dan total harga
                this.inpJumlah.setText(Integer.toString(this.jumlah));
                this.inpTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(Integer.toString(this.totalHarga))+"</p></html>");
            }else{
                Message.showWarning(this, String.format("Jumlah barang tidak boleh kurang dari 1!", jumlah, stok));
            }
        }else{
            Message.showWarning(this, "Tidak ada data barang yang dipilih!");
        }
    }//GEN-LAST:event_btnMinJumlahActionPerformed

    private void btnBeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeliMouseEntered
        this.btnBeli.setIcon(Gambar.getIcon("ic-pembayaran-pay-entered.png"));
    }//GEN-LAST:event_btnBeliMouseEntered

    private void btnBeliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeliMouseExited
        this.btnBeli.setIcon(Gambar.getIcon("ic-pembayaran-pay.png"));
    }//GEN-LAST:event_btnBeliMouseExited

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
        this.showDataBarang();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataBarangMouseClicked

    private void tabelDataBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataBarangKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelectedBarang = this.tabelDataBarang.getValueAt(tabelDataBarang.getSelectedRow() - 1, 0).toString();
//            this.showDataBarang();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelectedBarang = this.tabelDataBarang.getValueAt(tabelDataBarang.getSelectedRow() + 1, 0).toString();
//            this.showDataBarang();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataBarangKeyPressed

    private void tabelDataSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataSupplierMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data supplier
        this.idSelectedSupplier = this.tabelDataSupplier.getValueAt(tabelDataSupplier.getSelectedRow(), 0).toString();
        this.showDataSupplier();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataSupplierMouseClicked

    private void tabelDataSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataSupplierKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelectedSupplier = this.tabelDataSupplier.getValueAt(tabelDataSupplier.getSelectedRow() - 1, 0).toString();
            this.showDataSupplier();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelectedSupplier = this.tabelDataSupplier.getValueAt(tabelDataSupplier.getSelectedRow() + 1, 0).toString();
            this.showDataSupplier();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataSupplierKeyPressed

    private void inpCariBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariBarangKeyTyped
        String key = this.inpCariBarang.getText();
        this.keywordBarang = "WHERE id_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%'";
        this.updateTabelBarang();
    }//GEN-LAST:event_inpCariBarangKeyTyped

    private void inpCariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariBarangKeyReleased
        String key = this.inpCariBarang.getText();
        this.keywordBarang = "WHERE id_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%'";
        this.updateTabelBarang();
    }//GEN-LAST:event_inpCariBarangKeyReleased

    private void inpCariSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariSupplierKeyTyped
        String key = this.inpCariSupplier.getText();
        this.keywordSupplier = "WHERE id_supplier LIKE '%"+key+"%' OR nama_supplier LIKE '%"+key+"%'";
        this.updateTabelSupplier();
    }//GEN-LAST:event_inpCariSupplierKeyTyped

    private void inpCariSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariSupplierKeyReleased
        String key = this.inpCariSupplier.getText();
        this.keywordSupplier = "WHERE id_supplier LIKE '%"+key+"%' OR nama_supplier LIKE '%"+key+"%'";
        this.updateTabelSupplier();
    }//GEN-LAST:event_inpCariSupplierKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddJumlah;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBeli;
    private javax.swing.JButton btnMinJumlah;
    private javax.swing.JTextField inpCariBarang;
    private javax.swing.JTextField inpCariSupplier;
    private javax.swing.JLabel inpID;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JComboBox inpMetode;
    private javax.swing.JLabel inpNamaBarang;
    private javax.swing.JLabel inpNamaPetugas;
    private javax.swing.JLabel inpNamaSupplier;
    private javax.swing.JLabel inpTanggal;
    private javax.swing.JLabel inpTotalHarga;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCariBarang;
    private javax.swing.JLabel lblCariSupplier;
    private javax.swing.JLabel lblIDTransaksi;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblNamaKaryawan;
    private javax.swing.JLabel lblNamaSupplier;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JLabel lblTransaksiBeli;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JPanel pnlTransaksi;
    private javax.swing.JTable tabelDataBarang;
    private javax.swing.JTable tabelDataSupplier;
    // End of variables declaration//GEN-END:variables
}
