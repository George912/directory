package ru.bellintegrator.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.ContactListChangeObservable;
import ru.bellintegrator.app.ContactListChangeObserver;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class ContactService implements ContactListChangeObservable {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private GenericDAO<Contact> contactGenericDAO;
    private List<ContactListChangeObserver> contactListChangeObserverList = new ArrayList<>();

    public ContactService(GenericDAO<Contact> contactGenericDAO) {
        this.contactGenericDAO = contactGenericDAO;
    }

    public void addContact(Contact contact) {
        contactGenericDAO.create(contact);
        notifyContactListChangeObserver();
    }

    public void updateContact(Contact contact) {

        contactGenericDAO.update(contact);

        notifyContactListChangeObserver();

    }

    public void deleteContact(Contact contact) {

        contactGenericDAO.delete(contact);

        notifyContactListChangeObserver();

    }

    public List<Contact> getAllContacts() {

        return contactGenericDAO.getAll();

    }

    public void saveContacts(List<Contact> contactList){

        contactGenericDAO.save(contactList);

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
}
