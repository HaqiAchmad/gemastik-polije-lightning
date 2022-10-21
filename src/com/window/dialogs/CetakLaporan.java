package com.window.dialogs;

import com.manage.Message;
import com.media.Gambar;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;

/**
 *
 * @author Achmad Baihaqi
 */
public class CetakLaporan extends javax.swing.JDialog {

    
    public CetakLaporan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void setEnteredLabel(JLabel icon, JLabel txt, String iconEntered){
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        icon.setIcon(Gambar.getIcon(iconEntered));
        txt.setForeground(new Color(242,8,8));
    }

    private void setExitedLabel(JLabel icon, JLabel txt, String iconExited){
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        icon.setIcon(Gambar.getIcon(iconExited));
        txt.setForeground(new Color(0, 0, 0));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblIconPrint = new javax.swing.JLabel();
        lblPrint = new javax.swing.JLabel();
        lblText = new javax.swing.JLabel();
        lblIconText = new javax.swing.JLabel();
        lblPdf = new javax.swing.JLabel();
        lblIconPdf = new javax.swing.JLabel();
        lblWord = new javax.swing.JLabel();
        lblIconWord = new javax.swing.JLabel();
        lblExcel = new javax.swing.JLabel();
        lblIconExcel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cetak Laporan");

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setForeground(new java.awt.Color(0, 0, 0));

        lblIconPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-cetaklaporan-print.png"))); // NOI18N
        lblIconPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconPrintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconPrintMouseExited(evt);
            }
        });

        lblPrint.setForeground(new java.awt.Color(0, 0, 0));
        lblPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint.setText("Print");
        lblPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPrintMouseExited(evt);
            }
        });

        lblText.setForeground(new java.awt.Color(0, 0, 0));
        lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText.setText("Text File");
        lblText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTextMouseExited(evt);
            }
        });

        lblIconText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-cetaklaporan-textfile.png"))); // NOI18N
        lblIconText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconTextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconTextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconTextMouseExited(evt);
            }
        });

        lblPdf.setForeground(new java.awt.Color(0, 0, 0));
        lblPdf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPdf.setText("PDF File");
        lblPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPdfMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPdfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPdfMouseExited(evt);
            }
        });

        lblIconPdf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-cetaklaporan-pdf.png"))); // NOI18N
        lblIconPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconPdfMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconPdfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconPdfMouseExited(evt);
            }
        });

        lblWord.setForeground(new java.awt.Color(0, 0, 0));
        lblWord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWord.setText("MS Word");
        lblWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblWordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblWordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblWordMouseExited(evt);
            }
        });

        lblIconWord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconWord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-cetaklaporan-word.png"))); // NOI18N
        lblIconWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconWordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconWordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconWordMouseExited(evt);
            }
        });

        lblExcel.setForeground(new java.awt.Color(0, 0, 0));
        lblExcel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExcel.setText("MS Excel");
        lblExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExcelMouseExited(evt);
            }
        });

        lblIconExcel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-cetaklaporan-excel.png"))); // NOI18N
        lblIconExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconExcelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWord, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblIconPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrint))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblIconText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblText))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblIconPdf, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPdf))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblIconWord, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWord))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblIconExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblExcel)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblIconPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPrintMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblIconPrintMouseClicked

    private void lblIconPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPrintMouseEntered
        this.setEnteredLabel(this.lblIconPrint, this.lblPrint, "ic-cetaklaporan-print-entered.png");
    }//GEN-LAST:event_lblIconPrintMouseEntered

    private void lblIconPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPrintMouseExited
        this.setExitedLabel(this.lblIconPrint, this.lblPrint, "ic-cetaklaporan-print.png");
    }//GEN-LAST:event_lblIconPrintMouseExited

    private void lblPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblPrintMouseClicked

    private void lblPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseEntered
        this.setEnteredLabel(this.lblIconPrint, this.lblPrint, "ic-cetaklaporan-print-entered.png");
    }//GEN-LAST:event_lblPrintMouseEntered

    private void lblPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseExited
        this.setExitedLabel(this.lblIconPrint, this.lblPrint, "ic-cetaklaporan-print.png");
    }//GEN-LAST:event_lblPrintMouseExited

    private void lblIconTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTextMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblIconTextMouseClicked

    private void lblIconTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTextMouseEntered
        this.setEnteredLabel(this.lblIconText, this.lblText, "ic-cetaklaporan-textfile-entered.png");
    }//GEN-LAST:event_lblIconTextMouseEntered

    private void lblIconTextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconTextMouseExited
        this.setExitedLabel(this.lblIconText, this.lblText, "ic-cetaklaporan-textfile.png");
    }//GEN-LAST:event_lblIconTextMouseExited

    private void lblTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTextMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblTextMouseClicked

    private void lblTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTextMouseEntered
        this.setEnteredLabel(this.lblIconText, this.lblText, "ic-cetaklaporan-textfile-entered.png");
    }//GEN-LAST:event_lblTextMouseEntered

    private void lblTextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTextMouseExited
        this.setExitedLabel(this.lblIconText, this.lblText, "ic-cetaklaporan-textfile.png");
    }//GEN-LAST:event_lblTextMouseExited

    private void lblIconPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPdfMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblIconPdfMouseClicked

    private void lblIconPdfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPdfMouseEntered
        this.setEnteredLabel(this.lblIconPdf, this.lblPdf, "ic-cetaklaporan-pdf-entered.png");
    }//GEN-LAST:event_lblIconPdfMouseEntered

    private void lblIconPdfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconPdfMouseExited
        this.setExitedLabel(this.lblIconPdf, this.lblPdf, "ic-cetaklaporan-pdf.png");
    }//GEN-LAST:event_lblIconPdfMouseExited

    private void lblPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPdfMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblPdfMouseClicked

    private void lblPdfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPdfMouseEntered
        this.setEnteredLabel(this.lblIconPdf, this.lblPdf, "ic-cetaklaporan-pdf-entered.png");
    }//GEN-LAST:event_lblPdfMouseEntered

    private void lblPdfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPdfMouseExited
        this.setExitedLabel(this.lblIconPdf, this.lblPdf, "ic-cetaklaporan-pdf.png");
    }//GEN-LAST:event_lblPdfMouseExited

    private void lblIconWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWordMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblIconWordMouseClicked

    private void lblIconWordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWordMouseEntered
        this.setEnteredLabel(this.lblIconWord, this.lblWord, "ic-cetaklaporan-word-entered.png");
    }//GEN-LAST:event_lblIconWordMouseEntered

    private void lblIconWordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWordMouseExited
        this.setExitedLabel(this.lblIconWord, this.lblWord, "ic-cetaklaporan-word.png");
    }//GEN-LAST:event_lblIconWordMouseExited

    private void lblWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWordMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblWordMouseClicked

    private void lblWordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWordMouseEntered
        this.setEnteredLabel(this.lblIconWord, this.lblWord, "ic-cetaklaporan-word-entered.png");
    }//GEN-LAST:event_lblWordMouseEntered

    private void lblWordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWordMouseExited
        this.setExitedLabel(this.lblIconWord, this.lblWord, "ic-cetaklaporan-word.png");
    }//GEN-LAST:event_lblWordMouseExited

    private void lblIconExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconExcelMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblIconExcelMouseClicked

    private void lblIconExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconExcelMouseEntered
        this.setEnteredLabel(this.lblIconExcel, this.lblExcel, "ic-cetaklaporan-excel-entered.png");
    }//GEN-LAST:event_lblIconExcelMouseEntered

    private void lblIconExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconExcelMouseExited
        this.setExitedLabel(this.lblIconExcel, this.lblExcel, "ic-cetaklaporan-excel.png");
    }//GEN-LAST:event_lblIconExcelMouseExited

    private void lblExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExcelMouseClicked
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_lblExcelMouseClicked

    private void lblExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExcelMouseEntered
        this.setEnteredLabel(this.lblIconExcel, this.lblExcel, "ic-cetaklaporan-excel-entered.png");
    }//GEN-LAST:event_lblExcelMouseEntered

    private void lblExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExcelMouseExited
        this.setExitedLabel(this.lblIconExcel, this.lblExcel, "ic-cetaklaporan-excel.png");
    }//GEN-LAST:event_lblExcelMouseExited

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CetakLaporan dialog = new CetakLaporan(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblExcel;
    private javax.swing.JLabel lblIconExcel;
    private javax.swing.JLabel lblIconPdf;
    private javax.swing.JLabel lblIconPrint;
    private javax.swing.JLabel lblIconText;
    private javax.swing.JLabel lblIconWord;
    private javax.swing.JLabel lblPdf;
    private javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblWord;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
