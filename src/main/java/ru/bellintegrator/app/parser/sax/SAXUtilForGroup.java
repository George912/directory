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
import java.io.*;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class SAXUtilForGroup extends AbstractFileDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {

        List<Group> groupList = getAll();

        if (!groupList.contains(group)) {
            groupList.add(group);
        }

        save(groupList);

        return group.getId();

    }

    @Override
    //todo удалить группу у контактов перед вызовом delete
    public void delete(Group group) throws DAOException {

        List<Group> groupList = getAll();

        groupList.remove(group);

        save(groupList);

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
            SAXParser saxParser = factory.newSAXParser();
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

        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter = null;

        try {
            OutputStream outputStream = new FileOutputStream("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups2.xml");

            xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(outputStream);

            xMLStreamWriter.writeStartDocument();

            xMLStreamWriter.writeStartElement("groups");

            for (Group group1 : list) {
                xMLStreamWriter.writeStartElement("group");
                xMLStreamWriter.writeAttribute("id", String.valueOf(group1.getId()));

                xMLStreamWriter.writeStartElement("name");
                xMLStreamWriter.writeCharacters(group1.getName());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("notes");
                xMLStreamWriter.writeCharacters(group1.getNotes());
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
