package ru.bellintegrator.app.model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public enum PhoneNumberType {
    MOBILE("Мобильный"),
    HOME("Домашний"),
    WORKING("Рабочий"),
    UNKNOWN("Неизвестный");

    private String name;

    PhoneNumberType(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static PhoneNumberType getPhoneNumberTypeByTypeName(String typeName) {

        for (PhoneNumberType phoneNumberType : values()) {
            if (phoneNumberType.getName().equalsIgnoreCase(typeName)) {
                return phoneNumberType;
            }
        }

        return UNKNOWN;

    }
}
