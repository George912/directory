package ru.bellintegrator.app.parser.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.impl.file.AbstractFileDAO;
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
public class DOMUtilForGroup extends AbstractFileDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {

        //use generateId()
        group.setId(111);

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups.xml")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();

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

            root.appendChild(groupElement);

            writeToXml(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return group.getId();

    }

    @Override
    public void delete(Group group) throws DAOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            StringBuilder expression = new StringBuilder("/groups/group[@id='");
            expression.append(group.getId());
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
    public void update(Group group) throws DAOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups1.xml")) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            //подготовка основного выражения для поиска изменяемой группы
            StringBuilder mainExpression = new StringBuilder("/groups/group[@id='");
            mainExpression.append(group.getId());
            mainExpression.append("']");

            //выражение для поиска /group/name
            StringBuilder nameExpression = new StringBuilder(mainExpression.toString());
            nameExpression.append("/name");

            //выражение для поиска /group/notes
            StringBuilder notesExpression = new StringBuilder(mainExpression.toString());
            notesExpression.append("/notes");

            XPath xPath = XPathFactory.newInstance().newXPath();

            //изменение имени группы
            NodeList nList = (NodeList) xPath.compile(nameExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(group.getName());
                }
            }

            //изменение заметок группы
            nList = (NodeList) xPath.compile(notesExpression.toString()).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    eElement.setTextContent(group.getNotes());
                }
            }

            writeToXml(doc);

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

    }

    public List<Group> getAll() {

        List<Group> groupList = new ArrayList<>();
        Group group;

        try (InputStream inputStream = getClass().getResourceAsStream("/xml/groups.xml")) {
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
    public void save(List<Group> list) throws DAOException {

        throw new UnsupportedOperationException();

    }

    private void writeToXml(Document document) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups1.xml"));
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
