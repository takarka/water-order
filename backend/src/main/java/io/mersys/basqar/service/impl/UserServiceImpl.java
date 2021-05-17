package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.repository.UserRepository;
import io.mersys.basqar.service.UserService;
import io.mersys.basqar.service.dto.UserMngtDTO;
import io.mersys.basqar.service.dto.UserSearchDTO;
import io.mersys.basqar.service.mapper.UserMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserMapper mapper;
	private UserRepository repository;

	@Override
	public List<UserMngtDTO> search(UserSearchDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To search User, 'dto' must not be null");
		}
		List<User> list = repository.findBySearchDTO(dto);
		return mapper.toDto(list);
	}

	@Override
	public Optional<UserMngtDTO> get(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("User 'id' is not valid value: '" + id + "'");
		}
		return repository.findById(id).map(mapper::toDto);
	}

	@Override
	public UserMngtDTO create(UserMngtDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To create User, 'dto' must not be null");
		}
		if (dto.getId() != null) {
			throw new RuntimeException("To create User, 'id' must be null");
		}
		return save(dto);
	}

	@Override
	public UserMngtDTO update(UserMngtDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To update User, 'dto' must not be null");
		}
		return save(dto);
	}

	@Override
	public void delete(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("User 'id' is not valid value: '" + id + "'");
		}
		repository.deleteById(id);
	}

	private UserMngtDTO save(UserMngtDTO dto) {
		// UserPrincipal principal = (UserPrincipal)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// User user = repository.findById(principal.getId())
		// .orElseThrow(() -> new IllegalArgumentException("User not forund"));
		User doc = mapper.toDocument(dto);
		// doc.setCreatedBy(principal.getId());
		// doc.setUser(user);
		doc = repository.save(doc);
		return mapper.toDto(doc);
	}

}
