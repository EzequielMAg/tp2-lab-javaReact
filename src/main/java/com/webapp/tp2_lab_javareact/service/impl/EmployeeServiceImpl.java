package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;

import com.webapp.tp2_lab_javareact.exception.EntityAttributeExistsException;
import com.webapp.tp2_lab_javareact.exception.EntityNotFoundException;
//import jakarta.persistence.EntityNotFoundException;

import com.webapp.tp2_lab_javareact.exception.RequiredAttributeException;
import com.webapp.tp2_lab_javareact.mapper.EmployeeMapper;
import com.webapp.tp2_lab_javareact.repository.EmployeeRepository;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    //region -----------  HTTP METHODS  -----------
    @Override
    public EmployeeDTO getEmployee(Long id) {

        Employee entity = this.repository.findById(id).orElseThrow( () ->
                new EntityNotFoundException( NotificationMessage.employeeNotFound(id) ));

        return EmployeeMapper.employeeToDto(entity);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return null;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        String email = employeeDTO.getEmail();
        String documentNumber = employeeDTO.getDocumentNumber();

        if (this.repository.existsByEmail(email)) {
            throw new EntityAttributeExistsException( NotificationMessage.emailAlreadyExists(email));
        }

        if(this.repository.existsByDocumentNumber(documentNumber)) {
            throw new EntityAttributeExistsException( NotificationMessage.dniAlreadyExists(documentNumber));
        }

        // Establece la fecha de creacion del empleado (responsabilidad del sistema)
        employeeDTO.setCreationDate(LocalDate.now());

        // Convierte el DTO a entidad y la guarda en la DB
        Employee employee = EmployeeMapper.dtoToEmployee(employeeDTO);
        employee = this.repository.save( employee );

        // Convierte la entidad guardada de vuelta a DTO y la retorna
        return EmployeeMapper.employeeToDto(employee);
    }
    //endregion

    //region -----------  BUSINESS LAYER METHODS  -----------
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        if (birthDate == null) {
            throw new RequiredAttributeException( NotificationMessage.BIRTH_DATE_IS_REQUIRED );
        }

        if(birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException( NotificationMessage.INVALID_BIRTH_DATE );
        }

        return Period.between(birthDate, currentDate).getYears(); // Calcula la diferencia en años
    }
    //endregion
}
