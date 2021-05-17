package io.mersys.basqar.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mersys.basqar.document.auth.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomizedUserRepository {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

}
