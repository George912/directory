package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.IdGenerator;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupService {

    private static final Logger log = LoggerFactory.getLogger(GroupService.class);
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;

    public GroupService(GenericDAO<Group> groupGenericDAO, ContactService contactService) {

        this.groupGenericDAO = groupGenericDAO;
        this.contactService = contactService;

    }

    public void addGroup(Group group) throws DAOException {

        groupGenericDAO.create(group);

    }

    public void updateGroup(Group group) throws DAOException {

        groupGenericDAO.update(group);

    }

    public void deleteGroup(Group group) throws DAOException {

        groupGenericDAO.delete(group);
        contactService.deleteGroupFromContacts(group);

    }

    public List<Group> getAllGroups() throws DAOException {

        return groupGenericDAO.getAll();

    }

    public Group getGroupById(int id) {
        return groupGenericDAO.getById(id);
    }

    public List<Group> getGroupsByName(String name) {
        return groupGenericDAO.getByName(name);
    }

}
