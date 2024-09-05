package com.webapp.tp2_lab_javareact.validation;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.exception.EntityAttributeExistsException;
import com.webapp.tp2_lab_javareact.exception.EntityNotFoundException;
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
    private final int MINIMUN_AGE_EMPLOYEE = 18;

    public EmployeeServiceValidation(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee validateFindById(Long id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow( () ->
                new EntityNotFoundException( NotificationMessage.employeeNotFound(id) ));
    }

    public void validateCreateEmployee(EmployeeDTO dto) throws RequiredAttributeException, InvalidAttributeException,
            EntityAttributeExistsException {
        this.validateMinimunAgeEmployee(dto.getBirthDate());
        this.validateExistsByDocumentNumber(dto.getDocumentNumber());
        this.validateExistsByEmail(dto.getEmail());
    }

    //region private void validateMinimunAgeEmployee(LocalDate birthDate) throws RequiredAttributeException, InvalidAttributeException {...}
    private void validateMinimunAgeEmployee(LocalDate birthDate) throws RequiredAttributeException, InvalidAttributeException {
        if(calculateAge(birthDate) < MINIMUN_AGE_EMPLOYEE) {
            throw new InvalidAttributeException( NotificationMessage.UNDERAGE_EMPLOYEE );
        }
    }

    private int calculateAge(LocalDate birthDate) throws RequiredAttributeException, InvalidAttributeException {
        this.validateBirthDate(birthDate);
        return Period.between(birthDate, LocalDate.now()).getYears(); // Calcula la diferencia en años
    }

    private void validateBirthDate(LocalDate birthDate) throws RequiredAttributeException, InvalidAttributeException {
        if (birthDate == null) {
            throw new RequiredAttributeException( NotificationMessage.BIRTH_DATE_IS_REQUIRED );
        }

        if(birthDate.isAfter(LocalDate.now())) {
            throw new InvalidAttributeException( NotificationMessage.INVALID_BIRTH_DATE );
        }
    }
    //endregion

    private void validateExistsByDocumentNumber(String documentNumber) throws EntityAttributeExistsException {
        if(this.repository.existsByDocumentNumber(documentNumber)) {
            throw new EntityAttributeExistsException( NotificationMessage.DNI_ALREADY_EXISTS );
        }
    }

    private void validateExistsByEmail(String email) throws EntityAttributeExistsException {
        if (this.repository.existsByEmail(email)) {
            throw new EntityAttributeExistsException( NotificationMessage.EMAIL_ALREADY_EXISTS );
        }
    }

    public Employee validateUpdateEmployee(Long id, EmployeeDTO dto) {
        Employee employeeToModify = this.validateFindById(id);
        this.validateCreateEmployee(dto);
        return employeeToModify;
    }
}
