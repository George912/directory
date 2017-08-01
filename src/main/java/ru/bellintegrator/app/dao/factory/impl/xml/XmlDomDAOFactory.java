package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.dom.DomContactDAO;
import ru.bellintegrator.app.dao.impl.xml.dom.DomGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlDomDAOFactory extends DAOFactory {

    //todo get filePath for serialization/deserialization from config.properties
    private static final String CONTACT_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts1.xml";
    private static final String GROUP_FILE = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups1.xml";

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        DomContactDAO dao = new DomContactDAO(CONTACT_FILE);
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        DomGroupDAO dao = new DomGroupDAO(GROUP_FILE);
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

}
