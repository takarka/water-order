package io.mersys.basqar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.Product;

public interface ProductRepository extends MongoRepository<Product, String>, CustomizedProductRepository {
}
