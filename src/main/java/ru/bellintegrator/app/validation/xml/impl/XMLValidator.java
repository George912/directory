package ru.bellintegrator.app.validation.xml.impl;

import org.xml.sax.SAXException;
import ru.bellintegrator.app.validation.xml.Validator;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by neste_000 on 27.07.2017.
 */
public class XMLValidator implements Validator {

    private String xmlFilePath;
    private String xsdFilePath;
    private String xsdLanguage;

    public XMLValidator(String xmlFilePath, String xsdFilePath, String xsdLanguage) {
        this.xmlFilePath = xmlFilePath;
        this.xsdFilePath = xsdFilePath;
        this.xsdLanguage = xsdLanguage;
    }

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public String getXsdFilePath() {
        return xsdFilePath;
    }

    public String getXsdLanguage() {
        return xsdLanguage;
    }

    @Override
    public void validate() throws SAXException, IOException {

        SchemaFactory factory = SchemaFactory.newInstance(xsdLanguage);

        File schemaLocation = new File(xsdFilePath);
        Schema schema = factory.newSchema(schemaLocation);

        javax.xml.validation.Validator validator = schema.newValidator();

        Source source = new StreamSource(xmlFilePath);

        validator.validate(source);

    }

}
