package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.AnalyticalInfoController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.AnalyticalInfoService;

import java.util.Map;

/**
 * Реализация интерфейса AnalyticalInfoController.
 */
@RestController
@RequestMapping("/analytic")
public class AnalyticalInfoControllerImpl implements AnalyticalInfoController {

    private static final Logger log = Logger.getLogger(AnalyticalInfoControllerImpl.class);
    private AnalyticalInfoService service;
    private AnalyticalInfo info;

    public AnalyticalInfoControllerImpl(AnalyticalInfoService service) {
        this.service = service;
        try {
            info = service.collectAnalyticalInfo();

        } catch (ServiceException e) {
            log.error("Exception while collect analytic info: ", e);
        }
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/uc")
    public int userCount() {
        log.debug("Call userCount method");
        log.debug("User count:" + info.getUserCount());
        return info.getUserCount();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/eucc")
    public Map<Integer, Long> eachUserContactCount() {
        log.debug("Call eachUserContactCount method");
        log.debug("eachUserContactCount:" + info.getEachUserContactCount());
        return info.getEachUserContactCount();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/eugc")
    public Map<Integer, Long> eachUserGroupCount() {
        log.debug("Call eachUserGroupCount method");
        log.debug("eachUserGroupCount:" + info.getEachUserGroupCount());
        return info.getEachUserGroupCount();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/aucig")
    public double avgUserCountInGroup() {
        log.debug("Call avgUserCountInGroup method");
        log.debug("eachUserGroupCount:" + info.getAvgUserCountInGroups());
        return info.getAvgUserCountInGroups();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/iu")
    public Map<Integer, Long> inactiveUserCount() {
        log.debug("Call avgUserContactsCount method");
        log.debug("inactiveUserCount:" + info.getInactiveUserCount());
        return info.getInactiveUserCount();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/aucc")
    public double avgUserContactsCount() {
        log.debug("Call avgUserContactsCount method");
        log.debug("avgUserContactsCount:" + info.getAvgUsersContactCount());
        return info.getAvgUsersContactCount();
    }

}
