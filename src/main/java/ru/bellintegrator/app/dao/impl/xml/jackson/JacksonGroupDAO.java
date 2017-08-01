package ru.bellintegrator.app.dao.impl.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.jackson.model.JacksonGroups;
import ru.bellintegrator.app.parser.jackson.model.JacksonGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class JacksonGroupDAO extends AbstractDAOWithIdGenerator<Group> {

    private String filePath;

    public JacksonGroupDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Group group) throws DAOException {
        List<Group> groupList = getAll();

        if (!groupList.contains(group)) {
            groupList.add(group);
            save(groupList);
        }

        return group.getId();
    }

    @Override
    public void delete(Group group) throws DAOException {
        List<Group> groupList = getAll();

        if (groupList.remove(group)) {
            save(groupList);
        }
    }

    @Override
    public void update(Group group) throws DAOException {
        List<Group> groupList = getAll();

        for (int i = 0; i < groupList.size(); i++) {
            Group editableGroup = groupList.get(i);

            if (editableGroup.getId() == group.getId()) {
                editableGroup.setName(group.getName());
                editableGroup.setNotes(group.getNotes());

                save(groupList);
            }
        }
    }

    @Override
    public List<Group> getAll() throws DAOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<ru.bellintegrator.app.model.Group> groupList = null;

        try (InputStream inputStream = new FileInputStream(filePath)) {
            JacksonGroups jacksonGroups = xmlMapper.readValue(inputStream, JacksonGroups.class);
            groupList = getGroupList(jacksonGroups.getJacksonGroups());

        } catch (IOException e) {
            throw new DAOException("Exception while getting group list: " + e);
        }

        return groupList;
    }

    public void save(List<Group> list) throws DAOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            XmlMapper xmlMapper = new XmlMapper();
            JacksonGroups jacksonGroups = new JacksonGroups();
            JacksonGroup[] jacksonGroupArr =
                    new JacksonGroup[list.size()];

            for (int i = 0; i < list.size(); i++) {
                Group group = list.get(i);
                jacksonGroupArr[i] = getJacksonGroup(group);
            }

            jacksonGroups.setJacksonGroups(jacksonGroupArr);

            xmlMapper.writeValue(outputStream, jacksonGroups);

        } catch (IOException e) {
            throw new DAOException("Exception while saving group list: " + e);
        }
    }

    @Override
    public Group getById(int id) throws DAOException {
        try {
            List<Group> groupList = getAll();

            for (Group group : groupList) {
                if (group.getId() == id) {
                    return group;
                }
            }

        } catch (DAOException e) {
            throw new DAOException("Exception while getting group by id: " + e);
        }

        return null;
    }

    @Override
    public List<Group> getByName(String name) throws DAOException {
        List<Group> groups = new ArrayList<>();

        try {
            List<Group> groupList = getAll();

            for (Group group : groupList) {
                if (group.getName().equalsIgnoreCase(name)) {
                    groups.add(group);
                }
            }

        } catch (DAOException e) {
            throw new DAOException("Exception while getting groups by name: " + e);
        }

        return groups;
    }

    private List<ru.bellintegrator.app.model.Group> getGroupList(JacksonGroup[] jacksonGroups) {
        List<ru.bellintegrator.app.model.Group> groupList = new ArrayList<>();

        for (int i = 0; i < jacksonGroups.length; i++) {
            JacksonGroup jacksonGroup = jacksonGroups[i];
            groupList.add(new ru.bellintegrator.app.model.Group(jacksonGroup.getId(),
                    jacksonGroup.getName(), jacksonGroup.getNotes()));
        }

        return groupList;
    }

    private JacksonGroup getJacksonGroup(Group group) {
        return new JacksonGroup(group.getId(), group.getName(), group.getNotes());
    }

}
