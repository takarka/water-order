package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.Region;
import io.mersys.basqar.repository.RegionRepository;
import io.mersys.basqar.service.RegionService;
import io.mersys.basqar.service.dto.RegionDTO;
import io.mersys.basqar.service.dto.RegionSearchDTO;
import io.mersys.basqar.service.mapper.RegionMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {

    private RegionMapper mapper;
    private RegionRepository repository;

    @Override
    public RegionDTO create(RegionDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create Region, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create Region, 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Region 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<RegionDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Region 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<RegionDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private RegionDTO save(RegionDTO dto) {
        Region doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public RegionDTO update(RegionDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update Region, 'dto' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update Region, 'name' must not be null");
        }

        return save(dto);
    }

    @Override
    public List<RegionDTO> search(RegionSearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search Region, 'dto' must not be null");
        }

        List<Region> list = repository.findBySearchDTO(dto);
        return mapper.toDto(list);
    }

}
