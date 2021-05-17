package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.RegionDTO;
import io.mersys.basqar.service.dto.RegionSearchDTO;

public interface RegionService {

    List<RegionDTO> getAll();

    Optional<RegionDTO> get(String id);

    RegionDTO create(RegionDTO dto);

    RegionDTO update(RegionDTO dto);

    void delete(String id);

    List<RegionDTO> search(RegionSearchDTO dto);
}
