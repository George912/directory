package ru.bellintegrator.app.util;

import java.util.List;

/**
 * Created by neste_000 on 22.07.2017.
 */
public class IdGenerator {

    private int id;

    public IdGenerator(List<? extends Identifiable> list) {
        id = getMax(list);
    }

    private int getMax(List<? extends Identifiable> list) {
        int maxId = 0;
        for (Identifiable identifiable : list) {
            if (identifiable.getId() > maxId) {
                maxId = identifiable.getId();
            }
        }
        return maxId;
    }

    public int generateId() {
        return ++id;
    }

}
