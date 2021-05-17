package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.Product;
import io.mersys.basqar.service.dto.ProductDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper extends DocumentMapper<ProductDTO, Product> {
}
