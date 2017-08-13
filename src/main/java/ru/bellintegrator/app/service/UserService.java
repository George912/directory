package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

public class UserService {

    private UserDAO dao;

    public UserService(GenericDAO<User> dao) {
        this.dao = (UserDAO) dao;
    }

    public User getUserByCredential(String login, String password) throws DAOException {
        return dao.getUserByCredential(login, password);
    }

    public int getUserId(String login, String password) throws DAOException {
        return dao.getUserId(login, password);
    }

}
