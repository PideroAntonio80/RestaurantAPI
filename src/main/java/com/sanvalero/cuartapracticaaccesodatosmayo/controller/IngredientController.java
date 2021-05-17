package com.sanvalero.cuartapracticaaccesodatosmayo.controller;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.IngredientDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.IngredientNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.service.IngredientService;
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
@Tag(name = "Ingredients", description = "Ingredients information")
public class IngredientController {

    private final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientService ingredientService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al ingredients list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredients list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))),
            @ApiResponse(responseCode = "404", description = "Ingredients list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/ingredients", produces = "application/json")
    public ResponseEntity<Set<Ingredient>> getIngredients() {

        logger.info("Init getIngredients");

        Set<Ingredient> ingredients = ingredientService.findAll();

        logger.info("End getIngredients");

        return ResponseEntity.status(HttpStatus.OK).body(ingredients);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get ingredient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient exist",
                    content = @Content(schema = @Schema(implementation = Ingredient.class))),
            @ApiResponse(responseCode = "404", description = "Ingredient doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/ingredients/{id}", produces = "application/json")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {

        logger.info("Init getIngredientById");

        Ingredient ingredient = ingredientService.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));

        logger.info("End getIngredientById");

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new ingredient into a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient added",
                    content = @Content(schema = @Schema(implementation = Ingredient.class))),
            @ApiResponse(responseCode = "404", description = "Ingredient couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/restaurants/plates/{id}/ingredient", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Ingredient> addIngredient(@PathVariable long id, @RequestBody IngredientDTO ingredientDTO) {

        logger.info("Init addIngredient");

        Ingredient addedIngredient = ingredientService.addIngredientToPlate(id, ingredientDTO);

        logger.info("End addIngredient");

        return new ResponseEntity<>(addedIngredient, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient modified",
                    content = @Content(schema = @Schema(implementation = Ingredient.class))),
            @ApiResponse(responseCode = "404", description = "Ingredient doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/restaurants/ingredients/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Ingredient> modifyIngredient(@PathVariable long id, @RequestBody IngredientDTO ingredientDTO) {

        logger.info("Init modifyIngredient");

        Ingredient ingredient = ingredientService.modifyIngredient(id, ingredientDTO);

        logger.info("End modifyIngredient");

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Ingredient doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/restaurants/ingredients/{id}")
    public ResponseEntity<Response> deleteIngredient(@PathVariable long id) {

        logger.info("Init deleteIngredient");

        ingredientService.deleteIngredient(id);

        logger.info("End deleteIngredient");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(IngredientNotFoundException infe){
        Response response = Response.errorResponse(NOT_FOUND, infe.getMessage());
        logger.error(infe.getMessage(), infe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
