package ru.bellintegrator.app.dao.factory.impl.sql;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.sql.ContactLinkGroupDao;
import ru.bellintegrator.app.dao.impl.sql.postgresql.*;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

public class SqlPostgresqlDAOFactory extends DAOFactory {

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new PostgresqlContactDAO();
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new PostgresqlGroupDAO();
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        return new PostgresqlUserDAO();
    }

    @Override
    public AnalyticalInfoDAO getAnalyticalInfoDAO() {
        return new PostgresqlAnalyticalInfoDAO();
    }

    @Override
    public ContactLinkGroupDao getContactLinkGroupDao() throws DAOException {
        return new PostgresqlContactLinkGroupDAO();
    }

}
