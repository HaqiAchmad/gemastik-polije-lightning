package com.error;

/**
 * Checked exception untuk menangani kesalahan saat melakukan login.
 *
 * @author Achmad Baihaqi
 * @since 2021-06-14
 */
public class AuthenticationException extends Exception {

    /**
     * Creates a new instance of <code>AuthenticationException</code> without
     * detail message.
     */
    public AuthenticationException() {
    }

    /**
     * Constructs an instance of <code>AuthenticationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AuthenticationException(String msg) {
        super(msg);
    }
}
