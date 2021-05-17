package io.mersys.basqar.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.basqar.document.Sample;
import io.mersys.basqar.service.dto.SampleDTO;

@Mapper(componentModel = "spring")
public interface SampleMapper extends DocumentMapper<SampleDTO, Sample> {
}
