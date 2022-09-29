package com.error;

/**
 * Checked exception jika direktori penyimpanan aplikasi tidak dapat ditemukan.
 * 
 * @author Achmad Baihaqi
 * @since 2021-06-10
 */
public class StorageNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>StorageNotFoundException</code> without
     * detail message.
     */
    public StorageNotFoundException() {
    }

    /**
     * Constructs an instance of <code>StorageNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StorageNotFoundException(String msg) {
        super(msg);
    }
}
