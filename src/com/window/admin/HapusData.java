package com.window.admin;

import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.SPP;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import java.awt.Frame;

/**
 *
 * @author Achamd Baihaqi
 * @since 24 Juli 2021
 */
public class HapusData extends javax.swing.JDialog {
    
    private final Frame parent;
    private final boolean modal;
    private final Text txt = new Text();
    
    private final Users.LevelPetugas petugas = Users.levelPetugas();
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kelas = new Kelas();
    private final SPP spp = new SPP();
    
    private final String id;
    private String data1, data2, data3;
    private final int type;
    public static final int DATA_PETUGAS = 0, DATA_SISWA = 1, DATA_KELAS = 2, DATA_SPP = 3;

    public HapusData(java.awt.Frame parent, boolean modal, int type, String id) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        this.id = id;
        this.type = type;
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.showData();
        
        // mengatur ui pada button
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private void showData(){
        
        switch(this.type){
            // jika data yang dipilih adalah data petugas
            case HapusData.DATA_PETUGAS : {
                // mengatur tulisan pada label
                this.lblTop.setText("Hapus Data Petugas");
                this.lblData1.setText("ID Petugas");
                this.lblData2.setText("Nama Petugas");
                this.lblData3.setText("Level Petugas");
                this.btnHapus.setText("Hapus Petugas");
                
                // mendapatkan data petugas
                this.data1 = this.id;
                this.data2 = txt.toCapitalize(petugas.getNama(this.id));
                this.data3 = txt.toCapitalize(petugas.getLevel(this.id).name());
                
                // menampilkan data petugas
                this.setTitle(this.data2 + " ~ Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                this.closeConnection();
                break;
            }
            // jika data yang dipilih adalah data siswa
            case HapusData.DATA_SISWA : {
                // mengatur tulisan pada label
                this.lblTop.setText("Hapus Data Siswa");
                this.lblData1.setText("NIS");
                this.lblData2.setText("Nama Siswa");
                this.lblData3.setText("Kelas");
                this.btnHapus.setText("Hapus Siswa");
                
                // mendapatkan data siswa
                this.data1 = this.id;
                this.data2 = txt.toCapitalize(siswa.getNama(this.id));
                this.data3 = kelas.getNamaKelas(siswa.getIdKelas(this.id));
                       
                // menampilkan data siswa
                this.setTitle(this.data2 + " ~ Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                this.closeConnection();
                break;
            }
            // jika data yang dipilih adalah data kelas
            case HapusData.DATA_KELAS : {
                // mengatur tulisan pada label
                this.lblTop.setText("Hapus Data Kelas");
                this.lblData1.setText("ID Kelas");
                this.lblData2.setText("Nama Kelas");
                this.lblData3.setText("Jurusan");
                this.btnHapus.setText("Hapus Kelas");
                
                // mendapatkan data kelas
                this.data1 = this.id;
                this.data2 = kelas.getNamaKelas(this.id);
                this.data3 = kelas.getJurusanName(this.id);
                       
                // menampilkan data kelas
                this.setTitle(this.data2 + " ~ Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                this.closeConnection();
                break;
            }
            // jika data yang dipilih adalah data spp
            case HapusData.DATA_SPP : {
                // mengatur tulisan pada label
                this.lblTop.setText("Hapus Data SPP");
                this.lblData1.setText("ID SPP");
                this.lblData2.setText("Tahun SPP");
                this.lblData3.setText("Nominal SPP");
                this.btnHapus.setText("Hapus SPP");
                
                // mendapatkan data spp
                this.data1 = this.id;
                this.data2 = Integer.toString(spp.getTahunSpp(Integer.parseInt(this.id)));
                this.data3 = txt.toMoneyCase(Integer.toString(spp.getNominalSpp(Integer.parseInt(this.id))));
                       
                // menampilkan data spp
                this.setTitle(this.data2 + " ~ Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;" + this.data1 + "</p></html>");
                this.valData2.setText("<html><p>:&nbsp;" + this.data2 + "</p></html>");
                this.valData3.setText("<html><p>:&nbsp;" + this.data3 + "</p></html>");
                this.closeConnection();
                break;
            }
            default : {
                // mengatur tulisan pada label
                this.lblTop.setText("Hapus Data null");
                this.lblData1.setText("null");
                this.lblData2.setText("null");
                this.lblData3.setText("null");
                this.btnHapus.setText("Hapus null");
                
                // menampilkan data
                this.setTitle("null ~ Hapus Data");
                this.valData1.setText("<html><p>:&nbsp;null</p></html>");
                this.valData2.setText("<html><p>:&nbsp;null</p></html>");
                this.valData3.setText("<html><p>:&nbsp;null</p></html>");
                this.closeConnection();
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
        btnHapus = new javax.swing.JButton();

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
        lblTop.setText("Hapus Data");

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

        btnHapus.setBackground(new java.awt.Color(220, 41, 41));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnHapus.setText("Hapus Data");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusMouseExited(evt);
            }
        });
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
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
                            .addComponent(btnHapus)
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
                        .addGap(0, 8, Short.MAX_VALUE)))
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
                .addComponent(btnHapus)
                .addContainerGap(9, Short.MAX_VALUE))
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
        Log.addLog("Membuka Window " + getClass().getName());
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        Log.addLog("Menutup Window " + getClass().getName());
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeConnection();
        Log.addLog("Menutup Window " + getClass().getName());
    }//GEN-LAST:event_formWindowClosing

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        this.dispose();
        Audio.play(Audio.SOUND_INFO);
        new KonfirmasiHapusData(this.parent, this.modal, this.type, this.id).setVisible(true);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnHapusMouseExited

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
                HapusData dialog = new HapusData(new javax.swing.JFrame(), true, HapusData.DATA_SISWA, "6156");
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
    private javax.swing.JButton btnHapus;
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
