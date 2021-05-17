package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.OrderService;
import io.mersys.basqar.service.dto.OrderDTO;
import io.mersys.basqar.service.dto.OrderSearchDTO;
import io.mersys.basqar.web.rest.OrderResource;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderResourceImpl implements OrderResource {

	private OrderService service;

	@Override
	public ResponseEntity<List<OrderDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@Override
	public ResponseEntity<List<OrderDTO>> getAllOfCurrentUser() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllOfCurrentUser());
	}

	@Override
	public ResponseEntity<List<OrderDTO>> getBySearch(@Valid @RequestBody OrderSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}

	@Override
	public ResponseEntity<OrderDTO> get(@PathVariable("id") String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Override
	public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	private ResponseEntity<Void> delete(OrderDTO dto) {
		service.delete(dto.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	public ResponseEntity<Void> approve(@Valid List<String> ids) {
		service.approve(ids);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> cancel(@Valid List<String> ids) {
		service.cancel(ids);
		return ResponseEntity.ok().build();
	}

}