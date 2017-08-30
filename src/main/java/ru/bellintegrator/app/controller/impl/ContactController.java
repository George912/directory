package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.EntityController;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности Contact.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController implements EntityController<Contact> {

    private static final Logger log = Logger.getLogger(ContactController.class);

    @Override
    @RequestMapping("/owners/{ownerId}/list")
    public List<Contact> list(@PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/contact/{id}")
    public Contact findById(@PathVariable int id, @PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/owners/{ownerId}/contact/list/{name}")
    public List<Contact> findByName(@PathVariable String name, @PathVariable int ownerId) {
        return null;
    }

    @Override
    @RequestMapping("/add")
    public void create(@RequestParam(value = "group") Contact contact) {

    }

    @Override
    @RequestMapping("/update")
    public void update(@RequestParam(value = "group") Contact contact) {

    }

    @Override
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "group") Contact contact) {

    }

}
