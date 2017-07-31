package ru.bellintegrator.app.parser.sax.handler.byid;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class ByIdContactHandler extends DefaultHandler {

    Contact contact = null;
    List<Group> groupList = null;
    private boolean bFirstName;
    private boolean bLastName;
    private boolean bMiddleName;
    private boolean bFirstPhoneNumber;
    private boolean bFirstPhoneNumberType;
    private boolean bSecondPhoneNumber;
    private boolean bSecondPhoneNumberType;
    private boolean bEmail;
    private boolean bNotes;
    private boolean bGroupList;
    private boolean bGroupId;
    private boolean bContactIsFind;
    private int contactId;

    public ByIdContactHandler(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("contact".equalsIgnoreCase(qName)) {
            if (Integer.parseInt(attributes.getValue("id")) == contactId) {
                contact = new Contact();
                contact.setId(contactId);
                bContactIsFind = true;
            }

        } else if ("lastName".equalsIgnoreCase(qName) && bContactIsFind) {
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
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bLastName) {
            contact.setLastName(new String(ch, start, length));
            bLastName = false;

        } else if (bFirstName) {
            contact.setFirstName(new String(ch, start, length));
            bFirstName = false;

        } else if (bMiddleName) {
            contact.setMiddleName(new String(ch, start, length));
            bMiddleName = false;

        } else if (bFirstPhoneNumber) {
            contact.setFirstPhoneNumber(new String(ch, start, length));
            bFirstPhoneNumber = false;

        } else if (bFirstPhoneNumberType) {
            String s = new String(ch, start, length);
            contact.setFirstPhoneNumberType(PhoneNumberType.getTypeByName(s));
            bFirstPhoneNumberType = false;

        } else if (bSecondPhoneNumber) {
            contact.setSecondPhoneNumber(new String(ch, start, length));
            bSecondPhoneNumber = false;

        } else if (bSecondPhoneNumberType) {
            contact.setSecondPhoneNumberType(PhoneNumberType.getTypeByName(new String(ch, start, length)));
            bSecondPhoneNumberType = false;

        } else if (bEmail) {
            contact.setEmail(new String(ch, start, length));
            bEmail = false;

        } else if (bNotes) {
            contact.setNotes(new String(ch, start, length));
            bNotes = false;

        } else if (bGroupList) {
            groupList = new ArrayList<>();
            bGroupList = false;

        } else if (bGroupId) {
            if (groupList != null) {
                groupList.add(new Group(Integer.parseInt(new String(ch, start, length)), "", ""));
            }
            bGroupId = false;
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
