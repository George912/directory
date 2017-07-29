package ru.bellintegrator.app.dao.impl.xml.sax;

import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.parser.sax.handler.all.AllContactContactHandler;
import ru.bellintegrator.app.parser.sax.handler.byid.ByIdContactHandler;
import ru.bellintegrator.app.parser.sax.handler.byname.ByNameContactContactHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class SaxContactDAO implements GenericDAO<Contact> {

    private String filePath;

    public SaxContactDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Contact contact) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Create operation not supported while using SAX parser."));
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Delete operation not supported while using SAX parser."));
    }

    @Override
    public void update(Contact contact) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Update operation not supported while using SAX parser."));
    }

    @Override
    //todo filePath
    public List<Contact> getAll() throws DAOException {

        InputStream inputStream = getClass().getResourceAsStream("/xml/contacts.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Contact> contactList = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            AllContactContactHandler contactHandler = new AllContactContactHandler();
            saxParser.parse(inputStream, contactHandler);
            contactList = contactHandler.getContactList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contactList;

    }

    @Override
    //todo get List<Group>
    public Contact getById(int id) {
        Contact contact = null;
        InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            ByIdContactHandler groupHandler = new ByIdContactHandler(id);
            saxParser.parse(inputStream, groupHandler);
            contact = groupHandler.getContact();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    //todo get List<Group>
    public List<Contact> getByName(String name) {
        List<Contact> contactList = null;
        InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            ByNameContactContactHandler handler = new ByNameContactContactHandler(name);
            saxParser.parse(inputStream, handler);
            contactList = handler.getContactList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contactList;
    }

}
