package com.webapp.tp2_lab_javareact.validation;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.exception.EntityAttributeExistsException;
import com.webapp.tp2_lab_javareact.exception.InvalidAttributeException;
import com.webapp.tp2_lab_javareact.exception.RequiredAttributeException;
import com.webapp.tp2_lab_javareact.repository.EmployeeRepository;
import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class EmployeeServiceValidation {

    private final EmployeeRepository repository;

    public EmployeeServiceValidation(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void validateCreateEmployee(EmployeeDTO employeeDTO) {
        int MINIMUN_AGE_EMPLOYEE = 18;

        if(calculateAge(employeeDTO.getBirthDate()) < MINIMUN_AGE_EMPLOYEE) {
            throw new InvalidAttributeException( NotificationMessage.UNDERAGE_EMPLOYEE );
        }

        if(this.repository.existsByDocumentNumber(employeeDTO.getDocumentNumber())) {
            throw new EntityAttributeExistsException( NotificationMessage.DNI_ALREADY_EXISTS );
        }

        if (this.repository.existsByEmail(employeeDTO.getEmail())) {
            throw new EntityAttributeExistsException( NotificationMessage.EMAIL_ALREADY_EXISTS );
        }

        if(employeeDTO.getEntryDate().isAfter(LocalDate.now())) {
            throw new InvalidAttributeException( NotificationMessage.INVALID_ENTRY_DATE );
        }
    }

    private static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        if (birthDate == null) {
            throw new RequiredAttributeException( NotificationMessage.BIRTH_DATE_IS_REQUIRED );
        }

        if(birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException( NotificationMessage.INVALID_BIRTH_DATE );
        }

        return Period.between(birthDate, currentDate).getYears(); // Calcula la diferencia en años
    }


}
