package com.fiap.challengeOdontoPrev.services.models;

import com.fiap.challengeOdontoPrev.domain.ProcedureStatus;
import com.fiap.challengeOdontoPrev.domain.ProcedureType;
import com.fiap.challengeOdontoPrev.domain.ProcedureValidation;
import com.fiap.challengeOdontoPrev.domain.dto.ProcedureTypeDTO;
import com.fiap.challengeOdontoPrev.domain.dto.ProcedureValidationDTO;
import com.fiap.challengeOdontoPrev.repositories.ProcedureValidationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProcedureValidationService {

    private final ProcedureValidationRepository procedureValidationRepository;
    private final ProcedureStatusService procedureStatusService;
    private final ProcedureTypeService procedureTypeService;

    public ProcedureValidationService(ProcedureValidationRepository procedureValidationRepository, ProcedureStatusService procedureStatusService, ProcedureTypeService procedureTypeService) {
        this.procedureValidationRepository = procedureValidationRepository;
        this.procedureStatusService = procedureStatusService;
        this.procedureTypeService = procedureTypeService;
    }

    @Transactional
    public ProcedureValidationDTO insert(ProcedureValidationDTO procedureValidationDTO, Long procedureTypeId) {
        ProcedureValidation procedureValidation = new ProcedureValidation(procedureValidationDTO);

        procedureValidation.setProcedureStatus(new ProcedureStatus(
                procedureStatusService.findByName("Em Analise para Validacao")
        ));

        ProcedureTypeDTO procedureType = procedureTypeService.findById(procedureTypeId);

        procedureValidation.setProcedureType(new ProcedureType(
                procedureType
        ));

        procedureValidation.setClassInitial(procedureType.getClassInitial());
        procedureValidation.setClassFinal(procedureType.getClassFinal());

        procedureValidation = procedureValidationRepository.save(procedureValidation);
        return new ProcedureValidationDTO(procedureValidation);
    }

    @Transactional
    public void updateAddImages(String imgUrlInitial, String imgUrlFinal, Long id) {
        ProcedureValidation procedureValidation = new ProcedureValidation(
                this.procedureValidationRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Procedimento não encontrado !!"))
        );
        procedureValidation.setImgUrlInitial(imgUrlInitial);
        procedureValidation.setImgUrlFinal(imgUrlFinal);
        procedureValidationRepository.save(procedureValidation);
    }

    public List<ProcedureValidationDTO> findAll() {
        return procedureValidationRepository.findAll().stream().map(ProcedureValidationDTO::new).toList();
    }

    public ProcedureValidationDTO findById(Long id) {
        return new ProcedureValidationDTO(procedureValidationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Procedimento não encontrado !!")));
    }

    @Transactional
    public ProcedureValidationDTO updateStatus(Long id, String status) {
        ProcedureValidation procedureValidation = new ProcedureValidation(
                this.procedureValidationRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Procedimento não encontrado !!"))
        );
        procedureValidation.setProcedureStatus(new ProcedureStatus(
                procedureStatusService.findByName(status)
        ));
        procedureValidation = procedureValidationRepository.save(procedureValidation);

        return new ProcedureValidationDTO(procedureValidation);
    }

    @Transactional
    public void delete(ProcedureValidation procedureValidation) {
        this.procedureValidationRepository.delete(procedureValidation);
    }
}
