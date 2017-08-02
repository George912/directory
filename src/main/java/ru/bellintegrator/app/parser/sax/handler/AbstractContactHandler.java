package ru.bellintegrator.app.parser.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 29.07.2017.
 */
public abstract class AbstractContactHandler extends DefaultHandler {

    protected Contact contact;
    protected List<Group> groupList;

    protected boolean bFirstName;
    protected boolean bLastName;
    protected boolean bMiddleName;
    protected boolean bFirstPhoneNumber;
    protected boolean bFirstPhoneNumberType;
    protected boolean bSecondPhoneNumber;
    protected boolean bSecondPhoneNumberType;
    protected boolean bEmail;
    protected boolean bNotes;
    protected boolean bGroupList;
    protected boolean bGroupId;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        templateStartElementSubMethod(qName, attributes);

        if ("lastName".equalsIgnoreCase(qName)) {
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
        templateCharacters(ch, start, length);
    }

    protected abstract void templateStartElementSubMethod(String qName, Attributes attributes);

    protected void templateCharacters(char[] ch, int start, int length){
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
                groupList.add(new Group(Integer.parseInt(new String(ch, start, length))));
            }
            bGroupId = false;
        }
    }

}
