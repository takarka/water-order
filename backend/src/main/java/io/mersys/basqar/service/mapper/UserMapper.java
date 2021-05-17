package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.service.dto.UserMngtDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper extends DocumentMapper<UserMngtDTO, User> {
}
