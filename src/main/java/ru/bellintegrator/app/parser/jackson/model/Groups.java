package ru.bellintegrator.app.parser.jackson.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

/**
 * Created by neste_000 on 27.07.2017.
 */
@JacksonXmlRootElement(localName = "groups")
public final class Groups {

    @JacksonXmlElementWrapper(localName = "group", useWrapping = false)
    @JacksonXmlProperty(localName = "group")
    private Group[] groups;

    public Groups() {
    }

    public Groups(Group[] groups) {
        this.groups = groups;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groups=" + Arrays.toString(groups) +
                '}';
    }

}
