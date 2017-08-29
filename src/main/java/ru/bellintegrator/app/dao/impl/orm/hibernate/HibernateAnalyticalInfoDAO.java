package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
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
    private static final Logger log = Logger.getLogger(HibernateAnalyticalInfoDAO.class);

    @Override
    public int getUserCount() throws DAOException {
        log.debug("Call getUserCount method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_user_count");

                if (procedureQuery.execute()) {
                    return (int) procedureQuery.getSingleResult();
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving user count", e);
                throw new DAOException("Exception while retrieving user count: ", e);
            }

            return -1;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserContactCount() throws DAOException {
        log.debug("Call getEachUserContactCount method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_each_user_contact_count");

                if (procedureQuery.execute()) {
                    return convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving each user contact count", e);
                throw new DAOException("Exception while retrieving each user contact count: ", e);
            }

            return null;
        }
    }

    @Override
    public Map<Integer, Long> getEachUserGroupCount() throws DAOException {
        log.debug("Call getEachUserGroupCount method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_each_user_group_count");

                if (procedureQuery.execute()) {
                    return convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving each user group count", e);
                throw new DAOException("Exception while retrieving each user group count: ", e);
            }

            return null;
        }
    }

    @Override
    public double getAvgUserCountInGroup() throws DAOException {
        log.debug("Call getAvgUserCountInGroup method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("avg_user_count_in_groups");

                if (procedureQuery.execute()) {
                    return ((BigDecimal) procedureQuery.getSingleResult()).doubleValue();
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving average user count in groups", e);
                throw new DAOException("Exception while retrieving average user count in groups: ", e);
            }

            return -1;
        }
    }

    @Override
    public Map<Integer, Long> getInactiveUserCount() throws DAOException {
        log.debug("Call getInactiveUserCount method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("get_inactive_users");

                if (procedureQuery.execute()) {
                    return convertResultListToMap(procedureQuery.getResultList());
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving inactive user count", e);
                throw new DAOException("Exception while retrieving inactive user count: ", e);
            }

            return null;
        }
    }

    @Override
    public double getAvgUserContactsCount() throws DAOException {
        log.debug("Call getInactiveUserCount method");
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("avg_users_contact_count");

                if (procedureQuery.execute()) {
                    return ((BigDecimal) procedureQuery.getSingleResult()).doubleValue();
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving average user contact count", e);
                throw new DAOException("Exception while retrieving average user contact count: ", e);
            }

            return -1;
        }
    }

    private Map<Integer, Long> convertResultListToMap(List list) {
        log.debug("Call convertResultListToMap method: list to convert = " + list);
        Map<Integer, Long> map = new HashMap<>();

        for (Object o : list) {
            Object[] row = (Object[]) o;
            map.put((int) row[0], ((BigInteger) row[1]).longValue());
        }

        log.debug("Call convertResultListToMap method: converted map = " + map);

        return map;
    }

}
