package ru.bellintegrator.app.service;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;

public class AnalyticalInfoService {

    private AnalyticalInfoDAO dao;
    private static final Logger log = Logger.getLogger(AnalyticalInfoService.class);

    public AnalyticalInfoService(AnalyticalInfoDAO dao) {
        this.dao = dao;
        log.debug("Initialize AnalyticalInfoService");
        log.info("AnalyticalInfoService instance created");
    }

    public AnalyticalInfo collectAnalyticalInfo() throws ServiceException {
        AnalyticalInfo info = new AnalyticalInfo();

        try {
            info.setAvgUserCountInGroups(dao.getAvgUserCountInGroup());
            info.setAvgUsersContactCount(dao.getAvgUserContactsCount());
            info.setEachUserContactCount(dao.getEachUserContactCount());
            info.setEachUserGroupCount(dao.getEachUserGroupCount());
            info.setInactiveUserCount(dao.getInactiveUserCount());
            info.setUserCount(dao.getUserCount());

        } catch (DAOException e) {
            log.error("Exception while building analytic info object: ", e);
            throw new ServiceException("Exception while building analytic info object: ", e);
        }

        log.debug("Call collectAnalyticalInfo method: info object is " + info);

        return info;
    }

}
