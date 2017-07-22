package ru.bellintegrator.app.dao.impl.file;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileContactDAO implements GenericDAO<Contact> {

    private static final Logger log = LoggerFactory.getLogger(FileContactDAO.class);
    private List<Contact> contactList;

    public FileContactDAO() {
    }

    @Override
    public int create(Contact contact) {

        contactList = deserialize();

        if (!contactList.contains(contact)) {
            contactList.add(contact);
            serialize(contactList);
        }

        return contact.getId();

    }

    @Override
    public void delete(Contact contact) {

        contactList = deserialize();

        boolean isRemove = contactList.remove(contact);

        if (isRemove) {
            serialize(contactList);
        }

    }

    @Override
    public void update(Contact contact) {

        contactList = deserialize();

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
    public List<Contact> getAll() {

        contactList = deserialize();

        return contactList;

    }

    @Override
    public void save(List<Contact> contactList) {

        serialize(contactList);

    }

    public void serialize(List<Contact> contactList) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(MemoryDAOFactory.CONTACT_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            log.debug(contactList.toString());

            objectOutputStream.writeObject(contactList);

            objectOutputStream.flush();

        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка сохранения состояния");
            alert.setHeaderText("При сохранении состояния возникла ошибка.");
            alert.setContentText(e.getLocalizedMessage());

            alert.showAndWait();

        }

    }

    private List<Contact> deserialize() {

        List<Contact> contacts = null;

        try (FileInputStream fileInputStream = new FileInputStream(MemoryDAOFactory.CONTACT_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            contacts = (List<Contact>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            log.debug("При восстановлении состояния возникла ошибка: " + e.getLocalizedMessage());
        }

        return contacts == null ? new ArrayList<>() : contacts;

    }

}
