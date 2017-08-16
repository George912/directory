package ru.bellintegrator.app.model;

import java.io.Serializable;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Group {

    private int id;
    private String name;
    private String notes;
    private int ownerId;

    public Group() {
        this(0, "");
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, String notes) {
        this(id, name);
        this.notes = notes;
    }

    public Group(int id, String name, String notes, int ownerId) {
        this(id, name, notes);
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (ownerId != group.ownerId) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        return notes != null ? notes.equals(group.notes) : group.notes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + ownerId;
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

}
