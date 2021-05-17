package io.mersys.basqar.repository.base;

import java.util.List;

import io.mersys.basqar.document.BaseDocument;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;

public interface CustomizedRepository<DOCUMENT extends BaseDocument, SearchDTO extends BaseSearchDTO> {
    List<DOCUMENT> findBySearchDTO(SearchDTO dto);

}
