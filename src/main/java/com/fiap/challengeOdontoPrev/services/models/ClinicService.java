package com.fiap.challengeOdontoPrev.services.models;

import com.fiap.challengeOdontoPrev.domain.Address;
import com.fiap.challengeOdontoPrev.domain.Clinic;
import com.fiap.challengeOdontoPrev.domain.dto.ClinicDTO;
import com.fiap.challengeOdontoPrev.domain.projection.AuditProjection;
import com.fiap.challengeOdontoPrev.repositories.AddressRepository;
import com.fiap.challengeOdontoPrev.repositories.ClinicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final AddressRepository addressRepository;

    public ClinicService(ClinicRepository clinicRepository, AddressRepository addressRepository) {
        this.clinicRepository = clinicRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public ClinicDTO insert(ClinicDTO clinicDTO) {
        Clinic clinic = new Clinic();
        BeanUtils.copyProperties(clinicDTO, clinic);

        Address address = new Address(clinicDTO.getAddress());
        address = addressRepository.save(address);
        clinic.setAddress(address);

        clinic = clinicRepository.save(clinic);
        return new ClinicDTO(clinic);
    }

    public List<AuditProjection> findAllClinicAudits() {
        return clinicRepository.findAllClinicAudits();
    }

    public List<ClinicDTO> findAll() {
        return clinicRepository.findAll().stream().map(ClinicDTO::new).toList();
    }

    public ClinicDTO findById(Long id) {
        return new ClinicDTO(clinicRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Clínica não encontrada")));
    }
}
