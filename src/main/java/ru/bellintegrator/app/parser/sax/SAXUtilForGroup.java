package ru.bellintegrator.app.parser.sax;

import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.sax.handlers.GroupHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class SAXUtilForGroup extends AbstractFileDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {

        StringWriter stringWriter = new StringWriter();

        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();

        try {
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("cars");

            xMLStreamWriter.writeStartElement("supercars");
            xMLStreamWriter.writeAttribute("company", "Ferrari");

            xMLStreamWriter.writeStartElement("carname");
            xMLStreamWriter.writeAttribute("type", "formula one");
            xMLStreamWriter.writeCharacters("Ferrari 101");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("carname");
            xMLStreamWriter.writeAttribute("type", "sports");
            xMLStreamWriter.writeCharacters("Ferrari 202");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return group.getId();

    }

    @Override
    public void delete(Group group) throws DAOException {

    }

    @Override
    public void update(Group group) throws DAOException {

    }

    @Override
    public List<Group> getAll() throws DAOException {

        InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Group> groupList = null;

        try {
            SAXParser saxParser  = factory.newSAXParser();
            GroupHandler groupHandler = new GroupHandler();
            saxParser.parse(inputStream, groupHandler);
            groupList = groupHandler.getGroupList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return groupList;

    }

    @Override
    public void save(List<Group> list) throws DAOException {

    }
}
