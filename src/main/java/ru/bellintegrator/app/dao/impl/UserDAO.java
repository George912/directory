package ru.bellintegrator.app.dao.impl;

import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

public interface UserDAO {

    /**
     * Получает пользователя по логину и паролю.
     *
     * @param login
     * @param password
     * @return
     */
    User getUserByCredential(String login, String password) throws DAOException;

}
