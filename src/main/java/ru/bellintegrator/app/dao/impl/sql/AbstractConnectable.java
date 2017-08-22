package ru.bellintegrator.app.dao.impl.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnectable{

    Context initCtx = null;
    DataSource ds = null;
    private static final String APP_DB = "java:/comp/env/jdbc/AppDB";
    private static final Logger log = LoggerFactory.getLogger(AbstractConnectable.class);

    {
        try {
            initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup(APP_DB);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = ds.getConnection();

        } catch (SQLException e) {
            log.debug("Exception while getting connection:" + e);
        }

        return connection;
    }

}
