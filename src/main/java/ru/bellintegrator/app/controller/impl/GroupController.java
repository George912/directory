package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.GroupContainer;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности Group.
 */
@RestController
@RequestMapping("/groups")
public class GroupController implements GenericController<Group> {

    private static final Logger log = Logger.getLogger(GroupController.class);
    private GroupService service;
    private UserService userService;

    public GroupController(GroupService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public GroupContainer list() {
        User principal = getPrincipal(userService);
        return new GroupContainer(list(principal.getId()));
    }

    @GetMapping(value = "/groupById")
    public Group findById(@RequestParam(value = "id") int id) {
        User principal = getPrincipal(userService);
        return findById(id, principal.getId());
    }

        @GetMapping(value = "/groupByName")
    public GroupContainer findByName(@RequestParam(value = "name") String name) {
        User principal = getPrincipal(userService);
        return new GroupContainer(findByName(name, principal.getId()));
    }

    @Override
    @PostMapping(value = "/add")
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
    @PutMapping(value = "/update")
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
    @DeleteMapping(value = "/delete")
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
            return service.list(ownerId);

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
    public List<Group> findByName(String name, int ownerId) {
        log.debug("Call findByName method: name = " + name + "ownerId = " + ownerId);
        try {
            return service.findByName(name, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving group list by name: ", e);
        }
        return null;
    }

}
