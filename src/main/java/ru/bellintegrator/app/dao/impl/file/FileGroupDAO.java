package ru.bellintegrator.app.dao.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileGroupDAO extends AbstractFileDAO<Group> {

    private static final Logger log = LoggerFactory.getLogger(FileGroupDAO.class);

    @Override
    public int create(Group group) throws DAOException {

        group.setId(generateId());
        List<Group> groupList = deserialize();

        if (!groupList.contains(group)) {
            groupList.add(group);

            serialize(groupList);
        }

        return group.getId();

    }

    @Override
    public void delete(Group group) throws DAOException {

        List<Group> groupList = deserialize();

        boolean isRemove = groupList.remove(group);

        if (isRemove) {
            serialize(groupList);
        }

    }

    @Override
    public void update(Group group) throws DAOException {

        List<Group> groupList = deserialize();

        for (int i = 0; i < groupList.size(); i++) {
            Group editableGroup = groupList.get(i);

            if (editableGroup.getId() == group.getId()) {
                editableGroup.setName(group.getName());
                editableGroup.setNotes(group.getNotes());

                serialize(groupList);
            }
        }

    }

    @Override
    public List<Group> getAll() throws DAOException {

        return deserialize();

    }

    @Override
    public void save(List<Group> groupList) throws DAOException {

        serialize(groupList);

    }

    private void serialize(List<Group> groupList) throws DAOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(MemoryDAOFactory.GROUP_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            log.debug(groupList.toString());
            objectOutputStream.writeObject(groupList);

            objectOutputStream.flush();

        } catch (IOException e) {
            throw new DAOException(e);
        }

    }

    private List<Group> deserialize() throws DAOException {

        List<Group> groups = null;

        try (FileInputStream fileInputStream = new FileInputStream(MemoryDAOFactory.GROUP_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            groups = (List<Group>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {

            throw new DAOException(e);

        }

        return groups == null ? new ArrayList<>() : groups;

    }

}
