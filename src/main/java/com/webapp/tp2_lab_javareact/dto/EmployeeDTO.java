package com.webapp.tp2_lab_javareact.dto;

import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import com.webapp.tp2_lab_javareact.tool.Utility;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Pattern(regexp = Utility.NAME_PATTERN, message = NotificationMessage.INVALID_NAME)
    private String name;

    @NotBlank(message = NotificationMessage.LASTNAME_IS_REQUIRED)
    @Pattern(regexp = Utility.LAST_NAME_PATTERN, message = NotificationMessage.INVALID_LAST_NAME)
    private String lastName;

    @NotBlank(message = NotificationMessage.EMAIL_IS_REQUIRED)
    @Pattern(regexp = Utility.EMAIL_PATTERN, message = NotificationMessage.INVALID_EMAIL)
    private String email;

    @NotNull(message = NotificationMessage.BIRTH_DATE_IS_REQUIRED)
    @Past(message = NotificationMessage.INVALID_BIRTH_DATE)
    private LocalDate birthDate;

    @NotNull(message = NotificationMessage.ENTRY_DATE_IS_REQUIRED)
    @Past(message = NotificationMessage.INVALID_ENTRY_DATE)
    private LocalDate entryDate;

    //Este atributo se ha cambiado de LocalDate a LocalDateTime para almacenar la fecha y hora del
    // momento de la creación, tal como pide HU001.
    private LocalDateTime creationDate;
}
