package com.webapp.tp2_lab_javareact.tool;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NotificationMessage {

    //region -----------  CONSTANTS: ATTRIBUTE MESSAGE REQUIRED  -----------
    public static final String DOCUMENT_NUMBER_IS_REQUIRED = "The documentNumber is required.";
    public static final String NAME_IS_REQUIRED = "The name is required.";
    public static final String LASTNAME_IS_REQUIRED = "The lastName is required.";
    public static final String EMAIL_IS_REQUIRED = "The email is required.";
    public static final String BIRTH_DATE_IS_REQUIRED = "The birthDate is required.";
    public static final String ENTRY_DATE_IS_REQUIRED = "The entryDate is required.";
    //endregion

    //region -----------  CONSTANTS: INVALID ATTRIBUTE MESSAGE  -----------
    public static final String INVALID_EMAIL = "You must provide a valid email.";
    public static final String INVALID_BIRTH_DATE = "The date of birth must be before the current date.";
    //endregion

    //region -----------  METHODS THAT RETURN A MESSAGE: ATTRIBUTE ALREADY EXISTS  -----------
    public String emailAlreadyExists(String email) {
        return "¡The email " + email + " already exists!";
    }

    public String dniAlreadyExists(String dni) {
        return "¡The documentNumber " + dni + " already exists!";
    }
    //endregion

    //region OTHERS METHODS THAT RETURN A MESSAGE
    public String employeeNotFound(Long id) {
        return "¡The employee with id " + id + " was not found!";
    }
    //endregion

}
