package ru.bellintegrator.app;

import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 16.07.2017.
 */
public class Util {

    public static int getNewContactId(List<Contact> contactList) {

        if (contactList.isEmpty()) {
            return 0;
        } else {
            int newId = 0;
            for (Contact contact : contactList) {
                if (contact.getId() > newId)
                    newId = contact.getId();
            }

            return ++newId;
        }

    }

    public static int getNewGroupId(List<Group> groupList) {

        if (groupList.isEmpty()) {
            return 0;
        } else {
            int newId = 0;
            for (Group group : groupList) {
                if (group.getId() > newId)
                    newId = group.getId();
            }

            return ++newId;
        }

    }

}
