package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.impl.sql.ContactLinkGroupDao;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.EditorAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactEditorServlet extends HttpServlet {

    private DAOFactory daoFactory;
    private GenericDAO<Contact> contactGenericDAO;
    private GenericDAO<Group> groupGenericDAO;
    private ContactLinkGroupDao linkGroupDao;
    private ContactService contactService;
    private GroupService groupService;
    private static final Logger log = LoggerFactory.getLogger(ContactEditorServlet.class);

    @Override
    public void init() throws ServletException {
        daoFactory = DAOFactory.getDAOFactory();

        try {
            contactGenericDAO = daoFactory.getContactDAO();
            groupGenericDAO = daoFactory.getGroupDAO();
            contactService = new ContactService(contactGenericDAO);
            linkGroupDao = daoFactory.getContactLinkGroupDao();
            groupService = new GroupService(groupGenericDAO, contactService, linkGroupDao);
            contactService.setGroupService(groupService);

        } catch (DAOException e) {
            log.debug("Exception while init ContactEditorServlet: " + e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/contacteditor.jsp");
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("action") == null ? (String) req.getAttribute("action") : req.getParameter("action");
        EditorAction action = EditorAction.getActionFromString(act);

        System.out.println("Act:" + action);

        switch (action) {
            case CREATE:
                create(req, resp, userId, dispatcher);
                break;

            case CREATED:
                insert(req, resp, userId, action);
                break;

            case UPDATE:
                update(req, resp, userId, dispatcher);
                break;

            case UPDATED:
                insert(req, resp, userId, action);
                break;

            case DELETE:
                delete(req, resp, userId);
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/userdata");
                break;
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        int contactId = Integer.parseInt(request.getParameter("contact_id"));

        try {
            Contact contact = contactService.getContactById(contactId, userId);
            request.setAttribute("contact", contact);
            dispatcher.include(request, response);

        } catch (DAOException | ServletException | IOException e) {
            log.debug("Exception while update contact: " + e);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        Contact contact = new Contact(userId);

        try {
            List<Group> groups = groupService.getAllGroups(userId);

            request.setAttribute("groups", groups);
            request.setAttribute("contact", contact);

            dispatcher.include(request, response);

        } catch (IOException | ServletException | DAOException e) {
            log.debug("Exception while creating contact: " + e);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Contact contact = new Contact(userId);
        contact.setEmail(request.getParameter("email"));
        contact.setFirstName(request.getParameter("name"));
        contact.setFirstPhoneNumber(request.getParameter("phone1"));
        contact.setFirstPhoneNumberType(PhoneNumberType.getTypeFromString(request.getParameter("phone1_type")));
        contact.setLastName(request.getParameter("last_name"));
        contact.setMiddleName(request.getParameter("middle_name"));
        contact.setNotes(request.getParameter("notes"));
        contact.setSecondPhoneNumber(request.getParameter("phone2"));
        contact.setSecondPhoneNumberType(PhoneNumberType.getTypeFromString(request.getParameter("phone2_type")));

        try {
            if (action == EditorAction.CREATED) {
                contactService.addContact(contact);

                //получение идентификатора добаленного контакта
                List<Contact> contactList = contactService.getAllContacts(userId);
                int contactId = contactList.get(contactList.size() - 1).getId();
                contact.setId(contactId);

                //получение групп и добавление их в контакт
                String groups[] = request.getParameterValues("group");
                if (groups != null) {
                    for (String id : groups) {
                        Group group = groupService.getGroupById(Integer.parseInt(id), userId);
                        groupService.addGroupToContact(group, contact);
                    }
                }

            } else if (action == EditorAction.UPDATED) {
                contact.setId(Integer.parseInt(request.getParameter("contact_id")));
                contactService.updateContact(contact);
            }

            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (DAOException | IOException e) {
            log.debug("Exception while saving contact into storage: " + e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Contact contact = new Contact(Integer.parseInt(request.getParameter("contact_id")), "", "", "");
            contact.setOwner(userId);
            contactService.deleteContact(contact);

            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | DAOException e) {
            log.debug("Exception while deleting contact: " + e);
        }
    }

}
