package com.webapp.tp2_lab_javareact.repository;

import com.webapp.tp2_lab_javareact.entity.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {
    List<WorkShift> findByDate(LocalDate date);
    List<WorkShift> findByDateAndEmployeeId(LocalDate date, Long employeeId);

    Boolean existsByDateAndEmployeeIdAndConceptName(LocalDate date, Long employeeId, String conceptName);

    List<WorkShift> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
}
