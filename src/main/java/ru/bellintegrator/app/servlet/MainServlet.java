package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.impl.ContactServiceImpl;
import ru.bellintegrator.app.service.impl.GroupServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends AbstractServlet {

    private ContactServiceImpl contactServiceImpl;
    private GroupServiceImpl groupServiceImpl;
    private static final Logger log = Logger.getLogger(MainServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/main.jsp");
        contactServiceImpl = new ContactServiceImpl(contactGenericDAO);
        groupServiceImpl = new GroupServiceImpl(groupGenericDAO);
        log.debug("Initialize MainServlet");
        log.info("MainServlet instance created");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("find");
        List<Group> groups = null;
        List<Contact> contacts = null;

        log.debug("MainServlet.service: request params [" + "userId=" + userId + ", act=" + act + "]");

        try {
            if ("contacts".equals(act)) {
                groups = groupServiceImpl.list(userId);
                String name = req.getParameter("contact_name");
                contacts = findContactByName(name, userId);

            } else if ("groups".equals(act)) {
                contacts = contactServiceImpl.list(userId);
                String name = req.getParameter("group_name");
                groups = findGroupByName(name, userId);

            } else {
                groups = groupServiceImpl.list(userId);
                contacts = contactServiceImpl.list(userId);
            }

            log.debug("Request set attribute contactList = " + contacts);
            req.setAttribute("contactList", contacts);
            log.debug("Request set attribute groupList = " + groups);
            req.setAttribute("groupList", groups);

            log.debug("Go to /views/main.jsp");

            dispatcher.include(req, resp);

        } catch (ServiceException e) {
            log.error("Exception in MainServlet:", e);
        }
    }

    private List<Group> findGroupByName(String name, int ownerId) {
        List<Group> groups = new ArrayList<>();
        try {
            for (Group group : groupServiceImpl.list(ownerId)) {
                if (group.getName().contains(name)) {
                    groups.add(group);
                }
            }

        } catch (ServiceException e) {
            log.error("Exception while find groups by name:", e);
        }
        return groups;
    }

    private List<Contact> findContactByName(String name, int ownerId) {
        List<Contact> contacts = new ArrayList<>();
        try {
            for (Contact contact : contactServiceImpl.list(ownerId)) {
                if(contact.getFirstName().contains(name)){
                    contacts.add(contact);
                }
            }
        } catch (ServiceException e) {
            log.error("Exception while find contacts by name:", e);
        }
        return contacts;
    }
}
