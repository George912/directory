package ru.bellintegrator.app.parser.sax.handler.all;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.parser.sax.handler.AbstractContactHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class AllContactContactHandler extends AbstractContactHandler {

    private List<Contact> contactList = new ArrayList<>();

    protected void templateStartElementSubMethod(String qName, Attributes attributes) {
        if ("contact".equalsIgnoreCase(qName)) {
            contact = new Contact();
            contact.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("contact".equalsIgnoreCase(qName)) {
            if (contact != null) {
                if (groupList == null) {
                    groupList = new ArrayList<>();
                }

                contact.setGroupList(groupList);

                contactList.add(contact);
            }
        }
    }

    public List<Contact> getContactList() {
        return contactList;
    }

}
