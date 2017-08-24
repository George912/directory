package ru.bellintegrator.app.dao.impl.orm.hibernate;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

import java.util.List;

//todo: синхронизация
public class HibernateGroupDAO implements GenericDAO<Group> {

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
    public List<Group> getAll(int ownerId) throws DAOException {
        return null;
    }

    @Override
    public Group getById(int id, int ownerId) throws DAOException {
        return null;
    }

    @Override
    public List<Group> getByName(String name, int ownerId) throws DAOException {
        return null;
    }

}
