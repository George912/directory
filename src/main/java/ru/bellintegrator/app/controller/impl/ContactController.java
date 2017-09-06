package ru.bellintegrator.app.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.app.controller.GenericController;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.User;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Contact> list() {
        User principal = getPrincipal();
        return list(principal.getId());
    }

    @RequestMapping(name = "/contact", method = RequestMethod.GET)
    public Contact findById(@RequestParam(value = "id") int id) {
        User principal = getPrincipal();
        return findById(id, principal.getId());
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public List<Contact> findByName(@RequestParam(value = "name") String name) {
        User principal = getPrincipal();
        return findByName(name, principal.getId());
    }

    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@RequestParam(value = "contact") Contact contact) {
        log.debug("Call create method: contact = " + contact);
        try {
            service.add(contact);
            return "Contact successfully created!";

        } catch (ServiceException e) {
            log.error("Exception while creating contact : ", e);
            return "Exception while creating contact!";
        }
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam(value = "contact") Contact contact) {
        log.debug("Call update method: contact = " + contact);
        try {
            service.update(contact);
            return "Contact successfully updated!";

        } catch (ServiceException e) {
            log.error("Exception while updating contact : ", e);
            return "Exception while updating contact!";
        }
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "contact") Contact contact) {
        log.debug("Call delete method: contact = " + contact);
        try {
            service.delete(contact);
            return "Contact successfully removed!";

        } catch (ServiceException e) {
            log.error("Exception while removing contact : ", e);
            return "Exception while removing contact!";
        }
    }

    @Override
    public List<Contact> list(int ownerId) {
        log.debug("Call list method: ownerId = " + ownerId);
        try {
            return service.list(ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving contact list: ", e);
        }
        return null;
    }

    @Override
    public Contact findById(int id, int ownerId) {
        log.debug("Call findById method: id = " + id + "ownerId = " + ownerId);
        try {
            return service.findById(id, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving contact by id: ", e);
        }
        return null;
    }

    @Override
    public List<Contact> findByName(String name, int ownerId) {
        log.debug("Call findByName method: name = " + name + "ownerId = " + ownerId);
        try {
            return service.findByName(name, ownerId);

        } catch (ServiceException e) {
            log.error("Exception while retrieving contact list by name: ", e);
        }
        return null;
    }

}
