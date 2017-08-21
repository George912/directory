package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.viewmodel.EditorAction;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupEditorServlet extends HttpServlet {

    private DAOFactoryType daoFactoryType;
    private DAOFactory daoFactory;
    private GenericDAO<Contact> contactGenericDAO;
    private GenericDAO<Group> groupGenericDAO;
    private ContactService contactService;
    private GroupService groupService;

    @Override
    public void init() throws ServletException {
        daoFactoryType = DAOFactoryType.SQL_POSTGRESQL;
        daoFactory = DAOFactory.getDAOFactory(daoFactoryType);

        try {
            contactGenericDAO = daoFactory.getContactDAO();
            groupGenericDAO = daoFactory.getGroupDAO();
            contactService = new ContactService(contactGenericDAO);
            groupService = new GroupService(groupGenericDAO, contactService);
            contactService.setGroupService(groupService);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;
        dispatcher = context.getRequestDispatcher("/views/groupeditor.jsp");
        Group group;
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("action") == null ? (String) req.getAttribute("action") : req.getParameter("action");
        EditorAction action = EditorAction.getActionFromString(act);

        switch (action) {
            case CREATE:
                System.out.println("CREATE");
                group = new Group(0, "g1", "g1", userId);
                req.setAttribute("group", group);
                dispatcher.include(req, resp);

            case CREATED:
                System.out.println("CREATED");
                group = (Group) req.getAttribute("group");
                System.out.println(req.getParameter("name"));
                System.out.println(req.getParameter("notes"));
                group.setName(req.getParameter("name"));
                group.setNotes(req.getParameter("notes"));

                try {
                    groupService.addGroup(group);
                    dispatcher = context.getRequestDispatcher("/views/main.jsp");
                    dispatcher.include(req, resp);
                } catch (DAOException e) {
                    e.printStackTrace();
                }

            case UPDATE:
                System.out.println("UPDATE");
                int groupId = Integer.parseInt(req.getParameter("group_id"));

                try {
                    group = groupService.getGroupById(groupId, userId);
                    req.setAttribute("group", group);
                    dispatcher.include(req, resp);

                } catch (DAOException e) {
                    e.printStackTrace();
                }

            case UPDATED:
                System.out.println("UPDATED");
                group = (Group) req.getAttribute("group");
                System.out.println(req.getParameter("name"));
                System.out.println(req.getParameter("notes"));
                group.setName(req.getParameter("name"));
                group.setNotes(req.getParameter("notes"));
                System.out.println(group);

                try {
                    groupService.updateGroup(group);
                    List<Group> groups = groupService.getAllGroups(userId);
                    req.setAttribute("groupList", groups);
                    req.setAttribute("owner", userId);

                    dispatcher = context.getRequestDispatcher("/userdata");
                    dispatcher.include(req, resp);

                } catch (DAOException e) {
                    e.printStackTrace();
                }
                break;

            case UNKNOWN:
                break;

            default:
                break;

        }
    }
}
