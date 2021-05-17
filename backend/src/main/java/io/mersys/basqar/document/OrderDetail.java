package io.mersys.basqar.document;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class OrderDetail implements Serializable {

    @DBRef(lazy = true)
    private Product product;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;
}
