package com.viu.patronAPP.infrastructure.in.web.controller.impl.user;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/province")
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
    @GetMapping("/")
    public ResponseEntity<List<Province>> getProvinces();

    @Operation(
            summary = "Get villages by province ID",
            description = "This endpoint retrieves a list of villages associated with the specified province.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Villages retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Province not found with the given ID"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{provinceId}")
    public ResponseEntity<List<Village>> getVillagesbyProvince(
            @PathVariable @Parameter(description = "ID of the province to retrieve villages for") String provinceId
    );
}
