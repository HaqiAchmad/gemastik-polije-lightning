package com.window.pembayaran;

import com.data.app.Application;
import com.data.app.Kelas;
import com.data.app.Transaksi;
import com.manage.Message;
import com.manage.Text;
import com.manage.Waktu;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import java.awt.Frame;

/**
 *
 * @author Achamd Baihaqi
 * @since 12 Juli 2021
 */
public class WindowPembayaran extends javax.swing.JDialog {
    
    private final Frame parent;
    private final boolean modal;
    
    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final Transaksi tr = new Transaksi();
    
    private final Text txt = new Text();
    private final Waktu waktu = new Waktu();
    
    private final String nis, namaSiswa, kelas;
    private String blnBayar;
    private int thnBayar, jmlBayar, kekuranganSpp;

    public WindowPembayaran(Frame parent, boolean modal, String nis) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // mendapatkan data-data dari siswa
        this.nis = nis;
        this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
        this.kelas = kls.getNamaKelas(siswa.getIdKelas(nis));
        
        // menampilkan data-data siswa
        this.setTitle(namaSiswa + " - Pembayaran SPP");
        this.valNis.setText("<html><p>:&nbsp;" + nis + "</p></html>");
        this.valNama.setText("<html><p>:&nbsp;" + namaSiswa + "</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;" + kelas + "</p></html>");
        
        // menampilkan data default dari bulan, tahun dan jumlah bayar
        this.inpBulan.setSelectedItem(waktu.getNamaBulan(waktu.getBulan()));
        this.inpTahun.setSelectedItem(Application.getTahunAjaran().substring(0, 4));
        this.setDefaultJumlah();
        
        // mengatur ui pada button
        this.btnBayar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDef.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    public WindowPembayaran(Frame parent, boolean modal, String nis, String blnBayar, String thnBayar, String jmlBayar) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        
        // mendapatkan data-data dari siswa
        this.nis = nis;
        this.namaSiswa = txt.toCapitalize(siswa.getNama(nis));
        this.kelas = kls.getNamaKelas(siswa.getIdKelas(nis));
        this.blnBayar = blnBayar;
        this.thnBayar = Integer.parseInt(thnBayar);
        this.jmlBayar = Integer.parseInt(jmlBayar);
        
        // menampilkan data-data siswa
        this.setTitle(namaSiswa + " - Pembayaran SPP");
        this.valNis.setText("<html><p>:&nbsp;" + nis + "</p></html>");
        this.valNama.setText("<html><p>:&nbsp;" + namaSiswa + "</p></html>");
        this.valKelas.setText("<html><p>:&nbsp;" + kelas + "</p></html>");
        this.inpBulan.setSelectedItem(blnBayar);
        this.inpTahun.setSelectedItem(Integer.toString(this.thnBayar));
        this.inpJumlah.setText(txt.addDelim(this.jmlBayar));
        
        // mengatur ui pada button
        this.btnBayar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDef.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private void setDefaultJumlah(){
        String iBulan = this.inpBulan.getSelectedItem().toString(),
               iTahun = this.inpTahun.getSelectedItem().toString();
        
        if(iBulan.equalsIgnoreCase("pilih bulan") || iTahun.equalsIgnoreCase("pilih tahun")){
            this.inpJumlah.setText(txt.addDelim(tr.getNominalSppByNis(Integer.parseInt(nis))));
        }else{
            this.kekuranganSpp = tr.getKekuranganSppBySiswa(Integer.parseInt(nis), iBulan, Integer.parseInt(iTahun));
            this.inpJumlah.setText(txt.addDelim(this.kekuranganSpp));
        }
    }
    
    private void addDelimInInput(){
        // mendapatkan input dari textfield
        String input = inpJumlah.getText();
        // mengecek apakah input merupakan sebuah number atau tidak
        if(txt.isNumber(input)){
            input = txt.removeDelim(input);
            this.inpJumlah.setText(txt.addDelim(Long.parseLong(input)));
        }else if(!input.equals("") && !txt.isNumber(input)){
            Message.showInformation(this, "Input harus berupa angka!");
            this.inpJumlah.setText("");
        }
    }
    
    private boolean validateInput(){
        
        String iBulan = this.inpBulan.getSelectedItem().toString();
        
        int iNis = Integer.parseInt(nis),
            iTahun = Integer.parseInt(this.inpTahun.getSelectedItem().toString()),
            iJumlah, sppKurang, kelebihan, dibayar;
        
        
        // jika tahun pembayaran yang diinputkan lebih kecil dari tahun nominal sppp maka input dinyatakan tidak valid
        if(iTahun < tr.getTahunSpp(Integer.parseInt(siswa.getIdSpp(nis)))){
            Message.showWarning(this, "Tahun pembayaran tidak valid!", true);
            return false;
        }

       
       // jika input jumlah lunas maka input dinyatkan tidak valid
       if(this.inpJumlah.getText().equals("") || this.inpJumlah.getText().equals("0")){
           Message.showWarning(this, "Jumlah bayar tidak boleh kosong!", true);
           return false;
       }else{
           // mendapatkan input jumlah bayar dan kekurangan spp siswa pada bulan dan tahun yang diinputkan
           iJumlah = Integer.parseInt(txt.removeDelim(this.inpJumlah.getText()));
           sppKurang = this.tr.getKekuranganSppBySiswa(iNis, iBulan, iTahun);
       }
       
       // jika siswa sudah melunasi pada bulan dan tahun yang diinputkan maka input dinyatakan tidak valid
       if(this.tr.isLunas(iNis, iBulan, iTahun)){
           Message.showWarning(this, "Siswa ini sudah melunasi pembayaran SPP pada bulan " + iBulan + " " + iTahun, true);
           return false;
       }
       // jika input jumlah bayar lebih dari kekurangan spp maka input dinyatakan tidak valid
       if(iJumlah > sppKurang){
           // mendapatkan kelebihan pembayaran dan total spp yang sudah dibayar
           kelebihan = iJumlah - sppKurang;
           dibayar = tr.getTotalSppDibayarBySiswa(iNis, iBulan, iTahun);
           
           // menampilkan pesan warning
           Message.showWarning(this, 
                   String.format("Uang anda kelebihan %s!\nSiswa ini sebelumnya sudah membayar pada bulan %s %s sebesar %s", 
                           txt.toMoneyCase(Integer.toString(kelebihan)), iBulan, iTahun, txt.toMoneyCase(Integer.toString(dibayar))
                   ), 
                   true
           );
           return false;
       }
       
        return true;
    }
    
    private void closeConnection(){
        this.siswa.closeConnection();
        this.kls.closeConnection();
        this.tr.closeConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        lblNis = new javax.swing.JLabel();
        lbNama = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        lblBulanBayar = new javax.swing.JLabel();
        inpBulan = new javax.swing.JComboBox();
        valNis = new javax.swing.JLabel();
        valNama = new javax.swing.JLabel();
        valKelas = new javax.swing.JLabel();
        lblJumlahBayar = new javax.swing.JLabel();
        inpJumlah = new javax.swing.JTextField();
        lineBottom = new javax.swing.JSeparator();
        btnBayar = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        lblTahunBayar = new javax.swing.JLabel();
        inpTahun = new javax.swing.JComboBox();
        lblRp = new javax.swing.JLabel();
        btnDef = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bayar SPP");
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
        lblTop.setText("Pembayaran SPP Siswa");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblNis.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lbNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbNama.setForeground(new java.awt.Color(0, 0, 0));
        lbNama.setText("Nama Siswa");

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("Kelas");

        lblBulanBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBulanBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblBulanBayar.setText("Bulan Bayar");

        inpBulan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpBulan.setForeground(new java.awt.Color(0, 0, 0));
        inpBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Juli", "Agustus", "September", "Oktober", "November", "Desember", "Januari", "Februari", "Maret", "April", "Mei", "Juni" }));

        valNis.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": 6156");

        valNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valNama.setForeground(new java.awt.Color(0, 0, 0));
        valNama.setText(": Achmad Baihaqi");

        valKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        valKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKelas.setText(": XII-RPL-1");

        lblJumlahBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblJumlahBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblJumlahBayar.setText("Jumlah Bayar");

        inpJumlah.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inpJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpJumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpJumlahKeyTyped(evt);
            }
        });

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        btnBayar.setBackground(new java.awt.Color(34, 119, 237));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-bayar.png"))); // NOI18N
        btnBayar.setText("Bayar");
        btnBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBayarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBayarMouseExited(evt);
            }
        });
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-pembayaran-cancel.png"))); // NOI18N
        btnBatal.setText("Batal");
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

        lblTahunBayar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTahunBayar.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunBayar.setText("Tahun Bayar");

        inpTahun.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpTahun.setForeground(new java.awt.Color(0, 0, 0));
        inpTahun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2017", "2018", "2019", "2020", "2021", "2022" }));

        lblRp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblRp.setForeground(new java.awt.Color(0, 0, 0));
        lblRp.setText("Rp.");

        btnDef.setBackground(new java.awt.Color(237, 106, 35));
        btnDef.setForeground(new java.awt.Color(255, 255, 255));
        btnDef.setText("Default");
        btnDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lbNama, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valNis, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lineTop)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lineBottom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblJumlahBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTahunBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblRp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDef)
                                        .addGap(6, 6, 6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpTahun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpBulan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(3, 3, 3)))))
                        .addGap(15, 15, 15))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valNis, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lblNis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpBulan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpTahun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDef, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(inpJumlah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBayar)
                    .addComponent(btnBatal))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosing

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // mengecek input valid atau tidak
        if(this.validateInput()){
            // mendapatkan input
            this.blnBayar = this.inpBulan.getSelectedItem().toString();
            this.thnBayar = Integer.parseInt(this.inpTahun.getSelectedItem().toString());
            this.jmlBayar = Integer.parseInt(txt.removeDelim(this.inpJumlah.getText()));
            // menutup window
            this.dispose();
            // membuka window konfirmasi
            Audio.play(Audio.SOUND_INFO);
            new KonfirmasiPembayaran(parent, modal, this.nis, this.blnBayar, Integer.toString(this.thnBayar), Integer.toString(this.jmlBayar)).setVisible(true);            
        }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnBayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseEntered
        this.btnBayar.setIcon(Gambar.getIcon("ic-pembayaran-pay-entered.png"));
    }//GEN-LAST:event_btnBayarMouseEntered

    private void btnBayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseExited
        this.btnBayar.setIcon(Gambar.getIcon("ic-pembayaran-bayar.png"));
    }//GEN-LAST:event_btnBayarMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setIcon(Gambar.getIcon("ic-pembayaran-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void inpJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpJumlahKeyTyped
        this.addDelimInInput();
    }//GEN-LAST:event_inpJumlahKeyTyped

    private void inpJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpJumlahKeyReleased
        this.addDelimInInput();
    }//GEN-LAST:event_inpJumlahKeyReleased

    private void btnDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefActionPerformed
        this.setDefaultJumlah();
    }//GEN-LAST:event_btnDefActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Message.showException(null, ex, true);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                WindowPembayaran dialog = new WindowPembayaran(new javax.swing.JFrame(), true, "6156");
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
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnDef;
    private javax.swing.JComboBox inpBulan;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JComboBox inpTahun;
    private javax.swing.JLabel lbNama;
    private javax.swing.JLabel lblBulanBayar;
    private javax.swing.JLabel lblJumlahBayar;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblRp;
    private javax.swing.JLabel lblTahunBayar;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valKelas;
    private javax.swing.JLabel valNama;
    private javax.swing.JLabel valNis;
    // End of variables declaration//GEN-END:variables
}
