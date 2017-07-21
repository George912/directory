package ru.bellintegrator.app.dao.impl.file;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.model.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileGroupDAO implements GenericDAO<Group> {

    private static final Logger log = LoggerFactory.getLogger(FileGroupDAO.class);

    public FileGroupDAO() {
    }

    @Override
    public int create(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

        return group.getId();

    }

    @Override
    public void delete(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

    }

    @Override
    public void update(Group group) {

        serialize(DataManager.getInstance().getAllGroups());

    }

    @Override
    public List<Group> getAll() {

        return deserialize();

    }

    private void serialize(List<Group> groupList) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(MemoryDAOFactory.GROUP_FILE);
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

        List<Group> groups = null;

        try (FileInputStream fileInputStream = new FileInputStream(MemoryDAOFactory.GROUP_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            groups = (List<Group>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            log.debug("При восстановлении состояния возникла ошибка: " + e.getLocalizedMessage());
        }

        return groups == null ? new ArrayList<>() : groups;

    }

}
