package com.webapp.tp2_lab_javareact.controller;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO employee = this.employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employees = this.employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
        dto = this.employeeService.createEmployee(dto);
        return ResponseEntity.created(URI.create( "/employee/" + dto.getId() )).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {

        return ResponseEntity.ok(this.employeeService.updateEmployee(id, dto));
    }


}
