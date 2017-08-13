package ru.bellintegrator.app.dao.impl.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.parser.jackson.model.JacksonContact;
import ru.bellintegrator.app.parser.jackson.model.JacksonContacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class JacksonContactDAO extends AbstractDAOWithIdGenerator<Contact> {

    private String filePath;

    public JacksonContactDAO(String filePath) {
        this.filePath = filePath;
    }

    //todo: int ownerId
    @Override
    public int create(Contact contact) throws DAOException {
        List<Contact> contactList = getAll(1);

        if (!contactList.contains(contact)) {
            contactList.add(contact);
            save(contactList);
        }

        return contact.getId();
    }

    //todo: int ownerId
    @Override
    public void delete(Contact contact) throws DAOException {
        List<Contact> contactList = getAll(1);

        if (contactList.remove(contact)) {
            save(contactList);
        }
    }

    //todo: int ownerId
    @Override
    public void update(Contact contact) throws DAOException {
        List<Contact> contactList = getAll(1);

        for (int i = 0; i < contactList.size(); i++) {
            Contact updContact = contactList.get(i);

            if (updContact.getId() == contact.getId()) {
                updContact.setGroupList(contact.getGroupList());
                updContact.setNotes(contact.getNotes());
                updContact.setEmail(contact.getEmail());
                updContact.setFirstName(contact.getFirstName());
                updContact.setFirstPhoneNumber(contact.getFirstPhoneNumber());
                updContact.setFirstPhoneNumberType(contact.getFirstPhoneNumberType());
                updContact.setLastName(contact.getLastName());
                updContact.setMiddleName(contact.getMiddleName());
                updContact.setSecondPhoneNumber(contact.getSecondPhoneNumber());
                updContact.setSecondPhoneNumberType(contact.getSecondPhoneNumberType());

                save(contactList);
            }
        }
    }

    @Override
    public List<Contact> getAll(int ownerId) throws DAOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<ru.bellintegrator.app.model.Contact> contactList = null;

        try (InputStream inputStream = new FileInputStream(filePath)) {
            JacksonContacts jacksonContacts = xmlMapper.readValue(inputStream, JacksonContacts.class);
            contactList = getContactList(jacksonContacts.getJacksonContacts());

        } catch (IOException e) {
            throw new DAOException("Exception while getting contact list: " + e);
        }

        return contactList;
    }

    public void save(List<Contact> list) throws DAOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            XmlMapper xmlMapper = new XmlMapper();
            JacksonContacts jacksonContacts = new JacksonContacts();
            JacksonContact[] jacksonContactArr =
                    new JacksonContact[list.size()];

            for (int i = 0; i < list.size(); i++) {
                Contact contact = list.get(i);

                jacksonContactArr[i] = getJacksonContact(contact);
            }

            jacksonContacts.setJacksonContacts(jacksonContactArr);

            xmlMapper.writeValue(outputStream, jacksonContacts);

        } catch (IOException e) {
            throw new DAOException("Exception while saving contact list: " + e);
        }
    }

    //todo: int ownerId
    @Override
    public Contact getById(int id, int ownerId) throws DAOException {
        try {
            List<Contact> contactList = getAll(1);

            for (Contact contact : contactList) {
                if (contact.getId() == id) {
                    return contact;
                }
            }

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact by id: " + e);
        }

        return null;
    }

    //todo: int ownerId
    @Override
    public List<Contact> getByName(String name, int ownerId) throws DAOException {
        List<Contact> contactList = new ArrayList<>();

        try {
            List<Contact> contacts = getAll(1);

            for (Contact contact : contacts) {
                if (contact.getFirstName().equalsIgnoreCase(name)) {
                    contactList.add(contact);
                }
            }

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact list by name: " + e);
        }

        return contactList;
    }

    private List<ru.bellintegrator.app.model.Contact> getContactList(JacksonContact[] jacksonContacts) {
        List<ru.bellintegrator.app.model.Contact> contactList = new ArrayList<>();

        for (int i = 0; i < jacksonContacts.length; i++) {
            JacksonContact jacksonJacksonContact = jacksonContacts[i];

            Contact contact = new Contact(
                    jacksonJacksonContact.getId()
                    , jacksonJacksonContact.getFirstName()
                    , jacksonJacksonContact.getLastName()
                    , jacksonJacksonContact.getMiddleName()
                    , jacksonJacksonContact.getFirstPhoneNumber()
                    , PhoneNumberType.getTypeByName(jacksonJacksonContact.getFirstPhoneNumberType())
                    , jacksonJacksonContact.getSecondPhoneNumber()
                    , PhoneNumberType.getTypeByName(jacksonJacksonContact.getSecondPhoneNumberType())
                    , jacksonJacksonContact.getEmail(),
                    jacksonJacksonContact.getNotes());

            List<Group> groupList = new ArrayList<>();
            String[] groupIds = jacksonJacksonContact.getGroupIds();

            if (groupIds != null) {
                for (String s : groupIds) {
                    groupList.add(new Group(Integer.parseInt(s)));

                }
            }

            contact.setGroupList(groupList);
            contactList.add(contact);
        }

        return contactList;
    }

    private JacksonContact getJacksonContact(Contact contact) {
        JacksonContact jacksonJacksonContact = new JacksonContact();

        String[] groupIds = new String[contact.getGroupList().size()];

        jacksonJacksonContact.setId(contact.getId());
        jacksonJacksonContact.setFirstName(contact.getFirstName());
        jacksonJacksonContact.setLastName(contact.getLastName());
        jacksonJacksonContact.setMiddleName(contact.getMiddleName());
        jacksonJacksonContact.setFirstPhoneNumber(contact.getFirstPhoneNumber());
        jacksonJacksonContact.setFirstPhoneNumberType(contact.getFirstPhoneNumberType().name());
        jacksonJacksonContact.setSecondPhoneNumber(contact.getSecondPhoneNumber());
        jacksonJacksonContact.setSecondPhoneNumberType(contact.getSecondPhoneNumberType().name());
        jacksonJacksonContact.setEmail(contact.getEmail());
        jacksonJacksonContact.setNotes(contact.getNotes());

        for (int i = 0; i < contact.getGroupList().size(); i++) {
            groupIds[i] = String.valueOf(contact.getGroupList().get(i).getId());
        }

        jacksonJacksonContact.setGroupIds(groupIds);

        return jacksonJacksonContact;
    }

}
