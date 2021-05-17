package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.Product;
import io.mersys.basqar.repository.ProductRepository;
import io.mersys.basqar.service.ProductService;
import io.mersys.basqar.service.dto.ProductDTO;
import io.mersys.basqar.service.dto.ProductSearchDTO;
import io.mersys.basqar.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductMapper mapper;
    private ProductRepository repository;

    @Override
    public ProductDTO create(ProductDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create Product, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create Product, 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Product 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<ProductDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Product 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private ProductDTO save(ProductDTO dto) {
        Product doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update Product, 'dto' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update Product, 'name' must not be null");
        }

        return save(dto);
    }

    @Override
    public List<ProductDTO> search(ProductSearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search Product, 'dto' must not be null");
        }

        List<Product> list = repository.findBySearchDTO(dto);
        return mapper.toDto(list);
    }

}
