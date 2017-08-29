package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

public abstract class AbstractServlet extends HttpServlet {

    protected DAOFactory daoFactory;
    protected RequestDispatcher dispatcher;
    protected AnalyticalInfoDAO infoDAO;
    protected GenericDAO<Contact> contactGenericDAO;
    protected GenericDAO<Group> groupGenericDAO;
    protected GenericDAO<User> userGenericDAO;
    private static final Logger log = Logger.getLogger(AbstractServlet.class);

    {
        daoFactory = DAOFactory.getDAOFactory();
        try {
            infoDAO = daoFactory.getAnalyticalInfoDAO();
            contactGenericDAO = daoFactory.getContactDAO();
            groupGenericDAO = daoFactory.getGroupDAO();
            userGenericDAO = daoFactory.getUserDAO();
            log.debug("Initialize daos");

        } catch (DAOException e) {
            log.error("Exception while initializing daos:", e);
        }
    }
}
