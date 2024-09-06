package com.webapp.tp2_lab_javareact.mapper;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeMapper {

    public Employee dtoToEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId())
                .documentNumber(dto.getDocumentNumber())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .entryDate(dto.getEntryDate())
                .creationDate(dto.getCreationDate())
                .build();
    }

    //TODO: como voy a cambiar a LocalDate para mostrar como pide la docu, del LocalDateTime de la entity
    //      tengo que agarrar solo la fecha en este mapper
    public EmployeeDTO employeeToDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .documentNumber(employee.getDocumentNumber())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .entryDate(employee.getEntryDate())
                .creationDate(employee.getCreationDate())
                .build();
    }
}
