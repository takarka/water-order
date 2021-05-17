package io.mersys.basqar.service.mapper;

import io.mersys.basqar.document.City;
import io.mersys.basqar.service.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class CityMapperDecorator implements CityMapper {

	@Autowired
	private CityMapper mapper;

	@Override
	public City toDocument(CityDTO dto) {
		if (dto == null) {
			return null;
		}
		final City document = mapper.toDocument(dto);
		if (document != null) {
			document.setName(dto.getName());
		}
		return document;
	}

	@Override
	public CityDTO toDto(City doc) {
		if (doc == null) {
			return null;
		}
		final CityDTO dto = mapper.toDto(doc);
		if (dto != null) {
			dto.setName(doc.getName());
		}
		return dto;
	}

	@Override
	public List<City> toDocument(List<CityDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<City> list = new ArrayList<>(dtoList.size());
		for (final CityDTO CityDTO : dtoList) {
			list.add(toDocument(CityDTO));
		}

		return list;
	}

	@Override
	public List<CityDTO> toDto(List<City> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<CityDTO> list = new ArrayList<>(documentList.size());
		for (final City City : documentList) {
			list.add(toDto(City));
		}

		return list;
	}

}
