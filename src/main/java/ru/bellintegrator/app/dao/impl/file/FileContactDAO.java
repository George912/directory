package ru.bellintegrator.app.dao.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileContactDAO extends AbstractFileDAO<Contact> {

    private static final Logger log = LoggerFactory.getLogger(FileContactDAO.class);

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
    public List<Contact> getAll() throws DAOException {

        return deserialize();

    }

    public void save(List<Contact> contactList) throws DAOException {

        serialize(contactList);

    }

    @Override
    public Contact getById(int id) {

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
    public List<Contact> getByName(String name) {
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

    private void serialize(List<Contact> contactList) throws DAOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(MemoryDAOFactory.CONTACT_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            log.debug(contactList.toString());
            objectOutputStream.writeObject(contactList);

            objectOutputStream.flush();

        } catch (IOException e) {

            throw new DAOException(e);

        }

    }

    private List<Contact> deserialize() throws DAOException {

        List<Contact> contacts = null;

        try (FileInputStream fileInputStream = new FileInputStream(MemoryDAOFactory.CONTACT_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            contacts = (List<Contact>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {

            throw new DAOException(e);
        }

        return contacts == null ? new ArrayList<>() : contacts;

    }

}
