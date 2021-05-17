package io.mersys.basqar.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.ProductService;
import io.mersys.basqar.service.dto.ProductDTO;
import io.mersys.basqar.service.dto.ProductSearchDTO;
import io.mersys.basqar.web.rest.ProductResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductResourceImpl implements ProductResource {

    private ProductService service;

    @Override
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getBySearch(@Valid @RequestBody ProductSearchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
    }

    @Override
    public ResponseEntity<ProductDTO> get(@PathVariable("id") String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Override
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> delete(ProductDTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}