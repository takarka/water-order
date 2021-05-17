package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.Region;
import io.mersys.basqar.service.dto.RegionDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(RegionMapperDecorator.class)
public interface RegionMapper extends DocumentMapper<RegionDTO, Region> {
}
