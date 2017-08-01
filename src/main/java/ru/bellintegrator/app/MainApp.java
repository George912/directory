package ru.bellintegrator.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
import ru.bellintegrator.app.viewmodel.StartViewModel;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.util.ArrayList;
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
//        Contact contact = new Contact(5, "Контакт5", "Контакт5", "Контакт5");
//        List<Group> groupList = Arrays.asList(new Group(1), new Group(2), new Group(3));
//        contact.setGroupList(groupList);

//        contactService.addContact(contact);
//		System.out.println(contactService.getAllContacts().toString());
//========================================================================
//        String fxmlFile = "/fxml/startWindow.fxml";
//        FXMLLoader loader = new FXMLLoader();

//        loader.setController(new MainViewModel(contactService, groupService));
//        StartViewModel viewModel = new StartViewModel(getDaoTypeNameList());
//        viewModel.setStage(stage);
//        loader.setController(viewModel);
//        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
//        log.debug(viewModel.getDaoFactoryType().name());
//
//        Scene scene = new Scene(rootNode, 800, 500);
//        scene.getStylesheets().add("/styles/styles.css");
//
//        stage.setTitle("Справочник контактов");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();


//        showAdditionalWindow(contactService);

        DAOFactoryType daoFactoryType = showStartWindow();

        DAOFactory daoFactory = DAOFactory.getDAOFactory(daoFactoryType);
        GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
        GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
        ContactService contactService = new ContactService(contactGenericDAO);
        GroupService groupService = new GroupService(groupGenericDAO, contactService);
        contactService.setGroupService(groupService);

        Mode mode = defineMode(daoFactoryType);
        showMainlWindow(stage, mode, contactService, groupService);
    }

    private DAOFactoryType showStartWindow() {
        String fxmlPath = "/fxml/startWindow.fxml";
        StartViewModel viewModel = new StartViewModel(getDaoTypeNameList());

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(viewModel);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page, 400, 150);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            viewModel.setStage(dialogStage);

            dialogStage.showAndWait();

            return viewModel.getDaoFactoryType();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

        return DAOFactoryType.UNKNOWN;
    }

    private void showAdditionalWindow(AdditionalViewModel viewModel, ContactService contactService) {
        String fxmlPath = "/fxml/additionalWindow.fxml";

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(viewModel);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page, 760, 240);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.setTitle("Список контактов");

            contactService.addContactListChangeObserver(viewModel);

            dialogStage.show();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    private void showMainlWindow(Stage stage, Mode mode, ContactService contactService, GroupService groupService) {
        String fxmlPath = "/fxml/mainWindow.fxml";
        MainViewModel mainViewModel = new MainViewModel(contactService, groupService, mode);
        AdditionalViewModel additionalViewModel = new AdditionalViewModel(contactService);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(mainViewModel);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            GridPane page = loader.load();

            Scene scene = new Scene(page, 800, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Список контактов");

            contactService.addContactListChangeObserver(additionalViewModel);
            showAdditionalWindow(additionalViewModel, contactService);

            stage.show();

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

    private List<String> getDaoTypeNameList() {
        List<String> daoTypeNameList = new ArrayList<>();
        daoTypeNameList.add(DAOFactoryType.FILE.name());
        daoTypeNameList.add(DAOFactoryType.XML_DOM.name());
        daoTypeNameList.add(DAOFactoryType.XML_JACKSON.name());
        daoTypeNameList.add(DAOFactoryType.XML_SAX.name());

        return daoTypeNameList;
    }

    private Mode defineMode(DAOFactoryType type) {
        if (!type.equals(DAOFactoryType.XML_SAX)) {
            return Mode.READ_WRITE;
        }

        return Mode.READ_ONLY;
    }

}
