package com.window.panels;

import com.manage.Chart;
import com.manage.Message;
import com.manage.Waktu;
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
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Gemastik Lightning
 */
public class Dashboard extends javax.swing.JPanel {

    private final Chart chart = new Chart();
    
    private final Waktu waktu = new Waktu();
    
    public Dashboard() {
        initComponents();
        
        this.chart.showPieChart(this.pnlPieChart, "Presentase Pembelian Produk", 40, 20, 15, 25);
        this.chart.lineChartPenjualan(this.pnlLineChart);
//        this.showLineChart();
        
        // mengupdate waktu
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(isVisible()){
//                        System.out.println("update");
                        lblDate.setText(waktu.getUpdateWaktu() + "  ");
                        Thread.sleep(100);
                    }
                }catch(InterruptedException ex){
                    Message.showException(this, "Terjadi Kesalahan Saat Mengupdate Tanggal!\n" + ex.getMessage(), ex, true);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTotalPenjualan = new com.manage.PanelRound();
        lblTotalPenjualan = new javax.swing.JLabel();
        valTotalPenjualan = new javax.swing.JLabel();
        icTotalPenjualan = new javax.swing.JLabel();
        pnlTotalPembelian = new com.manage.PanelRound();
        lblTotalPembelian = new javax.swing.JLabel();
        valTotalPembelian = new javax.swing.JLabel();
        icTotalPembelian = new javax.swing.JLabel();
        pnlStokBarang = new com.manage.PanelRound();
        lblStokBarang = new javax.swing.JLabel();
        valStokBarang = new javax.swing.JLabel();
        icStokBarang = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlData = new javax.swing.JPanel();
        pnlDataTop = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        pnlLineChart = new javax.swing.JPanel();
        pnlPieChart = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        pnlTotalPenjualan.setBackground(new java.awt.Color(123, 123, 245));
        pnlTotalPenjualan.setRoundBottomLeft(50);
        pnlTotalPenjualan.setRoundBottomRight(50);
        pnlTotalPenjualan.setRoundTopLeft(50);
        pnlTotalPenjualan.setRoundTopRight(50);

        lblTotalPenjualan.setFont(new java.awt.Font("Ebrima", 1, 22)); // NOI18N
        lblTotalPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalPenjualan.setText("Total Penjualan Hari Ini");
        lblTotalPenjualan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valTotalPenjualan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        valTotalPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        valTotalPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        valTotalPenjualan.setText("Rp. 467.000");
        valTotalPenjualan.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        icTotalPenjualan.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        icTotalPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        icTotalPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icTotalPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-penjualan-1.png"))); // NOI18N
        icTotalPenjualan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlTotalPenjualanLayout = new javax.swing.GroupLayout(pnlTotalPenjualan);
        pnlTotalPenjualan.setLayout(pnlTotalPenjualanLayout);
        pnlTotalPenjualanLayout.setHorizontalGroup(
            pnlTotalPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalPenjualanLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlTotalPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valTotalPenjualan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPenjualan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(icTotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlTotalPenjualanLayout.setVerticalGroup(
            pnlTotalPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalPenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icTotalPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valTotalPenjualan)
                .addGap(17, 17, 17))
        );

        pnlTotalPembelian.setBackground(new java.awt.Color(186, 123, 247));
        pnlTotalPembelian.setRoundBottomLeft(50);
        pnlTotalPembelian.setRoundBottomRight(50);
        pnlTotalPembelian.setRoundTopLeft(50);
        pnlTotalPembelian.setRoundTopRight(50);

        lblTotalPembelian.setFont(new java.awt.Font("Ebrima", 1, 22)); // NOI18N
        lblTotalPembelian.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPembelian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalPembelian.setText("Total Pembeli Hari ini");
        lblTotalPembelian.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valTotalPembelian.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        valTotalPembelian.setForeground(new java.awt.Color(255, 255, 255));
        valTotalPembelian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        valTotalPembelian.setText("97 Pembeli");
        valTotalPembelian.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        icTotalPembelian.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        icTotalPembelian.setForeground(new java.awt.Color(255, 255, 255));
        icTotalPembelian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icTotalPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-pembelian-1.png"))); // NOI18N
        icTotalPembelian.setToolTipText("IC");
        icTotalPembelian.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlTotalPembelianLayout = new javax.swing.GroupLayout(pnlTotalPembelian);
        pnlTotalPembelian.setLayout(pnlTotalPembelianLayout);
        pnlTotalPembelianLayout.setHorizontalGroup(
            pnlTotalPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalPembelianLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlTotalPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valTotalPembelian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPembelian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(icTotalPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlTotalPembelianLayout.setVerticalGroup(
            pnlTotalPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalPembelianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icTotalPembelian, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valTotalPembelian)
                .addGap(17, 17, 17))
        );

        pnlStokBarang.setBackground(new java.awt.Color(219, 120, 49));
        pnlStokBarang.setRoundBottomLeft(50);
        pnlStokBarang.setRoundBottomRight(50);
        pnlStokBarang.setRoundTopLeft(50);
        pnlStokBarang.setRoundTopRight(50);

        lblStokBarang.setFont(new java.awt.Font("Ebrima", 1, 22)); // NOI18N
        lblStokBarang.setForeground(new java.awt.Color(255, 255, 255));
        lblStokBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStokBarang.setText("Total Stok Barang");
        lblStokBarang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valStokBarang.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        valStokBarang.setForeground(new java.awt.Color(255, 255, 255));
        valStokBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        valStokBarang.setText("2.457 Barang");
        valStokBarang.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        icStokBarang.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        icStokBarang.setForeground(new java.awt.Color(255, 255, 255));
        icStokBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icStokBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-dashboard-barang-1.png"))); // NOI18N
        icStokBarang.setToolTipText("IC");
        icStokBarang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlStokBarangLayout = new javax.swing.GroupLayout(pnlStokBarang);
        pnlStokBarang.setLayout(pnlStokBarangLayout);
        pnlStokBarangLayout.setHorizontalGroup(
            pnlStokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStokBarangLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlStokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valStokBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStokBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(icStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlStokBarangLayout.setVerticalGroup(
            pnlStokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStokBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icStokBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valStokBarang)
                .addGap(17, 17, 17))
        );

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 3));

        pnlDataTop.setBackground(new java.awt.Color(26, 121, 173));

        lblData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setText("  Laporan Penjualan Minggu Ini");

        lblDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("Rabu, 05 Oktober 2022 | 17:43  ");

        javax.swing.GroupLayout pnlDataTopLayout = new javax.swing.GroupLayout(pnlDataTop);
        pnlDataTop.setLayout(pnlDataTopLayout);
        pnlDataTopLayout.setHorizontalGroup(
            pnlDataTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataTopLayout.createSequentialGroup()
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
        );
        pnlDataTopLayout.setVerticalGroup(
            pnlDataTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlLineChart.setBackground(new java.awt.Color(255, 255, 255));
        pnlLineChart.setForeground(new java.awt.Color(226, 226, 0));
        pnlLineChart.setLayout(new java.awt.BorderLayout());

        pnlPieChart.setBackground(new java.awt.Color(255, 255, 255));
        pnlPieChart.setForeground(new java.awt.Color(255, 255, 0));
        pnlPieChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addComponent(pnlDataTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addComponent(pnlDataTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addComponent(pnlPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlTotalPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTotalPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTotalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "Makanan" , new Double( 20 ) );  
      barDataset.setValue( "Minuman" , new Double( 20 ) );   
      barDataset.setValue( "Snack" , new Double( 40 ) );    
      barDataset.setValue( "ATK" , new Double( 10 ) );  
      
      //create chart
      JFreeChart piechart = ChartFactory.createPieChart("Penjualan Produk",barDataset, false,true,false);//explain
      piechart.setTitle(new TextTitle("Pie Chart", new java.awt.Font("Ebrima", 1, 22)));
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Makanan", new Color(226,226,0));
       piePlot.setSectionPaint("Minuman", new Color(52,200,38));
       piePlot.setSectionPaint("Snack", new Color(255,43,237));
       piePlot.setSectionPaint("ATK", new Color(49,165,192));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnlPieChart.removeAll();
        pnlPieChart.add(barChartPanel, BorderLayout.CENTER);
        pnlPieChart.validate();
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
        JFreeChart linechart = ChartFactory.createLineChart("Penjualan Produk","Hari","Jumlah", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        linechart.setTitle(new TextTitle("Line Chart", new java.awt.Font("Ebrima", 1, 22)));
        
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
        pnlLineChart.removeAll();
        pnlLineChart.add(lineChartPanel, BorderLayout.CENTER);
        pnlLineChart.validate();
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
        pnlLineChart.removeAll();
        pnlLineChart.add(barpChartPanel2, BorderLayout.CENTER);
        pnlLineChart.validate();
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
        pnlLineChart.removeAll();
        pnlLineChart.add(barpChartPanel, BorderLayout.CENTER);
        pnlLineChart.validate();
        
        
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icStokBarang;
    private javax.swing.JLabel icTotalPembelian;
    private javax.swing.JLabel icTotalPenjualan;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblStokBarang;
    private javax.swing.JLabel lblTotalPembelian;
    private javax.swing.JLabel lblTotalPenjualan;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlDataTop;
    private javax.swing.JPanel pnlLineChart;
    private javax.swing.JPanel pnlPieChart;
    private com.manage.PanelRound pnlStokBarang;
    private com.manage.PanelRound pnlTotalPembelian;
    private com.manage.PanelRound pnlTotalPenjualan;
    private javax.swing.JLabel valStokBarang;
    private javax.swing.JLabel valTotalPembelian;
    private javax.swing.JLabel valTotalPenjualan;
    // End of variables declaration//GEN-END:variables

}
