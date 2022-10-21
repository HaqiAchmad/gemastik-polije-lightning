package com.window.dialogs;

import com.manage.Barang;
import com.manage.ManageTransaksiBeli;
import com.manage.ManageTransaksiJual;
import com.manage.Message;
import com.manage.Text;
import com.media.Gambar;
import com.users.Pembeli;
import com.users.Supplier;

/**
 *
 * @author Achmad Baihaqi
 */
public class KonfirmasiPembayaran extends javax.swing.JDialog {

    private final ManageTransaksiJual trj = new ManageTransaksiJual();
    
    private final ManageTransaksiBeli trb = new ManageTransaksiBeli();
    
    private final Pembeli pembeli = new Pembeli();
    
    private final Supplier supplier = new Supplier();
    
    private final Barang barang = new Barang();
    
    private final Text text = new Text();
    
    private static final int OPSI_JUAL = 1, OPSI_BELI = 2;
    
    private int opsi, stok;
    
    private String idTr, namaTr, namaSupplier, namaPembeli, namaBarang, idPetugas, idSupplier, idPembeli,
            idBarang, metodeBayar, tgl, jumlah, hargaBeli, totalHarga;
    
    private boolean isUpdated = false;

    public KonfirmasiPembayaran(java.awt.Frame parent, boolean modal, int opsi) {
        super(parent, modal);
        initComponents();
        
        this.opsi = opsi;
        
        this.btnKonfirmasi.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.setLocationRelativeTo(null);
    }
    
    public void putValueJual(String namaTrJual, String idPetugas, String idPembeli, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        // mendapatkan data
        this.idTr = trj.createIDTransaksi();
        this.namaTr = namaTrJual;
        this.idPetugas = idPetugas;
        this.idPembeli = idPembeli;
        this.namaPembeli = this.pembeli.getNama(idPembeli);
        this.idBarang = idBarang;
        this.namaBarang = this.barang.getNamaBarang(idBarang);
        this.jumlah = jmlBrg;
        this.metodeBayar = metodeByr;
        this.totalHarga = ttlHarga;
        this.tgl = tanggal;
        
        // menampilkan data
        this.lblNama.setText("Nama Pembeli");
        this.showData(this.idTr, this.namaPembeli, this.namaBarang, this.jumlah, this.totalHarga, this.metodeBayar);
    }
    
    public void putValueBeli(String namaTrBeli, String idPetugas, String idSupplier, String idBarang, String jmlBrg, String metodeByr, String ttlHarga, String tanggal){
        // mendapatkan data
        this.idTr = trj.createIDTransaksi();
        this.namaTr = namaTrBeli;
        this.idPetugas = idPetugas;
        this.idSupplier = idSupplier;
        this.namaSupplier = this.supplier.getNama(idSupplier);
        this.idBarang = idBarang;
        this.namaBarang = this.barang.getNamaBarang(idBarang);
        this.jumlah = jmlBrg;
        this.metodeBayar = metodeByr;
        this.totalHarga = ttlHarga;
        this.tgl = tanggal;
        
        // menampilkan data
        this.lblNama.setText("Nama Supplier");
        this.showData(this.idTr, this.namaSupplier, this.namaBarang, this.jumlah, this.totalHarga, this.metodeBayar);
    }
    
    private void showData(String id, String nama, String namaBrg, String jml, String ttlHrg, String metode){
        this.setTitle("Transaksi dari " + nama);
        valID.setText("<html><p>:&nbsp;"+id+"</p></html>");
        valNama.setText("<html><p>:&nbsp;"+nama+"</p></html>");
        valNamaBrg.setText("<html><p>:&nbsp;"+namaBrg+"</p></html>");
        valJumlah.setText("<html><p>:&nbsp;"+jml+"</p></html>");
        valTotalHarga.setText("<html><p>:&nbsp;"+text.toMoneyCase(ttlHrg)+"</p></html>");
        valMetode.setText("<html><p>:&nbsp;"+text.toCapitalize(metode)+"</p></html>");
    }
    
    public boolean isUpdated(){
        return this.isUpdated;
    }
    
    private void closeConn(){
        this.trj.closeConnection();
        this.trb.closeConnection();
        this.pembeli.closeConnection();
        this.supplier.closeConnection();
        this.barang.closeConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lineBottom = new javax.swing.JSeparator();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        btnBatal = new javax.swing.JButton();
        lblTransaksi = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();
        valID = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        lblNamaBrg = new javax.swing.JLabel();
        valNamaBrg = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        valJumlah = new javax.swing.JLabel();
        valTotalHarga = new javax.swing.JLabel();
        lblMetode = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        valMetode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        lblTop.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Konfirmasi Pembayaran");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
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

        lblTransaksi.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lblTransaksi.setText("ID Transaksi");

        btnKonfirmasi.setBackground(new java.awt.Color(41, 180, 50));
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-pay.png"))); // NOI18N
        btnKonfirmasi.setText("Konfirmasi");
        btnKonfirmasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKonfirmasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKonfirmasiMouseExited(evt);
            }
        });
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });

        valID.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valID.setForeground(new java.awt.Color(0, 0, 0));
        valID.setText(": TRJ0003");

        lblNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNama.setForeground(new java.awt.Color(0, 0, 0));
        lblNama.setText("Nama Pembeli");

        valNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Achmad Baihaqi");

        lblNamaBrg.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaBrg.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBrg.setText("Nama Barang");

        valNamaBrg.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNamaBrg.setForeground(new java.awt.Color(0, 0, 0));
        valNamaBrg.setText(": Beng Beng");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah");

        valJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valJumlah.setForeground(new java.awt.Color(0, 0, 0));
        valJumlah.setText(": 4");

        valTotalHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        valTotalHarga.setText(": Rp. 3.000.00");

        lblMetode.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(0, 0, 0));
        lblMetode.setText("Metode Bayar");

        lblHarga.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblHarga.setForeground(new java.awt.Color(0, 0, 0));
        lblHarga.setText("Total Harga");

        valMetode.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valMetode.setForeground(new java.awt.Color(0, 0, 0));
        valMetode.setText(": E-Wallet");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lineTop)
                    .addComponent(lineBottom)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valNamaBrg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valMetode, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))))
            .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNama, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaBrg, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valNamaBrg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMetode, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(valMetode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnKonfirmasi))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.closeConn();
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKonfirmasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseEntered
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-pembayaran-pay-entered.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseEntered

    private void btnKonfirmasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseExited
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-pembayaran-pay.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseExited

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        boolean save;
        switch(this.opsi){
            case OPSI_JUAL : {
                save = this.trj.addTransaksiJual(namaTr, idPetugas, idPembeli, idBarang, jumlah, metodeBayar, totalHarga, tgl);
                if(save){
                    Message.showInformation(this, "Transaksi berhasil!");
                    this.isUpdated = true;
                    // mengurangi stok dari barang
                    this.stok = Integer.parseInt(this.barang.getStok(this.idBarang)) - Integer.parseInt(this.jumlah);
                    this.barang.setStok(this.idBarang, Integer.toString(stok));
                    // menutup koneksi dan window
                    this.closeConn();
                    this.dispose();
                }
                break;
            }
            case OPSI_BELI : {
                
                break;
            }
        }
        
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KonfirmasiPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                KonfirmasiPembayaran dialog = new KonfirmasiPembayaran(new javax.swing.JFrame(), true, 1);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaBrg;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JLabel valID;
    private javax.swing.JLabel valJumlah;
    private javax.swing.JLabel valMetode;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNamaBrg;
    private javax.swing.JLabel valTotalHarga;
    // End of variables declaration//GEN-END:variables
}
