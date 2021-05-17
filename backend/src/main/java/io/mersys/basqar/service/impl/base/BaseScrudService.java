package io.mersys.basqar.service.impl.base;

import java.util.List;

import io.mersys.basqar.document.BaseDocument;
import io.mersys.basqar.repository.base.CrudRepository;
import io.mersys.basqar.repository.base.CustomizedRepository;
import io.mersys.basqar.repository.base.Repository;
import io.mersys.basqar.service.base.ScrudService;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;
import io.mersys.basqar.service.mapper.DocumentMapper;

public abstract class BaseScrudService<DOCUMENT extends BaseDocument, DTO extends BaseCrudDTO, SearchDTO extends BaseSearchDTO> extends BaseCrudService<DOCUMENT, DTO> implements ScrudService<DTO, SearchDTO> {


    private DocumentMapper<DTO, DOCUMENT> mapper;
    private Repository repository;
    private Class<DOCUMENT> clazz;

    public BaseScrudService(DocumentMapper<DTO, DOCUMENT> mapper, CrudRepository<DOCUMENT> repository, Class<DOCUMENT> clazz) {
        super(mapper, repository, clazz);
        this.repository = repository;
        this.clazz = clazz;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> search(SearchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To search " + clazz.getName() + ", 'dto' must not be null");
        }

        validate(dto);
        List<DOCUMENT> list = ((CustomizedRepository<DOCUMENT, SearchDTO>) repository).findBySearchDTO(dto);
        return mapper.toDto(list);
    }

    protected void validate(SearchDTO dto) {
    }
}
