package ru.bellintegrator.app.dao.factory.impl.hibernate;

import org.apache.log4j.Logger;
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

public class HibernateDAOFactory extends DAOFactory {

    private static final Logger log = Logger.getLogger(HibernateDAOFactory.class);

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        log.debug("Retrieving contact dao");
        return new HibernateContactDAO();
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        log.debug("Retrieving group dao");
        return new HibernateGroupDAO();
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        log.debug("Retrieving user dao");
        return new HibernateUserDAO();
    }

    @Override
    public AnalyticalInfoDAO getAnalyticalInfoDAO() throws DAOException {
        log.debug("Retrieving analytical info dao");
        return new HibernateAnalyticalInfoDAO();
    }

}
