package ru.bellintegrator.app.dao;

import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 19.07.2017.
 */
public interface GroupDAO {

    int insertGroup(Group group);

    void deleteGroup(Group group);

    void updateGroup(Group group);

    List<Group> getAllGroups();

}
