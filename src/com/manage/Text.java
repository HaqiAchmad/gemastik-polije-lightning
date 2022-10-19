package com.manage;

import java.util.StringTokenizer;

/**
 * Class ini akan berfokus untuk menangani segala hal berhubungan dengan text.
 * Method-method yang ada didalam class ini dapat digunakan untuk memanipulasi/
 * mengelola sebuah text yang diinputkan oleh user. Pada class ini kita juga dapat 
 * merubah sebuah gaya dari text ke gaya tertentu sesuai yang anda inginkan.
 *
 * @author Achmad Baihaqi
 * @since 2021-06-09
 */
public class Text {
    
    /**
     * Karakter yang mendukung number/angka.
     */
    private final char[] numbers = new char[]{
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    
    /**
     * Karakter yang mendukung huruf.
     */
    private final char[] characters = new char[]{
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    /**
     * Karakter yang mendukung simbol.
     */
    private final char[] symbols = new char[]{
        '`', '~', '!', '@', '#', '$', '%', '^', '&', '*',
        '(', ')', '-', '_', '=', '+', '\\', '|', '[', '{',
        ']', '}', ';', ':', '\'', '"', ',', '<', '.', '>',
        '/', '?'
    };
    
    /**
     * Digunakan untuk mengecek apakah sebuah string mengandung angka atau tidak didalamnya.
     * Method akan mengecek apakah string mengandung angka atau tidak dengan menggunakan 
     * bantuan dari method {@code contains()}. Jika output dari method tersebut adalah 
     * <strong>True</strong> maka string dianggap mengandung angka.
     * 
     * @param str string yang akan dicek.
     * @return  <strong>True</strong> jika string mengandung angka. <br>
     *          <strong>False</strong> jika string tidak mengandung angka.
     */
    public boolean containsNumbers(String str){
        return this.contains(numbers, str);
    }
    
    /**
     * Digunakan untuk mengecek apakah sebuah string mengandung karakter atau tidak didalamnya.
     * Method akan mengecek apakah string mengandung karakter atau tidak dengan menggunakan 
     * bantuan dari method {@code contains()}. Jika output dari method tersebut adalah 
     * <strong>True</strong> maka string dianggap mengandung karakter.
     * 
     * @param str string yang akan dicek.
     * @return  <strong>True</strong> jika string mengandung karakter. <br>
     *          <strong>False</strong> jika string tidak mengandung karakter.
     */
    public boolean containsCharacters(String str){
        return this.contains(characters, str.toLowerCase());
    }

    /**
     * Digunakan untuk mengecek apakah sebuah string mengandung symbol atau tidak didalamnya.
     * Method akan mengecek apakah string mengandung symbol atau tidak dengan menggunakan 
     * bantuan dari method {@code contains()}. Jika output dari method tersebut adalah 
     * <strong>True</strong> maka string dianggap mengandung symbol.
     * 
     * @param str string yang akan dicek.
     * @return  <strong>True</strong> jika string mengandung symbol. <br>
     *          <strong>False</strong> jika string tidak mengandung symbol.
     */
    public boolean containsSymbols(String str){
        return this.contains(symbols, str);
    }
    
    /**
     * Digunakan untuk mengecek apakah karakter dari string mengandung sebuah karakter yang ada 
     * didalam char array atau tidak. Method ini akan berguna saat ada sebuah kondisi yang 
     * diperlukan untuk memblokir atau hanya mengizinkan sebuah karakter tertentu. Contohnya 
     * jika kita memasukan sebuah nama maka kita tidak boleh memasukan sebuah angka didalam nama tersebut. 
     * Nah fungsi dari method ini adalah memberitahu user bahwa didalam nama tersebut mengandung 
     * angka/karakter yang diblokir.
     * 
     * @param equals karakter yang akan dicek ada atau tidaknya didalam string.
     * @param str string yang akan dicek.
     * @return <strong>True</strong> jika string mengandung karakter dari array. <br>
     *         <strong>False</strong> Otherwise.
     */
    public boolean contains(char[] equals, String str){
        // membaca satu-persatu karakter yang ada didalam string
        for(int i = 0; i < str.length(); i++){
            // membaca satu-persatu karakter yang ada didalam array
            for(char c : equals){
                // mengecek karakter yang sedang dibaca apakah ada didalam array atau tidak. 
                if(str.charAt(i) == c){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Untuk mengecek apakah nilai dari sebuah string diawali dengan komentar atau tidak. 
     * Pengecekan sangat perlu dilakuan untuk menghindari terjadinya error pada aplikasi.
     * Method akan mengecek apakah sebuah string diawali dengan komentar dengan menggunakan 
     * method {@code startsWith()} yang ada didalam class {@code String}.
     * <br><br>
     * <b>Adapun kriteria komentar menurut method ini adalah.</b>
     * <ul>
     *  <li>String diawali dengan simbol '//'</li>
     *  <li>String diawali dengan simbol '-'</li>
     *  <li>String diawali dengan simbol '#'</li>
     *  <li>Nilai dari string kosong/empty</li>
     *  <li>Nilai dari string blank</li>
     * </ul>
     * <br>
     * <b>Note : </b> Jika string memenuhi salah satu dari kriteria diatas maka string akan dianggap sebagai komentar.
     * 
     * @param str string yang akan dicek.
     * @return <strong>True</strong> jika string diawali dengan sebuah komentar. <br>
     *         <strong>False</strong> Otherwise.
     */
    public boolean isComments(String str){
        return str.startsWith("//") || str.startsWith("-") || str.startsWith("#") || this.isEmpty(str) || this.isBlank(str);
    }
    
    /**
     * Digunakan untuk mengecek apakah nilai dari sebuah string kosong atau tidak.
     * 
     * @param str string yang akan dicek.
     * @return <strong>True</strong> jika string kosong/empty. <br>
     *         <strong>False</strong> Otherwise.
     */
    public boolean isEmpty(String str){
        return str.isEmpty();
    }
 
    /**
     * Digunakan untuk mengecek apakah nilai dari sebuah string blank atau tidak. 
     * Blank string adalah sebuah kondisi jika nilai dari string hanya terdapat white case (space).
     * Method akan melakukan perulangan untuk membaca satu-persatu karakter yang ada didalam string.
     * Selama proses perulangan berlangsung method akan menhitung mana karakter yang white case dan 
     * mana karakter yang non white case. Setelah perulangan selesai maka akan dilakukan pengecekan 
     * jika jumlah karakter white case lebih banyak dari jumlah karakter non white case maka string akan 
     * dianggap blank.
     * 
     * @param str string yang akan dicek.
     * @return <strong>True</strong> jika string blank. <br>
     *         <strong>False</strong> Otherwise.
     */
    public boolean isBlank(String str){
        int spaces = 0, chars = 0;
        // membaca satu-persatu karakter
        for(int i = 0; i < str.length(); i++){
            // jika karakter yang sedang dibaca merupakah white case
            if(str.charAt(i) == ' '){
                spaces++; 
            }else{
                chars++;
            }
        }
        // jika white case jumlahnya lebih banyak dari non white case maka string dianggap blank.
        return spaces > chars;
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah sebuah string memiliki nilai number atau tidak.
     * Method ini akan berguna saat aplikasi menerima input number dari text field. Seperti yang kita
     * ketahui bahwa output dari text field adalah sebuah String. Nah method ini berguna untuk mengecek 
     * apakah String tersebut memiliki nilai number atau tidak.
     * <br><br>
     * Method akan memanggil method {@code parseInt()} yang ada didalam class {@code String}. Jika 
     * saat memanggil method tersebut tidak terjadi error maka string memiliki nilai sebuah number.
     * 
     * @param str string yang akan dicek.
     * @return <strong>True</strong> jika string nilainya adalah sebuah number.
     *         <strong>False</strong> Otherwise.
     */
    public boolean isNumber(String str){
        // jika input null maka akan mengembalikan nilai false
        if(str == null){
            return false;
        }
        try{
            Long.parseLong(this.removeDelim(str));
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    
    /**
     * Digunakan untuk menanbahkan delimetri didalam number yang diinputkan oleh user.
     * Method akan menambahkan delimetri didalam number dengan menggunakan string format.
     * 
     * @param num number yang akan ditambahkan delimetri.
     * @return number dengan delimetri.
     */
    public String addDelim(long num){
        return String.format("%,d", num);
    }
    
     /**
     * Digunakan untuk menanbahkan delimetri didalam number yang diinputkan oleh user.
     * Method akan menambahkan delimetri didalam number dengan menggunakan string format.
     * 
     * @param num number yang akan ditambahkan delimetri.
     * @return number dengan delimetri.
     */
    public String addDelim(double num){
        return String.format("%,.2f", num);
    }
    
    /**
     * Method ini digunakan untuk membuang karakter yang ada didalam string jika string tersebut 
     * diawali dengan white case. Pertama-tama method akan mengecek apakah string tersebut diawali 
     * dengan white case atau tidak dengan menggunakan method {@code startsWith()} yang ada didalam 
     * class {@code String}. Jika output dari method tersebut adalah <strong>True</strong> maka 
     * string diawali dengan white case. Selanjutnya method akan membuang white case tersebut dengan 
     * menggunakan method {@code substring()} yang ada didalam class {@code String}. Hal ini akan terus
     * dilakukan sampai string tidak diawali dengan white case lagi.
     * 
     * @param str string yang akan dibuag white case-nya.
     * @return Mengembalikan nilai dari string tanpa white case didepanya.
     */
    public String removeWhiteCase(String str){
        // mengecek apakah string diawali dengan white case atau tidak
        if(str.startsWith(" ") || str.startsWith("\t")){
            // membuang white case pada string
            removeWhiteCase(str.substring(1));
        }
        // mengembalikan nilai dari string tanpa white case
        return str;
    }
    
    /**
     * Method ini digunakan untuk membuang semua delimetri yang ada didalan string yang memiliki nilai 
     * number. Method akan membuang semua delimetri yang ada didalam string dengan menggunakan method 
     * {@code replaceAll()} yang ada didalam class {@code String}.
     * 
     * @param num string yang akan dibuang dilimetrinya.
     * @return Mengembalikan nilai dari string tanpa delimetri.
     */
    public String removeDelim(String num){
        return num.replaceAll("_", "").replaceAll(",", "").replaceAll("\\.", "");
    }
    
    /**
     * Method ini digunakan untuk mendapatkan kode gender dari user berdasarkan nama gender yang diinputkan. 
     * Hanya terdapat 2 nama gender yang ada di aplikasi ini yaitu Laki-Laki dan Perempuan. Laki-Laki bearti L 
     * dan Perempuan bearti P. Jika input yang dimasukan bukan dari kedua nama gender tersebut maka method akan mengembalikan 
     * nilai Tidak Dicantumkan.
     * 
     * @param genderName nama dari kode geder.
     * @return kode gender.
     */
    public String getGenderCode(String genderName){
        if(genderName.equalsIgnoreCase("Laki-Laki")){
            return "L";
        }else if(genderName.equalsIgnoreCase("Perempuan")){
            return "P";
        }else{
            return "N";
        }
    }
    
    /**
     * Method ini digunakan untuk mendapatkan nama gender dari user berdasarkan kode gender yang diinputkan. 
     * Hanya terdapat 2 kode gender yang ada di aplikasi ini yaitu L dan P. L bearti Laki-Laki dan P bearti 
     * Perempuan. Jika input yang dimasukan bukan dari kedua kode tersebut maka method akan mengembalikan 
     * nilai Tidak Dicantumkan.
     * 
     * @param gender kode geder.
     * @return nama gender.
     */
    public String getGenderName(String gender){
        if(gender.equalsIgnoreCase("L")){
            return "Laki-Laki";
        }else if(gender.equalsIgnoreCase("P")){
            return "Perempuan";
        }else{
            return "Tidak Dicantumkan";
        }
    }
    
    /**
     * Method ini digunakan untuk merubah sebuah gaya dari text ke Capitalize.
     * Method ini berguna saat untuk merapihkan penulisan data nama siswa yang 
     * akan ditransfer ke dalam <code>Database</code>.
     * <br><br>
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "achmad baihaqi." <br>
     * <strong>Sesudah</strong> : "Achmad Baihaqi."
     * 
     * @param text Text yang akan diubah ke capitalize.
     * @return Text yang gaya textnya diubah ke capitalize
     */
    public String toCapitalize(String text){
        String res = text.toLowerCase(), buffer;
        // merubah huruf pertama dari text menjadi kapital
        res = "" + res.charAt(0);
        res = res.toUpperCase() + (text.substring(1).toLowerCase());
        
        // melakulan looping dari index pertama sampai index terakhir dari text 
        for(int i = 0; i < res.length(); i++){
            // mengecek apakah masih terdapat index atau tidak yang ada didalam text
            if(i < text.length()-1 && i > 1){
                // mengecek apakah index dari text memiliki karakter ' ' atau tidak
                if(res.charAt(i) == ' '){
                    // merubah karakter yang ada disetelah index yang memiliki karakter ' ' ke huruf kapital
                    buffer = res.substring(0, i+1);
                    buffer = buffer + ("" + res.charAt(i+1) + "").toUpperCase();
                    buffer = buffer + res.substring(i+2);
                    res = buffer;
                }
            }
        }
        return res;
    }
    
    /**
     * Digunakan untuk merubah sebuah gaya dari tanggal menjadi gaya date case.
     * Petama-tama method akan mengecek apakah tanggal yang dinputkan valid atau tidak 
     * dengan menggunakam method {@code isTanggalLahir} yang ada didalam class {@code Validation}.
     * Selanjutnya method akan mengambil data-data dari tanggal seperti hari, bulan dan tahun.
     * Lalu method akan mengubah data-data hari, bulan dan tahun tersebut kedalam data case dengan 
     * menggunakan string format.
     * 
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "2003-08-04" <br>
     * <strong>Sesudah</strong> : "04 Agustus 2003."
     * 
     * @param yymmdd tanggal yang akan dirubah kedalam date case.
     * @return tanggal dengan gaya date case.
     */
    public String toDateCase(String yymmdd){
        
        int tahun, bulan, hari;
        // mengecek apakah tanggal valid atau tidak
        if(Validation.isTanggal(yymmdd)){
            // mendapatkan data dari tanggal 
            StringTokenizer token = new StringTokenizer(yymmdd, "-");
            tahun = Integer.parseInt(token.nextToken());
            bulan = Integer.parseInt(token.nextToken());
            hari =  Integer.parseInt(token.nextToken());
            // mengubah menjadi data case
            return String.format("%02d %s %02d", hari, new Waktu().getNamaBulan(bulan-1), tahun);            
        }
        return null;
    }
    
    /**
     * Digunakan untuk merubah sebuah gaya dari number menjadi gaya money case.
     * Pertama-tama method akan mengecek apakah input yang dimasukan oleh user adalah sebuah 
     * number atau tidak dengan menggunakan method {@code isNumber}. Jika output dari method 
     * tersebut adalah <strong>True</strong> maka method akan merubah gaya dari number menjadi 
     * money case dengan menggunakan string format.
     * 
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "1000000" <br>
     * <strong>Sesudah</strong> : "Rp. 1.000.000,00"
     * 
     * @param money number yang gayanya akan diubah ke money case.
     * @return number dengan gaya money case.
     */
    public String toMoneyCase(String money){
        // mengecek apakah input adalah sebuah number atau tidak
        if(this.isNumber(money)){
            // mengubah menjadi money case
            return String.format("Rp. %,d.00", Long.parseLong(money));
        }
        // jika input bukan sebuah number maka akan mengembalikan nilai 'Rp. -1.00'
        return "Rp. -1.00";
    }
    
    /**
     * Digunakan untuk merubah gaya dari data ttl kedalam gaya date of birth case.
     * Pertama-tama method akan mengecek apakah tempat dan tanggal lahir valid atau tidak.
     * Jika valid maka method akan mengubah gaya dari data ttl dengan menggunakan 
     * string format.
     * 
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "Jombang 2003-08-04" <br>
     * <strong>Sesudah</strong> : "Jombang, 04 Agustus 2003"
     * 
     * @param tempat tempat tanggal lahir.
     * @param tgl tanggal lahir.
     * @return data ttl dengan gaya date for birth case.
     */
    public String toDateOfBirthCase(String tempat, String tgl){
        // mengecek apakah tanggal dan nama tempat valid atau tidak
        if(Validation.isNamaTempat(tempat) && Validation.isTanggal(tgl)){
            // mengubah menjadi ttl case
            return String.format("%s, %s", this.toCapitalize(tempat), this.toDateCase(tgl));
        }
        return null;
    }
    
    /**
     * Digunakan untuk mengubah gaya dari nomor hp kedalam gaya telephone case.
     * Pertama-tama method akan membuang semua delimetri '-' yang ada didalam nohp.
     * Selanjutnya method akan mengecek apakah nohp yang diinputkan valid atau tidak 
     * dengan menggunakan method {@code isNoHp()} yang ada didalam class {@code Validation}.
     * Jika nohp valid maka method akan mengubah nomor hp kedalam gaya telephone case dengan 
     * menggunakan string format.
     * 
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "0856-5586-4624" <br>
     * <strong>Sesudah</strong> : "+62 856-5586-4624"
     * 
     * @param nohp nomor hp yang akan diubah kedalam telephone case.
     * @return nohp dengan gaya telephone case.
     */
    public String toTelephoneCase(String nohp){
        // menhapus delim '-'
        nohp = nohp.replaceAll("-", "");
        // mengecek apakah nomor hp valid atau tidak
        if(Validation.isNoHp(nohp)){
            return String.format("+62 %s-%s-%s", nohp.substring(1, 4), nohp.substring(4, 8), nohp.substring(8));
        }
        return null;
    }
    
    /**
     * Digunakan untuk mengubah gaya dari sebuah password kedalam gaya password case.
     * Method akan mengubah semua karakter yang ada didalam password yang diinputkan 
     * menjadi '•' sehingga tidak ada yang bisa melihat isi dari password (hidden).
     * 
     * <b>Example : </b> 
     * <br>
     * <strong>Sebelum</strong> : "abcdef" <br>
     * <strong>Sesudah</strong> : "••••••"
     * 
     * @param pass password yang akan diubah ke password case.
     * @return password dengan gaya password case.
     */
    public String toPasswordCase(String pass){
        String buffer = "";
        for(int i = 0; i < pass.length(); i++){
            buffer += "•";
        }
        return buffer;
    }
    
}
