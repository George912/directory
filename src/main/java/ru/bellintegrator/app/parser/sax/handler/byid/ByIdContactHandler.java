package ru.bellintegrator.app.parser.sax.handler.byid;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.parser.sax.handler.AbstractContactHandler;

import java.util.ArrayList;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class ByIdContactHandler extends AbstractContactHandler {

    private boolean bContactIsFind;
    private int contactId;

    public ByIdContactHandler(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        templateStartElementSubMethod(qName, attributes);

        if ("lastName".equalsIgnoreCase(qName) && bContactIsFind) {
            bLastName = true;

        } else if ("firstName".equalsIgnoreCase(qName) && bContactIsFind) {
            bFirstName = true;

        } else if ("middleName".equalsIgnoreCase(qName) && bContactIsFind) {
            bMiddleName = true;

        } else if ("firstPhoneNumber".equalsIgnoreCase(qName) && bContactIsFind) {
            bFirstPhoneNumber = true;

        } else if ("firstPhoneNumberType".equalsIgnoreCase(qName) && bContactIsFind) {
            bFirstPhoneNumberType = true;

        } else if ("secondPhoneNumber".equalsIgnoreCase(qName) && bContactIsFind) {
            bSecondPhoneNumber = true;

        } else if ("secondPhoneNumberType".equalsIgnoreCase(qName) && bContactIsFind) {
            bSecondPhoneNumberType = true;

        } else if ("email".equalsIgnoreCase(qName) && bContactIsFind) {
            bEmail = true;

        } else if ("notes".equalsIgnoreCase(qName) && bContactIsFind) {
            bNotes = true;

        } else if ("groupList".equalsIgnoreCase(qName) && bContactIsFind) {
            bGroupList = true;

        } else if ("id".equalsIgnoreCase(qName) && bContactIsFind) {
            bGroupId = true;
        }

    }

    @Override
    protected void templateStartElementSubMethod(String qName, Attributes attributes) {
        if ("contact".equalsIgnoreCase(qName)) {
            if (Integer.parseInt(attributes.getValue("id")) == contactId) {
                contact = new Contact();
                contact.setId(contactId);
                bContactIsFind = true;
            }
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
                bContactIsFind = false;
            }
        }
    }

    public Contact getContact() {
        return contact;
    }
}
