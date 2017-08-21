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
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.EditorAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupEditorServlet extends HttpServlet {

    private DAOFactory daoFactory;
    private GenericDAO<Contact> contactGenericDAO;
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;
    private ContactLinkGroupDao linkGroupDao;
    private GroupService groupService;
    private static final Logger log = LoggerFactory.getLogger(GroupEditorServlet.class);

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
            log.debug("Exception while init GroupEditorServlet: " + e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/groupeditor.jsp");
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("action") == null ? (String) req.getAttribute("action") : req.getParameter("action");
        EditorAction action = EditorAction.getActionFromString(act);

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
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        try {
            Group group = groupService.getGroupById(groupId, userId);
            request.setAttribute("group", group);
            dispatcher.include(request, response);

        } catch (DAOException | ServletException | IOException e) {
            log.debug("Exception while update group: " + e);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        Group group = new Group(0, "", "", userId);
        request.setAttribute("group", group);

        try {
            dispatcher.include(request, response);

        } catch (ServletException | IOException e) {
            log.debug("Exception while creating group: " + e);

        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Group group = new Group(0);
        group.setName(request.getParameter("name"));
        group.setNotes(request.getParameter("notes"));
        group.setOwner(userId);

        try {
            if (action == EditorAction.CREATED) {
                groupService.addGroup(group);

            } else if (action == EditorAction.UPDATED) {
                group.setId(Integer.parseInt(request.getParameter("group_id")));
                groupService.updateGroup(group);
            }

            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (DAOException | IOException e) {
            log.debug("Exception while saving group into storage: " + e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Group group = new Group(Integer.parseInt(request.getParameter("group_id")));
            group.setOwner(userId);
            groupService.deleteGroup(group, userId);

            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | DAOException e) {
            log.debug("Exception while deleting group: " + e);
        }
    }

}
