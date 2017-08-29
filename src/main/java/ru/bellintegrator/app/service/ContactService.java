package ru.bellintegrator.app.service;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService {

    private static final Logger log = Logger.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;
    private GroupService groupService;

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
        log.debug("ContactService UserService");
        log.info("ContactService instance created");
    }

    public void setGroupService(GroupService groupService) {
        log.debug("Call setGroupService method: groupService = " + groupService);
        this.groupService = groupService;
    }

    public void addContact(Contact contact) throws ServiceException {
        log.debug("Call addContact method: contact = " + contact);
        try {
            contactGenericDAO.create(contact);

        } catch (DAOException e) {
            log.error("Exception while adding contact: ", e);
            throw new ServiceException("Exception while adding contact: ", e);
        }
    }

    public void updateContact(Contact contact) throws ServiceException {
        log.debug("Call updateContact method: contact = " + contact);
        try {
            contactGenericDAO.update(contact);

        } catch (DAOException e) {
            log.error("Exception while updating contact: ", e);
            throw new ServiceException("Exception while updating contact: ", e);
        }
    }

    public void deleteContact(Contact contact) throws ServiceException {
        log.debug("Call deleteContact method: contact = " + contact);
        try {
            contactGenericDAO.delete(contact);

        } catch (DAOException e) {
            log.error("Exception while removing contact: ", e);
            throw new ServiceException("Exception while removing contact: ", e);
        }
    }

    public List<Contact> getAllContacts(int ownerId) throws ServiceException {
        log.debug("Call getAllContacts method: ownerId = " + ownerId);
        try {
            return contactGenericDAO.getAll(ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact list: ", e);
            throw new ServiceException("Exception while retrieving contact list: ", e);
        }
    }

    public Contact getContactById(int id, int ownerId) throws ServiceException {
        log.debug("Call getContactById method: id = " + id + ", ownerId = " + ownerId);

        try {
            return contactGenericDAO.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact by id: ", e);
            throw new ServiceException("Exception while retrieving contact by id: ", e);
        }
    }

    public List<Contact> getContactsByName(String name, int ownerId) throws ServiceException {
        log.debug("Call getContactsByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            return contactGenericDAO.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact list by name: ", e);
            throw new ServiceException("Exception while retrieving contact list by name: ", e);
        }
    }

}
