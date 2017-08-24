package ru.bellintegrator.app.servlets;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//            User user = userService.getUserByCredential(req.getParameter("login"), req.getParameter("password"));
            User user = new User();
            user.setLogin("l3333333");

            Group group = new Group();
            group.setName("g1");
            group.setOwner(1);

            Contact contact = new Contact();
            contact.setOwner(1);
            contact.setFirstName("newContact");

            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Group.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            Session session = sessionFactory.openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                System.out.println("User id:"+session.save(user));
                System.out.println("Group id:"+session.save(group));
                System.out.println("Contact id:"+session.save(contact));
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();

            } finally {
                session.close();
            }

            if (user != null) {
                req.getSession().setAttribute("userId", user.getId());
                dispatcher = context.getRequestDispatcher("/userdata");
                dispatcher.include(req, resp);

            } else {
                dispatcher = context.getRequestDispatcher("/views/login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (Exception e) {
            log.debug("Exception while authenticate user:" + e);
        }
    }

}
