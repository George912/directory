package ru.bellintegrator.app.parser.sax.handler.byid;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bellintegrator.app.model.Group;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class ByIdGroupHandler extends DefaultHandler {

    private Group group = null;
    private boolean bName;
    private boolean bNotes;
    private boolean bGroupIsFind;
    private int groupId;

    public ByIdGroupHandler(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("group".equalsIgnoreCase(qName)) {
            if (Integer.parseInt(attributes.getValue("id")) == groupId) {
                group = new Group();
                group.setId(groupId);
                bGroupIsFind = true;
            }


        } else if ("name".equalsIgnoreCase(qName) && bGroupIsFind) {
            bName = true;

        } else if ("notes".equalsIgnoreCase(qName) && bGroupIsFind) {
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
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("group".equalsIgnoreCase(qName)) {
            if (bGroupIsFind) {
                bGroupIsFind = false;
            }
        }
    }

    public Group getGroup() {
        return group;
    }
}
