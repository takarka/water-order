package io.mersys.basqar.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.mersys.basqar.service.dto.OrderTimeRangeDTO;

public interface OrderTimeRangeResource {

	@GetMapping()
	ResponseEntity<List<OrderTimeRangeDTO>> getAll();

	@PostMapping()
	ResponseEntity<OrderTimeRangeDTO> create(@Valid @RequestBody OrderTimeRangeDTO dto);

	@PutMapping()
	ResponseEntity<OrderTimeRangeDTO> update(@Valid @RequestBody OrderTimeRangeDTO dto);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable("id") String id);

	@GetMapping(value = "/{id}")
	ResponseEntity<OrderTimeRangeDTO> get(@PathVariable("id") String id);
}
