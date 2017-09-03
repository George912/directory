package ru.bellintegrator.app.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.app.dao.impl.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true, rollbackFor = DAOException.class)
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

    //todo crud operations for user
    @Override
    @Transactional(rollbackFor = DAOException.class)
    public void add(User user) throws ServiceException {

    }

    @Override
    @Transactional(rollbackFor = DAOException.class)
    public void update(User user) throws ServiceException {

    }

    @Override
    @Transactional(rollbackFor = DAOException.class)
    public void delete(User user) throws ServiceException {

    }

    @Override
    public List<User> list(int ownerId) throws ServiceException {
        return null;
    }

    @Override
    public User findById(int id, int ownerId) throws ServiceException {
        return null;
    }

    @Override
    public List<User> findByName(String name, int ownerId) throws ServiceException {
        return null;
    }
}
