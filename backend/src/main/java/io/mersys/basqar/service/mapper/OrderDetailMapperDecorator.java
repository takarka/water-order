package io.mersys.basqar.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.mersys.basqar.document.OrderDetail;
import io.mersys.basqar.service.dto.OrderDetailDTO;

public abstract class OrderDetailMapperDecorator implements OrderDetailMapper {

	@Autowired
	private OrderDetailMapper mapper;

	@Autowired
	private ProductMapper prodMapper;

	@Override
	public OrderDetail toDocument(OrderDetailDTO dto) {
		if (dto == null) {
			return null;
		}
		final OrderDetail document = mapper.toDocument(dto);
		if (document != null) {
			document.setProduct(prodMapper.toDocument(dto.getProductDTO()));
		}
		return document;
	}

	@Override
	public OrderDetailDTO toDto(OrderDetail doc) {
		if (doc == null) {
			return null;
		}
		final OrderDetailDTO dto = mapper.toDto(doc);
		if (dto != null) {
			dto.setProductDTO(prodMapper.toDto(doc.getProduct()));
			dto.setPrice(doc.getPrice());
			dto.setQuantity(doc.getQuantity());
			dto.setTotalPrice(doc.getTotalPrice());
		}
		return dto;
	}

	@Override
	public List<OrderDetail> toDocument(List<OrderDetailDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		final List<OrderDetail> list = new ArrayList<>(dtoList.size());
		for (final OrderDetailDTO orderDetailDTO : dtoList) {
			list.add(toDocument(orderDetailDTO));
		}

		return list;
	}

	@Override
	public List<OrderDetailDTO> toDto(List<OrderDetail> documentList) {
		if (documentList == null) {
			return null;
		}

		final List<OrderDetailDTO> list = new ArrayList<>(documentList.size());
		for (final OrderDetail orderDetail : documentList) {
			list.add(toDto(orderDetail));
		}

		return list;
	}

}
