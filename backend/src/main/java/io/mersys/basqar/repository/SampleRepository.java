package io.mersys.basqar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.Sample;

public interface SampleRepository extends MongoRepository<Sample, String>, CustomizedSampleRepository {

    Sample findByName();

    List<Sample> findByNameOrShortNameAllIgnoreCaseLike(String name, String shortName);
}
