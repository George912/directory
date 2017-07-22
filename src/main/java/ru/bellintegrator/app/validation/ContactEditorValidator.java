package ru.bellintegrator.app.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.PersonalDataNotSetException;
import ru.bellintegrator.app.exception.PhoneNumberFormatException;

/**
 * Created by neste_000 on 22.07.2017.
 */
public class ContactEditorValidator {

    private static final Logger log = LoggerFactory.getLogger(ContactEditorValidator.class);
    private static ContactEditorValidator instance;

    private ContactEditorValidator() {
    }

    public static ContactEditorValidator getInstance() {

        if (instance == null) {
            instance = new ContactEditorValidator();
        }

        return instance;

    }

    public void validate(String lastName, String name, String middleName, String firstPhoneNumber, String secondPhoneNumber) throws PersonalDataNotSetException, PhoneNumberFormatException {

        StringBuilder personalDataErrorMessageStringBuilder = new StringBuilder("Не полные персональные данные: установите");
        boolean incorrectPersonalData = false;

        if (lastName.isEmpty()){
            personalDataErrorMessageStringBuilder.append(" фамилию");
            incorrectPersonalData = true;

        }else if (name.isEmpty()){
            personalDataErrorMessageStringBuilder.append(" имя");
            incorrectPersonalData = true;

        }else if (middleName.isEmpty()){
            personalDataErrorMessageStringBuilder.append(" отчество");
            incorrectPersonalData = true;
        }

        if(incorrectPersonalData)
            throw new PersonalDataNotSetException(personalDataErrorMessageStringBuilder.toString());

        if (!firstPhoneNumber.trim().matches("\\d*")
                || !secondPhoneNumber.matches("\\d*"))
            throw new PhoneNumberFormatException("Телефон должен содержать только цифры.");

    }

}
