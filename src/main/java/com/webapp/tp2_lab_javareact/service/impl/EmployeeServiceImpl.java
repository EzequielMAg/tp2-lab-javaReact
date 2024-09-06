package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.mapper.EmployeeMapper;
import com.webapp.tp2_lab_javareact.repository.EmployeeRepository;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import com.webapp.tp2_lab_javareact.validation.EmployeeServiceValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeServiceValidation validation;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeServiceValidation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = this.validation.validateFindById(id);
        return EmployeeMapper.employeeToDto(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = this.repository.findAll();
        return employees.stream().map(EmployeeMapper::employeeToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        this.validation.validateCreateEmployee(employeeDTO); // Validaciones centralizadas para la creacion del empleado
        employeeDTO.setCreationDate(LocalDateTime.now()); // Establece la fecha de creacion (responsabilidad del sistema)

        // Convierte el DTO a entidad y la guarda en la DB
        Employee employee = EmployeeMapper.dtoToEmployee(employeeDTO);
        employee = this.repository.save( employee );

        return EmployeeMapper.employeeToDto(employee); // Convierte la entidad guardada de vuelta a DTO y la retorna
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {

        Employee employeeToModify = this.validation.validateUpdateEmployee(id, dto);

        employeeToModify.setDocumentNumber(dto.getDocumentNumber());
        employeeToModify.setName(dto.getName());
        employeeToModify.setLastName(dto.getLastName());
        employeeToModify.setEmail(dto.getEmail());
        employeeToModify.setBirthDate(dto.getBirthDate());
        employeeToModify.setEntryDate(dto.getEntryDate());

        Employee employeeSaved = this.repository.save(employeeToModify);
        return EmployeeMapper.employeeToDto(employeeSaved);
    }

}
