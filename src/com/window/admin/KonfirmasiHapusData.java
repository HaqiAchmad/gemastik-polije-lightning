package com.window.admin;

import com.data.app.Kelas;
import com.data.app.SPP;
import com.manage.Message;
import com.manage.Text;
import com.media.Gambar;
import com.users.Users;

/**
 *
 * @author Achamd Baihaqi
 * @since 24 Juli 2021
 */
public class KonfirmasiHapusData extends javax.swing.JDialog {
    
    private final Users.LevelPetugas petugas = Users.levelPetugas();
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kelas = new Kelas();
    private final SPP spp = new SPP();
    private final Text txt = new Text();
    
    private final String id;
    private String data1, data2, data3;
    private final int type;
    public static final int DATA_PETUGAS = 0, DATA_SISWA = 1, DATA_KELAS = 2, DATA_SPP = 3;

    public KonfirmasiHapusData(java.awt.Frame parent, boolean modal, int type, String id) {
        super(parent, modal);
        this.id = id;
        this.type = type;
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.showData();
        
        // mengatur ui pada button
        this.btnKonfirmasi.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatalkan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private void showData(){
        
        switch(this.type){
            // jika data yang dipilih adalah data petugas
            case KonfirmasiHapusData.DATA_PETUGAS : {
                // mengatur tulisan pada label
                this.lblTop.setText("Konfirmasi Hapus Data Petugas");
                this.lblData1.setText("ID Petugas");
                this.lblData2.setText("Nama Petugas");
                this.lblData3.setText("Level Petugas");
                
                // mendapatkan data petugas
                this.data1 = this.id;
                this.data2 = txt.toCapitalize(petugas.getNama(this.id));
                this.data3 = txt.toCapitalize(petugas.getLevel(this.id).name());
                
                // menampilkan data petugas
                this.setTitle(this.data2 + " ~ Konfirmasi Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                break;
            }
            // jika data yang dipilih adalah data siswa
            case KonfirmasiHapusData.DATA_SISWA : {
                // mengatur tulisan pada label
                this.lblTop.setText("Konfirmasi Hapus Data Siswa");
                this.lblData1.setText("NIS");
                this.lblData2.setText("Nama Siswa");
                this.lblData3.setText("Kelas");
                
                // mendapatkan data siswa
                this.data1 = this.id;
                this.data2 = txt.toCapitalize(siswa.getNama(this.id));
                this.data3 = kelas.getNamaKelas(siswa.getIdKelas(this.id));
                       
                // menampilkan data siswa
                this.setTitle(this.data2 + " ~ Konfirmasi Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                break;
            }
            // jika data yang dipilih adalah data kelas
            case KonfirmasiHapusData.DATA_KELAS : {
                // mengatur tulisan pada label
                this.lblTop.setText("Konfirmasi Hapus Data Kelas");
                this.lblData1.setText("ID Kelas");
                this.lblData2.setText("Nama Kelas");
                this.lblData3.setText("Jurusan");
                
                // mendapatkan data kelas
                this.data1 = this.id;
                this.data2 = kelas.getNamaKelas(this.id);
                this.data3 = kelas.getJurusanName(this.id);
                       
                // menampilkan data kelas
                this.setTitle(this.data2 + " ~ Konfirmasi Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                break;
            }
            // jika data yang dipilih adalah data spp
            case KonfirmasiHapusData.DATA_SPP : {
                // mengatur tulisan pada label
                this.lblTop.setText("Konfirmasi Hapus Data SPP");
                this.lblData1.setText("ID SPP");
                this.lblData2.setText("Tahun SPP");
                this.lblData3.setText("Nominal SPP");
                
                // mendapatkan data spp
                this.data1 = this.id;
                this.data2 = Integer.toString(spp.getTahunSpp(Integer.parseInt(this.id)));
                this.data3 = txt.toMoneyCase(Integer.toString(spp.getNominalSpp(Integer.parseInt(this.id))));
                       
                // menampilkan data spp
                this.setTitle(this.data1 + " ~ Konfirmasi Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                break;
            }
            default : {
                // mengatur tulisan pada label
                this.lblTop.setText("Konfirmasi Hapus Data null");
                this.lblData1.setText("null");
                this.lblData2.setText("null");
                this.lblData3.setText("null");
                
                // menampilkan data
                this.setTitle("null ~ Konfirmasi Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;null</p></html>");
                this.valData2.setText("<html><p>:&nbsp;null</p></html>");
                this.valData3.setText("<html><p>:&nbsp;null</p></html>");
                break;
            }
        }
    }
    
    private void closeConnection(){
        this.petugas.closeConnection();
        this.siswa.closeConnection();
        this.kelas.closeConnection();
        this.spp.closeConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        lblData1 = new javax.swing.JLabel();
        lblData2 = new javax.swing.JLabel();
        lblData3 = new javax.swing.JLabel();
        valData1 = new javax.swing.JLabel();
        valData2 = new javax.swing.JLabel();
        valData3 = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnKonfirmasi = new javax.swing.JButton();
        btnBatalkan = new javax.swing.JButton();

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
        lblTop.setText("Konfirmasi Hapus Data");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblData1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblData1.setForeground(new java.awt.Color(0, 0, 0));
        lblData1.setText("Data 1");

        lblData2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblData2.setForeground(new java.awt.Color(0, 0, 0));
        lblData2.setText("Data 2");

        lblData3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblData3.setForeground(new java.awt.Color(0, 0, 0));
        lblData3.setText("Data 3");

        valData1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valData1.setForeground(new java.awt.Color(0, 0, 0));
        valData1.setText(": Value 1");

        valData2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valData2.setForeground(new java.awt.Color(0, 0, 0));
        valData2.setText(": Value 2");

        valData3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valData3.setForeground(new java.awt.Color(0, 0, 0));
        valData3.setText(": Value 3");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnKonfirmasi.setBackground(new java.awt.Color(34, 119, 237));
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
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

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lineBottom)
                    .addComponent(lineTop)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnKonfirmasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatalkan))
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblData3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(valData3, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblData2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(valData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblData1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(valData1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valData1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblData1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valData2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblData3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valData3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBatalkan)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnKonfirmasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.closeConnection();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void btnKonfirmasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseEntered
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseEntered

    private void btnKonfirmasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKonfirmasiMouseExited
        this.btnKonfirmasi.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnKonfirmasiMouseExited

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini.");
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void btnBatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanActionPerformed
        this.dispose();
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
                KonfirmasiHapusData dialog = new KonfirmasiHapusData(new javax.swing.JFrame(), true, KonfirmasiHapusData.DATA_SISWA, "6156");
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
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblData2;
    private javax.swing.JLabel lblData3;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valData1;
    private javax.swing.JLabel valData2;
    private javax.swing.JLabel valData3;
    // End of variables declaration//GEN-END:variables
}
