package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.repository.OrderTimeRangeRepository;
import io.mersys.basqar.service.OrderTimeRangeService;
import io.mersys.basqar.service.dto.OrderTimeRangeDTO;
import io.mersys.basqar.service.mapper.OrderTimeRangeMapper;

@Service
@Transactional
public class OrderTimeRangeServiceImpl implements OrderTimeRangeService {

	@Autowired
	private OrderTimeRangeRepository repo;

	@Autowired
	private OrderTimeRangeMapper mapper;

	@Override
	public List<OrderTimeRangeDTO> getAll() {
		return mapper.toDto(repo.findAll());
	}

	@Override
	public Optional<OrderTimeRangeDTO> get(String id) {
		if (id == null || ObjectId.isValid(id)) {
			throw new IllegalArgumentException("id is invalid");
		}
		return repo.findById(id).map(x -> mapper.toDto(x));
	}

	@Override
	public OrderTimeRangeDTO create(OrderTimeRangeDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("dto is null");
		}
		if (dto.getId() != null) {
			throw new IllegalArgumentException("id should be null");
		}
		return mapper.toDto(repo.save(mapper.toDocument(dto)));
	}

	@Override
	public OrderTimeRangeDTO update(OrderTimeRangeDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("dto is null");
		}
		if (dto.getId() == null) {
			throw new IllegalArgumentException("id should not be null");
		}
		return mapper.toDto(repo.save(mapper.toDocument(dto)));
	}

	@Override
	public void delete(String id) {
		if (id == null || !ObjectId.isValid(id)) {
			throw new IllegalArgumentException("id is invalid");
		}
		repo.deleteById(id);
	}

}
