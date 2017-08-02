package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.dom.DomContactDAO;
import ru.bellintegrator.app.dao.impl.xml.dom.DomGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.ConfigLoader;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlDomDAOFactory extends DAOFactory {

    ConfigLoader configLoader = ConfigLoader.getInstance();

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        DomContactDAO dao = new DomContactDAO(configLoader.getXmlContactsPath());
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        DomGroupDAO dao = new DomGroupDAO(configLoader.getXmlGroupsPath());
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

}
