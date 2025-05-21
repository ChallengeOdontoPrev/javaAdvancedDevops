package com.fiap.challengeOdontoPrev.repositories;

import com.fiap.challengeOdontoPrev.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNumCard(Long numCard);

    @Procedure(name = "INSERT_PATIENT")
    void INSERT_PATIENT(
            @Param("p_birth_date") LocalDate birthdate,
            @Param("p_num_card") Long num_card,
            @Param("p_name") String name,
            @Param("p_rg") String rg
    );

    @Procedure(name = "UPDATE_PATIENT")
    void UPDATE_PATIENT(
            @Param("p_id") Long id,
            @Param("p_birth_date") LocalDate birthdate,
            @Param("p_num_card") Long num_card,
            @Param("p_name") String name,
            @Param("p_rg") String rg
    );

    @Procedure(name = "DELETE_PATIENT")
    void DELETE_PATIENT(@Param("p_id") Long id);
}
