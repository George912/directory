package ru.bellintegrator.app.dao.factory.impl.hibernate;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.orm.hibernate.HibernateAnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.orm.hibernate.HibernateContactDAO;
import ru.bellintegrator.app.dao.impl.orm.hibernate.HibernateGroupDAO;
import ru.bellintegrator.app.dao.impl.orm.hibernate.HibernateUserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

public class HibernateDAOFactory extends DAOFactory
{
    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new HibernateContactDAO();
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new HibernateGroupDAO();
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        return new HibernateUserDAO();
    }

    @Override
    public AnalyticalInfoDAO getAnalyticalInfoDAO() throws DAOException {
        return new HibernateAnalyticalInfoDAO();
    }

}
