package io.mersys.basqar.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.basqar.document.Address;
import io.mersys.basqar.document.OrderStatus;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class OrderDTO extends BaseCrudDTO implements Serializable {


    private String orderNumber;

    private LocalDate orderDate;
    
    private BigDecimal totalQuantity;
    
    private String phone;

    private BigDecimal totalPrice;

    @JsonProperty(value = "deliveryAddress")
    @NotNull
    private AddressDTO deliveryAddressDTO;

    @JsonProperty(value = "orderTimeRange")
    @NotNull
    private OrderTimeRangeDTO orderTimeRangeDTO;

    private String feedback;
    
    private String comment;

    private LocalDateTime estimateDeliveryTime;

    @JsonProperty(value = "details")
    private List<OrderDetailDTO> orderDetailDTOS;

    private OrderStatus status = OrderStatus.CREATED;

    private boolean manufactureCancel;


    private String userName;

}
