package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.bellintegrator.app.dao.impl.AbstractConnectable;
import ru.bellintegrator.app.dao.impl.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;

import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateAnalyticalInfoDAO extends AbstractConnectable implements AnalyticalInfoDAO {

    private final static Object monitor = new Object();

    @Override
    public int getUserCount() throws DAOException {
        synchronized (monitor) {
            int count = 0;

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_user_count");

                if (procedureQuery.execute()) {
                    count = (int) procedureQuery.getSingleResult();
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving user count: " + e);
            }

            return count;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserContactCount() throws DAOException {
        synchronized (monitor) {
            Map<Integer, Long> map = new HashMap<>();

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_each_user_contact_count");

                if (procedureQuery.execute()) {
                    map = convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving each user contact count: " + e);
            }

            return map;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserGroupCount() throws DAOException {
        synchronized (monitor) {
            Map<Integer, Long> map = new HashMap<>();

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_each_user_group_count");

                if (procedureQuery.execute()) {
                    map = convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving each user group count: " + e);
            }

            return map;
        }
    }

    @Override
    public double getAvgUserCountInGroup() throws DAOException {
        synchronized (monitor) {
            double count = 0;

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("avg_user_count_in_groups");

                if (procedureQuery.execute()) {
                    count = ((BigDecimal) procedureQuery.getSingleResult()).doubleValue();
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving average user count in groups: " + e);
            }

            return count;
        }
    }

    @Override
    public Map<Integer, Long> getInactiveUserCount() throws DAOException {
        synchronized (monitor) {
            Map<Integer, Long> map = new HashMap<>();

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_inactive_users");

                if (procedureQuery.execute()) {
                    map = convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving inactive user count: " + e);
            }

            return map;
        }
    }

    @Override
    public double getAvgUserContactsCount() throws DAOException {
        synchronized (monitor) {
            double count = 0;

            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("avg_users_contact_count");

                if (procedureQuery.execute()) {
                    count = ((BigDecimal) procedureQuery.getSingleResult()).doubleValue();
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving average user contact count: " + e);
            }

            return count;
        }
    }

    private Map<Integer, Long> convertResultListToMap(List list) {
        Map<Integer, Long> map = new HashMap<>();

        for (Object o : list) {
            Object[] row = (Object[]) o;
            map.put((int) row[0], ((BigInteger) row[1]).longValue());
        }

        return map;
    }

}
