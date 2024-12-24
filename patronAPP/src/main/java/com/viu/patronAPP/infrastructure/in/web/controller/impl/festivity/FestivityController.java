package com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity;

import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/festivities")
@Tag(name = "Festivity", description = "Operations related to festivities, such as creation and retrieval of festival data.")
public interface FestivityController {

    @Operation(
            summary = "Create a new festivity",
            description = "This endpoint allows the creation of a new festivity by providing the necessary festivity details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Festivity created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping()
    @CrossOrigin
    public ResponseEntity<FestivityDTO> createFestivity(
            @RequestBody @Parameter(description = "Details of the festivity to be created") FestivityDTO festivityDTO
    );

    @Operation(
            summary = "Get festivity details by village ID",
            description = "This endpoint retrieves the festivity details for a given village ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Festivity found successfully"),
                    @ApiResponse(responseCode = "404", description = "Festivity not found for the given village ID")
            }
    )
    @GetMapping("/villages/{villageId}")
    @CrossOrigin
    public ResponseEntity<FestivityDTO> getFestivityByVillageId(

            @PathVariable @Parameter(description = "ID of the village to retrieve the festivity") String villageId
    );


    @Operation(
            summary = "Get all festivities",
            description = "This endpoint retrieves all festivities available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Festivities retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<FestivityDTO>> getFestivities(@RequestParam @Parameter(description = "Page to retrieve festivities for") String page,
                                                             @RequestParam @Parameter(description = "Size of the page to retrieve festivities for") String size);

    @Operation(
            summary = "Get a festivity by id",
            description = "This endpoint retrieves a festivity by its id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Festivity retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Festivity not found")
            }
    )
    @GetMapping("/{festivityId}")
    @CrossOrigin
    public ResponseEntity<FestivityDTO> getFestivityById(@PathVariable String festivityId);

    @Operation(
            summary = "Update a festivity",
            description = "This endpoint allows the update of a festivity by providing the necessary festivity details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Festivity retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Festivity not found")
            }
    )
    @PutMapping("/{festivityId}")
    @CrossOrigin
    public ResponseEntity<FestivityDTO> updateFestivity(@PathVariable String festivityId, @RequestBody FestivityDTO festivityDTO);
}
