package com.webapp.tp2_lab_javareact.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboralConceptDTO {
    private Long id;
    private String name;
    private Integer minimumHours;
    private Integer maximumHours;
    private boolean laborable;
}
