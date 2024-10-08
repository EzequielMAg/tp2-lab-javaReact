package com.webapp.tp2_lab_javareact.repository;

import com.webapp.tp2_lab_javareact.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
