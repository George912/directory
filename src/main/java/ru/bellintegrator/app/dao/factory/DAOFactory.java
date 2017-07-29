package ru.bellintegrator.app.dao.factory;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.dao.factory.impl.xml.XmlDomDAOFactory;
import ru.bellintegrator.app.dao.factory.impl.xml.XmlJacksonDAOFactory;
import ru.bellintegrator.app.dao.factory.impl.xml.XmlSaxDAOFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 19.07.2017.
 */
public abstract class DAOFactory {

    public abstract GenericDAO<Contact> getContactDAO() throws DAOException;

    public abstract GenericDAO<Group> getGroupDAO() throws DAOException;

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType) {

        switch (daoFactoryType) {

            case XML_DOM:
                return new XmlDomDAOFactory();

            case XML_JACKSON:
                return new XmlJacksonDAOFactory();

            case XML_SAX:
                return new XmlSaxDAOFactory();

            case ORACLE:
                throw new UnsupportedOperationException("Unable to create ORACLE dao factory. Define ORACLE dao factory class!");

            case MYSQL:
                throw new UnsupportedOperationException("Unable to create MYSQL dao factory. Define MYSQL dao factory class!");

            case MSSQL:
                throw new UnsupportedOperationException("Unable to create MSSQL dao factory. Define MSSQL dao factory class!");

            case POSTGRESQL:
                throw new UnsupportedOperationException("Unable to create POSTGRESQL dao factory. Define POSTGRESQL dao factory class!");

            case FILE:
                return new MemoryDAOFactory();

            default:
                throw new UnsupportedOperationException("Unable to create dao factory for target type. Type " + daoFactoryType + " is unknown!");
        }

    }

}
