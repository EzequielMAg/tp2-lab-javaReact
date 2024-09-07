package com.webapp.tp2_lab_javareact.dto;

import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import com.webapp.tp2_lab_javareact.tool.Utility;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkShiftCreateDTO {
    @NotNull(message = NotificationMessage.EMPLOYEE_ID_IS_REQUIRED)
    private Long employeeId;

    @NotNull(message = NotificationMessage.CONCEPT_ID_IS_REQUIRED)
    private Long conceptId;

    @NotNull(message = NotificationMessage.DATE_IS_REQUIRED)
    //@Past(message = NotificationMessage.INVALID_WORK_SHIFT_DATE)
    private LocalDate date;

    //@Pattern(regexp = Utility.POSITIVE_INTEGER_PATTERN) //TODO: llevar este patron a todos los demas DTO's
    private int hoursWorked;
}
