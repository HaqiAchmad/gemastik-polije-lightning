package com.window.panels;

import com.manage.Message;
import com.manage.Waktu;
import java.awt.Font;
import java.sql.SQLException;

/**
 *
 * @author Gemastik Lightning
 */
public class TransaksiBeli extends javax.swing.JPanel {
    private final Waktu waktu = new Waktu();
    private String time;
    public TransaksiBeli() {
        initComponents();
        // mengupdate waktu
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(isVisible()){
//                        System.out.println("update");
                        tanggalLabel.setText(waktu.getUpdateWaktu() + "  ");
                        time = waktu.getUpdateWaktu();
                        Thread.sleep(100);
                    }
                }catch(InterruptedException ex){
                    Message.showException(this, "Terjadi Kesalahan Saat Mengupdate Tanggal!\n" + ex.getMessage(), ex, true);
                }
            }
        }).start();
        
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
        
    }
//    private Object[][] getData(){
//        try{
//            Object[][] obj;
//            int rows = 0;
//            String sql = "SELECT id_pembeli, nama_pembeli, no_telp, alamat FROM pembeli " + keyword;
//            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
//            obj = new Object[pembeli.getJumlahData("pembeli", keyword)][4];
//            // mengeksekusi query
//            pembeli.res = pembeli.stat.executeQuery(sql);
//            // mendapatkan semua data yang ada didalam tabel
//            while(pembeli.res.next()){
//                // menyimpan data dari tabel ke object
//                obj[rows][0] = pembeli.res.getString("id_pembeli");
//                obj[rows][1] = pembeli.res.getString("nama_pembeli");
//                obj[rows][2] = pembeli.res.getString("no_telp");
//                obj[rows][3] = pembeli.res.getString("alamat");
//                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
//            }
//            return obj;
//        }catch(SQLException ex){
//            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
//        }
//        return null;
//    }
    
//    private void update(){
//    }
    
//    private void showData(){
//        // mendapatkan data
//        this.namaPembeli = pembeli.getNama(this.idSelected);
//        this.noTelp = text.toTelephoneCase(pembeli.getNoTelp(this.idSelected));
//        this.alamat = pembeli.getAlamat(this.idSelected);
//        
//        // menampilkan data
//        this.valIDPembeli.setText("<html><p>:&nbsp;"+idSelected+"</p></html>");
//        this.valNamaPembeli.setText("<html><p>:&nbsp;"+namaPembeli+"</p></html>");
//        this.valNoTelp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;"+noTelp+"</p></html>");
//        this.valAlamat.setText("<html><p>:&nbsp;"+alamat+"</p></html>");
//        
//        // menampilkan chart
//        this.chart.showPieChart(this.pieChart, "Produk yang dibeli " + this.namaPembeli, new Font("Ebrima", 1, 18), 15, 20, 60, 0);
//    }
    
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
        idLabel = new javax.swing.JLabel();
        namaLabel = new javax.swing.JLabel();
        nama1Label = new javax.swing.JLabel();
        nama2Label = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        tanggalLabel = new javax.swing.JLabel();
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

        lblCariSupplier.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCariSupplier.setForeground(new java.awt.Color(237, 12, 12));
        lblCariSupplier.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariSupplier.setText("Cari ID / Nama Supplier :");

        inpCariSupplier.setBackground(new java.awt.Color(255, 255, 255));
        inpCariSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCariSupplier.setForeground(new java.awt.Color(0, 0, 0));

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
        lblNamaKaryawan.setText("Nama Karyawan");

        lblNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaSupplier.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSupplier.setText("Nama Supplier");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah");

        inpJumlah.setBackground(new java.awt.Color(255, 255, 255));
        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpJumlah.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlah.setText("12");

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
        btnBeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-bayar.png"))); // NOI18N
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

        idLabel.setText("jLabel1");

        namaLabel.setText("jLabel2");

        nama1Label.setText("jLabel3");

        nama2Label.setText("jLabel4");

        totalLabel.setText("jLabel5");

        tanggalLabel.setText("jLabel6");

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
                                        .addComponent(idLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(namaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nama1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addComponent(nama2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tanggalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addComponent(idLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(nama2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMetode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpMetode, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(tanggalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeliActionPerformed
        
    }//GEN-LAST:event_btnBeliActionPerformed

    private void btnAddJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddJumlahActionPerformed
        
    }//GEN-LAST:event_btnAddJumlahActionPerformed

    private void btnMinJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinJumlahActionPerformed
        
    }//GEN-LAST:event_btnMinJumlahActionPerformed

    private void btnBeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeliMouseEntered
        
    }//GEN-LAST:event_btnBeliMouseEntered

    private void btnBeliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeliMouseExited
        
    }//GEN-LAST:event_btnBeliMouseExited

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnAddJumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddJumlahMouseEntered
        
    }//GEN-LAST:event_btnAddJumlahMouseEntered

    private void btnAddJumlahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddJumlahMouseExited
        
    }//GEN-LAST:event_btnAddJumlahMouseExited

    private void btnMinJumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinJumlahMouseEntered
        
    }//GEN-LAST:event_btnMinJumlahMouseEntered

    private void btnMinJumlahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinJumlahMouseExited
        
    }//GEN-LAST:event_btnMinJumlahMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddJumlah;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBeli;
    private javax.swing.JButton btnMinJumlah;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField inpCariBarang;
    private javax.swing.JTextField inpCariSupplier;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JComboBox inpMetode;
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
    private javax.swing.JLabel nama1Label;
    private javax.swing.JLabel nama2Label;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JPanel pnlTransaksi;
    private javax.swing.JTable tabelDataBarang;
    private javax.swing.JTable tabelDataSupplier;
    private javax.swing.JLabel tanggalLabel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
