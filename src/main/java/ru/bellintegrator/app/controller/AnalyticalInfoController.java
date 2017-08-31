package ru.bellintegrator.app.controller;

import java.util.Map;

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
    Map<Integer, Long> eachUserContactCount();

    /**
     * Возвращает количество групп каждого пользователя.
     * @return
     */
    Map<Integer, Long> eachUserGroupCount();

    /**
     * Возвращает среднее количество пользователей в группах.
     * @return
     */
    double avgUserCountInGroup();

    /**
     * Возвращает список неактивных пользователей приложения - количество контактов меньше 10.
     * @return
     */
    Map<Integer, Long> inactiveUserCount();

    /**
     * Возвращает среднее количество контактов у пользователей.
     * @return
     */
    double avgUserContactsCount();

}
