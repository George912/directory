package ru.bellintegrator.app.dao.impl.orm.hibernate;

import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.util.List;
import java.util.Map;

//todo: убрать crud, синхронизация
public class HibernateAnalyticalInfoDAO implements AnalyticalInfoDAO {

    @Override
    public int getUserCount() throws DAOException {
        return 0;
    }

    @Override
    public Map<Integer, Long> getEachUserContactCount() throws DAOException {
        return null;
    }

    @Override
    public Map<Integer, Long> getEachUserGroupCount() throws DAOException {
        return null;
    }

    @Override
    public double getAvgUserCountInGroup() throws DAOException {
        return 0;
    }

    @Override
    public Map<Integer, Long> getInactiveUserCount() throws DAOException {
        return null;
    }

    @Override
    public double getAvgUserContactsCount() throws DAOException {
        return 0;
    }

    @Override
    public int create(AnalyticalInfo analyticalInfo) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Create operation not supported."));
    }

    @Override
    public void delete(AnalyticalInfo analyticalInfo) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Delete operation not supported."));
    }

    @Override
    public void update(AnalyticalInfo analyticalInfo) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Update operation not supported."));
    }

    @Override
    public List<AnalyticalInfo> getAll(int ownerId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetAll operation not supported."));
    }

    @Override
    public AnalyticalInfo getById(int id, int ownerId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetById operation not supported."));
    }

    @Override
    public List<AnalyticalInfo> getByName(String name, int ownerId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetByName operation not supported."));
    }

}
