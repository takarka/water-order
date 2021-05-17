package io.mersys.basqar.document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.mersys.basqar.document.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("order")
@TypeAlias("Order")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseDocument implements Serializable {

	@NotBlank
	private String orderNumber;

	private LocalDate orderDate;

	private String phone;

	private BigDecimal totalQuantity;

	private BigDecimal totalPrice;

	@JsonManagedReference
	@NotNull
	@DBRef(lazy = true)
	private Address deliveryAddress;

	@JsonManagedReference
	@NotNull
	@DBRef(lazy = true)
	private OrderTimeRange orderTimeRange;

	private String feedback;

	private String comment;

	private LocalDateTime estimateDeliveryTime;

	private List<OrderDetail> details;

	private OrderStatus status = OrderStatus.CREATED;

	private boolean manufactureCancel = false;

	@JsonManagedReference
	@NotNull
	@DBRef(lazy = true)
	private User user;

}
