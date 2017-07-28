package ru.bellintegrator.app.parser.sax.handler.byname;

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
public class ByNameContactHandler extends DefaultHandler {

    Contact contact = null;
    List<Contact> contactList = new ArrayList<>();
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
    private String name;
    List<Group> groupList;
    private String lastName;

    public ByNameContactHandler(String name) {
        this.name = name;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("contact".equalsIgnoreCase(qName)) {
            contactId = Integer.parseInt(attributes.getValue("id"));

        } else if ("lastName".equalsIgnoreCase(qName)) {
            bLastName = true;

        } else if ("firstName".equalsIgnoreCase(qName)) {
            bFirstName = true;

        } else if ("middleName".equalsIgnoreCase(qName)) {
            bMiddleName = true;

        } else if ("firstPhoneNumber".equalsIgnoreCase(qName)) {
            bFirstPhoneNumber = true;

        } else if ("firstPhoneNumberType".equalsIgnoreCase(qName)) {
            bFirstPhoneNumberType = true;

        } else if ("secondPhoneNumber".equalsIgnoreCase(qName)) {
            bSecondPhoneNumber = true;

        } else if ("secondPhoneNumberType".equalsIgnoreCase(qName)) {
            bSecondPhoneNumberType = true;

        } else if ("email".equalsIgnoreCase(qName)) {
            bEmail = true;

        } else if ("notes".equalsIgnoreCase(qName)) {
            bNotes = true;

        } else if ("groupList".equalsIgnoreCase(qName)) {
            bGroupList = true;

        } else if ("id".equalsIgnoreCase(qName)) {
            bGroupId = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bLastName) {
            lastName = new String(ch, start, length);
            bLastName = false;

        } else if (bFirstName) {
            String firstName = new String(ch, start, length);
            if (name.equals(firstName)) {
                contact = new Contact();
                contact.setId(contactId);
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                bFirstName = false;
                bContactIsFind = true;
            }

        } else if (bFirstName && bContactIsFind) {
            contact.setFirstName(new String(ch, start, length));
            bFirstName = false;

        } else if (bMiddleName && bContactIsFind) {
            contact.setMiddleName(new String(ch, start, length));
            bMiddleName = false;

        } else if (bFirstPhoneNumber && bContactIsFind) {
            contact.setFirstPhoneNumber(new String(ch, start, length));
            bFirstPhoneNumber = false;

        } else if (bFirstPhoneNumberType && bContactIsFind) {
            String s = new String(ch, start, length);
            contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(s));
            bFirstPhoneNumberType = false;

        } else if (bSecondPhoneNumber && bContactIsFind) {
            contact.setSecondPhoneNumber(new String(ch, start, length));
            bSecondPhoneNumber = false;

        } else if (bSecondPhoneNumberType && bContactIsFind) {
            contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(new String(ch, start, length)));
            bSecondPhoneNumberType = false;

        } else if (bEmail && bContactIsFind) {
            contact.setEmail(new String(ch, start, length));
            bEmail = false;

        } else if (bNotes && bContactIsFind) {
            contact.setNotes(new String(ch, start, length));
            bNotes = false;

        } else if (bGroupList && bContactIsFind) {
            groupList = new ArrayList<>();
            bGroupList = false;

        } else if (bGroupId && bContactIsFind) {
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

                if (bContactIsFind) {
                    contact.setGroupList(groupList);
                    bContactIsFind = false;
                    contactList.add(contact);
                }
            }
        }
    }

    public List<Contact> getContactList() {
        return contactList;
    }

}
