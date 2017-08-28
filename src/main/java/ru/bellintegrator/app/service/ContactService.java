package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;
    private GroupService groupService;

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
        log.debug("ContactService UserService");
        log.info("ContactService instance created");
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
        log.debug("Call setGroupService method: groupService = " + groupService);
    }

    public void addContact(Contact contact) throws DAOException {
        log.debug("Call addContact method: contact = " + contact);
        contactGenericDAO.create(contact);
    }

    public void updateContact(Contact contact) throws DAOException {
        log.debug("Call updateContact method: contact = " + contact);
        contactGenericDAO.update(contact);
    }

    public void deleteContact(Contact contact) throws DAOException {
        log.debug("Call deleteContact method: contact = " + contact);
        contactGenericDAO.delete(contact);
    }

    public List<Contact> getAllContacts(int ownerId) throws DAOException {
        log.debug("Call getAllContacts method: ownerId = " + ownerId);
        return contactGenericDAO.getAll(ownerId);
    }

    @Deprecated
    public void saveContacts(List<Contact> contactList) throws DAOException {
        for (Contact contact : contactList) {
            contactGenericDAO.create(contact);
        }
    }

    public Contact getContactById(int id, int ownerId) throws DAOException {
        Contact contact;

        log.debug("Call getContactById method: id = " + id + ", ownerId = " + ownerId);

        try {
            contact = contactGenericDAO.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact by id: " + e);
            throw new DAOException("Exception while retrieving contact by id: " + e);
        }

        return contact;
    }

    public List<Contact> getContactsByName(String name, int ownerId) throws DAOException {
        List<Contact> contactList;

        log.debug("Call getContactsByName method: name = " + name + ", ownerId = " + ownerId);

        try {
            contactList = contactGenericDAO.getByName(name, ownerId);
//            updateContactGroups(contactList);

        } catch (DAOException e) {
            log.error("Exception while retrieving contact list by name: " + e);
            throw new DAOException("Exception while retrieving contact list by name: " + e);
        }

        return contactList;
    }

    @Deprecated
    public void deleteGroupFromContacts(Group group, int ownerId) throws DAOException {
        List<Contact> contactList = contactGenericDAO.getAll(ownerId);
        for (Contact contact : contactList) {
            if (contact.getGroupList().remove(group)) {
//                groupService.deleteGroupFromContact(group, contact);
                contactGenericDAO.update(contact);
            }
        }
    }

}
