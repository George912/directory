package ru.bellintegrator.app.dao.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileContactDAO extends AbstractFileDAO<Contact> {

    private static final Logger log = LoggerFactory.getLogger(FileContactDAO.class);

    public FileContactDAO(String filePath) {
        this.setFilePath(filePath);
    }

    @Override
    public int create(Contact contact) throws DAOException {

        contact.setId(generateId());
        List<Contact> contactList = deserialize();

        if (!contactList.contains(contact)) {
            contactList.add(contact);
            serialize(contactList);
        }

        return contact.getId();

    }

    @Override
    public void delete(Contact contact) throws DAOException {

        List<Contact> contactList = deserialize();

        boolean isRemove = contactList.remove(contact);

        if (isRemove) {
            serialize(contactList);
        }

    }

    @Override
    public void update(Contact contact) throws DAOException {

        List<Contact> contactList = deserialize();

        for (int i = 0; i < contactList.size(); i++) {
            Contact editableContact = contactList.get(i);

            if (editableContact.getId() == contact.getId()) {
                editableContact.setNotes(contact.getNotes());
                editableContact.setEmail(contact.getEmail());
                editableContact.setFirstName(contact.getFirstName());
                editableContact.setFirstPhoneNumber(contact.getFirstPhoneNumber());
                editableContact.setFirstPhoneNumberType(contact.getFirstPhoneNumberType());
                editableContact.setLastName(contact.getLastName());
                editableContact.setMiddleName(contact.getMiddleName());
                editableContact.setSecondPhoneNumber(contact.getSecondPhoneNumber());
                editableContact.setSecondPhoneNumberType(contact.getSecondPhoneNumberType());
                editableContact.setGroupList(contact.getGroupList());

                serialize(contactList);
            }
        }

    }

    @Override
    public List<Contact> getAll(int ownerId) throws DAOException {

        return deserialize();

    }

    public void save(List<Contact> contactList) throws DAOException {

        serialize(contactList);

    }

    @Override
    public Contact getById(int id, int ownerId) {

        try {
            List<Contact> contactList = deserialize();

            for (Contact contact : contactList) {
                if (contact.getId() == id) {
                    return contact;
                }
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Contact> getByName(String name, int ownerId) {
        List<Contact> contacts = new ArrayList<>();

        try {
            List<Contact> contactList = deserialize();

            for (Contact contact : contactList) {
                if (contact.getFirstName().equalsIgnoreCase(name)) {
                    contacts.add(contact);
                }
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

}
