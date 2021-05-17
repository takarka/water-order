package io.mersys.basqar.repository.base;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.basqar.document.BaseDocument;

public interface CrudRepository<DOCUMENT extends BaseDocument> extends MongoRepository<DOCUMENT, String>, Repository {
}
