package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.RegionService;
import io.mersys.basqar.service.dto.RegionDTO;
import io.mersys.basqar.service.dto.RegionSearchDTO;
import io.mersys.basqar.web.rest.RegionResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RegionResourceImpl implements RegionResource {

    private RegionService service;

    @Override
    public ResponseEntity<List<RegionDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Override
    public ResponseEntity<List<RegionDTO>> getBySearch(@Valid @RequestBody RegionSearchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
    }

    @Override
    public ResponseEntity<RegionDTO> get(@PathVariable("id") String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Override
    public ResponseEntity<RegionDTO> update(@Valid @RequestBody RegionDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> delete(RegionDTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}