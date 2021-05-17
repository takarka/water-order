package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.UserMngtDTO;
import io.mersys.basqar.service.dto.UserSearchDTO;

public interface UserService {

	List<UserMngtDTO> search(UserSearchDTO dto);

	Optional<UserMngtDTO> get(String id);

	UserMngtDTO create(UserMngtDTO dto);

	UserMngtDTO update(UserMngtDTO dto);

	void delete(String id);

}
