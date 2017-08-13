package ru.bellintegrator.app.dao.impl.sql.postgresql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresqlAnalyticalInfoDAO implements AnalyticalInfoDAO, Connectable {

    private final static Object monitor = new Object();

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

    @Override
    public int getUserCount() throws DAOException {
        synchronized (monitor) {
            String query = "{call get_user_count()}";
            int userCount = -1;
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<Integer> handler = new ScalarHandler<>(1);
                List<Integer> lists = runner.execute(connection, query, handler);
                userCount = lists.get(0);

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return userCount;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserContactCount() throws DAOException {
        synchronized (monitor) {
            String query = "{call get_each_user_contact_count()}";
            Map<Integer, Long> info = new HashMap<>();

            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<List<Map<String, Object>>> handler = new MapListHandler();
                List<List<Map<String, Object>>> lists = runner.execute(connection, query, handler);

                for (Map<String, Object> map : lists.get(0)) {
                    info.put((Integer) map.get("user_id"), (Long) map.get("contact_count"));
                }

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return info;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserGroupCount() throws DAOException {
        synchronized (monitor) {
            String query = "{call get_each_user_group_count()}";
            Map<Integer, Long> info = new HashMap<>();

            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<List<Map<String, Object>>> handler = new MapListHandler();
                List<List<Map<String, Object>>> lists = runner.execute(connection, query, handler);

                for (Map<String, Object> map : lists.get(0)) {
                    info.put((Integer) map.get("user_id"), (Long) map.get("group_count"));
                }

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return info;
        }
    }

    @Override
    public double getAvgUserCountInGroup() throws DAOException {
        synchronized (monitor) {
            String query = "{call avg_user_count_in_groups()}";
            double avgUserCount = -1;
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<BigDecimal> handler = new ScalarHandler<>(1);
                List<BigDecimal> lists = runner.execute(connection, query, handler);
                avgUserCount = lists.get(0).doubleValue();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return avgUserCount;
        }
    }

    @Override
    public int getInactiveUserCount() throws DAOException {
        synchronized (monitor) {
            String query = "{call get_inactive_user_count()}";
            int userCount = -1;
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<Integer> handler = new ScalarHandler<>(1);
                List<Integer> lists = runner.execute(connection, query, handler);
                userCount = lists.get(0);

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return userCount;
        }
    }

    @Override
    public double getAvgUserContactsCount() throws DAOException {
        synchronized (monitor) {
            String query = "{call avg_users_contact_count()}";
            double count = -1;

            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<BigDecimal> handler = new ScalarHandler<>(1);
                List<BigDecimal> lists = runner.execute(connection, query, handler);
                count = lists.get(0).doubleValue();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return count;
        }
    }

}
