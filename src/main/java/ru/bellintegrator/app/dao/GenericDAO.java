package ru.bellintegrator.app.dao;

import ru.bellintegrator.app.exception.DAOException;

import java.util.List;

/**
 * Основной интерфейс DAO. Включает ключевые методы для работы с персистентными данными.
 * Методы класса:
 * <ol>
 * <li>create(T t)</li>
 * <li>delete(T t)</li>
 * <li>update(T t)</li>
 * <li>getAll(int ownerId)</li>
 * <li>getById(int id, int ownerId)</li>
 * <li>getByName(String name, int ownerId)</li>
 * <ol/>
 * Created by neste_000 on 21.07.2017.
 */
public interface GenericDAO<T> {

    /**
     * Создаёт данные в хранилище из объекта, переданного в метод.
     *
     * @param t объект, данные которого требуется сохранить в хранилище.
     * @return идентификатор объекта.
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    int create(T t) throws DAOException;

    /**
     * Удаляет данные из хранилища об объекте, переданном в метод.
     *
     * @param t объект, данные которого требуется удалить из хранилища.
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    void delete(T t) throws DAOException;

    /**
     * Обновляет данные в хранилище об объекте, переданном в метод.
     *
     * @param t объект, данные которого требуется обновить в хранилище.
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    void update(T t) throws DAOException;

    /**
     * Получает список персистентных объектов из хранилища.
     *
     * @param ownerId идентификатор владельца персистентных данных.
     * @return список персистентных объектов типа <T>
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    List<T> getAll(int ownerId) throws DAOException;

    /**
     * Получает персистентный объект владельца с ownerId из хранилища по идентификатору.
     *
     * @param id      идентификатор персистентного объекта.
     * @param ownerId идентификатор владельца персистентного объекта.
     * @return персистентный объект типа <T>
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    T getById(int id, int ownerId) throws DAOException;

    /**
     * Получает персистентный объект владельца с ownerId из хранилища по имени.
     *
     * @param name    имя персистентного объекта.
     * @param ownerId идентификатор владельца персистентного объекта.
     * @return список персистентных объектов типа <T>
     * @throws DAOException исключение, описывающее возникшую
     *                      проблемную ситуацию в процессе взаимодействия с хранилищем данных.
     */
    List<T> getByName(String name, int ownerId) throws DAOException;

}
