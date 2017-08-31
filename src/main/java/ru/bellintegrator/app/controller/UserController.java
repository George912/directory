package ru.bellintegrator.app.controller;

import ru.bellintegrator.app.model.User;

/**
 * Интерфейс, содержащий специфичные методы для манипуляции ресурсом пользователь.
 */
public interface UserController {

    /**
     * Осуществляет поиск пользователя по логину login
     *  и паролю password.
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return пользователь
     */
    User findByCredential(String login, String password);

}
