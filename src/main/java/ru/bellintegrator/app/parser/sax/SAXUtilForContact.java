package ru.bellintegrator.app.parser.sax;

import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.sax.handlers.ContactHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class SAXUtilForContact extends AbstractFileDAO<Contact> {

    @Override
    public int create(Contact contact) throws DAOException {
        return 0;
    }

    @Override
    public void delete(Contact contact) throws DAOException {

    }

    @Override
    public void update(Contact contact) throws DAOException {

    }

    @Override
    public List<Contact> getAll() throws DAOException {

        InputStream inputStream = getClass().getResourceAsStream("/xml/contacts.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Contact> contactList = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            ContactHandler contactHandler = new ContactHandler();
            saxParser.parse(inputStream, contactHandler);
            contactList = contactHandler.getContactList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contactList;

    }

    @Override
    public void save(List<Contact> list) throws DAOException {

        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter = null;

        try {
            OutputStream outputStream = new FileOutputStream("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts2.xml");

            xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(outputStream);

            xMLStreamWriter.writeStartDocument();

            xMLStreamWriter.writeStartElement("contacts");

            for (Contact contact : list) {
                xMLStreamWriter.writeStartElement("contact");
                xMLStreamWriter.writeAttribute("id", String.valueOf(contact.getId()));

                xMLStreamWriter.writeStartElement("lastName");
                xMLStreamWriter.writeCharacters(contact.getLastName());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("firstName");
                xMLStreamWriter.writeCharacters(contact.getFirstName());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("middleName");
                xMLStreamWriter.writeCharacters(contact.getMiddleName());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("firstPhoneNumber");
                xMLStreamWriter.writeCharacters(contact.getFirstPhoneNumber());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("firstPhoneNumberType");
                xMLStreamWriter.writeCharacters(contact.getFirstPhoneNumberType().name());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("secondPhoneNumber");
                xMLStreamWriter.writeCharacters(contact.getSecondPhoneNumber());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("secondPhoneNumberType");
                xMLStreamWriter.writeCharacters(contact.getSecondPhoneNumberType().name());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("email");
                xMLStreamWriter.writeCharacters(contact.getEmail());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("notes");
                xMLStreamWriter.writeCharacters(contact.getNotes());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("groupList");

                if (contact.getGroupList() != null) {

                    for (Group group : contact.getGroupList()) {
                        xMLStreamWriter.writeStartElement("id");
                        xMLStreamWriter.writeCharacters(String.valueOf(group.getId()));
                        xMLStreamWriter.writeEndElement();

                    }

                }

                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeEndElement();

            }

            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();

        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
