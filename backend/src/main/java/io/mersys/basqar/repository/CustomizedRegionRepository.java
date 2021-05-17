package io.mersys.basqar.repository;

import java.util.List;

import io.mersys.basqar.document.Region;
import io.mersys.basqar.service.dto.RegionSearchDTO;

public interface CustomizedRegionRepository {

    List<Region> findBySearchDTO(RegionSearchDTO dto);

}
