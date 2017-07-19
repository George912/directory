package ru.bellintegrator.app.dao;

import ru.bellintegrator.app.model.Contact;

import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public interface ContactDAO {

    int insertContact(Contact contact);

    void deleteContact(Contact contact);

    void updateContact(Contact contact);

    List<Contact> getAllContacts();

}
