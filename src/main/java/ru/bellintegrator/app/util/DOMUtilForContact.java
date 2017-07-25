package ru.bellintegrator.app.util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 25.07.2017.
 */
public class DOMUtilForContact extends AbstractFileDAO<Contact> {

    @Override
    public int create(Contact contact) throws DAOException {

        //use generateId()
        contact.setId(111);

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();

            Element contactElement = doc.createElement("contact");
            Attr idAttr = doc.createAttribute("id");
            idAttr.setValue(String.valueOf(contact.getId()));
            Element contactLastNameElement = doc.createElement("lastName");
            contactLastNameElement.appendChild(doc.createTextNode(contact.getLastName()));
            Element contactNameElement = doc.createElement("firstName");
            contactNameElement.appendChild(doc.createTextNode(contact.getFirstName()));
            Element contactMiddleNameElement = doc.createElement("middleName");
            contactMiddleNameElement.appendChild(doc.createTextNode(contact.getFirstName()));
            Element contactFirstPhoneNumberElement = doc.createElement("firstPhoneNumber");
            contactFirstPhoneNumberElement.appendChild(doc.createTextNode(contact.getFirstPhoneNumber()));
            Element contactFirstPhoneNumberTypeElement = doc.createElement("firstPhoneNumberType");
            contactFirstPhoneNumberTypeElement.appendChild(doc.createTextNode(contact.getFirstPhoneNumberType().name()));
            Element contactSecondPhoneNumberElement = doc.createElement("secondPhoneNumber");
            contactSecondPhoneNumberElement.appendChild(doc.createTextNode(contact.getSecondPhoneNumber()));
            Element contactSecondPhoneNumberTypeElement = doc.createElement("secondPhoneNumberType");
            contactSecondPhoneNumberTypeElement.appendChild(doc.createTextNode(contact.getSecondPhoneNumberType().name()));
            Element contactEmailElement = doc.createElement("email");
            contactEmailElement.appendChild(doc.createTextNode(contact.getEmail()));
            Element contactNotesElement = doc.createElement("notes");
            contactNotesElement.appendChild(doc.createTextNode(contact.getNotes()));

            Element contactGroupListElement = doc.createElement("groupList");
            Element groupIdElement = null;

            for (Group group : contact.getGroupList()) {
                groupIdElement.appendChild(doc.createTextNode(String.valueOf(group.getId())));
                contactGroupListElement.appendChild(groupIdElement);
            }

            contactElement.setAttributeNode(idAttr);
            contactElement.appendChild(contactLastNameElement);
            contactElement.appendChild(contactNameElement);
            contactElement.appendChild(contactMiddleNameElement);
            contactElement.appendChild(contactFirstPhoneNumberElement);
            contactElement.appendChild(contactFirstPhoneNumberTypeElement);
            contactElement.appendChild(contactSecondPhoneNumberElement);
            contactElement.appendChild(contactSecondPhoneNumberTypeElement);
            contactElement.appendChild(contactEmailElement);
            contactElement.appendChild(contactNotesElement);
            contactElement.appendChild(contactGroupListElement);

            root.appendChild(contactElement);

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contact.getId();

    }

    @Override
    public void delete(Contact contact) throws DAOException {

    }

    @Override
    public void update(Contact contact) throws DAOException {

    }

    @Override
    public List<Contact> getAll() throws DAOException {

        List<Contact> contactList = new ArrayList<>();
        Contact contact;
        List<Group> groupList = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/contacts/contact";
            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    Element groupListElement = (Element) eElement.getElementsByTagName("groupList").item(0);
                    NodeList groupListElementChildNodes = groupListElement.getChildNodes();

                    for (int j = 0; j < groupListElementChildNodes.getLength(); j++) {
                        Node node = groupListElementChildNodes.item(j);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) nNode;

                            //get group by id from DOMUtilForGroup
                        }

                    }

                    contact = new Contact(Integer.parseInt(eElement.getAttribute("id"))
                            , eElement.getElementsByTagName("lastName").item(0).getTextContent()
                            , eElement.getElementsByTagName("firstName").item(0).getTextContent()
                            , eElement.getElementsByTagName("middleName").item(0).getTextContent()
                            , eElement.getElementsByTagName("firstPhoneNumber").item(0).getTextContent()
                            , PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("firstPhoneNumberType").item(0).getTextContent())
                            , eElement.getElementsByTagName("secondPhoneNumber").item(0).getTextContent()
                            , PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("secondPhoneNumberType").item(0).getTextContent())
                            , eElement.getElementsByTagName("email").item(0).getTextContent()
                            , eElement.getElementsByTagName("notes").item(0).getTextContent()
                    );

                    contactList.add(contact);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contactList;

    }

    @Override
    public void save(List<Contact> list) throws DAOException {

    }

    private void writeToXml(Document document) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts1.xml"));
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
