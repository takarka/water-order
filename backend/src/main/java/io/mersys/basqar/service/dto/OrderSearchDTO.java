package io.mersys.basqar.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.mersys.basqar.document.OrderStatus;
import io.mersys.basqar.service.dto.base.BaseSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class OrderSearchDTO extends BaseSearchDTO implements Serializable {
    private String orderNumber;
    
    private String phone;

    private LocalDate orderDateStart;
    private LocalDate orderDateEnd;

    private LocalDateTime estimateDeliveryStart;
    private LocalDateTime estimateDeliveryEnd;

    private OrderStatus status;

}
