package com.webapp.tp2_lab_javareact.repository;

import com.webapp.tp2_lab_javareact.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
