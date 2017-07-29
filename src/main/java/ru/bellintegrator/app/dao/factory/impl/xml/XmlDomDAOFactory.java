package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.dom.DomContactDAO;
import ru.bellintegrator.app.dao.impl.xml.dom.DomGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlDomDAOFactory extends DAOFactory {

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new DomContactDAO();
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new DomGroupDAO();
    }

}
