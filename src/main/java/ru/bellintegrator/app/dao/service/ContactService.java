package ru.bellintegrator.app.dao.service;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.model.Contact;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;

    public ContactService() {
        this(null);
    }

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
    }

    public void addContact(Contact contact) {

        contactGenericDAO.create(contact);

    }

    public void updateContact(Contact contact) {

        contactGenericDAO.update(contact);

    }

    public void deleteContact(Contact contact) {

        contactGenericDAO.delete(contact);

    }

    public List<Contact> getAllContacts() {

        return contactGenericDAO.getAll();

    }

}
