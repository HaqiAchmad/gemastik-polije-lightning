package com.window.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Gemastik Lightning
 */
public class DataBarang extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public DataBarang() {
        initComponents();
        
        this.showLineChart();
    }
    
    public void showLineChart(){
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "Kamis");
        dataset.setValue(150, "Amount", "Jumat");
        dataset.setValue(58, "Amount", "Sabtu");
        dataset.setValue(30, "Amount", "Minggu");
        dataset.setValue(180, "Amount", "Senin");
        dataset.setValue(250, "Amount", "Selasa");
        dataset.setValue(250, "Amount", "Rabu");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Penjualan Minggu Ini","Hari","Jumlah", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.WHITE);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(255,2,9);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        lineChart.removeAll();
        lineChart.add(lineChartPanel, BorderLayout.CENTER);
        lineChart.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        lineBottom = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        lineChart = new javax.swing.JPanel();
        pnlDataBarang = new javax.swing.JPanel();
        lblDataBarang = new javax.swing.JLabel();
        lblIDBarang = new javax.swing.JLabel();
        lblNamaBarang = new javax.swing.JLabel();
        lblJenisBarang = new javax.swing.JLabel();
        lblStok = new javax.swing.JLabel();
        lblHrgBeli = new javax.swing.JLabel();
        lblHrgJual = new javax.swing.JLabel();
        lblPjln = new javax.swing.JLabel();
        valIDBarang = new javax.swing.JLabel();
        valNamaBarang = new javax.swing.JLabel();
        valJenis = new javax.swing.JLabel();
        valStok = new javax.swing.JLabel();
        valHargaBeli = new javax.swing.JLabel();
        valHargaJual = new javax.swing.JLabel();
        valPjln = new javax.swing.JLabel();
        lblPjlnMinggu = new javax.swing.JLabel();
        valPjlnMinggu = new javax.swing.JLabel();
        lblPenghasilan = new javax.swing.JLabel();
        valPenghasilan = new javax.swing.JLabel();
        inpCari = new javax.swing.JTextField();
        lblCari = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        tabelData.setBackground(new java.awt.Color(255, 255, 255));
        tabelData.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Jenis", "Stok"
            }
        ));
        tabelData.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelData.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane2.setViewportView(tabelData);

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));

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

        lineChart.setBackground(new java.awt.Color(255, 255, 255));
        lineChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lineChart.setLayout(new java.awt.BorderLayout());

        pnlDataBarang.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataBarang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 3));

        lblDataBarang.setBackground(new java.awt.Color(11, 114, 238));
        lblDataBarang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataBarang.setForeground(new java.awt.Color(255, 255, 255));
        lblDataBarang.setText("  Data Barang");
        lblDataBarang.setOpaque(true);

        lblIDBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblIDBarang.setText("ID Bahan");

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBarang.setText("Nama Barang");

        lblJenisBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJenisBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblJenisBarang.setText("Jenis Barang");

        lblStok.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblStok.setForeground(new java.awt.Color(0, 0, 0));
        lblStok.setText("Stok");

        lblHrgBeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHrgBeli.setForeground(new java.awt.Color(0, 0, 0));
        lblHrgBeli.setText("Harga Beli");

        lblHrgJual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHrgJual.setForeground(new java.awt.Color(0, 0, 0));
        lblHrgJual.setText("Harga Jual");

        lblPjln.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPjln.setForeground(new java.awt.Color(0, 0, 0));
        lblPjln.setText("Total Penjualan");

        valIDBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDBarang.setForeground(new java.awt.Color(0, 0, 0));
        valIDBarang.setText(": BG0001");

        valNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        valNamaBarang.setText(": Ichi Ocha Jasmine Tea");

        valJenis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valJenis.setForeground(new java.awt.Color(0, 0, 0));
        valJenis.setText(": Minuman");

        valStok.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valStok.setForeground(new java.awt.Color(0, 0, 0));
        valStok.setText(": 3 Stok");

        valHargaBeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valHargaBeli.setForeground(new java.awt.Color(0, 0, 0));
        valHargaBeli.setText(": Rp. 3.000");

        valHargaJual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valHargaJual.setForeground(new java.awt.Color(0, 0, 0));
        valHargaJual.setText(": Rp. 3.500");

        valPjln.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPjln.setForeground(new java.awt.Color(0, 0, 0));
        valPjln.setText(": 45 Penjualan");

        lblPjlnMinggu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPjlnMinggu.setForeground(new java.awt.Color(0, 0, 0));
        lblPjlnMinggu.setText("Penjualan Minggu Ini");

        valPjlnMinggu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPjlnMinggu.setForeground(new java.awt.Color(0, 0, 0));
        valPjlnMinggu.setText(": 3 Penjualan");

        lblPenghasilan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPenghasilan.setForeground(new java.awt.Color(0, 0, 0));
        lblPenghasilan.setText("Penghasilah Didapat");

        valPenghasilan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPenghasilan.setForeground(new java.awt.Color(0, 0, 0));
        valPenghasilan.setText(": Rp. 157.500");

        javax.swing.GroupLayout pnlDataBarangLayout = new javax.swing.GroupLayout(pnlDataBarang);
        pnlDataBarang.setLayout(pnlDataBarangLayout);
        pnlDataBarangLayout.setHorizontalGroup(
            pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPjlnMinggu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPjln, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblHrgJual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHrgBeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblJenisBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPenghasilan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valIDBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valJenis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valStok, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(valHargaBeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valHargaJual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPjln, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPjlnMinggu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPenghasilan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataBarangLayout.setVerticalGroup(
            pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataBarangLayout.createSequentialGroup()
                .addComponent(lblDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDBarang)
                    .addComponent(valIDBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaBarang)
                    .addComponent(valNamaBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenisBarang)
                    .addComponent(valJenis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStok)
                    .addComponent(valStok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHrgBeli)
                    .addComponent(valHargaBeli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHrgJual)
                    .addComponent(valHargaJual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPjln)
                    .addComponent(valPjln))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPjlnMinggu)
                    .addComponent(valPjlnMinggu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPenghasilan)
                    .addComponent(valPenghasilan))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        inpCari.setBackground(new java.awt.Color(255, 255, 255));
        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.setForeground(new java.awt.Color(0, 0, 0));

        lblCari.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Barang :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lineBottom)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
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
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataBarang;
    private javax.swing.JLabel lblHrgBeli;
    private javax.swing.JLabel lblHrgJual;
    private javax.swing.JLabel lblIDBarang;
    private javax.swing.JLabel lblJenisBarang;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblPenghasilan;
    private javax.swing.JLabel lblPjln;
    private javax.swing.JLabel lblPjlnMinggu;
    private javax.swing.JLabel lblStok;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JPanel lineChart;
    private javax.swing.JPanel pnlDataBarang;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valHargaBeli;
    private javax.swing.JLabel valHargaJual;
    private javax.swing.JLabel valIDBarang;
    private javax.swing.JLabel valJenis;
    private javax.swing.JLabel valNamaBarang;
    private javax.swing.JLabel valPenghasilan;
    private javax.swing.JLabel valPjln;
    private javax.swing.JLabel valPjlnMinggu;
    private javax.swing.JLabel valStok;
    // End of variables declaration//GEN-END:variables
}
