package ru.bellintegrator.app.parser.sax.handler.all;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.parser.sax.handler.AbstractContactHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 26.07.2017.
 */
public class AllContactContactHandler extends AbstractContactHandler {

    Contact contact = null;
    List<Contact> contactList = new ArrayList<>();
    List<Group> groupList = null;

    protected void templateMethod(String qName, Attributes attributes) {
        if ("contact".equalsIgnoreCase(qName)) {
            contact = new Contact();
            contact.setId(Integer.parseInt(attributes.getValue("id")));
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

                contactList.add(contact);

            }

        }

    }

    public List<Contact> getContactList() {
        return contactList;
    }

}
