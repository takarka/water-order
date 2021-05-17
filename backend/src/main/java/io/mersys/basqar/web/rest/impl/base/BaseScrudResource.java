package io.mersys.basqar.web.rest.impl.base;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.mersys.basqar.service.Service;
import io.mersys.basqar.service.base.ScrudService;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;
import io.mersys.basqar.web.rest.base.ScrudResource;

public class BaseScrudResource<DTO extends BaseCrudDTO, SearchDTO extends BaseSearchDTO> extends BaseCrudResource implements ScrudResource<DTO, SearchDTO> {

    private ScrudService<DTO, SearchDTO> service;

    public BaseScrudResource(Service service) {
        super(service);
        this.service = (ScrudService<DTO, SearchDTO>) service;
    }

    @Override
    public ResponseEntity<List<DTO>> getBySearch(SearchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
    }
}
