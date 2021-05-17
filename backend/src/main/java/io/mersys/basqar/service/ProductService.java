package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.ProductDTO;
import io.mersys.basqar.service.dto.ProductSearchDTO;

public interface ProductService {

    List<ProductDTO> getAll();

    Optional<ProductDTO> get(String id);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(ProductDTO dto);

    void delete(String id);

    List<ProductDTO> search(ProductSearchDTO dto);
}
