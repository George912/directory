package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxContactDAO;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.ConfigLoader;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlSaxDAOFactory extends DAOFactory {

    ConfigLoader configLoader = ConfigLoader.getInstance();

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new SaxContactDAO(configLoader.getXmlContactsPath());
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new SaxGroupDAO(configLoader.getXmlGroupsPath());
    }

}
