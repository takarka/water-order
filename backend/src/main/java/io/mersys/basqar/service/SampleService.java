package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.SampleDTO;
import io.mersys.basqar.service.dto.SampleSearchDTO;

public interface SampleService {

    List<SampleDTO> getAll();

    Optional<SampleDTO> get(String id);

    SampleDTO create(SampleDTO dto);

    SampleDTO update(SampleDTO dto);

    void delete(String id);

    List<SampleDTO> search(SampleSearchDTO dto);
}
