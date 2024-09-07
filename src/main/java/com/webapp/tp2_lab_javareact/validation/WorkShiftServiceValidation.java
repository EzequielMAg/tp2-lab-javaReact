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
import org.springframework.stereotype.Component;

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
        this.validateWorkedHoursRange(laboralConcept, requestDTO.getHoursWorked());
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


}
