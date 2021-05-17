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

import io.mersys.basqar.service.dto.ProductDTO;
import io.mersys.basqar.service.dto.ProductSearchDTO;

@RequestMapping("/api")
public interface ProductResource {


    @GetMapping(value = "/product")
    ResponseEntity<List<ProductDTO>> getAll();

    @PostMapping(value = "/product")
    ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto);

    @PutMapping(value = "/product")
    ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto);

    @DeleteMapping(value = "/product/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @GetMapping(value = "/product/{id}")
    ResponseEntity<ProductDTO> get(@PathVariable("id") String id);

    @PostMapping(value = "/product/search")
    ResponseEntity<List<ProductDTO>> getBySearch(@Valid @RequestBody ProductSearchDTO dto);
}
