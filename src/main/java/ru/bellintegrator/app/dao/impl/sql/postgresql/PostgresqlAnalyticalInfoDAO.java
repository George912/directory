package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.util.List;
import java.util.Map;

public class PostgresqlAnalyticalInfoDAO implements AnalyticalInfoDAO{

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
    public List<AnalyticalInfo> getAll() throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetAll operation not supported."));
    }

    @Override
    public AnalyticalInfo getById(int id) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetById operation not supported."));
    }

    @Override
    public List<AnalyticalInfo> getByName(String name) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("GetByName operation not supported."));
    }

    @Override
    public int getUserCount() {
        return 0;
    }

    @Override
    public Map<Integer, Integer> getEachUserContactCount() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getEachUserGroupCount() {
        return null;
    }

    @Override
    public double getAvgUserCountInGroup() {
        return 0;
    }

    @Override
    public int getInactiveUserCount() {
        return 0;
    }

    @Override
    public double getAvgUserContactsCount() {
        return 0;
    }
}
