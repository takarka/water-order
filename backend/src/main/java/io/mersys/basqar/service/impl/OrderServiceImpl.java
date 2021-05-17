package io.mersys.basqar.service.impl;

import java.util.List;
import java.util.Optional;

import io.mersys.basqar.document.type.RoleEnum;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.document.OrderStatus;
import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.repository.OrderRepository;
import io.mersys.basqar.repository.UserRepository;
import io.mersys.basqar.security.UserPrincipal;
import io.mersys.basqar.service.OrderService;
import io.mersys.basqar.service.dto.OrderDTO;
import io.mersys.basqar.service.dto.OrderSearchDTO;
import io.mersys.basqar.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderMapper mapper;
	private OrderRepository repository;
	private UserRepository userRepo;

	@Override
	public OrderDTO create(OrderDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To create Order, 'dto' must not be null");
		}
		if (dto.getId() != null) {
			throw new RuntimeException("To create Order, 'id' must be null");
		}

		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));
		Order doc = mapper.toDocument(dto);
		doc.setCreatedBy(principal.getId());
		doc.setUser(user);
		long count = repository.count();
		String orderNumber = String.format("%06d", count);
		doc.setOrderNumber(orderNumber);
		doc = repository.save(doc);
		return mapper.toDto(doc);
	}

	@Override
	public void delete(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Order 'id' is not valid value: '" + id + "'");
		}

		repository.deleteById(id);
	}

	@Override
	public Optional<OrderDTO> get(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Order 'id' is not valid value: '" + id + "'");
		}

		return repository.findById(id).map(mapper::toDto);
	}

	@Override
	public List<OrderDTO> getAllOfCurrentUser() {
		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));

		List<Order> allByUserId = repository.findAllByUserId(user.getId());

		return mapper.toDto(allByUserId);
	}

	@Override
	public List<OrderDTO> getAll() {
		return mapper.toDto(repository.findAll());
	}


	@Override
	public OrderDTO update(OrderDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To update Order, 'dto' must not be null");
		}

		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));
		Order doc = mapper.toDocument(dto);
		doc.setCreatedBy(principal.getId());
		doc.setUser(user);
		doc = repository.save(doc);
		return mapper.toDto(doc);
	}

	@Override
	public List<OrderDTO> search(OrderSearchDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To search Order, 'dto' must not be null");
		}
		List<Order> list = repository.findBySearchDTO(dto);
		return mapper.toDto(list);
	}

	@Override
	public void approve(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("ids null or empty");
		}
		final List<Order> orders = repository.getByIds(ids);
		if (orders == null || orders.isEmpty()) {
			throw new IllegalArgumentException("Orders not found");
		}
		boolean isAllCreated = orders.stream().allMatch(o -> o.getStatus() == OrderStatus.CREATED);
		if (!isAllCreated) {
			throw new IllegalArgumentException("Only orders with CREATED status can be approved");
		}
		orders.forEach(order -> order.setStatus(OrderStatus.APPROVED));
		repository.saveAll(orders);
	}

	@Override
	public void cancel(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("ids null or empty");
		}
		final List<Order> orders = repository.getByIds(ids);
		if (orders == null || orders.isEmpty()) {
			throw new IllegalArgumentException("Orders not found");
		}

		boolean isAllCreated = orders.stream().allMatch(o -> o.getStatus() == OrderStatus.CREATED || o.getStatus() == OrderStatus.APPROVED );
		if (!isAllCreated) {
			throw new IllegalArgumentException("Only orders with CREATED or APPROVED status can be approved");
		}

		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));

		if(user.getRoles() == null){
			throw new IllegalArgumentException("User's roles can not be null!!!");
		}

		boolean isAdmin = user.getRoles().contains(RoleEnum.ADMIN);
		boolean isSupplier = user.getRoles().contains(RoleEnum.SUPPLIER);

		if(isAdmin) {
			orders.forEach(order -> order.setStatus(OrderStatus.CANCELED));
		}else if (isSupplier){
			orders.forEach(order -> order.setManufactureCancel(true));
		}else {
			throw new IllegalArgumentException("Only ADMIN or SUPPLIER can cancel!!!");
		}
		repository.saveAll(orders);
	}

}
