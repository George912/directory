package ru.bellintegrator.app.parser.jackson.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

/**
 * Created by neste_000 on 27.07.2017.
 */
@JacksonXmlRootElement(localName = "jacksonGroups")
public class JacksonGroups {

    @JacksonXmlProperty(localName = "group")
    @JacksonXmlElementWrapper(useWrapping = false)
    private JacksonGroup[] jacksonGroups;

    public JacksonGroups() {
    }

    public JacksonGroups(JacksonGroup[] jacksonGroups) {
        this.jacksonGroups = jacksonGroups;
    }

    public JacksonGroup[] getJacksonGroups() {
        return jacksonGroups;
    }

    public void setJacksonGroups(JacksonGroup[] jacksonGroups) {
        this.jacksonGroups = jacksonGroups;
    }

    @Override
    public String toString() {
        return "JacksonGroups{" +
                "jacksonGroups=" + Arrays.toString(jacksonGroups) +
                '}';
    }

}
