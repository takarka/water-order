package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.Sample;
import io.mersys.basqar.repository.SampleRepository;
import io.mersys.basqar.service.SampleService;
import io.mersys.basqar.service.dto.SampleDTO;
import io.mersys.basqar.service.dto.SampleSearchDTO;
import io.mersys.basqar.service.mapper.SampleMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {

    private SampleMapper mapper;
    private SampleRepository repository;

    @Override
    public SampleDTO create(SampleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create Sample, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create Sample, 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Sample 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<SampleDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Sample 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<SampleDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private SampleDTO save(SampleDTO dto) {
        Sample doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public SampleDTO update(SampleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update Sample, 'dto' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update Sample, 'name' must not be null");
        }

        return save(dto);
    }

    @Override
    public List<SampleDTO> search(SampleSearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search Sample, 'dto' must not be null");
        }

        List<Sample> list = repository.findBySearchDTO(dto);
        return mapper.toDto(list);
    }

}
