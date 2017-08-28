package ru.bellintegrator.app.service;

import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

public class AnalyticalInfoService {

    private AnalyticalInfoDAO dao;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AnalyticalInfoService.class);

    public AnalyticalInfoService(AnalyticalInfoDAO dao) {
        this.dao = dao;
        log.debug("Initialize AnalyticalInfoService");
        log.info("AnalyticalInfoService instance created");
    }

    public AnalyticalInfo collectAnalyticalInfo() throws DAOException {
        AnalyticalInfo info = AnalyticalInfo.getInstance();

        info.setAvgUserCountInGroups(dao.getAvgUserCountInGroup());
        info.setAvgUsersContactCount(dao.getAvgUserContactsCount());
        info.setEachUserContactCount(dao.getEachUserContactCount());
        info.setEachUserGroupCount(dao.getEachUserGroupCount());
        info.setInactiveUserCount(dao.getInactiveUserCount());
        info.setUserCount(dao.getUserCount());

        log.debug("Call collectAnalyticalInfo method: info object is " + info);

        return info;
    }

}
