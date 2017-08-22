package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

public class AnalyticalInfoService {

    AnalyticalInfoDAO dao;

    public AnalyticalInfoService(AnalyticalInfoDAO dao) {
        this.dao = dao;
    }

    public AnalyticalInfo collectAnalyticalInfo() throws DAOException {
        AnalyticalInfo info = AnalyticalInfo.getInstance();

        info.setAvgUserCountInGroups(dao.getAvgUserCountInGroup());
        info.setAvgUsersContactCount(dao.getAvgUserContactsCount());
        info.setEachUserContactCount(dao.getEachUserContactCount());
        info.setEachUserGroupCount(dao.getEachUserGroupCount());
        info.setInactiveUserCount(dao.getInactiveUserCount());
        info.setUserCount(dao.getUserCount());

        return info;
    }

}
