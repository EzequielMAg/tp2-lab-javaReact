package com.webapp.tp2_lab_javareact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee")
    private Long id;

    @Column(name = "document_number", length = 8, nullable = false, unique = true)
    private String documentNumber;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;
}
