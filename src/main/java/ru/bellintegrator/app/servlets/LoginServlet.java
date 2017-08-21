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
import ru.bellintegrator.app.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

   private DAOFactoryType daoFactoryType;
   private DAOFactory daoFactory;
   private GenericDAO<User> userGenericDAO;
   private UserService userService;

    @Override
    public void init() throws ServletException {
        daoFactoryType = DAOFactoryType.SQL_POSTGRESQL;
        daoFactory = DAOFactory.getDAOFactory(daoFactoryType);

        try {
            userGenericDAO = daoFactory.getUserDAO();
            userService = new UserService(userGenericDAO);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;

        try {
            System.out.println(req.getParameter("login") + "|" + req.getParameter("password"));
            User user = userService.getUserByCredential(req.getParameter("login"), req.getParameter("password"));

            if (user != null) {
                req.getSession().setAttribute("userId", user.getId());
                dispatcher = context.getRequestDispatcher("/userdata");
//                req.setAttribute("user", user);
                dispatcher.include(req, resp);

            }else{
                dispatcher = context.getRequestDispatcher("/views/login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
