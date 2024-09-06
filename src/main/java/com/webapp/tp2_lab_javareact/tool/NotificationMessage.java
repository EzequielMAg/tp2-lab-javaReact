package com.webapp.tp2_lab_javareact.tool;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NotificationMessage {

    //region -----------  CONSTANTS: ATTRIBUTE MESSAGE REQUIRED  -----------
    public static final String DOCUMENT_NUMBER_IS_REQUIRED = "'documentNumber' is required.";
    public static final String NAME_IS_REQUIRED = "'name' is required.";
    public static final String LASTNAME_IS_REQUIRED = "'lastName' is required.";
    public static final String EMAIL_IS_REQUIRED = "'email' is required.";
    public static final String BIRTH_DATE_IS_REQUIRED = "'birthDate' is required.";
    public static final String ENTRY_DATE_IS_REQUIRED = "'entryDate' is required.";
    //endregion

    //region -----------  CONSTANTS: INVALID ATTRIBUTE MESSAGE  -----------
    public static final String INVALID_NAME = "Only letters are allowed in the 'name' field";
    public static final String INVALID_LAST_NAME = "Only letters are allowed in the 'lastName' field";
    public static final String INVALID_EMAIL = "The email entered is not correct.";
    public static final String INVALID_BIRTH_DATE = "The date of birth cannot be after today's date.";
    public static final String UNDERAGE_EMPLOYEE = "The employee's age cannot be less than 18 years.";
    public static final String INVALID_ENTRY_DATE = "The entry date cannot be after today's date.";
    //endregion

    //region -----------  CONSTANTS: ATTRIBUTE ALREADY EXISTS  -----------
    public static final String EMAIL_ALREADY_EXISTS = "An employee with the entered email already exists.";

    public static final String DNI_ALREADY_EXISTS = "There is already an employee with the document entered.";
    //endregion

    //region OTHERS METHODS THAT RETURN A MESSAGE
    public String employeeNotFound(Long id) {
        return "¡The employee with id '" + id + "' was not found!";
    }

    public String laboralConceptNotFound(Long id) {
        return "¡The laboral concept with id '" + id + "' was not found!";
    }

    public String laboralConceptNotFound(String name) {
        return "¡The laboral concept with name '" + name + "' was not found!";
    }
    //endregion

}
