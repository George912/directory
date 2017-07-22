package ru.bellintegrator.app.dao;

import ru.bellintegrator.app.model.Contact;

import java.io.Serializable;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public interface GenericDAO<T extends Serializable> {

    int create(T t);

    void delete(T t);

    void update(T t);

    List<T> getAll();

    void save(List<T> list);

}
