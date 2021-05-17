package com.sanvalero.cuartapracticaaccesodatosmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "plate")
public class Plate {

    @Schema(description = "Plate identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Plate name", example = "chilly beans", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Price plate", example = "20.5")
    @Column
    private float price;

    @Schema(description = "Plate's restaurant identity")
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;

    @Schema(description = "Plate´s ingredients list")
    @OneToMany(mappedBy = "plate", cascade = CascadeType.REMOVE)
    private List<Ingredient> ingredients;
}
