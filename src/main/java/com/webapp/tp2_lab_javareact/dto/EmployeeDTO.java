package com.webapp.tp2_lab_javareact.dto;

import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
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

    @NotBlank(message = NotificationMessage.DOCUMENT_NUMBER_IS_REQUIRED)
    private String documentNumber;

    @NotBlank(message = NotificationMessage.NAME_IS_REQUIRED)
    private String name;

    @NotBlank(message = NotificationMessage.LASTNAME_IS_REQUIRED)
    private String lastName;

    @NotBlank(message = NotificationMessage.EMAIL_IS_REQUIRED)
    @Email(message = NotificationMessage.INVALID_EMAIL)
    private String email;

    @NotNull(message = NotificationMessage.BIRTH_DATE_IS_REQUIRED)
    @Past(message = NotificationMessage.INVALID_BIRTH_DATE)
    private LocalDate birthDate;

    @NotNull(message = NotificationMessage.ENTRY_DATE_IS_REQUIRED)
    private LocalDate entryDate;

    private LocalDate creationDate;
}
