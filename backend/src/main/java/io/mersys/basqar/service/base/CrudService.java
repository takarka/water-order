package io.mersys.basqar.service.base;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.Service;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;

public interface CrudService<DTO extends BaseCrudDTO> extends Service {

    List<DTO> getAll();

    Optional<DTO> get(String id);

    DTO create(DTO dto);

    DTO update(DTO dto);

    void delete(String id);
}
