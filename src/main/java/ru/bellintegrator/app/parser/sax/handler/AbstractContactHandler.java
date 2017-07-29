package ru.bellintegrator.app.parser.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Andrey on 29.07.2017.
 */
public abstract class AbstractContactHandler extends DefaultHandler {

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
        templateMethod(qName, attributes);

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
    protected abstract void templateMethod(String qName, Attributes attributes);
}
