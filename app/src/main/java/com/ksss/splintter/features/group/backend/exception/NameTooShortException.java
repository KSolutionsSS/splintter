package com.ksss.splintter.features.group.backend.exception;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class NameTooShortException extends Throwable {
    public NameTooShortException() {
        super("Name must have at least 3 letters");
    }
}
