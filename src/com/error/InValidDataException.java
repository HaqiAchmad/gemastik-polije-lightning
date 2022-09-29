package com.error;

/**
 * Checked exception saat ada data yang tidak valid.
 * 
 * @author Achmad Baihaqi
 * @since 15 Juli 2021
 */
public class InValidDataException extends Exception {

    /**
     * Creates a new instance of <code>InValidDataException</code> without
     * detail message.
     */
    public InValidDataException() {
    }

    /**
     * Constructs an instance of <code>InValidDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InValidDataException(String msg) {
        super(msg);
    }
}
