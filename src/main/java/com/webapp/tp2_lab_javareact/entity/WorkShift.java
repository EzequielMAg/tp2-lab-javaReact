package com.webapp.tp2_lab_javareact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "work_shift")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkShift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "concept_id", nullable = false)
    private LaboralConcept concept;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "hours_worked")
    private Integer hoursWorked;
}
