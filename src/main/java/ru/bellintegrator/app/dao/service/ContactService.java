package ru.bellintegrator.app.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.model.Contact;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private DAOFactory daoFactory;

    public ContactService() {
        this(null);
    }

    public ContactService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addContact(Contact contact) {

//        daoFactory.

    }

}
