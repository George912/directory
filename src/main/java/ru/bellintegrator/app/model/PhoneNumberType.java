package ru.bellintegrator.app.model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public enum PhoneNumberType {
    MOBILE("Мобильный"),
    HOME("Домашний"),
    WORKING("Рабочий"),
    UNKNOWN("Неизвестный");

    private final String name;

    PhoneNumberType(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static PhoneNumberType getPhoneNumberTypeByTypeDescription(String typeDescription) {

        for (PhoneNumberType phoneNumberType : values()) {
            if (phoneNumberType.getName().equalsIgnoreCase(typeDescription)) {
                return phoneNumberType;
            }
        }

        return UNKNOWN;

    }

    public static PhoneNumberType getPhoneNumberTypeByTypeName(String typeName) {

        for (PhoneNumberType phoneNumberType : values()) {
            if (phoneNumberType.name().equalsIgnoreCase(typeName)) {
                return phoneNumberType;
            }
        }

        return UNKNOWN;

    }
}
