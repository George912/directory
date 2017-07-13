package ru.bellintegrator.app.exception;

/**
 * Created by neste_000 on 13.07.2017.
 */
public class PersonalDataNotSetException extends Exception {

    public PersonalDataNotSetException() {
        super();
    }

    public PersonalDataNotSetException(String message){
        super(message);
    }

}
