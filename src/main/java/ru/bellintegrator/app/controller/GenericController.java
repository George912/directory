package ru.bellintegrator.app.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.bellintegrator.app.model.User;

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

    default User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String login = authentication.getName();
            String password = (String) authentication.getCredentials();
            return new User(0, login, password);
        }
        return null;
    }

}
