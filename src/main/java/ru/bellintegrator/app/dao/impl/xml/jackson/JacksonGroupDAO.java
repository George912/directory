package ru.bellintegrator.app.dao.impl.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.jackson.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class JacksonGroupDAO implements GenericDAO<Group> {

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
            Groups groups = xmlMapper.readValue(inputStream, Groups.class);
            groupList = getGroupList(groups.getGroups());

        } catch (IOException e) {
            throw new DAOException("Exception while getting group list: " + e);
        }

        return groupList;
    }

    public void save(List<Group> list) throws DAOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            XmlMapper xmlMapper = new XmlMapper();
            Groups groups = new Groups();
            ru.bellintegrator.app.parser.jackson.model.Group[] groupArr =
                    new ru.bellintegrator.app.parser.jackson.model.Group[list.size()];

            for (int i = 0; i < list.size(); i++) {
                Group group = list.get(i);
                groupArr[i] = getJacksonGroup(group);
            }

            groups.setGroups(groupArr);

            xmlMapper.writeValue(outputStream, groups);

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

    private List<ru.bellintegrator.app.model.Group> getGroupList(ru.bellintegrator.app.parser.jackson.model.Group[] groups) {
        List<ru.bellintegrator.app.model.Group> groupList = new ArrayList<>();

        for (int i = 0; i < groups.length; i++) {
            ru.bellintegrator.app.parser.jackson.model.Group group = groups[i];
            groupList.add(new ru.bellintegrator.app.model.Group(group.getId(),
                    group.getName(), group.getNotes()));
        }

        return groupList;
    }

    private ru.bellintegrator.app.parser.jackson.model.Group getJacksonGroup(Group group) {
        return new ru.bellintegrator.app.parser.jackson.model.Group(group.getId(), group.getName(), group.getNotes());
    }

}
