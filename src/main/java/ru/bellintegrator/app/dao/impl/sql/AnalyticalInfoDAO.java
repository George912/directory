package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.util.Map;

public interface AnalyticalInfoDAO extends GenericDAO<AnalyticalInfo>{

    int getUserCount();

    Map<Integer, Integer> getEachUserContactCount();

    Map<Integer, Integer> getEachUserGroupCount();

    double getAvgUserCountInGroup();

    int getInactiveUserCount();

    double getAvgUserContactsCount();

}
