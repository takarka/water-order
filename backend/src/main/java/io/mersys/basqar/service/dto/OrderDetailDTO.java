package io.mersys.basqar.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class OrderDetailDTO implements Serializable {

    @JsonProperty(value = "product")
    private ProductDTO productDTO;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;

}
