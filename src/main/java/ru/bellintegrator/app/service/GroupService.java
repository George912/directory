package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupService {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GroupService.class);
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;

    public GroupService(GenericDAO<Group> groupGenericDAO, ContactService contactService) {
        this.groupGenericDAO = groupGenericDAO;
        this.contactService = contactService;
        log.debug("Initialize GroupService");
        log.info("GroupService instance created");
    }

    public void addGroup(Group group) throws DAOException {
        log.info("Call addGroup method: group = " + group);
        groupGenericDAO.create(group);
    }

    public void updateGroup(Group group) throws DAOException {
        log.info("Call updateGroup method: group = " + group);
        groupGenericDAO.update(group);
    }

    public void deleteGroup(Group group, int ownerId) throws DAOException {
        log.info("Call deleteGroup method: group = " + group);
        groupGenericDAO.delete(group);
    }

    public List<Group> getAllGroups(int ownerId) throws DAOException {
        log.info("Call getAllGroups method: ownerId = " + ownerId);
        return groupGenericDAO.getAll(ownerId);
    }

    public Group getGroupById(int id, int ownerId) throws DAOException {
        Group group;

        log.info("Call getGroupById method: id = " + id + ", ownerId = " + ownerId);

        try {
            group = groupGenericDAO.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group by id: " + e);
            throw new DAOException("Exception while retrieving group by id: " + e);
        }

        return group;
    }

    public List<Group> getGroupsByName(String name, int ownerId) throws DAOException {
        List<Group> groupList;

        log.info("Call getGroupsByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            groupList = groupGenericDAO.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group list by name: " + e);
            throw new DAOException("Exception while retrieving group list by name: " + e);
        }

        return groupList;
    }

}
