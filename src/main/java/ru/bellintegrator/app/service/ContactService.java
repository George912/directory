package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService{

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;
    private GroupService groupService;

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void addContact(Contact contact) throws DAOException {
        contactGenericDAO.create(contact);
    }

    public void updateContact(Contact contact) throws DAOException {
        contactGenericDAO.update(contact);
    }

    public void deleteContact(Contact contact) throws DAOException {
        contactGenericDAO.delete(contact);
    }

    public List<Contact> getAllContacts(int ownerId) throws DAOException {
        List<Contact> contactList = contactGenericDAO.getAll(ownerId);

        updateContactGroups(contactList);

        return contactList;

    }

    private void fillGroupData(Group group) throws DAOException {
        Group groupWithData = null;

        try {
            groupWithData = groupService.getGroupById(group.getId(), group.getOwner().getId());
            group.setName(groupWithData.getName());
            group.setNotes(groupWithData.getNotes());

//        } catch (DAOException e) {
        } catch (Exception e) {
            throw new DAOException("Exception while setting group data: " + e);
        }
    }

    public void saveContacts(List<Contact> contactList) throws DAOException {
        for (Contact contact : contactList) {
            contactGenericDAO.create(contact);
        }
    }

    public Contact getContactById(int id, int ownerId) throws DAOException {
        Contact contact;

        try {
            contact = contactGenericDAO.getById(id, ownerId);
            List<Contact> contactList = new ArrayList<>();
            contactList.add(contact);
            updateContactGroups(contactList);

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact by id: " + e);
        }

        return contact;
    }

    public List<Contact> getContactsByName(String name, int ownerId) throws DAOException {
        List<Contact> contactList;

        try {
            contactList = contactGenericDAO.getByName(name, ownerId);
            updateContactGroups(contactList);

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact by name: " + e);
        }

        return contactList;
    }

    public void deleteGroupFromContacts(Group group, int ownerId) throws DAOException {
        List<Contact> contactList = contactGenericDAO.getAll(ownerId);
        for (Contact contact : contactList) {
            if (contact.getGroupList().remove(group)) {
//                groupService.deleteGroupFromContact(group, contact);
                contactGenericDAO.update(contact);
            }
        }
    }

    private void updateContactGroups(List<Contact> contactList) throws DAOException {
        for (int i = 0; i < contactList.size(); i++) {
            Contact contact = contactList.get(i);

            List<Group> groupList = contact.getGroupList();

            for (int i1 = 0; i1 < groupList.size(); i1++) {
                Group group = groupList.get(i1);

                try {
                    fillGroupData(group);
                } catch (DAOException e) {
                    throw new DAOException("Exception while updating contact groups: " + e);
                }
            }
        }
    }

}
