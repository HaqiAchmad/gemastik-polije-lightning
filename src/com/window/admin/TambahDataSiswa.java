package com.window.admin;

import com.data.app.Kelas;
import com.data.app.Log;
import com.data.app.SPP;
import com.manage.Message;
import com.manage.Text;
import com.media.Audio;
import com.media.Gambar;
import com.users.Users;
import com.window.petugas.PenampilFotoSiswa;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-24
 */
public class TambahDataSiswa extends javax.swing.JDialog {

    private final Users.LevelSiswa siswa = Users.levelSiswa();
    private final Kelas kls = new Kelas();
    private final SPP spp = new SPP();
    
    private final Text txt = new Text();
    private final Frame parent;
    
    private int x, y;
    private final boolean modal;
    private String namaSiswa, gender, tempatLahir, tanggalLahir, alamat, 
                   kelas, nohp, email, namaWali, idSpp, password;
    private File foto;
    
    public TambahDataSiswa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
//        this.setSize(955, 590);
        this.setResizable(false);
        
        // mendapatkan semua id kelas dan spp yang ada didalam database
        this.inpKelas.setModel(new javax.swing.DefaultComboBoxModel(this.kls.getIDs()));
        this.inpSpp.setModel(new javax.swing.DefaultComboBoxModel(this.spp.getIDs()));
        
        // mengatur ui pada button
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnReset.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTambah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private void closeConnection(){
        this.siswa.closeConnection();
        this.kls.closeConnection();
        this.spp.closeConnection();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        lblNis = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        lblEditFoto = new javax.swing.JLabel();
        lineBottom = new javax.swing.JSeparator();
        pnlFoto = new javax.swing.JPanel();
        showFoto = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        inpNis = new javax.swing.JTextField();
        lblNamaSiswa = new javax.swing.JLabel();
        inpNamaSiswa = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        inpNoHp = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        inpAlamat = new javax.swing.JTextField();
        lblTempatLahir = new javax.swing.JLabel();
        inpTempatLahir = new javax.swing.JTextField();
        lblTanggalLahir = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        lblSpp = new javax.swing.JLabel();
        lblNamaWali = new javax.swing.JLabel();
        inpNamaWali = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        inpGender = new javax.swing.JComboBox();
        inpTanggalLahir = new com.toedter.calendar.JDateChooser();
        inpKelas = new javax.swing.JComboBox();
        inpSpp = new javax.swing.JComboBox();
        lblHapusFoto = new javax.swing.JLabel();
        lblShowPassword = new javax.swing.JLabel();
        inpPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Data Siswa");
        setBackground(new java.awt.Color(0, 0, 0));
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
        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMainMouseDragged(evt);
            }
        });
        pnlMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMainMousePressed(evt);
            }
        });

        lblTop.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N
        lblTop.setForeground(new java.awt.Color(222, 8, 8));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambah Data Siswa");

        lineTop.setBackground(new java.awt.Color(0, 36, 252));
        lineTop.setForeground(new java.awt.Color(0, 36, 252));

        lblNis.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNis.setForeground(new java.awt.Color(0, 0, 0));
        lblNis.setText("NIS");

        lblFoto.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblFoto.setText("Foto Siswa");
        lblFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblEditFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblEditFoto.setText("Edit Foto");
        lblEditFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseExited(evt);
            }
        });

        lineBottom.setBackground(new java.awt.Color(0, 36, 252));
        lineBottom.setForeground(new java.awt.Color(0, 36, 252));

        pnlFoto.setBackground(new java.awt.Color(255, 255, 255));

        showFoto.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        showFoto.setForeground(new java.awt.Color(0, 0, 0));
        showFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showFoto.setText("3 x 4");
        showFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        showFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showFotoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlFotoLayout = new javax.swing.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-cancel.png"))); // NOI18N
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

        btnReset.setBackground(new java.awt.Color(17, 159, 77));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(34, 119, 237));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-save.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTambahMouseExited(evt);
            }
        });
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        inpNis.setBackground(new java.awt.Color(255, 255, 255));
        inpNis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNis.setForeground(new java.awt.Color(0, 0, 0));
        inpNis.setCaretColor(new java.awt.Color(10, 79, 223));

        lblNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaSiswa.setText("Nama Siswa");

        inpNamaSiswa.setBackground(new java.awt.Color(255, 255, 255));
        inpNamaSiswa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNamaSiswa.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaSiswa.setCaretColor(new java.awt.Color(10, 79, 223));

        lblGender.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblGender.setForeground(new java.awt.Color(0, 0, 0));
        lblGender.setText("Jenis Kelamin");

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        inpEmail.setBackground(new java.awt.Color(255, 255, 255));
        inpEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpEmail.setForeground(new java.awt.Color(0, 0, 0));
        inpEmail.setCaretColor(new java.awt.Color(10, 79, 223));

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        inpNoHp.setBackground(new java.awt.Color(255, 255, 255));
        inpNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNoHp.setForeground(new java.awt.Color(0, 0, 0));
        inpNoHp.setCaretColor(new java.awt.Color(10, 79, 223));

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        inpAlamat.setBackground(new java.awt.Color(255, 255, 255));
        inpAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpAlamat.setForeground(new java.awt.Color(0, 0, 0));
        inpAlamat.setCaretColor(new java.awt.Color(10, 79, 223));

        lblTempatLahir.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTempatLahir.setForeground(new java.awt.Color(0, 0, 0));
        lblTempatLahir.setText("Tempat Lahir");

        inpTempatLahir.setBackground(new java.awt.Color(255, 255, 255));
        inpTempatLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpTempatLahir.setForeground(new java.awt.Color(0, 0, 0));
        inpTempatLahir.setCaretColor(new java.awt.Color(10, 79, 223));

        lblTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggalLahir.setText("Tanggal Lahir");

        lblKelas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(0, 0, 0));
        lblKelas.setText("ID Kelas");

        lblSpp.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblSpp.setForeground(new java.awt.Color(0, 0, 0));
        lblSpp.setText("ID SPP");

        lblNamaWali.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNamaWali.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaWali.setText("Nama Wali");

        inpNamaWali.setBackground(new java.awt.Color(255, 255, 255));
        inpNamaWali.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNamaWali.setForeground(new java.awt.Color(0, 0, 0));
        inpNamaWali.setCaretColor(new java.awt.Color(10, 79, 223));

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setText("Password");

        inpGender.setBackground(new java.awt.Color(202, 217, 242));
        inpGender.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpGender.setForeground(new java.awt.Color(0, 0, 0));
        inpGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));

        inpTanggalLahir.setBackground(new java.awt.Color(255, 255, 255));
        inpTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));
        inpTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        inpKelas.setBackground(new java.awt.Color(202, 217, 242));
        inpKelas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpKelas.setForeground(new java.awt.Color(0, 0, 0));
        inpKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "x.rpl1", "x.rpl2", "xi.rpl1", "xi.rpl2", "xii.rpl1", "xii.rpl2" }));

        inpSpp.setBackground(new java.awt.Color(202, 217, 242));
        inpSpp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpSpp.setForeground(new java.awt.Color(0, 0, 0));
        inpSpp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17", "18", "19", "20" }));

        lblHapusFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHapusFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblHapusFoto.setText("Hapus Foto");
        lblHapusFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseExited(evt);
            }
        });

        lblShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-eye-close.png"))); // NOI18N
        lblShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseExited(evt);
            }
        });

        inpPassword.setBackground(new java.awt.Color(255, 255, 255));
        inpPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpPassword.setForeground(new java.awt.Color(0, 0, 0));
        inpPassword.setCaretColor(new java.awt.Color(10, 79, 223));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lineTop)
                    .addComponent(lineBottom)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblEditFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHapusFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpNis, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNamaWali, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpNamaWali, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpSpp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditFoto)
                            .addComponent(lblHapusFoto)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpGender, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpSpp, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNamaWali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNamaWali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblShowPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(inpPassword))))
                .addGap(32, 32, 32)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambah)
                    .addComponent(btnReset)
                    .addComponent(btnBatal))
                .addGap(20, 20, 20))
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

    private void lblEditFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseClicked
        Audio.play(Audio.SOUND_INFO);
        // mendapatkan input gambar dari user
        this.foto = Gambar.chooseImage();
        
        // mengecek user memilih sebuah gambar atau tidak
        if(this.foto != null){
            this.showFoto.setText("");
            // menampilkan gambar yang dipilih user
            this.showFoto.setIcon(Gambar.scaleImage(this.foto, 76, 98));
        }
    }//GEN-LAST:event_lblEditFotoMouseClicked

    private void lblEditFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblEditFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Edit Foto</p></html>");
    }//GEN-LAST:event_lblEditFotoMouseEntered

    private void lblEditFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblEditFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Edit Foto</p></html>");
    }//GEN-LAST:event_lblEditFotoMouseExited

    private void showFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFotoMouseClicked
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // jika user belum memilih foto
        if(this.foto == null){
            Message.showWarning(this, "Tidak ada foto yang dipilih!");
        }
        // jika user sudah memilih foto
        else{
            // menampilkan foto yang dipilh oleh user
            Audio.play(Audio.SOUND_INFO);
            new PenampilFotoSiswa(this.parent, this.modal, Gambar.scaleImage(this.foto, 339, 471)).setVisible(true);
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showFotoMouseClicked

    private void showFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFotoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showFotoMouseEntered

    private void showFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFotoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showFotoMouseExited

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setBackground(this.btnBatal.getBackground().darker());
        this.btnBatal.setIcon(Gambar.getIcon("ic-manipulasi-cancel-entered.png"));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setBackground(new Color(220,41,41));
        this.btnBatal.setIcon(Gambar.getIcon("ic-manipulasi-cancel.png"));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        this.btnReset.setBackground(this.btnReset.getBackground().darker());
        this.btnReset.setIcon(Gambar.getIcon("ic-manipulasi-reset-entered.png"));
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        this.btnReset.setBackground(new Color(17,159,77));
        this.btnReset.setIcon(Gambar.getIcon("ic-manipulasi-reset.png"));
    }//GEN-LAST:event_btnResetMouseExited

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.dispose();
        Audio.play(Audio.SOUND_INFO);
        new TambahDataSiswa(this.parent, this.modal).setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        this.btnTambah.setBackground(this.btnTambah.getBackground().darker());
        this.btnTambah.setIcon(Gambar.getIcon("ic-manipulasi-save-entered.png"));
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        this.btnTambah.setBackground(new Color(34,119,237));
        this.btnTambah.setIcon(Gambar.getIcon("ic-manipulasi-save.png"));
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        Message.showInformation(this, "Saat ini belum tersedia!");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Log.addLog("Membuka Window TambahDataSiswa");
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
            yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void lblHapusFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseClicked
        // menghapus foto
        this.foto = null;
        this.foto = null;
        this.showFoto.setIcon(null);
        this.showFoto.setText("3 x 4");
    }//GEN-LAST:event_lblHapusFotoMouseClicked

    private void lblHapusFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Hapus Foto</p></html>");
    }//GEN-LAST:event_lblHapusFotoMouseEntered

    private void lblHapusFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Hapus Foto</p></html>");
    }//GEN-LAST:event_lblHapusFotoMouseExited

    private void lblShowPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPasswordMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblShowPassword.setIcon(Gambar.getIcon("ic-login-eye-open.png"));
        this.inpPassword.setEchoChar((char)0);
    }//GEN-LAST:event_lblShowPasswordMouseEntered

    private void lblShowPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPasswordMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblShowPassword.setIcon(Gambar.getIcon("ic-login-eye-close.png"));
        this.inpPassword.setEchoChar('•');
    }//GEN-LAST:event_lblShowPasswordMouseExited

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
            java.util.logging.Logger.getLogger(TambahDataSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TambahDataSiswa dialog = new TambahDataSiswa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JTextField inpAlamat;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JComboBox inpGender;
    private javax.swing.JComboBox inpKelas;
    private javax.swing.JTextField inpNamaSiswa;
    private javax.swing.JTextField inpNamaWali;
    private javax.swing.JTextField inpNis;
    private javax.swing.JTextField inpNoHp;
    private javax.swing.JPasswordField inpPassword;
    private javax.swing.JComboBox inpSpp;
    private com.toedter.calendar.JDateChooser inpTanggalLahir;
    private javax.swing.JTextField inpTempatLahir;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblEditFoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHapusFoto;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNamaSiswa;
    private javax.swing.JLabel lblNamaWali;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblShowPassword;
    private javax.swing.JLabel lblSpp;
    private javax.swing.JLabel lblTanggalLahir;
    private javax.swing.JLabel lblTempatLahir;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator lineBottom;
    private javax.swing.JSeparator lineTop;
    private javax.swing.JPanel pnlFoto;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel showFoto;
    // End of variables declaration//GEN-END:variables
}
