package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.EditorAction;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.impl.ContactServiceImpl;
import ru.bellintegrator.app.service.impl.GroupServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactEditorServlet extends AbstractEditorServlet {

    private ContactServiceImpl contactServiceImpl;
    private GroupServiceImpl groupServiceImpl;
    private static final Logger log = Logger.getLogger(ContactEditorServlet.class);

    @Override
    public void init() throws ServletException {
        contactServiceImpl = new ContactServiceImpl(contactGenericDAO);
        groupServiceImpl = new GroupServiceImpl(groupGenericDAO, contactServiceImpl);
        contactServiceImpl.setGroupServiceImpl(groupServiceImpl);
        dispatcher = this.getServletContext().getRequestDispatcher("/views/contacteditor.jsp");
        log.debug("Initialize ContactEditorServlet");
        log.info("ContactEditorServlet instance created");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        int contactId = Integer.parseInt(request.getParameter("contact_id"));

        log.info("ContactEditorServlet.update: contactId = " + contactId);

        try {
            Contact contact = contactServiceImpl.findById(contactId, userId);
            log.debug("Request set attribute contact = " + contact);
            request.setAttribute("contact", contact);
            log.debug("Go to /views/contacteditor.jsp");
            dispatcher.include(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            log.error("Exception while update contact: ", e);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        Contact contact = new Contact(userId);

        log.info("ContactEditorServlet.create: ownerId = " + userId);

        try {
            List<Group> groups = groupServiceImpl.list(userId);

            log.debug("Request set attribute groups = " + groups);
            request.setAttribute("groups", groups);
            log.debug("Request set attribute contact = " + contact);
            request.setAttribute("contact", contact);

            log.debug("Go to /views/contacteditor.jsp");
            dispatcher.include(request, response);

        } catch (IOException | ServletException | ServiceException e) {
            log.error("Exception while creating contact: ", e);
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Contact contact = new Contact(userId);
        contact.setEmail(request.getParameter("email"));
        contact.setFirstName(request.getParameter("name"));
        contact.setFirstPhoneNumber(request.getParameter("phone1"));
        contact.setFirstPhoneNumberType(request.getParameter("phone1_type"));
        contact.setLastName(request.getParameter("last_name"));
        contact.setMiddleName(request.getParameter("middle_name"));
        contact.setNotes(request.getParameter("notes"));
        contact.setSecondPhoneNumber(request.getParameter("phone2"));
        contact.setSecondPhoneNumberType(request.getParameter("phone2_type"));


        try {
            if (action == EditorAction.CREATED) {
                //получение идентификатора добаленного контакта
                List<Contact> contactList = contactServiceImpl.list(userId);
                int contactId = contactList.get(contactList.size() - 1).getId();
                contact.setId(contactId);

                //получение групп и добавление их в контакт
                String groups[] = request.getParameterValues("group");
                List<Group> groupList = new ArrayList<>();
                if (groups != null) {
                    for (String id : groups) {
                        Group group = groupServiceImpl.findById(Integer.parseInt(id), userId);
                        groupList.add(group);
                    }
                }

                contact.setGroupList(groupList);
                contact.setOwner(new User(userId));
                contactServiceImpl.add(contact);

                log.debug("ContactEditorServlet.insert: action = " + action + ", contact=" + contact);

            } else if (action == EditorAction.UPDATED) {
                contact.setId(Integer.parseInt(request.getParameter("contact_id")));
                contact.setOwner(new User(userId));
                contactServiceImpl.update(contact);
                log.debug("ContactEditorServlet.insert: action = " + action + ", contact=" + contact);
            }

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (ServiceException | IOException e) {
            log.error("Exception while saving contact into storage: ", e);
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Contact contact = new Contact(Integer.parseInt(request.getParameter("contact_id")), "", "", "");
            contact.setOwner(new User(userId));
            contactServiceImpl.delete(contact);
            log.debug("ContactEditorServlet.delete: contact=" + contact);

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | ServiceException e) {
            log.error("Exception while deleting contact: ", e);
        }
    }

}
