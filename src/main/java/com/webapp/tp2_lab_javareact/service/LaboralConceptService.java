package com.webapp.tp2_lab_javareact.service;

import com.webapp.tp2_lab_javareact.dto.LaboralConceptDTO;

import java.util.List;

public interface LaboralConceptService {
    List<LaboralConceptDTO> getLaboralConcepts(Long id, String name);
    List<LaboralConceptDTO> getLaboralConcepts();
    List<LaboralConceptDTO> getLaboralConceptById(Long id);
    List<LaboralConceptDTO> getLaboralConceptsByName(String name);
    List<LaboralConceptDTO> getLaboralConceptsByIdAndName(Long id, String name);
}
