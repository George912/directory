package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private DAOFactory daoFactory;
    private GenericDAO<User> userGenericDAO;
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);


    @Override
    public void init() throws ServletException {
        daoFactory = DAOFactory.getDAOFactory();

        try {
            userGenericDAO = daoFactory.getUserDAO();
            userService = new UserService(userGenericDAO);

        } catch (DAOException e) {
            log.debug("Exception while init LoginServlet: " + e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;

        try {
            User user = userService.getUserByCredential(req.getParameter("login"), req.getParameter("password"));

            if (user != null) {
                req.getSession().setAttribute("userId", user.getId());
                dispatcher = context.getRequestDispatcher("/userdata");
                dispatcher.include(req, resp);

            } else {
                dispatcher = context.getRequestDispatcher("/views/login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (DAOException e) {
            log.debug("Exception while authenticate user:" + e);
        }
    }
}
