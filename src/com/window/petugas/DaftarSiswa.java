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
public class DaftarSiswa extends javax.swing.JDialog {

    public DaftarSiswa(java.awt.Frame parent, boolean modal, String idKelas) {
        super(parent, modal);
        initComponents();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        
        this.setTitle(idKelas + " ~ Daftar Siswa");
        
        // mengatur ui pada button
        this.btnSetKetua.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEditKelas.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnKeluarkan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        JLabel[] infoKelas = new JLabel[]{
            this.valNamaKelas, this.valWaliKelas, this.valKetuaKelas
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
        pnlInfoKelas = new javax.swing.JPanel();
        pnlTitleInfoKelas = new javax.swing.JPanel();
        lblInfoKelas = new javax.swing.JLabel();
        lblNamaKelas = new javax.swing.JLabel();
        valNamaKelas = new javax.swing.JLabel();
        lblWaliKelas = new javax.swing.JLabel();
        valWaliKelas = new javax.swing.JLabel();
        lblKetuaKelas = new javax.swing.JLabel();
        valKetuaKelas = new javax.swing.JLabel();
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
        btnSetKetua = new javax.swing.JButton();
        btnEditKelas = new javax.swing.JButton();
        btnKeluarkan = new javax.swing.JButton();

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
        lblTop.setText("Daftar Siswa Kelas XII-RPL-1");

        lineTop.setBackground(new java.awt.Color(0, 134, 224));
        lineTop.setForeground(new java.awt.Color(0, 134, 224));

        tabelData.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        tabelData.setForeground(new java.awt.Color(0, 0, 0));
        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"6156", "Achmad Baihaqi", "Laki-Laki"},
                {"6157", "Ade Raihan Masha", "Laki-Laki"},
                {"6158", "Adelia Putri Gita Novita Sari", "Perempuan"},
                {"6159", "Adetya Wardani", "Perempuan"},
                {"6160", "Agunt Tri Laksono", "Laki-Laki"},
                {"6161", "Ahmad Ansori", "Laki-Laki"},
                {"6162", "Ahmad Rozikul", "Laki-Laki"},
                {"6163", "Amalia Devi Fitriana", "Perempuan"},
                {"6164", "Ananta Eka Prayoga", "Laki-Laki"},
                {"6165", "Aning Rahma Handayani", "Perempuan"}
            },
            new String [] {
                "NIS", "Nama Siswa", "Jenis Kelamin"
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

        pnlInfoKelas.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoKelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 170, 57), 2));

        pnlTitleInfoKelas.setBackground(new java.awt.Color(5, 170, 57));

        lblInfoKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfoKelas.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoKelas.setText("Informasi Kelas");

        javax.swing.GroupLayout pnlTitleInfoKelasLayout = new javax.swing.GroupLayout(pnlTitleInfoKelas);
        pnlTitleInfoKelas.setLayout(pnlTitleInfoKelasLayout);
        pnlTitleInfoKelasLayout.setHorizontalGroup(
            pnlTitleInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleInfoKelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleInfoKelasLayout.setVerticalGroup(
            pnlTitleInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfoKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        lblNamaKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaKelas.setText("Nama Kelas");

        valNamaKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valNamaKelas.setForeground(new java.awt.Color(0, 0, 0));
        valNamaKelas.setText(": null");

        lblWaliKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblWaliKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblWaliKelas.setText("Wali Kelas");

        valWaliKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valWaliKelas.setForeground(new java.awt.Color(0, 0, 0));
        valWaliKelas.setText(": null");

        lblKetuaKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKetuaKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKetuaKelas.setText("Ketua Kelas");

        valKetuaKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKetuaKelas.setForeground(new java.awt.Color(0, 0, 0));
        valKetuaKelas.setText(": null");

        javax.swing.GroupLayout pnlInfoKelasLayout = new javax.swing.GroupLayout(pnlInfoKelas);
        pnlInfoKelas.setLayout(pnlInfoKelasLayout);
        pnlInfoKelasLayout.setHorizontalGroup(
            pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoKelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoKelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoKelasLayout.createSequentialGroup()
                        .addComponent(lblNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valNamaKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoKelasLayout.createSequentialGroup()
                        .addComponent(lblWaliKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valWaliKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(pnlInfoKelasLayout.createSequentialGroup()
                        .addComponent(lblKetuaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valKetuaKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInfoKelasLayout.setVerticalGroup(
            pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoKelasLayout.createSequentialGroup()
                .addComponent(pnlTitleInfoKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaKelas)
                    .addComponent(valNamaKelas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWaliKelas)
                    .addComponent(valWaliKelas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKetuaKelas)
                    .addComponent(valKetuaKelas))
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

        btnSetKetua.setBackground(new java.awt.Color(41, 180, 50));
        btnSetKetua.setForeground(new java.awt.Color(255, 255, 255));
        btnSetKetua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-daftarsiswa-ketuakelas.png"))); // NOI18N
        btnSetKetua.setText("Set Ketua");
        btnSetKetua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSetKetuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSetKetuaMouseExited(evt);
            }
        });
        btnSetKetua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetKetuaActionPerformed(evt);
            }
        });

        btnEditKelas.setBackground(new java.awt.Color(34, 119, 237));
        btnEditKelas.setForeground(new java.awt.Color(255, 255, 255));
        btnEditKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-edit.png"))); // NOI18N
        btnEditKelas.setText("Edit Kelas");
        btnEditKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditKelasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditKelasMouseExited(evt);
            }
        });
        btnEditKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKelasActionPerformed(evt);
            }
        });

        btnKeluarkan.setBackground(new java.awt.Color(235, 29, 29));
        btnKeluarkan.setForeground(new java.awt.Color(255, 255, 255));
        btnKeluarkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-data-hapus.png"))); // NOI18N
        btnKeluarkan.setText("Keluarkan");
        btnKeluarkan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKeluarkanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKeluarkanMouseExited(evt);
            }
        });
        btnKeluarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoSiswaLayout = new javax.swing.GroupLayout(pnlInfoSiswa);
        pnlInfoSiswa.setLayout(pnlInfoSiswaLayout);
        pnlInfoSiswaLayout.setHorizontalGroup(
            pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleInfoSiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                .addGroup(pnlInfoSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(valAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlInfoSiswaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnSetKetua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKeluarkan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSiswaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lineInfoSiswa)))
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
                    .addComponent(btnSetKetua)
                    .addComponent(btnEditKelas)
                    .addComponent(btnKeluarkan))
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
                            .addComponent(pnlInfoKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(pnlInfoKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnSetKetuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetKetuaActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnSetKetuaActionPerformed

    private void btnSetKetuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetKetuaMouseEntered
        this.btnSetKetua.setIcon(Gambar.getIcon("ic-daftarsiswa-ketuakelas-entered.png"));
    }//GEN-LAST:event_btnSetKetuaMouseEntered

    private void btnSetKetuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetKetuaMouseExited
        this.btnSetKetua.setIcon(Gambar.getIcon("ic-daftarsiswa-ketuakelas.png"));
    }//GEN-LAST:event_btnSetKetuaMouseExited

    private void btnEditKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKelasActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnEditKelasActionPerformed

    private void btnEditKelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditKelasMouseEntered
        this.btnEditKelas.setIcon(Gambar.getIcon("ic-data-edit-entered.png"));
    }//GEN-LAST:event_btnEditKelasMouseEntered

    private void btnEditKelasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditKelasMouseExited
        this.btnEditKelas.setIcon(Gambar.getIcon("ic-data-edit.png"));
    }//GEN-LAST:event_btnEditKelasMouseExited

    private void btnKeluarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarkanActionPerformed
        Message.showInformation(this, "Sedang tidak tersedia untuk saat ini!");
    }//GEN-LAST:event_btnKeluarkanActionPerformed

    private void btnKeluarkanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarkanMouseEntered
        this.btnKeluarkan.setIcon(Gambar.getIcon("ic-data-hapus-entered.png"));
    }//GEN-LAST:event_btnKeluarkanMouseEntered

    private void btnKeluarkanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarkanMouseExited
        this.btnKeluarkan.setIcon(Gambar.getIcon("ic-data-hapus.png"));
    }//GEN-LAST:event_btnKeluarkanMouseExited

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
            java.util.logging.Logger.getLogger(DaftarSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DaftarSiswa dialog = new DaftarSiswa(new javax.swing.JFrame(), true, "x.rpl1");
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
    private javax.swing.JButton btnEditKelas;
    private javax.swing.JButton btnKeluarkan;
    private javax.swing.JButton btnSetKetua;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblInfoKelas;
    private javax.swing.JLabel lblInfoSiswa;
    private javax.swing.JLabel lblJenisKelamin;
    private javax.swing.JLabel lblKetuaKelas;
    private javax.swing.JLabel lblNamaKelas;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTtl;
    private javax.swing.JLabel lblWaliKelas;
    private javax.swing.JSeparator lineCenter;
    private javax.swing.JSeparator lineInfoSiswa;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlInfoKelas;
    private javax.swing.JPanel pnlInfoSiswa;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitleInfoKelas;
    private javax.swing.JPanel pnlTitleInfoSiswa;
    private javax.swing.JTable tabelData;
    private javax.swing.JLabel valAlamat;
    private javax.swing.JLabel valEmail;
    private javax.swing.JLabel valJenisKelamin;
    private javax.swing.JLabel valKetuaKelas;
    private javax.swing.JLabel valNamaKelas;
    private javax.swing.JLabel valNamaSiswa;
    private javax.swing.JLabel valNis;
    private javax.swing.JLabel valNoHp;
    private javax.swing.JLabel valTtl;
    private javax.swing.JLabel valWaliKelas;
    // End of variables declaration//GEN-END:variables
}
