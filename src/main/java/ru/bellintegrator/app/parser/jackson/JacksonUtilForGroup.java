package ru.bellintegrator.app.parser.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.jackson.model.Groups;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 27.07.2017.
 */
public class JacksonUtilForGroup extends AbstractFileDAO<Group> {

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

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups.xml")) {
            Groups groups = xmlMapper.readValue(inputStream, Groups.class);
            groupList = getGroupList(groups.getGroups());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return groupList;
    }

    public void save(List<Group> list) throws DAOException {
        try (OutputStream outputStream = new FileOutputStream("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups3.xml")) {
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
            e.printStackTrace();
        }
    }

    @Override
    public Group getById(int id) {
        List<Group> groupList = null;
        try {
            groupList = getAll();

            for (Group group : groupList) {
                if (group.getId() == id) {
                    return group;
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Group> getByName(String name) {
        List<Group> groups = new ArrayList<>();

        try {
            List<Group> groupList = getAll();

            for (Group group : groupList) {
                if (group.getName().equalsIgnoreCase(name)) {
                    groups.add(group);
                }
            }

        } catch (DAOException e) {
            e.printStackTrace();
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
