package ru.bellintegrator.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by neste_000 on 02.08.2017.
 */
public class ConfigLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private static ConfigLoader instance;

    private String fileContactsPath;
    private String fileGroupsPath;
    private String xmlContactsPath;
    private String xmlGroupsPath;
    private String xsdContactsPath;
    private String xsdGroupsPath;
    private String fxmlStartWindowPath;
    private String fxmlAdditionalWindowPath;
    private String fxmlMainWindowPath;
    private String fxmlContactEditorWindowPath;
    private String fxmlGroupEditorWindowPath;
    private String fxmlAuthorizationWindowPath;
    private String fxmlAnalyticalInfoWindowPath;
    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    private ConfigLoader() {
        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);

            fileContactsPath = property.getProperty("file.contacts");
            fileGroupsPath = property.getProperty("file.groups");

            xmlContactsPath = property.getProperty("xml.contacts");
            xmlGroupsPath = property.getProperty("xml.groups");

            xsdContactsPath = property.getProperty("xsd.contacts");
            xsdGroupsPath = property.getProperty("xsd.groups");

            fxmlAdditionalWindowPath = property.getProperty("fxml.additionalWindow");
            fxmlStartWindowPath = property.getProperty("fxml.startWindow");
            fxmlMainWindowPath = property.getProperty("fxml.mainWindow");
            fxmlContactEditorWindowPath = property.getProperty("fxml.contactEditorWindow");
            fxmlGroupEditorWindowPath = property.getProperty("fxml.groupEditorWindow");
            fxmlAuthorizationWindowPath = property.getProperty("fxml.authorizationWindow");
            fxmlAnalyticalInfoWindowPath = property.getProperty("fxml.analyticalInfoWindow");

            jdbcDriver = property.getProperty("jdbc.driver");
            jdbcUrl = property.getProperty("jdbc.url");
            jdbcUser = property.getProperty("jdbc.user");
            jdbcPassword = property.getProperty("jdbc.password");

        } catch (IOException e) {
            logger.debug("Exception while getting properties: " + e);
        }
    }

    public String getFileContactsPath() {
        return fileContactsPath;
    }

    public String getFileGroupsPath() {
        return fileGroupsPath;
    }

    public String getXmlContactsPath() {
        return xmlContactsPath;
    }

    public String getXmlGroupsPath() {
        return xmlGroupsPath;
    }

    public String getXsdContactsPath() {
        return xsdContactsPath;
    }

    public String getXsdGroupsPath() {
        return xsdGroupsPath;
    }

    public String getFxmlStartWindowPath() {
        return fxmlStartWindowPath;
    }

    public String getFxmlAdditionalWindowPath() {
        return fxmlAdditionalWindowPath;
    }

    public String getFxmlMainWindowPath() {
        return fxmlMainWindowPath;
    }

    public String getFxmlContactEditorWindowPath() {
        return fxmlContactEditorWindowPath;
    }

    public String getFxmlGroupEditorWindowPath() {
        return fxmlGroupEditorWindowPath;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getFxmlAuthorizationWindowPath() {
        return fxmlAuthorizationWindowPath;
    }

    public String getFxmlAnalyticalInfoWindowPath() {
        return fxmlAnalyticalInfoWindowPath;
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }

        return instance;
    }

}
