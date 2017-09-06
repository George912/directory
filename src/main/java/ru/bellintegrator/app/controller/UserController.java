package ru.bellintegrator.app.controller;

import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.model.UserContainer;

import java.util.List;

/**
 * Интерфейс, содержащий специфичные методы для манипуляции ресурсом пользователь.
 */
public interface UserController extends GenericController<User>{

    /**
     * Осуществляет поиск пользователя по логину login
     *  и паролю password.
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return пользователь
     */
    User findByCredential(String login, String password);

    /**
     * Возвращает контейнер, содержащий список пользователей.
     * @return
     */
    UserContainer list();

    /**
     * Осуществляет поиск пользователя по идентификатору id.
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * Осуществляет поиск объектов по имени name.
     * @param name
     * @return
     */
    List<User> findByName(String name);

}
