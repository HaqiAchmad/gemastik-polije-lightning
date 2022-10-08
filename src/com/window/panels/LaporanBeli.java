/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.window.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Infinite World
 */
public class LaporanBeli extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public LaporanBeli() {
        initComponents();
        
        this.showLineChart1();
        this.showLineChart2();
    }
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "Makanan" , new Double( 20 ) );  
      barDataset.setValue( "Minuman" , new Double( 20 ) );   
      barDataset.setValue( "Snack" , new Double( 40 ) );    
      barDataset.setValue( "ATK" , new Double( 10 ) );  
      
      //create chart
      JFreeChart piechart = ChartFactory.createPieChart("Penjualan Seminggu Terakhir",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Makanan", new Color(255,255,0));
       piePlot.setSectionPaint("Minuman", new Color(51,255,0));
       piePlot.setSectionPaint("Snack", new Color(255,0,255));
       piePlot.setSectionPaint("ATK", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
//        chartPane.removeAll();
//        chartPane.add(barChartPanel, BorderLayout.CENTER);
//        chartPane.validate();
    }

    public void showLineChart1(){
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
        JFreeChart linechart = ChartFactory.createLineChart("Pengeluaran Minggu Ini","Hari","Jumlah", 
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
        chart1.removeAll();
        chart1.add(lineChartPanel, BorderLayout.CENTER);
        chart1.validate();
    }
    
    public void showLineChart2(){
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(410, "Amount", "Minggu 1");
        dataset.setValue(390, "Amount", "Minggu 2");
        dataset.setValue(198, "Amount", "Minggu 3");
        dataset.setValue(420, "Amount", "Minggu 4");
        dataset.setValue(396, "Amount", "Minggu 5");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Pengeluaran Bulan Ini","Hari","Jumlah", 
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
        chart2.removeAll();
        chart2.add(lineChartPanel, BorderLayout.CENTER);
        chart2.validate();
    }

    public void showHistogram(){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        chart2.removeAll();
        chart2.add(barpChartPanel2, BorderLayout.CENTER);
        chart2.validate();
    }

    public void showBarChart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");
        dataset.setValue(100, "Amount", "april");
        dataset.setValue(80, "Amount", "may");
        dataset.setValue(250, "Amount", "june");
        
        JFreeChart chart = ChartFactory.createBarChart("contribution","monthly","amount", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
//        chartPane.removeAll();
//        chartPane.add(barpChartPanel, BorderLayout.CENTER);
//        chartPane.validate();
//        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        lblCari = new javax.swing.JLabel();
        pnlDataPembeli4 = new javax.swing.JPanel();
        lblDataPembeli4 = new javax.swing.JLabel();
        lblIDPembeli4 = new javax.swing.JLabel();
        lblNamaPembeli4 = new javax.swing.JLabel();
        lblNoTelp4 = new javax.swing.JLabel();
        lblAlamat4 = new javax.swing.JLabel();
        lblFavorite4 = new javax.swing.JLabel();
        lblPembelian4 = new javax.swing.JLabel();
        lblLast4 = new javax.swing.JLabel();
        lblUang4 = new javax.swing.JLabel();
        valIDPembelian4 = new javax.swing.JLabel();
        valNamaPembeli4 = new javax.swing.JLabel();
        valNoTelp4 = new javax.swing.JLabel();
        valAlamat4 = new javax.swing.JLabel();
        valFavorite4 = new javax.swing.JLabel();
        valPembelian4 = new javax.swing.JLabel();
        valUang4 = new javax.swing.JLabel();
        valLast4 = new javax.swing.JLabel();
        lblLast5 = new javax.swing.JLabel();
        valLast5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        chart1 = new javax.swing.JPanel();
        chart2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();

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
                "ID Pengeluaran", "Nama Barang", "Total Pengeluaran", "Tanggal"
            }
        ));
        tabelData.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelData.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane2.setViewportView(tabelData);

        inpCari.setBackground(new java.awt.Color(255, 255, 255));
        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.setForeground(new java.awt.Color(0, 0, 0));

        lblCari.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Pengeluaran :");

        pnlDataPembeli4.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataPembeli4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 3));

        lblDataPembeli4.setBackground(new java.awt.Color(5, 170, 57));
        lblDataPembeli4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataPembeli4.setForeground(new java.awt.Color(255, 255, 255));
        lblDataPembeli4.setText("  Detail Pengeluaran");
        lblDataPembeli4.setOpaque(true);

        lblIDPembeli4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDPembeli4.setForeground(new java.awt.Color(0, 0, 0));
        lblIDPembeli4.setText("ID Pengeluaran");

        lblNamaPembeli4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaPembeli4.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPembeli4.setText("ID Transaksi");

        lblNoTelp4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoTelp4.setForeground(new java.awt.Color(0, 0, 0));
        lblNoTelp4.setText("Nama Supplier");

        lblAlamat4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat4.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat4.setText("Nama Karyawan");

        lblFavorite4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFavorite4.setForeground(new java.awt.Color(0, 0, 0));
        lblFavorite4.setText("Nama Barang");

        lblPembelian4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPembelian4.setForeground(new java.awt.Color(0, 0, 0));
        lblPembelian4.setText("Jenis Barang");

        lblLast4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLast4.setForeground(new java.awt.Color(0, 0, 0));
        lblLast4.setText("Total Harga");

        lblUang4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUang4.setForeground(new java.awt.Color(0, 0, 0));
        lblUang4.setText("Jumlah Barang");

        valIDPembelian4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDPembelian4.setForeground(new java.awt.Color(0, 0, 0));
        valIDPembelian4.setText(": PB001");

        valNamaPembeli4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaPembeli4.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPembeli4.setText(": TR001");

        valNoTelp4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoTelp4.setForeground(new java.awt.Color(0, 0, 0));
        valNoTelp4.setText(": Ilham Islamy");

        valAlamat4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat4.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat4.setText(": Amirzan F");

        valFavorite4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valFavorite4.setForeground(new java.awt.Color(0, 0, 0));
        valFavorite4.setText(": Teh Pucuk");

        valPembelian4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPembelian4.setForeground(new java.awt.Color(0, 0, 0));
        valPembelian4.setText(": Minuman");

        valUang4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valUang4.setForeground(new java.awt.Color(0, 0, 0));
        valUang4.setText(": 15");

        valLast4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valLast4.setForeground(new java.awt.Color(0, 0, 0));
        valLast4.setText(": Rp. 15.000");

        lblLast5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLast5.setForeground(new java.awt.Color(0, 0, 0));
        lblLast5.setText("Tanggal");

        valLast5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valLast5.setForeground(new java.awt.Color(0, 0, 0));
        valLast5.setText(": 02 Oktober 2022");

        javax.swing.GroupLayout pnlDataPembeli4Layout = new javax.swing.GroupLayout(pnlDataPembeli4);
        pnlDataPembeli4.setLayout(pnlDataPembeli4Layout);
        pnlDataPembeli4Layout.setHorizontalGroup(
            pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataPembeli4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataPembeli4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLast4, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblUang4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPembelian4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFavorite4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAlamat4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoTelp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaPembeli4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDPembeli4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLast5, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valIDPembelian4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaPembeli4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNoTelp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valAlamat4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valFavorite4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPembelian4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valUang4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valLast4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valLast5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataPembeli4Layout.setVerticalGroup(
            pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPembeli4Layout.createSequentialGroup()
                .addComponent(lblDataPembeli4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDPembeli4)
                    .addComponent(valIDPembelian4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaPembeli4)
                    .addComponent(valNamaPembeli4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoTelp4)
                    .addComponent(valNoTelp4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlamat4)
                    .addComponent(valAlamat4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFavorite4)
                    .addComponent(valFavorite4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPembelian4)
                    .addComponent(valPembelian4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUang4)
                    .addComponent(valUang4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLast4)
                    .addComponent(valLast4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeli4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLast5)
                    .addComponent(valLast5))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        chart1.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Minggu Ini", chart1);

        chart2.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Bulan Ini", chart2);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnAdd.setBackground(new java.awt.Color(41, 180, 50));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnAdd.setText("Print Data");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDataPembeli4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 39, Short.MAX_VALUE)
                                .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDataPembeli4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
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
    private javax.swing.JPanel chart1;
    private javax.swing.JPanel chart2;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAlamat4;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataPembeli4;
    private javax.swing.JLabel lblFavorite4;
    private javax.swing.JLabel lblIDPembeli4;
    private javax.swing.JLabel lblLast4;
    private javax.swing.JLabel lblLast5;
    private javax.swing.JLabel lblNamaPembeli4;
    private javax.swing.JLabel lblNoTelp4;
    private javax.swing.JLabel lblPembelian4;
    private javax.swing.JLabel lblUang4;
    private javax.swing.JPanel pnlDataPembeli4;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valAlamat4;
    private javax.swing.JLabel valFavorite4;
    private javax.swing.JLabel valIDPembelian4;
    private javax.swing.JLabel valLast4;
    private javax.swing.JLabel valLast5;
    private javax.swing.JLabel valNamaPembeli4;
    private javax.swing.JLabel valNoTelp4;
    private javax.swing.JLabel valPembelian4;
    private javax.swing.JLabel valUang4;
    // End of variables declaration//GEN-END:variables
}
