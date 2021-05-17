package io.mersys.basqar.service.mapper;

import io.mersys.basqar.document.Address;
import io.mersys.basqar.service.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AddressMapperDecorator implements AddressMapper {

	@Autowired
	private AddressMapper mapper;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public Address toDocument(AddressDTO dto) {
		if (dto == null) {
			return null;
		}
		final Address document = mapper.toDocument(dto);
		if (document != null) {
			document.setCity(cityMapper.toDocument(dto.getCityDTO()));
			document.setRegion(regionMapper.toDocument(dto.getRegionDTO()));
		}
		return document;
	}

	@Override
	public AddressDTO toDto(Address doc) {
		if (doc == null) {
			return null;
		}
		final AddressDTO dto = mapper.toDto(doc);
		if (dto != null) {
			dto.setCityDTO(cityMapper.toDto(doc.getCity()));
			dto.setRegionDTO(regionMapper.toDto(doc.getRegion()));
		}
		return dto;
	}

	@Override
	public List<Address> toDocument(List<AddressDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<Address> list = new ArrayList<>(dtoList.size());
		for (final AddressDTO AddressDTO : dtoList) {
			list.add(toDocument(AddressDTO));
		}

		return list;
	}

	@Override
	public List<AddressDTO> toDto(List<Address> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<AddressDTO> list = new ArrayList<>(documentList.size());
		for (final Address Address : documentList) {
			list.add(toDto(Address));
		}

		return list;
	}

}
