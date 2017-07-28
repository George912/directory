package ru.bellintegrator.app.dao.factory.impl.xml;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.xml.dom.DomContactDAO;
import ru.bellintegrator.app.dao.impl.xml.dom.DomGroupDAO;
import ru.bellintegrator.app.dao.impl.xml.jackson.JacksonContactDAO;
import ru.bellintegrator.app.dao.impl.xml.jackson.JacksonGroupDAO;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxContactDAO;
import ru.bellintegrator.app.dao.impl.xml.sax.SaxGroupDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class XmlDAOFactory extends DAOFactory {

    private XmlParserType parserType;

    public XmlDAOFactory(XmlParserType parserType) {
        this.parserType = parserType;
    }

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        System.out.println(parserType);
        switch (parserType){
            case DOM:
                return new DomContactDAO();

            case SAX:
                return new SaxContactDAO();

            case JACKSON:
                return new JacksonContactDAO();

            default:
                throw new DAOException(new UnsupportedOperationException("Unable to create dao factory for target xml parser. Parser type " + parserType + " is unknown!"));
        }
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        switch (parserType){
            case DOM:
                return new DomGroupDAO();

            case SAX:
                return new SaxGroupDAO();

            case JACKSON:
                return new JacksonGroupDAO();

            default:
                throw new DAOException(new UnsupportedOperationException("Unable to create dao factory for target xml parser. Parser type " + parserType + " is unknown!"));
        }
    }

}
