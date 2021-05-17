package io.mersys.basqar.service.mapper;

import java.util.List;

/**
 * @param <DTO>      - DTO type parameter.
 * @param <DOCUMENT> - DOCUMENT type parameter.
 */
public interface DocumentMapper<DTO, DOCUMENT> {

    DOCUMENT toDocument(DTO dto);

    DTO toDto(DOCUMENT document);

    List<DOCUMENT> toDocument(List<DTO> dtoList);

    List<DTO> toDto(List<DOCUMENT> documentList);
}
