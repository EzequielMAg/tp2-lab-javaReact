package com.webapp.tp2_lab_javareact.service;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO getEmployee(Long id);

    List<EmployeeDTO> getEmployees();

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
}
