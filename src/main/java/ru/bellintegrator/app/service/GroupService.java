package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

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

    public void addGroup(Group group) {

        groupGenericDAO.create(group);

    }

    public void updateGroup(Group group) {

        groupGenericDAO.update(group);

    }

    public void deleteGroup(Group group) {

        List<Contact> contactList = contactService.getAllContacts();

        for (Contact contact : contactList) {

            contact.getGroupList().remove(group);

        }

        contactService.saveContacts(contactList);

        groupGenericDAO.delete(group);

    }

    public List<Group> getAllGroups() {

        return groupGenericDAO.getAll();

    }

}
