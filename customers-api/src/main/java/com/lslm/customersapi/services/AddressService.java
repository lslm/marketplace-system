package com.lslm.customersapi.services;

import com.lslm.customersapi.entities.Address;
import com.lslm.customersapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address create(Address address) {
        return addressRepository.save(address);
    }
}
