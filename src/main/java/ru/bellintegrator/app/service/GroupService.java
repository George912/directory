package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupService {

    private static final Logger log = LoggerFactory.getLogger(GroupService.class);
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;
    private User user;

    public GroupService(GenericDAO<Group> groupGenericDAO, ContactService contactService) {

        this.groupGenericDAO = groupGenericDAO;
        this.contactService = contactService;

    }

    public GroupService(GenericDAO<Group> groupGenericDAO, ContactService contactService, User user) {
        this(groupGenericDAO, contactService);
        this.user = user;
    }

    public void addGroup(Group group) throws DAOException {

        groupGenericDAO.create(group);

    }

    public void updateGroup(Group group) throws DAOException {

        groupGenericDAO.update(group);

    }

    public void deleteGroup(Group group) throws DAOException {

        groupGenericDAO.delete(group);
        contactService.deleteGroupFromContacts(group, user.getId());

    }

    public List<Group> getAllGroups(int ownerId) throws DAOException {

        return groupGenericDAO.getAll(ownerId);

    }

    public Group getGroupById(int id, int ownerId) throws DAOException {
        Group group = null;

        try {
            group = groupGenericDAO.getById(id, ownerId);
        } catch (DAOException e) {
            throw new DAOException("Exception while getting group list by id: " + e);
        }

        return group;
    }

    public List<Group> getGroupsByName(String name, int ownerId) throws DAOException {
        List<Group> groupList = null;

        try {
            groupList = groupGenericDAO.getByName(name, ownerId);
        } catch (DAOException e) {
            throw new DAOException("Exception while getting group list by name: " + e);
        }

        return groupList;
    }

}
