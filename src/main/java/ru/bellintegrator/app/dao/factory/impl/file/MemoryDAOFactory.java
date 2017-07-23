package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class MemoryDAOFactory extends DAOFactory {

    public static final String CONTACT_FILE = "contact";
    public static final String GROUP_FILE = "group";

    @Override
    public GenericDAO<Contact> getContactDAO() {

        FileContactDAO fileContactDAO = new FileContactDAO();
        fileContactDAO.setIdGenerator(new IdGenerator(fileContactDAO.getAll()));

        return fileContactDAO;

    }

    @Override
    public GenericDAO<Group> getGroupDAO() {

        FileGroupDAO fileGroupDAO = new FileGroupDAO();
        fileGroupDAO.setIdGenerator(new IdGenerator(fileGroupDAO.getAll()));

        return fileGroupDAO;

    }

}
