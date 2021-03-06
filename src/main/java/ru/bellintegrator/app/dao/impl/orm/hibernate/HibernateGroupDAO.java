package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractConnectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

import java.util.List;

@Repository("grouoDao")
public class HibernateGroupDAO extends AbstractConnectable implements GenericDAO<Group> {

    private final static Object monitor = new Object();
    private static final Logger log = Logger.getLogger(HibernateGroupDAO.class);

    @Override
    public int create(Group group) throws DAOException {
        log.debug("Call create method: group = " + group);
        synchronized (monitor) {
            Transaction transaction = null;
            int groupId;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                groupId = (int) session.save(group);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                log.error("Exception while creating group: ", e);
                throw new DAOException("Exception while creating group: ", e);
            }

            return groupId;
        }
    }

    @Override
    public void delete(Group group) throws DAOException {
        log.debug("Call delete method: group = " + group);
        synchronized (monitor) {
            Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                Group persistGroup = session.get(Group.class, group.getId());

                session.delete(persistGroup);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                log.error("Exception while removing group: ", e);
                throw new DAOException("Exception while removing group: ", e);
            }
        }
    }

    @Override
    public void update(Group group) throws DAOException {
        log.debug("Call update method: group = " + group);
        synchronized (monitor) {
            Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                Group persistGroup = session.get(Group.class, group.getId());
                persistGroup.setName(group.getName());
                persistGroup.setNotes(group.getNotes());

                session.update(persistGroup);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                log.error("Exception while updating group: ", e);
                throw new DAOException("Exception while updating group: ", e);
            }
        }
    }

    @Override
    public List<Group> getAll(int ownerId) throws DAOException {
        log.debug("Call getAll method: ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                //get owner
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq(("id"), ownerId));
                User user = (User) criteria.list().get(0);
                criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq(("owner"), user));
                return criteria.list();

            } catch (HibernateException e) {
                log.error("Exception while retrieving group list: ", e);
                throw new DAOException("Exception while retrieving group list: ", e);
            }
        }
    }

    @Override
    public Group getById(int id, int ownerId) throws DAOException {
        log.debug("Call getById method: id = " + id + ", ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq(("id"), id));
                criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
                List result = criteria.list();

                if (!result.isEmpty()) {
                    return (Group) result.get(0);
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving group by id: ", e);
                throw new DAOException("Exception while retrieving group by id: ", e);
            }

            return null;
        }
    }

    @Override
    public List<Group> getByName(String name, int ownerId) throws DAOException {
        log.debug("Call getByName method: name = " + name + ", ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq(("name"), name));
                criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
                return criteria.list();

            } catch (HibernateException e) {
                log.error("Exception while retrieving group list by name: ", e);
                throw new DAOException("Exception while retrieving group list by name: ", e);
            }
        }
    }

}
