package com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity;

import com.viu.patronAPP.infrastructure.DTO.festivity.FestivityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/festivity")
@Tag(name = "Festivity", description = "Operations related to festivities, such as creation and retrieval of festival data.")
public interface FestivityController {

    @Operation(
            summary = "Create a new festivity",
            description = "This endpoint allows the creation of a new festivity by providing the necessary festivity details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Festivity created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping("/")
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
    @GetMapping("/{villageId}")
    public ResponseEntity<FestivityDTO> getFestivityByVillageId(
            @PathVariable @Parameter(description = "ID of the village to retrieve the festivity") String villageId
    );
}
