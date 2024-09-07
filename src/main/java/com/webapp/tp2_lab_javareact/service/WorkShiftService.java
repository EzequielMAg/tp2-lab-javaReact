package com.webapp.tp2_lab_javareact.service;

import com.webapp.tp2_lab_javareact.dto.WorkShiftCreateDTO;
import com.webapp.tp2_lab_javareact.dto.WorkShiftResponseDTO;

public interface WorkShiftService {
    WorkShiftResponseDTO createWorkShift(WorkShiftCreateDTO requestDTO);
}
