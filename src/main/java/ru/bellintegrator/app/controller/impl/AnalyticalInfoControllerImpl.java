package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.AnalyticalInfoController;
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

    public AnalyticalInfoControllerImpl(AnalyticalInfoService service) {
        this.service = service;
    }

    @Override
    @RequestMapping("/uc")
    public int userCount() {
        return 0;
    }

    @Override
    @RequestMapping("/eucc")
    public Map<Integer, Long> eachUserContactCount() {
        return null;
    }

    @Override
    @RequestMapping("/eugc")
    public Map<Integer, Long> eachUserGroupCount() {
        return null;
    }

    @Override
    @RequestMapping("/aucig")
    public double avgUserCountInGroup() {
        return 0;
    }

    @Override
    @RequestMapping("/iu")
    public Map<Integer, Long> inactiveUserCount() {
        return null;
    }

    @Override
    @RequestMapping("/aucc")
    public double avgUserContactsCount() {
        return 0;
    }

}
