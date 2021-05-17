package io.mersys.basqar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.Region;

public interface RegionRepository extends MongoRepository<Region, String>, CustomizedRegionRepository {
}
