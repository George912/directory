package ru.bellintegrator.app.util;

import ru.bellintegrator.app.model.PhoneNumberType;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class Util {

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
        }

        return s;

    }

    public static PhoneNumberType getPhoneNumberTypeFromString(String s) {

        PhoneNumberType phoneNumberType = null;

        if ("мобильный".equals(s.toLowerCase()))
            phoneNumberType = PhoneNumberType.MOBILE;

        else if ("домашний".equals(s.toLowerCase()))
            phoneNumberType = PhoneNumberType.HOME;

        else if ("рабочий".equals(s.toLowerCase()))
            phoneNumberType = PhoneNumberType.WORKING;

        return phoneNumberType;

    }

}
