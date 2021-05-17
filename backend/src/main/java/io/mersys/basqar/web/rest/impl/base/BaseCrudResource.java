package io.mersys.basqar.web.rest.impl.base;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.mersys.basqar.service.Service;
import io.mersys.basqar.service.base.CrudService;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.web.rest.base.CrudResource;

public abstract class BaseCrudResource<DTO extends BaseCrudDTO> implements CrudResource<DTO> {

    private CrudService<DTO> service;

    public BaseCrudResource(Service service) {
        this.service = (CrudService<DTO>) service;
    }

    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    public ResponseEntity<DTO> get(String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<DTO> create(DTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    public ResponseEntity<DTO> update(DTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    public ResponseEntity<Void> delete(String id) {
        return service.get(id).map(this::tryToRemove).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> tryToRemove(DTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}