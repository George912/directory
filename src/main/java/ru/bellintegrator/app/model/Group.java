package ru.bellintegrator.app.model;

import java.io.Serializable;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Group implements Serializable{

    private static final long serialVersionUID = 8751089525686641L;
    private int id;
    private String name;
    private String notes;

    public Group() {

        this.name = "";

    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, String notes) {
        this(id, name);
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
