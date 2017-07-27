package ru.bellintegrator.app.validation.xml;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by neste_000 on 27.07.2017.
 */
public interface Validator {

    void validate() throws SAXException, IOException;

}
