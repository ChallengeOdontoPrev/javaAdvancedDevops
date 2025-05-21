package com.fiap.challengeOdontoPrev.controllers;

import com.fiap.challengeOdontoPrev.domain.dto.ProcedureValidationDTO;
import com.fiap.challengeOdontoPrev.services.models.ProcedureValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proceduresValidation")
public class ProcedureValidationController {

    @Autowired
    private ProcedureValidationService procedureValidationService;

    @GetMapping
    public ResponseEntity<List<ProcedureValidationDTO>> findAll() {
        List<ProcedureValidationDTO> proceduresValidation = procedureValidationService.findAll();
        return ResponseEntity.ok(proceduresValidation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProcedureValidationDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        ProcedureValidationDTO procedureValidation = procedureValidationService.updateStatus(id, status);
        return ResponseEntity.ok(procedureValidation);
    }
}
