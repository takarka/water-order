package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.ProductCategory;
import io.mersys.basqar.repository.ProductCategoryRepository;
import io.mersys.basqar.service.ProductCategoryService;
import io.mersys.basqar.service.dto.ProductCategoryDTO;
import io.mersys.basqar.service.dto.ProductCategorySearchDTO;
import io.mersys.basqar.service.mapper.ProductCategoryMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryMapper mapper;
    private ProductCategoryRepository repository;

    @Override
    public ProductCategoryDTO create(ProductCategoryDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create ProductCategory, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create ProductCategory, 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("ProductCategory 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<ProductCategoryDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("ProductCategory 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<ProductCategoryDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private ProductCategoryDTO save(ProductCategoryDTO dto) {
        ProductCategory doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update ProductCategory, 'dto' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update ProductCategory, 'name' must not be null");
        }

        return save(dto);
    }

    @Override
    public List<ProductCategoryDTO> search(ProductCategorySearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search ProductCategory, 'dto' must not be null");
        }
        List<ProductCategory> list = repository.findBySearchDTO(dto);
        return mapper.toDto(list);
    }

}
