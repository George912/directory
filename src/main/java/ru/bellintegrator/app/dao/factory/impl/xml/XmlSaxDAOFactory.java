package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxContactDAO;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlSaxDAOFactory extends DAOFactory {

    //todo serialization/deserialization from config.properties
    private static final String CONTACT_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts.xml";
    private static final String GROUP_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups.xml";

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new SaxContactDAO(CONTACT_FILE);
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new SaxGroupDAO(GROUP_FILE);
    }

}
