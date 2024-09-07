package com.webapp.tp2_lab_javareact.repository;

import com.webapp.tp2_lab_javareact.entity.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {


}
