package ru.bellintegrator.app.dao.impl.xml.sax;

import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.sax.handler.all.AllGroupHandler;
import ru.bellintegrator.app.parser.sax.handler.byid.ByIdGroupHandler;
import ru.bellintegrator.app.parser.sax.handler.byname.ByNameGroupHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class SaxGroupDAO implements GenericDAO<Group> {

    private String filePath;

    public SaxGroupDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Group group) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Create operation not supported while using SAX parser."));
    }

    @Override
    public void delete(Group group) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Delete operation not supported while using SAX parser."));
    }

    @Override
    public void update(Group group) throws DAOException {
        throw new DAOException(
                new UnsupportedOperationException("Update operation not supported while using SAX parser."));
    }

    @Override
    //todo filePath
    public List<Group> getAll() throws DAOException {

        InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Group> groupList = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            AllGroupHandler groupHandler = new AllGroupHandler();
            saxParser.parse(inputStream, groupHandler);
            groupList = groupHandler.getGroupList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return groupList;

    }

    @Override
    //todo filePath
    public Group getById(int id) {
        Group group = null;
        InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            ByIdGroupHandler groupHandler = new ByIdGroupHandler(id);
            saxParser.parse(inputStream, groupHandler);
            group = groupHandler.getGroup();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return group;
    }

    @Override
    //todo filePath
    public List<Group> getByName(String name) {
        List<Group> groupList = null;
        InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            ByNameGroupHandler groupHandler = new ByNameGroupHandler(name);
            saxParser.parse(inputStream, groupHandler);
            groupList = groupHandler.getGroupList();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return groupList;
    }

}
