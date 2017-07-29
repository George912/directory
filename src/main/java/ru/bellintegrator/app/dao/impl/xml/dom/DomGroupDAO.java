package ru.bellintegrator.app.dao.impl.xml.dom;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class DomGroupDAO implements GenericDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {
        return 0;
    }

    @Override
    public void delete(Group group) throws DAOException {

    }

    @Override
    public void update(Group group) throws DAOException {

    }

    @Override
    public List<Group> getAll() throws DAOException {
        return null;
    }

    public void save(List<Group> list) throws DAOException {

    }

    @Override
    public Group getById(int id) {
        return null;
    }

    @Override
    public List<Group> getByName(String name) {
        return null;
    }

}
