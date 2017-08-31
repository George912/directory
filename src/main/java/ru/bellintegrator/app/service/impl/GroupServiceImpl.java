package ru.bellintegrator.app.service.impl;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.GroupService;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class GroupServiceImpl implements GroupService{

    private static final Logger log = Logger.getLogger(GroupServiceImpl.class);
    private GenericDAO<Group> groupGenericDAO;
    private ContactServiceImpl contactServiceImpl;

    public GroupServiceImpl(GenericDAO<Group> groupGenericDAO, ContactServiceImpl contactServiceImpl) {
        this.groupGenericDAO = groupGenericDAO;
        this.contactServiceImpl = contactServiceImpl;
        log.debug("Initialize GroupService");
        log.info("GroupService instance created");
    }

    @Override
    public void add(Group group) throws ServiceException {
        log.info("Call add method: group = " + group);
        try {
            groupGenericDAO.create(group);

        } catch (DAOException e) {
            log.error("Exception while adding group: ", e);
            throw new ServiceException("Exception while adding group: ", e);
        }
    }

    @Override
    public void update(Group group) throws ServiceException {
        log.info("Call update method: group = " + group);
        try {
            groupGenericDAO.update(group);

        } catch (DAOException e) {
            log.error("Exception while updating group: ", e);
            throw new ServiceException("Exception while updating group: ", e);
        }
    }

    @Override
    public void delete(Group group) throws ServiceException {
        log.info("Call delete method: group = " + group);
        try {
            groupGenericDAO.delete(group);

        } catch (DAOException e) {
            log.error("Exception while removing group: ", e);
            throw new ServiceException("Exception while removing group: ", e);
        }
    }

    @Override
    public List<Group> list(int ownerId) throws ServiceException {
        log.info("Call list method: ownerId = " + ownerId);
        try {
            return groupGenericDAO.getAll(ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group list: ", e);
            throw new ServiceException("Exception while retrieving group list: ", e);
        }
    }

    @Override
    public Group findById(int id, int ownerId) throws ServiceException {
        log.info("Call findById method: id = " + id + ", ownerId = " + ownerId);

        try {
            return groupGenericDAO.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group by id: ", e);
            throw new ServiceException("Exception while retrieving group by id: ", e);
        }
    }

    @Override
    public List<Group> findByName(String name, int ownerId) throws ServiceException {
        log.info("Call findByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            return groupGenericDAO.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving group list by name: ", e);
            throw new ServiceException("Exception while retrieving group list by name: ", e);
        }
    }

}
