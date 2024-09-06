package com.webapp.tp2_lab_javareact.service.impl;

import com.webapp.tp2_lab_javareact.dto.LaboralConceptDTO;
import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import com.webapp.tp2_lab_javareact.mapper.LaboralConceptMapper;
import com.webapp.tp2_lab_javareact.repository.LaboralConceptRepository;
import com.webapp.tp2_lab_javareact.service.LaboralConceptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaboralConceptServiceImpl implements LaboralConceptService {

    private final LaboralConceptRepository repository;

    public LaboralConceptServiceImpl(LaboralConceptRepository laboralConceptRepository) {
        this.repository = laboralConceptRepository;
    }

    public List<LaboralConceptDTO> getLaboralConcepts(Long id, String name) {
        List<LaboralConceptDTO> concepts;

        if(id == null && name == null) {
            concepts = this.getLaboralConcepts();

        } else if(id != null && name == null) {
            concepts =  this.getLaboralConceptById(id);

        } else if(id == null) {
            concepts =  this.getLaboralConceptsByName(name);

        } else {
            concepts = this.getLaboralConceptsByIdAndName(id, name);
        }

        return this.filterLaborConceptsDTO(concepts);
    }
    @Override
    public List<LaboralConceptDTO> getLaboralConcepts() {
        List<LaboralConcept> laboralConcepts = this.repository.findAll();
        return laboralConcepts.stream().map(LaboralConceptMapper::laboralConceptToDto).collect(Collectors.toList());
    }

    @Override
    public List<LaboralConceptDTO> getLaboralConceptById(Long id) {
        List<LaboralConcept> laboralConcepts = this.repository.findLaboralConceptById(id);
        return laboralConcepts.stream().map(LaboralConceptMapper::laboralConceptToDto).collect(Collectors.toList());
    }

    @Override
    public List<LaboralConceptDTO> getLaboralConceptsByName(String name) {
        List<LaboralConcept> laboralConcepts = this.repository.findByNameIgnoreCaseContaining(name);
        return laboralConcepts.stream()
                .map(LaboralConceptMapper::laboralConceptToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LaboralConceptDTO> getLaboralConceptsByIdAndName(Long id, String name) {
        return this.repository.findByIdAndNameContainingIgnoreCase(id, name)
                .stream()
                .map(LaboralConceptMapper::laboralConceptToDto)
                .collect(Collectors.toList());
    }

    private List<LaboralConceptDTO> filterLaborConceptsDTO(List<LaboralConceptDTO> list) {
        return list.stream()
                .filter(concept -> concept.getMinimumHours() != null && concept.getMaximumHours() != null)
                .collect(Collectors.toList());
    }
}
