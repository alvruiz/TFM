package com.viu.patronAPP.infrastructure.in.web.controller.impl.village;

import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/villages")
@Tag(name = "Village", description = "Operations related to village management, such as creation and retrieval of village data.")
public interface VillageController {

    @Operation(
            summary = "Get village by ID",
            description = "This endpoint retrieves a village by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Village retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Village not found with the given ID")
            }
    )
    @GetMapping("/{villageId}")
    @CrossOrigin
    public ResponseEntity<VillageDTO> getVillageById(@PathVariable String villageId);

    @Operation(
            summary = "Update a village",
            description = "This endpoint allows the update of a village by providing the necessary village details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Village created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PutMapping("/{villageId}")
    @CrossOrigin
    public ResponseEntity<VillageDTO> updateVillage(@PathVariable String villageId, @RequestBody VillageDTO villageDTO);

    @Operation(
            summary = "Delete a village",
            description = "This endpoint allows the deletion of a village by its unique ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Village deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Village not found")
            }
    )
    @DeleteMapping("/{villageId}")
    public ResponseEntity<String> deleteVillage(@PathVariable String villageId);

    @Operation(
            summary = "Get all villages",
            description = "This endpoint retrieves all villages available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Villages retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<VillageDTO>> getAllVillages();

    @Operation(summary = "Create a new village", description = "This endpoint allows the creation of a new village by providing the necessary village details.", responses = {@ApiResponse(responseCode = "201", description = "Village created successfully"), @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping()
    @CrossOrigin
    public ResponseEntity<VillageDTO> createVillage(@RequestBody VillageDTO villageDTO);


}
