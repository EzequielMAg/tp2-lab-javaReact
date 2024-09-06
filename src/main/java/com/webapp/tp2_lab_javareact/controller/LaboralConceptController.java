package com.webapp.tp2_lab_javareact.controller;

import com.webapp.tp2_lab_javareact.dto.LaboralConceptDTO;
import com.webapp.tp2_lab_javareact.service.LaboralConceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concept")
public class LaboralConceptController {
    private final LaboralConceptService laboralConceptService;

    public LaboralConceptController(LaboralConceptService laboralConceptService) {
        this.laboralConceptService = laboralConceptService;
    }

    @GetMapping
    public ResponseEntity<List<LaboralConceptDTO>> getLaboralConcepts(@RequestParam(required = false)  Long id,
                                                                     @RequestParam(required = false)  String name) {

        List<LaboralConceptDTO> laboralConceptDTOList = this.laboralConceptService.getLaboralConcepts(id, name);
        return ResponseEntity.ok(laboralConceptDTOList);
    }
}
