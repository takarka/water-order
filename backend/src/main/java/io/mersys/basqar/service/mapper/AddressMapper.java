package io.mersys.basqar.service.mapper;

import io.mersys.basqar.document.Address;
import io.mersys.basqar.service.dto.AddressDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(AddressMapperDecorator.class)
public interface AddressMapper extends DocumentMapper<AddressDTO, Address> {
}
