package ru.bellintegrator.app;

/**
 * Created by neste_000 on 21.07.2017.
 */
public interface ContactListChangeObservable {

    void addContactListChangeObserver(ContactListChangeObserver contactListChangeObserver);

    void removeContactListChangeObserver(ContactListChangeObserver contactListChangeObserver);

    void notifyContactListChangeObserver();

}
