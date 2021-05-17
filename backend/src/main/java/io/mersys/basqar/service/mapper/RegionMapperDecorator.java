package io.mersys.basqar.service.mapper;

import io.mersys.basqar.document.Region;
import io.mersys.basqar.service.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class RegionMapperDecorator implements RegionMapper {

	@Autowired
	private RegionMapper mapper;

	@Override
	public Region toDocument(RegionDTO dto) {
		if (dto == null) {
			return null;
		}
		final Region document = mapper.toDocument(dto);
		if (document != null) {
			document.setName(dto.getName());
		}
		return document;
	}

	@Override
	public RegionDTO toDto(Region doc) {
		if (doc == null) {
			return null;
		}
		final RegionDTO dto = mapper.toDto(doc);
		if (dto != null) {
			dto.setName(doc.getName());
		}
		return dto;
	}

	@Override
	public List<Region> toDocument(List<RegionDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<Region> list = new ArrayList<>(dtoList.size());
		for (final RegionDTO RegionDTO : dtoList) {
			list.add(toDocument(RegionDTO));
		}

		return list;
	}

	@Override
	public List<RegionDTO> toDto(List<Region> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<RegionDTO> list = new ArrayList<>(documentList.size());
		for (final Region Region : documentList) {
			list.add(toDto(Region));
		}

		return list;
	}

}
