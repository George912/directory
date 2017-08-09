package ru.bellintegrator.app.dao.factory;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.dao.factory.impl.sql.SqlPostgresqlDAOFactory;
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

            case SQL_ORACLE:
                throw new UnsupportedOperationException("Unable to create ORACLE dao factory. Define SQL_ORACLE dao factory class!");

            case SQL_MYSQL:
                throw new UnsupportedOperationException("Unable to create SQL_MYSQL dao factory. Define SQL_MYSQL dao factory class!");

            case SQL_MSSQL:
                throw new UnsupportedOperationException("Unable to create SQL_MSSQL dao factory. Define SQL_MSSQL dao factory class!");

            case SQL_POSTGRESQL:
                return new SqlPostgresqlDAOFactory();

            case FILE:
                return new MemoryDAOFactory();

            default:
                throw new UnsupportedOperationException("Unable to create dao factory for target type. Type " + daoFactoryType + " is unknown!");
        }

    }

}
