package ru.bellintegrator.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoContainer {
    List<Info> infoList;

    public InfoContainer(Map<Integer, Long> infoMap) {
        infoList = new ArrayList<>();

        for (Map.Entry<Integer, Long> entry : infoMap.entrySet()) {
            infoList.add(new Info(entry.getKey(), entry.getValue()));
        }
    }

    public List<Info> getInfoList() {
        return infoList;
    }
}
