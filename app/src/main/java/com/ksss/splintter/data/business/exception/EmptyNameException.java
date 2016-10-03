package com.ksss.splintter.data.business.exception;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class EmptyNameException extends Exception {
    public EmptyNameException() {
        super("Name must NOT be null or empty");
    }
}
