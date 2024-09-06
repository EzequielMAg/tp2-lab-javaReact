package com.webapp.tp2_lab_javareact.mapper;

import com.webapp.tp2_lab_javareact.dto.WorkShiftCreateDTO;
import com.webapp.tp2_lab_javareact.dto.WorkShiftResponseDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import com.webapp.tp2_lab_javareact.entity.WorkShift;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkShiftMapper {
    public WorkShift dtoToWorkShift(WorkShiftCreateDTO dto, Employee employee, LaboralConcept concept) {
        return WorkShift.builder()
                .employee(employee)
                .concept(concept)
                .date(dto.getDate())
                .hoursWorked(dto.getHoursWorked())
                .build();
    }

    public WorkShiftResponseDTO workShiftToDto(WorkShift workShift) {
        return WorkShiftResponseDTO.builder()
                .id(workShift.getId())
                .documentNumber(workShift.getEmployee().getDocumentNumber())
                .fullName(workShift.getEmployee().getName() + " " + workShift.getEmployee().getLastName())
                .date(workShift.getDate())
                .conceptName(workShift.getConcept().getName())
                .hoursWorked(workShift.getHoursWorked())
                .build();
    }
}
