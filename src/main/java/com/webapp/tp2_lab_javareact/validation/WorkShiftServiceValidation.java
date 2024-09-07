package com.webapp.tp2_lab_javareact.validation;

import com.webapp.tp2_lab_javareact.dto.WorkShiftCreateDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import com.webapp.tp2_lab_javareact.entity.WorkShift;
import com.webapp.tp2_lab_javareact.exception.BusinessException;
import com.webapp.tp2_lab_javareact.exception.EntityNotFoundException;
import com.webapp.tp2_lab_javareact.exception.InvalidAttributeException;
import com.webapp.tp2_lab_javareact.exception.RequiredAttributeException;
import com.webapp.tp2_lab_javareact.repository.LaboralConceptRepository;
import com.webapp.tp2_lab_javareact.repository.WorkShiftRepository;
import com.webapp.tp2_lab_javareact.tool.NotificationMessage;
import com.webapp.tp2_lab_javareact.tool.Utility;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class WorkShiftServiceValidation {
    private final WorkShiftRepository workShiftRepository;

    // No hago la injeccion de EmployeeRepository, ya que TENGO una capa de validaciones que lo hace y metodos que quiero reutilizar
    private final EmployeeServiceValidation employeeServiceValidation;

    // Hago la injeccion de tal repositorio, porque que  NO TENGO una capa de validaciones (ya que no hacia falta)
    private final LaboralConceptRepository laboralConceptRepository;

    public WorkShiftServiceValidation(WorkShiftRepository workShiftRepository, EmployeeServiceValidation employeeServiceValidation,
                                      LaboralConceptRepository laboralConceptRepository) {
        this.workShiftRepository = workShiftRepository;
        this.employeeServiceValidation = employeeServiceValidation;
        this.laboralConceptRepository = laboralConceptRepository;
    }

    public void validateCreateWorkShift(WorkShiftCreateDTO requestDTO, LaboralConcept laboralConcept)
            throws  RequiredAttributeException, InvalidAttributeException {
        // DEMAS VALIDACIONES DE LOS CRITERIOS DE ACEPTACION
        this.validateWorkedHoursByWorkday(laboralConcept.isWorkDay(), requestDTO.getHoursWorked());

        // VALIDACIONES DE LAS REGLAS DE NEGOCIO
        this.businessRulesValidations(requestDTO, laboralConcept);
    }

    public WorkShift validateFindById(Long id) throws EntityNotFoundException {
        return this.workShiftRepository.findById(id).orElseThrow( () ->
                new EntityNotFoundException( NotificationMessage.workShiftNotFound(id) ));
    }

    public Employee validateEmployeeFindById(Long employeeId) throws EntityNotFoundException {
        return this.employeeServiceValidation.validateFindById(employeeId);
    }

    public LaboralConcept validateLaboralConceptById(Long conceptId) throws EntityNotFoundException {
        return this.laboralConceptRepository.findById(conceptId).orElseThrow( () ->
                new EntityNotFoundException( NotificationMessage.laboralConceptNotFound(conceptId) ));
    }

    private void validateWorkedHoursByWorkday(Boolean isWorkDay, Integer hours) throws RequiredAttributeException {
        //if(nameConcept.equalsIgnoreCase("Turno Normal") || nameConcept.equalsIgnoreCase("Turno Extra")) {
        if(isWorkDay) {
            if(hours == null) {
                throw new RequiredAttributeException( NotificationMessage.HOURS_WORKED_IS_REQUIRED );
            }
        } else {
            if(hours != null) {
                throw new InvalidAttributeException( NotificationMessage.HOURS_WORKED_IS_NOT_REQUIRED );
            }
        }
    }

    private void businessRulesValidations(WorkShiftCreateDTO requestDTO, LaboralConcept laboralConcept) {
        Long employeeId = requestDTO.getEmployeeId();
        LocalDate workShiftDate = requestDTO.getDate();
        Integer hoursWorked = requestDTO.getHoursWorked();

        String conceptName = laboralConcept.getName();

        this.validateWorkedHoursRange(laboralConcept, hoursWorked); // 1
        this.validateMaxWorkedHoursPerDay(requestDTO);   // 2
        this.validateWeeklyHours(requestDTO);   // 3 // Para probar esta validacion comente las dos lineas anteriores
        this.validateUniqueWorkShiftPerDay(workShiftDate, employeeId, conceptName); // 11
    }

    private void validateWorkedHoursRange(LaboralConcept laboralConcept, Integer hoursWorked) {
        if(laboralConcept.isWorkDay()) {
            Integer minimumHours = laboralConcept.getMinimumHours();
            Integer maximumHours = laboralConcept.getMaximumHours();

            if(hoursWorked < minimumHours || hoursWorked > maximumHours) {
                throw new BusinessException( NotificationMessage.workedHoursOutOfRange(minimumHours, maximumHours) );
            }
        }
    }

    private void validateMaxWorkedHoursPerDay(WorkShiftCreateDTO requestDTO) {
        if(requestDTO.getHoursWorked() != null) {

            final int MAX_WORKED_HOURS = 14;
            List<WorkShift> workShiftList = workShiftRepository.
                    findByDateAndEmployeeId(requestDTO.getDate(), requestDTO.getEmployeeId());

            if (!workShiftList.isEmpty()) {
                int totalHours = workShiftList.stream()
                        //.mapToInt(WorkShift::getHoursWorked) // Uso el siguiente para evitar un NullPointerException
                        .mapToInt(shift -> shift.getHoursWorked() != null ? shift.getHoursWorked() : 0)
                        .sum() + requestDTO.getHoursWorked();

                System.out.println(totalHours); // TODO: <<<<<<<<<--------------------------- revisar en consola

                if (totalHours > MAX_WORKED_HOURS) {
                    throw new BusinessException( NotificationMessage.WORK_HOURS_LIMIT_EXCEEDED );
                }
            }
        }
    }

    private void validateWeeklyHours(WorkShiftCreateDTO requestDTO) {
        // Si horas trabajadas es null -> no tiene sentido sumarla con el resto para calcular si es > 52hs
        if(requestDTO.getHoursWorked() != null) {
            final int WEEKLY_HOURS_LIMIT = 52;
            LocalDate workShiftDate = requestDTO.getDate();
            Long employeeId = requestDTO.getEmployeeId();

            // Calcula el rango de fechas para la semana
            LocalDate startOfWeek = Utility.getStartOfWeek(workShiftDate);
            LocalDate endOfWeek = Utility.getEndOfWeek(workShiftDate);

            // Recupera todas las jornadas laborales del empleado en la misma semana
            List<WorkShift> weeklyShifts = this.workShiftRepository.
                    findByEmployeeIdAndDateBetween(employeeId, startOfWeek, endOfWeek);

            // Suma las horas trabajadas
            int totalHours = weeklyShifts.stream()
                    .mapToInt(shift -> shift.getHoursWorked() != null ? shift.getHoursWorked() : 0)
                    .sum() + requestDTO.getHoursWorked();

            // Verifica si el total de horas supera las 52 horas
            if (totalHours > WEEKLY_HOURS_LIMIT) {
                throw new BusinessException( NotificationMessage.WEEKLY_HOURS_LIMIT_EXCEEDED );
            }
        }
    }

    private void validateUniqueWorkShiftPerDay(LocalDate date, Long employeeId, String conceptName) {
        if(this.workShiftRepository.existsByDateAndEmployeeIdAndConceptName(date, employeeId, conceptName)) {
            throw new BusinessException( NotificationMessage.DUPLICATE_SHIFT_FOR_DATE );
        }
    }


}
