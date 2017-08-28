package ru.bellintegrator.app.servlet;

import ru.bellintegrator.app.EditorAction;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupEditorServlet extends AbstractEditorServlet {

    private ContactService contactService;
    private GroupService groupService;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GroupEditorServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/groupeditor.jsp");
        contactService = new ContactService(contactGenericDAO);
        groupService = new GroupService(groupGenericDAO, contactService);
        contactService.setGroupService(groupService);
        log.debug("Initialize GroupEditorServlet");
        log.info("GroupEditorServlet instance created");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        log.info("GroupEditorServlet.update: groupId = " + groupId);

        try {
            Group group = groupService.getGroupById(groupId, userId);
            log.debug("Request set attribute group = " + group);
            request.setAttribute("group", group);
            log.debug("Go to /views/groupeditor.jsp");
            dispatcher.include(request, response);

        } catch (DAOException | ServletException | IOException e) {
            log.error("Exception while update group: " + e);
        }
    }
    protected void create(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        Group group = new Group(0, "", "", userId);
        request.setAttribute("group", group);

        log.info("GroupEditorServlet.create: ownerId = " + userId);

        try {
            log.debug("Go to /views/groupeditor.jsp");
            dispatcher.include(request, response);

        } catch (ServletException | IOException e) {
            log.error("Exception while creating group: " + e);

        }
    }
    protected void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Group group = new Group(0);
        group.setName(request.getParameter("name"));
        group.setNotes(request.getParameter("notes"));
        group.setOwner(new User(userId));

        try {
            if (action == EditorAction.CREATED) {
                groupService.addGroup(group);
                log.debug("GroupEditorServlet.insert: action = " + action + ", group=" + group);

            } else if (action == EditorAction.UPDATED) {
                group.setId(Integer.parseInt(request.getParameter("group_id")));
                groupService.updateGroup(group);
                log.debug("GroupEditorServlet.insert: action = " + action + ", group=" + group);
            }

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (DAOException | IOException e) {
            log.error("Exception while saving group into storage: " + e);
        }
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Group group = new Group(Integer.parseInt(request.getParameter("group_id")));
            group.setOwner(new User(userId));
            groupService.deleteGroup(group, userId);
            log.debug("GroupEditorServlet.delete: group=" + group);

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | DAOException e) {
            log.error("Exception while deleting group: " + e);
        }
    }

}
