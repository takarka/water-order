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

import io.mersys.basqar.service.dto.ProductCategoryDTO;
import io.mersys.basqar.service.dto.ProductCategorySearchDTO;

@RequestMapping("/api")
public interface ProductCategoryResource {


    @GetMapping(value = "/product-category")
    ResponseEntity<List<ProductCategoryDTO>> getAll();

    @PostMapping(value = "/product-category")
    ResponseEntity<ProductCategoryDTO> create(@Valid @RequestBody ProductCategoryDTO dto);

    @PutMapping(value = "/product-category")
    ResponseEntity<ProductCategoryDTO> update(@Valid @RequestBody ProductCategoryDTO dto);

    @DeleteMapping(value = "/product-category/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @GetMapping(value = "/product-category/{id}")
    ResponseEntity<ProductCategoryDTO> get(@PathVariable("id") String id);

    @PostMapping(value = "/product-category/search")
    ResponseEntity<List<ProductCategoryDTO>> getBySearch(@Valid @RequestBody ProductCategorySearchDTO dto);
}
