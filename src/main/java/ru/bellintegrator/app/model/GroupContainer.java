package ru.bellintegrator.app.model;

import java.util.List;

public class GroupContainer {
    private List<Group> groups;

    public GroupContainer(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
