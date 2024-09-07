package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.WorkShiftCreateDTO;
import com.webapp.tp2_lab_javareact.dto.WorkShiftResponseDTO;
import com.webapp.tp2_lab_javareact.entity.Employee;
import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import com.webapp.tp2_lab_javareact.entity.WorkShift;
import com.webapp.tp2_lab_javareact.mapper.WorkShiftMapper;
import com.webapp.tp2_lab_javareact.repository.WorkShiftRepository;
import com.webapp.tp2_lab_javareact.service.WorkShiftService;
import com.webapp.tp2_lab_javareact.validation.WorkShiftServiceValidation;
import org.springframework.stereotype.Service;

@Service
public class WorkShiftServiceImpl implements WorkShiftService {
    private final WorkShiftRepository repository;
    private final WorkShiftServiceValidation validation;

    public WorkShiftServiceImpl(WorkShiftRepository repository, WorkShiftServiceValidation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    @Override
    public WorkShiftResponseDTO createWorkShift(WorkShiftCreateDTO requestDTO) {
        // Validar que exista el empleado o arroja EntityNotFoundException
        Employee employee = this.validation.validateEmployeeFindById(requestDTO.getEmployeeId());

        // Validar que exista el concepto laboral o arroja EntityNotFoundException
        LaboralConcept laboralConcept = this.validation.validateLaboralConceptById(requestDTO.getConceptId());

        // Validaciones (respecto a las reglas de negocio e/ otras) centralizadas para la creacion de la jornada laboral
        this.validation.validateCreateWorkShift(requestDTO, laboralConcept);

        // Convierte el DTO a entidad y la guarda en la DB
        WorkShift workShift = WorkShiftMapper.dtoToWorkShift(requestDTO, employee, laboralConcept);
        this.repository.save(workShift);

        // Convierte la entidad guardada de vuelta a DTO y la retorna
        return WorkShiftMapper.workShiftToDto(workShift);
    }
}
