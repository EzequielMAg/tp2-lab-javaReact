package com.webapp.tp2_lab_javareact.service;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public EmployeeDTO getEmployee(Long id);

    public List<EmployeeDTO> getEmployees();

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

}
