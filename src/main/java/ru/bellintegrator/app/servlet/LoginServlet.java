package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractServlet {

    private UserServiceImpl userServiceImpl;
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userServiceImpl = new UserServiceImpl(userDAO);
        log.debug("Initialize LoginServlet");
        log.info("LoginServlet instance created");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        try {
            User user = userServiceImpl.getUserByCredential(req.getParameter("username"), req.getParameter("password"));

            log.debug("LoginServlet.service: User credentials:" + user);

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
            log.error("Exception while authenticate user:", e);
        }
    }

}
