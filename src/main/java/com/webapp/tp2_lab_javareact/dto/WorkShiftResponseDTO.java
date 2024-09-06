package com.webapp.tp2_lab_javareact.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkShiftResponseDTO {

    private Long id;

    private String documentNumber;

    private String fullName;

    private LocalDate date;

    private String conceptName;

    private int hoursWorked;
}
