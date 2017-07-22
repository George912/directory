package ru.bellintegrator.app.util;

import java.util.List;

/**
 * Created by neste_000 on 22.07.2017.
 */
public class IdGenerator {

    private int id = 1;

    public IdGenerator(List<Rankable> list) {

        for (Rankable rankable : list) {
            if (rankable.getId() > id) {
                id = rankable.getId();
            }
        }

    }

    public int generateId() {

        return ++id;

    }

}
