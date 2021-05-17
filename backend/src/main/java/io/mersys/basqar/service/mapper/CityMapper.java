package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.City;
import io.mersys.basqar.service.dto.CityDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(CityMapperDecorator.class)
public interface CityMapper extends DocumentMapper<CityDTO, City> {
}
