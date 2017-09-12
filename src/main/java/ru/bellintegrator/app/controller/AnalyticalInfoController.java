package ru.bellintegrator.app.controller;

import ru.bellintegrator.app.model.InfoContainer;

/**
 * Интерфейс, содержащий методы для получения статистической информации.
 */
public interface AnalyticalInfoController {

    /**
     * Возвращает общее количество пользователей в базе данных.
      * @return
     */
    int userCount();

    /**
     * Возвращает количество контактов каждого пользователя.
     * @return
     */
    InfoContainer eachUserContactCount();

    /**
     * Возвращает количество групп каждого пользователя.
     * @return
     */
    InfoContainer eachUserGroupCount();

    /**
     * Возвращает среднее количество пользователей в группах.
     * @return
     */
    double avgUserCountInGroup();

    /**
     * Возвращает список неактивных пользователей приложения - количество контактов меньше 10.
     * @return
     */
    InfoContainer inactiveUserCount();

    /**
     * Возвращает среднее количество контактов у пользователей.
     * @return
     */
    double avgUserContactsCount();

}
