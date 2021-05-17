package io.mersys.basqar.repository;

import java.util.List;

import io.mersys.basqar.document.ProductCategory;
import io.mersys.basqar.service.dto.ProductCategorySearchDTO;

public interface CustomizedProductCategoryRepository {

    List<ProductCategory> findBySearchDTO(ProductCategorySearchDTO dto);

}
