package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresqlAnalyticalInfoDAO implements AnalyticalInfoDAO, Connectable {

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
    public int getUserCount() throws DAOException {
        String query = "{call get_user_count()}";
        int userCount = -1;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

                //todo: use row mapper
                while (resultSet.next()) {
                }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting user count:" + e);
        }

        return userCount;
    }

    @Override
    public Map<Integer, Integer> getEachUserContactCount() throws DAOException {
        String query = "{call get_each_user_contact_count()}";
        Map<Integer, Integer> info = new HashMap<>();

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting each user contact count:" + e);
        }

        return info;
    }

    @Override
    public Map<Integer, Integer> getEachUserGroupCount() throws DAOException {
        String query = "{call get_each_user_group_count()}";
        Map<Integer, Integer> info = new HashMap<>();

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting each user group count:" + e);
        }

        return info;
    }

    @Override
    public double getAvgUserCountInGroup() throws DAOException {
        String query = "{call avg_user_count_in_groups()}";
        double userCount = -1;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting avg user count in group:" + e);
        }

        return userCount;
    }

    @Override
    public int getInactiveUserCount() throws DAOException {
        String query = "{call get_inactive_user_count()}";
        int userCount = -1;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting inactive user count:" + e);
        }

        return userCount;
    }

    @Override
    public double getAvgUserContactsCount() throws DAOException {
        String query = "{call avg_users_contact_count()}";
        double userCount = -1;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting avg user contacts count:" + e);
        }

        return userCount;
    }

}
