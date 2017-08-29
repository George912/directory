package ru.bellintegrator.app.dao.impl;

import ru.bellintegrator.app.exception.DAOException;

import java.util.Map;

public interface AnalyticalInfoDAO {

    int getUserCount() throws DAOException;

    Map<Integer, Long> getEachUserContactCount() throws DAOException;

    Map<Integer, Long> getEachUserGroupCount() throws DAOException;

    double getAvgUserCountInGroup() throws DAOException;

    Map<Integer, Long> getInactiveUserCount() throws DAOException;

    double getAvgUserContactsCount() throws DAOException;

}
