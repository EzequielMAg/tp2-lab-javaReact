package com.webapp.tp2_lab_javareact.controller;

import com.webapp.tp2_lab_javareact.dto.EmployeeDTO;
import com.webapp.tp2_lab_javareact.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//http://localhost:8080/empleado/{empleadoId}

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@NotNull @PathVariable Long id) {
        EmployeeDTO employeeDTO = this.employeeService.getEmployee(id);
        return ResponseEntity.ok(employeeDTO);
    }

   /* @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeService
        return null;
    }*/

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeDTO = this.employeeService.createEmployee(employeeDTO);

        return ResponseEntity.created(URI.create( "/employee/" + employeeDTO.getId() )).body(employeeDTO);
    }


}
