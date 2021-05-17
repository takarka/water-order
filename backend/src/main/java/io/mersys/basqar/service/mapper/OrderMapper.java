package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.service.dto.OrderDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper extends DocumentMapper<OrderDTO, Order> {
}
