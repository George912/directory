package ru.bellintegrator.app.parser.jackson.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

/**
 * Created by neste_000 on 27.07.2017.
 */
@JacksonXmlRootElement(localName = "contacts")
public class Contacts {

    @JacksonXmlProperty(localName = "contact")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Contact[] contacts;

    public Contacts() {
    }

    public Contacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contacts=" + Arrays.toString(contacts) +
                '}';
    }

}
