package ru.bellintegrator.app.service;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;

public class UserService {

    private UserDAO dao;
    private static final Logger log = Logger.getLogger(UserService.class);

    public UserService(GenericDAO<User> dao) {
        this.dao = (UserDAO) dao;
        log.debug("Initialize UserService");
        log.info("UserService instance created");
    }

    public User getUserByCredential(String login, String password) throws ServiceException {
        log.debug("Call getUserByCredential method: login = " + login + ", password=" + password);
        try {
            return dao.getUserByCredential(login, password);

        } catch (DAOException e) {
            log.error("Exception while retrieving user by credential: ", e);
            throw new ServiceException("Exception while retrieving user by credential: ", e);
        }
    }

}
