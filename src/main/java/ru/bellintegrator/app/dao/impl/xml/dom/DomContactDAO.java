package ru.bellintegrator.app.dao.impl.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
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
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class DomContactDAO extends AbstractDAOWithIdGenerator<Contact> {

    private String filePath;

    public DomContactDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Contact contact) throws DAOException {
        contact.setId(generateId());

        try (InputStream inputStream = new FileInputStream(filePath)) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();

            root.appendChild(createContactElement(doc, contact));

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contact.getId();
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/contacts/contact[@id='" + contact.getId() + "']";

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.getParentNode().removeChild(eElement);
                }
            }

            writeToXml(doc);

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Contact contact) throws DAOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String mainExpression = "/contacts/contact[@id='" + contact.getId() + "']";
            String lastNameExpression = mainExpression + "/lastName";
            String firstNameExpression = mainExpression + "/firstName";
            String middleNameExpression = mainExpression + "/middleName";
            String firstPhoneNumberExpression = mainExpression + "/firstPhoneNumber";
            String firstPhoneNumberTypeExpression = mainExpression + "/firstPhoneNumberType";
            String secondPhoneNumberExpression = mainExpression + "/secondPhoneNumber";
            String secondPhoneNumberTypeExpression = mainExpression + "/secondPhoneNumberType";
            String emailExpression = mainExpression + "/email";
            String notesExpression = mainExpression + "/notes";
            String groupListExpression = mainExpression + "/groupList";

            XPath xPath = XPathFactory.newInstance().newXPath();

            updateTagByXpath(doc, xPath.compile(lastNameExpression), contact.getLastName());
            updateTagByXpath(doc, xPath.compile(firstNameExpression), contact.getFirstName());
            updateTagByXpath(doc, xPath.compile(middleNameExpression), contact.getMiddleName());
            updateTagByXpath(doc, xPath.compile(firstPhoneNumberExpression), contact.getFirstPhoneNumber());
            updateTagByXpath(doc, xPath.compile(firstPhoneNumberTypeExpression), contact.getFirstPhoneNumberType().name());
            updateTagByXpath(doc, xPath.compile(secondPhoneNumberExpression), contact.getSecondPhoneNumber());
            updateTagByXpath(doc, xPath.compile(secondPhoneNumberTypeExpression), contact.getSecondPhoneNumberType().name());
            updateTagByXpath(doc, xPath.compile(emailExpression), contact.getEmail());
            updateTagByXpath(doc, xPath.compile(notesExpression), contact.getNotes());
            updateGroupsTagByXpath(doc, xPath.compile(groupListExpression), contact.getGroupList());

            writeToXml(doc);

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAll() throws DAOException {
        List<Contact> contactList = new ArrayList<>();
        Contact contact;

        try (InputStream inputStream = new FileInputStream(filePath)) {
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

                    contact = getContact(eElement);

                    contactList.add(contact);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    @Override
    public Contact getById(int id) {
        Contact contact = null;

        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/contacts/contact[@id='" + id + "']";

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    contact = getContact(eElement);
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public List<Contact> getByName(String name) {
        List<Contact> contactList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/contacts/contact[firstName='" + name + "']";

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    contactList.add(getContact(eElement));
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    private Element createContactElement(Document document, Contact contact) {

        Element contactElement = document.createElement("contact");
        Attr idAttr = document.createAttribute("id");
        idAttr.setValue(String.valueOf(contact.getId()));
        Element contactLastNameElement = document.createElement("lastName");
        contactLastNameElement.appendChild(document.createTextNode(contact.getLastName()));
        Element contactNameElement = document.createElement("firstName");
        contactNameElement.appendChild(document.createTextNode(contact.getFirstName()));
        Element contactMiddleNameElement = document.createElement("middleName");
        contactMiddleNameElement.appendChild(document.createTextNode(contact.getFirstName()));
        Element contactFirstPhoneNumberElement = document.createElement("firstPhoneNumber");
        contactFirstPhoneNumberElement.appendChild(document.createTextNode(contact.getFirstPhoneNumber()));
        Element contactFirstPhoneNumberTypeElement = document.createElement("firstPhoneNumberType");
        contactFirstPhoneNumberTypeElement.appendChild(document.createTextNode(contact.getFirstPhoneNumberType().name()));
        Element contactSecondPhoneNumberElement = document.createElement("secondPhoneNumber");
        contactSecondPhoneNumberElement.appendChild(document.createTextNode(contact.getSecondPhoneNumber()));
        Element contactSecondPhoneNumberTypeElement = document.createElement("secondPhoneNumberType");
        contactSecondPhoneNumberTypeElement.appendChild(document.createTextNode(contact.getSecondPhoneNumberType().name()));
        Element contactEmailElement = document.createElement("email");
        contactEmailElement.appendChild(document.createTextNode(contact.getEmail()));
        Element contactNotesElement = document.createElement("notes");
        contactNotesElement.appendChild(document.createTextNode(contact.getNotes()));

        Element contactGroupListElement = document.createElement("groupList");

        for (Group group : contact.getGroupList()) {
            Element groupIdElement = document.createElement("id");
            groupIdElement.appendChild(document.createTextNode(String.valueOf(group.getId())));
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

        return contactElement;

    }

    private void writeToXml(Document document) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
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

    private void updateTagByXpath(Document doc, XPathExpression xPath, String newValue) throws XPathExpressionException {
        NodeList nList = (NodeList) xPath.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                eElement.setTextContent(newValue);
            }
        }
    }

    private void updateGroupsTagByXpath(Document doc, XPathExpression xPath, List<Group> groupList) throws XPathExpressionException {
        Node groupListNode = (Node) xPath.evaluate(doc, XPathConstants.NODE);
        Element groupListElem = (Element) groupListNode;
        NodeList nList = groupListElem.getElementsByTagName("id");

        //удаление id групп
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                eElement.getParentNode().removeChild(eElement);
            }
        }

        //добавление id групп
        for (Group group : groupList) {
            Element groupIdElement = doc.createElement("id");
            groupIdElement.appendChild(doc.createTextNode(String.valueOf(group.getId())));
            groupListElem.appendChild(groupIdElement);
        }
    }

    private List<Group> getGroupList(Element contactElem) {
        List<Group> groupList = new ArrayList<>();

        Element groupListElement = (Element) contactElem.getElementsByTagName("groupList").item(0);
        NodeList groupIds = groupListElement.getElementsByTagName("id");

        for (int j = 0; j < groupIds.getLength(); j++) {
            Node node = groupIds.item(j);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                groupList.add(new Group(Integer.parseInt(element.getTextContent())));
            }

        }

        return groupList;
    }

    private Contact getContact(Element eElement) {
        Contact contact = new Contact();

        contact.setId(Integer.parseInt(eElement.getAttribute("id")));
        contact.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
        contact.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
        contact.setMiddleName(eElement.getElementsByTagName("middleName").item(0).getTextContent());
        contact.setFirstPhoneNumber(eElement.getElementsByTagName("firstPhoneNumber").item(0).getTextContent());
        contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("firstPhoneNumberType").item(0).getTextContent()));
        contact.setSecondPhoneNumber(eElement.getElementsByTagName("secondPhoneNumber").item(0).getTextContent());
        contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("secondPhoneNumberType").item(0).getTextContent()));
        contact.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
        contact.setNotes(eElement.getElementsByTagName("notes").item(0).getTextContent());
        contact.setGroupList(getGroupList(eElement));

        return contact;
    }

}
