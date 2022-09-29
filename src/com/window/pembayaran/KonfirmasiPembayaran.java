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
 * @since 15 Juli 2021
 */
public class KonfirmasiPembayaran extends javax.swing.JDialog {
    
    private final Frame parent;
    private final boolean modal;
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Transaksi tr = new Transaksi();
    
    private final Text txt = new Text();
    
    private final String nis, namaSiswa, kelas, blnBayar, thnBayar, jmlBayar;

    public KonfirmasiPembayaran(java.awt.Frame parent, boolean modal, String nis, String blnBayar, String thnBayar, String jmlBayar) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // mendapatkan data-data dari siswa
        this.nis = nis;
        this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
        this.kelas = kls.getNamaKelas(siswa.getIdKelas(nis));
        this.blnBayar = blnBayar;
        this.thnBayar = thnBayar;
        this.jmlBayar = jmlBayar;
        
        // menampilkan data-data siswa
        this.setTitle(namaSiswa + " - Konfirmasi Pembayaran");
        this.valNis.setText("<html><p>:&nbsp;" + this.nis + "</p></html>");
        this.valNama.setText("<html><p>:&nbsp;" + this.namaSiswa + "</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;" + this.kelas + "</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;" + this.blnBayar + "</p></html>");
        this.valTahunBayar.setText("<html><p>:&nbsp;" + this.thnBayar + "</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;" + this.txt.toMoneyCase(this.jmlBayar) + "</p></html>");
        
        // mengatur ui pada button
        this.btnKonfirmasi.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
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
        lblNis = new javax.swing.JLabel();
        lbNama = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        lblBulanBayar = new javax.swing.JLabel();
        valNis = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnEdit = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        lblTahunBayar = new javax.swing.JLabel();
        valJumlahBayar = new javax.swing.JLabel();
        valTahunBayar = new javax.swing.JLabel();
        valBulanBayar = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();

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
        lblTop.setText("Konfirmasi Pembayaran");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblNis.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lbNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbNama.setForeground(new java.awt.Color(0, 0, 0));
        lbNama.setText("Nama Siswa");

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        valNis.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": 6156");

        valNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Achmad Baihaqi");

        valKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII-RPL-1");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-editakun.png"))); // NOI18N
        btnEdit.setText("Edit");
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

        lblTahunBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunBayar.setText("Tahun Bayar");

        valJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        valJumlahBayar.setText(": Rp. 180.000.00");

        valTahunBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        valTahunBayar.setText(": 2021");

        valBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        valBulanBayar.setText(": Juli");

        btnKonfirmasi.setBackground(new java.awt.Color(41, 180, 50));
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-bayar.png"))); // NOI18N
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
                        .addComponent(btnKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lblTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valTahunBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lbNama, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                            .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valNis, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valNis, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lblNis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnBatal)
                    .addComponent(btnKonfirmasi))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.dispose();
        // membuka window pembayaran
        Audio.play(Audio.SOUND_INFO);
        new WindowPembayaran(parent, modal, nis, blnBayar, thnBayar, jmlBayar).setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-manipulasi-editakun.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini.");
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void btnKonfirmasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseEntered
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-pembayaran-pay-entered.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseEntered

    private void btnKonfirmasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseExited
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-pembayaran-bayar.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseExited

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
                KonfirmasiPembayaran dialog = new KonfirmasiPembayaran(new javax.swing.JFrame(), true, "6156", "Juli", "2021", "180000");
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
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JLabel lbNama;
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblTahunBayar;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valBulanBayar;
    private javax.swing.JLabel valJumlahBayar;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valTahunBayar;
    // End of variables declaration//GEN-END:variables
}
