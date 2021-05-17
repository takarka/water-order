package io.mersys.basqar.service;

import io.mersys.basqar.service.dto.AddressDTO;
import io.mersys.basqar.service.dto.AddressSearchDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressDTO> getAll();

    Optional<AddressDTO> get(String id);

    AddressDTO create(AddressDTO dto);

    AddressDTO update(AddressDTO dto);

    void delete(String id);

    List<AddressDTO> getAllOfCurrentUser();

//    List<AddressDTO> search(AddressSearchDTO dto);
}
