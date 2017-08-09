package ru.bellintegrator.app.dao.impl.sql;

public interface UserDAO {

    /**
     * Проверяет существование пользователя в базе данных.
     * @param login
     * @param password
     * @return
     */
    boolean isExist(String login, String password);

    /**
     * Получает id пользователя в базе данных.
     * @param login
     * @param password
     * @return
     */
    int getUserId(String login, String password);

}
