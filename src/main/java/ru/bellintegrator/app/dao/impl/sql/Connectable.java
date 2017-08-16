package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Интрефейс для DAO, работающих с БД.
 */
//todo: use jndi
public interface Connectable {
//    ConfigLoader configLoader = ConfigLoader.getInstance();

    /**
     * Получает и возвращает соединение с БД.
     *
     * @return
     */
    default Connection getConnection() throws DAOException {
        Connection connection = null;

//        try {
//            Class.forName(configLoader.getJdbcDriver());
//        } catch (ClassNotFoundException e) {
//            throw new DAOException("Exception while register jdbc driver:" + e);
//        }
//
//        try {
//            connection = DriverManager.getConnection(configLoader.getJdbcUrl()
//                    , configLoader.getJdbcUser()
//                    , configLoader.getJdbcPassword());
//        } catch (SQLException e) {
//            throw new DAOException("Exception while getting connection:" + e);
//        }

        return connection;
    }

}
