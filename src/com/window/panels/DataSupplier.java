package com.window.panels;

import com.data.db.DatabaseTables;
import com.manage.Barang;
import com.manage.Internet;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.sun.glass.events.KeyEvent;
import com.users.Supplier;
import com.window.dialogs.InputSupplier;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Gemastik Lightning
 */
public class DataSupplier extends javax.swing.JPanel {
    
    private final Supplier supplier = new Supplier();
    
    private final Barang barang = new Barang();
    
    private final Internet net = new Internet();
    
    private final Text text = new Text();
    
    private String idSelected = "", keyword = "", namaSupplier, noTelp, alamat, ttBrg, ttlUang, last;
    
    
    public DataSupplier() {
        initComponents();
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));

        this.tabelHistori.setRowHeight(29);
        this.tabelHistori.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelHistori.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.btnAdd.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDel.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        JLabel[] values = {
          this.valIDSupplier, this.valNamaSupplier, this.valNoTelp, this.valAlamat, 
          this.valBrgSupplier, this.valUang, valLast
        };
        
        for(JLabel lbl : values){
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
        
        this.updateTabel();
        this.updateTabelHistori();
        }
    }

    private Object[][] getData(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_supplier, nama_supplier, no_telp, alamat FROM supplier " + keyword;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[supplier.getJumlahData("supplier", keyword)][4];
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
    
    private void updateTabel(){
        this.tabelData.setModel(new javax.swing.table.DefaultTableModel(
            getData(),
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
    
    private Object[][] getDataHistori(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_tr_beli, id_barang, jumlah_brg, total_hrg FROM transaksi_beli where id_supplier = '"+this.idSelected+"'";
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[supplier.getJumlahData("transaksi_beli", "where id_supplier = '"+this.idSelected+"'")][4];
            // mengeksekusi query
            supplier.res = supplier.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(supplier.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = supplier.res.getString("id_tr_beli");
                obj[rows][1] = barang.getNamaBarang(supplier.res.getString("id_barang"));
                obj[rows][2] = supplier.res.getString("jumlah_brg");
                obj[rows][3] = text.toMoneyCase(supplier.res.getString("total_hrg"));
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabelHistori(){
        this.tabelHistori.setModel(new javax.swing.table.DefaultTableModel(
            getDataHistori(),
            new String [] {
                "ID Penggeluaran", "Nama Barang", "Jumlah", "Total Harga"
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
    
    public void showData(){
        // mendapatkan data
        this.namaSupplier = supplier.getNama(this.idSelected);
        this.noTelp = text.toTelephoneCase(supplier.getNoTelp(this.idSelected));
        this.alamat = supplier.getAlamat(this.idSelected);
        this.ttBrg = ""+this.supplier.getJumlahData(DatabaseTables.TRANSAKSI_BELI.name(), String.format("WHERE id_supplier='%s'", this.idSelected));
        this.ttlUang = text.toMoneyCase(""+this.supplier.sumData(DatabaseTables.TRANSAKSI_BELI.name(), "total_hrg", String.format("where id_supplier = '%s'", this.idSelected)));
        this.last = this.text.toDateCase(this.supplier.getData(DatabaseTables.TRANSAKSI_BELI.name(), "tanggal", "WHERE id_supplier = '" + this.idSelected + "'  ORDER BY tanggal DESC"));
        
        // menampilkan data
        this.valIDSupplier.setText("<html><p>:&nbsp;"+idSelected+"</p></html>");
        this.valNamaSupplier.setText("<html><p>:&nbsp;"+namaSupplier+"</p></html>");
        this.valNoTelp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;"+noTelp+"</p></html>");
        this.valAlamat.setText("<html><p>:&nbsp;"+alamat+"</p></html>");
        this.valBrgSupplier.setText("<html><p>:&nbsp;"+ttBrg+" Barang</p></html>");
        this.valUang.setText("<html><p>:&nbsp;"+ttlUang+"</p></html>");
        this.valLast.setText("<html><p>:&nbsp;"+last+"</p></html>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelHistori = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        lblCari = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        valDataSupplier = new javax.swing.JPanel();
        lblDataSupplier = new javax.swing.JLabel();
        lblIDSupplier = new javax.swing.JLabel();
        lblNamaSupplier = new javax.swing.JLabel();
        lblNoTelp = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBrgSupplier = new javax.swing.JLabel();
        lblUang = new javax.swing.JLabel();
        lblLast = new javax.swing.JLabel();
        valIDSupplier = new javax.swing.JLabel();
        valNamaSupplier = new javax.swing.JLabel();
        valNoTelp = new javax.swing.JLabel();
        valAlamat = new javax.swing.JLabel();
        valBrgSupplier = new javax.swing.JLabel();
        valUang = new javax.swing.JLabel();
        valLast = new javax.swing.JLabel();
        lblHistori = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        tabelHistori.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelHistori.setForeground(new java.awt.Color(0, 0, 0));
        tabelHistori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"PG0001", "Aqua 1 L", "5", "Rp. 15.000"},
                {"PG0002", "Nabati Wafer", "7", "Rp. 17.500"},
                {"PG0003", "Pulpen Snowman", "14", "Rp. 35.000"},
                {null, null, null, null}
            },
            new String [] {
                "ID Pengeluaran", "Nama Barang", "Jumlah", "Total Harga"
            }
        ));
        tabelHistori.setGridColor(new java.awt.Color(0, 0, 0));
        tabelHistori.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelHistori.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelHistori.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelHistori);

        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpCariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari ID / Nama Supplier :");

        btnAdd.setBackground(new java.awt.Color(41, 180, 50));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnAdd.setText("Tambah Data");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

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

        btnDel.setBackground(new java.awt.Color(220, 41, 41));
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnDel.setText("Hapus Data");
        btnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelMouseExited(evt);
            }
        });
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        valDataSupplier.setBackground(new java.awt.Color(255, 255, 255));
        valDataSupplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 3));

        lblDataSupplier.setBackground(new java.awt.Color(11, 114, 238));
        lblDataSupplier.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataSupplier.setForeground(new java.awt.Color(255, 255, 255));
        lblDataSupplier.setText("  Data Supplier");
        lblDataSupplier.setOpaque(true);

        lblIDSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDSupplier.setForeground(new java.awt.Color(0, 0, 0));
        lblIDSupplier.setText("ID Supplier");

        lblNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaSupplier.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSupplier.setText("Nama Supplier");

        lblNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoTelp.setText("No Telepon");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        lblBrgSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBrgSupplier.setForeground(new java.awt.Color(0, 0, 0));
        lblBrgSupplier.setText("Barang Dari Supplier");

        lblUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUang.setForeground(new java.awt.Color(0, 0, 0));
        lblUang.setText("Uang Dikeluarkan");

        lblLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLast.setForeground(new java.awt.Color(0, 0, 0));
        lblLast.setText("Pembelian Terakhir");

        valIDSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDSupplier.setForeground(new java.awt.Color(0, 0, 0));
        valIDSupplier.setText(":");

        valNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaSupplier.setForeground(new java.awt.Color(0, 0, 0));
        valNamaSupplier.setText(":");

        valNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        valNoTelp.setText(":");
        valNoTelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNoTelpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNoTelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNoTelpMouseExited(evt);
            }
        });

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat.setText(":");

        valBrgSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valBrgSupplier.setForeground(new java.awt.Color(0, 0, 0));
        valBrgSupplier.setText(":");

        valUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valUang.setForeground(new java.awt.Color(0, 0, 0));
        valUang.setText(":");

        valLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valLast.setForeground(new java.awt.Color(0, 0, 0));
        valLast.setText(":");

        javax.swing.GroupLayout valDataSupplierLayout = new javax.swing.GroupLayout(valDataSupplier);
        valDataSupplier.setLayout(valDataSupplierLayout);
        valDataSupplierLayout.setHorizontalGroup(
            valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(valDataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLast, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblUang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBrgSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valIDSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(valBrgSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valUang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        valDataSupplierLayout.setVerticalGroup(
            valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valDataSupplierLayout.createSequentialGroup()
                .addComponent(lblDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDSupplier)
                    .addComponent(valIDSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaSupplier)
                    .addComponent(valNamaSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoTelp)
                    .addComponent(valNoTelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlamat)
                    .addComponent(valAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBrgSupplier)
                    .addComponent(valBrgSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUang)
                    .addComponent(valUang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(valDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLast)
                    .addComponent(valLast))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        lblHistori.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblHistori.setForeground(new java.awt.Color(237, 12, 12));
        lblHistori.setText("Histori Supplier");

        tabelData.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Supplier", "Nama Supplier", "Alamat"
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
        jScrollPane3.setViewportView(tabelData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblHistori, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valDataSupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                                .addGap(3, 3, 3)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHistori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int status;
        boolean delete;
        
        // mengecek apakah ada data yang dipilih atau tidak
        if(tabelData.getSelectedRow() > -1){
            // membuka confirm dialog untuk menghapus data
            Audio.play(Audio.SOUND_INFO);
            status = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus '" + this.namaSupplier + "' ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

            // mengecek pilihan dari supplier
            switch(status){
                // jika yes maka data akan dihapus
                case JOptionPane.YES_OPTION : 
                    // menghapus data supplier
                    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    delete = this.supplier.deleteSupplier(this.idSelected);
                    // mengecek apakah data supplier berhasil terhapus atau tidak
                    if(delete){
                        Message.showInformation(this, "Data berhasil dihapus!");
                        // mengupdate tabel
                        this.updateTabel();
                    }else{
                        Message.showInformation(this, "Data gagal dihapus!");
                    }
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    break;
            }            
        }else{
            Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // membuka window input supplier
        Audio.play(Audio.SOUND_INFO);
        InputSupplier tbh = new InputSupplier(null, true, null);
        tbh.setVisible(true);
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // mengecek apakah supplier jadi menambahkan data atau tidak
        if(tbh.isUpdated()){
            // mengupdate tabel
            this.updateTabel();
            this.tabelData.setRowSelectionInterval(this.tabelData.getRowCount()-1, this.tabelData.getRowCount()-1);
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // mengecek apakah ada data yang dipilih atau tidak
        if(tabelData.getSelectedRow() > -1){
            // membuka window input supplier
            Audio.play(Audio.SOUND_INFO);
            InputSupplier tbh = new InputSupplier(null, true, this.idSelected);
            tbh.setVisible(true);

            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            // mengecek apakah supplier jadi mengedit data atau tidak
            if(tbh.isUpdated()){
                // mengupdate tabel dan menampilkan ulang data
                this.updateTabel();
                this.showData();
            }
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
        }  
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        this.btnAdd.setIcon(Gambar.getIcon("ic-data-tambah-entered.png"));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        this.btnAdd.setIcon(Gambar.getIcon("ic-data-tambah.png"));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnDelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseEntered
        this.btnDel.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnDelMouseEntered

    private void btnDelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseExited
        this.btnDel.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnDelMouseExited

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data supplier
        this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow(), 0).toString();
        this.showData();
        this.updateTabelHistori();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataMouseClicked

    private void tabelDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() - 1, 0).toString();
            this.showData();
            this.updateTabelHistori();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() + 1, 0).toString();
            this.showData();
            this.updateTabelHistori();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataKeyPressed

    private void valNoTelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoTelpMouseClicked

        String nomor = this.noTelp.substring(1).replaceAll(" ", "").replaceAll("-", "");
        if(net.isConnectInternet()){
            try {
                net.openLink("https://wa.me/"+nomor);
            } catch (IOException | URISyntaxException ex) {
                Message.showException(this, ex, true);
            }
        }else{
            Message.showWarning(this, "Tidak terhubung ke Internet!", true);
        }
    }//GEN-LAST:event_valNoTelpMouseClicked

    private void valNoTelpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoTelpMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.valNoTelp.setText("<html><p style=\"text-decoration:underline; color:rgb(15,98,230);\">:&nbsp;"+noTelp+"</p></html>");
    }//GEN-LAST:event_valNoTelpMouseEntered

    private void valNoTelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoTelpMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.valNoTelp.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,0);\">:&nbsp;"+noTelp+"</p></html>");
    }//GEN-LAST:event_valNoTelpMouseExited

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_supplier LIKE '%"+key+"%' OR nama_supplier LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyTyped

    private void inpCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyReleased
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_supplier LIKE '%"+key+"%' OR nama_supplier LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBrgSupplier;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataSupplier;
    private javax.swing.JLabel lblHistori;
    private javax.swing.JLabel lblIDSupplier;
    private javax.swing.JLabel lblLast;
    private javax.swing.JLabel lblNamaSupplier;
    private javax.swing.JLabel lblNoTelp;
    private javax.swing.JLabel lblUang;
    private javax.swing.JTable tabelData;
    private javax.swing.JTable tabelHistori;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valBrgSupplier;
    private javax.swing.JPanel valDataSupplier;
    private javax.swing.JLabel valIDSupplier;
    private javax.swing.JLabel valLast;
    private javax.swing.JLabel valNamaSupplier;
    private javax.swing.JLabel valNoTelp;
    private javax.swing.JLabel valUang;
    // End of variables declaration//GEN-END:variables
}
