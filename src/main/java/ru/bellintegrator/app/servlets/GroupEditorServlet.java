package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.EditorAction;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
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
    private static final Logger log = LoggerFactory.getLogger(GroupEditorServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/groupeditor.jsp");
        contactService = new ContactService(contactGenericDAO);
        groupService = new GroupService(groupGenericDAO, contactService, linkGroupDao);
        contactService.setGroupService(groupService);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        try {
            Group group = groupService.getGroupById(groupId, userId);
            request.setAttribute("group", group);
            dispatcher.include(request, response);

        } catch (DAOException | ServletException | IOException e) {
            log.debug("Exception while update group: " + e);
        }
    }
    protected void create(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        Group group = new Group(0, "", "", userId);
        request.setAttribute("group", group);

        try {
            dispatcher.include(request, response);

        } catch (ServletException | IOException e) {
            log.debug("Exception while creating group: " + e);

        }
    }
    protected void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Group group = new Group(0);
        group.setName(request.getParameter("name"));
        group.setNotes(request.getParameter("notes"));
//        group.setOwner(userId);

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
    protected void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Group group = new Group(Integer.parseInt(request.getParameter("group_id")));
//            group.setOwner(userId);
            groupService.deleteGroup(group, userId);

            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | DAOException e) {
            log.debug("Exception while deleting group: " + e);
        }
    }

}
