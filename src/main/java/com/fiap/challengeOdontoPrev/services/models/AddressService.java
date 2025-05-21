package com.fiap.challengeOdontoPrev.services.models;

import com.fiap.challengeOdontoPrev.domain.Address;
import com.fiap.challengeOdontoPrev.domain.dto.AddressDTO;
import com.fiap.challengeOdontoPrev.repositories.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressDTO insert(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address = addressRepository.save(address);
        return new AddressDTO(address);
    }

    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(AddressDTO::new).toList();
    }

    public AddressDTO findById(Long id) {
        return new AddressDTO(addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Address não encontrada")));
    }
}