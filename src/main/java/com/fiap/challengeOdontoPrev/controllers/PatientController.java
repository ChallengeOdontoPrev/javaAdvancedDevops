package com.fiap.challengeOdontoPrev.controllers;

import com.fiap.challengeOdontoPrev.domain.dto.PatientDTO;
import com.fiap.challengeOdontoPrev.services.models.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO> insert(@RequestBody @Valid PatientDTO patientDTO) {
        PatientDTO patient = patientService.insert(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/withProcedure")
    public ResponseEntity insertWithProcedure(@RequestBody @Valid PatientDTO patientDTO) {
        this.patientService.insertWithProcedure(patientDTO);
        return ResponseEntity.ok("Paciente inserido com sucesso !!");
    }

    @PutMapping("/withProcedure")
    public ResponseEntity updateWithProcedure(@RequestBody @Valid PatientDTO patientDTO) {
        this.patientService.updateWithProcedure(patientDTO);
        return ResponseEntity.ok("Paciente atualizado com sucesso !!");
    }

    @DeleteMapping("/withProcedure/{id}")
    public ResponseEntity deleteWithProcedure(@PathVariable Long id) {
        this.patientService.deleteWithProcedure(id);
        return ResponseEntity.ok("Paciente deletado com sucesso !!");
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        List<PatientDTO> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{idOdontoPrev}")
    public ResponseEntity<PatientDTO> findByIdOdontoPrev(@PathVariable Long idOdontoPrev) {
        PatientDTO patient = patientService.findByIdOdontoPrev(idOdontoPrev);
        return ResponseEntity.ok(patient);
    }

    @PutMapping
    public ResponseEntity<PatientDTO> update(@RequestBody @Valid PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
