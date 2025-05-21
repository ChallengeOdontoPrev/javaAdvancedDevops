package com.fiap.challengeOdontoPrev.services.models;

import com.fiap.challengeOdontoPrev.domain.ProcedureStatus;
import com.fiap.challengeOdontoPrev.domain.dto.ProcedureStatusDTO;
import com.fiap.challengeOdontoPrev.repositories.ProcedureStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProcedureStatusService {

    private final ProcedureStatusRepository procedureStatusRepository;

    public ProcedureStatusService(ProcedureStatusRepository procedureStatusRepository) {
        this.procedureStatusRepository = procedureStatusRepository;
    }

    @Transactional
    public ProcedureStatusDTO insert(ProcedureStatusDTO procedureStatusDTO) {
        ProcedureStatus procedureStatus = new ProcedureStatus(procedureStatusDTO);
        procedureStatus = procedureStatusRepository.save(procedureStatus);
        return new ProcedureStatusDTO(procedureStatus);
    }

    @Transactional
    public void insertWithProcedure(ProcedureStatusDTO procedureStatusDTO) {
        this.procedureStatusRepository.INSERT_PROCEDURE_STATUS(procedureStatusDTO.getName(), procedureStatusDTO.getDescription());
    }

    @Transactional
    public void updateWithProcedure(ProcedureStatusDTO procedureStatusDTO) {
        this.procedureStatusRepository.UPDATE_PROCEDURE_STATUS(procedureStatusDTO.getId(), procedureStatusDTO.getName(), procedureStatusDTO.getDescription());
    }

    @Transactional
    public void deleteWithProcedure(Long id) {
        this.procedureStatusRepository.DELETE_PROCEDURE_STATUS(id);
    }

    public List<ProcedureStatusDTO> findAll() {
        return procedureStatusRepository.findAll().stream().map(ProcedureStatusDTO::new).toList();
    }

    public ProcedureStatusDTO findById(Long id) {
        return new ProcedureStatusDTO(procedureStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Status de Procedimento não encontrado !!")));
    }

    public ProcedureStatusDTO findByName(String name) {
        return new ProcedureStatusDTO(procedureStatusRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Status de Procedimento não encontrado !!")));
    }

}
