package ru.bellintegrator.app.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbstractConnectable {

    private static final Logger log = Logger.getLogger(AbstractConnectable.class);
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    protected SessionFactory getSessionFactory() {
        log.debug("Call getSessionFactory method");
        return factory;
    }

}
