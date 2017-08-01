package ru.bellintegrator.app.dao.factory;

/**
 * Created by neste_000 on 19.07.2017.
 */
public enum DAOFactoryType {

    XML_DOM,
    XML_SAX,
    XML_JACKSON,
    ORACLE,
    MYSQL,
    MSSQL,
    POSTGRESQL,
    FILE,
    UNKNOWN;

    public static DAOFactoryType getTypeByName(String typeName) {
        for (DAOFactoryType type : values()) {
            if (type.name().equalsIgnoreCase(typeName)) {
                return type;
            }
        }

        return UNKNOWN;
    }

}
