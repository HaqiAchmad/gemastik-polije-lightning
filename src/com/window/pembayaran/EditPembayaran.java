package com.window.pembayaran;

import com.data.app.Kelas;
import com.data.app.Transaksi;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import java.awt.Frame;

/**
 *
 * @author Achamd Baihaqi
 * @since 17 Juli 2021
 */
public class EditPembayaran extends javax.swing.JDialog {
    
    private final Frame parent;
    private final boolean modal;
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Transaksi tr = new Transaksi();
    
    private final Text txt = new Text();
    
    private String nis, namaSiswa, kelas, blnBayar, thnBayar, jmlBayar;

    public EditPembayaran(java.awt.Frame parent, boolean modal, String idPembayaran) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
        // mendapatkan data-data pembayaran
        this.nis = tr.getNis(idPembayaran);
        this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
        
        this.setTitle(namaSiswa + " - Edit Pembayaran");
        this.inpIDPembayaran.setText(idPembayaran);
        this.inpIDPembayaran.setEditable(false);
        
        // mengatur ui pada button
        this.btnSimpan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private void closeConnection(){
        this.siswa.closeConnection();
        this.kls.closeConnection();
        this.tr.closeConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        lblIdPembayaran = new javax.swing.JLabel();
        lblIdPetugas = new javax.swing.JLabel();
        lblBulan = new javax.swing.JLabel();
        lblTahun = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        lblTanggal = new javax.swing.JLabel();
        inpIDPembayaran = new javax.swing.JTextField();
        inpIdPetugas = new javax.swing.JTextField();
        inpBulanBayar = new javax.swing.JComboBox();
        inpTahunBayar = new javax.swing.JTextField();
        inpTanggal = new com.toedter.calendar.JDateChooser();
        lblRp = new javax.swing.JLabel();
        inpJumlah = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bayar SPP");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblTop.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Edit Pembayaran");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPembayaran.setText("ID Pembayaran");

        lblIdPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIdPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPetugas.setText("ID Petugas");

        lblBulan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBulan.setForeground(new java.awt.Color(0, 0, 0));
        lblBulan.setText("Bulan Bayar");

        lblTahun.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTahun.setForeground(new java.awt.Color(0, 0, 0));
        lblTahun.setText("Tahun Bayar");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah Bayar");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnSimpan.setBackground(new java.awt.Color(34, 119, 237));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-save.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

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

        lblTanggal.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggal.setText("Tanggal Bayar");

        inpIDPembayaran.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpIDPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        inpIDPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpIDPembayaranMouseClicked(evt);
            }
        });

        inpIdPetugas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        inpBulanBayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpBulanBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Juli", "Agustus", "September", "Oktober", "November", "Desember", "Januari", "Februari", "Maret", "April", "Mei", "Juni" }));

        inpTahunBayar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        inpTanggal.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        lblRp.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        lblRp.setForeground(new java.awt.Color(0, 0, 0));
        lblRp.setText("Rp.");

        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lineBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(lineTop)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(lblIdPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpIDPembayaran)
                            .addComponent(inpIdPetugas)
                            .addComponent(inpBulanBayar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpTahunBayar)
                            .addComponent(inpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblRp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inpJumlah)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpIDPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpIdPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lblBulan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosing

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setIcon(Gambar.getIcon("ic-manipulasi-save-entered.png"));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setIcon(Gambar.getIcon("ic-manipulasi-save.png"));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void inpIDPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpIDPembayaranMouseClicked
        Message.showWarning(this, "ID Pembayaran tidak diperbolehkan untuk diedit!");
    }//GEN-LAST:event_inpIDPembayaranMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Message.showException(null, ex, true);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EditPembayaran dialog = new EditPembayaran(new javax.swing.JFrame(), true, "TR000100");
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
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox inpBulanBayar;
    private javax.swing.JTextField inpIDPembayaran;
    private javax.swing.JTextField inpIdPetugas;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JTextField inpTahunBayar;
    private com.toedter.calendar.JDateChooser inpTanggal;
    private javax.swing.JLabel lblBulan;
    private javax.swing.JLabel lblIdPembayaran;
    private javax.swing.JLabel lblIdPetugas;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblRp;
    private javax.swing.JLabel lblTahun;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
