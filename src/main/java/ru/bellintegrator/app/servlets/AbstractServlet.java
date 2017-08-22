package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.sql.ContactLinkGroupDao;
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
    protected ContactLinkGroupDao linkGroupDao;
    protected GenericDAO<User> userGenericDAO;
    private static final Logger log = LoggerFactory.getLogger(AbstractServlet.class);

    {
        daoFactory = DAOFactory.getDAOFactory();
        try {
            infoDAO = daoFactory.getAnalyticalInfoDAO();
            contactGenericDAO = daoFactory.getContactDAO();
            groupGenericDAO = daoFactory.getGroupDAO();
            linkGroupDao = daoFactory.getContactLinkGroupDao();
            userGenericDAO = daoFactory.getUserDAO();

        } catch (DAOException e) {
            log.debug("Exception while initializing daos:" + e);
        }
    }
}
