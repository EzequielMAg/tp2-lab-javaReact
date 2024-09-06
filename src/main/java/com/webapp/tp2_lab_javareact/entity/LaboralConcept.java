package com.webapp.tp2_lab_javareact.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laboral_concept")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboralConcept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "minimun_hs")
    private Integer minimumHours;
    @Column(name = "maximum_hs")
    private Integer maximumHours;
    private boolean laborable;
}
