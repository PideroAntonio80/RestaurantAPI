package com.sanvalero.cuartapracticaaccesodatosmayo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "restaurant")
public class Restaurant {

    @Schema(description = "Restaurant identification code", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Restaurant name", example = "Plan", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Restaurant opening date", example = "08/07/1994")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate openingDate;

    @Schema(description = "Plates list in this location")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Plate> plates;
}
