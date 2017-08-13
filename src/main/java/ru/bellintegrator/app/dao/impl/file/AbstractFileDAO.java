package ru.bellintegrator.app.dao.impl.file;

import ru.bellintegrator.app.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 01.08.2017.
 */
public abstract class AbstractFileDAO<T extends Serializable> extends ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator<T> {

    private String filePath;

    protected void serialize(List<T> list) throws DAOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(list);

            objectOutputStream.flush();

        } catch (IOException e) {
            throw new DAOException("Exception while serializing: " + e);
        }

    }

    protected List<T> deserialize() throws DAOException {
        List<T> list = null;

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            list = (List<T>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Exception while deserializing:" + e);
        }

        return list == null ? new ArrayList<>() : list;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
