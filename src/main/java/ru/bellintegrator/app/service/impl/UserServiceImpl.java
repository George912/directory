package ru.bellintegrator.app.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.app.dao.impl.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl implements UserService {

    private UserDAO dao;
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
        log.debug("Initialize UserService");
        log.info("UserService instance created");
    }

    @Override
    public User getUserByCredential(String login, String password) throws ServiceException {
        log.debug("Call getUserByCredential method: login = " + login + ", password=" + password);
        try {
            return dao.getUserByCredential(login, password);

        } catch (DAOException e) {
            log.error("Exception while retrieving user by credential: ", e);
            throw new ServiceException("Exception while retrieving user by credential: ", e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void add(User user) throws ServiceException {
        log.info("Call add method: user = " + user);
        try {
            dao.create(user);

        } catch (DAOException e) {
            log.error("Exception while adding user: ", e);
            throw new ServiceException("Exception while adding user: ", e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(User user) throws ServiceException {
        log.info("Call update method: user = " + user);
        try {
            dao.update(user);

        } catch (DAOException e) {
            log.error("Exception while updating user: ", e);
            throw new ServiceException("Exception while updating user: ", e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(User user) throws ServiceException {
        log.info("Call delete method: user = " + user);
        try {
            dao.delete(user);

        } catch (DAOException e) {
            log.error("Exception while removing user: ", e);
            throw new ServiceException("Exception while removing user: ", e);
        }
    }

    @Override
    public List<User> list(int ownerId) throws ServiceException {
        log.info("Call list method: ownerId = " + ownerId);
        try {
            return dao.getAll(ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving user list: ", e);
            throw new ServiceException("Exception while retrieving user list: ", e);
        }
    }

    @Override
    public User findById(int id, int ownerId) throws ServiceException {
        log.info("Call findById method: id = " + id + ", ownerId = " + ownerId);
        try {
            return dao.getById(id, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving user by id: ", e);
            throw new ServiceException("Exception while retrieving user by id: ", e);
        }
    }

    @Override
    public List<User> findByName(String name, int ownerId) throws ServiceException {
        log.info("Call findByName method: name = " + name + ", ownerId = " + ownerId);
        try {
            return dao.getByName(name, ownerId);

        } catch (DAOException e) {
            log.error("Exception while retrieving user list by name: ", e);
            throw new ServiceException("Exception while retrieving user list by name: ", e);
        }
    }

}
