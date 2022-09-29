package com.window.pembayaran;

import com.data.app.Kelas;
import com.data.app.Transaksi;
import com.manage.Message;
import com.media.Gambar;
import com.users.Users;

/**
 *
 * @author Achamd Baihaqi
 * @since 18 Juli 2021
 */
public class LaporkanPembayaran extends javax.swing.JDialog {
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Transaksi tr = new Transaksi();
    
    private final String idPembayaran, namaPetugas, namaSiswa, blnBayar, jmlBayar;

    public LaporkanPembayaran(java.awt.Frame parent, boolean modal, String idPembayaran) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // mendapatkan data-data dari siswa
        this.idPembayaran = idPembayaran;
        this.namaPetugas = tr.getNamaPetugas(this.idPembayaran);
        this.namaSiswa = tr.getNamaSiswa(this.idPembayaran);
        this.blnBayar = tr.getBulanBayar(this.idPembayaran) + " " + tr.getTahunBayar(this.idPembayaran);
        this.jmlBayar = tr.getJumlahBayar(this.idPembayaran);
        
        // menampilkan data-data siswa
        this.setTitle(namaSiswa + " - Laporkan Kesalahan Pembayaran");
        this.valIdPembayaran.setText("<html><p>:&nbsp;" + this.idPembayaran + "</p></html>");
        this.valNamaPetugas.setText("<html><p>:&nbsp;" + this.namaPetugas + "</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;" + this.blnBayar + "</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;" + this.jmlBayar + "</p></html>");
        
        // mengatur ui pada button
        this.btnLaporkan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatalkan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
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
        lblNamaPetugas = new javax.swing.JLabel();
        lblBulanBayar = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        valIdPembayaran = new javax.swing.JLabel();
        valNamaPetugas = new javax.swing.JLabel();
        valBulanBayar = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnBatalkan = new javax.swing.JButton();
        lblMasalah = new javax.swing.JLabel();
        valJumlahBayar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inpMasalah = new javax.swing.JTextArea();
        btnLaporkan = new javax.swing.JButton();

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
        lblTop.setText("Laporkan Pembayaran");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPembayaran.setText("ID Pembayaran");

        lblNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPetugas.setText("Nama Petugas");

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        valIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        valIdPembayaran.setText(": TR090242");

        valNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPetugas.setText(": Administrator");

        valBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        valBulanBayar.setText(": Juli 2021");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnBatalkan.setBackground(new java.awt.Color(220, 41, 41));
        btnBatalkan.setForeground(new java.awt.Color(255, 255, 255));
        btnBatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
        btnBatalkan.setText("Batalkan");
        btnBatalkan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalkanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBatalkanMouseExited(evt);
            }
        });
        btnBatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalkanActionPerformed(evt);
            }
        });

        lblMasalah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblMasalah.setForeground(new java.awt.Color(0, 0, 0));
        lblMasalah.setText("Masalah Anda");

        valJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        valJumlahBayar.setText(": Rp. 180.000.00");

        inpMasalah.setColumns(20);
        inpMasalah.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpMasalah.setForeground(new java.awt.Color(0, 0, 0));
        inpMasalah.setRows(5);
        inpMasalah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpMasalah.setCaretColor(new java.awt.Color(238, 9, 9));
        jScrollPane1.setViewportView(inpMasalah);

        btnLaporkan.setBackground(new java.awt.Color(34, 119, 237));
        btnLaporkan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-error.png"))); // NOI18N
        btnLaporkan.setText("Laporkan");
        btnLaporkan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLaporkanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLaporkanMouseExited(evt);
            }
        });
        btnLaporkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnLaporkan, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatalkan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lineBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .addComponent(lineTop)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMasalah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblJumlahBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane1))
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(valBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(valJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))))))
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
                    .addComponent(valIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lblIdPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblMasalah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatalkan)
                    .addComponent(btnLaporkan))
                .addGap(16, 16, 16))
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

    private void btnBatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalkanActionPerformed

    private void btnBatalkanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalkanMouseEntered
        this.btnBatalkan.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalkanMouseEntered

    private void btnBatalkanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalkanMouseExited
        this.btnBatalkan.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalkanMouseExited

    private void btnLaporkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporkanActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnLaporkanActionPerformed

    private void btnLaporkanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporkanMouseEntered
        this.btnLaporkan.setIcon(Gambar.getIcon("ic-pembayaran-error-entered.png"));
    }//GEN-LAST:event_btnLaporkanMouseEntered

    private void btnLaporkanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporkanMouseExited
        this.btnLaporkan.setIcon(Gambar.getIcon("ic-pembayaran-error.png"));
    }//GEN-LAST:event_btnLaporkanMouseExited

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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LaporkanPembayaran dialog = new LaporkanPembayaran(new javax.swing.JFrame(), true, "TR033333");
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
    private javax.swing.JButton btnBatalkan;
    private javax.swing.JButton btnLaporkan;
    private javax.swing.JTextArea inpMasalah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblIdPembayaran;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblMasalah;
    private javax.swing.JLabel lblNamaPetugas;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valBulanBayar;
    private javax.swing.JLabel valIdPembayaran;
    private javax.swing.JLabel valJumlahBayar;
    private javax.swing.JLabel valNamaPetugas;
    // End of variables declaration//GEN-END:variables
}
