package ru.bellintegrator.app.dao.factory;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.impl.file.MemoryDAOFactory;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 19.07.2017.
 */
public abstract class DAOFactory {

    public abstract GenericDAO<Contact> getContactDAO();

    public abstract GenericDAO<Group> getGroupDAO();

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType) {

        switch (daoFactoryType) {

            case XML:
                throw new UnsupportedOperationException("Define dao factory class!");
            case ORACLE:
                throw new UnsupportedOperationException("Define dao factory class!");
            case MYSQL:
                throw new UnsupportedOperationException("Define dao factory class!");
            case MSSQL:
                throw new UnsupportedOperationException("Define dao factory class!");
            case POSTGRESQL:
                throw new UnsupportedOperationException("Define dao factory class!");
            case FILE:
                return new MemoryDAOFactory();
        }

        return null;

    }

}
