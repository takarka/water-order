package io.mersys.basqar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.OrderTimeRange;

public interface OrderTimeRangeRepository extends MongoRepository<OrderTimeRange, String>{

}
