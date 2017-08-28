package ru.bellintegrator.app.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnectable {

    private Context initCtx = null;
    private DataSource ds = null;
    private static final String APP_DB = "java:/comp/env/jdbc/AppDB";
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AbstractConnectable.class);
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    {
        try {
            initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup(APP_DB);

        } catch (NamingException e) {
            log.fatal("Exception while lookup JNDI data source");
        }
    }

    protected Connection getConnection() {
        Connection connection = null;

        try {
            connection = ds.getConnection();

        } catch (SQLException e) {
            log.fatal("Exception while retrieving connection:" + e);
        }

        return connection;
    }

    protected SessionFactory getSessionFactory() {
        return factory;
    }

}
