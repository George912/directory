package ru.bellintegrator.app;

/**
 * Created by neste_000 on 12.07.2017.
 */
public enum EditorAction {
    CREATE,
    CREATED,
    UPDATE,
    UPDATED,
    DELETE,
    UNKNOWN;

    public static EditorAction getActionFromString(String act) {
        for (EditorAction action : values()) {
            if (action.name().equalsIgnoreCase(act)) {
                return action;
            }
        }

        return UNKNOWN;
    }

}
