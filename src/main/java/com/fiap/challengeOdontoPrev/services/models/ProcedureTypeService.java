package com.fiap.challengeOdontoPrev.services.models;

import com.fiap.challengeOdontoPrev.domain.ProcedureType;
import com.fiap.challengeOdontoPrev.domain.dto.ProcedureTypeDTO;
import com.fiap.challengeOdontoPrev.repositories.ProcedureTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProcedureTypeService {

    private final ProcedureTypeRepository procedureTypeRepository;

    public ProcedureTypeService(ProcedureTypeRepository procedureTypeRepository) {
        this.procedureTypeRepository = procedureTypeRepository;
    }

    @Transactional
    public ProcedureTypeDTO insert(ProcedureTypeDTO procedureTypeDTO) {
        ProcedureType procedureType = new ProcedureType(procedureTypeDTO);
        procedureType = procedureTypeRepository.save(procedureType);
        return new ProcedureTypeDTO(procedureType);
    }

    public List<ProcedureTypeDTO> findAll() {
        return procedureTypeRepository.findAll().stream().map(ProcedureTypeDTO::new).toList();
    }

    public ProcedureTypeDTO findById(Long id) {
        return new ProcedureTypeDTO(procedureTypeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de Procedimento não encontrado !!")));
    }
}
