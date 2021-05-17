package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.ProductCategoryDTO;
import io.mersys.basqar.service.dto.ProductCategorySearchDTO;

public interface ProductCategoryService {

    List<ProductCategoryDTO> getAll();

    Optional<ProductCategoryDTO> get(String id);

    ProductCategoryDTO create(ProductCategoryDTO dto);

    ProductCategoryDTO update(ProductCategoryDTO dto);

    void delete(String id);

    List<ProductCategoryDTO> search(ProductCategorySearchDTO dto);
}
