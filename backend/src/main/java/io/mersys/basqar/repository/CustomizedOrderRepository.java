package io.mersys.basqar.repository;

import java.util.List;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.repository.base.CustomizedRepository;
import io.mersys.basqar.repository.base.Repository;
import io.mersys.basqar.service.dto.OrderSearchDTO;

public interface CustomizedOrderRepository extends Repository, CustomizedRepository<Order, OrderSearchDTO> {
	List<Order> getByIds(List<String> ids);

	List<Order> findAllByUserId(String id);
}
