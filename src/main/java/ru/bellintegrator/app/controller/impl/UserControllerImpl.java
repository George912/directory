package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.app.controller.UserController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.model.UserContainer;
import ru.bellintegrator.app.service.UserService;

import java.util.ArrayList;
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
    public List<User> list(int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);

        try {
            System.out.println("List:" + service.list(0));

        } catch (ServiceException e) {
            log.error("Exception while retrieving user list: ", e);
        }
        List<User> users = new ArrayList<>();
        users.add(new User(0, "l1", "p1", "qwe", "qwe", "qwe"));
        return users;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/user/{id}")
    public User findById(@PathVariable int id, @PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/user/list/{name}")
    public List<User> findByName(@PathVariable String name, @PathVariable int ownerId) {
        return null;
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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/userByCredential", method = RequestMethod.GET)
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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UserContainer list() {
        return new UserContainer(list(0));
    }

}
