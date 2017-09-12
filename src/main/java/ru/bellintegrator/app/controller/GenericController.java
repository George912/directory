package ru.bellintegrator.app.controller;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

/**
 * Интерфейс, содержащий методы манипуляции ресурсами:
 * контакт, группа, пользователь(общие методы).
 */
public interface GenericController<T> {

    /**
     * Возвращает список объектов, владельцем которых является пользователь
     * с идентификатором ownerId.
     *
     * @param ownerId идентификатор владельца объектов
     * @return список объектов
     */
    List<T> list(int ownerId);

    /**
     * Осуществляет поиск объекта по идентификатору id, владельцем которого
     * является пользователь с идентификатором ownerId.
     *
     * @param id      идентификатор объекта
     * @param ownerId идентификатор владельца объектов
     * @return объект
     */
    T findById(int id, int ownerId);

    /**
     * Осуществляет поиск объектов по имени name, владельцем которых является пользователь
     * с идентификатором ownerId.
     *
     * @param name    имя, по которому осуществляется поиск объектов
     * @param ownerId идентификатор владельца объектов
     * @return список объектов
     */
    List<T> findByName(String name, int ownerId);

    /**
     * Создаёт ресурс.
     *
     * @param t данные создаваемого ресурса
     */
    String create(T t);

    /**
     * Редактирует ресурс.
     *
     * @param t данные редактируемого ресурса
     */
    String update(T t);

    /**
     * Удаляет ресурс.
     *
     * @param t данные удаляемого ресурса
     */
    String delete(T t);

    /**
     * Возвращает объект пользователя, содержащий логин и пароль,
     * указанные при авторизации.
     * @return
     */
    default User getPrincipal(UserService service) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String login = authentication.getName();
            String password = (String) authentication.getCredentials();
            try {
                return service.getUserByCredential(login, password);
            } catch (ServiceException e) {
                Logger.getLogger(GenericController.class).error("Exception while retrieving principal: ",e);
            }
        }
        return null;
    }

}
