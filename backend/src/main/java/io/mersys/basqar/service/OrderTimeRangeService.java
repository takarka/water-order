package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.OrderTimeRangeDTO;

public interface OrderTimeRangeService {
	List<OrderTimeRangeDTO> getAll();

    Optional<OrderTimeRangeDTO> get(String id);

    OrderTimeRangeDTO create(OrderTimeRangeDTO dto);

    OrderTimeRangeDTO update(OrderTimeRangeDTO dto);

    void delete(String id);
}
