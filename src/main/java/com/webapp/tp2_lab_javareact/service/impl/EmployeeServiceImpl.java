package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;

import com.webapp.tp2_lab_javareact.exception.EntityAttributeExistsException;
import com.webapp.tp2_lab_javareact.exception.EntityNotFoundException;
//import jakarta.persistence.EntityNotFoundException;

import com.webapp.tp2_lab_javareact.mapper.EmployeeMapper;
import com.webapp.tp2_lab_javareact.repository.EmployeeRepository;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

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
    public EmployeeDTO createEmployee(EmployeeDTO employeDTO) {

        String email = employeDTO.getEmail();
        String documentNumber = employeDTO.getDocumentNumber();

        if (this.repository.existsByEmail(email)) {
            throw new EntityAttributeExistsException( NotificationMessage.emailAlreadyExists(email));
        }

        if(this.repository.existsByDocumentNumber(documentNumber)) {
            throw new EntityAttributeExistsException( NotificationMessage.dniAlreadyExists(documentNumber));
        }

        Employee employee = this.repository.save( EmployeeMapper.dtoToEmployee(employeDTO) );
        return EmployeeMapper.employeeToDto(employee);
    }
}
