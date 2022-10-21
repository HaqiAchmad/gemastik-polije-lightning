package com.manage;

import com.data.app.Log;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author Achmad Baihaqi
 * @since 2021-03-16
 */
public class Waktu {
    
    private static final Calendar kalender = Calendar.getInstance();
    
    private static int tahun, bulan, tanggal, jam, menit, detik, milidetik;
    
    public static final String JANUARI = "Januari", FEBRUARI = "Februari", MARET = "Maret", APRIL = "April", 
                               MEI = "Mei", JUNI = "Juni", JULI = "Juli", AGUSTUS = "Agustus", SEPTEMBER = "September", 
                               OKTOBER = "Oktober", NOVEMBER = "November", DESEMBER = "Desember";
    
    public int getTahun(){
        return tahun;
    }
    
    public int getBulan(){
        return bulan;
    }
    
    public int getTanggal(){
        return tanggal;
    }
    
    public int getJam(){
        return jam;
    }
    
    public int getMenit(){
        return menit;
    }
    
    public int getDetik(){
        return detik;
    }
    
    public int getMiliDetik(){
        return milidetik;
    }
    
    public String getCurrentDate(){
        return String.format("%d-%02d-%02d", getTahun(), getBulan()+1, getTanggal());
    }
    
    public String getCurrentDateTime(){
        return String.format("%s %02d:%02d:%02d.%03d", this.getCurrentDate(), getJam(), getMenit(), getDetik(), getMiliDetik());
    }
    
    public String getDateFromInput(Calendar c){
        // mendapatkatkan data tanggal
        return String.format(
                "%d-%d-%d", 
                c.get(Calendar.YEAR), // mendapatkan tahun
                c.get(Calendar.MONTH)+1, // mendapatkan bulan
                c.get(Calendar.DAY_OF_MONTH) // mendapatkan tanggal
        );
    }
    
    public String getNamaHari(int dayOfWeek){
        switch(kalender.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY: return "Minggu";
            case Calendar.MONDAY: return "Senin";
            case Calendar.TUESDAY: return "Selasa";
            case Calendar.WEDNESDAY: return "Rabu";
            case Calendar.THURSDAY: return "Kamis";
            case Calendar.FRIDAY: return "Jumat";
            case Calendar.SATURDAY: return "Sabtu";
            default: return "null";
        }
    }
    
    public String getNamaHari(){
        return this.getNamaHari(kalender.get(Calendar.DAY_OF_WEEK));
    }
    
    public String getNamaBulan(int bulan){
        switch(bulan){
            case Calendar.JANUARY: return "Januari";
            case Calendar.FEBRUARY: return "Februari";
            case Calendar.MARCH: return "Maret";
            case Calendar.APRIL: return "April";
            case Calendar.MAY: return "Mei";
            case Calendar.JUNE: return "Juni";
            case Calendar.JULY: return "Juli";
            case Calendar.AUGUST: return "Agustus";
            case Calendar.SEPTEMBER: return "September";
            case Calendar.OCTOBER: return "Oktober";
            case Calendar.NOVEMBER: return "November";
            case Calendar.DECEMBER: return "Desember";
            default: return "null";
        }
    }
    
    public String getNamaBulan(){
        return this.getNamaBulan(Waktu.bulan);
    }
    
    public int getNilaiBulan(String bulan){
        switch(bulan){
            case "Januari": return 1;
            case "Februari": return 2;
            case "Maret": return 3;
            case "April": return 4;
            case "Mei": return 5;
            case "Juni": return 6;
            case "Juli": return 7;
            case "Agustus": return 8;
            case "September": return 9;
            case "Oktober": return 10;
            case "November": return 11;
            case "Desember": return 12;
            default: return -11;
        }
    }
    
    public int getTotalHari(int bulan){
        switch(bulan){
            case 1: return 31;
            case 2: return (tahun%4==0) || (tahun%400==0) ? 29 : 28;
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: return -1;
        }
    }
    
    public boolean isTanggal(String tanggal){
        String hariStr, bulanStr, tahunStr;
        int hariInt, bulanInt, tahunInt;
        StringTokenizer token = new StringTokenizer(tanggal, "-");
        
        // mengecek jumlah token dari tanggal yang diinputkan
        if(token.countTokens() >= 3){
            // mendapatkan data dari tanggal
           tahunStr = token.nextToken();
           bulanStr = token.nextToken();
           hariStr = token.nextToken();
           // mengecek apakah data dari tanggal adalah number atau bukan
           if(Validation.isNumber(hariStr) && Validation.isNumber(bulanStr) && Validation.isNumber(tahunStr)){
               // mengkonversi data tanggal ke integer
               hariInt = Integer.parseInt(hariStr);
               bulanInt = Integer.parseInt(bulanStr);
               tahunInt = Integer.parseInt(tahunStr);
               // mengecek apakah tahun valid atau tidak
               if(tahunInt >= 1 && tahunInt < 10000){
                   // mengecek apakah bulan valid atau tidak
                   if(bulanInt >= 1 && bulanInt < 13){
                       // mengecek apakah hari valid atau tidak
                       if(hariInt >= 1 && hariInt < this.getTotalHari(bulanInt)+1){
                           // jika tahun, bulan dan hari valid maka method akan mengembalikan nilai true
                           return true;
                       }
                   }
               }
           }
        }
        return false;
    }
    
    public static void updateWaktuOld(){
        Log.addLog("Update Waktu diaktifkan");
        // mendapatkan waktu saat ini
        Waktu.tahun = Waktu.kalender.get(Calendar.YEAR);
        Waktu.bulan = Waktu.kalender.get(Calendar.MONTH);
        Waktu.tanggal = Waktu.kalender.get(Calendar.DAY_OF_MONTH);
        Waktu.jam = Waktu.kalender.get(Calendar.HOUR_OF_DAY);
        Waktu.menit = Waktu.kalender.get(Calendar.MINUTE);
        Waktu.detik = Waktu.kalender.get(Calendar.SECOND);
        Waktu.milidetik = Waktu.kalender.get(Calendar.MILLISECOND);
        
        // mengupdate waktu
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(true){
                        milidetik+=1;
                        if(milidetik > 999){
                            detik++;
                            milidetik = 0;
                        }else if(detik > 59){
                            menit++;
                            detik = 0;
                        }else if(menit > 59){
                            jam++;
                            menit = 0;
                        }else if(jam > 23){
                            jam = 0;
                            tanggal++;
                        }
                        Thread.sleep(1);
                    }
                }catch(InterruptedException ex){
                    Message.showException(this, "Terjadi Kesalahan : " + ex.getMessage(), ex, true);
                }
            }
        }).start();
    }
    
    public String getUpdateWaktu(){
        return String.format("%s, %02d %s %d %02d:%02d:%02d", 
                this.getNamaHari(), tanggal, this.getNamaBulan(bulan), tahun, jam, menit, detik
        );
    }
    
    public String getUpdateTime(){
        return String.format("%02d %s %d %02d:%02d:%02d", 
                tanggal, this.getNamaBulan(bulan), tahun, jam, menit, detik
        );
    }
}
