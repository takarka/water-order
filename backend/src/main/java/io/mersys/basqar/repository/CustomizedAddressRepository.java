package io.mersys.basqar.repository;

import io.mersys.basqar.document.Address;

import java.util.List;

public interface CustomizedAddressRepository {

    List<Address> findAllByUserId(String id);

//    List<Address> findBySearchDTO(AddressSearchDTO dto);

}
