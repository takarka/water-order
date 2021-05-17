package io.mersys.basqar.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import io.mersys.basqar.document.Product;
import io.mersys.basqar.service.dto.ProductDTO;

public abstract class ProductMapperDecorator implements ProductMapper {
	
	
	@Autowired
    private ProductMapper mapper;
	
	@Autowired
	private ProductCategoryMapper catMapper;


    @Override
    public Product toDocument(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        final Product document = mapper.toDocument(dto);
        document.setProductCategory(catMapper.toDocument(dto.getProductCategoryDTO()));
        return document;
    }
    
    @Override
    public ProductDTO toDto(Product doc) {
    	if (doc == null) {
            return null;
        }
        final ProductDTO dto = mapper.toDto(doc);
    	dto.setId(doc.getId());
    	dto.setName(doc.getName());
    	dto.setCode(doc.getCode());
    	dto.setDescription(doc.getDescription());
    	dto.setImageUrl(doc.getImageUrl());
    	dto.setPrice(doc.getPrice());
        dto.setProductCategoryDTO(catMapper.toDto(doc.getProductCategory()));
        return dto;
    }
}
