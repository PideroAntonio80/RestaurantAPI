package com.sanvalero.cuartapracticaaccesodatosmayo.controller;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Plate;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.PlateDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.PlateNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.service.PlateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.cuartapracticaaccesodatosmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@RestController
@Tag(name = "plates", description = "Plates information")
public class PlateController {

    private final Logger logger = LoggerFactory.getLogger(PlateController.class);

    @Autowired
    private PlateService plateService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al plates list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Plate.class)))),
            @ApiResponse(responseCode = "404", description = "Plate list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/plates", produces = "application/json")
    public ResponseEntity<Set<Plate>> getPlates() {

        logger.info("Init getPlates");

        Set<Plate> plates = plateService.findAll();

        logger.info("End getPlates");

        return ResponseEntity.status(HttpStatus.OK).body(plates);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get plate by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate exist",
                    content = @Content(schema = @Schema(implementation = Plate.class))),
            @ApiResponse(responseCode = "404", description = "Plate doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/plates/{id}", produces = "application/json")
    public ResponseEntity<Plate> getPlateById(@PathVariable long id) {

        logger.info("Init getPlateById");

        Plate plate = plateService.findById(id)
                .orElseThrow(() -> new PlateNotFoundException(id));

        logger.info("End getPlateById");

        return new ResponseEntity<>(plate, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new plate into a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate added",
                    content = @Content(schema = @Schema(implementation = Plate.class))),
            @ApiResponse(responseCode = "404", description = "Plate couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/restaurants/restaurants/{id}/plate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Plate> addPlate(@PathVariable long id, @RequestBody PlateDTO plateDTO) {

        logger.info("Init addPlate");

        Plate addedPlate = plateService.addPlateToRestaurant(id, plateDTO);

        logger.info("End addPlate");

        return new ResponseEntity<>(addedPlate, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate modified",
                    content = @Content(schema = @Schema(implementation = Plate.class))),
            @ApiResponse(responseCode = "404", description = "Plate doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/restaurants/plates/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Plate> modifyPlate(@PathVariable long id, @RequestBody PlateDTO plateDTO) {

        logger.info("Init modifyPlate");

        Plate plate = plateService.modifyPlate(id, plateDTO);

        logger.info("End modifyPlate");

        return new ResponseEntity<>(plate, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Plate doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/restaurants/plates/{id}")
    public ResponseEntity<Response> deletePlate(@PathVariable long id) {

        logger.info("Init deletePlate");

        plateService.deletePlate(id);

        logger.info("End deletePlate");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(PlateNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(PlateNotFoundException pnfe){
        Response response = Response.errorResponse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
