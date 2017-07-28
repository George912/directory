package ru.bellintegrator.app.parser.dom;

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

            root.appendChild(createContactElement(doc, contact));

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return contact.getId();

    }

    @Override
    public void delete(Contact contact) throws DAOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            StringBuilder expression = new StringBuilder("/contacts/contact[@id='");
            expression.append(contact.getId());
            expression.append("']");

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression.toString()).evaluate(doc, XPathConstants.NODESET);

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
    //todo update List<Group>
    public void update(Contact contact) throws DAOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            //подготовка основного выражения для поиска изменяемой группы
            StringBuilder mainExpression = new StringBuilder("/contacts/contact[@id='");
            mainExpression.append(contact.getId());
            mainExpression.append("']");

            //выражение для поиска /contact/lastName
            StringBuilder lastNameExpression = new StringBuilder(mainExpression.toString());
            lastNameExpression.append("/lastName");

            //выражение для поиска /contact/firstName
            StringBuilder firstNameExpression = new StringBuilder(mainExpression.toString());
            firstNameExpression.append("/firstName");

            //выражение для поиска /contact/middleName
            StringBuilder middleNameExpression = new StringBuilder(mainExpression.toString());
            middleNameExpression.append("/middleName");

            //выражение для поиска /contact/firstPhoneNumber
            StringBuilder firstPhoneNumberExpression = new StringBuilder(mainExpression.toString());
            firstPhoneNumberExpression.append("/firstPhoneNumber");

            //выражение для поиска /contact/firstPhoneNumberType
            StringBuilder firstPhoneNumberTypeExpression = new StringBuilder(mainExpression.toString());
            firstPhoneNumberTypeExpression.append("/firstPhoneNumberType");

            //выражение для поиска /contact/secondPhoneNumber
            StringBuilder secondPhoneNumberExpression = new StringBuilder(mainExpression.toString());
            secondPhoneNumberExpression.append("/secondPhoneNumber");

            //выражение для поиска /contact/secondPhoneNumberType
            StringBuilder secondPhoneNumberTypeExpression = new StringBuilder(mainExpression.toString());
            secondPhoneNumberTypeExpression.append("/secondPhoneNumberType");

            //выражение для поиска /contact/email
            StringBuilder emailExpression = new StringBuilder(mainExpression.toString());
            emailExpression.append("/email");

            //выражение для поиска /contact/notes
            StringBuilder notesExpression = new StringBuilder(mainExpression.toString());
            notesExpression.append("/notes");

            XPath xPath = XPathFactory.newInstance().newXPath();

            //изменение /contact/lastName
            NodeList nList = (NodeList) xPath.compile(lastNameExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getLastName());
                }
            }

            //изменение /contact/firstName
            nList = (NodeList) xPath.compile(firstNameExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getFirstName());
                }
            }

            //изменение /contact/middleName
            nList = (NodeList) xPath.compile(middleNameExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getMiddleName());
                }
            }

            //изменение /contact/firstPhoneNumber
            nList = (NodeList) xPath.compile(firstPhoneNumberExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getFirstPhoneNumber());
                }
            }

            //изменение /contact/firstPhoneNumberType
            nList = (NodeList) xPath.compile(firstPhoneNumberTypeExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getFirstPhoneNumberType().name());
                }
            }

            //изменение /contact/secondPhoneNumber
            nList = (NodeList) xPath.compile(secondPhoneNumberExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getSecondPhoneNumber());
                }
            }

            //изменение /contact/secondPhoneNumberType
            nList = (NodeList) xPath.compile(secondPhoneNumberTypeExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getSecondPhoneNumberType().name());
                }
            }

            //изменение /contact/email
            nList = (NodeList) xPath.compile(emailExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getEmail());
                }
            }

            //todo upd groups

            //===========================================================

            //изменение /contact/notes
            nList = (NodeList) xPath.compile(notesExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(contact.getNotes());
                }
            }

            writeToXml(doc);

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

    }

    @Override
    //todo get List<Group>
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
                            , PhoneNumberType.getPhoneNumberTypeByTypeDescription(eElement.getElementsByTagName("firstPhoneNumberType").item(0).getTextContent())
                            , eElement.getElementsByTagName("secondPhoneNumber").item(0).getTextContent()
                            , PhoneNumberType.getPhoneNumberTypeByTypeDescription(eElement.getElementsByTagName("secondPhoneNumberType").item(0).getTextContent())
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
    //todo пишет всё из старого файла+новый список
    public void save(List<Contact> list) throws DAOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();

            for (Contact contact : list) {
                root.appendChild(createContactElement(doc, contact));
            }

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    //todo get List<Group>
    public Contact getById(int id) {
        Contact contact = null;

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            StringBuilder expression = new StringBuilder("/contacts/contact[@id='");
            expression.append(id);
            expression.append("']");

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    contact = new Contact();

                    contact.setId(id);
                    contact.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    contact.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    contact.setMiddleName(eElement.getElementsByTagName("middleName").item(0).getTextContent());
                    contact.setFirstPhoneNumber(eElement.getElementsByTagName("firstPhoneNumber").item(0).getTextContent());
                    contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("firstPhoneNumberType").item(0).getTextContent()));
                    contact.setSecondPhoneNumber(eElement.getElementsByTagName("secondPhoneNumber").item(0).getTextContent());
                    contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeName(eElement.getElementsByTagName("secondPhoneNumberType").item(0).getTextContent()));
                    contact.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
                    contact.setNotes(eElement.getElementsByTagName("notes").item(0).getTextContent());
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    //todo get List<Group>
    public List<Contact> getByName(String name) {
        List<Contact> contactList = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/contacts1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            StringBuilder expression = new StringBuilder("/contacts/contact[firstName='");
            expression.append(name);
            expression.append("']");

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
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

                    List<Group> groupList = new ArrayList<>();

                    Element groupListElement = (Element) eElement.getElementsByTagName("groupList").item(0);
                    NodeList groupIdElements = groupListElement.getElementsByTagName("id");

                    for (int j = 0; j < groupIdElements.getLength(); j++) {
                        Node item = groupIdElements.item(j);

                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) item;
                            Group group = new Group(Integer.parseInt(element.getTextContent().trim()), "", "");

                            groupList.add(group);
                        }
                    }

                    contact.setGroupList(groupList);

                    contactList.add(contact);
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return contactList;
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
        Element groupIdElement = null;

        for (Group group : contact.getGroupList()) {
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

}
