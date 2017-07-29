package ru.bellintegrator.app.parser.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.parser.jackson.model.Contacts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neste_000 on 29.07.2017.
 */
public class JacksonUtilForContact extends AbstractFileDAO<Contact> {

    @Override
    public int create(Contact contact) throws DAOException {
        return 0;
    }

    @Override
    public void delete(Contact contact) throws DAOException {

    }

    @Override
    public void update(Contact contact) throws DAOException {

    }

    @Override
    //todo дозаполнить группы
    public List<Contact> getAll() throws DAOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<ru.bellintegrator.app.model.Contact> contactList = null;

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts.xml")) {
            Contacts contacts = xmlMapper.readValue(inputStream, Contacts.class);
            contactList = getContactList(contacts.getContacts());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    public void save(List<Contact> list) throws DAOException {
        try (OutputStream outputStream = new FileOutputStream("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts3.xml")) {
            XmlMapper xmlMapper = new XmlMapper();
            Contacts contacts = new Contacts();
            ru.bellintegrator.app.parser.jackson.model.Contact[] contactArr =
                    new ru.bellintegrator.app.parser.jackson.model.Contact[list.size()];

            for (int i = 0; i < list.size(); i++) {
                Contact contact = list.get(i);

                contactArr[i] = getJacksonContact(contact);
            }

            contacts.setContacts(contactArr);

            xmlMapper.writeValue(outputStream, contacts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact getById(int id) {
        return null;
    }

    @Override
    public List<Contact> getByName(String name) {
        return null;
    }

    private List<ru.bellintegrator.app.model.Contact> getContactList(ru.bellintegrator.app.parser.jackson.model.Contact[] contacts) {
        List<ru.bellintegrator.app.model.Contact> contactList = new ArrayList<>();

        for (int i = 0; i < contacts.length; i++) {
            ru.bellintegrator.app.parser.jackson.model.Contact jacksonContact = contacts[i];

            Contact contact = new Contact(
                    jacksonContact.getId()
                    , jacksonContact.getFirstName()
                    , jacksonContact.getLastName()
                    , jacksonContact.getMiddleName()
                    , jacksonContact.getFirstPhoneNumber()
                    , PhoneNumberType.getPhoneNumberTypeByTypeName(jacksonContact.getFirstPhoneNumberType())
                    , jacksonContact.getSecondPhoneNumber()
                    , PhoneNumberType.getPhoneNumberTypeByTypeName(jacksonContact.getSecondPhoneNumberType())
                    , jacksonContact.getEmail(),
                    jacksonContact.getNotes());

            List<Group> groupList = new ArrayList<>();
            List groupIds = jacksonContact.getGroupIds();

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

    private ru.bellintegrator.app.parser.jackson.model.Contact getJacksonContact(Contact contact) {
        return new ru.bellintegrator.app.parser.jackson.model.Contact(
                contact.getId()
                , contact.getFirstName()
                , contact.getLastName()
                , contact.getMiddleName()
                , contact.getFirstPhoneNumber()
                , contact.getFirstPhoneNumberType().name()
                , contact.getSecondPhoneNumber()
                , contact.getSecondPhoneNumberType().name()
                , contact.getEmail()
                , contact.getNotes()
                , contact.getGroupList());
    }

}
