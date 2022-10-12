package com.window.panels;

import com.manage.Message;
import com.users.Users;
import java.sql.SQLException;

/**
 *
 * @author Gemastik Lightning
 */
public class TransaksiJual extends javax.swing.JPanel {

    private final Users user = new Users();
    
    private String keywordPembeli = "";
    
    public TransaksiJual() {
        initComponents();
        
        this.tabelDataBarang.setRowHeight(29);
        this.tabelDataBarang.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataBarang.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.tabelDataPembeli.setRowHeight(29);
        this.tabelDataPembeli.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelDataPembeli.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.updateTabel();
    }
    
    private Object[][] getData(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_pembeli, nama_pembeli, no_telp, alamat FROM pembeli " + keywordPembeli;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[user.getJumlahData("pembeli", keywordPembeli)][4];
            // mengeksekusi query
            user.res = user.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(user.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = user.res.getString("id_pembeli");
                obj[rows][1] = user.res.getString("nama_pembeli");
                obj[rows][2] = user.res.getString("no_telp");
                obj[rows][3] = user.res.getString("alamat");
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabel(){
        this.tabelDataPembeli.setModel(new javax.swing.table.DefaultTableModel(
            getData(),
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
        inpID = new javax.swing.JTextField();
        lblNamaKaryawan = new javax.swing.JLabel();
        inpNamaKaryawan = new javax.swing.JTextField();
        inpNamaPembeli = new javax.swing.JTextField();
        lblNamaPembeli = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        inpJumlah = new javax.swing.JTextField();
        lblNamaBarang = new javax.swing.JLabel();
        inpNamaBarang = new javax.swing.JTextField();
        lblTotalHarga = new javax.swing.JLabel();
        inpTotalHarga = new javax.swing.JTextField();
        btnAddJumlah = new javax.swing.JButton();
        btnMinJumlah = new javax.swing.JButton();
        lblMetode = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        inpTanggal = new javax.swing.JTextField();
        inpMetode = new javax.swing.JComboBox();
        lineBottom = new javax.swing.JSeparator();
        btnBayar = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
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

        lblCariPembeli.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCariPembeli.setForeground(new java.awt.Color(237, 12, 12));
        lblCariPembeli.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariPembeli.setText("Cari ID / Nama Pembeli :");

        inpCariPembeli.setBackground(new java.awt.Color(255, 255, 255));
        inpCariPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCariPembeli.setForeground(new java.awt.Color(0, 0, 0));
        inpCariPembeli.addKeyListener(new java.awt.event.KeyAdapter() {
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

        inpID.setBackground(new java.awt.Color(255, 255, 255));
        inpID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpID.setForeground(new java.awt.Color(0, 0, 0));
        inpID.setText("TR00001");

        lblNamaKaryawan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaKaryawan.setText("Nama Karyawan");

        inpNamaKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        inpNamaKaryawan.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpNamaKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaKaryawan.setText("Amirzan Fikri Prasetyo");

        inpNamaPembeli.setBackground(new java.awt.Color(255, 255, 255));
        inpNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaPembeli.setText("Mohammad Ilham Islamy");

        lblNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPembeli.setText("Nama Pembeli");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah");

        inpJumlah.setBackground(new java.awt.Color(255, 255, 255));
        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        inpJumlah.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlah.setText("3");

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBarang.setText("Nama Barang");

        inpNamaBarang.setBackground(new java.awt.Color(255, 255, 255));
        inpNamaBarang.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaBarang.setText("Pulpen Snowman");

        lblTotalHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalHarga.setText("Total Harga");

        inpTotalHarga.setBackground(new java.awt.Color(255, 255, 255));
        inpTotalHarga.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        inpTotalHarga.setText("Rp. 2.500.00");

        btnAddJumlah.setBackground(new java.awt.Color(34, 119, 237));
        btnAddJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddJumlah.setForeground(new java.awt.Color(255, 255, 255));
        btnAddJumlah.setText("+");

        btnMinJumlah.setBackground(new java.awt.Color(220, 41, 41));
        btnMinJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnMinJumlah.setForeground(new java.awt.Color(255, 255, 255));
        btnMinJumlah.setText("-");

        lblMetode.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(0, 0, 0));
        lblMetode.setText("Metode Bayar");

        lblTanggal.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggal.setText("Tanggal");

        inpTanggal.setBackground(new java.awt.Color(255, 255, 255));
        inpTanggal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpTanggal.setForeground(new java.awt.Color(0, 0, 0));
        inpTanggal.setText("05 Oktober 2022 | 17:05:20");

        inpMetode.setBackground(new java.awt.Color(255, 255, 255));
        inpMetode.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpMetode.setForeground(new java.awt.Color(0, 0, 0));
        inpMetode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Metode Pembayaran", "Cash", "E-Wallet" }));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));

        btnBayar.setBackground(new java.awt.Color(34, 119, 237));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-bayar.png"))); // NOI18N
        btnBayar.setText("Bayar");

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

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
                                        .addComponent(inpID, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpNamaPembeli))
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
                                        .addComponent(inpNamaBarang))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTransaksiLayout.createSequentialGroup()
                                        .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(inpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(inpID, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblIDTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpNamaKaryawan)
                    .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpNamaPembeli)
                    .addComponent(lblNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpNamaBarang)
                    .addComponent(lblNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddJumlah)
                        .addComponent(btnMinJumlah))
                    .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpTotalHarga)
                    .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMetode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpMetode, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpTanggal)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 141, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void inpCariPembeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariPembeliKeyTyped
        String key = this.inpCariPembeli.getText();
        this.keywordPembeli = "WHERE id_pembeli LIKE '%"+key+"%' OR nama_pembeli LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariPembeliKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddJumlah;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnMinJumlah;
    private javax.swing.JTextField inpCariBarang;
    private javax.swing.JTextField inpCariPembeli;
    private javax.swing.JTextField inpID;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JComboBox inpMetode;
    private javax.swing.JTextField inpNamaBarang;
    private javax.swing.JTextField inpNamaKaryawan;
    private javax.swing.JTextField inpNamaPembeli;
    private javax.swing.JTextField inpTanggal;
    private javax.swing.JTextField inpTotalHarga;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCariBarang;
    private javax.swing.JLabel lblCariPembeli;
    private javax.swing.JLabel lblIDTransaksi;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblNamaKaryawan;
    private javax.swing.JLabel lblNamaPembeli;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JPanel pnlTransaksi;
    private javax.swing.JTable tabelDataBarang;
    private javax.swing.JTable tabelDataPembeli;
    // End of variables declaration//GEN-END:variables
}
