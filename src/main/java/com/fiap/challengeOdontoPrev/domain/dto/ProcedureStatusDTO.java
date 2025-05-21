package com.fiap.challengeOdontoPrev.domain.dto;

import com.fiap.challengeOdontoPrev.domain.ProcedureStatus;
import jakarta.validation.constraints.NotBlank;

public class ProcedureStatusDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String code;

    public ProcedureStatusDTO() {
    }

    public ProcedureStatusDTO(ProcedureStatus procedureStatus) {
        this.id = procedureStatus.getId();
        this.name = procedureStatus.getName();
        this.description = procedureStatus.getDescription();
        this.code = procedureStatus.getCode();
    }

    public ProcedureStatusDTO(Long id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
