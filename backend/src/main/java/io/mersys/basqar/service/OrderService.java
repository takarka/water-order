package io.mersys.basqar.service;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.service.dto.OrderDTO;
import io.mersys.basqar.service.dto.OrderSearchDTO;

public interface OrderService {
	List<OrderDTO> getAll();

	Optional<OrderDTO> get(String id);

	List<OrderDTO> getAllOfCurrentUser();

	OrderDTO create(OrderDTO dto);

	OrderDTO update(OrderDTO dto);

	void delete(String id);

	List<OrderDTO> search(OrderSearchDTO dto);

	void approve(List<String> ids);

    void cancel(List<String> ids);
}
