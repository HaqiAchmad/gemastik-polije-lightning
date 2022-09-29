package com.window.all;

import com.manage.Message;
import com.media.Gambar;

/**
 *
 * @author Achmad Baihaqi
 * @since 27 July 2021
 */
public class ConfirmHapusData extends javax.swing.JDialog {

    public ConfirmHapusData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        // menatur ui pada button
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPeringatan = new javax.swing.JTextPane();
        lblPeringatan = new javax.swing.JLabel();
        lblYakin = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hapus Data Aplikasi");
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
        lblTop.setText(" Hapus Data Aplikasi");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        textPeringatan.setEditable(false);
        textPeringatan.setBackground(new java.awt.Color(255, 255, 255));
        textPeringatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        textPeringatan.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        textPeringatan.setForeground(new java.awt.Color(0, 0, 0));
        textPeringatan.setText("Jika Anda menekan tombol 'Hapus Data' maka seluruh data pada aplikasi ini akan dihapus secara permanen. Ini mencakup semua file, pengaturan, akun, database, dll.");
        textPeringatan.setSelectedTextColor(new java.awt.Color(250, 247, 247));
        textPeringatan.setSelectionColor(new java.awt.Color(240, 96, 104));
        jScrollPane2.setViewportView(textPeringatan);

        lblPeringatan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPeringatan.setForeground(new java.awt.Color(255, 14, 0));
        lblPeringatan.setText("Peringatan!!");

        lblYakin.setBackground(new java.awt.Color(0, 0, 0));
        lblYakin.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblYakin.setForeground(new java.awt.Color(0, 153, 153));
        lblYakin.setText("Yakin ingin menghapus data ?");

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnHapus.setBackground(new java.awt.Color(34, 119, 237));
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

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
        btnBatal.setText("Batalkan");
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

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lineBottom)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lineTop)
                        .addComponent(lblPeringatan, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addComponent(lblYakin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPeringatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYakin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal))
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
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        Message.showInformation(this, "Belum tersedia!");
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        this.btnHapus.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnHapusMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmHapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConfirmHapusData dialog = new ConfirmHapusData(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHapus;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPeringatan;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblYakin;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextPane textPeringatan;
    // End of variables declaration//GEN-END:variables
}
