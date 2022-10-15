package com.window.panels;

import com.manage.Chart;
import com.manage.Message;
import com.media.Audio;
import com.media.Gambar;
import com.sun.glass.events.KeyEvent;
import com.users.Pembeli;
import com.window.dialogs.TambahPembeli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Baihaqi
 */
public class DataPembeli extends javax.swing.JPanel {
    
    private String idSelected = "", keyword = "", namaPembeli, noTelp, alamat;
    
    private final Pembeli pembeli = new Pembeli();
    
    private final Chart chart = new Chart();
    
    private boolean isAdd = false, isEdit = false;
    
    public DataPembeli() {
        initComponents();
//        this.showPieChart();
        
        this.chart.showPieChart(this.pieChart, "Produk yang dibeli Achmad Baihaqi", new Font("Ebrima", 1, 20), 15, 20, 60, 0);
        
        this.btnAdd.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDel.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        this.updateTabel();
    }
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "Makanan" , new Double( 15 ) );  
      barDataset.setValue( "Minuman" , new Double( 20 ) );   
      barDataset.setValue( "Snack" , new Double( 60 ) );    
      barDataset.setValue( "ATK" , new Double( 0 ) );  
      
      //create chart
      JFreeChart piechart = ChartFactory.createPieChart("Produk yang dibeli Achmad Baihaqi",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Makanan", new Color(255,255,0));
       piePlot.setSectionPaint("Minuman", new Color(51,255,0));
       piePlot.setSectionPaint("Snack", new Color(255,0,255));
       piePlot.setSectionPaint("ATK", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pieChart.removeAll();
        pieChart.add(barChartPanel, BorderLayout.CENTER);
        pieChart.validate();
    }

    private Object[][] getData(){
        try{
            Object[][] obj;
            int rows = 0;
            String sql = "SELECT id_pembeli, nama_pembeli, no_telp, alamat FROM pembeli " + keyword;
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[pembeli.getJumlahData("pembeli", keyword)][4];
            // mengeksekusi query
            pembeli.res = pembeli.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(pembeli.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = pembeli.res.getString("id_pembeli");
                obj[rows][1] = pembeli.res.getString("nama_pembeli");
                obj[rows][2] = pembeli.res.getString("no_telp");
                obj[rows][3] = pembeli.res.getString("alamat");
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
                "ID Pembeli", "Nama Pembeli", "No Telephone", "Alamat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private void showData(){
        // mendapatkan data
        this.namaPembeli = pembeli.getNama(this.idSelected);
        this.noTelp = pembeli.getNoTelp(this.idSelected);
        this.alamat = pembeli.getAlamat(this.idSelected);
        
        // menampilkan data
        this.valIDPembeli.setText("<html><p>:&nbsp;"+idSelected+"</p></html>");
        this.valNamaPembeli.setText("<html><p>:&nbsp;"+namaPembeli+"</p></html>");
        this.valNoTelp.setText("<html><p>:&nbsp;"+noTelp+"</p></html>");
        this.valAlamat.setText("<html><p>:&nbsp;"+alamat+"</p></html>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pieChart = new javax.swing.JPanel();
        pnlDataPembeli = new javax.swing.JPanel();
        lblDataPembeli = new javax.swing.JLabel();
        lblIDPembeli = new javax.swing.JLabel();
        lblNamaPembeli = new javax.swing.JLabel();
        lblNoTelp = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblFavorite = new javax.swing.JLabel();
        lblPembelian = new javax.swing.JLabel();
        lblLast = new javax.swing.JLabel();
        lblUang = new javax.swing.JLabel();
        valIDPembeli = new javax.swing.JLabel();
        valNamaPembeli = new javax.swing.JLabel();
        valNoTelp = new javax.swing.JLabel();
        valAlamat = new javax.swing.JLabel();
        valFavorite = new javax.swing.JLabel();
        valPembelian = new javax.swing.JLabel();
        valUang = new javax.swing.JLabel();
        valLast = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblCari = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        pieChart.setBackground(new java.awt.Color(255, 255, 255));
        pieChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pieChart.setLayout(new java.awt.BorderLayout());

        pnlDataPembeli.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataPembeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 121, 173), 3));

        lblDataPembeli.setBackground(new java.awt.Color(11, 114, 238));
        lblDataPembeli.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataPembeli.setForeground(new java.awt.Color(255, 255, 255));
        lblDataPembeli.setText("  Data Pembeli");
        lblDataPembeli.setOpaque(true);

        lblIDPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDPembeli.setForeground(new java.awt.Color(0, 0, 0));
        lblIDPembeli.setText("ID Pembeli");

        lblNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPembeli.setText("Nama Pembeli");

        lblNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoTelp.setText("No Telepon");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        lblFavorite.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFavorite.setForeground(new java.awt.Color(0, 0, 0));
        lblFavorite.setText("Produk Favorite");

        lblPembelian.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPembelian.setForeground(new java.awt.Color(0, 0, 0));
        lblPembelian.setText("Total Pembelian");

        lblLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLast.setForeground(new java.awt.Color(0, 0, 0));
        lblLast.setText("Pembelian Terakhir");

        lblUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUang.setForeground(new java.awt.Color(0, 0, 0));
        lblUang.setText("Uang Dikeluarkan");

        valIDPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDPembeli.setForeground(new java.awt.Color(0, 0, 0));
        valIDPembeli.setText(": PB289");

        valNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPembeli.setText(": Achmad Baihaqi");

        valNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        valNoTelp.setText(": 085655864624");

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat.setText(": Jombang, Jawa Timur, Indonesia");

        valFavorite.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valFavorite.setForeground(new java.awt.Color(0, 0, 0));
        valFavorite.setText(": Teh Pucuk");

        valPembelian.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPembelian.setForeground(new java.awt.Color(0, 0, 0));
        valPembelian.setText(": 19 Pembelian");

        valUang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valUang.setForeground(new java.awt.Color(0, 0, 0));
        valUang.setText(": Rp. 95.000");

        valLast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valLast.setForeground(new java.awt.Color(0, 0, 0));
        valLast.setText(": 07 Oktober 2022");

        javax.swing.GroupLayout pnlDataPembeliLayout = new javax.swing.GroupLayout(pnlDataPembeli);
        pnlDataPembeli.setLayout(pnlDataPembeliLayout);
        pnlDataPembeliLayout.setHorizontalGroup(
            pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataPembeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataPembeliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLast, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblUang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPembelian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFavorite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valIDPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(valFavorite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPembelian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valUang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataPembeliLayout.setVerticalGroup(
            pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPembeliLayout.createSequentialGroup()
                .addComponent(lblDataPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDPembeli)
                    .addComponent(valIDPembeli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaPembeli)
                    .addComponent(valNamaPembeli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoTelp)
                    .addComponent(valNoTelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlamat)
                    .addComponent(valAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFavorite)
                    .addComponent(valFavorite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPembelian)
                    .addComponent(valPembelian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUang)
                    .addComponent(valUang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLast)
                    .addComponent(valLast))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        tabelData.setBackground(new java.awt.Color(255, 255, 255));
        tabelData.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pembeli", "Nama Pembeli", "No Telp", "Alamat"
            }
        ));
        tabelData.setGridColor(new java.awt.Color(0, 0, 0));
        tabelData.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelData.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelData.getTableHeader().setReorderingAllowed(false);
        tabelData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataMouseClicked(evt);
            }
        });
        tabelData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelDataKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabelData);

        inpCari.setBackground(new java.awt.Color(255, 255, 255));
        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.setForeground(new java.awt.Color(0, 0, 0));
        inpCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(41, 180, 50));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnAdd.setText("Tambah Data");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari ID / Nama Pembeli :");

        btnEdit.setBackground(new java.awt.Color(34, 119, 237));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEdit.setText("Edit Data");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
        });
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(220, 41, 41));
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnDel.setText("Hapus Data");
        btnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelMouseExited(evt);
            }
        });
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pieChart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlDataPembeli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlDataPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int status;
        Audio.play(Audio.SOUND_INFO);
        if(tabelData.getSelectedRow() > -1){
            status = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus '" + this.namaPembeli + "' ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch(status){
                case JOptionPane.YES_OPTION : JOptionPane.showMessageDialog(this, "Data berhasil dihapus!"); break;
                case JOptionPane.NO_OPTION : JOptionPane.showMessageDialog(this, "Batal dihapus!"); break;
            }            
        }else{
            Message.showWarning(this, "Tidak ada data yang dipilih!!", true);
        }
        
    }//GEN-LAST:event_btnDelActionPerformed

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_pembeli LIKE '%"+key+"%' OR nama_pembeli LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Audio.play(Audio.SOUND_INFO);
        new TambahPembeli(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.isEdit = true;
        this.isAdd = false;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        this.btnAdd.setIcon(Gambar.getIcon("ic-data-tambah-entered.png"));        
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        this.btnAdd.setIcon(Gambar.getIcon("ic-data-tambah.png"));        
    }//GEN-LAST:event_btnAddMouseExited

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit-entered.png"));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setIcon(Gambar.getIcon("ic-data-edit.png"));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnDelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseEntered
        this.btnDel.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnDelMouseEntered

    private void btnDelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseExited
        this.btnDel.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnDelMouseExited

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // menampilkan data kelas
        this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow(), 0).toString();
        this.showData();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataMouseClicked

    private void tabelDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataKeyPressed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() - 1, 0).toString();
            this.showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            this.idSelected = this.tabelData.getValueAt(tabelData.getSelectedRow() + 1, 0).toString();
            this.showData();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tabelDataKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataPembeli;
    private javax.swing.JLabel lblFavorite;
    private javax.swing.JLabel lblIDPembeli;
    private javax.swing.JLabel lblLast;
    private javax.swing.JLabel lblNamaPembeli;
    private javax.swing.JLabel lblNoTelp;
    private javax.swing.JLabel lblPembelian;
    private javax.swing.JLabel lblUang;
    private javax.swing.JPanel pieChart;
    private javax.swing.JPanel pnlDataPembeli;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valFavorite;
    private javax.swing.JLabel valIDPembeli;
    private javax.swing.JLabel valLast;
    private javax.swing.JLabel valNamaPembeli;
    private javax.swing.JLabel valNoTelp;
    private javax.swing.JLabel valPembelian;
    private javax.swing.JLabel valUang;
    // End of variables declaration//GEN-END:variables
}
