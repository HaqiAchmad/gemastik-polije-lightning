package com.window.dialogs;

import com.manage.Message;
import com.media.Gambar;
import com.users.Barang;
import com.users.Pembeli;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;

/**
 *
 * @author Achmad Baihaqi
 */
public class InputBarang extends javax.swing.JDialog {

    private final Barang barang = new Barang();
    
    public int option;
    
    public static final int ADD_OPTION = 1, EDIT_OPTION = 2;
    
    private final String idBarang;
    
    private String nama, jenis,          newNama, newJenis;
    private int jumlah, hargaBeli, hargaJual, newJumlah, newHargaBeli, newHargaJual;
    private boolean isUpdated = false;
    
    /**
     * Creates new form TambahPembeli
     * @param parent
     * @param modal
     * @param idPembeli
     */
    public InputBarang(Frame parent, boolean modal, String idBarang) {
        super(parent, modal);
        initComponents();
        
        if(idBarang == null){
            // menyetting window untuk tambah data
            this.option = 1;
            this.idBarang = this.barang.createID();
            this.setTitle("Tambah Data Barang");
            this.lblTop.setText("Tambah Data Barang");
            this.btnSimpan.setText("Tambah");
        } else {
            // menyetting window untuk edit data
            this.option = 2;
            this.idBarang = idBarang;
            this.setTitle("Edit Data Barang");
            this.lblTop.setText("Edit Data Barang");
            this.btnSimpan.setText("Simpan");

            // mendapatkan data-data barang
            this.nama = this.barang.getNama(this.idBarang);
            this.jenis = this.barang.getJenis(this.idBarang);
            this.jumlah = Integer.parseInt(this.barang.getJumlah(this.idBarang));
            this.hargaBeli = Integer.parseInt(this.barang.getHargaBeli(this.idBarang));
            this.hargaJual = Integer.parseInt(this.barang.getHargaJual(this.idBarang));
            
            // menampilkan data-data pembeli ke input text
            this.inpNama.setText(this.nama);
            this.inpJenis.setText(this.jenis);
            this.inpJumlah.setText(Integer.toString(this.jumlah));
            this.inpHargaBeli.setText(Integer.toString(this.hargaBeli));
            this.inpHargaJual.setText(Integer.toString(this.hargaJual));
        }

        this.setLocationRelativeTo(null);
        
        this.inpId.setText(this.idBarang);
        this.btnSimpan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnCancel.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    /**
     * Mengecek apakah user menekan tombol simpan / tambah atau tidak
     * 
     * @return <strong>True</strong> jika user menekan tombol simpan / tambah. <br>
     *         <strong>False</strong> jika user menekan tombol kembali / close.
     */
    public boolean isUpdated(){
        return this.isUpdated;
    }
    
    /**
     * Digunakan untuk menambahkan data pembeli ke Database.
     * 
     */
    private void addData(){
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // mendapatkan data dari textfield
        this.nama = this.inpNama.getText();
        this.jenis = this.inpJenis.getText();
        this.jumlah = Integer.parseInt(this.inpJumlah.getText());
        this.hargaBeli = Integer.parseInt(this.inpHargaBeli.getText());
        this.hargaJual = Integer.parseInt(this.inpHargaJual.getText());
        
        // menambahkan data pembeli ke database
        boolean save = this.barang.addBarang(nama, jenis, jumlah, hargaBeli, hargaJual);
        
        // mengecek data berhasil disimpan atau belum
        if(save){
            Message.showInformation(this, "Data berhasil disimpan!");
            this.isUpdated = true;
            this.barang.closeConnection();
            this.dispose();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Digunakan untuk mengedit data dari pembeli
     * 
     */
    private void editData(){
        boolean eNama, eJenis, eJumlah, eHargaBeli, eHargaJual;
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        // mendapakan data dari textfield
        this.newNama = this.inpNama.getText();
        this.newJenis = this.inpJenis.getText();
        this.newJumlah = Integer.parseInt(this.inpJumlah.getText());
        this.newHargaBeli = Integer.parseInt(this.inpHargaBeli.getText());
        this.newHargaJual = Integer.parseInt(this.inpHargaJual.getText());
        
        // validasi data
        if(this.barang.validateAddBarang(this.idBarang, this.newNama, this.newJenis, this.newJumlah, this.newHargaBeli, this.newHargaJual)){
            // mengedit data
            eNama = this.barang.setNama(this.idBarang, this.newNama);
            eJenis = this.barang.setJenis(this.idBarang, this.newJenis);
            eJumlah = this.barang.setJumlah(this.idBarang, Integer.toString(this.newJumlah));
            eHargaBeli = this.barang.setHargaBeli(this.idBarang, Integer.toString(this.newHargaBeli));
            eHargaJual = this.barang.setHargaJual(this.idBarang, Integer.toString(this.newHargaJual));
            
            // mengecek apa data berhasil disave atau tidak
            if(eNama && eJenis && eJumlah && eHargaBeli && eHargaJual){
                Message.showInformation(this, "Data berhasil diedit!");
                this.isUpdated = true;
                this.barang.closeConnection();
                this.dispose();
            }
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        inpId = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        inpNama = new javax.swing.JTextField();
        lblJenis = new javax.swing.JLabel();
        inpJenis = new javax.swing.JTextField();
        lineTop = new javax.swing.JSeparator();
        lblHargaBeli = new javax.swing.JLabel();
        inpHargaBeli = new javax.swing.JTextField();
        lineBottom = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        inpHargaJual = new javax.swing.JTextField();
        inpJumlah = new javax.swing.JTextField();
        lblJumlah = new javax.swing.JLabel();
        lblHargaJual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(246, 247, 248));

        lblTop.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblTop.setForeground(new java.awt.Color(222, 8, 8));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambah Data Barang");

        lblId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(28, 115, 196));
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblId.setText("ID Barang");
        lblId.setMaximumSize(new java.awt.Dimension(305, 17));
        lblId.setMinimumSize(new java.awt.Dimension(305, 17));
        lblId.setPreferredSize(new java.awt.Dimension(305, 17));

        inpId.setBackground(new java.awt.Color(211, 215, 224));
        inpId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpId.setForeground(new java.awt.Color(0, 0, 0));
        inpId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpId.setText("PB587");
        inpId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpId.setCaretColor(new java.awt.Color(230, 11, 11));
        inpId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        inpId.setEnabled(false);
        inpId.setMaximumSize(new java.awt.Dimension(305, 21));
        inpId.setMinimumSize(new java.awt.Dimension(305, 21));
        inpId.setPreferredSize(new java.awt.Dimension(305, 21));
        inpId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpIdMouseClicked(evt);
            }
        });

        lblNama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNama.setForeground(new java.awt.Color(28, 115, 196));
        lblNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNama.setText("Nama Barang");

        inpNama.setBackground(new java.awt.Color(255, 255, 255));
        inpNama.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNama.setForeground(new java.awt.Color(0, 0, 0));
        inpNama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpNama.setCaretColor(new java.awt.Color(213, 8, 8));
        inpNama.setMaximumSize(new java.awt.Dimension(305, 21));
        inpNama.setMinimumSize(new java.awt.Dimension(305, 21));
        inpNama.setPreferredSize(new java.awt.Dimension(305, 21));

        lblJenis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(28, 115, 196));
        lblJenis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJenis.setText("Jenis Barang");

        inpJenis.setBackground(new java.awt.Color(255, 255, 255));
        inpJenis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpJenis.setForeground(new java.awt.Color(0, 0, 0));
        inpJenis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpJenis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpJenis.setCaretColor(new java.awt.Color(213, 8, 8));
        inpJenis.setMaximumSize(new java.awt.Dimension(305, 21));
        inpJenis.setMinimumSize(new java.awt.Dimension(305, 21));
        inpJenis.setPreferredSize(new java.awt.Dimension(305, 21));
        inpJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpJenisActionPerformed(evt);
            }
        });

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblHargaBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHargaBeli.setForeground(new java.awt.Color(28, 115, 196));
        lblHargaBeli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHargaBeli.setText("Harga Beli");

        inpHargaBeli.setBackground(new java.awt.Color(255, 255, 255));
        inpHargaBeli.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpHargaBeli.setForeground(new java.awt.Color(0, 0, 0));
        inpHargaBeli.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpHargaBeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpHargaBeli.setCaretColor(new java.awt.Color(213, 8, 8));
        inpHargaBeli.setMaximumSize(new java.awt.Dimension(305, 21));
        inpHargaBeli.setMinimumSize(new java.awt.Dimension(305, 21));
        inpHargaBeli.setPreferredSize(new java.awt.Dimension(305, 21));
        inpHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpHargaBeliActionPerformed(evt);
            }
        });

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnSimpan.setBackground(new java.awt.Color(34, 119, 237));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-save.png"))); // NOI18N
        btnSimpan.setText("Tambah");
        btnSimpan.setPreferredSize(new java.awt.Dimension(130, 28));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(220, 41, 41));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-cancel.png"))); // NOI18N
        btnCancel.setText("Batal");
        btnCancel.setMaximumSize(new java.awt.Dimension(130, 28));
        btnCancel.setMinimumSize(new java.awt.Dimension(130, 28));
        btnCancel.setPreferredSize(new java.awt.Dimension(130, 28));
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        inpHargaJual.setBackground(new java.awt.Color(255, 255, 255));
        inpHargaJual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpHargaJual.setForeground(new java.awt.Color(0, 0, 0));
        inpHargaJual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpHargaJual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpHargaJual.setCaretColor(new java.awt.Color(213, 8, 8));
        inpHargaJual.setMaximumSize(new java.awt.Dimension(305, 21));
        inpHargaJual.setMinimumSize(new java.awt.Dimension(305, 21));
        inpHargaJual.setPreferredSize(new java.awt.Dimension(305, 21));
        inpHargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpHargaJualActionPerformed(evt);
            }
        });

        inpJumlah.setBackground(new java.awt.Color(255, 255, 255));
        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpJumlah.setForeground(new java.awt.Color(0, 0, 0));
        inpJumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inpJumlah.setCaretColor(new java.awt.Color(213, 8, 8));
        inpJumlah.setMaximumSize(new java.awt.Dimension(305, 21));
        inpJumlah.setMinimumSize(new java.awt.Dimension(305, 21));
        inpJumlah.setPreferredSize(new java.awt.Dimension(305, 21));

        lblJumlah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(28, 115, 196));
        lblJumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJumlah.setText("Jumlah");
        lblJumlah.setMaximumSize(new java.awt.Dimension(305, 17));
        lblJumlah.setMinimumSize(new java.awt.Dimension(305, 17));
        lblJumlah.setPreferredSize(new java.awt.Dimension(305, 17));

        lblHargaJual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHargaJual.setForeground(new java.awt.Color(28, 115, 196));
        lblHargaJual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHargaJual.setText("Harga Jual");
        lblHargaJual.setMaximumSize(new java.awt.Dimension(305, 17));
        lblHargaJual.setMinimumSize(new java.awt.Dimension(305, 17));
        lblHargaJual.setPreferredSize(new java.awt.Dimension(305, 17));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(inpNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblHargaBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inpHargaBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(inpId, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inpJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(inpHargaJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblHargaJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(27, 27, 27))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineTop, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lineBottom))
                        .addContainerGap())))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(lblHargaBeli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenis)
                    .addComponent(lblHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setBackground(this.btnSimpan.getBackground().darker());
        this.btnSimpan.setIcon(Gambar.getIcon("ic-manipulasi-save-entered.png"));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setBackground(new Color(34,119,237));
        this.btnSimpan.setIcon(Gambar.getIcon("ic-manipulasi-save.png"));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // action button sesuai opsi tambah atau edit
        switch(option){
            case ADD_OPTION : this.addData(); break;
            case EDIT_OPTION : this.editData(); break;
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        this.btnCancel.setBackground(this.btnCancel.getBackground().darker());
        this.btnCancel.setIcon(Gambar.getIcon("ic-manipulasi-cancel-entered.png"));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        this.btnCancel.setBackground(new Color(220,41,41));
        this.btnCancel.setIcon(Gambar.getIcon("ic-manipulasi-cancel.png"));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        barang.closeConnection();
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void inpIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpIdMouseClicked
        Message.showWarning(this, "ID Pembeli tidak bisa diedit!");
    }//GEN-LAST:event_inpIdMouseClicked

    private void inpHargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpHargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpHargaJualActionPerformed

    private void inpJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpJenisActionPerformed

    private void inpHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpHargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpHargaBeliActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputPembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                InputPembeli dialog = new InputPembeli(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField inpHargaBeli;
    private javax.swing.JTextField inpHargaJual;
    private javax.swing.JTextField inpId;
    private javax.swing.JTextField inpJenis;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JTextField inpNama;
    private javax.swing.JLabel lblHargaBeli;
    private javax.swing.JLabel lblHargaJual;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
