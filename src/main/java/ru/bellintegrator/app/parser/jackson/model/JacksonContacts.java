package ru.bellintegrator.app.parser.jackson.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

/**
 * Created by neste_000 on 27.07.2017.
 */
@JacksonXmlRootElement(localName = "jacksonContacts")
public class JacksonContacts {

    @JacksonXmlProperty(localName = "contact")
    @JacksonXmlElementWrapper(useWrapping = false)
    private JacksonContact[] jacksonContacts;

    public JacksonContacts() {
    }

    public JacksonContacts(JacksonContact[] jacksonContacts) {
        this.jacksonContacts = jacksonContacts;
    }

    public JacksonContact[] getJacksonContacts() {
        return jacksonContacts;
    }

    public void setJacksonContacts(JacksonContact[] jacksonContacts) {
        this.jacksonContacts = jacksonContacts;
    }

    @Override
    public String toString() {
        return "JacksonContacts{" +
                "jacksonContacts=" + Arrays.toString(jacksonContacts) +
                '}';
    }

}
