package io.mersys.basqar.repository.impl;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.repository.CustomizedOrderRepository;
import io.mersys.basqar.service.dto.OrderSearchDTO;

public class CustomizedOrderRepositoryImpl implements CustomizedOrderRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public List<Order> findBySearchDTO(OrderSearchDTO dto) {
		final Query q = new Query();
		if (dto.getOrderNumber() != null && !dto.getOrderNumber().trim().isEmpty()) {
			q.addCriteria(Criteria.where("orderNumber").regex(Pattern.compile(
					Pattern.quote(dto.getOrderNumber().trim()), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		if (dto.getPhone() != null && !dto.getPhone().trim().isEmpty()) {
			q.addCriteria(Criteria.where("phone").regex(Pattern.compile(Pattern.quote(dto.getPhone().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		if (dto.getStatus() != null) {
			q.addCriteria(Criteria.where("status").is(dto.getStatus()));
		}
		// TODO fill search dto criteria
		// if (dto.getType() != null) {
		// q.addCriteria(Criteria.where("type").is(dto.getType()));
		// }
		q.with(new Sort(Sort.Direction.DESC, "_id"));
		return template.find(q, Order.class);
	}

	@Override
	public List<Order> getByIds(List<String> ids) {
		List<ObjectId> objectIds = ids.stream().map(id -> new ObjectId(id)).collect(Collectors.toList());
		final Query q = new Query();
		q.addCriteria(Criteria.where("_id").in(objectIds));
		return template.find(q, Order.class);
	}

	@Override
	public List<Order> findAllByUserId(String id) {
		final Query q = new Query();
		q.addCriteria(Criteria.where("user.$id").is(new ObjectId(id)));
		q.with(new Sort(Sort.Direction.DESC, "_orderDate"));
		return template.find(q, Order.class);
	}

}
