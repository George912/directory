package ru.bellintegrator.app.dao.factory.impl.sql;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.sql.ContactLinkGroupDao;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

public class HibernateDAOFactory extends DAOFactory
{
    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return null;
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return null;
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        return null;
    }

    @Override
    public AnalyticalInfoDAO getAnalyticalInfoDAO() throws DAOException {
        return null;
    }

    @Override
    public ContactLinkGroupDao getContactLinkGroupDao() throws DAOException {
        return null;
    }
}
