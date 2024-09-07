package com.webapp.tp2_lab_javareact.controller;

import com.webapp.tp2_lab_javareact.dto.WorkShiftCreateDTO;
import com.webapp.tp2_lab_javareact.dto.WorkShiftResponseDTO;
import com.webapp.tp2_lab_javareact.service.WorkShiftService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/shift")
public class WorkShiftController {

    private final WorkShiftService workShiftService;

    public WorkShiftController(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @PostMapping
    public ResponseEntity<WorkShiftResponseDTO> createWorkShift(@Valid @RequestBody WorkShiftCreateDTO requestDTO) {
        WorkShiftResponseDTO responseDTO = this.workShiftService.createWorkShift(requestDTO);
        return ResponseEntity.created(URI.create( "/shift/" + responseDTO.getId() )).body(responseDTO);
    }
}
