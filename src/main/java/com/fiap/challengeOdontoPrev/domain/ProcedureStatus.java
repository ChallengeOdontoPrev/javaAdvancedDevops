package com.fiap.challengeOdontoPrev.domain;


import com.fiap.challengeOdontoPrev.domain.dto.ProcedureStatusDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_procedure_status")
public class ProcedureStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String code;

    @OneToMany(mappedBy = "procedureStatus")
    private List<ProcedureValidation> procedureValidations;

    public ProcedureStatus(Long id, String name, String description, String code, List<ProcedureValidation> procedureValidations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.procedureValidations = procedureValidations;
    }

    public ProcedureStatus(ProcedureStatusDTO procedureStatusDTO) {
        this.id = procedureStatusDTO.getId();
        this.name = procedureStatusDTO.getName();
        this.description = procedureStatusDTO.getDescription();
        this.code = procedureStatusDTO.getCode();
    }

    public ProcedureStatus() {
    }

    public ProcedureStatus(Long id, String name, String description, List<ProcedureValidation> procedureValidations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.procedureValidations = procedureValidations;
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

    public List<ProcedureValidation> getProcedureValidations() {
        return procedureValidations;
    }

    public void setProcedureValidations(List<ProcedureValidation> procedureValidations) {
        this.procedureValidations = procedureValidations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
