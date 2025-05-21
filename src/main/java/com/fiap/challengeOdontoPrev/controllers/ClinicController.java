package com.fiap.challengeOdontoPrev.controllers;

import com.fiap.challengeOdontoPrev.domain.dto.ClinicDTO;
import com.fiap.challengeOdontoPrev.domain.projection.AuditProjection;
import com.fiap.challengeOdontoPrev.services.models.ClinicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity<ClinicDTO> insert(@RequestBody @Valid ClinicDTO clinicDTO) {
        ClinicDTO clinic = clinicService.insert(clinicDTO);
        return ResponseEntity.ok(clinic);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDTO>> findAll() {
        List<ClinicDTO> clinics = clinicService.findAll();
        return ResponseEntity.ok(clinics);
    }

    @GetMapping("/clinicsAudit")
    public ResponseEntity<List<AuditProjection>> findAllClinicAudits() {
        return ResponseEntity.ok(this.clinicService.findAllClinicAudits());
    }

}
