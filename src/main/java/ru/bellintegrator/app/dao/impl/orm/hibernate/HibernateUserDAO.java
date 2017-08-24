package ru.bellintegrator.app.dao.impl.orm.hibernate;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

import java.util.List;

//todo: синхронизация
public class HibernateUserDAO implements GenericDAO<User>, UserDAO {

    @Override
    public int create(User user) throws DAOException {
        return 0;
    }

    @Override
    public void delete(User user) throws DAOException {

    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public List<User> getAll(int ownerId) throws DAOException {
        return null;
    }

    @Override
    public User getById(int id, int ownerId) throws DAOException {
        return null;
    }

    @Override
    public List<User> getByName(String name, int ownerId) throws DAOException {
        return null;
    }

    @Override
    public User getUserByCredential(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public int getUserId(String login, String password) throws DAOException {
        return 0;
    }

}
