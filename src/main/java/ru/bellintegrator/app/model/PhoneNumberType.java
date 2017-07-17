package ru.bellintegrator.app.model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public enum PhoneNumberType {
    MOBILE,
    HOME,
    WORKING,
    UNKNOWN;

    public static String getStringFromPhoneNumberType(PhoneNumberType phoneNumberType) {

        String s = null;

        switch (phoneNumberType) {
            case MOBILE:
                s = "Мобильный";
                break;

            case HOME:
                s = "Домашний";
                break;

            case WORKING:
                s = "Рабочий";
                break;
            case UNKNOWN:
                s = "";
                break;
        }

        return s;

    }

    public static PhoneNumberType getPhoneNumberTypeFromString(String s) {

        PhoneNumberType phoneNumberType;

        switch (s){
            case "Мобильный":
                phoneNumberType = PhoneNumberType.MOBILE;
                break;
            case "Домашний":
                phoneNumberType = PhoneNumberType.HOME;
                break;
            case "Рабочий":
                phoneNumberType = PhoneNumberType.WORKING;
                break;
            default:
                phoneNumberType = PhoneNumberType.UNKNOWN;
                break;
        }

        return phoneNumberType;

    }
}
