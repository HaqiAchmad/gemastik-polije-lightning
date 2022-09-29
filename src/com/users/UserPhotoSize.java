package com.users;

/**
 * Size-size dari Foto User.
 * 
 * @author Achmad Baihaqi
 * @since 30-06-2021
 */
public enum UserPhotoSize {
    
    FOTO_PROFILE_PETUGAS,
    FOTO_PROFILE_SISWA,
    INFO_AKUN_PETUGAS,
    INFO_AKUN_SISWA,
    DATA_PETUGAS,
    DATA_SISWA,
    SHOW_FOTO_PETUGAS,
    SHOW_FOTO_SISWA,
    DASHBOARD_SISWA_FOTO,
    EDIT_FOTO_PETUGAS,
    EDIT_FOTO_SISWA;
    
    /**
     * 
     * <ul>
     *  <li><b>FOTO_PROFILE_PETUGAS : 72</b></li>
     *  <li><b>FOTO_PROFILE_SISWA : 72</b></li>
     *  <li><b>INFO_AKUN_PETUGAS : 92</b></li>
     *  <li><b>INFO_AKUN_SISWA : 76</b></li>
     *  <li><b>DATA_PETUGAS : 82</b></li>
     *  <li><b>DATA_SISWA : 74</b></li>
     *  <li><b>SHOW_FOTO_PETUGAS : 359</b></li>
     *  <li><b>SHOW_FOTO_SISWA : 339</b></li>
     *  <li><b>DASHBOARD_SISWA_FOTO : 200</b></li>
     *  <li><b>EDIT_FOTO_PETUGAS : 76</b></li>
     *  <li><b>EDIT_FOTO_SISWA : 76</b></li>
     * </ul>
     * 
     * @param size
     * @return 
     */
    public static int getWidth(UserPhotoSize size){
        switch(size){
            case FOTO_PROFILE_PETUGAS : return 72;
            case FOTO_PROFILE_SISWA : return 72;
            case INFO_AKUN_PETUGAS : return 92;
            case INFO_AKUN_SISWA : return 76;
            case DATA_PETUGAS : return 82;
            case DATA_SISWA: return 74;
            case SHOW_FOTO_PETUGAS : return 359;
            case SHOW_FOTO_SISWA : return 339;
            case DASHBOARD_SISWA_FOTO : return 200;
            case EDIT_FOTO_PETUGAS : return 76;
            case EDIT_FOTO_SISWA : return 76;
            default : return -1;
                
        }
    }
    
    /**
     * 
     * <ul>
     *  <li><b>FOTO_PROFILE_PETUGAS : 60</b></li>
     *  <li><b>FOTO_PROFILE_SISWA : 93</b></li>
     *  <li><b>INFO_AKUN_PETUGAS : 86</b></li>
     *  <li><b>INFO_AKUN_SISWA : 97</b></li>
     *  <li><b>DATA_PETUGAS : 75</b></li>
     *  <li><b>DATA_SISWA : 96</b></li>
     *  <li><b>SHOW_FOTO_PETUGAS : 328</b></li>
     *  <li><b>SHOW_FOTO_SISWA : 471</b></li>
     *  <li><b>DASHBOARD_SISWA_FOTO : 270</b></li>
     *  <li><b>EDIT_FOTO_PETUGAS : 78</b></li>
     *  <li><b>EDIT_FOTO_SISWA : 100</b></li>
     * </ul>
     * 
     * @param size
     * @return 
     */
    public static int getHeight(UserPhotoSize size){
        switch(size){
            case FOTO_PROFILE_PETUGAS : return 60;
            case FOTO_PROFILE_SISWA : return 93;
            case INFO_AKUN_PETUGAS : return 86;
            case INFO_AKUN_SISWA : return 97;
            case DATA_PETUGAS : return 75;
            case DATA_SISWA: return 96;
            case SHOW_FOTO_PETUGAS : return 328;
            case SHOW_FOTO_SISWA : return 471;
            case DASHBOARD_SISWA_FOTO : return 270;
            case EDIT_FOTO_PETUGAS : return 78;
            case EDIT_FOTO_SISWA : return 96;
            default : return -1;
                
        }
    }
    
}
