package io.mersys.basqar.service.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.service.dto.OrderDTO;

public abstract class OrderMapperDecorator implements OrderMapper {

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrderDetailMapper detailMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private OrderTimeRangeMapper orderTimeRangeMapper;

	@Override
	public OrderDTO toDto(Order document) {
		OrderDTO dto = mapper.toDto(document);
		if (dto != null) {
			dto.setOrderDetailDTOS(document.getDetails() != null ? detailMapper.toDto(document.getDetails())
					: Collections.emptyList());
			String userName = document.getUser() != null ? document.getUser().getName() : "";
			dto.setUserName(userName);

			dto.setId(document.getId());
			dto.setOrderNumber(document.getOrderNumber());
			dto.setPhone(document.getPhone());
			dto.setTotalQuantity(document.getTotalQuantity());
			dto.setTotalPrice(document.getTotalPrice());
			dto.setDeliveryAddressDTO(addressMapper.toDto(document.getDeliveryAddress()));
			dto.setOrderTimeRangeDTO(orderTimeRangeMapper.toDto(document.getOrderTimeRange()));
			dto.setFeedback(document.getFeedback());
			dto.setComment(document.getComment());
			dto.setEstimateDeliveryTime(document.getEstimateDeliveryTime());
			dto.setStatus(document.getStatus());
		}
		return dto;
	}

	@Override
	public Order toDocument(OrderDTO dto) {
		Order document = mapper.toDocument(dto);
		if (document != null) {
			document.setDetails(dto.getOrderDetailDTOS() != null ? detailMapper.toDocument(dto.getOrderDetailDTOS())
					: Collections.emptyList());
			document.setDeliveryAddress(addressMapper.toDocument(dto.getDeliveryAddressDTO()));
			document.setOrderTimeRange(orderTimeRangeMapper.toDocument(dto.getOrderTimeRangeDTO()));
		}
		return document;
	}

	@Override
	public List<Order> toDocument(List<OrderDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<Order> list = new ArrayList<>(dtoList.size());
		for (final OrderDTO orderDTO : dtoList) {
			list.add(toDocument(orderDTO));
		}

		return list;
	}

	@Override
	public List<OrderDTO> toDto(List<Order> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<OrderDTO> list = new ArrayList<>(documentList.size());
		for (final Order order : documentList) {
			list.add(toDto(order));
		}

		return list;
	}
}
