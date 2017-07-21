package ru.bellintegrator.app.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 11.06.2017.
 */
public class DataManager {

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);
    DAOFactory memoryDAOFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
    GenericDAO<Contact> contactGenericDAO = memoryDAOFactory.getContactDAO();
    GenericDAO<Group> groupGenericDAO = memoryDAOFactory.getGroupDAO();
    private List<Contact> contactList = contactGenericDAO.getAll();
    private List<Group> groupList = groupGenericDAO.getAll();
    private static DataManager instance;

    private DataManager() {
    }

    public static DataManager getInstance() {

        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public List<Group> getAllGroups() {
        return groupList;
    }

    public void addContact(Contact contact) {

        if (!contactList.contains(contact)) {
            contactList.add(contact);
            contactGenericDAO.create(contact);
        }

    }

    public void updateContact(Contact contact) {

        for (int i = 0; i < contactList.size(); i++) {
            Contact editableContact = contactList.get(i);

            if (editableContact.getId() == contact.getId()) {
                editableContact.setNotes(contact.getNotes());
                editableContact.setEmail(contact.getEmail());
                editableContact.setFirstName(contact.getFirstName());
                editableContact.setFirstPhoneNumber(contact.getFirstPhoneNumber());
                editableContact.setFirstPhoneNumberType(contact.getFirstPhoneNumberType());
                editableContact.setLastName(contact.getLastName());
                editableContact.setMiddleName(contact.getMiddleName());
                editableContact.setSecondPhoneNumber(contact.getSecondPhoneNumber());
                editableContact.setSecondPhoneNumberType(contact.getSecondPhoneNumberType());
                editableContact.setGroupList(contact.getGroupList());

                contactGenericDAO.update(contact);
            }
        }

    }

    public void deleteContact(Contact contact) {

        boolean isRemove = contactList.remove(contact);

        if (isRemove) {
            contactGenericDAO.delete(contact);
        }

    }

    public void addGroup(Group group) {

        if (!groupList.contains(group)) {
            groupList.add(group);
            groupGenericDAO.create(group);
        }

    }

    public void updateGroup(Group group) {

        for (int i = 0; i < groupList.size(); i++) {
            Group editableGroup = groupList.get(i);

            if (editableGroup.getId() == group.getId()) {
                editableGroup.setName(group.getName());
                editableGroup.setNotes(group.getNotes());

                groupGenericDAO.update(group);
            }
        }

    }

    public void deleteGroup(Group group) {

        boolean isRemove = groupList.remove(group);

        if (isRemove) {
            groupGenericDAO.delete(group);
        }

        //удалить группу у всех контактов

    }

}
