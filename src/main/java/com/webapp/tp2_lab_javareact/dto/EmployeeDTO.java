package com.webapp.tp2_lab_javareact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    // Tanto "id" como "creationDate" no son requeridos ya que se asignan por default (DB y en el constructor)
    private Long id;

    @NotBlank(message = "documentNumber is required")
    private String documentNumber;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "You must provide a valid email")
    private String email;

    @NotNull(message = "birthDate is required")
    @Past(message = "The date of birth must be in the past.")
    private LocalDate birthDate;

    @NotNull(message = "entryDate is required")
    private LocalDate entryDate;

    private LocalDate creationDate;
}
