package io.mersys.basqar.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.City;
import io.mersys.basqar.repository.CityRepository;
import io.mersys.basqar.service.CityService;
import io.mersys.basqar.service.dto.CityDTO;
import io.mersys.basqar.service.dto.CitySearchDTO;
import io.mersys.basqar.service.impl.base.BaseScrudService;
import io.mersys.basqar.service.mapper.CityMapper;

@Service
@Transactional
public class CityServiceImpl extends BaseScrudService<City, CityDTO, CitySearchDTO> implements CityService {

    private CityMapper mapper;
    private CityRepository repository;

    public CityServiceImpl(CityMapper mapper, CityRepository repository) {
        super(mapper, repository, City.class);
        this.mapper = mapper;
        this.repository = repository;
    }

}
