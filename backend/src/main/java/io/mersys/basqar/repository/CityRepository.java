package io.mersys.basqar.repository;

import io.mersys.basqar.document.City;
import io.mersys.basqar.repository.base.CrudRepository;
import io.mersys.basqar.repository.base.Repository;

public interface CityRepository extends Repository, CrudRepository<City>, CustomizedCityRepository
 {
}
