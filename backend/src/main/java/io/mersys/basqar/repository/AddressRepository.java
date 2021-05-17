package io.mersys.basqar.repository;

import io.mersys.basqar.document.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String>, CustomizedAddressRepository {
}
