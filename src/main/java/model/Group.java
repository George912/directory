package model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Group {

    //<editor-fold desc="поля">

    private int id;
    private String name;
    private String notes;

    //</editor-fold>

    //<editor-fold desc="конструкторы">

    public Group() {
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, String notes) {
        this(id, name);
        this.notes = notes;
    }

    //</editor-fold>

    //<editor-fold desc="методы получения и установки">

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

    //</editor-fold>
}
