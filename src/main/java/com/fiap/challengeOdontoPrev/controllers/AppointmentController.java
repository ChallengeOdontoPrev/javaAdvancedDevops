package com.fiap.challengeOdontoPrev.controllers;

import com.fiap.challengeOdontoPrev.domain.dto.AppointmentDTO;
import com.fiap.challengeOdontoPrev.domain.dto.AppointmentResponseDTO;
import com.fiap.challengeOdontoPrev.domain.dto.UpdateAppointmentDTO;
import com.fiap.challengeOdontoPrev.domain.projection.AuditProjection;
import com.fiap.challengeOdontoPrev.services.models.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> insert(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        AppointmentDTO appointment = appointmentService.insert(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> findAll() {
        List<AppointmentResponseDTO> appointments = appointmentService.findAll().stream().map(AppointmentResponseDTO::new).toList();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/status")
    public ResponseEntity<List<AppointmentResponseDTO>> findAllByStatus(@RequestParam String status) {
        List<AppointmentResponseDTO> appointments = appointmentService.findAllByStatus(status).stream().map(AppointmentResponseDTO::new).toList();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
        AppointmentResponseDTO appointment = new AppointmentResponseDTO(appointmentService.findById(id));
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("byPatientIdOdontoPrev/{idOdontoPrev}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatientIdOdontoPrev(@PathVariable Long idOdontoPrev) {
        return ResponseEntity.ok(this.appointmentService.findAllByPatientNumCard(idOdontoPrev));
    }

    @GetMapping("/appointmentsAudit")
    public ResponseEntity<List<AuditProjection>> findAllAppointmentAudits() {
        return ResponseEntity.ok(this.appointmentService.findAllAppointmentAudits());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.ok("Consulta deletada com sucesso");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid UpdateAppointmentDTO appointmentDTO) {
        appointmentService.updateProcedureType(id, appointmentDTO);
        return ResponseEntity.ok("Consulta atualizada com sucesso");
    }
}
