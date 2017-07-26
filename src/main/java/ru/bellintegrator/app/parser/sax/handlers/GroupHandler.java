package ru.bellintegrator.app.parser.sax.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bellintegrator.app.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class GroupHandler extends DefaultHandler {

    private Group group = null;
    private List<Group> groupList = new ArrayList<>();
    private boolean bName;
    private boolean bNotes;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("group".equalsIgnoreCase(qName)) {
            group = new Group();
            group.setId(Integer.parseInt(attributes.getValue("id")));

        } else if ("name".equalsIgnoreCase(qName)) {
            bName = true;

        } else if ("notes".equalsIgnoreCase(qName)) {
            bNotes = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bName) {
            group.setName(new String(ch, start, length));
            bName = false;

        } else if (bNotes) {
            group.setNotes(new String(ch, start, length));
            bNotes = false;
            groupList.add(group);

        }

    }

    public List<Group> getGroupList() {
        return groupList;
    }
}
