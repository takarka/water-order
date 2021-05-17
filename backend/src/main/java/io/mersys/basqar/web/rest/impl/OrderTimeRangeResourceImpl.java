package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.OrderTimeRangeService;
import io.mersys.basqar.service.dto.OrderTimeRangeDTO;
import io.mersys.basqar.web.rest.OrderTimeRangeResource;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/order-time-range")
@AllArgsConstructor
public class OrderTimeRangeResourceImpl implements OrderTimeRangeResource {

	private OrderTimeRangeService service;

	@Override
	public ResponseEntity<List<OrderTimeRangeDTO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@Override
	public ResponseEntity<OrderTimeRangeDTO> create(@Valid OrderTimeRangeDTO dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@Override
	public ResponseEntity<OrderTimeRangeDTO> update(@Valid OrderTimeRangeDTO dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<OrderTimeRangeDTO> get(String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}
