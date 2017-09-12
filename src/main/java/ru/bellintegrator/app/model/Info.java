package ru.bellintegrator.app.model;

public class Info {
    private Integer id;
    private Long count;

    public Info(Integer id, Long count) {
        this.count = count;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Long getCount() {
        return count;
    }
}
