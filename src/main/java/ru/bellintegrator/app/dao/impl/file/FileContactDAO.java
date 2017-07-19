package ru.bellintegrator.app.dao.impl.file;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.ContactDAO;
import ru.bellintegrator.app.dao.factory.impl.file.FileDAOFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileContactDAO implements ContactDAO {

    private static final Logger log = LoggerFactory.getLogger(FileContactDAO.class);

    public FileContactDAO() {
    }

    @Override
    public int insertContact(Contact contact) {

        serialize(DataManager.getInstance().getAllContacts());

        return contact.getId();

    }

    @Override
    public void deleteContact(Contact contact) {

        serialize(DataManager.getInstance().getAllContacts());

    }

    @Override
    public void updateContact(Contact contact) {

        serialize(DataManager.getInstance().getAllContacts());

    }

    @Override
    public List<Contact> getAllContacts() {

        return deserialize();
    }

    private void serialize(List<Contact> contactList) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(FileDAOFactory.FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

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
        List<Group> groups = null;

        try (FileInputStream fileInputStream = new FileInputStream(FileDAOFactory.FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            contacts = (List<Contact>) objectInputStream.readObject();
            groups = (List<Group>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            log.debug("При восстановлении состояния возникла ошибка: " + e.getLocalizedMessage());
        }

        return contacts == null ? new ArrayList<>() : contacts;

    }

}
