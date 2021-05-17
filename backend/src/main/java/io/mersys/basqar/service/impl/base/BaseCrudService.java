package io.mersys.basqar.service.impl.base;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import io.mersys.basqar.document.BaseDocument;
import io.mersys.basqar.repository.base.CrudRepository;
import io.mersys.basqar.service.base.CrudService;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.service.mapper.DocumentMapper;

public abstract class BaseCrudService<DOCUMENT extends BaseDocument, DTO extends BaseCrudDTO> implements CrudService<DTO> {

    private DocumentMapper<DTO, DOCUMENT> mapper;
    private CrudRepository<DOCUMENT> repository;
    private Class<DOCUMENT> clazz;

    public BaseCrudService(DocumentMapper<DTO, DOCUMENT> mapper, CrudRepository<DOCUMENT> repository, Class<DOCUMENT> clazz) {
        this.mapper = mapper;
        this.repository = repository;
        this.clazz = clazz;
    }


    @Override
    public DTO create(DTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create " + clazz.getName() + ", 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create " + clazz.getName() + ", 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException(clazz.getName() + " 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<DTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException(clazz.getName() + " 'id' is not valid value: '" + id + "'");
        }
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<DTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private DTO save(DTO dto) {
        DOCUMENT doc = mapper.toDocument(dto);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public DTO update(DTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update " + clazz.getName() + ", 'dto' must not be null");
        }
        validate(dto);
        return save(dto);
    }

    private void validate(DTO dto) {
    }


}
