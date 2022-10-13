package com.window.panels;

import com.manage.Message;
import com.users.Users;
import java.sql.SQLException;

/**
 *
 * @author Gemastik Lightning
 */
public class DataSupplier extends javax.swing.JPanel {

    private String keyword = "";
    
    private final Users user = new Users();
    
    public DataSupplier() {
        initComponents();
        this.updateTabel();
    }

    private Object[][] getData(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_supplier, nama_supplier, no_telp, alamat FROM supplier " + keyword;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[user.getJumlahData("supplier", keyword)][4];
            // mengeksekusi query
            user.res = user.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(user.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = user.res.getString("id_supplier");
                obj[rows][1] = user.res.getString("nama_supplier");
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
        this.tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSupplier = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        tabelHistori.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
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
        tabelHistori.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelHistori.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane2.setViewportView(tabelHistori);

        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        lblCari.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Supplier :");

        btnAdd.setBackground(new java.awt.Color(41, 180, 50));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnAdd.setText("Tambah Data");

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEdit.setText("Edit Data");

        btnDel.setBackground(new java.awt.Color(220, 41, 41));
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnDel.setText("Hapus Data");
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
        lblIDSupplier.setText("ID Supplier");

        lblNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaSupplier.setText("Nama Supplier");

        lblNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoTelp.setText("No Telepon");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setText("Alamat");

        lblBrgSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBrgSupplier.setText("Barang Dari Supplier");

        lblUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUang.setText("Uang Dikeluarkan");

        lblLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLast.setText("Pembelian Terakhir");

        valIDSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDSupplier.setText(": SP001");

        valNamaSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaSupplier.setText(": Achmad Baihaqi");

        valNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoTelp.setText(": 085655864624");

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat.setText(": Jombang, Jawa Timur, Indonesia");

        valBrgSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valBrgSupplier.setText(": 341 Barang");

        valUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valUang.setText(": Rp. 1.390.000");

        valLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valLast.setText(": 07 Oktober 2022");

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

        tabelSupplier.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSupplier.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelSupplier.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane3.setViewportView(tabelSupplier);

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
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelActionPerformed


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
    private javax.swing.JTable tabelHistori;
    private javax.swing.JTable tabelSupplier;
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
