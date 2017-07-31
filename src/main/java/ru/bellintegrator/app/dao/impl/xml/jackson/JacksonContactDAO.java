package ru.bellintegrator.app.dao.impl.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.parser.jackson.model.JacksonContacts;
import ru.bellintegrator.app.parser.jackson.model.JacksonContact;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class JacksonContactDAO implements GenericDAO<Contact> {

    private String filePath;

    public JacksonContactDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Contact contact) throws DAOException {
        List<Contact> contactList = getAll();

        if (!contactList.contains(contact)) {
            contactList.add(contact);
            save(contactList);
        }

        return contact.getId();
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        List<Contact> contactList = getAll();

        if (contactList.remove(contact)) {
            save(contactList);
        }
    }

    @Override
    public void update(Contact contact) throws DAOException {
        List<Contact> contactList = getAll();

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
    public List<Contact> getAll() throws DAOException {
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

    @Override
    public Contact getById(int id) throws DAOException {
        try {
            List<Contact> contactList = getAll();

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

    @Override
    public List<Contact> getByName(String name) throws DAOException {
        List<Contact> contactList = new ArrayList<>();

        try {
            List<Contact> contacts = getAll();

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
                    , PhoneNumberType.getPhoneNumberTypeByTypeName(jacksonJacksonContact.getFirstPhoneNumberType())
                    , jacksonJacksonContact.getSecondPhoneNumber()
                    , PhoneNumberType.getPhoneNumberTypeByTypeName(jacksonJacksonContact.getSecondPhoneNumberType())
                    , jacksonJacksonContact.getEmail(),
                    jacksonJacksonContact.getNotes());

            List<Group> groupList = new ArrayList<>();
            List groupIds = jacksonJacksonContact.getGroupIds();

            for (Object groupId : groupIds) {
                LinkedHashMap<String, String> hashMap = (LinkedHashMap<String, String>) groupId;

                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    groupList.add(new Group(Integer.parseInt(entry.getValue().toString())));
                }
            }

            contact.setGroupList(groupList);
            contactList.add(contact);
        }

        return contactList;
    }

    private JacksonContact getJacksonContact(Contact contact) {
        JacksonContact jacksonJacksonContact = new JacksonContact();

        List<String> groupIds = new ArrayList<>();

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

        for (Group group : contact.getGroupList()) {
            groupIds.add(String.valueOf(group.getId()));
        }

        jacksonJacksonContact.setGroupIds(groupIds);

        return jacksonJacksonContact;
    }

}
