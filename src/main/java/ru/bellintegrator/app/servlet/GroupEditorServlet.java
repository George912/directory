package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.EditorAction;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.impl.ContactServiceImpl;
import ru.bellintegrator.app.service.impl.GroupServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupEditorServlet extends AbstractEditorServlet {

    private GroupServiceImpl groupServiceImpl;
    private static final Logger log = Logger.getLogger(GroupEditorServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/groupeditor.jsp");
        groupServiceImpl = new GroupServiceImpl(groupGenericDAO);
        log.debug("Initialize GroupEditorServlet");
        log.info("GroupEditorServlet instance created");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response, int userId, RequestDispatcher dispatcher) {
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        log.info("GroupEditorServlet.update: groupId = " + groupId);

        try {
            Group group = groupServiceImpl.findById(groupId, userId);
            log.debug("Request set attribute group = " + group);
            request.setAttribute("group", group);
            log.debug("Go to /views/groupeditor.jsp");
            dispatcher.include(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            log.error("Exception while update group: ", e);
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
            log.error("Exception while creating group: ", e);

        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response, int userId, EditorAction action) {
        Group group = new Group(0);
        group.setName(request.getParameter("name"));
        group.setNotes(request.getParameter("notes"));
        group.setOwner(new User(userId));

        try {
            if (action == EditorAction.CREATED) {
                groupServiceImpl.add(group);
                log.debug("GroupEditorServlet.insert: action = " + action + ", group=" + group);

            } else if (action == EditorAction.UPDATED) {
                group.setId(Integer.parseInt(request.getParameter("group_id").replace("/", "")));
                groupServiceImpl.update(group);
                log.debug("GroupEditorServlet.insert: action = " + action + ", group=" + group);
            }

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (ServiceException | IOException e) {
            log.error("Exception while saving group into storage: ", e);
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response, int userId) {
        try {
            Group group = new Group(Integer.parseInt(request.getParameter("group_id")));
            group.setOwner(new User(userId));
            groupServiceImpl.delete(group);
            log.debug("GroupEditorServlet.delete: group=" + group);

            log.debug("Go to /userdata");
            response.sendRedirect(request.getContextPath() + "/userdata");

        } catch (IOException | ServiceException e) {
            log.error("Exception while deleting group: ", e);
        }
    }

}
