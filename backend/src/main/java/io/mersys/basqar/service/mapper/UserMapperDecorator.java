package io.mersys.basqar.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.service.dto.UserMngtDTO;

public abstract class UserMapperDecorator implements UserMapper {

	@Autowired
	private UserMapper mapper;

	@Override
	public UserMngtDTO toDto(User document) {
		UserMngtDTO dto = mapper.toDto(document);
		return dto;
	}

	@Override
	public User toDocument(UserMngtDTO dto) {
		User document = mapper.toDocument(dto);
		return document;
	}

	@Override
	public List<User> toDocument(List<UserMngtDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<User> list = new ArrayList<>(dtoList.size());
		for (final UserMngtDTO orderDTO : dtoList) {
			list.add(toDocument(orderDTO));
		}

		return list;
	}

	@Override
	public List<UserMngtDTO> toDto(List<User> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<UserMngtDTO> list = new ArrayList<>(documentList.size());
		for (final User order : documentList) {
			list.add(toDto(order));
		}

		return list;
	}

}
