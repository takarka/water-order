package io.mersys.basqar.repository;

import io.mersys.basqar.document.City;
import io.mersys.basqar.repository.base.CustomizedRepository;
import io.mersys.basqar.repository.base.Repository;
import io.mersys.basqar.service.dto.CitySearchDTO;

public interface CustomizedCityRepository extends Repository, CustomizedRepository<City, CitySearchDTO> {
}
