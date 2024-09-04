package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.exception.EntityNotFoundException;
import com.webapp.tp2_lab_javareact.mapper.EmployeeMapper;
import com.webapp.tp2_lab_javareact.repository.EmployeeRepository;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import com.webapp.tp2_lab_javareact.validation.EmployeeServiceValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeServiceValidation validation;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeServiceValidation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    //region -----------  HTTP METHODS  -----------
    @Override
    public EmployeeDTO getEmployee(Long id) {
        //TODO: mover esta validacion a EmployeeServiceValidation
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

        // Validaciones centralizadas para la creacion del empleado:
        this.validation.validateCreateEmployee(employeeDTO);

        // Establece la fecha de creacion del empleado (responsabilidad del sistema)
        employeeDTO.setCreationDate(LocalDateTime.now());

        // Convierte el DTO a entidad y la guarda en la DB
        Employee employee = EmployeeMapper.dtoToEmployee(employeeDTO);
        employee = this.repository.save( employee );

        // Convierte la entidad guardada de vuelta a DTO y la retorna
        return EmployeeMapper.employeeToDto(employee);
    }
    //endregion

    //region -----------  BUSINESS LAYER METHODS  -----------

    //endregion
}
