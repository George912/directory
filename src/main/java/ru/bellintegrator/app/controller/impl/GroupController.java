package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Group> list() {
        User principal = getPrincipal();
        return list(principal.getId());
    }

    @RequestMapping(name = "/group", method = RequestMethod.GET)
    public Group findById(@RequestParam(value = "id") int id) {
        User principal = getPrincipal();
        return findById(id, principal.getId());
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Group> findByName(@RequestParam(value = "name") String name) {
        User principal = getPrincipal();
        return findByName(name, principal.getId());
    }

    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@RequestParam(value = "group") Group group) {
        log.debug("Call create method: group = " + group);
        try {
            service.add(group);
            return "Group successfully created!";

        } catch (ServiceException e) {
            log.error("Exception while creating group : ", e);
            return "Exception while creating group!";
        }
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam(value = "group") Group group) {
        log.debug("Call update method: group = " + group);
        try {
            service.update(group);
            return "Group successfully updated!";

        } catch (ServiceException e) {
            log.error("Exception while updating group : ", e);
            return "Exception while updating group!";
        }
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "group") Group group) {
        log.debug("Call delete method: contact = " + group);
        try {
            service.delete(group);
            return "Group successfully removed!";

        } catch (ServiceException e) {
            log.error("Exception while removing group : ", e);
            return "Exception while removing group!";
        }
    }

    @Override
    public List<Group> list(int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);
        try {
            log.debug("list:" + service.list(ownerId));

        } catch (ServiceException e) {
            log.error("Exception while retrieving group list: ", e);
        }
        return null;
    }

    @Override
    public Group findById(int id, int ownerId) {
        log.debug("Call findById method: id = " + id + "ownerId = " + ownerId);
        try {
            return service.findById(id, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving group by id: ", e);
        }
        return null;
    }

    @Override
    public List<Group> findByName(@PathVariable String name, @PathVariable int ownerId) {
        log.debug("Call findByName method: name = " + name + "ownerId = " + ownerId);
        try {
            return service.findByName(name, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving group list by name: ", e);
        }
        return null;
    }

}
