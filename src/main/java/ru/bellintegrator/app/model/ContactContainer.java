package ru.bellintegrator.app.model;

import java.util.List;

public class ContactContainer {
    private List<Contact> contacts;

    public ContactContainer(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
