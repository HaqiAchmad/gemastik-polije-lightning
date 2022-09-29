package com.error;

/**
 * Runtime exception jika ada data dari user yang tidak valid.
 *
 * @author Achmad Baihaqi
 * @since 2021-06-14
 */
public class InValidUserDataException extends RuntimeException {

    /**
     * Creates a new instance of <code>InValidUserDataException</code> without
     * detail message.
     */
    public InValidUserDataException() {
    }

    /**
     * Constructs an instance of <code>InValidUserDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InValidUserDataException(String msg) {
        super(msg);
    }
}
