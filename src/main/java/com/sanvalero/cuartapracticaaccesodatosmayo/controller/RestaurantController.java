package com.sanvalero.cuartapracticaaccesodatosmayo.controller;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Restaurant;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.RestaurantDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.RestaurantNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.service.RestaurantService;
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
@Tag(name = "restaurants", description = "Restaurant information")
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all restaurants list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurants list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Restaurant.class)))),
            @ApiResponse(responseCode = "404", description = "Restaurants list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/restaurants", produces = "application/json")
    public ResponseEntity<Set<Restaurant>> getRestaurants() {

        logger.info("Init getRestaurants");

        Set<Restaurant> restaurants = restaurantService.findAll();

        logger.info("End getRestaurants");

        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get restaurant by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant exists",
                    content = @Content(schema = @Schema(implementation = Restaurant.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/restaurants/restaurants/{id}", produces = "application/json")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable long id) {

        logger.info("Init getRestaurantById");

        Restaurant restaurant = restaurantService.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        logger.info("End getRestaurantById");

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant added",
                    content = @Content(schema = @Schema(implementation = Restaurant.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/restaurants/restaurants", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

        logger.info("Init addRestaurant");

        Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);

        logger.info("End addRestaurant");

        return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant modified",
                    content = @Content(schema = @Schema(implementation = Restaurant.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/restaurants/restaurants/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Restaurant> modifyRestaurant(@PathVariable long id, @RequestBody RestaurantDTO restaurantDTO) {

        logger.info("Init modifyRestaurant");

        Restaurant restaurant = restaurantService.modifyRestaurant(id, restaurantDTO);

        logger.info("End modifyRestaurant");

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Deletes restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/restaurants/restaurants/{id}")
    public ResponseEntity<Response> deleteRestaurant(@PathVariable long id) {

        logger.info("Init deleteRestaurant");

        restaurantService.deleteRestaurant(id);

        logger.info("End deleteRestaurant");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(RestaurantNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
