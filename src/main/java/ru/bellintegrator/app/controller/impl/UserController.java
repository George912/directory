package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.EntityController;
import ru.bellintegrator.app.controller.UserEntityController;
import ru.bellintegrator.app.model.User;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности User,
 * а также интерфейса UserEntityController.
 */
@RestController
@RequestMapping("/users")
public class UserController implements EntityController<User>, UserEntityController {

    private static final Logger log = Logger.getLogger(UserController.class);

    @Override
    @RequestMapping("/owners/{ownerId}/list")
    public List<User> list(@PathVariable int ownerId) {
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

    //todo: use post for retrieve
    @Override
    @RequestMapping("/userByCredential")
    public User findByCredential(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
        return null;
    }

}
