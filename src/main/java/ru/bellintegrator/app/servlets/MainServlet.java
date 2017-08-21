package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

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

        int userId = (int) req.getSession().getAttribute("userId");

        List<Group> groups = null;
        List<Contact> contacts = null;

        try {
            groups = groupService.getAllGroups(userId);
            contacts = contactService.getAllContacts(userId);
            req.setAttribute("contactList", contacts);
            req.setAttribute("groupList", groups);

            dispatcher = context.getRequestDispatcher("/views/main.jsp");
            dispatcher.include(req, resp);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
