package com.webapp.tp2_lab_javareact.mapper;

import com.webapp.tp2_lab_javareact.dto.LaboralConceptDTO;
import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LaboralConceptMapper {

    public LaboralConcept dtoToLaboralConcept(LaboralConceptDTO dto) {
        return LaboralConcept.builder()
                .id(dto.getId())
                .name(dto.getName())
                .minimumHours(dto.getMinimumHours())
                .maximumHours(dto.getMaximumHours())
                .laborable(dto.isLaborable())
                .build();
    }

    public LaboralConceptDTO laboralConceptToDto(LaboralConcept laboralConcept) {
        return LaboralConceptDTO.builder()
                .id(laboralConcept.getId())
                .name(laboralConcept.getName())
                .minimumHours(laboralConcept.getMinimumHours())
                .maximumHours(laboralConcept.getMaximumHours())
                .laborable(laboralConcept.isLaborable())
                .build();
    }
}
