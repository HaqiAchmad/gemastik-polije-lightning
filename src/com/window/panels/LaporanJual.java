package com.window.panels;

import com.manage.Barang;
import com.manage.ManageTransaksiJual;
import com.manage.Message;
import com.manage.Text;
import com.manage.Waktu;
import com.media.Audio;
import com.media.Gambar;
import com.sun.glass.events.KeyEvent;
import com.users.Pembeli;
import com.users.Petugas;
import com.window.dialogs.CetakLaporan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Gemastik Lightning
 */
public class LaporanJual extends javax.swing.JPanel {
    
    private final ManageTransaksiJual trj = new ManageTransaksiJual();
    
    private final Petugas petugas = new Petugas();
    
    private final Pembeli pembeli = new Pembeli();
    
    private final Barang barang = new Barang();
    
    private final Text text = new Text();
    
    private final Waktu waktu = new Waktu();
    
    private String idSelected = "", keyword = "", idTr, idPd, idBg, namaPembeli, namaPetugas, namaBarang, jenis, jumlah, totalHrg, tanggal;

    public LaporanJual() {
        initComponents();
        
        this.btnPrint.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDel.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        JLabel[] lbls = new JLabel[]{
            this.valIDPendapatan, this.valIDTransaksi, this.valNamaBarang, this.valNamaKaryawan, this.valNamaPembeli, 
            this.valJenisBarang, this.valJumlah, this.valTanggal, this.valTotalHarga
        };
        
        for(JLabel lbl : lbls){
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
        
        this.showLineChart1();
        this.showPieChart();
        
        this.updateTabel();
    }
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "Nabati Wafer" , new Double( 18 ) );  
      barDataset.setValue( "Aqua 500ml" , new Double( 15 ) );   
      barDataset.setValue( "Coca Cola" , new Double( 14 ) );    
      barDataset.setValue( "Oreo" , new Double( 12 ) );  
      barDataset.setValue( "Ichi Ocha 350ml" , new Double( 10 ) );  
      barDataset.setValue( "Aqua 1L" , new Double( 10 ) );   
      barDataset.setValue( "Pulpen Snowman" , new Double( 8 ) );    
      barDataset.setValue( "Teh Pucuk" , new Double( 7 ) );    
      barDataset.setValue( "Lainya" , new Double( 6 ) );  
      
      //create chart
      JFreeChart piechart = ChartFactory.createPieChart("Penjualan Produk",barDataset, false,true,false);//explain
      piechart.setTitle(new TextTitle("Produk Terlaris", new java.awt.Font("Ebrima", 1, 22)));
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Nabati Wafer", new Color(226,226,0));
       piePlot.setSectionPaint("Aqua 500ml", new Color(52,200,38));
       piePlot.setSectionPaint("Coca Cola", new Color(255,43,237));
       piePlot.setSectionPaint("Oreo", new Color(49,165,192));
       piePlot.setSectionPaint("Ichi Ocha 350ml", new Color(255,0,153));
       piePlot.setSectionPaint("Aqua 1L", new Color(51,255,0));
       piePlot.setSectionPaint("Pulpen Snowman", new Color(255,102,51));
       piePlot.setSectionPaint("Teh Pucuk", new Color(51,0,204));
       piePlot.setSectionPaint("Lainya", new Color(0,102,102));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        paneProduk.removeAll();
        paneProduk.add(barChartPanel, BorderLayout.CENTER);
        paneProduk.validate();
    }

    public void showLineChart1(){
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(120, "Amount", "Minggu 1");
        dataset.setValue(70, "Amount", "Minggu 2");
        dataset.setValue(60, "Amount", "Minggu 3");
        dataset.setValue(120, "Amount", "Minggu 4");
        dataset.setValue(120, "Amount", "Minggu 5");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Pendapatan Bulan Ini","Hari","Jumlah", 
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
        tabGrafikPendapatan.removeAll();
        tabGrafikPendapatan.add(lineChartPanel, BorderLayout.CENTER);
        tabGrafikPendapatan.validate();
    }
    
    private Object[][] getData(){
        try{
            Object obj[][];
            int rows = 0;
            String sql = "SELECT id_tr_jual, id_barang, jumlah_brg, total_hrg, tanggal FROM transaksi_jual " + keyword + " ORDER BY id_tr_jual DESC";
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[trj.getJumlahData("transaksi_jual", keyword)][5];
            // mengeksekusi query
            trj.res = trj.stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(trj.res.next()){
                // menyimpan data dari tabel ke object
                obj[rows][0] = trj.res.getString("id_tr_jual").replace("TRJ", "LPD");
                obj[rows][1] = barang.getNamaBarang(trj.res.getString("id_barang"));
                obj[rows][2] = trj.res.getString("jumlah_brg");
                obj[rows][3] = text.toMoneyCase(trj.res.getString("total_hrg"));
                obj[rows][4] = text.toDateCase(trj.res.getString("tanggal"));
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            ex.printStackTrace();
            Message.showException(this, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), ex, true);
        }
        return null;
    }
    
    private void updateTabel(){
        this.tabelData.setModel(new javax.swing.table.DefaultTableModel(
            getData(),
            new String [] {
                "ID Pendapatan", "Nama Barang", "Jumlah Barang", "Total Pendapatan", "Tanggal"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private void showData(){
        // mendapatkan data-data
        this.idTr = this.tabelData.getValueAt(this.tabelData.getSelectedRow(), 0).toString().replace("LPD", "TRJ");
        this.idPd = this.idTr.replace("TRJ", "LPD");
        this.namaPembeli = this.pembeli.getNama(this.trj.getIdPembeli(this.idTr));
        this.namaPetugas = this.petugas.getNama(this.trj.getIdPetugas(this.idTr));
        this.namaBarang = this.barang.getNamaBarang(this.trj.getIdBarang(this.idTr));
        this.jenis = text.toCapitalize(this.barang.getJenis(this.trj.getIdBarang(this.idTr)));
        this.jumlah = Integer.toString(this.trj.getJumlahBarang(this.idTr));
        this.totalHrg = text.toMoneyCase(this.trj.getTotalHarga(this.idTr));
        this.tanggal = this.text.toDateCase(this.trj.getTanggal(this.idTr));
        
        // menampilkan data-data
        this.valIDTransaksi.setText("<html><p>:&nbsp;"+this.idTr+"</p></html>");
        this.valIDPendapatan.setText("<html><p>:&nbsp;"+this.idPd+"</p></html>");
        this.valNamaPembeli.setText("<html><p>:&nbsp;"+this.namaPembeli+"</p></html>");
        this.valNamaKaryawan.setText("<html><p>:&nbsp;"+this.namaPetugas+"</p></html>");
        this.valNamaBarang.setText("<html><p>:&nbsp;"+this.namaBarang+"</p></html>");
        this.valJumlah.setText("<html><p>:&nbsp;"+this.jumlah+"</p></html>");
        this.valJenisBarang.setText("<html><p>:&nbsp;"+this.jenis+"</p></html>");
        this.valTotalHarga.setText("<html><p>:&nbsp;"+this.totalHrg+"</p></html>");
        this.valTanggal.setText("<html><p>:&nbsp;"+this.tanggal+"</p></html>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        lblCari = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnPrint = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        pnlDataPendapatan = new javax.swing.JPanel();
        lblDataPendapatan = new javax.swing.JLabel();
        lblIDPendapatan = new javax.swing.JLabel();
        lblIDTransaksi = new javax.swing.JLabel();
        lblNamaPembeli = new javax.swing.JLabel();
        lblNamaKaryawan = new javax.swing.JLabel();
        lblNamaBarang = new javax.swing.JLabel();
        lblJenisBarang = new javax.swing.JLabel();
        lblTotalHarga = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        valIDPendapatan = new javax.swing.JLabel();
        valIDTransaksi = new javax.swing.JLabel();
        valNamaPembeli = new javax.swing.JLabel();
        valNamaKaryawan = new javax.swing.JLabel();
        valNamaBarang = new javax.swing.JLabel();
        valJenisBarang = new javax.swing.JLabel();
        valJumlah = new javax.swing.JLabel();
        valTotalHarga = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        valTanggal = new javax.swing.JLabel();
        tabPendapatan = new javax.swing.JTabbedPane();
        paneProduk = new javax.swing.JPanel();
        tabGrafikPendapatan = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(957, 650));

        tabelData.setBackground(new java.awt.Color(255, 255, 255));
        tabelData.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"PD0001", "Teh Pucuk", "3", "Rp. 9.000.00", "01 Oktober 2022"},
                {"PD0002", "Aqua 500ml", "2", "Rp. 10.000.00", "01 Oktober 2022"},
                {"PD0003", "Indomilk", "1", "Rp. 8.000.00", "01 Oktober 2022"},
                {"PD0004", "Nabati Wafer", "5", "Rp. 17.500.00", "01 Oktober 2022"},
                {"PD0005", "Coca Cola", "4", "Rp. 16.000.00", "01 Oktober 2022"},
                {"PD0006", "Oreo", "4", "Rp. 8.000.00", "01 Oktober 2022"},
                {"PD0007", "Teh Pucuk", "2", "Rp. 8.000.00", "01 Oktober 2022"},
                {"PD0008", "Coca Cola", "1", "Rp. 4.000.00", "02 Oktober 2022"},
                {"PD0009", "Oreo", "6", "Rp. 12.000.00", "02 Oktober 2022"},
                {"PD0010", "Ichi Ocha 350ml", "1", "Rp. 2.000.00", "02 Oktober 2022"},
                {"PD0011", "Aqua 500ml", "1", "Rp. 5.000.00", "02 Oktober 2022"},
                {"PD0012", "Coca Cola", "1", "Rp. 4.000.00", "02 Oktober 2022"},
                {"PD0013", "Spidol Merah", "2", "Rp. 5.000.00", "02 Oktober 2022"},
                {"PD0014", "Kertas Folio", "50", "Rp. 12.500.00", "03 Oktober 2022"},
                {"PD0015", "Yupi", "12", "Rp. 30.000.00", "03 Oktober 2022"},
                {"PD0016", "Nabati Wafer", "8", "Rp. 28.000.00", "03 Oktober 2022"},
                {"PD0017", "Nabati Wafer", "3", "Rp. 10.500.00", "03 Oktober 2022"},
                {"PD0018", "Coca Cola", "1", "Rp. 4.000.00", "03 Oktober 2022"},
                {"PD0019", "Ichi Ocha 350ml", "1", "Rp. 2.000.00", "03 Oktober 2022"},
                {"PD0020", "Indomilk", "1", "Rp. 8.000.00", "04 Oktober 2022"},
                {"PD0021", "Aqua 500ml", "3", "Rp. 15.000.00", "04 Oktober 2022"},
                {"PD0022", "Teh Pucuk", "2", "Rp. 8.000.00", "04 Oktober 2022"},
                {"PD0023", "Indomilk", "4", "Rp. 36.000.00", "04 Oktober 2022"}
            },
            new String [] {
                "ID Pendapatan", "Nama Barang", "Jumlah Barang", "Total Pendapatan", "Tanggal"
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpCariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari ID Pendapatan :");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnPrint.setBackground(new java.awt.Color(41, 180, 50));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-tambah.png"))); // NOI18N
        btnPrint.setText("Print Data");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrintMouseExited(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

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

        pnlDataPendapatan.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataPendapatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(11, 114, 238), 3));

        lblDataPendapatan.setBackground(new java.awt.Color(11, 114, 238));
        lblDataPendapatan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDataPendapatan.setForeground(new java.awt.Color(255, 255, 255));
        lblDataPendapatan.setText("  Detail Pendapatan");
        lblDataPendapatan.setOpaque(true);

        lblIDPendapatan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDPendapatan.setForeground(new java.awt.Color(0, 0, 0));
        lblIDPendapatan.setText("ID Pendapatan");

        lblIDTransaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIDTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lblIDTransaksi.setText("ID Transaksi");

        lblNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaPembeli.setText("Nama Pembeli");

        lblNamaKaryawan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaKaryawan.setText("Nama Karyawan");

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaBarang.setText("Nama Barang");

        lblJenisBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJenisBarang.setForeground(new java.awt.Color(0, 0, 0));
        lblJenisBarang.setText("Jenis Barang");

        lblTotalHarga.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalHarga.setText("Total Harga");

        lblJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlah.setText("Jumlah Barang");

        valIDPendapatan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDPendapatan.setForeground(new java.awt.Color(0, 0, 0));
        valIDPendapatan.setText(":");

        valIDTransaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIDTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        valIDTransaksi.setText(":");

        valNamaPembeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaPembeli.setForeground(new java.awt.Color(0, 0, 0));
        valNamaPembeli.setText(":");

        valNamaKaryawan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaKaryawan.setForeground(new java.awt.Color(0, 0, 0));
        valNamaKaryawan.setText(":");

        valNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaBarang.setForeground(new java.awt.Color(0, 0, 0));
        valNamaBarang.setText(":");

        valJenisBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valJenisBarang.setForeground(new java.awt.Color(0, 0, 0));
        valJenisBarang.setText(":");

        valJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valJumlah.setForeground(new java.awt.Color(0, 0, 0));
        valJumlah.setText(":");

        valTotalHarga.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        valTotalHarga.setText(":");

        lblTanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggal.setText("Tanggal");

        valTanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTanggal.setForeground(new java.awt.Color(0, 0, 0));
        valTanggal.setText(":");

        javax.swing.GroupLayout pnlDataPendapatanLayout = new javax.swing.GroupLayout(pnlDataPendapatan);
        pnlDataPendapatan.setLayout(pnlDataPendapatanLayout);
        pnlDataPendapatanLayout.setHorizontalGroup(
            pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataPendapatan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataPendapatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblJenisBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valIDPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valIDTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valJenisBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataPendapatanLayout.setVerticalGroup(
            pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPendapatanLayout.createSequentialGroup()
                .addComponent(lblDataPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDPendapatan)
                    .addComponent(valIDPendapatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDTransaksi)
                    .addComponent(valIDTransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaPembeli)
                    .addComponent(valNamaPembeli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaKaryawan)
                    .addComponent(valNamaKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaBarang)
                    .addComponent(valNamaBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenisBarang)
                    .addComponent(valJenisBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah)
                    .addComponent(valJumlah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalHarga)
                    .addComponent(valTotalHarga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataPendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggal)
                    .addComponent(valTanggal))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        paneProduk.setBackground(new java.awt.Color(255, 255, 255));
        paneProduk.setForeground(new java.awt.Color(255, 102, 51));
        paneProduk.setLayout(new java.awt.BorderLayout());
        tabPendapatan.addTab("Produk Terlaris", paneProduk);

        tabGrafikPendapatan.setBackground(new java.awt.Color(255, 255, 255));
        tabGrafikPendapatan.setLayout(new java.awt.BorderLayout());
        tabPendapatan.addTab("Grafik Pendapatan", tabGrafikPendapatan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDataPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tabPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpCari)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDataPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabPendapatan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int status;
        boolean delete;
        // mengecek apakah ada data yang dipilih atau tidak
        if(tabelData.getSelectedRow() > -1){
            // membuka confirm dialog untuk menghapus data
            Audio.play(Audio.SOUND_INFO);
            status = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus '" + this.idTr + "' ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            // mengecek pilihan dari barang
            switch(status){
                // jika yes maka data akan dihapus
                case JOptionPane.YES_OPTION : 
                    // menghapus data pembeli
                    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    delete = this.trj.deleteTransaksiJual(this.idTr);
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

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Audio.play(Audio.SOUND_INFO);
        CetakLaporan cetak = new CetakLaporan(null, true);
        cetak.setVisible(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        this.btnPrint.setIcon(Gambar.getIcon("ic-data-tambah-entered.png"));
    }//GEN-LAST:event_btnPrintMouseEntered

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        this.btnPrint.setIcon(Gambar.getIcon("ic-data-tambah.png"));
    }//GEN-LAST:event_btnPrintMouseExited

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
        // menampilkan data pembeli
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

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_tr_jual LIKE '%"+key+"%' OR id_barang LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyTyped

    private void inpCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyReleased
        String key = this.inpCari.getText();
        this.keyword = "WHERE id_tr_jual LIKE '%"+key+"%' OR id_barang LIKE '%"+key+"%'";
        this.updateTabel();
    }//GEN-LAST:event_inpCariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDataPendapatan;
    private javax.swing.JLabel lblIDPendapatan;
    private javax.swing.JLabel lblIDTransaksi;
    private javax.swing.JLabel lblJenisBarang;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblNamaKaryawan;
    private javax.swing.JLabel lblNamaPembeli;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JPanel paneProduk;
    private javax.swing.JPanel pnlDataPendapatan;
    private javax.swing.JPanel tabGrafikPendapatan;
    private javax.swing.JTabbedPane tabPendapatan;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valIDPendapatan;
    private javax.swing.JLabel valIDTransaksi;
    private javax.swing.JLabel valJenisBarang;
    private javax.swing.JLabel valJumlah;
    private javax.swing.JLabel valNamaBarang;
    private javax.swing.JLabel valNamaKaryawan;
    private javax.swing.JLabel valNamaPembeli;
    private javax.swing.JLabel valTanggal;
    private javax.swing.JLabel valTotalHarga;
    // End of variables declaration//GEN-END:variables
}
