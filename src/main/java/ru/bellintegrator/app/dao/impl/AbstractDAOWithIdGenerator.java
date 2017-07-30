package ru.bellintegrator.app.dao.impl;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.util.IdGenerator;

import java.io.Serializable;

/**
 * Created by neste_000 on 23.07.2017.
 */
public abstract class AbstractDAOWithIdGenerator<T extends Serializable> implements GenericDAO<T> {

    private IdGenerator idGenerator;

    protected int generateId() {
        return idGenerator.generateId();
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}
