package com.manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.JPanel;
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
 * @author Achmad Baihaqi
 * @since 2022-10-12
 */
public class Chart {
    
    private final Color C_MAKANAN = new Color(226,226,0), C_MINUMAN = new Color(52,200,38), 
                        C_SNACK = new Color(255,43,237), C_ATK = new Color(49,165,192),
                        BG_CHART = Color.WHITE;
    
    private final Font F_PRODUK = new Font("Ebrima", 1, 22);
    
    public void showPieChart(JPanel panel, String title, Font font, double makanan, double minuman, double snack, double atk){
        
        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset( );
        barDataset.setValue( "Makanan", new Double(makanan));  
        barDataset.setValue( "Minuman", new Double(minuman));   
        barDataset.setValue( "Snack", new Double(snack));    
        barDataset.setValue( "ATK", new Double(atk));  

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Penjualan Produk",barDataset, false,true,false);//explain
        piechart.setTitle(new TextTitle(title, font));

        //changing pie chart blocks colors
        PiePlot piePlot =(PiePlot) piechart.getPlot();
        piePlot.setSectionPaint("Makanan", this.C_MAKANAN);
        piePlot.setSectionPaint("Minuman", this.C_MINUMAN);
        piePlot.setSectionPaint("Snack", this.C_SNACK);
        piePlot.setSectionPaint("ATK", this.C_ATK);
        piePlot.setBackgroundPaint(this.BG_CHART);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panel.removeAll();
        panel.add(barChartPanel, BorderLayout.CENTER);
        panel.validate();
    }

    public void showPieChart(JPanel panel, String title, double makanan, double minuman, double snack, double atk){
        this.showPieChart(panel, title, F_PRODUK, makanan, minuman, snack, atk);
    }
    
    public void pieChartProduk(){
        
    }
    
    public void lineChartPenjualan(JPanel panel){
        HashMap<String, Integer> hash = new HashMap();
        hash.put("Kamis", 200);
        hash.put("Jumat", 150);
        hash.put("Sabtu", 58);
        hash.put("Minggu", 30);
        hash.put("Senin", 180);
        hash.put("Selasa", 250);
        hash.put("Rabu", 250);
        
        this.showLineChart(panel, hash);
    }
    
    public void lineChartPengeluaran(){
        
    }
    
    public void lineChartTransaksi(){
        
    }
    
    public void showLineChart(JPanel panel, HashMap<String, Integer> hash){
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(200, "Amount", "Kamis");
//        dataset.setValue(150, "Amount", "Jumat");
//        dataset.setValue(58, "Amount", "Sabtu");
//        dataset.setValue(30, "Amount", "Minggu");
//        dataset.setValue(180, "Amount", "Senin");
//        dataset.setValue(250, "Amount", "Selasa");
//        dataset.setValue(250, "Amount", "Rabu");
        
        String key;
        int valKey;
        StringTokenizer token;
        Object[] buff = hash.entrySet().toArray();
        
        for(Object obj : buff){
            token = new StringTokenizer(obj.toString(), "=");
            key = token.nextToken();
            valKey = Integer.parseInt(token.nextToken());
            dataset.setValue(valKey, "Amount", key);
        }
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Penjualan Seminggu Terakhir","Hari","Jumlah", 
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
        panel.removeAll();
        panel.add(lineChartPanel, BorderLayout.CENTER);
        panel.validate();
    }

    public void showHistogram(JPanel panel){
        
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
        panel.removeAll();
        panel.add(barpChartPanel2, BorderLayout.CENTER);
        panel.validate();
    }

    public void showBarChart(JPanel panel){
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
        panel.removeAll();
        panel.add(barpChartPanel, BorderLayout.CENTER);
        panel.validate();
        
        
    }
    
    public static void main(String[] args) {
        
        HashMap<String, Integer> hash = new HashMap();
        hash.put("Senin", 10);
        hash.put("Selasa", 20);
        
        System.out.println(hash.toString());
        
        Object[] values = hash.values().toArray(),
                 key = hash.keySet().toArray();
        
        Object[] val = hash.entrySet().toArray();
        
        StringTokenizer token;
        for(Object b : val){
            token = new StringTokenizer(b.toString(), "=");
            System.out.printf("Key : %s\nValue : %s\n\n", token.nextToken(), token.nextToken());
        }
        
    }
    
}
