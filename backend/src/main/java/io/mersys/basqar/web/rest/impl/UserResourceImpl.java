package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.UserService;
import io.mersys.basqar.service.dto.UserMngtDTO;
import io.mersys.basqar.service.dto.UserSearchDTO;
import io.mersys.basqar.web.rest.UserResource;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserResourceImpl implements UserResource {

	private UserService service;

	@Override
	public ResponseEntity<List<UserMngtDTO>> getBySearch(@Valid @RequestBody UserSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}

	@Override
	public ResponseEntity<UserMngtDTO> get(@PathVariable("id") String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Override
	public ResponseEntity<UserMngtDTO> create(@Valid @RequestBody UserMngtDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<UserMngtDTO> update(@Valid @RequestBody UserMngtDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		service.delete(id);
		// return
		// service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}