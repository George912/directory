package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class MemoryDAOFactory extends DAOFactory {
    //todo get filePath for serialization/deserialization from config.properties
    private static final String CONTACT_FILE = "contact";
    private static final String GROUP_FILE = "group";

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        FileContactDAO fileContactDAO = new FileContactDAO(CONTACT_FILE);
        fileContactDAO.setIdGenerator(new IdGenerator(fileContactDAO.getAll()));

        return fileContactDAO;
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        FileGroupDAO fileGroupDAO = new FileGroupDAO(GROUP_FILE);
        fileGroupDAO.setIdGenerator(new IdGenerator(fileGroupDAO.getAll()));

        return fileGroupDAO;
    }

}
