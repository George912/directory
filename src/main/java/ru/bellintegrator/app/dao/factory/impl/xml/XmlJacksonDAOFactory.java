package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.jackson.JacksonContactDAO;
import ru.bellintegrator.app.dao.impl.xml.jackson.JacksonGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlJacksonDAOFactory extends DAOFactory {

    //todo serialization/deserialization from config.properties
    private static final String CONTACT_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts.xml";
    private static final String GROUP_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups.xml";

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        return new JacksonContactDAO(CONTACT_FILE);
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        return new JacksonGroupDAO(GROUP_FILE);
    }

}
