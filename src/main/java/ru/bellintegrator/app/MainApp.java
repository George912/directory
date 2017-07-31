package ru.bellintegrator.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.validation.xml.Validator;
import ru.bellintegrator.app.validation.xml.impl.XMLValidator;
import ru.bellintegrator.app.viewmodel.AdditionalViewModel;
import ru.bellintegrator.app.viewmodel.MainViewModel;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void init() throws Exception {
        String xmlFilePath = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups.xml";
        String xsdFilePath = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xsd\\groups.xsd";

        validate(xmlFilePath, xsdFilePath);

        xmlFilePath = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\contacts.xml";
        xsdFilePath = "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xsd\\contacts.xsd";

        validate(xmlFilePath, xsdFilePath);

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.XML_JACKSON);
        GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
        GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
        ContactService contactService = new ContactService(contactGenericDAO);
        GroupService groupService = new GroupService(groupGenericDAO, contactService);
        contactService.setGroupService(groupService);

//        Contact contact = new Contact(5, "Контакт5", "Контакт5", "Контакт5");
//        List<Group> groupList = Arrays.asList(new Group(1), new Group(2), new Group(3));
//        contact.setGroupList(groupList);

//        contactService.addContact(contact);
		System.out.println(contactService.getAllContacts().toString());

        String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();

        loader.setController(new MainViewModel(contactService, groupService));
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 800, 500);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Справочник контактов");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        showAdditionalWindow(contactService);

    }

    private void showAdditionalWindow(ContactService contactService) {

        String stageTitle = "";
        String fxmlPath = "/fxml/additional.fxml";
        FXMLLoader loader = null;
        AnchorPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        AdditionalViewModel additionalController = new AdditionalViewModel(contactService);

        try {
            loader = new FXMLLoader();
            loader.setController(additionalController);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            page = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            scene = new Scene(page, 760, 240);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.setTitle("Список контактов");

            contactService.addContactListChangeObserver(additionalController);

            dialogStage.showAndWait();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

    private void validate(String xmlFilePath, String xsdFilePath) {
        Validator validator;

        validator = new XMLValidator(xmlFilePath, xsdFilePath, XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            validator.validate();
            log.debug("File " + xmlFilePath + " is valid.");

        } catch (SAXException | IOException e) {
            log.debug("Exception while xml validation: " + e);
        }
    }

}
