package com.ksss.splintter.data.business.exception;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class NameTooShortException extends Exception {
    public NameTooShortException() {
        super("Name must have at least 3 letters");
    }
}
