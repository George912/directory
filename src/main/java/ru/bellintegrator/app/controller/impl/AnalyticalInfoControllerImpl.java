package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.AnalyticalInfoController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.model.InfoContainer;
import ru.bellintegrator.app.service.AnalyticalInfoService;

/**
 * Реализация интерфейса AnalyticalInfoController.
 */
@RestController
@Transactional(readOnly = true)
@RequestMapping("/analytic")
public class AnalyticalInfoControllerImpl implements AnalyticalInfoController {

    private static final Logger log = Logger.getLogger(AnalyticalInfoControllerImpl.class);
    private AnalyticalInfo info;

    public AnalyticalInfoControllerImpl(AnalyticalInfoService service) {
        try {
            info = service.collectAnalyticalInfo();

        } catch (ServiceException e) {
            log.error("Exception while collect analytic info: ", e);
        }
    }

    @Override
    @GetMapping("/uc")
    public int userCount() {
        log.debug("Call userCount method");
        log.debug("User count:" + info.getUserCount());
        return info.getUserCount();
    }

    @Override
    @GetMapping("/eucc")
    public InfoContainer eachUserContactCount() {
        log.debug("Call eachUserContactCount method");
        log.debug("eachUserContactCount:" + info.getEachUserContactCount());
        return new InfoContainer(info.getEachUserContactCount());
    }

    @Override
    @GetMapping("/eugc")
    public InfoContainer eachUserGroupCount() {
        log.debug("Call eachUserGroupCount method");
        log.debug("eachUserGroupCount:" + info.getEachUserGroupCount());
        return new InfoContainer(info.getEachUserGroupCount());
    }

    @Override
    @GetMapping("/aucig")
    public double avgUserCountInGroup() {
        log.debug("Call avgUserCountInGroup method");
        log.debug("avgUserCountInGroup:" + info.getAvgUserCountInGroups());
        return info.getAvgUserCountInGroups();
    }

    @Override
    @GetMapping("/iuc")
    public InfoContainer inactiveUserCount() {
        log.debug("Call avgUserContactsCount method");
        log.debug("inactiveUserCount:" + info.getInactiveUserCount());
        return new InfoContainer(info.getInactiveUserCount());
    }

    @Override
    @GetMapping("/aucc")
    public double avgUserContactsCount() {
        log.debug("Call avgUserContactsCount method");
        log.debug("avgUserContactsCount:" + info.getAvgUsersContactCount());
        return info.getAvgUsersContactCount();
    }

}
