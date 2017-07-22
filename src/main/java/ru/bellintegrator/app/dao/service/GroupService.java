package ru.bellintegrator.app.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupService {

    private static final Logger log = LoggerFactory.getLogger(GroupService.class);
    private GenericDAO<Group> groupGenericDAO;

    public GroupService() {
        this(null);
    }

    public GroupService(GenericDAO<Group> groupGenericDAO) {
        this.groupGenericDAO = groupGenericDAO;
    }

    public void addGroup(Group group) {

        groupGenericDAO.create(group);

    }

    public void updateGroup(Group group) {

        groupGenericDAO.update(group);

    }

    public void deleteGroup(Group group) {

        groupGenericDAO.delete(group);

    }

    public List<Group> getAllGroups() {

        return groupGenericDAO.getAll();

    }

}
