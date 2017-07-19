package ru.bellintegrator.app.dao.impl.file;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GroupDAO;
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
public class FileGroupDAO implements GroupDAO {

    private static final Logger log = LoggerFactory.getLogger(FileGroupDAO.class);

    public FileGroupDAO() {
    }

    @Override
    public int insertGroup(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

        return group.getId();

    }

    @Override
    public void deleteGroup(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

    }

    @Override
    public void updateGroup(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

    }

    @Override
    public List<Group> getAllGroups() {

        return deserialize();

    }

    private void serialize(List<Group> groupList) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(FileDAOFactory.FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(groupList);

            objectOutputStream.flush();

        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка сохранения состояния");
            alert.setHeaderText("При сохранении состояния возникла ошибка.");
            alert.setContentText(e.getLocalizedMessage());

            alert.showAndWait();

        }

    }

    private List<Group> deserialize() {

        List<Contact> contacts = null;
        List<Group> groups = null;

        try (FileInputStream fileInputStream = new FileInputStream(FileDAOFactory.FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            contacts = (List<Contact>) objectInputStream.readObject();
            groups = (List<Group>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            log.debug("При восстановлении состояния возникла ошибка: " + e.getLocalizedMessage());
        }

        return groups == null ? new ArrayList<>() : groups;

    }

}
