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
import org.springframework.web.bind.annotation.RequestMapping;

import io.mersys.basqar.service.dto.UserMngtDTO;
import io.mersys.basqar.service.dto.UserSearchDTO;

@RequestMapping("/api/user")
public interface UserResource {

	@PostMapping(value = "/search")
	ResponseEntity<List<UserMngtDTO>> getBySearch(@Valid @RequestBody UserSearchDTO dto);

	@GetMapping(value = "/{id}")
	ResponseEntity<UserMngtDTO> get(@PathVariable("id") String id);

	@PostMapping()
	ResponseEntity<UserMngtDTO> create(@Valid @RequestBody UserMngtDTO dto);

	@PutMapping()
	ResponseEntity<UserMngtDTO> update(@Valid @RequestBody UserMngtDTO dto);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable("id") String id);

}
