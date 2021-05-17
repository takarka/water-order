package io.mersys.basqar.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import io.mersys.basqar.document.OrderDetail;
import io.mersys.basqar.service.dto.OrderDetailDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderDetailMapperDecorator.class)
public interface OrderDetailMapper extends DocumentMapper<OrderDetailDTO, OrderDetail> {
}
