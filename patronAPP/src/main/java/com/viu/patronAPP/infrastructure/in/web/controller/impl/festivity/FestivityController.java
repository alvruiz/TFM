package com.viu.patronAPP.infrastructure.in.web.controller.impl.festivity;

import com.viu.patronAPP.infrastructure.DTO.user.FestivityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/festivity")
public interface FestivityController {

    @PostMapping("/")
    public ResponseEntity<FestivityDTO> createFestivity(@RequestBody FestivityDTO festivityDTO);

    @GetMapping("/{villageId}")
    public ResponseEntity<FestivityDTO> getFestivityByVillageId(@PathVariable String villageId);
}
