package com.window.admin;

import com.data.app.Log;
import com.error.InValidUserDataException;
import com.manage.Message;
import com.manage.Text;
import com.manage.Waktu;
import com.media.Audio;
import com.media.Gambar;
import com.users.UserLevels;
import com.users.Users;
import com.window.petugas.PenampilFotoPetugas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-24
 */
public class TambahDataPetugas extends javax.swing.JDialog {

    private final Users.LevelPetugas petugas = Users.levelPetugas();
    private final Text txt = new Text();
    private final Frame parent;
    private final Waktu waktu = new Waktu();
    
    private boolean result;
    private final boolean modal;
    private int x, y;
    private String id, nama, gender, tempatLahir, tanggalLahir, alamat, nohp, email, password, level;
    private File foto;
    
    public TambahDataPetugas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        initComponents();
        this.setLocationRelativeTo(null);
        
        // mengatur ui pada button
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnReset.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTambah.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }
    
    private boolean isEmpty(){
        
        // mengecek apakah id petugas kosong atau tidak 
        if(this.inpID.getText().equals("")){
            Message.showWarning(this, "ID Petugas tidak boleh kosong!", true);
            return true;
        }
        // mengecek apakah nama petugas kosong atau tidak
        if(this.inpNama.getText().equals("")){
            Message.showWarning(this, "Nama Petugas tidak boleh kosong!", true);
            return true;
        }
        // mengecek apakah nomor hp kosong atau tidak
        if(this.inpNoHp.getText().equals("")){
            Message.showWarning(this, "Nomor HP tidak boleh kosong!", true);
            return true;
        }
        // mengecek apakah email kosong atau tidak
        if(this.inpEmail.getText().equals("")){
            Message.showWarning(this, "Email tidak boleh kosong!", true);
            return true;
        }
        // mengecek tempat lahir kosong atau tidak
        if(this.inpTempatLahir.getText().equals("")){
            Message.showWarning(this, "Tempat Lahir tidak boleh kosong!", true);
            return true;
        }
        // mengecek tanggal lahir kosong atau tidak
        if(this.inpTanggalLahir.getDate() == null){
            Message.showWarning(this, "Tanggal Lahir tidak boleh kosong!", true);
            return true;
        }
        // mengecek apakah alamat kosong atau tidak
        if(this.inpAlamat.getText().equals("")){
            Message.showWarning(this, "Alamat tidak boleh kosong!", true);
            return true;
        }
        // mengecek apakah password kosong atau tidak
        if(this.inpPassword.getText().equals("")){
            Message.showWarning(this, "Password tidak boleh kosong!");
            return true;
        }
        return false;
    }
    
    private void getInput(){
        // mendapatakn data-data petugas
        this.id = this.inpID.getText();
        this.nama = txt.toCapitalize(this.inpNama.getText());
        this.gender = txt.getGenderCode(this.inpGender.getSelectedItem().toString());
        this.nohp = this.inpNoHp.getText();
        this.email = this.inpEmail.getText().toLowerCase();
        this.tempatLahir = txt.toCapitalize(this.inpTempatLahir.getText());
        this.tanggalLahir = waktu.getDateFromInput(this.inpTanggalLahir.getCalendar());
        this.alamat = txt.toCapitalize(this.inpAlamat.getText());
        this.level = this.inpLevel.getSelectedItem().toString().toUpperCase();
        this.password = this.inpPassword.getText();
    }
    
    private void showInput(){
        System.out.println("ID : " + this.id);
        System.out.println("Nama : " + this.nama);
        System.out.println("Gender : " + this.gender);
        System.out.println("No HP : " + this.nohp);
        System.out.println("Email : " + this.email);
        System.out.println("Tempat Lahir : " + this.tempatLahir);
        System.out.println("Tanggal Lahir : " + this.tanggalLahir);
        System.out.println("Alamat : " + this.alamat);
        System.out.println("Level : " + this.level);
        System.out.println("Password : " + this.password);
        System.out.println("Foto : " + this.foto.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTop = new javax.swing.JLabel();
        lineTop = new javax.swing.JSeparator();
        lblFoto = new javax.swing.JLabel();
        pnlFoto = new javax.swing.JPanel();
        showFoto = new javax.swing.JLabel();
        lblEditFoto = new javax.swing.JLabel();
        lblHapusFoto = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        inpNama = new javax.swing.JTextField();
        lblData2 = new javax.swing.JLabel();
        inpNoHp = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        inpTempatLahir = new javax.swing.JTextField();
        lblTanggalLahir = new javax.swing.JLabel();
        inpAlamat = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblData8 = new javax.swing.JLabel();
        lblTempatLahir = new javax.swing.JLabel();
        inpGender = new javax.swing.JComboBox();
        inpTanggalLahir = new com.toedter.calendar.JDateChooser();
        inpLevel = new javax.swing.JComboBox();
        lineBottom = new javax.swing.JSeparator();
        btnTambah = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        inpID = new javax.swing.JTextField();
        inpPassword = new javax.swing.JPasswordField();
        lblShowPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Data Petugas");
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

        lblTop.setFont(new java.awt.Font("Dialog", 1, 23)); // NOI18N
        lblTop.setForeground(new java.awt.Color(222, 8, 8));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambah Data Petugas");

        lineTop.setBackground(new java.awt.Color(0, 0, 0));
        lineTop.setForeground(new java.awt.Color(0, 0, 0));

        lblFoto.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(0, 0, 0));
        lblFoto.setText("Foto Petugas");
        lblFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnlFoto.setBackground(new java.awt.Color(255, 255, 255));
        pnlFoto.setForeground(new java.awt.Color(0, 0, 0));

        showFoto.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        showFoto.setForeground(new java.awt.Color(0, 0, 0));
        showFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showFoto.setText("3 x 3");
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
            .addComponent(showFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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

        lblID.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblID.setForeground(new java.awt.Color(0, 0, 0));
        lblID.setText("ID Petugas");

        lblNama.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNama.setForeground(new java.awt.Color(0, 0, 0));
        lblNama.setText("Nama Petugas");

        inpNama.setBackground(new java.awt.Color(255, 255, 255));
        inpNama.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNama.setForeground(new java.awt.Color(0, 0, 0));
        inpNama.setCaretColor(new java.awt.Color(10, 79, 223));

        lblData2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblData2.setForeground(new java.awt.Color(0, 0, 0));
        lblData2.setText("Jenis Kelamin");

        inpNoHp.setBackground(new java.awt.Color(255, 255, 255));
        inpNoHp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpNoHp.setForeground(new java.awt.Color(0, 0, 0));
        inpNoHp.setCaretColor(new java.awt.Color(10, 79, 223));

        lblNoHp.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblNoHp.setForeground(new java.awt.Color(0, 0, 0));
        lblNoHp.setText("Nomor HP");

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        inpEmail.setBackground(new java.awt.Color(255, 255, 255));
        inpEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpEmail.setForeground(new java.awt.Color(0, 0, 0));
        inpEmail.setCaretColor(new java.awt.Color(10, 79, 223));

        inpTempatLahir.setBackground(new java.awt.Color(255, 255, 255));
        inpTempatLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpTempatLahir.setForeground(new java.awt.Color(0, 0, 0));
        inpTempatLahir.setCaretColor(new java.awt.Color(10, 79, 223));

        lblTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));
        lblTanggalLahir.setText("Tanggal Lahir");

        inpAlamat.setBackground(new java.awt.Color(255, 255, 255));
        inpAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpAlamat.setForeground(new java.awt.Color(0, 0, 0));
        inpAlamat.setCaretColor(new java.awt.Color(10, 79, 223));

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(0, 0, 0));
        lblAlamat.setText("Alamat");

        lblLevel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblLevel.setForeground(new java.awt.Color(0, 0, 0));
        lblLevel.setText("Level");

        lblData8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblData8.setForeground(new java.awt.Color(0, 0, 0));
        lblData8.setText("Password");

        lblTempatLahir.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTempatLahir.setForeground(new java.awt.Color(0, 0, 0));
        lblTempatLahir.setText("Tempat Lahir");

        inpGender.setBackground(new java.awt.Color(202, 217, 242));
        inpGender.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpGender.setForeground(new java.awt.Color(0, 0, 0));
        inpGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));

        inpTanggalLahir.setBackground(new java.awt.Color(255, 255, 255));
        inpTanggalLahir.setForeground(new java.awt.Color(0, 0, 0));
        inpTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        inpLevel.setBackground(new java.awt.Color(202, 217, 242));
        inpLevel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpLevel.setForeground(new java.awt.Color(0, 0, 0));
        inpLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Petugas", "Guru" }));

        lineBottom.setBackground(new java.awt.Color(0, 0, 0));
        lineBottom.setForeground(new java.awt.Color(0, 0, 0));

        btnTambah.setBackground(new java.awt.Color(34, 119, 237));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-manipulasi-save.png"))); // NOI18N
        btnTambah.setText("Simpan");
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

        inpID.setBackground(new java.awt.Color(255, 255, 255));
        inpID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpID.setForeground(new java.awt.Color(0, 0, 0));
        inpID.setCaretColor(new java.awt.Color(10, 79, 223));

        inpPassword.setBackground(new java.awt.Color(255, 255, 255));
        inpPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inpPassword.setForeground(new java.awt.Color(0, 0, 0));
        inpPassword.setCaretColor(new java.awt.Color(10, 79, 223));

        lblShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/icons/ic-login-eye-close.png"))); // NOI18N
        lblShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineBottom)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblEditFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblHapusFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblData2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inpID, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inpNama, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inpNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(57, 57, 57)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblData8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inpPassword)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lineTop))
                        .addGap(39, 39, 39))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineTop, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditFoto)
                            .addComponent(lblHapusFoto))))
                .addGap(29, 29, 29)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNama, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpGender, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inpLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblShowPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28)
                .addComponent(lineBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambah)
                    .addComponent(btnReset)
                    .addComponent(btnBatal))
                .addContainerGap(19, Short.MAX_VALUE))
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
            new PenampilFotoPetugas(this.parent, this.modal, Gambar.scaleImage(this.foto, 359, 323)).setVisible(true);
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showFotoMouseClicked

    private void showFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFotoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showFotoMouseEntered

    private void showFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFotoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showFotoMouseExited

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

    private void lblHapusFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseClicked
        // menghapus foto
        this.foto = null;
        this.showFoto.setIcon(null);
        this.showFoto.setText("3 x 3");
    }//GEN-LAST:event_lblHapusFotoMouseClicked

    private void lblHapusFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Hapus Foto</p></html>");
    }//GEN-LAST:event_lblHapusFotoMouseEntered

    private void lblHapusFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Hapus Foto</p></html>");
    }//GEN-LAST:event_lblHapusFotoMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Log.addLog("Membuka Window TambahDataPetugas");
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.petugas.closeConnection();
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

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        this.btnTambah.setBackground(this.btnTambah.getBackground().darker());
        this.btnTambah.setIcon(Gambar.getIcon("ic-manipulasi-save-entered.png"));
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        this.btnTambah.setBackground(new Color(34,119,237));
        this.btnTambah.setIcon(Gambar.getIcon("ic-manipulasi-save.png"));
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        try {
            if(!this.isEmpty()){
                // mendapatkan data yang diinputkan user
                this.getInput();
                
                // mengecek id petugas apakah sudah digunakan atau belum
                if(!petugas.isExistUser(this.id)){
                    // menambahkan data petugas
                    this.result = petugas.addPetugas(
                            id, nohp, email, foto, password, UserLevels.ADMIN, nama, gender, tempatLahir, tanggalLahir, alamat
                    );

                    // mengecek apakah data petugas berhasil ditambahkan atau tidak
                    if(result){
                        Message.showInformation(this, "Petugas berhasil ditambahkan!", true);
                    }                    
                }else{
                    Message.showWarning(this, "'" + this.id + "' ID Petugas tersebut sudah digunakan!", true);
                }
            }
        } catch (FileNotFoundException | SQLException | InValidUserDataException ex) {
            Message.showException(this, "Terjadi kesalahan : " + ex.getMessage(), ex, true);
        }
    }//GEN-LAST:event_btnTambahActionPerformed

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
        new TambahDataPetugas(this.parent, this.modal).setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

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
            java.util.logging.Logger.getLogger(TambahDataPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TambahDataPetugas dialog = new TambahDataPetugas(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField inpID;
    private javax.swing.JComboBox inpLevel;
    private javax.swing.JTextField inpNama;
    private javax.swing.JTextField inpNoHp;
    private javax.swing.JPasswordField inpPassword;
    private com.toedter.calendar.JDateChooser inpTanggalLahir;
    private javax.swing.JTextField inpTempatLahir;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblData2;
    private javax.swing.JLabel lblData8;
    private javax.swing.JLabel lblEditFoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblHapusFoto;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblShowPassword;
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
