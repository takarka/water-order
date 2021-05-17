package io.mersys.basqar.repository;

import java.util.List;

import io.mersys.basqar.document.Sample;
import io.mersys.basqar.service.dto.SampleSearchDTO;

public interface CustomizedSampleRepository {

    List<Sample> findBySearchDTO(SampleSearchDTO dto);

}
