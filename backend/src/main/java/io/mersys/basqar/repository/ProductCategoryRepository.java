package io.mersys.basqar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.ProductCategory;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String>, CustomizedProductCategoryRepository {
}
