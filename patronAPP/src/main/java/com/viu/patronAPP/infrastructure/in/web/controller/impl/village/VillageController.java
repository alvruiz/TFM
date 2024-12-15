package com.viu.patronAPP.infrastructure.in.web.controller.impl.village;

import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
