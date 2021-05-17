package io.mersys.basqar.repository.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.repository.CustomizedUserRepository;
import io.mersys.basqar.service.dto.UserSearchDTO;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public List<User> findBySearchDTO(UserSearchDTO dto) {
		final Query q = new Query();
		if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
			q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		q.with(new Sort(Sort.Direction.DESC, "_id"));
		return template.find(q, User.class);
	}

}
