package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

public class UserService {

    private UserDAO dao;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserService.class);

    public UserService(GenericDAO<User> dao) {
        this.dao = (UserDAO) dao;
        log.debug("Initialize UserService");
        log.info("UserService instance created");
    }

    public User getUserByCredential(String login, String password) throws DAOException {
        log.debug("Call getUserByCredential method: login = " + login + ", password=" + password);
        return dao.getUserByCredential(login, password);
    }

    public int getUserId(String login, String password) throws DAOException {
        log.debug("Call getUserId method: login = " + login + ", password=" + password);
        return dao.getUserId(login, password);
    }

}
