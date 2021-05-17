package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.ProductCategoryService;
import io.mersys.basqar.service.dto.ProductCategoryDTO;
import io.mersys.basqar.service.dto.ProductCategorySearchDTO;
import io.mersys.basqar.web.rest.ProductCategoryResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductCategoryResourceImpl implements ProductCategoryResource {

    private ProductCategoryService service;

    @Override
    public ResponseEntity<List<ProductCategoryDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Override
    public ResponseEntity<List<ProductCategoryDTO>> getBySearch(@Valid @RequestBody ProductCategorySearchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
    }

    @Override
    public ResponseEntity<ProductCategoryDTO> get(@PathVariable("id") String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<ProductCategoryDTO> create(@Valid @RequestBody ProductCategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Override
    public ResponseEntity<ProductCategoryDTO> update(@Valid @RequestBody ProductCategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> delete(ProductCategoryDTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}