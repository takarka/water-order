package io.mersys.basqar.web.rest.base;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;

public interface ScrudResource<DTO extends BaseCrudDTO, SearchDTO extends BaseSearchDTO> extends CrudResource {

    @PostMapping(value = "/search")
    ResponseEntity<List<DTO>> getBySearch(@Valid @RequestBody SearchDTO dto);

}
