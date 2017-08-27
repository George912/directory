package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends AbstractServlet {

    private ContactService contactService;
    private GroupService groupService;
    private static final Logger log = LoggerFactory.getLogger(MainServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/main.jsp");
        contactService = new ContactService(contactGenericDAO);
        groupService = new GroupService(groupGenericDAO, contactService);
        contactService.setGroupService(groupService);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("find");
        List<Group> groups = null;
        List<Contact> contacts = null;

        try {
            if ("contacts".equals(act)) {
                groups = groupService.getAllGroups(userId);
                String name = req.getParameter("contact_name");
                contacts = contactService.getContactsByName(name, userId);

            } else if ("groups".equals(act)) {
                contacts = contactService.getAllContacts(userId);
                String name = req.getParameter("group_name");
                groups = groupService.getGroupsByName(name, userId);

            } else {
                groups = groupService.getAllGroups(userId);
                contacts = contactService.getAllContacts(userId);
            }

            req.setAttribute("contactList", contacts);
            req.setAttribute("groupList", groups);

            dispatcher.include(req, resp);

        } catch (DAOException e) {
            log.debug("Exception in MainServlet:" + e);
        }
    }

}
