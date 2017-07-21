package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class MemoryDAOFactory extends DAOFactory {

    public static final String CONTACT_FILE = "contact";
    public static final String GROUP_FILE = "group";

    @Override
    public GenericDAO<Contact> getContactDAO() {
        return new FileContactDAO();
    }

    @Override
    public GenericDAO<Group> getGroupDAO() {
        return new FileGroupDAO();
    }

}
