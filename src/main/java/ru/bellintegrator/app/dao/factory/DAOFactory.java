package ru.bellintegrator.app.dao.factory;

import ru.bellintegrator.app.dao.GroupDAO;
import ru.bellintegrator.app.dao.factory.impl.file.FileDAOFactory;
import ru.bellintegrator.app.dao.ContactDAO;

/**
 * Created by neste_000 on 19.07.2017.
 */
public abstract class DAOFactory {

    public abstract ContactDAO getContactDAO();

    public abstract GroupDAO getGroupDAO();

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType){

        switch (daoFactoryType){

            case XML:
                break;
            case ORACLE:
                break;
            case MYSQL:
                break;
            case MSSQL:
                break;
            case POSTGRESQL:
                break;
            case FILE:
                return new FileDAOFactory();
        }

        return null;

    }


}
