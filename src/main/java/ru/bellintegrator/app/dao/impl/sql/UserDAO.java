package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

public interface UserDAO {

    /**
     * Получает пользователя по логину и паролю.
     * @param login
     * @param password
     * @return
     */
    User getUserByCredential(String login, String password) throws DAOException;

    /**
     * Получает id пользователя в базе данных.
     * @param login
     * @param password
     * @return
     */
    int getUserId(String login, String password) throws DAOException;

}
