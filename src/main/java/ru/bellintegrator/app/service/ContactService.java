package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.ContactListChangeObservable;
import ru.bellintegrator.app.ContactListChangeObserver;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService implements ContactListChangeObservable {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;
    private List<ContactListChangeObserver> contactListChangeObserverList = new ArrayList<>();
    private GroupService groupService;

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void addContact(Contact contact) throws DAOException {
        contactGenericDAO.create(contact);
        notifyContactListChangeObserver();
    }

    public void updateContact(Contact contact) throws DAOException {

        contactGenericDAO.update(contact);

        notifyContactListChangeObserver();

    }

    public void deleteContact(Contact contact) throws DAOException {

        contactGenericDAO.delete(contact);

        notifyContactListChangeObserver();

    }

    public List<Contact> getAllContacts() throws DAOException {
        List<Contact> contactList = contactGenericDAO.getAll();

        updateContactGroups(contactList);

        return contactList;

    }

    private void fillGroupData(Group group) throws DAOException {
        Group groupWithData = null;
        try {
            groupWithData = groupService.getGroupById(group.getId());
            group.setName(groupWithData.getName());
            group.setNotes(groupWithData.getNotes());

        } catch (DAOException e) {
            throw new DAOException("Exception while setting group data: " + e);
        }
    }

    public void saveContacts(List<Contact> contactList) throws DAOException {
        for (Contact contact : contactList) {
            contactGenericDAO.create(contact);
        }
    }

    public Contact getContactById(int id) throws DAOException {
        Contact contact = null;

        try {
            contact = contactGenericDAO.getById(id);
            List<Contact> contactList = new ArrayList<>();
            contactList.add(contact);
            updateContactGroups(contactList);

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact by id: " + e);
        }

        return contact;
    }

    public List<Contact> getContactsByName(String name) throws DAOException {
        List<Contact> contactList = null;

        try {
            contactList = contactGenericDAO.getByName(name);
            updateContactGroups(contactList);

        } catch (DAOException e) {
            throw new DAOException("Exception while getting contact by name: " + e);
        }

        return contactList;
    }

    @Override
    public void addContactListChangeObserver(ContactListChangeObserver contactListChangeObserver) {

        if (!contactListChangeObserverList.contains(contactListChangeObserver)) {
            contactListChangeObserverList.add(contactListChangeObserver);
        }

    }

    @Override
    public void removeContactListChangeObserver(ContactListChangeObserver contactListChangeObserver) {

        contactListChangeObserverList.remove(contactListChangeObserver);

    }

    @Override
    public void notifyContactListChangeObserver() {
        for (ContactListChangeObserver observer : contactListChangeObserverList) {
            observer.listChanged();
        }
    }

    public void deleteGroupFromContacts(Group group) throws DAOException {
        List<Contact> contactList = contactGenericDAO.getAll();
        for (Contact contact : contactList) {
            if (contact.getGroupList().remove(group)) {
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
