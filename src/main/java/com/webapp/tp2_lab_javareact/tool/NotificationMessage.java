package com.webapp.tp2_lab_javareact.tool;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NotificationMessage {

    public String employeeNotFound(Long id) {
        return "¡The employee with id " + id + " was not found!";
    }

    public String emailAlreadyExists(String email) {
        return "¡The email " + email + " already exists!";
    }

    public String dniAlreadyExists(String dni) {
        return "¡The documentNumber " + dni + " already exists!";
    }
}
