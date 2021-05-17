package io.mersys.basqar.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.basqar.document.OrderTimeRange;
import io.mersys.basqar.service.dto.OrderTimeRangeDTO;

@Mapper(componentModel = "spring")
public interface OrderTimeRangeMapper  extends DocumentMapper<OrderTimeRangeDTO, OrderTimeRange> {
}
