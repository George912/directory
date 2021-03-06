package ru.bellintegrator.app.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.service.ContactService;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
@Service("contactService")
@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
public class ContactServiceImpl implements ContactService {

    private static final Logger log = Logger.getLogger(ContactServiceImpl.class);
    private GenericDAO<Contact> dao;

    public ContactServiceImpl(GenericDAO<Contact> dao) {
        this.dao = dao;
        log.debug("ContactService UserService");
        log.info("ContactService instance created");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void add(Contact contact) throws ServiceException {
        log.debug("Call add method: contact = " + contact);
        try {
            dao.create(contact);

        } catch (DAOException e) {
            log.error("Exception while adding contact: ", e);
            throw new ServiceException("Exception while adding contact: ", e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Contact contact) throws ServiceException {
        log.debug("Call update method: contact = " + contact);
        try {
            dao.update(contact);

        } catch (DAOException e) {
            log.error("Exception while updating contact: ", e);
            throw new ServiceException("Exception while updating contact: ", e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Contact contact) throws ServiceException {
        log.debug("Call delete method: contact = " + contact);
        try {
            dao.delete(contact);

        } catch (DAOException e) {
            log.error("Exception while removing contact: ", e);
            throw new ServiceException("Exception while removing contact: ", e);
        }
    }

    @Override
    public List<Contact> list(int ownerId) throws ServiceException {
        log.debug("Call list method: ownerId = " + ownerId);
        try {
            return dao.getAll(ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact list: ", e);
            throw new ServiceException("Exception while retrieving contact list: ", e);
        }
    }

    @Override
    public Contact findById(int id, int ownerId) throws ServiceException {
        log.debug("Call findById method: id = " + id + ", ownerId = " + ownerId);

        try {
            return dao.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact by id: ", e);
            throw new ServiceException("Exception while retrieving contact by id: ", e);
        }
    }

    @Override
    public List<Contact> findByName(String name, int ownerId) throws ServiceException {
        log.debug("Call findByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            return dao.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact list by name: ", e);
            throw new ServiceException("Exception while retrieving contact list by name: ", e);
        }
    }

}
