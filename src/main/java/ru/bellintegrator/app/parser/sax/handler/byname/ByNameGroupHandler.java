package ru.bellintegrator.app.parser.sax.handler.byname;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bellintegrator.app.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class ByNameGroupHandler extends DefaultHandler {

    private Group group = null;
    private boolean bName;
    private boolean bNotes;
    private boolean bGroupIsFind;
    private String name;
    private List<Group> groupList = new ArrayList<>();
    private int groupId;

    public ByNameGroupHandler(String name) {
        this.name = name;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("group".equalsIgnoreCase(qName)) {
            groupId = Integer.parseInt(attributes.getValue("id"));

        } else if ("name".equalsIgnoreCase(qName)) {
            bName = true;

        } else if ("notes".equalsIgnoreCase(qName)) {
            bNotes = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bName) {
            String groupName = new String(ch, start, length);
            if (name.equals(groupName)) {
                group = new Group();
                group.setId(groupId);
                group.setName(groupName);
                bName = false;
                bGroupIsFind = true;
            }


        } else if (bNotes && bGroupIsFind) {
            group.setNotes(new String(ch, start, length));
            bNotes = false;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bGroupIsFind) {
            bGroupIsFind = false;
            groupList.add(group);
        }
    }

    public List<Group> getGroupList() {
        return groupList;
    }
}
