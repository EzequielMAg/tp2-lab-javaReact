package com.webapp.tp2_lab_javareact.tool;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NotificationMessage {

    //region -----------  CONSTANTS: ATTRIBUTE MESSAGE REQUIRED  -----------
    //region -----------  EMPLOYEE  -----------
    public static final String DOCUMENT_NUMBER_IS_REQUIRED = "'documentNumber' is required.";
    public static final String NAME_IS_REQUIRED = "'name' is required.";
    public static final String LASTNAME_IS_REQUIRED = "'lastName' is required.";
    public static final String EMAIL_IS_REQUIRED = "'email' is required.";
    public static final String BIRTH_DATE_IS_REQUIRED = "'birthDate' is required.";
    public static final String ENTRY_DATE_IS_REQUIRED = "'entryDate' is required.";
    //endregion

    // -----------  WORK SHIFT  -----------
    public static final String EMPLOYEE_ID_IS_REQUIRED = "'employeeId' is required.";
    public static final String CONCEPT_ID_IS_REQUIRED = "'conceptId' is required.";
    public static final String DATE_IS_REQUIRED = "'date' of the work shift is required.";
    public static final String HOURS_WORKED_IS_REQUIRED = "'hoursWorked'is required for the entered concept.";
    //endregion
    //endregion

    public static final String HOURS_WORKED_IS_NOT_REQUIRED = "The concept entered does not require the entry of 'hoursWorked'";
    public static final String WORK_HOURS_LIMIT_EXCEEDED = "An employee cannot work more than 14 hours in a single day.";
    public static final String WEEKLY_HOURS_LIMIT_EXCEEDED = "The entered employee exceeds the 52-hour weekly limit.";

    public static final String MONTHLY_HOURS_LIMIT_EXCEEDED = "The entered employee exceeds the 190-hour monthly limit.";

    public static final String EMPLOYEE_ALREADY_HAS_DAY_OFF = "The entered employee already has a day off on that date.";

    public static final String DUPLICATE_SHIFT_FOR_DATE = "The employee already has a shift with this concept registered on the entered date.";


    //region -----------  CONSTANTS: INVALID ATTRIBUTE MESSAGE  -----------
    public static final String INVALID_NAME = "Only letters are allowed in the 'name' field.";
    public static final String INVALID_LAST_NAME = "Only letters are allowed in the 'lastName' field.";
    public static final String INVALID_EMAIL = "The email entered is not correct.";
    public static final String INVALID_BIRTH_DATE = "The date of birth cannot be after today's date.";
    public static final String UNDERAGE_EMPLOYEE = "The employee's age cannot be less than 18 years.";
    public static final String INVALID_ENTRY_DATE = "The entry date cannot be after today's date.";

    public static final String INVALID_WORK_SHIFT_DATE = "The work shift date cannot be after today's date.";
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

    public String workShiftNotFound(Long id) {
        return "¡The work shift with id '" + id + "' was not found!";
    }

    public String workedHoursOutOfRange(Integer minimunHours, Integer maximumHours) {
        return  "The range of hours that can be charged for this concept is " + minimunHours + " - " + maximumHours + ".";
    }


    //endregion

}
