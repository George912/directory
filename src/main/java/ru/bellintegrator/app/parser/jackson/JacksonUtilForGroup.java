package ru.bellintegrator.app.parser.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.jackson.model.Groups;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 27.07.2017.
 */
public class JacksonUtilForGroup extends AbstractFileDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {
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
        ObjectMapper objectMapper = new XmlMapper();
        List<ru.bellintegrator.app.model.Group> groupList = null;

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups.xml")) {
            Groups groups = objectMapper.readValue(inputStream, Groups.class);
            groupList = getGroupList(groups.getGroups());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return groupList;
    }

    @Override
    public void save(List<Group> list) throws DAOException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        StringWriter out = new StringWriter();
        XMLStreamWriter sw = null;

        try {
            sw = xmlOutputFactory.createXMLStreamWriter(out);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        XmlMapper mapper = new XmlMapper(xmlInputFactory);


        try {
            sw.writeStartDocument();
            sw.writeStartElement("groups");

            for (Group group : list) {
                ru.bellintegrator.app.parser.jackson.model.Group group1 =
                        new ru.bellintegrator.app.parser.jackson.model.Group(group.getId(),
                                group.getName(),
                                group.getNotes());
                mapper.writeValue(sw, group1);
            }

            sw.writeEndElement();
            sw.writeEndDocument();

            sw.flush();
            System.out.println(sw.toString());

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Group getById(int id) {
        return null;
    }

    @Override
    public List<Group> getByName(String name) {
        return null;
    }

    private List<ru.bellintegrator.app.model.Group> getGroupList(ru.bellintegrator.app.parser.jackson.model.Group[] groups) {
        List<ru.bellintegrator.app.model.Group> groupList = new ArrayList<>();

        for (int i = 0; i < groups.length; i++) {
            ru.bellintegrator.app.parser.jackson.model.Group group = groups[i];
            groupList.add(new ru.bellintegrator.app.model.Group(group.getId(),
                    group.getName(), group.getNotes()));
        }

        return groupList;
    }

}
