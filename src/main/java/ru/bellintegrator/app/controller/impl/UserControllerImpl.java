package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.app.controller.UserController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.model.UserContainer;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности User,
 * а также интерфейса UserEntityController.
 */
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private static final Logger log = Logger.getLogger(UserControllerImpl.class);
    private UserService service;

    public UserControllerImpl(UserService service) {
        log.debug("create UserControllerImpl");
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@RequestParam(value = "user") User user) {
        log.debug("Call create method: user = " + user);
        try {
            service.add(user);
            return "User successfully created!";

        } catch (ServiceException e) {
            log.error("Exception while creating user : ", e);
            return "Exception while creating user!";
        }
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam(value = "user") User user) {
        log.debug("Call update method: user = " + user);
        try {
            service.update(user);
            return "User successfully updated!";

        } catch (ServiceException e) {
            log.error("Exception while updating user : ", e);
            return "Exception while updating user!";
        }
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "user") User user) {
        log.debug("Call delete method: user = " + user);
        try {
            service.delete(user);
            return "User successfully removed!";

        } catch (ServiceException e) {
            log.error("Exception while removing user : ", e);
            return "Exception while removing user!";
        }
    }

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByCredential(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
        log.debug("Call findByCredential method: login = " + login + ", password=" + password);
        try {
            return service.getUserByCredential(login, password);

        } catch (ServiceException e) {
            log.error("Exception while retrieving user by credential: ", e);
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UserContainer list() {
        User principal = getPrincipal();
        return new UserContainer(list(findByCredential(principal.getLogin(), principal.getPassword()).getId()));
    }

    @Override
    @RequestMapping(name = "/user", method = RequestMethod.GET)
    public User findById(@PathVariable int id) {
        User principal = getPrincipal();
        return findById(id, principal.getId());
    }

    @Override
    @RequestMapping("/user")
    public List<User> findByName(@RequestParam(value = "name") String name) {
        User principal = getPrincipal();
        return findByName(name, principal.getId());
    }

    @Override
    public List<User> list(int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);
        try {
            return service.list(ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving user list: ", e);
        }
        return null;
    }

    @Override
    public User findById(int id, int ownerId) {
        log.debug("Call findById method: id = " + id + "ownerId = " + ownerId);
        try {
            return service.findById(id, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving user by id: ", e);
        }
        return null;
    }

    @Override
    public List<User> findByName(String name, int ownerId) {
        log.debug("Call findByName method: name = " + name + "ownerId = " + ownerId);
        try {
            return service.findByName(name, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving user list by name: ", e);
        }
        return null;
    }

}
