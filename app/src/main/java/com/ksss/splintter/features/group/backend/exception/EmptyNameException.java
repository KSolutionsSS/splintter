package com.ksss.splintter.features.group.backend.exception;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class EmptyNameException extends Throwable {
    public EmptyNameException() {
        super("Name must NOT be null or empty");
    }
}
