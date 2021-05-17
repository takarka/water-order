package io.mersys.basqar.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.basqar.document.ProductCategory;
import io.mersys.basqar.service.dto.ProductCategoryDTO;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends DocumentMapper<ProductCategoryDTO, ProductCategory> {
}
