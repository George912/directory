package ru.bellintegrator.app.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by neste_000 on 11.06.2017.
 */
public class DataManager {

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);
    private List<Contact> contactList = new ArrayList<>();
    private List<Group> groupList = new ArrayList<>();
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
        contactList.add(contact);
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
            }
        }

    }

    public void deleteContact(Contact contact) {

        int deletableContactId = contact.getId();

        for (int i = 0; i < contactList.size(); i++) {
            Contact deletableContact = contactList.get(i);

            if (deletableContact.getId() == deletableContactId) {
                contactList.remove(i);
            }
        }

    }

    public void addGroup(Group group) {
        groupList.add(group);
    }

    public void updateGroup(Group group) {

        Iterator<Group> groupIterator = groupList.iterator();

        while (groupIterator.hasNext()) {
            Group editableGroup = groupIterator.next();

            if (editableGroup.getId() == group.getId()) {
                editableGroup.setName(group.getName());
                editableGroup.setNotes(group.getNotes());
            }
        }
    }

    public void deleteGroup(Group group) {

        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getId() == group.getId()) {
                groupList.remove(group);
            }
        }

    }

}
