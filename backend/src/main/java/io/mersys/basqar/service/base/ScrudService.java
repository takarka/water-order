package io.mersys.basqar.service.base;

import java.util.List;

import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;

public interface ScrudService<DTO extends BaseCrudDTO, SearchDTO extends BaseSearchDTO> extends CrudService<DTO> {

    List<DTO> search(SearchDTO dto);

}