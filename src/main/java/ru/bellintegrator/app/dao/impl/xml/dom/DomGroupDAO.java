package ru.bellintegrator.app.dao.impl.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

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
public class DomGroupDAO extends AbstractDAOWithIdGenerator<Group> {

    private String filePath;

    public DomGroupDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int create(Group group) throws DAOException {
        group.setId(generateId());

        try (InputStream inputStream = new FileInputStream(filePath)) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();

            root.appendChild(createGroupElement(doc, group));

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return group.getId();
    }

    @Override
    public void delete(Group group) throws DAOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/groups/group[@id='" + group.getId() + "']";

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
    public void update(Group group) throws DAOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            //подготовка основного выражения для поиска изменяемой группы
            String mainExpression = "/groups/group[@id='" + group.getId() + "']";

            //выражение для поиска /group/name
            String nameExpression = mainExpression + "/name";

            //выражение для поиска /group/notes
            String notesExpression = mainExpression + "/notes";

            XPath xPath = XPathFactory.newInstance().newXPath();

            updateTagByXpath(doc, xPath.compile(nameExpression), group.getName());
            updateTagByXpath(doc, xPath.compile(notesExpression), group.getNotes());

            writeToXml(doc);

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAll() throws DAOException {
        List<Group> groupList = new ArrayList<>();
        Group group;

        try (InputStream inputStream = new FileInputStream(filePath)) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/groups/group";
            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    group = new Group(Integer.parseInt(eElement.getAttribute("id"))
                            , eElement.getElementsByTagName("name").item(0).getTextContent()
                            , eElement.getElementsByTagName("notes").item(0).getTextContent());

                    groupList.add(group);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return groupList;
    }

    @Override
    public Group getById(int id) {
        Group group = null;

        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/groups/group[@id='" + id + "']";

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    group = new Group();

                    group.setId(id);
                    group.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    group.setNotes(eElement.getElementsByTagName("notes").item(0).getTextContent());
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return group;
    }

    @Override
    public List<Group> getByName(String name) {
        List<Group> groupList = new ArrayList<>();
        Group group = null;

        try (InputStream inputStream = new FileInputStream(filePath)) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            String expression = "/groups/group[name='" + name + "']";

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    group = new Group();

                    group.setId(Integer.parseInt(eElement.getAttribute("id")));
                    group.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    group.setNotes(eElement.getElementsByTagName("notes").item(0).getTextContent());

                    groupList.add(group);
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return groupList;
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
        NodeList nList;
        nList = (NodeList) xPath.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                eElement.setTextContent(newValue);
            }
        }
    }

    private Element createGroupElement(Document doc, Group group) {
        Element groupElement = doc.createElement("group");
        Attr idAttr = doc.createAttribute("id");
        idAttr.setValue(String.valueOf(group.getId()));
        Element groupNameElement = doc.createElement("name");
        groupNameElement.appendChild(doc.createTextNode(group.getName()));
        Element groupNotesElement = doc.createElement("notes");
        groupNotesElement.appendChild(doc.createTextNode(group.getNotes()));
        groupElement.setAttributeNode(idAttr);
        groupElement.appendChild(groupNameElement);
        groupElement.appendChild(groupNotesElement);

        return groupElement;
    }
}
