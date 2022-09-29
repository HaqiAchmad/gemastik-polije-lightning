package com.window.petugas;

import com.manage.Message;
import com.media.Gambar;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JLabel;

/**
 *
 * @author Achmad Baihaqi
 * @since 18 Juli 2021
 */
public class DaftarPenggunaSpp extends javax.swing.JDialog {

    public DaftarPenggunaSpp(java.awt.Frame parent, boolean modal, String IdSpp) {
        super(parent, modal);
        initComponents();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        
        this.setTitle(IdSpp + " ~ Daftar Pengguna SPP");
        
        // mengatur ui pada button
        this.btnEditSpp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapusSiswa.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        JLabel[] infoKelas = new JLabel[]{
            this.valIdSpp, this.valTahunSpp, this.valNominalSpp
        };
        
        for(JLabel lbl : infoKelas){
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
                    lbl.setForeground(new Color(5,170,57));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lbl.setForeground(new Color(0, 0, 0));
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
        
        JLabel[] infoSiswa = new JLabel[]{
            this.valNis, this.valNamaSiswa, this.valJenisKelamin, this.valTtl, this.valNoHp, this.valEmail, this.valAlamat
        };
        
        for(JLabel lbl : infoSiswa){
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
                    lbl.setForeground(new Color(15,98,230));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lbl.setForeground(new Color(0, 0, 0));
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
        
        
        this.tabelData.setRowHeight(29);
        this.tabelData.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelData.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        lblCari = new javax.swing.JLabel();
        inpCari = new javax.swing.JTextField();
        lineCenter = new javax.swing.JSeparator();
        pnlInfoSpp = new javax.swing.JPanel();
        pnlTitleInfoSpp = new javax.swing.JPanel();
        lblInfoSpp = new javax.swing.JLabel();
        lblIdSpp = new javax.swing.JLabel();
        valIdSpp = new javax.swing.JLabel();
        lblTahunSpp = new javax.swing.JLabel();
        valTahunSpp = new javax.swing.JLabel();
        lblNominalSpp = new javax.swing.JLabel();
        valNominalSpp = new javax.swing.JLabel();
        pnlInfoSiswa = new javax.swing.JPanel();
        pnlTitleInfoSiswa = new javax.swing.JPanel();
        lblInfoSiswa = new javax.swing.JLabel();
        lblNis = new javax.swing.JLabel();
        valNis = new javax.swing.JLabel();
        lblNamaSiswa = new javax.swing.JLabel();
        valNamaSiswa = new javax.swing.JLabel();
        valJenisKelamin = new javax.swing.JLabel();
        lblJenisKelamin = new javax.swing.JLabel();
        lblTtl = new javax.swing.JLabel();
        valTtl = new javax.swing.JLabel();
        valEmail = new javax.swing.JLabel();
        valNoHp = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        valAlamat = new javax.swing.JLabel();
        lineInfoSiswa = new javax.swing.JSeparator();
        btnEditSpp = new javax.swing.JButton();
        btnHapusSiswa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        lblTop.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        lblTop.setForeground(new java.awt.Color(0, 0, 0));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Daftar Pengguna SPP 21 (2021)");

        lineTop.setBackground(new java.awt.Color(0, 134, 224));
        lineTop.setForeground(new java.awt.Color(0, 134, 224));

        tabelData.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"6156", "Achmad Baihaqi", "Laki-Laki", "XII-RPL-1"},
                {"6157", "Ade Raihan Masha", "Laki-Laki", "XII-RPL-1"},
                {"6158", "Adelia Putri Gita Novita Sari", "Perempuan", "X-TITL-2"},
                {"6159", "Adetya Wardani", "Perempuan", "XI-TBG-1"},
                {"6160", "Agunt Tri Laksono", "Laki-Laki", "X-TPM-3"},
                {"6161", "Ahmad Ansori", "Laki-Laki", "X-RPL-2"},
                {"6162", "Ahmad Rozikul", "Laki-Laki", "X-TBS-1"},
                {"6163", "Amalia Devi Fitriana", "Perempuan", "XII-TOI-1"},
                {"6164", "Ananta Eka Prayoga", "Laki-Laki", "X-TITL-3"},
                {"6165", "Aning Rahma Handayani", "Perempuan", "XII-TBS-2"}
            },
            new String [] {
                "NIS", "Nama Siswa", "Jenis Kelamin", "Kelas"
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
        jScrollPane1.setViewportView(tabelData);

        lblCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCari.setForeground(new java.awt.Color(237, 12, 12));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari NIS / Nama Siswa : ");

        inpCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpCari.setForeground(new java.awt.Color(0, 0, 0));
        inpCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });

        lineCenter.setBackground(new java.awt.Color(0, 134, 224));
        lineCenter.setForeground(new java.awt.Color(0, 134, 224));
        lineCenter.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pnlInfoSpp.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoSpp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 2));

        pnlTitleInfoSpp.setBackground(new java.awt.Color(5, 170, 57));

        lblInfoSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfoSpp.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoSpp.setText("Informasi SPP");

        javax.swing.GroupLayout pnlTitleInfoSppLayout = new javax.swing.GroupLayout(pnlTitleInfoSpp);
        pnlTitleInfoSpp.setLayout(pnlTitleInfoSppLayout);
        pnlTitleInfoSppLayout.setHorizontalGroup(
            pnlTitleInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleInfoSppLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleInfoSppLayout.setVerticalGroup(
            pnlTitleInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoSpp, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        lblIdSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIdSpp.setForeground(new java.awt.Color(0, 0, 0));
        lblIdSpp.setText("ID SPP");

        valIdSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valIdSpp.setForeground(new java.awt.Color(0, 0, 0));
        valIdSpp.setText(": null");

        lblTahunSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTahunSpp.setForeground(new java.awt.Color(0, 0, 0));
        lblTahunSpp.setText("Tahun SPP");

        valTahunSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTahunSpp.setForeground(new java.awt.Color(0, 0, 0));
        valTahunSpp.setText(": null");

        lblNominalSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNominalSpp.setForeground(new java.awt.Color(0, 0, 0));
        lblNominalSpp.setText("Nominal SPP");

        valNominalSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNominalSpp.setForeground(new java.awt.Color(0, 0, 0));
        valNominalSpp.setText(": null");

        javax.swing.GroupLayout pnlInfoSppLayout = new javax.swing.GroupLayout(pnlInfoSpp);
        pnlInfoSpp.setLayout(pnlInfoSppLayout);
        pnlInfoSppLayout.setHorizontalGroup(
            pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoSpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoSppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoSppLayout.createSequentialGroup()
                        .addComponent(lblIdSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valIdSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSppLayout.createSequentialGroup()
                        .addComponent(lblTahunSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valTahunSpp, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(pnlInfoSppLayout.createSequentialGroup()
                        .addComponent(lblNominalSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNominalSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInfoSppLayout.setVerticalGroup(
            pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSppLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoSpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdSpp)
                    .addComponent(valIdSpp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahunSpp)
                    .addComponent(valTahunSpp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNominalSpp)
                    .addComponent(valNominalSpp))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pnlInfoSiswa.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoSiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 98, 230), 2));

        pnlTitleInfoSiswa.setBackground(new java.awt.Color(15, 98, 230));

        lblInfoSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfoSiswa.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoSiswa.setText("Informasi Siswa");

        javax.swing.GroupLayout pnlTitleInfoSiswaLayout = new javax.swing.GroupLayout(pnlTitleInfoSiswa);
        pnlTitleInfoSiswa.setLayout(pnlTitleInfoSiswaLayout);
        pnlTitleInfoSiswaLayout.setHorizontalGroup(
            pnlTitleInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleInfoSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleInfoSiswaLayout.setVerticalGroup(
            pnlTitleInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoSiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        lblNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        valNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNis.setForeground(new java.awt.Color(0, 0, 0));
        valNis.setText(": null");

        lblNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSiswa.setText("Nama Siswa");

        valNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        valNamaSiswa.setText(": null");

        valJenisKelamin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valJenisKelamin.setForeground(new java.awt.Color(0, 0, 0));
        valJenisKelamin.setText(": null");

        lblJenisKelamin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJenisKelamin.setForeground(new java.awt.Color(0, 0, 0));
        lblJenisKelamin.setText("Jenis Kelamin");

        lblTtl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTtl.setForeground(new java.awt.Color(0, 0, 0));
        lblTtl.setText("TTL");

        valTtl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valTtl.setForeground(new java.awt.Color(0, 0, 0));
        valTtl.setText(": null");

        valEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valEmail.setForeground(new java.awt.Color(0, 0, 0));
        valEmail.setText(": null");

        valNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNoHp.setForeground(new java.awt.Color(0, 0, 0));
        valNoHp.setText(": null");
        valNoHp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valNoHpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valNoHpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valNoHpMouseExited(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        valAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAlamat.setForeground(new java.awt.Color(0, 0, 0));
        valAlamat.setText(": null");

        lineInfoSiswa.setBackground(new java.awt.Color(15, 98, 230));
        lineInfoSiswa.setForeground(new java.awt.Color(15, 98, 230));

        btnEditSpp.setBackground(new java.awt.Color(34, 119, 237));
        btnEditSpp.setForeground(new java.awt.Color(255, 255, 255));
        btnEditSpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEditSpp.setText("Edit SPP");
        btnEditSpp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditSppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditSppMouseExited(evt);
            }
        });
        btnEditSpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSppActionPerformed(evt);
            }
        });

        btnHapusSiswa.setBackground(new java.awt.Color(235, 29, 29));
        btnHapusSiswa.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnHapusSiswa.setText("Hapus Siswa");
        btnHapusSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusSiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusSiswaMouseExited(evt);
            }
        });
        btnHapusSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusSiswaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoSiswaLayout = new javax.swing.GroupLayout(pnlInfoSiswa);
        pnlInfoSiswa.setLayout(pnlInfoSiswaLayout);
        pnlInfoSiswaLayout.setHorizontalGroup(
            pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoSiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNis, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valJenisKelamin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblTtl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valTtl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnEditSpp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusSiswa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lineInfoSiswa))
                .addContainerGap())
        );
        pnlInfoSiswaLayout.setVerticalGroup(
            pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNis)
                    .addComponent(valNis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaSiswa)
                    .addComponent(valNamaSiswa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenisKelamin)
                    .addComponent(valJenisKelamin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTtl)
                    .addComponent(valTtl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoHp)
                    .addComponent(valNoHp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(valEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlamat)
                    .addComponent(valAlamat))
                .addGap(18, 18, 18)
                .addComponent(lineInfoSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditSpp)
                    .addComponent(btnHapusSiswa))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lineTop)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlInfoSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lineCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpCari))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(lineCenter)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlInfoSpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlInfoSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked

    }//GEN-LAST:event_tabelDataMouseClicked

    private void tabelDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelDataKeyPressed

    }//GEN-LAST:event_tabelDataKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped

    }//GEN-LAST:event_inpCariKeyTyped

    private void valNoHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseClicked
        
    }//GEN-LAST:event_valNoHpMouseClicked

    private void valNoHpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseEntered
        
    }//GEN-LAST:event_valNoHpMouseEntered

    private void valNoHpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valNoHpMouseExited
        
    }//GEN-LAST:event_valNoHpMouseExited

    private void btnEditSppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSppActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnEditSppActionPerformed

    private void btnEditSppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditSppMouseEntered
        this.btnEditSpp.setIcon(Gambar.getIcon("ic-data-edit-entered.png"));
    }//GEN-LAST:event_btnEditSppMouseEntered

    private void btnEditSppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditSppMouseExited
        this.btnEditSpp.setIcon(Gambar.getIcon("ic-data-edit.png"));
    }//GEN-LAST:event_btnEditSppMouseExited

    private void btnHapusSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusSiswaActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnHapusSiswaActionPerformed

    private void btnHapusSiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusSiswaMouseEntered
        this.btnHapusSiswa.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnHapusSiswaMouseEntered

    private void btnHapusSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusSiswaMouseExited
        this.btnHapusSiswa.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnHapusSiswaMouseExited

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
            java.util.logging.Logger.getLogger(DaftarPenggunaSpp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DaftarPenggunaSpp dialog = new DaftarPenggunaSpp(new javax.swing.JFrame(), true, "21");
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
    private javax.swing.JButton btnEditSpp;
    private javax.swing.JButton btnHapusSiswa;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIdSpp;
    private javax.swing.JLabel lblInfoSiswa;
    private javax.swing.JLabel lblInfoSpp;
    private javax.swing.JLabel lblJenisKelamin;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblNominalSpp;
    private javax.swing.JLabel lblTahunSpp;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTtl;
    private javax.swing.JSeparator lineCenter;
    private javax.swing.JSeparator lineInfoSiswa;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlInfoSiswa;
    private javax.swing.JPanel pnlInfoSpp;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitleInfoSiswa;
    private javax.swing.JPanel pnlTitleInfoSpp;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valEmail;
    private javax.swing.JLabel valIdSpp;
    private javax.swing.JLabel valJenisKelamin;
    private javax.swing.JLabel valNamaSiswa;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valNominalSpp;
    private javax.swing.JLabel valTahunSpp;
    private javax.swing.JLabel valTtl;
    // End of variables declaration//GEN-END:variables
}
