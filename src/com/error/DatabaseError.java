package com.error;

/**
 * Thrown ketika terjadi kesalahan yang sangat fatal terhadap Database.
 * 
 * @author Achmad Baihaqi
 * @since 2021-06-11
 */
public class DatabaseError extends Error {

    /**
     * Creates a new instance of <code>DatabaseError</code> without detail
     * message.
     */
    public DatabaseError() {
    }

    /**
     * Constructs an instance of <code>DatabaseError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DatabaseError(String msg) {
        super(msg);
    }
}
