package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractServlet {

    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userService = new UserService(userGenericDAO);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        try {
            User user = new User(1);

            DAOFactory factory = DAOFactory.getDAOFactory();
            GenericDAO<User> userDAO = factory.getUserDAO();
            GenericDAO<Group> groupDAO = factory.getGroupDAO();
            GenericDAO<Contact> contactDao = factory.getContactDAO();

//            System.out.println("getAll:" + groupDAO.getAll(1));
//            System.out.println("getById:" + groupDAO.getById(1, 1));
//            System.out.println("getByName:" + groupDAO.getByName("g1", 1));

//            System.out.println("contactDao.getAll:" + contactDao.getAll(1));
            userDAO.getByName("fn7", 1);

//            groupDAO.delete(group);

            if (user == null) {
                req.getSession().setAttribute("userId", user.getId());
                dispatcher = context.getRequestDispatcher("/userdata");
                dispatcher.include(req, resp);

            } else {
                dispatcher = context.getRequestDispatcher("/views/login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (Exception e) {
            log.debug("Exception while authenticate user:" + e);
            e.printStackTrace();
        }
    }

}
