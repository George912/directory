package ru.bellintegrator.app.service;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupService {

    private static final Logger log = Logger.getLogger(GroupService.class);
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;

    public GroupService(GenericDAO<Group> groupGenericDAO, ContactService contactService) {
        this.groupGenericDAO = groupGenericDAO;
        this.contactService = contactService;
        log.debug("Initialize GroupService");
        log.info("GroupService instance created");
    }

    public void addGroup(Group group) throws ServiceException {
        log.info("Call addGroup method: group = " + group);
        try {
            groupGenericDAO.create(group);

        } catch (DAOException e) {
            log.error("Exception while adding group: ", e);
            throw new ServiceException("Exception while adding group: ", e);
        }
    }

    public void updateGroup(Group group) throws ServiceException {
        log.info("Call updateGroup method: group = " + group);
        try {
            groupGenericDAO.update(group);

        } catch (DAOException e) {
            log.error("Exception while updating group: ", e);
            throw new ServiceException("Exception while updating group: ", e);
        }
    }

    public void deleteGroup(Group group, int ownerId) throws ServiceException {
        log.info("Call deleteGroup method: group = " + group);
        try {
            groupGenericDAO.delete(group);

        } catch (DAOException e) {
            log.error("Exception while removing group: ", e);
            throw new ServiceException("Exception while removing group: ", e);
        }
    }

    public List<Group> getAllGroups(int ownerId) throws ServiceException {
        log.info("Call getAllGroups method: ownerId = " + ownerId);
        try {
            return groupGenericDAO.getAll(ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group list: ", e);
            throw new ServiceException("Exception while retrieving group list: ", e);
        }
    }

    public Group getGroupById(int id, int ownerId) throws ServiceException {
        log.info("Call getGroupById method: id = " + id + ", ownerId = " + ownerId);

        try {
            return groupGenericDAO.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group by id: ", e);
            throw new ServiceException("Exception while retrieving group by id: ", e);
        }
    }

    public List<Group> getGroupsByName(String name, int ownerId) throws ServiceException {
        log.info("Call getGroupsByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            return groupGenericDAO.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group list by name: ", e);
            throw new ServiceException("Exception while retrieving group list by name: ", e);
        }
    }

}
