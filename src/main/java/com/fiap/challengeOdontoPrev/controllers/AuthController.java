package com.fiap.challengeOdontoPrev.controllers;

import com.fiap.challengeOdontoPrev.domain.dto.UserDTO;
import com.fiap.challengeOdontoPrev.domain.projection.AuditProjection;
import com.fiap.challengeOdontoPrev.enums.UserRole;
import com.fiap.challengeOdontoPrev.services.models.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO data) {
        UserDTO userDTO = this.userService.insert(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
                .buildAndExpand(userDTO.getEmail()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findByRole(@RequestParam UserRole role) {
        List<UserDTO> users = userService.findByRole(role).stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/usersAudit")
    public ResponseEntity<List<AuditProjection>> findAllUserAudits() {
        return ResponseEntity.ok(this.userService.findAllUserAudits());
    }


}
