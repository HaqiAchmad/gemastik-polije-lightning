package com.error;

/**
 * Checked exception saat user melakukan transaksi pembayaran SPP yang tidak sah.
 * 
 * @author Achmad Baihaqi
 * @since 2021-06-13
 */
public class InvalidTransactionException extends Exception {

    /**
     * Creates a new instance of <code>InvalidTransactionException</code>
     * without detail message.
     */
    public InvalidTransactionException() {
    }

    /**
     * Constructs an instance of <code>InvalidTransactionException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidTransactionException(String msg) {
        super(msg);
    }
}
