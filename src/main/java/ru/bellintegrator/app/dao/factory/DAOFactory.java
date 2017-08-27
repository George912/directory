package ru.bellintegrator.app.dao.factory;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.sql.HibernateDAOFactory;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

/**
 * Created by neste_000 on 19.07.2017.
 */
public abstract class DAOFactory {
    private static final DAOFactoryType DEFAULT_DAO_FACTORY_TYPE = DAOFactoryType.HIBERNATE;

    public abstract GenericDAO<Contact> getContactDAO() throws DAOException;

    public abstract GenericDAO<Group> getGroupDAO() throws DAOException;

    public abstract GenericDAO<User> getUserDAO() throws DAOException;

    public abstract AnalyticalInfoDAO getAnalyticalInfoDAO() throws DAOException;

    public static DAOFactory getDAOFactory() {
        return DAOFactory.getDAOFactory(DEFAULT_DAO_FACTORY_TYPE);
    }

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType) {

        switch (daoFactoryType) {

            case XML_DOM:
                throw new UnsupportedOperationException("Unable to create XML_DOM dao factory. Define SQL_ORACLE dao factory class!");

            case XML_JACKSON:
                throw new UnsupportedOperationException("Unable to create XML_JACKSON dao factory. Define SQL_ORACLE dao factory class!");

            case XML_SAX:
                throw new UnsupportedOperationException("Unable to create XML_SAX dao factory. Define SQL_ORACLE dao factory class!");

            case SQL_ORACLE:
                throw new UnsupportedOperationException("Unable to create ORACLE dao factory. Define SQL_ORACLE dao factory class!");

            case SQL_MYSQL:
                throw new UnsupportedOperationException("Unable to create SQL_MYSQL dao factory. Define SQL_MYSQL dao factory class!");

            case SQL_MSSQL:
                throw new UnsupportedOperationException("Unable to create SQL_MSSQL dao factory. Define SQL_MSSQL dao factory class!");

            case SQL_POSTGRESQL:
            throw new UnsupportedOperationException("Unable to create SQL_POSTGRESQL dao factory. Define SQL_POSTGRESQL dao factory class!");

            case HIBERNATE:
                return new HibernateDAOFactory();

            case FILE:
                throw new UnsupportedOperationException("Unable to create FILE dao factory. Define SQL_MSSQL dao factory class!");

            default:
                throw new UnsupportedOperationException("Unable to create dao factory for target type. Type " + daoFactoryType + " is unknown!");
        }

    }

}
