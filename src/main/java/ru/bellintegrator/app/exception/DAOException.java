package ru.bellintegrator.app.exception;

/**
 * Created by neste_000 on 24.07.2017.
 */
public class DAOException extends Exception{

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

}
