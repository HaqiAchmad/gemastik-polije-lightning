package com.window.panels;

import com.manage.Chart;
import com.manage.Internet;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.sun.glass.events.KeyEvent;
import com.users.Barang;
import com.users.Users;
import com.window.dialogs.InputBarang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class DataBarang extends javax.swing.JPanel /*implements ItemListener*/{
    private final Barang Barang = new Barang();
    
//    private final Chart chart = new Chart();
    
    private final Internet net = new Internet();
    
    private final Text text = new Text();

    private String idSelected = "", keyword = "", namaBarang, jenis, stok;
    private final Users user = new Users(); //
    /**
     * Creates new form Dashboard
     */
    public DataBarang() {
        initComponents();
//        this.showPieChart();
        
//        this.chart.showPieChart(this.pieChart, "Produk yang dibeli Achmad Baihaqi", new Font("Ebrima", 1, 20), 15, 20, 60, 0);
        
        this.btnAdd.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDel.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        JLabel[] values = {
          this.valIDBarang, this.valNamaBarang, this.valJenis, this.valStok, 
          this.valHargaBeli, this.valHargaJual, this.valPjln, this.valPjlnMinggu, this.valPenghasilan
        };
        
        for(JLabel lbl : values){
            lbl.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setForeground(new Color(15,98,230));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0,0,0));
                }
            });
        }
        
        this.updateTabel();
    }
    private void showData(){
        // mendapatkan data
        this.namaBarang = Barang.getNama(this.idSelected);
//        this. = text.toTelephoneCase(pembeli.getNoTelp(this.idSelected));
//        this.alamat = pembeli.getAlamat(this.idSelected);
        
        // menampilkan data
        this.valIDBarang.setText("<html><p>:&nbsp;"+idSelected+"</p></html>");
        this.valNamaBarang.setText("<html><p>:&nbsp;"+namaBarang+"</p></html>");
        this.valJenis.setText("<html><p>:&nbsp;"+jenis+"</p></html>");
        this.valStok.setText("<html><p>:&nbsp;"+stok+"</p></html>");
        
        // menampilkan chart
//        this.chart.showPieChart(this.pieChart, "Produk yang dibeli " + this.namaBarang, new Font("Ebrima", 1, 18), 15, 20, 60, 0);
    }
    private Object[][] getData(){
        try{
            Object obj[][];
            Object column[] = {"ID Barang","Nama Barang","Jumlah","Jenis Barang","Harga Beli","Harga Jual"};
            int rows = 0;
            String sql = "SELECT id_barang, nama_barang, jumlah, jenis_barang, harga_beli, harga_jual FROM barang " + keyword;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[user.getJumlahData("barang", keyword)][6];
            // mengeksekusi query
            user.res = user.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(user.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = user.res.getString("id_barang");
                obj[rows][1] = user.res.getString("nama_barang");
                obj[rows][2] = user.res.getString("jumlah");
                obj[rows][3] = user.res.getString("jenis_barang");
                obj[rows][4] = user.res.getString("harga_beli");
                obj[rows][5] = user.res.getString("harga_jual");
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    private void updateTabel(){
        this.tabelData.setModel(new javax.swing.table.DefaultTableModel(
            getData(),
            new String [] {
                "ID Barang", "Nama Barang", "Jumlah", "Jenis Barang", "Harga Beli", "Harga Jual"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
//                return true;
            }
        });
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
        btnEdit = new javax.swing.JToggleButton();
        btnDel = new javax.swing.JToggleButton();
        btnOk = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

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
        tabelData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataMouseClicked(evt);
            }
        });
        tabelData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablDataKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabelData);

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));

        btnAdd.setBackground(new java.awt.Color(41, 180, 50));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnAdd.setText("Tambah Data");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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
        lblIDBarang.setText("ID Bahan");

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaBarang.setText("Nama Barang");

        lblJenisBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJenisBarang.setText("Jenis Barang");

        lblStok.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblStok.setText("Stok");

        lblHrgBeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHrgBeli.setText("Harga Beli");

        lblHrgJual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHrgJual.setText("Harga Jual");

        lblPjln.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPjln.setText("Total Penjualan");

        valIDBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDBarang.setText(": BG0001");

        valNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaBarang.setText(": Ichi Ocha Jasmine Tea");

        valJenis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valJenis.setText(": Minuman");

        valStok.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valStok.setText(": 3 Stok");

        valHargaBeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valHargaBeli.setText(": Rp. 3.000");

        valHargaJual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valHargaJual.setText(": Rp. 3.500");

        valPjln.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPjln.setText(": 45 Penjualan");

        lblPjlnMinggu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPjlnMinggu.setText("Penjualan Minggu Ini");

        valPjlnMinggu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPjlnMinggu.setText(": 3 Penjualan");

        lblPenghasilan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPenghasilan.setText("Penghasilah Didapat");

        valPenghasilan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        lblCari.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Barang :");

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEdit.setText("Edit Data");
        btnEdit.setMaximumSize(new java.awt.Dimension(109, 25));
        btnEdit.setMinimumSize(new java.awt.Dimension(109, 25));
        btnEdit.setPreferredSize(new java.awt.Dimension(109, 25));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(220, 41, 41));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnDel.setText("Hapus Data ");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

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
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 23, Short.MAX_VALUE)
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
                        .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
        
    //String format
// contoh query edit UPDATE pembukuan.barang1 SET evt)  nama_barang = 'susu' WHERE id_barang = 'BG002'
    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data pembeli
        this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // membuka window input pembeli
        Audio.play(Audio.SOUND_INFO);
        InputBarang tbh = new InputBarang(null, true, null);
        tbh.setVisible(true);
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // mengecek apakah user jadi menambahkan data atau tidak
//        if(tbh.isUpdated()){
            // mengupdate tabel
            this.updateTabel();
            this.tabelData.setRowSelectionInterval(this.tabelData.getRowCount()-1, this.tabelData.getRowCount()-1);
//        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int status;
        boolean delete;
        // mengecek apakah ada data yang dipilih atau tidak
        if(tabelData.getSelectedRow() > -1){
            // membuka confirm dialog untuk menghapus data
            Audio.play(Audio.SOUND_INFO);
            status = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus '" + this.namaBarang + "' ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            // mengecek pilihan dari user
            switch(status){
                // jika yes maka data akan dihapus
                case JOptionPane.YES_OPTION : 
                    // menghapus data pembeli
                    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    delete = this.Barang.deleteBarang(this.idSelected);
                    // mengecek apakah data pembeli berhasil terhapus atau tidak
                    if(delete){
                        Message.showInformation(this, "Data berhasil dihapus!");
                        // mengupdate tabel
                        this.updateTabel();
                    }else{
                        Message.showInformation(this, "Data gagal dihapus!");
                    }
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    break;
            }            
        }else{
            Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // mengecek apakah ada data yang dipilih atau tidak
        if(tabelData.getSelectedRow() > -1){
            // membuka window input pembeli
            Audio.play(Audio.SOUND_INFO);
            InputBarang tbh = new InputBarang(null, true, this.idSelected);
            tbh.setVisible(true);

            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            // mengecek apakah user jadi mengedit data atau tidak
            if(tbh.isUpdated()){
                // mengupdate tabel dan menampilkan ulang data
                this.updateTabel();
                this.showData();
            }
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }else{
                Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
            }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
//        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void tablDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablDataKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() - 1, 0).toString();
            this.showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tablDataKeyPressed
//UPDATE pembukuan.barang1 SET nama_barang = 'susu' WHERE id_barang = 'BG002'
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JToggleButton btnDel;
    private javax.swing.JToggleButton btnEdit;
    private javax.swing.JButton btnOk;
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