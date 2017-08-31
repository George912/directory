package ru.bellintegrator.app.service;

import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;

/**
 * Интерфейс, сервиса для работы с пользователями.
 */
public interface UserService {

    /**
     * Осуществляет поиск пользователя по логину login
     * и паролю password, возвращает найденный объект.
     * @param login
     * @param password
     * @return
     * @throws ServiceException
     */
    User getUserByCredential(String login, String password) throws ServiceException;

}
