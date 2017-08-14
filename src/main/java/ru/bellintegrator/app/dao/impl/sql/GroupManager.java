package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

public interface GroupManager {

    void addGroupToContact(Group group, Contact contact) throws DAOException;

    void deleteGroupFromContact(Group group, Contact contact) throws DAOException;

}
