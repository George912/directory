package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.controller.UserController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности User,
 * а также интерфейса UserEntityController.
 */
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements GenericController<User>, UserController {

    private static final Logger log = Logger.getLogger(UserControllerImpl.class);
    private UserService service;

    public UserControllerImpl(UserService service) {
        log.debug("create UserControllerImpl");
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/owners/{ownerId}/list", method = RequestMethod.GET)
    public List<User> list(@PathVariable int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);

        try {
            System.out.println("List:" + service.list(0));

        } catch (ServiceException e) {
            log.error("Exception while retrieving user list: ", e);
        }
        return null;
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
    @RequestMapping("/add")
    public void create(@RequestParam(value = "user") User user) {

    }

    @Override
    @RequestMapping("/update")
    public void update(@RequestParam(value = "user") User user) {

    }

    @Override
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "user") User user) {

    }

    @Override
    @RequestMapping("/userByCredential")
    public User findByCredential(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
        log.debug("Call findByCredential method: login = " + login + ", password=" + password);
        try {
            return service.getUserByCredential(login, password);

        } catch (ServiceException e) {
            log.error("Exception while retrieving user by credential: ", e);
        }

        return null;
    }

}
