package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.exception.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Интрефейс для DAO, работающих с БД.
 */
public interface Connectable {
    String APP_DB = "java:/comp/env/jdbc/AppDB";

    /**
     * Получает и возвращает соединение с БД.
     *
     * @return
     */
    default Connection getConnection() throws DAOException {
        try {
            Context initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup(APP_DB);

            return ds.getConnection();

        } catch (NamingException | SQLException e) {
            throw new DAOException("Exception while getting connection:" + e);
        }
    }

}
