package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.AbstractConnectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;

import java.util.List;

//todo: синхронизация
public class HibernateGroupDAO extends AbstractConnectable implements GenericDAO<Group> {

    @Override
    public int create(Group group) throws DAOException {
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

            throw new DAOException("Exception while creating group: " + e);
        }

        return groupId;
    }

    @Override
    public void delete(Group group) throws DAOException {
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

            throw new DAOException("Exception while removing group: " + e);
        }
    }

    @Override
    public void update(Group group) throws DAOException {
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

            throw new DAOException("Exception while updating group: " + e);
        }
    }

    @Override
    public List<Group> getAll(int ownerId) throws DAOException {
        List<Group> groups;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            groups = session.createQuery("FROM Group").list();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while retrieving group list: " + e);
        }

        return groups;
    }

    @Override
    //todo
    public Group getById(int id, int ownerId) throws DAOException {
        Group group;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Group WHERE Group.id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            group = (Group) query.list().get(0);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while retrieving group list: " + e);
        }

        return group;
    }

    @Override
    //todo
    public List<Group> getByName(String name, int ownerId) throws DAOException {
        List<Group> groups;
        Transaction transaction = null;
        String hql = "FROM Group WHERE Group.name = :name AND Group.owner = :owner";

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("owner", ownerId);

            groups = query.list();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while retrieving group list: " + e);
        }

        return groups;
    }

}
