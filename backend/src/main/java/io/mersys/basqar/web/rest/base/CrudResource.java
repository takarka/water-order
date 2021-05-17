package io.mersys.basqar.web.rest.base;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.mersys.basqar.service.dto.base.BaseCrudDTO;

public interface CrudResource<DTO extends BaseCrudDTO> {

    @GetMapping()
    ResponseEntity<List<DTO>> getAll();

    @GetMapping(value = "/{id}")
    ResponseEntity<DTO> get(@PathVariable("id") String id);

    @PostMapping()
    ResponseEntity<DTO> create(@Valid @RequestBody DTO dto);

    @PutMapping()
    ResponseEntity<DTO> update(@Valid @RequestBody DTO dto);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

}
