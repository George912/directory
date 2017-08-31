package ru.bellintegrator.app.service;

import ru.bellintegrator.app.exception.ServiceException;

import java.util.List;

/**
 * Интерфейс, содержащий базовые операции с экземплярами классов-моделей системы.
 * @param <T>
 */
public interface BaseService<T> {

    /**
     * Добавляет сущность.
     * @param t
     * @throws ServiceException
     */
    void add(T t) throws ServiceException;

    /**
     * Обновдяет сущность.
     * @param t
     * @throws ServiceException
     */
    void update(T t) throws ServiceException;

    /**
     * Удаляет сущность.
     * @param t
     * @throws ServiceException
     */
    void delete(T t) throws ServiceException;

    /**
     * Получает список сущностей, пользователя с идентификатором ownerId.
     * @param ownerId
     * @return
     * @throws ServiceException
     */
    List<T> list(int ownerId) throws ServiceException;

    /**
     * Осуществляет поиск сущности по идентификатору id,
     * владельцем которой является пользователь с идентификатором ownerId.
     * @param id
     * @param ownerId
     * @return
     * @throws ServiceException
     */
    T findById(int id, int ownerId) throws ServiceException;

    /**
     * Осуществляет поиск сущностей по имени name,
     * владельцем которой является пользователь
     * с идентификатором ownerId.
     * @param name
     * @param ownerId
     * @return
     * @throws ServiceException
     */
    List<T> findByName(String name, int ownerId) throws ServiceException;
}
