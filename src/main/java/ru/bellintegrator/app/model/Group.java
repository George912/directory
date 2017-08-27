package ru.bellintegrator.app.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by neste_000 on 11.07.2017.
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    private int id;
    private String name;
    private String notes;
    private User owner;

    public Group() {
        this.name = "";
        this.notes = "";
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

    public Group(int id, String name, String notes, int owner) {
        this(id, name, notes);
//        this.owner = owner;
    }

    @Column(name="name", nullable=false, length=30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="notes", nullable=false, length=300)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner", nullable = false, referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
//        if (owner != group.owner) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        return notes != null ? notes.equals(group.notes) : group.notes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
//        result = 31 * result + owner;
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", owner=" + owner +
                '}';
    }

}
