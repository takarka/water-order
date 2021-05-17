package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.SampleService;
import io.mersys.basqar.service.dto.SampleDTO;
import io.mersys.basqar.service.dto.SampleSearchDTO;
import io.mersys.basqar.web.rest.SampleResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SampleResourceImpl implements SampleResource {

    private SampleService service;

    @Override
    public ResponseEntity<List<SampleDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Override
    public ResponseEntity<List<SampleDTO>> getBySearch(@Valid @RequestBody SampleSearchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
    }

    @Override
    public ResponseEntity<SampleDTO> get(@PathVariable("id") String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<SampleDTO> create(@Valid @RequestBody SampleDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Override
    public ResponseEntity<SampleDTO> update(@Valid @RequestBody SampleDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> delete(SampleDTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}