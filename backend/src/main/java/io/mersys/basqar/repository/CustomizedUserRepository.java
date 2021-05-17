package io.mersys.basqar.repository;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.repository.base.CustomizedRepository;
import io.mersys.basqar.service.dto.UserSearchDTO;

public interface CustomizedUserRepository extends CustomizedRepository<User, UserSearchDTO> {
}
