package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractServlet {

    private UserService userService;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userService = new UserService(userGenericDAO);
        log.debug("Initialize LoginServlet");
        log.info("LoginServlet instance created");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        try {
            User user = userService.getUserByCredential(req.getParameter("login"), req.getParameter("password"));

            log.debug("LoginServlet.service: User credentials:" + user);

            System.out.println("groups" + groupGenericDAO.getAll(1));
            System.out.println("contacts" + contactGenericDAO.getAll(1));

            if (user != null) {
                log.debug("Request set attribute userId = " + user.getId());
                req.getSession().setAttribute("userId", user.getId());
                dispatcher = context.getRequestDispatcher("/userdata");
                log.debug("Go to /userdata");
                dispatcher.include(req, resp);

            } else {
                dispatcher = context.getRequestDispatcher("/views/login.jsp");
                log.debug("Go to views/login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (Exception e) {
            log.error("Exception while authenticate user:" + e);
        }
    }

}
