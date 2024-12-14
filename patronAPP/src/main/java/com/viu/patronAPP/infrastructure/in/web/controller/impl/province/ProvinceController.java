package com.viu.patronAPP.infrastructure.in.web.controller.impl.province;

import com.viu.patronAPP.infrastructure.DTO.province.ProvinceDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/provinces", produces = "application/json")
@Tag(name = "Province", description = "Operations related to provinces and villages.")
public interface ProvinceController {

    @Operation(
            summary = "Get a list of all provinces",
            description = "This endpoint returns a list of all provinces available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Provinces retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<ProvinceDTO>> getProvinces();

    @Operation(
            summary = "Get villages by province ID",
            description = "This endpoint retrieves a list of villages associated with the specified province.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Villages retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Province not found with the given ID"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{provinceId}/villages/paginated")
    @CrossOrigin
    public ResponseEntity<List<VillageDTO>> getVillagesbyProvince(
            @PathVariable @Parameter(description = "ID of the province to retrieve villages for") String provinceId,
            @RequestParam @Parameter(description = "Page to retrieve villages for") String page,
            @RequestParam @Parameter(description = "Size of the page to retrieve villages for") String size
    );

    @GetMapping("/{provinceId}/villages")
    @CrossOrigin
    public ResponseEntity<List<VillageDTO>> getAllVillages(
            @PathVariable @Parameter(description = "ID of the province to retrieve villages for") String provinceId

    );
}
