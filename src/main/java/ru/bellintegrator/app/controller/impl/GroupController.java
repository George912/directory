package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.GroupService;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности Group.
 */
@RestController
@RequestMapping("/groups")
public class GroupController implements GenericController<Group> {

    private static final Logger log = Logger.getLogger(GroupController.class);
    private GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/list")
    public List<Group> list(@PathVariable int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);
        try {
            log.debug("list:" + service.list(ownerId));

        } catch (ServiceException e) {
            log.error("Exception while retrieving group list: ", e);
        }
        return null;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/group/{id}")
    public Group findById(@PathVariable int id, @PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/group/list/{name}")
    public List<Group> findByName(@PathVariable String name, @PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/add")
    public void create(@RequestParam(value = "group") Group group) {

    }

    @Override
    @RequestMapping("/update")
    public void update(@RequestParam(value = "group") Group group) {

    }

    @Override
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "group") Group group) {

    }

}
