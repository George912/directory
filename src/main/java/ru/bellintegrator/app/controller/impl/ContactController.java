package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.service.ContactService;

import java.util.List;

/**
 * Реализация интерфейса EntityController для сущности Contact.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController implements GenericController<Contact> {

    private static final Logger log = Logger.getLogger(ContactController.class);
    private ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

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
    public String create(@RequestParam(value = "group") Contact contact) {
        return "";
    }

    @Override
    @RequestMapping("/update")
    public String update(@RequestParam(value = "group") Contact contact) {

        return null;
    }

    @Override
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "group") Contact contact) {

        return null;
    }

}
