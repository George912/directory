package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.util.ConfigLoader;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class MemoryDAOFactory extends DAOFactory {

    ConfigLoader configLoader = ConfigLoader.getInstance();

    //todo: int ownerId
    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        FileContactDAO fileContactDAO = new FileContactDAO(configLoader.getFileContactsPath());
//        fileContactDAO.setIdGenerator(new IdGenerator(fileContactDAO.getAll()));

        return fileContactDAO;
    }

    //todo: int ownerId
    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        FileGroupDAO fileGroupDAO = new FileGroupDAO(configLoader.getFileGroupsPath());
//        fileGroupDAO.setIdGenerator(new IdGenerator(fileGroupDAO.getAll()));

        return fileGroupDAO;
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Not allowed here!"));
    }

    @Override
    public GenericDAO<AnalyticalInfo> getAnalyticalInfoDAO() throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Not allowed here!"));
    }

}
