package io.mersys.basqar.repository;

import java.util.List;

import io.mersys.basqar.document.Product;
import io.mersys.basqar.service.dto.ProductSearchDTO;

public interface CustomizedProductRepository {

    List<Product> findBySearchDTO(ProductSearchDTO dto);

}
