package ru.bellintegrator.app.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.AnalyticalInfoService;

@Service("analyticalInfoService")
public class AnalyticalInfoServiceImpl implements AnalyticalInfoService{

    private AnalyticalInfoDAO dao;
    private static final Logger log = Logger.getLogger(AnalyticalInfoServiceImpl.class);

    public AnalyticalInfoServiceImpl(AnalyticalInfoDAO dao) {
        this.dao = dao;
        log.debug("Initialize AnalyticalInfoService");
        log.info("AnalyticalInfoService instance created");
    }

    @Override
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
