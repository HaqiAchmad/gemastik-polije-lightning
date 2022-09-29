package com.window.pembayaran;

import com.data.app.Kelas;
import com.data.app.Transaksi;
import com.manage.Message;
import com.manage.Text;
import com.media.Gambar;
import com.users.Users;
import java.awt.Frame;

/**
 *
 * @author Achamd Baihaqi
 * @since 15 Juli 2021
 */
public class BatalkanPembayaran extends javax.swing.JDialog {
    
    private final Frame parent;
    private final boolean modal;
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Transaksi tr = new Transaksi();
    
    private final Text txt = new Text();
    
    private final String idPembayaran, namaPetugas, namaSiswa, kelas, blnBayar, jmlBayar;

    public BatalkanPembayaran(java.awt.Frame parent, boolean modal, String idPembayaran) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // mendapatkan data-data dari siswa
        this.idPembayaran = idPembayaran;
        this.namaPetugas = tr.getNamaPetugas(this.idPembayaran);
        this.namaSiswa = tr.getNamaSiswa(this.idPembayaran);
        this.kelas = kls.getNamaKelas(siswa.getIdKelas(tr.getNis(this.idPembayaran)));
        this.blnBayar = tr.getBulanBayar(this.idPembayaran) + " " + tr.getTahunBayar(this.idPembayaran);
        this.jmlBayar = tr.getJumlahBayar(this.idPembayaran);
        
        // menampilkan data-data siswa
        this.setTitle(namaSiswa + " - Batalkan Pembayaran");
        this.valIdPembayaran.setText("<html><p>:&nbsp;" + this.idPembayaran + "</p></html>");
        this.valNamaPetugas.setText("<html><p>:&nbsp;" + this.namaPetugas + "</p></html>");
        this.valNamaSIswa.setText("<html><p>:&nbsp;" + this.namaSiswa + "</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;" + this.kelas + "</p></html>");
        this.valBulanBayar.setText("<html><p>:&nbsp;" + this.blnBayar + "</p></html>");
        this.valJumlahBayar.setText("<html><p>:&nbsp;" + jmlBayar + "</p></html>");
        
        // mengatur ui pada button
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
        lblNamaSiswa = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        valIdPembayaran = new javax.swing.JLabel();
        valNamaPetugas = new javax.swing.JLabel();
        valNamaSIswa = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnBatalkan = new javax.swing.JButton();
        lblBulanBayar = new javax.swing.JLabel();
        valJumlahBayar = new javax.swing.JLabel();
        valBulanBayar = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();

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
        lblTop.setText("Batalkan Pembayaran");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        lblIdPembayaran.setText("ID Pembayaran");

        lblNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPetugas.setText("Nama Petugas");

        lblNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSiswa.setText("Nama Siswa");

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        valIdPembayaran.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        valIdPembayaran.setText(": TR090242");

        valNamaPetugas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPetugas.setText(": Administrator");

        valNamaSIswa.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNamaSIswa.setForeground(new java.awt.Color(0, 0, 0));
        valNamaSIswa.setText(": Achmad Baihaqi");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnBatalkan.setBackground(new java.awt.Color(220, 41, 41));
        btnBatalkan.setForeground(new java.awt.Color(255, 255, 255));
        btnBatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
        btnBatalkan.setText("Batalkan Pembayaran");
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

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        valJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        valJumlahBayar.setText(": Rp. 180.000.00");

        valBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        valBulanBayar.setText(": Juli 2021");

        valKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII-RPL-1");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lineBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .addComponent(lineTop)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valBulanBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valNamaSIswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valNamaPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBatalkan))
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
                    .addComponent(lblNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaSIswa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatalkan)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void btnBatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnBatalkanActionPerformed

    private void btnBatalkanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalkanMouseEntered
        this.btnBatalkan.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalkanMouseEntered

    private void btnBatalkanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalkanMouseExited
        this.btnBatalkan.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalkanMouseExited

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
                BatalkanPembayaran dialog = new BatalkanPembayaran(new javax.swing.JFrame(), true, "TR033333");
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
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblIdPembayaran;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNamaPetugas;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valBulanBayar;
    private javax.swing.JLabel valIdPembayaran;
    private javax.swing.JLabel valJumlahBayar;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNamaPetugas;
    private javax.swing.JLabel valNamaSIswa;
    // End of variables declaration//GEN-END:variables
}
